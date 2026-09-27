package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MovementService;

import java.io.IOException;

import entity.Movements;

/**
 * Servlet implementation class MovementDeleteServlet
 */
@WebServlet("/movementDelete")
public class MovementDeleteServlet extends HttpServlet {
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
		Movements movement = MovementService.findMovementById(id);
		if(movement==null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		request.setAttribute("movement", movement);
		request.getRequestDispatcher("/movements/movementDelete.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id, projectId;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			projectId = Integer.parseInt(request.getParameter("projectId"));
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/projectList");
			return;
		}
		MovementService.deleteMovement(id);
		response.sendRedirect(request.getContextPath() + "/movementList?projectId=" + projectId + "&deleted=1");
	}

}
