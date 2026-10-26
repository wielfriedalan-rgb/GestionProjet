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

@Entity @Table(name="projet")
public class Project {
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="nom")
	private String name;
	private double budget;
	@Column(name="description_projet")
	private String description;
	@Column(name="date_debut")
	private LocalDate startDate;
	@Column(name="date_fin")
	private LocalDate endDate;
	@Enumerated(EnumType.STRING)
	private Statut statut;
	
//	Declaratioon de constructeur de classe Projects
	public Project(int id, String name, double budget, String description, LocalDate startDate, LocalDate endDate, Statut statut) {
		this.setId(id);
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setStatut(statut);
	}
	public Project(String name, double budget, String description, LocalDate startDate, LocalDate endDate, Statut statut) {
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setStatut(statut);
	}
	public Project(int id) {
		this.setId(id);
	}
	public Project() {
	}
	
	
//	Declaration des Getters et Setters	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", budget=" + budget + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", statut=" + statut + "]";
	}
	
}
