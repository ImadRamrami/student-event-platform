# 💸 FinOps & Métrologie : Suivi des Coûts

Dans une approche **FinOps**, chaque ressource consommée a un coût. Pour optimiser notre facture cloud (AWS, Azure, ou Kubernetes), nous devons surveiller précisément la consommation de nos conteneurs, en particulier la **RAM** et le **CPU**.

Nous utilisons **Spring Boot Actuator** pour exposer ces métriques vitales.

## 📈 Dashboard FinOps (Nouveau !)

Pour une visualisation en temps réel et "Manager-friendly" :
👉 **[http://localhost:8080/finops](http://localhost:8080/finops)**

Ce tableau de bord affiche :
*   Le coût estimé de la RAM par heure.
*   Un graphique d'évolution en temps réel.
*   Des alertes automatiques si le CPU est sous/sur-utilisé.

## 📊 Accéder aux Métriques Brutes

Les endpoints de monitoring sont accessibles publiquement pour faciliter le scraping par des outils comme Prometheus.

### 1. Consommation RAM (Mémoire)
La mémoire est souvent le facteur limitant qui détermine la taille ("T-shirt size") et donc le prix de nos instances/pods.

*   **URL** : `http://localhost:8080/actuator/metrics/jvm.memory.used`
*   **Interprétation** :
    *   `value` : Quantité de mémoire utilisée en octets.
    *   **Action FinOps** : Si cette valeur est constamment basse par rapport à la limite allouée (ex: 512Mo alloués pour 50Mo utilisés), nous pouvons *downscale* le conteneur pour économiser de l'argent.

### 2. Utilisation CPU
*   **URL** : `http://localhost:8080/actuator/metrics/process.cpu.usage`
*   **Interprétation** :
    *   `value` : Pourcentage d'utilisation du CPU (0.0 à 1.0).
    *   **Action FinOps** : Un CPU sous-utilisé indique une instance trop puissante.

### 3. Endpoint Prometheus (Global)
Pour une visualisation dans Grafana :
*   **URL** : `http://localhost:8080/actuator/prometheus`
*   **Usage** : Cet endpoint expose toutes les métriques au format compatible Prometheus pour l'ingestion automatique.

---

## 🛠 Commandes de Test

Pour vérifier rapidement la consommation actuelle sans outil externe :

```bash
# Vérifier la RAM utilisée (en octets)
curl http://localhost:8080/actuator/metrics/jvm.memory.used

# Vérifier la santé de l'application
curl http://localhost:8080/actuator/health
```

> **Note** : En production, ces endpoints devraient être sécurisés ou accessibles uniquement via un réseau interne/VPN de management. Pour ce projet pédagogique, ils sont ouverts pour la démonstration.
