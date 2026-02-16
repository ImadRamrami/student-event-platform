# 🎓 Student Event Platform

Bienvenue sur la **Student Event Platform**, une solution moderne pour gérer et rejoindre des événements sur le campus.
Ce projet a été réalisé dans le cadre de notre cursus universitaire, mettant en œuvre une approche **DevOps** et une architecture **M-V-C** robuste avec Spring Boot.

---

## 🚀 Fonctionnalités Clés

### 🎨 Expérience Utilisateur (UX/UI)
- **Design "Glassmorphism"** : Interface moderne et épurée avec effets de flou et transparence.
- **Micro-interactions** : Animations fluides, effets de survol 3D (Tilt.js) et feedback visuel instantané.
- **Responsive** : Compatible mobile et desktop grâce à Bootstrap 5.

### 👤 Gestion des Utilisateurs
- **Authentification Sécurisée** : Inscription et connexion gérées par Spring Security.
- **Rôles** : Distinction entre visiteurs et utilisateurs connectés.
- **Tableau de Bord** : Vue personnalisée "Mes Événements" (créés et rejoints).

### 📅 Gestion des Événements
- **Création Simple** : Formulaire intuitif pour proposer un nouvel événement.
- **Inscription / Désinscription** : Rejoignez ou quittez un événement en un clic.
- **Gestion de la Capacité** : Jauge de places en temps réel (ex: "5 places restantes").
- **Sécurité** : Impossible de s'inscrire deux fois ou de dépasser la capacité.

---

## 🛠️ Stack Technique

- **Backend** : Java 25, Spring Boot 3+ (Spring MVC, Spring Data JPA, Spring Security).
- **Base de Données** : H2 (In-Memory) pour le développement rapide.
- **Frontend** : Thymeleaf, Bootstrap 5, CSS3 (Variables & Animations), JavaScript (Vanilla + Librairies externes).
- **Outils** : Maven, Docker (compatible), Git.

---

## ⚙️ Installation et Lancement

### Pré-requis
- JDK 25 installé.
- Maven (optionnel, le wrapper `mvnw` est fourni).

### Démarrage Rapide

1. **Cloner le projet**
   ```bash
   git clone https://github.com/ImadRamrami/student-event-platform.git
   cd student-event-platform
   ```

2. **Lancer l'application**
   *Sur Windows :*
   ```powershell
   ./mvnw spring-boot:run
   ```
   *Sur Mac/Linux :*
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Accéder à l'application**
   Ouvrez votre navigateur sur : [http://localhost:8080](http://localhost:8080)

---

## 📖 Guide d'Utilisation

1. **Visiteur** : Vous pouvez voir la liste des événements.
2. **Inscription** : Créez un compte via le lien "Sign Up" en haut à droite.
3. **Connexion** : Connectez-vous pour débloquer les fonctionnalités.
4. **Participer** : Cliquez sur "View Details" d'un événement, puis "Register Now".
5. **Organiser** : Utilisez le bouton "Create Event" pour ajouter le vôtre.

---

## 👥 Équipe Projet

Projet développé avec passion pour le module de Développement Web & DevOps.
*Code propre, testes et intégration continue.*
