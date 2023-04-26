# Controle 1 - Gestion des abonnements

## Application
On souhaite développer une application JEE basée sur Spring qui permet de gérer les
abonnements d’un opérateur télécom. Chaque client peut avoir plusieurs abonnements.
• Un client est défini par : sont id, son nom, son email et son username
• Un abonnement est défini par : son id, la date d’abonnement, le type d’abonnement
(GSM, INTERNET, TELEPHONE_FIXE), son solde, et le montant mensuel
L’architecture de l’application est basée sur :
• Un SGBD relationnel de votre Choix (H2, MySQL, PostGres, etc..)
• Spring Data, JPA, Hibernate
• Spring MVC avec Thymeleaf
• Spring Security
***

## Travail à faire:
Rendre le code source du projet et un rapport au format PDF contenant les réponses aux
questions suivantes :
1. Schéma de l’architecture technique de l’application
2. Diagramme de classe représentant les données manipulées par l’application
3. Couche DAO :
       a. Créer les entités JPA
       b. Créer les interfaces JpaRepository basées sur Spring Data
       c. Tester la couche DAO
4. Couche Web : Créer une applications Web qui permet de :
       a. Gérer les clients (Chercher, Pagination, Ajout, Edition et Suppression)
       b. Gérer les abonnements :
              o Afficher les abonnements d’un clients
              o Charger le solde de l’abonnement avec un montant
              o Autres opérations de gestion des abonnements

5. Créer un web service RESTful qui permet de gérer les clients et les abonnements
6. Sécurité : Sécuriser l’accès à l’application en développant un système
d’authentification statefull basé sur Spring Security avec deux rôles CLIENT et ADMIN.
l’application doit répondre aux critères suivants :
       a. Authentification avec le rôle CLIENT : le client ne peut voir que : son profile, ses abonnements et peut charger ses abonnements
       b. Authentification avec le rôle ADMIN : l’administrateur peut gérer les clients et les abonnements avec tous les droits possible. En plus il peut créer de nouveau utilisateurs et affecter des rôles aux utilisateurs.
***

## Rapport
[Rapport de controle 1 - Gestion des abonnements.pdf](https://github.com/Ayoub-etoullali/Activites-Pratiques-JEE/files/11336836/Rapport.de.controle.1.-.Gestion.des.abonnements.pdf)

## Demo

<div align="center">
       <p>
       <sup>  <strong>Vidéo - </strong>Gestion des abonnements</sup>
       </p>
</div>

<kbd>Enjoy Code</kbd> 👨‍💻
