package service;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.ProjectDAO;
import entity.Projects;
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
		
		Projects projet = new Projects(name, budget, description, startDate, endDate, statut);
		ProjectDAO.save(projet);
	}
	
	public static ArrayList<Projects> findAllProject() {
		return ProjectDAO.findAll();
	}
	
}
