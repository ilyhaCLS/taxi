package com.taxi.web.command;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taxi.web.Path;

public class LogoutCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		HashSet<Integer> loggedUsers =  (HashSet<Integer>)session.getServletContext().getAttribute("loggedUsers");
		loggedUsers.remove(session.getAttribute("user_id"));
		System.out.println("deleted "+ session.getAttribute("user_id")+ " from session");
		session.invalidate();

		return "redirect:"+Path.PAGE_INDEX;
	}
}