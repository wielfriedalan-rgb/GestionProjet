package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.MovementDAO;
import dao.ProjectDAO;
import entity.Movement;
import entity.Project;
import entity.TypeMovement;
import util.Constant;
import util.Validation;

public class MovementService {
	
	private ProjectDAO projectDAO;
	private MovementDAO movementDAO;
	
	public MovementService() {
		this.projectDAO = new ProjectDAO();
		this.movementDAO = new MovementDAO();
	}
	
	
	public Map<String, String> validationMovement(int id, String name, String libelle, String typeString, String montantString,  String dateString, String description, int projectId){
		Map<String, String> error = new HashMap<String, String>();
		Project project = this.projectDAO.findById(projectId);
		Validation.text(error, "Le nom", name, Constant.MIN_NAME, Constant.MAX_NAME, false);
		Validation.text(error, "Le libelle", libelle, Constant.MIN_LIBELLE, Constant.MAX_LIBELLE, true);
		Double montant = Validation.number(error, "Le montant", montantString, 0, true);
		Validation.text(error, "La description", description, Constant.MIN_DESCRIPTION, Constant.MAX_DESCTIPTION, false);
		LocalDate date = Validation.date(error, "La date", dateString);
		if(date==null || montant==null) return error;
		if(date.isBefore(project.getStartDate()) || date.isAfter(project.getEndDate())) {
			error.put("La date", "La date doit etre comprise entre la date de début et la date de fin du projet !");
			return error;
		}
		Validation.select(error,TypeMovement.valueOf(typeString), montant, project.getId(), id);
		return error;
	}

	public ArrayList<Movement> findMovementByProject(int projectId){
		return this.movementDAO.findByProjects(projectId);
	}
	
	public void createMovement(String name, String libelle, String typeString, String montantString,  String dateString, String description, int projectId) {
		TypeMovement type = TypeMovement.valueOf(typeString);
		double montant = Double.parseDouble(montantString);
		LocalDate date = LocalDate.parse(dateString);
		this.movementDAO.save(new Movement(name, libelle, type, montant, date, description, projectId));
	}
	
	public Movement findMovementById(int id) {
		return this.movementDAO.findById(id);
	}
	
	public void updateMovement(int id, String name, String libelle, String typeString, String montantString,  String dateString, String description) {
		double montant = Double.parseDouble(montantString);
		LocalDate date = LocalDate.parse(dateString);
		TypeMovement type = TypeMovement.valueOf(typeString);
		this.movementDAO.update(new Movement(id, name, libelle, type, montant, date, description));
	}
	
	public void deleteMovement(int id) {
		this.movementDAO.delete(id);
	}

}
