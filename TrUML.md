````mermaid
classDiagram
direction TB
class Adresse {
+Long id
+Integer codePostal
+String complement
+String numeroDeVoie
+String rue
+String ville
}
class User {
+Long id
+Boolean firstLogin
+Boolean isAccountEnabled
+String email
+String entreprise
+String nom
+String password
+String plateForme
+String prenom
}
class Message {
+Long id
+Boolean read
+LocalDateTime timeStamp
+String text
}
class Notification {
+Long id
+LocalDateTime dateNotification
}
class Parrain {
+Long id
+Short domaineActivite
+String disponibilites
+String parcours
+Short[] zonesDeDeplacement
}
class Porteur {
+Long id
+LocalDate dateDebutActivite
+Short domaineActivite
+String descriptifActivite
+String disponibilites
+Short[] besoinsPotentiels
}
class SalarieAsso {
+Long id
+String fonction
}
class UserRoles {
+String roles
}
class Reunion {
+Long id
+String compteRendu
+LocalDateTime date
+String motif
}
User "1" --> "*" Message : sender
User "1" --> "*" Message : receiver
User "1" --> "*" Notification : sender
User "1" --> "*" Notification : receiver
Parrain "1" --> "1" User : user
Porteur "1" --> "1" User : user
Porteur "*" --> "1" Adresse : lieuActivite
Porteur "*" --> "1" Parrain : parrain
SalarieAsso "1" --> "1" User : user
UserRoles "*" --> "1" User : user
Reunion "*" --> "1" Parrain : parrain
Reunion "*" --> "1" Porteur : porteur
````