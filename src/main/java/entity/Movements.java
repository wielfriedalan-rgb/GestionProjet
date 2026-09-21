package entity;

import java.time.LocalDate;


public class Movements {
	
	private int id;
	private int projectId;
	private String name;
	private String libelle;
	private double montant;
	private TypeMovement type;
	private LocalDate date;
	private String description;
	
	public Movements(int id, String name, String libelle, TypeMovement type, double montant, LocalDate date, String description, int projectId) {
		this.setId(id);
		this.setName(name);
		this.setLibelle(libelle);
		this.setType(type);
		this.setMontant(montant);
		this.setDate(date);
		this.setDescription(description);
		this.setProjectId(projectId);
	}
	public Movements(String name, String libelle, TypeMovement type, double montant,  LocalDate dateMovement, String description, int projectId) {
		this.setName(name);
		this.setLibelle(libelle);
		this.setType(type);
		this.setMontant(montant);
		this.setDate(dateMovement);
		this.setDescription(description);
		this.setProjectId(projectId);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public TypeMovement getType() {
		return type;
	}
	public void setType(TypeMovement type) {
		this.type = type;
	}
	
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getDescriptionMovement() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
