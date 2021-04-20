package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.ClientNotFoundException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controller {
	
	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

	//Exception Handler
		private ExceptionHandler<ClientNotFoundException> clientNotFoundExceptionExceptionHandler = (e,ctx)->{
			logger.warn("a user provided unknown client. Exception message is: " + e.getMessage());
			ctx.status(400);
		};
		private ExceptionHandler<BadParameterException> BadParamterExceptionExceptionHandler = (e,ctx)->{
			logger.warn("a user provided bad parameters. Exception message is: " + e.getMessage());
			ctx.status(400);
		};
		
		

	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(ClientNotFoundException.class, clientNotFoundExceptionExceptionHandler);
		app.exception(BadParameterException.class, BadParamterExceptionExceptionHandler);
	}

}
