Pour créer la base de donnee avec PostgreSQL dans pgAdmin4 : CREATE DATABASE projects;

Si tu veux le faire depuis le terminal avec psql, la commande est : createdb projects

Ensuite, pour se connecter à cette base avant d’exécuter le script SQL taper la commande : psql -U postgres -d projects


Ou, pour un utiliser un utilisateur spécifique : psql -U ton_utilisateur -d projects

Puis, une fois dans psql, on peut lancer le fichier SQL :

via le terminal, lancer : \i chemin/create_database_projects.sql (tout en percisant le chemin)

via pgAdmin4, ouvrir et executer le script : create_database_projects.sql

ensuite effectuer tous les tests voulu sur la base de donnee.

NB : le fichier document Modelisation.pdf contient la modelisation MCD & MLD.
