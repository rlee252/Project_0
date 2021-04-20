package com.revature.main;



import java.util.List;

import com.revature.exceptions.ClientCreationException;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.DatabaseException;
import com.revature.model.Client;
import com.revature.service.ClientService;


public class Main {

	public static void main(String[] args)  {
		ClientService clientService = new ClientService();
		
	
		try {
			List<Client> clients = clientService.getClients();
			System.out.println(clients);
			
			Client client = clientService.getClientById(1);
			System.out.println(client);
			
			Client clientToAdd = new Client(0,"Betty","Lam",20);
			Client addedClient =clientService.addClient(clientToAdd);
			System.out.println("added client: " + addedClient);
		} catch (DatabaseException e) {
		
			e.printStackTrace();
		} catch (ClientNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		
		

	}

}
