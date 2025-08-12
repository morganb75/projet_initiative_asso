````mermaid
classDiagram
    direction BT
    class Adresse {
        - String rue
        - Long id
        - String numeroDeVoie
        - String complement
        - Integer codePostal
        - String ville
    }

    class Reunion {
        - String compteRendu
        - Parrain parrain
        - LocalDateTime date
        - Long id
        - String motif
        - Porteur porteur
    }
    
    class Parrain {
        - List~Reunion~ listeReunions
        - List~ZonesDeplacement~ zonesDeDeplacement
        - String disponibilites
        - List~Porteur~ listePorteurs
        - SecteursReseaux domaineActivite
        - String parcours
    }
    class Porteur {
        - Adresse lieuActivite
        - String descriptifActivite
        - List~TypeAccompagnement~ besoinsPotentiels
        - List~Reunion~ listeReunions
        - String disponibilites
        - LocalDate dateDebutActivite
        - SecteursReseaux domaineActivite
        - Parrain parrain
    }
    
    class SalarieAsso {
        - String fonction
    }
    class User {
        - Boolean isAccountEnabled
        - List~UserRole~ roles
        - String password
        - String prenom
        - PlateForme plateForme
        - Long id
        - String email
        - Boolean firstLogin
        - String nom
        - String entreprise
    }

    Parrain  -->  User
    Porteur  -->  User
    SalarieAsso  -->  User

````
