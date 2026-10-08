package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProjectService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import entity.Project;

/**
 * Servlet implementation class ProjectServlet
 */
@WebServlet("/project")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;

    public ProjectServlet() {
    	this.projectService = new ProjectService();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		switch (page) {
		
		case "Create": { // doGet() pour la page Create
			System.out.println("Dans le doGet de ProjectCreateServlet ======================");
			request.getRequestDispatcher("/WEB-INF/views/projects/projectCreate.jsp").forward(request, response);
			return;
		}
		
		case "List":{ // doGet() pour la page List
			System.out.println("Dans le doGet de ProjectListServlet ======================");
			ArrayList<Project> projects = this.projectService.findAllProject();
			request.setAttribute("projects", projects);
			request.getRequestDispatcher("/WEB-INF/views/projects/projectList.jsp").forward(request, response);
			return;
		}
		
		case "Detail":{ // doGet() pour la page Detail
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			Project project = this.projectService.findProjectById(id);
			if(project == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			request.setAttribute("project", project);
			request.getRequestDispatcher("/WEB-INF/views/projects/projectDetail.jsp").forward(request, response);
			return;
		}
		
		case "Update":{ // doGet() pour la page Update
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			Project project = this.projectService.findProjectById(id);
			if(project == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			request.setAttribute("project", project);
			request.getRequestDispatcher("/WEB-INF/views/projects/projectUpdate.jsp").forward(request, response);
			return;
		}
		
		case "Delete":{ // doGet() pour la page Delete
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			Project project = this.projectService.findProjectById(id);
			if(project == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			request.setAttribute("project", project);
			request.getRequestDispatcher("/WEB-INF/views/projects/projectDelete.jsp").forward(request, response);
			return;
		}
		
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		switch (page) {
		
		case "Create": { // doPost() pour la page Create
			System.out.println("Dans le doPost de ProjectCreateServlet ======================");
			String name = request.getParameter("name");
			String budgetString = request.getParameter("budget");
			String description = request.getParameter("description");
			String statutString = request.getParameter("statut");
			String startDateString = request.getParameter("startDate");
			String endDateString = request.getParameter("endDate");
			
			Map<String, String> error = this.projectService.validationProject(name, budgetString, description, startDateString, endDateString, statutString);
			if(error.size()!=0) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/views/projects/projectCreate.jsp").forward(request, response);
				return;
			}
			this.projectService.createProject(name, budgetString, description, startDateString, endDateString, statutString);
			response.sendRedirect(request.getContextPath() + "/project?page=List");
			return;
		}
		
		case "Update": { // doPost() pour la page Update
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			String name = request.getParameter("name");
			String budgetString = request.getParameter("budget");
			String description = request.getParameter("description");
			String statutString = request.getParameter("statut");
			String startDateString = request.getParameter("startDate");
			String endDateString = request.getParameter("endDate");
			Map<String, String> error = this.projectService.validationProject(name, budgetString, description, startDateString, endDateString, statutString);
			if(error.size()!=0) {
				Project project = this.projectService.findProjectById(id);
				if(project == null) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				request.setAttribute("project", project);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/views/projects/projectUpdate.jsp").forward(request, response);
				return;
			}
			this.projectService.updateProject(id, name, budgetString, description, startDateString, endDateString, statutString);
			response.sendRedirect(request.getContextPath() + "/project?page=Detail&id=" + id);
			return;
		}
		
		case "Delete": { // doPost() pour la page Delete
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			boolean deleteProject = this.projectService.deleteProject(id);
			if(deleteProject) {
				response.sendRedirect(request.getContextPath() + "/project?page=List&deleted=1");
			}else {
				response.sendRedirect(request.getContextPath() + "/project?page=Detail&id=" + id + "&deleteError=1");
			}
			return;
		}
		
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
