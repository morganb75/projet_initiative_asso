````mermaid
classDiagram
    class User {
        <<actor>>
    }
    class Porteur {
        <<actor>>
    }
    class Parrain {
        <<actor>>
    }
    class SalarieAsso {
        <<actor>>
    }
    class Admin {
        <<actor>>
    }

    class Se_connecter {
        <<usecase>>
    }
    class Consulter_profil {
        <<usecase>>
    }
    class Modifier_profil {
        <<usecase>>
    }
    class Envoyer_message {
        <<usecase>>
    }
    class Recevoir_message {
        <<usecase>>
    }
    class Recevoir_notifications {
        <<usecase>>
    }
    class Creer_reunion {
        <<usecase>>
    }
    class Consulter_reunion {
        <<usecase>>
    }
    class Gérer_parrainage {
        <<usecase>>
    }
    class Gérer_rôles {
        <<usecase>>
    }

    User -- Se_connecter
    User -- Consulter_profil
    User -- Modifier_profil
    User -- Envoyer_message
    User -- Recevoir_message
    User -- Recevoir_notifications

    Porteur -- Creer_reunion
    Porteur -- Consulter_reunion
    Porteur -- Gérer_parrainage

    Parrain -- Creer_reunion
    Parrain -- Consulter_reunion
    Parrain -- Gérer_parrainage

    SalarieAsso -- Consulter_reunion

    Admin -- Gérer_rôles
    Admin -- Consulter_reunion
    Admin -- Envoyer_message
    Admin -- Recevoir_message


````