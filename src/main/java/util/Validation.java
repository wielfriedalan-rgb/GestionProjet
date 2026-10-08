package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;

import dao.MovementDAO;
import entity.Movement;
import entity.TypeMovement;

public class Validation {

	public static void text(Map<String, String> error, String field, String value, int min, int max, boolean required) {
		if(value.isBlank() && required) {
			error.put(field, field + " est obligatoire !");
			return;
		}
		if(value.length()<min && required) error.put(field, field + " doit avoir minimum " + min + " caracteres !");
		if(value.length()>max) error.put(field, field + " doit avoir maximum " + max + " caracteres !");
	}

	public static Double number(Map<String, String> error, String field, String value, double min, boolean strict) {
		if(value.isBlank()) {
			error.put(field, field + " est obligatoire !");
			return (Double) null;
		}
		double valueNumber;
		try {
			valueNumber=Double.parseDouble(value);
		}catch(NumberFormatException e) {
			error.put(field, field + " doit etre un nombre !");
			return (Double) null;
		}
		if(valueNumber<min) {
			error.put(field, field + " doit etre superieur a " + min + " !");
			return (Double) null;
		}
		if(strict && valueNumber<=min) {
			error.put(field, field + " doit etre strictement superieur a " + min + " !");
			return (Double) null;
		}
		return valueNumber;
	}

	public static LocalDate date(Map<String, String> error, String field, String value) {
		if(value==null || value.isBlank()) {
			error.put(field, field + " est obligatoire !");
			return null;
		}
		LocalDate date;
		try {
			date = LocalDate.parse(value);
		}catch(DateTimeParseException e) {
			error.put(field, field + " doit etre une date valide !");
			return null;
		}
		return date;
	}
	
	public static void select(Map<String, String> error, TypeMovement type, double montant, int projectId, int id) {
		if(type==TypeMovement.SORTIE) {
			ArrayList<Movement> movements = new MovementDAO().findByProjects(projectId);
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
	}

}
