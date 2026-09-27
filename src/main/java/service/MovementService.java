package service;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.MovementDAO;
import entity.Movements;
import entity.TypeMovement;

public class MovementService {

	public static ArrayList<Movements> findMovementByProject(int projectId){
		return MovementDAO.findByProjects(projectId);
	}
	
	public static void createMovement(String name, String libelle, String typeString, String montantString,  String dateString, String description, int projectId) {
		TypeMovement type = TypeMovement.valueOf(typeString);
		double montant = Double.parseDouble(montantString);
		LocalDate date = LocalDate.parse(dateString);
		MovementDAO.save(new Movements(name, libelle, type, montant, date, description, projectId));
	}
	
	public static Movements findMovementById(int id) {
		return MovementDAO.findById(id);
	}
	
	public static void updateMovement(int id, String name, String libelle, String typeString, String montantString,  String dateString, String description) {
		double montant = Double.parseDouble(montantString);
		LocalDate date = LocalDate.parse(dateString);
		TypeMovement type = TypeMovement.valueOf(typeString);
		MovementDAO.update(new Movements(id, name, libelle, type, montant, date, description));
	}
	
	public static void deleteMovement(int id) {
		MovementDAO.delete(id);
	}
	
}
