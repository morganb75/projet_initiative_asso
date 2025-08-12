````mermaid
erDiagram

    USER {
        BIGINT id PK
        STRING nom
        STRING prenom
        STRING email
        STRING entreprise
        STRING plateForme
        STRING password
        BOOLEAN isAccountEnabled
        BOOLEAN firstLogin
    }

    PORTEUR {
        BIGINT id PK, FK
        DATE dateDebutActivite
        STRING domaineActivite
        STRING descriptifActivite
        STRING disponibilites
        BIGINT parrain_id FK
        BIGINT lieuActivite_id FK
    }

    PARRAIN {
        BIGINT id PK, FK
        STRING parcours
        STRING domaineActivite
        STRING disponibilites
    }

    SALARIE_ASSO {
        BIGINT id PK, FK
        STRING fonction
    }

    ADRESSE {
        BIGINT id PK
        STRING numeroDeVoie
        STRING rue
        STRING complement
        INTEGER codePostal
        STRING ville
    }

    REUNION {
        BIGINT id PK
        DATETIME date
        STRING motif
        STRING compteRendu
        BIGINT parrain_id FK
        BIGINT porteur_id FK
    }

    MESSAGE {
        BIGINT id PK
        DATETIME timeStamp
        BOOLEAN read
        STRING text
        BIGINT sender_id FK
        BIGINT receiver_id FK
    }

    USER_ROLES {
        BIGINT user_id FK
        STRING role
    }

    PORTEUR_BESOINS {
        BIGINT porteur_id FK
        STRING besoin
    }

    PARRAIN_ZONES {
        BIGINT parrain_id FK
        STRING zone
    }

    %% --- RELATIONS ---

    USER ||--|| PORTEUR : extends
    USER ||--|| PARRAIN : extends
    USER ||--|| SALARIE_ASSO : extends
    USER ||--o{ USER_ROLES : has
    USER ||--o{ MESSAGE : sender
    USER ||--o{ MESSAGE : receiver

    PORTEUR }o--|| ADRESSE : lieuActivite
    PORTEUR ||--o{ PORTEUR_BESOINS : a_besoin
    PORTEUR }o--|| PARRAIN : est_suivi_par

    PARRAIN ||--o{ PARRAIN_ZONES : a_zones
    PARRAIN ||--o{ REUNION : organise
    PORTEUR ||--o{ REUNION : participe

````