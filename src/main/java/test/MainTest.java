package test;

import java.time.LocalDate;
import java.util.List;

import dao.ProjectDAO;
import entity.Project;
import entity.Statut;

public class MainTest {

	public static void main(String[] args) {
		ProjectDAO dao = new ProjectDAO();
		
//		System.out.println("Test : Liste des Projects : ");
//		List<Project> projects = dao.findAll();
//		for (Project project : projects) {
//			System.out.println(project);
//		}
		
//		System.out.println("projet avec l'id 4 : \n" + dao.findById(4) );
//		String name = "nom 2 test JPA";
//		String description = "description 2 test JPA";
//		double budget = 2000;
//		LocalDate startdate = LocalDate.now();
//		LocalDate endDate = LocalDate.now();
//		Statut statut = Statut.TERMINE;
//		Project project = new Project(name, budget, description, startdate, endDate, statut);
//		System.out.println(dao.save(project));
		
//		dao.delete(16);
//		System.out.println("supprimer avec succes");
		
//		System.out.println(dao.update(new Project(15, name, budget, description, startdate, endDate, statut)));
//		System.out.println("projet avec l'id 15 : \n" + dao.findById(15) );
	}
	
}
