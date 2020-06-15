# LB2 - Vorlage (M226B) // IN18-22

## Beschreibung

Diese Vorlage soll Ihnen verschiedene Dinge, hinsichtlich der LB2 im Modul M226B, zeigen:

* Basisimplementierung von einem Spring-Boot-Projekt
* MVC (Web)-Erweiterung in Spring Boot-Applikationen
* REST-Erweiterung in Spring Boot-Applikationen
* JPA-Erweiterung in Spring Boot-Applikationen
* Anbindung einer MySQL-Datenbank 
* docker-compose-Projekt um einen MySQL-Serverdienst laufen zu lassen

## Starten / Weiterentwicklung

Wollen Sie die Applikation starten, und auch daran entwickeln, gehen Sie bitte wie folgt vor:

1. Starten Sie den Datenbankserverdienst wie folgt:
    ```bash
    cd database # wechseln Sie in das Verzeichnis database
    docker-compose up # damit wird der Docker-Container für MySQL gestartet
    ```

1. Importieren Sie den Ordner in ein leeres VS-Code-Windows (_New Empty Window -> Open Folder_)
1. Starten Sie Applikation über das _SPRING-BOOT-DASHBOARD_
1. Zugriff ist jetzt möglich über _http://localhost:8080_

## Start der Applikation (ohne Debugging und LiveReload)

Wollen Sie die Applikation einfach starten (ohne daran zu entwicklen), kann man die Applikation auch mittels dem folgenden Maven Befehl starten:

```
mvn spring-boot:run
```
