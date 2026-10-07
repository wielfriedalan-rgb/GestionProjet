package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.MovementDAO;
import entity.Movement;
import entity.Project;
import entity.TypeMovement;

public class MovementService {
	
	public static final int MIN_NAME=4;
	public static final int MAX_NAME=50;
	public static final int MIN_LIBELLE=4;
	public static final int MAX_LIBELLE=500;
	public static final int MIN_DESCRIPTION=0;
	public static final int MAX_DESCTIPTION=2000;
	
	
	public static Map<String, String> validationMovement(int id, String name, String libelle, String typeString, String montantString,  String dateString, String description, int projectId){
		Map<String, String> error = new HashMap<String, String>();
		Project project = ProjectService.findProjectById(projectId);
		Validation.text(error, "Le nom", name, MIN_NAME, MAX_NAME, false);
		Validation.text(error, "Le libelle", libelle, MIN_LIBELLE, MAX_LIBELLE, true);
		Double montant = Validation.number(error, "Le montant", montantString, 0, true);
		Validation.text(error, "La description", description, MIN_DESCRIPTION, MAX_DESCTIPTION, false);
		LocalDate date = Validation.date(error, "La date", dateString);
		if(date==null || montant==null) return error;
		if(date.isBefore(project.getStartDate()) || date.isAfter(project.getEndDate())) {
			error.put("La date", "La date doit etre comprise entre la date de début et la date de fin du projet !");
			return error;
		}
		if(TypeMovement.valueOf(typeString)==TypeMovement.SORTIE) {
			ArrayList<Movement> movements = MovementDAO.findByProjects(project.getId());
			double totalEntrees=0, totalSorties=montant;
			for(Movement mov : movements) {
				if(id!=(-1) && id==mov.getId()) continue;
				if(mov.getType()==TypeMovement.ENTREE) {
					totalEntrees += mov.getMontant();
				}else if(mov.getType()==TypeMovement.SORTIE) {
					totalSorties += mov.getMontant();
				}
			}
			if(totalEntrees<totalSorties) {
				error.put("Le montant", "Fonds insuffissants pour effectuer cette sortie");
			}
		}
		return error;
	}

	public static ArrayList<Movement> findMovementByProject(int projectId){
		return MovementDAO.findByProjects(projectId);
	}
	
	public static void createMovement(String name, String libelle, String typeString, String montantString,  String dateString, String description, int projectId) {
		TypeMovement type = TypeMovement.valueOf(typeString);
		double montant = Double.parseDouble(montantString);
		LocalDate date = LocalDate.parse(dateString);
		MovementDAO.save(new Movement(name, libelle, type, montant, date, description, projectId));
	}
	
	public static Movement findMovementById(int id) {
		return MovementDAO.findById(id);
	}
	
	public static void updateMovement(int id, String name, String libelle, String typeString, String montantString,  String dateString, String description) {
		double montant = Double.parseDouble(montantString);
		LocalDate date = LocalDate.parse(dateString);
		TypeMovement type = TypeMovement.valueOf(typeString);
		MovementDAO.update(new Movement(id, name, libelle, type, montant, date, description));
	}
	
	public static void deleteMovement(int id) {
		MovementDAO.delete(id);
	}

}
