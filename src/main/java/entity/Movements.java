package entity;

import java.util.Date;


public class Movements {
	
	private int id;
	private int projectId;
	private String libelle;
	private double montant;
	private TypeMovement type;
	private Date date = new Date();
	private String description;
	
	public Movements(int id, int projectId, String libelle, double montant, TypeMovement type, Date date, String description) {
		this.setId(id);
		this.setProjectId(projectId);
		this.setLibelle(libelle);
		this.setMontant(montant);
		this.setType(type);
		this.setDate(date);
		this.setDescription(description);
	}
	public Movements(int projectId, String libelle, double montant, TypeMovement type, Date dateMovement, String description) {
		this.setProjectId(projectId);
		this.setLibelle(libelle);
		this.setMontant(montant);
		this.setType(type);
		this.setDate(dateMovement);
		this.setDescription(description);
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
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescriptionMovement() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
