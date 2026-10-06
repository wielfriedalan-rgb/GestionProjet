package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MovementService;
import service.ProjectService;

import java.io.IOException;
import java.util.ArrayList;

import entity.Movement;
import entity.Project;

/**
 * Servlet implementation class MovementListServlet
 */
@WebServlet("/movementList")
public class MovementListServlet extends HttpServlet {
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
		if(project == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		ArrayList<Movement> movements = MovementService.findMovementByProject(projectId);
		request.setAttribute("movements", movements);
		request.setAttribute("project", project);
		request.getRequestDispatcher("/WEB-INF/views/movements/movementList.jsp").forward(request, response);
	}

}
