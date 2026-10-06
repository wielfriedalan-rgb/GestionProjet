package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MovementService;

import java.io.IOException;
import java.util.Map;

import entity.Movement;

/**
 * Servlet implementation class MovementUpdateServlet
 */
@WebServlet("/movementUpdate")
public class MovementUpdateServlet extends HttpServlet {
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
		Movement movement = MovementService.findMovementById(id);
		if(movement==null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		request.setAttribute("movement", movement);
		request.getRequestDispatcher("/WEB-INF/views/movements/movementUpdate.jsp").forward(request, response);
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
		int id, projectId;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			projectId = Integer.parseInt(request.getParameter("projectId"));
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/projectList");
			return;
		}
		
		Map<String, String> error = MovementService.validationMovement(id, name, libelle, typeString, montantString, dateString, description, projectId);
		if(error.size()!=0) {
			Movement movement = MovementService.findMovementById(id);
			if(movement==null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			request.setAttribute("movement", movement);
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/views/movements/movementUpdate.jsp").forward(request, response);
			return;
		}
		
		MovementService.updateMovement(id, name, libelle, typeString, montantString, dateString, description);
		response.sendRedirect(request.getContextPath() + "/movementDetail?id=" + id);
	}

}
