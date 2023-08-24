import fuel.httpGet
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URI
import kotlin.system.exitProcess

private val json = Json {
    prettyPrint = true
    isLenient = true
}

suspend fun main(args: Array<String>) {
    val ids = Json.decodeFromString<List<Int>>(File("ids.json").readText()).toMutableList()
    var scraped = Json.decodeFromString<List<Formato>>(File("scraped.json").readText()).toMutableList()
    val toScrape = "https://app.guidaevai.com/api/quizzes/generate_sheet/?license_type=11" //patente B

    val newIds = mutableListOf<Int>()
    scraped = scraped.filter {
        if (newIds.contains(it.appId ?: 0)) {
            println("trovato duplicato: ${it.appId}")
            return@filter false
        } else {
            newIds.add(it.appId ?: 0)
            return@filter true
        }
    }.toMutableList()

    File("scraped.json").writeText(
        json.encodeToString(ListSerializer(Formato.serializer()), scraped)
    )

    println("new size. ${scraped.count()}")

    if (args.contains("img"))
        scaricaImmagini(scraped)

    while (true) {
        try {
            val mappa =
                Json.decodeFromString<List<Formato>>(toScrape.httpGet().body).filterNot { ids.contains(it.appId ?: 0) }
            scraped.addAll(mappa)
            ids.addAll(mappa.map { it.appId ?: 0 })

            println("addate ${mappa.count()}, siamo a ${scraped.count()}")

            File("scraped.json").writeText(
                json.encodeToString(ListSerializer(Formato.serializer()), scraped)
            )

            File("ids.json").writeText(
                json.encodeToString(ListSerializer(Int.serializer()), ids)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            break
        }
    }
}

fun scaricaImmagini(scraped: MutableList<Formato>) {
    println("Scarico tutte le immagini")

    val folder = File("img/").apply { mkdirs() }
    val giaFatti = mutableListOf<String>().also { it.addAll(folder.listFiles()?.map { it.name } ?: emptyList()) }

    scraped.forEach {
        val link = it.image?.image ?: return@forEach
        val id = "pic${it.image.id ?: return@forEach}.png"

        if (giaFatti.contains(id))
            return@forEach

        try {
            BufferedInputStream(URI(link).toURL().openStream()).use { `in` ->
                FileOutputStream(File(folder, id)).use { fileOutputStream ->
                    val dataBuffer = ByteArray(1024)
                    var bytesRead: Int
                    while (`in`.read(dataBuffer, 0, 1024).also { bytesRead = it } != -1) {
                        fileOutputStream.write(dataBuffer, 0, bytesRead)
                    }
                }
            }

            println("scaricata img $id")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        giaFatti.add(id)
    }

    println("finito!")

    exitProcess(0)
}
