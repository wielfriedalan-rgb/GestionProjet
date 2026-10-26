package dao;

import java.util.ArrayList;
import java.util.List;

import entity.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProjectDAO {

	public List<Project> findAll() {
		List<Project> projects = new ArrayList<>();
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			projects = em.createQuery("from Project", Project.class).getResultList();
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
		}
		return projects;
	}
	
	
	public Project findById(int idt) {
		Project project = new Project();
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			project = em.find(Project.class, idt);
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
		}
		return project;
	}
	
	
	public Project save(Project project) {
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin();
				em.persist(project);
				System.out.println("nouvel id : " + project.getId());
			trans.commit();
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
		}
		return project;
	}
	
	
	public Project update(Project projectUpdate) {
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin();
				Project project = em.find(Project.class, projectUpdate.getId());
				project.setName(projectUpdate.getName());
				project.setDescription(projectUpdate.getDescription());
				project.setBudget(projectUpdate.getBudget());
				project.setStartDate(projectUpdate.getStartDate());
				project.setEndDate(projectUpdate.getEndDate());
				project.setStatut(projectUpdate.getStatut());
			trans.commit();
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
		}
		return projectUpdate;
	}
	
	
	public void delete(int id) {
		EntityManagerFactory emf = null; //entityManagerFactory
		EntityManager em = null; //entityManager
		try {
			emf = Persistence.createEntityManagerFactory("projects");
			em = emf.createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin();
				em.remove(em.find(Project.class, id));
				System.out.println("suppression");
			trans.commit();
		}finally {
			if(emf!=null) emf.close();
			if(em!=null) em.close();
		}
	}
	
}
