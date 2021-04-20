package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.ClientCreationException;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.DatabaseException;
import com.revature.model.Client;
import com.revature.util.ConnectionUtil;

public class ClientDAO {

	public List<Client> getClients() throws DatabaseException {
		List<Client> listOfClients = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {
			// define the sql query
			String sql = "SELECT * FROM clients";
			// Create a statement or prepared statement object
			// Using the connection object that we obtained from DriverManager inside of our
			// ConnectionUtil class
			Statement stmt = connection.createStatement();
			// execute the query
			ResultSet rs = stmt.executeQuery(sql);

			// Iterate through our resultset
			while (rs.next()) {

				int clientId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int age = rs.getInt("age");

				Client client = new Client(clientId, firstName, lastName, age);
				listOfClients.add(client);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Something happened with the database. Exception message is: " + e.getMessage());
		}

		return listOfClients;
	}

	public Client getClientsById(int id) throws ClientNotFoundException, DatabaseException {
		
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM clients c WHERE c.id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int clientId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int age = rs.getInt("age");
				
				return new Client(clientId, firstName, lastName, age);
			}
		} catch (SQLException e) {
		
			throw new DatabaseException("Something happened with the database. Exception message is: " + e.getMessage());
		}
			throw new ClientNotFoundException("Client with id " + id + " was not found.");
	}	

	public Client addClient(Client client) throws DatabaseException {
		
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO clients (first_name, last_name, age) VALUES (?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,client.getFirstName());
			pstmt.setString(2,client.getLastName());
			pstmt.setInt(3,client.getAge());
			
			//For DML such as insert update delete you would use something like execute UPDATE
			int recordsModified = pstmt.executeUpdate();
			if(recordsModified != 1) {
				throw new DatabaseException("Client was not able to be inserted into the database");
			}
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				client.setId(generatedKeys.getInt(1));
			} else {
				throw new DatabaseException("No ID was able to be obtained when trying to insert a client. " 
						+ "Client insertion failed" );
			}
			return client;
			
		} catch (SQLException e) {
			throw new DatabaseException("Something happened with the database. Exception message is: " + e.getMessage());
		}
			
	}	
	}


