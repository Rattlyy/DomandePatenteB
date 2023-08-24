# ScuolaGuidaScraper
Scarica tutte le domande & risposte del quiz per la patente B dal sito https://guidaevai.com/
PS: Educational purposes only :)

## How to
- `gradle build`
- `java -jar <build jar>`

Ci vorrà circa mezz'ora, se non vieni rate-limitato (Il server manda solo 30 domande casuali). 
Dopo che continua a ottenere 0, ferma il programma (finite le domande)
Tutte le domande si trovano in scraped.json, col formato preso dal sito.

- `java -jar <build jar> --img`

Scarica tutte le immagini nella cartella `img/`.

## Reasons
Non esiste nessuna fonte che riporta le domande per i quiz, quindi noi plebei siam condannati allo scraping.