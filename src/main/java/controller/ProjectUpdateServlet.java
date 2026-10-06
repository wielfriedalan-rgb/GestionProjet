package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProjectService;

import java.io.IOException;
import java.util.Map;

import entity.Project;

/**
 * Servlet implementation class ProjectUpdateServlet
 */
@WebServlet("/projectUpdate")
public class ProjectUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		Project project = ProjectService.findProjectById(id);
		if(project == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		request.setAttribute("project", project);
		request.getRequestDispatcher("/projects/projectUpdate.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/projectList");
			return;
		}
		String name = request.getParameter("name");
		String budgetString = request.getParameter("budget");
		String description = request.getParameter("description");
		String statutString = request.getParameter("statut");
		String startDateString = request.getParameter("startDate");
		String endDateString = request.getParameter("endDate");
		
		Map<String, String> error = ProjectService.validationProject(name, budgetString, description, startDateString, endDateString, statutString);
		if(error.size()!=0) {
			Project project = ProjectService.findProjectById(id);
			if(project == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			request.setAttribute("project", project);
			request.setAttribute("error", error);
			request.getRequestDispatcher("/projects/projectUpdate.jsp").forward(request, response);
			return;
		}
		
		ProjectService.updateProject(id, name, budgetString, description, startDateString, endDateString, statutString);
		
		response.sendRedirect(request.getContextPath() + "/projectDetail?id=" + id);
	}

}
