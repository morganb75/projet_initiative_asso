````mermaid
erDiagram
    ADRESSE {
        int id PK
        int codePostal
        string complement
        string numeroDeVoie
        string rue
        string ville
    }

    USER {
        int id PK
        boolean firstLogin
        boolean isAccountEnabled
        string email
        string entreprise
        string nom
        string password
        string plateForme
        string prenom
    }

    MESSAGE {
        int id PK
        boolean isRead
        int receiverId 
        int senderId 
        date timeStamp
        string text
    }

    NOTIFICATION {
        int id PK
        date dateNotification
        int receiverId 
        int senderId 
    }

    PARRAIN {
        int id PK 
        int domaineActivite
        string disponibilites
        string parcours
        string zonesDeDeplacement
    }

    PORTEUR {
        int id PK 
        date dateDebutActivite
        int domaineActivite
        int lieuActiviteId 
        int parrainId 
        string descriptifActivite
        string disponibilites
        string besoinsPotentiels
    }

    SALARIE_ASSO {
        int id PK 
        string fonction
    }

    USER_ROLES {
        int userId 
        string roles
    }

    REUNION {
        int id PK
        string compteRendu
        date date
        string motif
        int parrainId 
        int porteurId 
}

%% Relations

MESSAGE }o--|| USER : "receiver"
MESSAGE }o--|| USER : "sender"

NOTIFICATION }o--|| USER : "receiver"
NOTIFICATION }o--|| USER : "sender"

PARRAIN ||--|| USER : "user"

PORTEUR ||--|| USER : "user"
PORTEUR }o--|| ADRESSE : "lieuActivite"
PORTEUR }o--|| PARRAIN : "parrain"

SALARIE_ASSO ||--|| USER : "user"

USER_ROLES }o--|| USER : "user"

REUNION }o--|| PARRAIN : "parrain"
REUNION }o--|| PORTEUR : "porteur"

````