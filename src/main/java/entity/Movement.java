package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name="mouvement")
public class Movement {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="projet_id")
	private int projectId;
	@Column(name="nom")
	private String name;
	private String libelle;
	private double montant;
	@Column(name="type_mouvement")
	@Enumerated(EnumType.STRING)
	private TypeMovement type;
	@Column(name="date_mouvement")
	private LocalDate date;
	@Column(name="description_mouvement")
	private String description;
	
	public Movement(int id, String name, String libelle, TypeMovement type, double montant, LocalDate date, String description, int projectId) {
		this.setId(id);
		this.setName(name);
		this.setLibelle(libelle);
		this.setType(type);
		this.setMontant(montant);
		this.setDate(date);
		this.setDescription(description);
		this.setProjectId(projectId);
	}
	public Movement(int id, String name, String libelle, TypeMovement type, double montant, LocalDate date, String description) {
		this.setId(id);
		this.setName(name);
		this.setLibelle(libelle);
		this.setType(type);
		this.setMontant(montant);
		this.setDate(date);
		this.setDescription(description);
	}
	public Movement(String name, String libelle, TypeMovement type, double montant,  LocalDate date, String description, int projectId) {
		this.setName(name);
		this.setLibelle(libelle);
		this.setType(type);
		this.setMontant(montant);
		this.setDate(date);
		this.setDescription(description);
		this.setProjectId(projectId);
	}
	public Movement() {
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		return "Movement [id=" + id + ", projectId=" + projectId + ", name=" + name + ", libelle=" + libelle
				+ ", montant=" + montant + ", type=" + type + ", date=" + date + ", description=" + description + "]";
	}

}
