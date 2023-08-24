import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Formato(
    @SerialName("app_id")
    val appId: Int? = null,
    @SerialName("argument")
    val argument: Int? = null,
    @SerialName("comment")
    val comment: String? = null,
    @SerialName("created_datetime")
    val createdDatetime: String? = null,
    @SerialName("has_answer")
    val hasAnswer: Boolean? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image")
    val image: Image? = null,
    @SerialName("is_active")
    val isActive: Boolean? = null,
    @SerialName("license_type")
    val licenseType: Int? = null,
    @SerialName("manual")
    val manual: Int? = null,
    @SerialName("modified_datetime")
    val modifiedDatetime: String? = null,
    @SerialName("original_id")
    val originalId: String? = null,
    @SerialName("position")
    val position: Int? = null,
    @SerialName("quizanswer_set")
    val quizanswerSet: List<String?>? = null,
    @SerialName("result")
    val result: Boolean? = null,
    @SerialName("symbol")
    val symbol: String? = null,
    @SerialName("text")
    val text: String? = null
)

@Serializable
data class Image(
    @SerialName("aspect_ratio")
    val aspectRatio: Double? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("image_hd")
    val imageHd: String? = null,
    @SerialName("symbol")
    val symbol: String? = null,
    @SerialName("user")
    val user: Int? = null
)