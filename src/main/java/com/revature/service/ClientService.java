package com.revature.service;

import java.util.List;

import com.revature.dao.ClientDAO;
import com.revature.exceptions.ClientCreationException;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.DatabaseException;
import com.revature.model.Client;

public class ClientService {
	//ClientDAO is a dependency of ClientService
	private ClientDAO clientDAO;
	
	//creates a new ClientDAO object
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
	public Client getClientById(int id) throws ClientNotFoundException, DatabaseException {
		return clientDAO.getClientsById(id);
	}
	public Client addClient(Client client) throws DatabaseException {
		return clientDAO.addClient(client);
	}
}
