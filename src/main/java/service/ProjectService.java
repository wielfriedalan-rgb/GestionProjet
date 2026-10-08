package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.MovementDAO;
import dao.ProjectDAO;
import entity.Project;
import entity.Statut;
import util.Constant;
import util.Validation;

public class ProjectService {
	
	private ProjectDAO projectDAO;
	private MovementDAO movementDAO;
	
	public ProjectService() {
		this.projectDAO = new ProjectDAO();
		this.movementDAO = new MovementDAO();
	}
	
	
	public Map<String, String> validationProject(String name, String budgetString, String description, String startDateString, String endDateString, String statutString) {
		Map<String, String> error = new HashMap<String, String>();
		Validation.text(error, "Le nom", name, Constant.MIN_NAME, Constant.MAX_NAME, true);
		Double budget = Validation.number(error, "Le budget", budgetString, 0, false);
		Validation.text(error, "La description", description, Constant.MIN_DESCRIPTION, Constant.MAX_DESCTIPTION, false);
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
	
	public void createProject(String name, String budgetString, String description, String startDateString, String endDateString, String statutString) {
		double budget = Double.parseDouble(budgetString);
		LocalDate startDate = LocalDate.parse(startDateString);
		LocalDate endDate = null;
		if (endDateString != null && !endDateString.isEmpty()) {
			endDate = LocalDate.parse(endDateString);
		}
		Statut statut = Statut.valueOf(statutString);
		Project projet = new Project(name, budget, description, startDate, endDate, statut);
		this.projectDAO.save(projet);
	}
	
	public ArrayList<Project> findAllProject() {
		return this.projectDAO.findAll();
	}
	
	public Project findProjectById(int id) {
		return this.projectDAO.findById(id);
	}
	
	public void updateProject(int id, String name, String budgetString, String description, String startDateString, String endDateString, String statutString) {
		double budget = Double.parseDouble(budgetString);
		LocalDate startDate = LocalDate.parse(startDateString);
		LocalDate endDate = null;
		if (endDateString != null && !endDateString.isEmpty()) {
			endDate = LocalDate.parse(endDateString);
		}
		Statut statut = Statut.valueOf(statutString);
		this.projectDAO.update(new Project(id,name, budget, description, startDate, endDate, statut));
	}
	
	public boolean deleteProject(int id) {
		int mov = this.movementDAO.findByProjects(id).size();
		if(mov==0) {
			this.projectDAO.delete(id);
			return true;
		}else {
			return false;
		}
	}
	
}
