package service;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.MovementDAO;
import dao.ProjectDAO;
import entity.Project;
import entity.Statut;

public class ProjectService {
	
	
	public static void createProject(String name, String budgetString, String description, String startDateString, String endDateString, String statutString) {
		
		double budget = Double.parseDouble(budgetString);
		LocalDate startDate = LocalDate.parse(startDateString);
		LocalDate endDate = null;
		if (endDateString != null && !endDateString.isEmpty()) {
			endDate = LocalDate.parse(endDateString);
		}
		Statut statut = Statut.valueOf(statutString);
		
		Project projet = new Project(name, budget, description, startDate, endDate, statut);
		ProjectDAO.save(projet);
	}
	
	public static ArrayList<Project> findAllProject() {
		return ProjectDAO.findAll();
	}
	
	public static Project findProjectById(int id) {
		return ProjectDAO.findById(id);
	}
	
	public static void updateProject(int id, String name, String budgetString, String description, String startDateString, String endDateString, String statutString) {
		double budget = Double.parseDouble(budgetString);
		LocalDate startDate = LocalDate.parse(startDateString);
		LocalDate endDate = null;
		if (endDateString != null && !endDateString.isEmpty()) {
			endDate = LocalDate.parse(endDateString);
		}
		Statut statut = Statut.valueOf(statutString);
		ProjectDAO.update(new Project(id,name, budget, description, startDate, endDate, statut));
	}
	
	public static boolean deleteProject(int id) {
		int mov = MovementDAO.findByProjects(id).size();
		if(mov==0) {
			ProjectDAO.delete(id);
			return true;
		}else {
			return false;
		}
	}
	
}
