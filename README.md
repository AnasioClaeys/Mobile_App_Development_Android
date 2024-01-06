# Mobile_App_Development_Android: Game-Application

### Gemaakt door Anasio Claeys voor het vak Mobile Application Development: Android

### Klasgroep: G3A2

### Academiejaar: 2023-2024

### Opleiding: Bachelor in de Toegepaste Informatica

---

## Extra informatie

Voor extra informatie over de applicatie, kan KDoc gegenereerd worden. Dit kan door de volgende commando's uit te voeren als Gradle Task:

```bash
./gradlew dokkaHtml
```

Voor deze applicatie is er gebruik gemaakt van de RAWG API. Deze API is te vinden op https://rawg.io/apidocs. De API is volledig gratis te gebruiken tot
20 000 requests per maand. De API is een game database met meer dan 500 000 games. De API key kan verkregen worden via de website. Hierbij moet dan een package "keys" gemaakt worden in de root folder van de applicatie zelf. Hierin wordt dan een kotlin file "ApiKeys.kt" gemaakt. In deze file wordt dan de volgende code geplaatst:

```kotlin
object ApiKeys {
    val API_KEY = UW_API_KEY
}
```
