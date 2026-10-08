package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FinanceService;
import service.ProjectService;

import java.io.IOException;

import entity.Project;

/**
 * Servlet implementation class FinanceSituationServlet
 */
@WebServlet("/financeSituation")
public class FinanceSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	private FinanceService financeService;
	
	public FinanceSituationServlet() {
		this.projectService = new ProjectService();
		this.financeService = new FinanceService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/projectList");
			return;
		}
		Project project = this.projectService.findProjectById(id);
		if(project == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		request.setAttribute("project", project);
		request.setAttribute("finance", this.financeService.financeCalculation(project));
		request.getRequestDispatcher("/WEB-INF/views/projects/financeSituation.jsp").forward(request, response);
	}
}
