package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MovementService;
import service.ProjectService;

import java.io.IOException;

import entity.Project;

/**
 * Servlet implementation class MovementCreateServlet
 */
@WebServlet("/movementCreate")
public class MovementCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectId;
		try {
			projectId = Integer.parseInt(request.getParameter("projectId"));
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/projectList");
			return;
		}
		Project project = ProjectService.findProjectById(projectId);
		if(project==null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		request.setAttribute("project", project);
		request.getRequestDispatcher("/movements/movementCreate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String libelle = request.getParameter("libelle");
		String typeString = request.getParameter("type");
		String montantString = request.getParameter("montant");
		String dateString = request.getParameter("date");
		String description = request.getParameter("description");
		int projectId;
		try {
			projectId = Integer.parseInt(request.getParameter("projectId"));
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/projectList");
			return;
		}
		MovementService.createMovement(name, libelle, typeString, montantString, dateString, description, projectId);
		response.sendRedirect(request.getContextPath() + "/movementList?projectId=" + projectId);
	}

}
