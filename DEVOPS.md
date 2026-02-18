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

### 3. Infrastructure as Code & Orchestration
Pour répondre aux exigences modernes (et aux TDs avancés), nous avons intégré :
*   **Java 21** : Utilisation des dernières fonctionnalités LTS et de l'image Docker optimisée `eclipse-temurin:21-jre`.
*   **Kubernetes (K8s)** : Manifestes de déploiement (`k8s/`) pour orchestrer les conteneurs (Pods, Services).
*   **Terraform** : Configuration IaC (`terraform/`) pour provisionner l'infrastructure de manière déclarative.
*   **Monitoring** : Endpoints Prometheus activés via Spring Boot Actuator.
*   **Sécurité** : Scan de vulnérabilités **Trivy** intégré dans le pipeline CI.

---

## 🔐 Configuration des Secrets (CI/CD)

Pour que le pipeline GitHub Actions puisse publier l'image sur Docker Hub, les variables suivantes doivent être définies dans **Settings > Secrets and variables > Actions** du dépôt GitHub :

| Nom du Secret | Valeur à renseigner | Description |
| :--- | :--- | :--- |
| `DOCKERHUB_USERNAME` | Votre identifiant Docker Hub | Ex: `imadramrami` |
| `DOCKERHUB_ACCESS_TOKEN` | Votre Token d'accès (PAT) | Généré sur [hub.docker.com](https://hub.docker.com/settings/security) |

> **Note de Sécurité :** Nous n'utilisons jamais le mot de passe brut, mais un *Access Token* pour assurer une révocation facile en cas de compromission.

---

## 💻 Guide de Démarrage Rapide

Pour un investisseur ou un auditeur technique souhaitant tester l'application localement, nous avons simplifié le processus grâce à Docker.

### Prérequis
*   Docker Desktop installé.
*   (Optionnel) Minikube / Kind pour K8s.

### Commandes

1.  **Construire l'application**
    Cette commande compile le code et package l'application dans une image optimisée.
    ```bash
    docker build -t student-event-platform .
    ```

2.  **Lancer avec Docker**
    Démarre l'application dans un conteneur isolé, accessible sur le port 8080.
    ```bash
    docker run -p 8080:8080 student-event-platform
    ```

3.  **Déployer sur K8s (Exemple)**
    ```bash
    kubectl apply -f k8s/deployment.yaml
    kubectl apply -f k8s/service.yaml
    ```

Une fois lancée, l'application est accessible à l'adresse : `http://localhost:8080`

---
*Ce document témoigne de notre engagement envers l'excellence technique, garantissant un produit stable, maintenable et prêt pour la croissance.*

---

## 📝 Annexe : Générer un Token Docker Hub

Pour obtenir le `DOCKERHUB_ACCESS_TOKEN`, suivez ces étapes précises :

1.  Connectez-vous sur [hub.docker.com](https://hub.docker.com/).
2.  Cliquez sur votre avatar (en haut à droite) > **Account Settings**.
3.  Allez dans l'onglet **Security**.
4.  Cliquez sur le bouton bleu **New Access Token**.
5.  Remplissez le formulaire :
    *   **Description** : `GitHub Actions CI`
    *   **Access permissions** : `Read, Write, Delete`
6.  Cliquez sur **Generate**.
7.  **Copiez immédiatement le token** (il ne sera plus jamais affiché). C'est cette valeur qu'il faut coller dans GitHub Secrets.
