package com.taxi.web.listener;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent event) {
	
	}

	public void contextInitialized(ServletContextEvent event) {

		ServletContext servletContext = event.getServletContext();
		servletContext.setAttribute("loggedUsers", new HashSet<Integer>());
		
		try {
			Class.forName("com.taxi.web.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		}
		System.out.println("context initialized!");
	}
}