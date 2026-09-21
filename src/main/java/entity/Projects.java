package entity;

import java.time.LocalDate;

public class Projects {
	
	
	private int id;
	private String name;
	private double budget;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private Statut statut;
	
//	Declaratioon de constructeur de classe Projects
	public Projects(int id, String name, double budget, String description, LocalDate startDate, LocalDate endDate, Statut statut) {
		this.setId(id);
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setStatut(statut);
	}
	public Projects(String name, double budget, String description, LocalDate startDate, LocalDate endDate, Statut statut) {
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setStatut(statut);
	}
	public Projects(String name, double budget, String description, LocalDate startDate, Statut statut) {
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setStatut(statut);
	}
	public Projects(String name, double budget, String description, LocalDate startDate) {
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
		this.setStartDate(startDate);
	}
	public Projects(String name, double budget, String description) {
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
	}
	public Projects(int id) {
		this.setId(id);
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
	
}
