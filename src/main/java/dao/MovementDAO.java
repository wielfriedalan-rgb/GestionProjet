package dao;

import java.util.ArrayList;
import java.util.List;

import entity.Movement;
import entity.Project;
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
	
	
	public List<Movement> findByProjects(Project project) {
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		List<Movement> movements = new ArrayList<>();
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			movements = em.createQuery("SELECT m FROM Movement m WHERE m.project = :project", Movement.class)
						.setParameter("project", project)
						.getResultList();
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
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
