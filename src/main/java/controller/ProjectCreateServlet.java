package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProjectService;

import java.io.IOException;
import java.util.Map;

/**
 * Servlet implementation class ProjectCreateServlet
 */
@WebServlet("/projectCreate")
public class ProjectCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dans le doGet de ProjectCreateServlet ======================");
		request.getRequestDispatcher("/WEB-INF/views/projects/projectCreate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dans le doPost de ProjectCreateServlet ======================");
		String name = request.getParameter("name");
		String budgetString = request.getParameter("budget");
		String description = request.getParameter("description");
		String statutString = request.getParameter("statut");
		String startDateString = request.getParameter("startDate");
		String endDateString = request.getParameter("endDate");
		
		Map<String, String> error = ProjectService.validationProject(name, budgetString, description, startDateString, endDateString, statutString);
		if(error.size()!=0) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/views/projects/projectCreate.jsp").forward(request, response);
			return;
		}
		
		ProjectService.createProject(name, budgetString, description, startDateString, endDateString, statutString);
		
		System.out.println("Nouveau projet cree avec succes ! ======================");
		
		response.sendRedirect(request.getContextPath() + "/projectList");
	}

}
