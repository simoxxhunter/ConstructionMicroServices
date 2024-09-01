# ConstructionMicroServices

Project context
​

En tant que développeur full stack freelance, vous êtes sollicité pour créer une application de gestion de projets de construction pour l’entreprise « ConstructionXpert Services ». Afin d'assurer une meilleure évolutivité, maintenabilité, et résilience, l'application sera développée en utilisant une architecture microservices. Chaque service micro gérera une partie distincte des fonctionnalités de l'application, et les services communiqueront entre eux via des API RESTful sécurisées.

​

Microservices Identifiés

​

Service de Gestion des Projets*

Fonctionnalités:

--Créer un nouveau projet avec son nom, sa description, sa date de début, sa date de fin et son budget.

--Afficher la liste des projets existants avec leurs détails.

--Mettre à jour les détails d'un projet existant.

--Supprimer un projet existant.

​

Service de Gestion des Tâches

Fonctionnalités:

--Créer une nouvelle tâche en spécifiant sa description, sa date de début, sa date de fin, son statut (à faire, en cours, terminé) et les ressources nécessaires.

--Afficher la liste des tâches associées à un projet avec leurs détails.

--Mettre à jour les détails d'une tâche existante.

--Supprimer une tâche existante.

​

Service de Gestion des Ressources

Fonctionnalités:

--Ajouter de nouvelles ressources avec leurs détails tels que le nom, le type, la quantité, et les informations du fournisseur.

--Afficher la liste des ressources disponibles avec leurs détails.

--Mettre à jour les détails d'une ressource existante.

--Supprimer une ressource existante.

​

​

Base de données

--chaque service dispose de sa propre base de données qui stocke des informations spécifiques, assurant ainsi une isolation complète des données et facilitant les migrations et les évolutions indépendantes.

​

Gestion des migrations:

--Flyway sera utilisé pour gérer les migrations de schéma de la base de données. Chaque changement de schéma sera versionné et appliqué de manière ordonnée lors des déploiements.

​

​

Bonus: Implémentation de service Registry/Discovery (Eureka)

******Responsabilités: Le Service Registry est un composant central qui permet aux microservices de s'enregistrer et de découvrir les autres services au sein de l'application. Cela permet une communication dynamique et flexible entre les microservices, tout en simplifiant la gestion des adresses des services.
