package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.MovementDAO;
import dao.ProjectDAO;
import entity.Project;
import entity.Statut;

public class ProjectService {
	
	public static final int MIN_NAME=4;
	public static final int MAX_NAME=50;
	public static final int MIN_DESCRIPTION=0;
	public static final int MAX_DESCTIPTION=2000;
	
	
	public static Map<String, String> validationProject(String name, String budgetString, String description, String startDateString, String endDateString, String statutString) {
		Map<String, String> error = new HashMap<String, String>();
		Validation.text(error, "Le nom", name, MIN_NAME, MAX_NAME, true);
		Double budget = Validation.number(error, "Le budget", budgetString, 0, false);
		Validation.text(error, "La description", description, MIN_DESCRIPTION, MAX_DESCTIPTION, false);
		LocalDate startDate = Validation.date(error, "La date de debut", startDateString);
		LocalDate endDate = Validation.date(error, "La date de fin", endDateString);
		if(startDate==null || endDate==null || budget==null) {
			return error;
		}
		if(endDate.isBefore(startDate)) {
			error.put("La date de debut", "La date de début doit etre inferieur ou égale a la date de fin du projet !");
			error.put("La date de fin", "La date de fin doit etre superieur ou égale a la date de début du projet !");
			return error;
		}
		if((endDate.isBefore(LocalDate.now()) && Statut.valueOf(statutString)==Statut.EN_COURS) || (!endDate.isBefore(LocalDate.now()) && Statut.valueOf(statutString)==Statut.TERMINE)) {
			error.put("Le statut", "Veuillez vérifier le statut de votre projet");
			return error;
		}
		return error;
	}
	
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
