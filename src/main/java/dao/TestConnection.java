package dao;

import java.time.LocalDate;

import entity.Statut;
import entity.TypeMovement;

import java.util.ArrayList;
import java.util.Random;

import entity.Movements;
import entity.Projects;


// TEST DE CONNECTIVITE AVEC LA BD
	public class TestConnection {
	
		public static void main(String[] args) {
			
// DELETE MOUVEMENT
//			MovementDAO.delete(11);

// CREER UN MOUVEMENT 
//			String name = "TEST nom";
//			String libelle = "TEST libelle";
//			TypeMovement type = TypeMovement.ENTREE;
//			double montant = 4000;
//			LocalDate date = LocalDate.now();
//			String description = "TEST description";
//			int projectId = 8;
//			int id = 11;
//			Movements mouv = new Movements(id, name, libelle, type, montant, date, description, projectId);
//			
//			mouv.setType(TypeMovement.SORTIE);
//			mouv.setName("nouveau nom");
//			mouv.setDescription("nouvelle description");
//			
//			MovementDAO.update(mouv);
			
//			MovementDAO.save(mouv);
			
// RECHERCHER UN MOUVEMENT PAR ID PROJET
//			ArrayList<Movements> mouvs = MovementDAO.findByProjects(1);
//			System.out.println(" ------ AFFICHAGE DES PROJETS ------ ");
//			int i = 1;
//			for(Movements mouv : mouvs) {
//				System.out.println("\n --- MOUVEMENT " + i + " --- ");
//				System.out.println("nom : " + mouv.getName() + "\n montant " + mouv.getMontant() + "\n type " + mouv.getType() + "\n date " + mouv.getDate());
//				i++;
//			}

			
// RECHERCHER UN MOUVEMENT
//			Movements mouv = MovementDAO.findByProjects(1);
//			System.out.println("nom : " + mouv.getName() + "\n montant " + mouv.getMontant() + "\n type " + mouv.getType() + "\n date " + mouv.getDate());
			
			
// SUPPRIMER UN PROJET
//			ProjectDAO.delete(11);
			

// MISE A JOUR D'UN PROJET
/*			int id = 11;
			Projects project = new Projects(id);
			project.setName("TEST Nouveau nom");
			project.setDescription("TEST Nouvelle description");
			project.setBudget(4000);
			project.setStatut(Statut.TERMINE);
			project.setStartDate(LocalDate.now());
			project.setEndDate(LocalDate.now());
			ProjectDAO.update(project);*/
			
			
// CREATION D'UN PROJET
/*			String name = "TEST nom";
			String description = "TEST descrition";
			double budget = 5000;
			LocalDate startDate = LocalDate.now();
			LocalDate endDate = null;
			Statut statut = Statut.EN_COURS;
			ProjectDAO.save(name, budget, description, startDate, endDate, statut);*/
			
			
// AFFICHAGE DES PROJETS EN BASE DE DONNEE
/*			ArrayList<Projects> projets = ProjectDAO.findAll(); 
			System.out.println(" ------ AFFICHAGE DES PROJETS ------ ");
			int i = 1;
			for(Projects projet : projets) {
				System.out.println(" --- PROJET " + i + " --- ");
				System.out.println("id : " + projet.getId() + "\n nom : " + projet.getName() + "\n description : " + projet.getDescription() + "\n budget : " + projet.getBudget() + "\n date de debut : " + projet.getStartDate() + "\n date de fin : " + projet.getEndDate() + "\n statut : " + projet.getStatut());
				i++;
			}*/
			
			
// AFFICHAGE D'UN PROJET ALEATOIRE			
/*			Random rd = new Random();
			int min = 1, max = 9;
			int id = rd.nextInt(max - min + 1) + min;
			Projects project = ProjectDAO.findById(id);
			
			System.out.println("==== Affichage d'un projet a l'aleatoire : ====");
			
			System.out.println("Projet " + id);
			System.out.println("nom du projet : " + project.getName());
			System.out.println("description du projet : " + project.getDescription());
			System.out.println("budget du projet : " + project.getBudget()); */
			
			System.out.println("REUSSITE");	
		}
	
}
