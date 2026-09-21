package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.Movements;
import entity.TypeMovement;

public class MovementDAO {

	public static Movements findById(int idt) {
		String sql = "SELECT * FROM mouvement WHERE id = ?";
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			statement.setInt(1, idt);
			try(ResultSet resultSet = statement.executeQuery()){
				if(resultSet.next()) {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("nom");
					String libelle = resultSet.getString("libelle");
					TypeMovement type = TypeMovement.valueOf(resultSet.getString("type_mouvement"));
					double montant = resultSet.getDouble("montant");
					LocalDate date = resultSet.getDate("date_mouvement").toLocalDate();
					String description = resultSet.getString("description_mouvement");
					int projectId = resultSet.getInt("projet_id");
					return new Movements(id, name, libelle, type, montant, date, description, projectId);
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static ArrayList<Movements> findByProjects(int projectIdt) {
		String sql = "SELECT * FROM mouvement WHERE projet_id = ?";
		ArrayList<Movements> movements = new ArrayList<Movements>();
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
					movements.add( new Movements(id, name, libelle, type, montant, date, description, projectId));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return movements;
	}
	
	
	public static void save(Movements movement) {
		String sql = "INSERT INTO mouvement (nom, libelle, type_mouvement, montant, date_mouvement, description_mouvement, projet_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			statement.setString(1, movement.getName());
			statement.setString(2, movement.getLibelle());
			statement.setString(3, movement.getType().name());
			statement.setDouble(4, movement.getMontant());
			statement.setDate(5, Date.valueOf(movement.getDate()));
			statement.setString(6, movement.getDescriptionMovement());
			statement.setInt(7, movement.getProjectId());
			statement.executeUpdate();
			System.out.println(" ------ Query execute !  ------");
		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}
	
		
	public static void update(Movements movement) {
		String sql = "UPDATE mouvement SET nom = ? , libelle = ? , type_mouvement = ? , montant = ? , date_mouvement = ? , description_mouvement = ? , projet_id = ? WHERE id = ?";
		try(Connection connection = ConnectionDB.getConnectionDB(); PreparedStatement statement = connection.prepareStatement(sql)){
			System.out.println(" ====== Connexion a la Base de Donnee reussi !!  ======");
			statement.setString(1, movement.getName());
			statement.setString(2, movement.getLibelle());
			statement.setString(3, movement.getType().name());
			statement.setDouble(4, movement.getMontant());
			statement.setDate(5, Date.valueOf(movement.getDate()));
			statement.setString(6, movement.getDescriptionMovement());
			statement.setInt(7, movement.getProjectId());
			statement.setInt(8, movement.getId());
			statement.executeUpdate();
			System.out.println(" ------ Query execute !  ------");
		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}
	
	
	public static void delete(int id) {
		String sql = "DELETE FROM mouvement WHERE id= ?";
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
