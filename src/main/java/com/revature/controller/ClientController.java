package com.revature.controller;

import java.util.List;

import com.revature.model.Client;
import com.revature.service.ClientService;

import io.javalin.Javalin;

import io.javalin.http.Handler;

public class ClientController implements Controller {
	
	private ClientService clientService;
	
	public ClientController() {
		this.clientService = new ClientService();
	}

	private Handler getClients = ctx -> {
		List<Client> client = clientService.getClients();
		ctx.json(client);
		ctx.status(200); //success
	};

	private Handler getClientsById = ctx -> {
		String clientId = ctx.pathParam("client_id");
		Client client = clientService.getClientById(clientId);
		ctx.json(client);
		ctx.status(200); //success
	};
	private Handler postClients = ctx -> {
		ctx.html("<h1>postClients Handler</h1>");
	};

//	private Handler getClientAccountsById = ctx -> {
//		
//	};
//
//	private Handler getClientAccountsByIdLessThanGreaterThan = ctx -> {
//
//	};

	
	
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/clients", getClients);
		app.get("/clients/:client_id", getClientsById);

		app.post("/clients", postClients);
//		app.get("/clients/:client_id/accounts", getClientAccountsById);
//		app.get("/clients/:client_id/accounts", getClientAccountsById);

	}

}
