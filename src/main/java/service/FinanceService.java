package service;

import java.util.List;

import dao.MovementDAO;
import entity.Movement;
import entity.Project;
import entity.TypeMovement;

public class FinanceService {
	private MovementDAO movementDAO;
	
	public FinanceService() {
		this.movementDAO = new MovementDAO();
	}

	public FinanceSituation financeCalculation(Project project) {
		List<Movement> movements = this.movementDAO.findByProjects(project);
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
