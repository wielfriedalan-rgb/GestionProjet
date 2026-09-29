package service;

import java.util.ArrayList;

import dao.MovementDAO;
import entity.Movement;
import entity.Project;
import entity.TypeMovement;

public class FinanceService {

	public static FinanceSituation financeCalculation(Project project) {
		ArrayList<Movement> movements = MovementDAO.findByProjects(project.getId());
		double totalEntrees=0, totalSorties=0;
		for(Movement mov : movements) {
			if(mov.getType()==TypeMovement.ENTREE) {
				totalEntrees += mov.getMontant();
			}else if(mov.getType()==TypeMovement.SORTIE) {
				totalSorties += mov.getMontant();
			}
		}
		double solde = totalEntrees - totalSorties;
		double budgetRestant = project.getBudget() - totalSorties;
		return new FinanceSituation(totalEntrees, totalSorties, solde, budgetRestant);
	}
	
}
