package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import entity.Projects;

public class ProjectDAO {
	
	public static List<Projects> findAll() {
		return null;
	}
	
	public static Projects findById(int id) {
		String sql = "SELECT nom, description_projet, budget FROM projet WHERE id = ?";
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			statement.setInt(1, id);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) {
					String nom = resultSet.getString("nom");
					String description = resultSet.getString("description_projet"); 
					double budget = resultSet.getDouble("budget");
					return new Projects(nom, budget, description);
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void save(Projects project) {
		
	}
	
	public static void update(Projects project) {
		
	}
	
	public static void delete(int id) {
		
	}
	
}
