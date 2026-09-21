package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.time.LocalDate;

import entity.Projects;
import entity.Statut;

public class ProjectDAO {
	
	public static ArrayList<Projects> findAll() {
		ArrayList<Projects> projects = new ArrayList<Projects>();
		String sql = "SELECT * FROM projet";
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			try(ResultSet resultSet = statement.executeQuery()){
				System.out.println(" ------ Debut de remplissage  ------");
				while(resultSet.next()) {
					int id = resultSet.getInt("id");
					String nom = resultSet.getString("nom");
					double budget = resultSet.getDouble("budget");
					String description = resultSet.getString("description_projet"); 
					LocalDate startDate = ( resultSet.getDate("date_debut") != null ) 
							? resultSet.getDate("date_debut").toLocalDate()
									: null;
					LocalDate endDate = ( resultSet.getDate("date_fin") != null ) 
							? resultSet.getDate("date_fin").toLocalDate()
									: null;
					String statutString = resultSet.getString("statut");
					Statut statut = Statut.valueOf(statutString);
					projects.add(new Projects(id, nom, budget, description, startDate, endDate, statut));
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println(" ------ Fin de remplissage  ------");
		return projects;
	}
	
	
	public static Projects findById(int idt) {
		String sql = "SELECT * FROM projet WHERE id = ?";
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			statement.setInt(1, idt);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) {
					int id = resultSet.getInt("id");
					String nom = resultSet.getString("nom");
					double budget = resultSet.getDouble("budget");
					String description = resultSet.getString("description_projet"); 
					LocalDate startDate = ( resultSet.getDate("date_debut") != null ) 
							? resultSet.getDate("date_debut").toLocalDate()
									: null;
					LocalDate endDate = ( resultSet.getDate("date_fin") != null ) 
							? resultSet.getDate("date_fin").toLocalDate()
									: null;
					String statutString = resultSet.getString("statut");
					Statut statut = Statut.valueOf(statutString);
					return new Projects(id, nom, budget, description, startDate, endDate, statut);
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void save(Projects project) {
		String sql = "INSERT INTO projet (nom, description_projet, budget, date_debut, date_fin, statut) VALUES (?, ?, ?, ?, ?, ?)";
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setDouble(3, project.getBudget());
			statement.setDate(4, Date.valueOf(project.getStartDate()));
			if (project.getEndDate() != null ) {
				statement.setDate(5, Date.valueOf(project.getEndDate()));
			}else {
				statement.setNull(5, Types.DATE);
			}
			statement.setString(6, project.getStatut().name());
			statement.executeUpdate();
			System.out.println(" ------ Query execute !  ------");
		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}
	
	
	public static void update(Projects projectUpdate) {
		String sql = "UPDATE projet SET nom = ?, description_projet = ?, budget = ?, date_debut = ?, date_fin = ?, statut = ? WHERE id = ?";
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			statement.setString(1, projectUpdate.getName());
			statement.setString(2, projectUpdate.getDescription());
			statement.setDouble(3, projectUpdate.getBudget());
			statement.setDate(4, Date.valueOf(projectUpdate.getStartDate()));
			if (projectUpdate.getEndDate() != null ) {
				statement.setDate(5, Date.valueOf(projectUpdate.getEndDate()));
			}else {
				statement.setNull(5, Types.DATE);
			}
			statement.setString(6, projectUpdate.getStatut().name());
			statement.setInt(7, projectUpdate.getId());
			statement.executeUpdate();
			System.out.println(" ------ Query execute !  ------");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void delete(int id) {
		String sql = "DELETE FROM projet WHERE id= ?";
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.println(" ------ Query execute !  ------");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
