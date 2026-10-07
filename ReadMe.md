# GestionBudgetaire (V1)

Application web de gestion budgétaire : on crée des **projets** et on enregistre leurs **mouvements financiers** (entrées / sorties). La situation financière d'un projet est calculée à la volée.

## Technologies

- Java 21, Jakarta EE (Servlet, JSP, JSTL), Tomcat 11.0.25
- PostgreSQL + JDBC

## Lancer le projet

1. Créer la base PostgreSQL `projects`, puis exécuter `GestionBudgetaire.sql` (le script supprime et recrée les tables `projet` et `mouvement`, et insère des données de test).
2. Adapter l'URL, le login et le mot de passe dans `dao/ConnectionDB.java`.
3. Déployer sur Tomcat, puis ouvrir `http://localhost:8080/GestionBudgetaire/`.

## Architecture

```
src/main/java/
  entity/       Project, Movement, Statut (EN_COURS, TERMINE), TypeMovement (ENTREE, SORTIE)
  dao/          ConnectionDB, ProjectDAO, MovementDAO
  service/      ProjectService, MovementService, FinanceService, FinanceSituation, Validation
  controller/   Servlets (une par action)
src/main/webapp/
  index.html, style.css          (publics)
  WEB-INF/views/projects/        JSP des projets
  WEB-INF/views/movements/       JSP des mouvements
```

## Validation

Les méthodes de base sont dans `service/Validation.java`. Elles remplissent une `Map<String, String>` d'erreurs (clé = champ, valeur = message) et renvoient la valeur convertie, ou `null` si invalide :

- `Validation.text(error, field, value, min, max, required)` : présence et la longueur pour les champs de type texte, et cette methode prend aussi en parametre un booleen `required` qui a `true` rend le champs obligatoire et `false` dans le cas contraire.
- `Validation.number(error, field, value, min, strict)` : nombre valide et borne minimale (`strict` : `true` si strictement supérieur et `false` dans le cas contraire)
- `Validation.date(error, champ, valeur)` : date valide (format `yyyy-MM-dd`)

Elles sont appelées par :

- `ProjectService.validationProject(String name, String budgetString, String description, String startDateString, String endDateString, String statutString)`
  - nom obligatoire (4 à 50 caractères)
  - budget obligatoire et ≥ 0
  - description : 2000 caractères maximum
  - date de début et de fin obligatoires
  - date de fin ≥ date de début

- `MovementService.validationMovement(int id, String name, String libelle, String typeString, String montantString,  String dateString, String description, int projectId)`
  - nom (4 à 50 caractères), libellé obligatoire (4 à 500 caractères)
  - montant obligatoire et strictement positif
  - type obligatoire (ENTREE ou SORTIE)
  - date obligatoire et comprise entre la date de début et la date de fin du projet
  - le total des SORTIE ne peut pas dépasser le total des entrées du projet (« fonds insuffisants »)
  - lors de l'appel de cette methode dans le controller de la page de creation de mouvement, le parametre id recoit la valeur : -1; mais si la methode est appele dans le controller de la page de modification de mouvement, alors id recoit l'id du mouvement.

Si la `Map` d'erreurs n'est pas vide, la servlet réaffiche le formulaire avec les messages sous chaque champ et les valeurs saisies. Sinon, elle appelle le service pour enregistrer.

## Calculs financiers (`FinanceService.financeCalculation`)

Calculés à partir des mouvements, jamais stockés en base :

- **Total des entrées** = somme des mouvements `ENTREE`
- **Total des sorties** = somme des mouvements `SORTIE`
- **Solde** = total des entrées − total des sorties
- **Budget restant** = budget du projet − total des sorties

Le résultat est renvoyé dans un objet `FinanceSituation`. Un projet sans mouvement affiche 0 partout et un budget restant égal à son budget.

## Prochains axes de travail

- La gestion des exceptions
- creation de page d'erreur (error 404, 500, etc) comprehensible par utilisateur qui sera deployer lorsqu'il y aura mauvaise manipulation.

