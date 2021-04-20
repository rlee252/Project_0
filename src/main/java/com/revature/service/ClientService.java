package com.revature.service;

import java.util.List;

import com.revature.dao.ClientDAO;
import com.revature.exceptions.BadParameterException;

import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.DatabaseException;
import com.revature.model.Client;

public class ClientService {
	
	private ClientDAO clientDAO;
	
	public ClientService() {
		this.clientDAO = new ClientDAO();
	}
	
	//Mocked Objects through Mockito
	//For passing in the mocked pirateDAO whenever I am unit testing
	public ClientService(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	
	public List<Client> getClients() throws DatabaseException{
		return clientDAO.getClients();
	}
	//Exception Mapping Exception Handler
	public Client getClientById(String stringId) throws ClientNotFoundException, DatabaseException, BadParameterException {
		try {
				int id = Integer.parseInt(stringId);
				return clientDAO.getClientsById(id);
		} catch (NumberFormatException e) {
			throw new BadParameterException("Pirate id must be an int. User provided " + stringId);
		}
	}
	public Client addClient(Client client) throws DatabaseException {
		return clientDAO.addClient(client);
	}
}
