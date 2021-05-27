package com.taxi.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taxi.web.command.Command;
import com.taxi.web.command.CommandContainer;



public class Controller extends HttpServlet {
	private static final long serialVersionUID = 2423353715955164816L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		process(request, response);
	}

	
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		
		String commandName = request.getParameter("command");
		
		Command command = CommandContainer.get(commandName);
		
		String forward = command.execute(request, response);
		
		if (forward.contains("redirect:")) {
			response.sendRedirect(forward.replace("redirect:", ""));
		}else {
			RequestDispatcher disp = request.getRequestDispatcher(forward);
			disp.forward(request, response);
		}
	}
}