package restController;

import java.util.HashMap;
import java.util.List;

import entity.Movement;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.MovementService;

@Path("/projects/project/{projectId}/movements")
public class MovementRestController {

	private MovementService movementService = new MovementService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movement> getAllMovement(@PathParam("projectId") int projectId){
		return movementService.findMovementByProject(projectId);
	}
	
	@GET
	@Path("/movement/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movement getMovement(@PathParam("id") int id) {
		return movementService.findMovementById(id);
	}
	
	@DELETE
	@Path("/movement/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMovement(@PathParam("id") int id) {
		HashMap<String, String> response = new HashMap<String, String>();
		movementService.deleteMovement(id);
		response.put("message", "Projet supprime avec succes !!");
		return Response.ok(response).build();
	}
	
	@POST
	@Path("/movement")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMovement(@PathParam("projectId") int projectId, Movement movement) {
		Movement m = movementService.createMovement(
				movement.getName(),
				movement.getLibelle(),
				String.valueOf(movement.getType()), 
				String.valueOf(movement.getMontant()), 
				String.valueOf(movement.getDate()), 
				movement.getDescription(),
				projectId
				);
		return Response.status(Response.Status.CREATED).entity(m).build();
	}
	
	@PUT
	@Path("/movement/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMovement(@PathParam("id") int id, Movement movement) {
		Movement m = movementService.updateMovement(
				id,
				movement.getName(),
				movement.getLibelle(),
				String.valueOf(movement.getType()), 
				String.valueOf(movement.getMontant()), 
				String.valueOf(movement.getDate()), 
				movement.getDescription()
				);
		return Response.ok(m).build();
	}
	
}
