package entity;

import java.util.Date;

public class Projects {
	
	
	private int id;
	private String name;
	private double budget;
	private String description;
	private Date startDate = new Date();
	private Date endDate = new Date();
	private Statut statut;
	
//	Declaratioon de constructeur de classe Projects
	public Projects(int id, String name, double budget, String description, Date startDate, Date endDate, Statut statut) {
		this.setId(id);
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}
	
	public Projects(String name, double budget, String description, Date startDate, Date endDate, Statut statut) {
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}
	public Projects(String name, double budget, String description) {
		this.setName(name);
		this.setBudget(budget);
		this.setDescription(description);
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
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
}
