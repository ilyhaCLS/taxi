package com.taxi.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taxi.web.Path;

public class RideCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("option1");
		session.removeAttribute("option2");
		session.removeAttribute("dist");
		session.removeAttribute("time");
		session.removeAttribute("ride");
		session.removeAttribute("carClass");
		
		return Path.PAGE_RIDE;
	}
}