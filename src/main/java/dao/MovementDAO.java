package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.Movement;
import entity.TypeMovement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MovementDAO {

	public Movement findById(int idt) {
		Movement movement = new Movement();
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			movement = em.find(Movement.class, idt);
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
		}
		return movement;
	}
	
	
	public ArrayList<Movement> findByProjects(int projectIdt) {
		String sql = "SELECT * FROM mouvement WHERE projet_id = ?";
		ArrayList<Movement> movements = new ArrayList<Movement>();
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			statement.setInt(1, projectIdt);
			try(ResultSet resultSet = statement.executeQuery()){
				while(resultSet.next()) {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("nom");
					String libelle = resultSet.getString("libelle");
					TypeMovement type = TypeMovement.valueOf(resultSet.getString("type_mouvement"));
					double montant = resultSet.getDouble("montant");
					LocalDate date = resultSet.getDate("date_mouvement").toLocalDate();
					String description = resultSet.getString("description_mouvement");
					int projectId = resultSet.getInt("projet_id");
					movements.add( new Movement(id, name, libelle, type, montant, date, description, projectId));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return movements;
	}
	
	
	public Movement save(Movement movement) {
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin();
				em.persist(movement);
				System.out.println("nouvel id : " + movement.getId());
			trans.commit();
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
		}
		return movement;
	}
	
		
	public Movement update(Movement movement) {
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin();
				Movement mov = em.find(Movement.class, movement.getId());
				mov.setName(movement.getName());
				mov.setLibelle(movement.getLibelle());
				mov.setDescription(movement.getDescription());
				mov.setMontant(movement.getMontant());
				mov.setDate(movement.getDate());
				mov.setType(movement.getType());
			trans.commit();
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
		}
		return movement;
	}
	
	
	public void delete(int id) {
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin();
				em.remove(em.find(Movement.class, id));
				System.out.println("suppression");
			trans.commit();
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
		}
	}
	
}
