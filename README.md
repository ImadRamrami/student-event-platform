# student-event-platform
POC d'une plateforme de gestion d'événements étudiants. Projet de groupe démontrant une approche DevOps complète (GitFlow, CI/CD, Docker).

## Comment lancer l'application

### Pré-requis
- Java 17 ou supérieur
- Maven (ou utiliser le wrapper inclus)

> [!IMPORTANT]
> Assurez-vous que votre variable d'environnement `JAVA_HOME` pointe bien vers un JDK (et non un JRE).
> Si vous avez une erreur "No compiler is provided", c'est que `JAVA_HOME` est mal configuré.
> 
> **Solution rapide (Windows PowerShell) :**
> ```powershell
> $env:JAVA_HOME = 'C:\Users\Imad\AppData\Local\Programs\Eclipse Adoptium\jdk-21.0.6.7-hotspot'
> ./mvnw spring-boot:run
> ```

### Lancement
Ouvrez un terminal à la racine du projet et lancez :

Sur Windows :
```powershell
./mvnw spring-boot:run
```

Sur Mac/Linux :
```bash
./mvnw spring-boot:run
```

L'application démarrera sur `http://localhost:8080`.

### Tester les fonctionnalités

1. **Créer un événement** :
   - Allez sur [http://localhost:8080/event/new](http://localhost:8080/event/new)
   - Remplissez le formulaire.
   - Cliquez sur "Enregistrer".
   - Vous serez redirigé vers l'accueil (`/`), ce qui affichera une page d'erreur 404 (WhiteLabel Error Page) pour l'instant car la page d'accueil n'est pas encore créée. C'est normal !

2. **Vérifier la base de données (Optionnel)** :
   - Si vous avez une console H2 active ou des logs, vous pourrez voir l'insertion.
