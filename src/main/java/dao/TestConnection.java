package dao;

import java.sql.SQLException;
import java.util.Random;
import entity.Projects;


// TEST DE CONNECTIVITE AVEC LA BD
	public class TestConnection {
	
		public static void main(String[] args) {
			try{
				ConnectionDB.getConnectionDB();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			Random rd = new Random();
			int min = 1, max = 9;
			int id = rd.nextInt(max - min + 1) + min;
			Projects project = ProjectDAO.findById(id);
			
			System.out.println("==== Affichage d'un projet a l'aleatoire : ====");
			
			System.out.println("Projet " + id);
			System.out.println("nom du projet : " + project.getName());
			System.out.println("description du projet : " + project.getDescription());
			System.out.println("budget du projet : " + project.getBudget());
		}
	
}
