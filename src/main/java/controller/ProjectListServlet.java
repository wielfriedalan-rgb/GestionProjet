package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProjectService;

import java.io.IOException;
import java.util.ArrayList;

import entity.Projects;

/**
 * Servlet implementation class ProjectListServlet
 */
@WebServlet("/projectList")
public class ProjectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dans le doGet de ProjectListServlet ======================");
		ArrayList<Projects> projects = ProjectService.findAllProject();
		request.setAttribute("projects", projects);
		request.getRequestDispatcher("/projects/projectList.jsp").forward(request, response);
	}


}
