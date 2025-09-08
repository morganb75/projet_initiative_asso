# 🌐 Projet Initiative Asso

Application web complète destinée à la gestion parrain/porteurs de projet pour le compte de l'association Initative Deux-Sèvres.  
Le projet repose sur une architecture **Java Spring Boot** (backend) et **React (Vite)** (frontend).  
Il inclut un système de **messagerie en temps réel** via WebSocket, une **authentification sécurisée avec JWT**, et une gestion des utilisateurs/membres.

## Technologies utilisées

### Backend (Java Spring Boot)
- **Spring Boot 3**
- **Spring Data JPA** (PostgreSQL)
- **Spring Security + JWT** (authentification & autorisation)
- **WebSocket (STOMP)** pour la messagerie en temps réel
- **Architecture MVC**

### Frontend (React + Vite)
- **React 18**
- **React Router** (navigation)
- **WebSocket client (STOMP.js)** pour la messagerie
- **Vite** pour le bundling

## ⚙️ Installation et démarrage

### 1. Prérequis

Avant de lancer le projet, assurez-vous d’avoir installé sur votre machine :

- **Java 17+**
- **Maven** (ou wrapper intégré de Spring Boot)
- **Node.js 18+** et **npm**
- **PostgreSQL** (base relationnelle)

---

### 2. Récupération du projet

Cloner le dépôt :

```bash
git clone https://github.com/ton-compte/projet-initiative-asso.git
cd projet-initiative-asso
```

### 3. Configuration base de données

1. Créer une base **PostgreSQL** (ex. `db_initiative_asso`).
3. Vérifier ou adapter la configuration dans `src/main/resources/application.yml` :

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/initiative_asso
    username: votre_user
    password: votre_password
  ```

### 4. Lancement du backend (Spring Boot)

Depuis la racine du projet :

```bash
mvn spring-boot:run
```

### 5. Installation et lancement du frontend (React + Vite)

Depuis le dossier frontend :

```bash
cd src/js
npm install
npm run dev
```

### 6. Users: 
On les trouve en base de données suite à la migration flyway




