# Stratégie DevOps & CI/CD

Ce document décrit notre approche d'ingénierie logicielle, axée sur la fiabilité, la qualité et la rapidité de déploiement. Notre infrastructure repose sur des standards industriels éprouvés (**Docker** et **GitHub Actions**) pour garantir un produit robuste et évolutif.

## 🚀 Pourquoi cette infrastructure ?

### 1. Docker : Reproductibilité et Isolation
L'utilisation de conteneurs Docker est un choix stratégique pour assurer la stabilité de notre application :
*   **"Write once, run anywhere"** : L'environnement de développement est strictement identique à celui de production. Cela élimine totalement le fameux "ça marche sur ma machine" et réduit drastiquement les bugs liés aux environnements.
*   **Isolation totale** : L'application embarque ses propres dépendances (Java, bibliothèques). Elle ne dépend pas de la configuration du serveur hôte, ce qui simplifie le déploiement et la maintenance.
*   **Scalabilité** : Les conteneurs standardisés permettent de monter en charge rapidement sur n'importe quelle plateforme cloud.

### 2. GitHub Actions : Qualité et Automatisation (CI/CD)
Nous avons mis en place un pipeline d'Intégration Continue (CI) automatisé pour maintenir une qualité de code irréprochable :
*   **Feedback immédiat** : Chaque modification de code est automatiquement testée. Si une régresssion est introduite, l'équipe est alertée instantanément.
*   **Sécurité** : Le code ne peut être considéré comme valide que si tous les tests automatisés passent.
*   **Efficacité** : Les développeurs se concentrent sur la création de valeur (features) plutôt que sur des tâches manuelles répétitives et sujettes à l'erreur.

---

## 🛠 Le Pipeline CI/CD Actuel

Notre workflow automatisé (`.github/workflows/ci.yml`) s'exécute à chaque modification sur les branches principales. Il suit un processus rigoureux en plusieurs étapes :

1.  **Récupération du Code** : Checkout de la dernière version du code source.
2.  **Configuration de l'Environnement** : Installation d'un environnement Java 17 propre (Eclipse Temurin).
3.  **Build & Tests Automatisés** (`Maven Verify`) :
    *   Compilation du projet.
    *   Exécution de l'ensemble des tests unitaires et d'intégration.
    *   *Si cette étape échoue, le pipeline s'arrête immédiatement pour prévenir l'intégration de code défectueux.*
4.  **Construction de l'Image Docker** :
    *   Une fois le code validé, une image Docker de production est construite.
    *   Cette image est autonome et prête à être déployée.

---

## 💻 Guide de Démarrage Rapide

Pour un investisseur ou un auditeur technique souhaitant tester l'application localement, nous avons simplifié le processus grâce à Docker.

### Prérequis
*   Docker Desktop installé et lancé.

### Commandes

1.  **Construire l'application**
    Cette commande compile le code et package l'application dans une image optimisée.
    ```bash
    docker build -t student-event-platform .
    ```

2.  **Lancer l'application**
    Démarre l'application dans un conteneur isolé, accessible sur le port 8080.
    ```bash
    docker run -p 8080:8080 student-event-platform
    ```

Une fois lancée, l'application est accessible à l'adresse : `http://localhost:8080`

---
*Ce document témoigne de notre engagement envers l'excellence technique, garantissant un produit stable, maintenable et prêt pour la croissance.*
