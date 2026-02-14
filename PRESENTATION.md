# 🎓 Soutenance de Projet : Student Event Platform
**Durée : 15 minutes** | **Cible : Investisseur / Auditeur Technique**

---

## 1. Pitch du Besoin (3 min)
**Accroche :** "Aujourd'hui, la vie étudiante est fragmentée. Les événements se perdent sur Facebook, Instagram ou des tableaux d'affichage obsolètes."

*   **Le Problème :** Centralisation inexistante, communication ratée, places perdues.
*   **La Solution :** *Student Event Platform*. Une application **moderne**, **centralisée**, et conçu pour être **évolutive**.
*   **Pourquoi nous ?**
    1.  **Expérience Utilisateur Premium** ("Future Glass" Design).
    2.  **Robustesse Technique** (Architecture Java Spring Boot + DevOps).

---

## 2. Démonstration (5 min)
*Scénario de démo à suivre en direct :*

### Étape 1 : L'Effet "Wow" (Page d'accueil)
*   *Action :* Ouvrir `http://localhost:8080`.
*   *Discours :* "Dès l'arrivée, on ne voit pas un simple site, mais une expérience. Notez les **animations fluides**, les **cartes 3D interactives** qui réagissent à la souris, et cet **arrière-plan dynamique**."

### Étape 2 : Création de Compte & Connexion
*   *Action :* Cliquer sur "Sign Up". Créer un compte "Organisateur".
*   *Discours :* "La sécurité est native. Mots de passe chiffrés, protection CSRF."

### Étape 3 : Création d'un Événement
*   *Action :* Remplir le formulaire.
*   *Titre :* "Gala de fin d'année"
*   *Description :* "La soirée inoubliable..."
*   *Places :* 50
*   *Discours :* "L'interface est intuitive. Tout est validé en temps réel."

### Étape 4 : Inscription & Désinscription (Cycle Complet)
*   *Action :* Aller sur l'événement créé.
*   *Action :* Cliquer sur **"Register Now"**. (Confettis !)
*   *Observation :* Le bouton devient rouge **"Unregister"**. La jauge diminue.
*   *Action :* Cliquer sur "Unregister".
*   *Discours :* "Fluidité totale. On s'inscrit, on change d'avis, le système gère la cohérence des données instantanément."

---

## 3. Explications Techniques (5 min)
*Transition :* "Derrière cette fluidité, il y a une ingénierie rigoureuse."

### Architecture & Design
*   **Backend :** Java 17 + Spring Boot 3 + **Spring Security 6**.
*   **Structure :** Clean Architecture (Controller -> Service -> Repository).
*   **Base de Données :** H2 (Dev) / MySQL Ready (Prod).

### DevOps & CI/CD (L'Argumentaire Investisseur)
*   *Support :* Montrer le fichier `DEVOPS.md`.
*   **Docker :** "Application conteneurisée. Prête pour le Cloud."
*   **CI Pipeline :** "Chaque ligne de code est testée automatiquement."

---

## 4. Questions / Réponses (2 min)
*Anticipation des questions :*
*   *Q: La sécurité ?* -> R: Authentification robuste avec Spring Security et BCrypt.
*   *Q: Gestion des erreurs ?* -> R: Gestion centralisée des exceptions (doublons, crashs) avec feedback utilisateur clair.

---

## ✅ Checklist Avant Soutenance
- [ ] Lancer l'app (`.\mvnw spring-boot:run` ou Docker).
- [ ] Avoir une page propre (pas d'événements tests "toto").
- [ ] Ouvrir les slides ou ce plan sur un deuxième écran.
