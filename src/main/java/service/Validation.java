package service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

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

}
