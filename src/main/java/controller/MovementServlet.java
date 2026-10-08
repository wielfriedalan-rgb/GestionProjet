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
import java.util.Map;

import entity.Movement;
import entity.Project;

/**
 * Servlet implementation class MovementServlet
 */
@WebServlet("/movement")
public class MovementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	private MovementService movementService;

    public MovementServlet() {
    	this.projectService = new ProjectService();
		this.movementService = new MovementService();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		switch (page) {
		
		case "Create": { // doGet() pour la page Create
			int projectId;
			try {
				projectId = Integer.parseInt(request.getParameter("projectId"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			Project project = this.projectService.findProjectById(projectId);
			if(project==null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			request.setAttribute("project", project);
			request.getRequestDispatcher("/WEB-INF/views/movements/movementCreate.jsp").forward(request, response);
			return;
		}
		
		case "List": { // doGet() pour la page List
			int projectId;
			try {
				projectId = Integer.parseInt(request.getParameter("projectId"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			Project project = this.projectService.findProjectById(projectId);
			if(project == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			ArrayList<Movement> movements = this.movementService.findMovementByProject(projectId);
			request.setAttribute("movements", movements);
			request.setAttribute("project", project);
			request.getRequestDispatcher("/WEB-INF/views/movements/movementList.jsp").forward(request, response);
			return;
		}
		
		case "Detail": { // doGet() pour la page Detail
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			Movement movement = this.movementService.findMovementById(id);
			if(movement==null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			request.setAttribute("movement", movement);
			request.getRequestDispatcher("/WEB-INF/views/movements/movementDetail.jsp").forward(request, response);
			return;
		}
		
		case "Update": { // doGet() pour la page Update
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			Movement movement = this.movementService.findMovementById(id);
			if(movement==null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			request.setAttribute("movement", movement);
			request.getRequestDispatcher("/WEB-INF/views/movements/movementUpdate.jsp").forward(request, response);
			return;
		}
		
		case "Delete": { // doGet() pour la page Delete
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			Movement movement = this.movementService.findMovementById(id);
			if(movement==null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			request.setAttribute("movement", movement);
			request.getRequestDispatcher("/WEB-INF/views/movements/movementDelete.jsp").forward(request, response);
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
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			
			Map<String, String> error = this.movementService.validationMovement(-1, name, libelle, typeString, montantString, dateString, description, projectId);
			if(error.size()!=0) {
				Project project = this.projectService.findProjectById(projectId);
				if(project==null) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				request.setAttribute("project", project);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/views/movements/movementCreate.jsp").forward(request, response);
				return;
			}
			
			this.movementService.createMovement(name, libelle, typeString, montantString, dateString, description, projectId);
			response.sendRedirect(request.getContextPath() + "/movement?page=List&projectId=" + projectId);
			return;
		}
		
		case "Update": { // doPost() pour la page Update
			String name = request.getParameter("name");
			String libelle = request.getParameter("libelle");
			String typeString = request.getParameter("type");
			String montantString = request.getParameter("montant");
			String dateString = request.getParameter("date");
			String description = request.getParameter("description");
			int id, projectId;
			try {
				id = Integer.parseInt(request.getParameter("id"));
				projectId = Integer.parseInt(request.getParameter("projectId"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/project?page=List");
				return;
			}
			
			Map<String, String> error = this.movementService.validationMovement(id, name, libelle, typeString, montantString, dateString, description, projectId);
			if(error.size()!=0) {
				Movement movement = this.movementService.findMovementById(id);
				if(movement==null) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				request.setAttribute("movement", movement);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/WEB-INF/views/movements/movementUpdate.jsp").forward(request, response);
				return;
			}
			
			this.movementService.updateMovement(id, name, libelle, typeString, montantString, dateString, description);
			response.sendRedirect(request.getContextPath() + "/movement?page=Detail&id=" + id + "&modified=1");
			return;
		}
		
		case "Delete": { // doPost() pour la page Delete
			int id, projectId;
			try {
				id = Integer.parseInt(request.getParameter("id"));
				projectId = Integer.parseInt(request.getParameter("projectId"));
			}catch(NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/projectList");
				return;
			}
			this.movementService.deleteMovement(id);
			response.sendRedirect(request.getContextPath() + "/movement?page=List&projectId=" + projectId + "&deleted=1");
			return;
		}
		
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
