package restController;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.FinanceService;
import service.FinanceSituation;
import service.ProjectService;

import java.util.HashMap;
import java.util.List;

import entity.Project;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;

@Path("/projects")
public class ProjectRestController {

	private ProjectService projectService = new ProjectService();
	private FinanceService finance = new FinanceService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getAllProject() {
		return projectService.findAllProject();
	}
	
	@GET
	@Path("/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Project getProject(@PathParam("id") int id) {
		return projectService.findProjectById(id);
	}
	
	@DELETE
	@Path("/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProject(@PathParam("id") int id) {
		HashMap<String, String> response = new HashMap<String, String>();
		if(!projectService.deleteProject(id)) {
			response.put("message", "Projet non supprime : le projet contient un ou plusieurs mouvement(s)");
			return Response.ok(response).build();
		}
		response.put("message", "Projet supprime avec succes !!");
		return Response.ok(response).build();
	}
	
	@POST
	@Path("/project")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProject(Project project) {
		projectService.createProject(
				project.getName(),
				String.valueOf(project.getBudget()),
				project.getDescription(), 
				String.valueOf(project.getStartDate()), 
				String.valueOf(project.getEndDate()), 
				String.valueOf(project.getStatut())
				);
		return Response.ok("Projet cree avec sucees !!").build();
	}
	
	@PUT
	@Path("/project/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProject(@PathParam("id") int id, Project project) {
		projectService.updateProject(
				id,
				project.getName(),
				String.valueOf(project.getBudget()),
				project.getDescription(), 
				String.valueOf(project.getStartDate()), 
				String.valueOf(project.getEndDate()), 
				String.valueOf(project.getStatut())
				);
		return Response.ok("Projet modifier avec sucees !!").build();
	}
	
	@GET
	@Path("/project/{id}/finance")
	@Produces(MediaType.APPLICATION_JSON)
	public FinanceSituation getFinance(@PathParam("id") int id) {
		return finance.financeCalculation(projectService.findProjectById(id));
	}
	
}
