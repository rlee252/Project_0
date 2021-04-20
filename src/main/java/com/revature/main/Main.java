package com.revature.main;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.controller.ClientController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.exceptions.BadParameterException;

import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.DatabaseException;
import com.revature.model.Client;
import com.revature.service.ClientService;

import io.javalin.Javalin;

public class Main {
	
	private static Javalin app;
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws BadParameterException {
		app = Javalin.create();
		app.before(ctx -> {
			String URI = ctx.req.getRequestURI();
			String httpMethod = ctx.req.getMethod();
			logger.info(httpMethod + " request to endpoint " + URI + " received");
		});
		mapControllers(new ClientController(), new ExceptionController());

		app.start(7000);

		ClientService clientService = new ClientService();

		try {
			List<Client> clients = clientService.getClients();
			System.out.println(clients);

			Client client = clientService.getClientById("1");
			System.out.println(client);

//			Client clientToAdd = new Client(0, "Betty", "Lam", 20);
//			Client addedClient = clientService.addClient(clientToAdd);
//			System.out.println("added client: " + addedClient);
		} catch (DatabaseException e) {

			e.printStackTrace();
		} catch (ClientNotFoundException e1) {
			System.out.println(e1.getMessage());
		}

	}

	public static void mapControllers(Controller... controllers) {
		for (int i = 0; i < controllers.length; i++) {
			controllers[i].mapEndpoints(app);
		}
	}

}
