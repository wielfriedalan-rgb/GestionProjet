package service;

public class FinanceSituation {

	private double totalEntrees;
	private double totalSorties;
	private double solde;
	private double budgetRestant;
	
	public FinanceSituation(double totalEntrees, double totalSorties, double solde, double budgetRestant) {
		this.totalEntrees = totalEntrees;
		this.totalSorties = totalSorties;
		this.solde = solde;
		this.budgetRestant = budgetRestant;
	}
	
	public double getTotalEntrees() {return totalEntrees;}
	public double getTotalSorties() {return totalSorties;}
	public double getSolde() {return solde;}
	public double getBudgetRestant() {return budgetRestant;}
	
}
