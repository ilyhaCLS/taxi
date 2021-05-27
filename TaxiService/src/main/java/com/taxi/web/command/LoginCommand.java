package com.taxi.web.command;

import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import com.taxi.web.Path;
import com.taxi.web.model.entity.Role;
import com.taxi.web.model.entity.User;
import com.taxi.web.model.service.UserService;
import com.taxi.web.security.PasswordEncoder;

public class LoginCommand extends Command {
	private static final long serialVersionUID = 1L;
	
	private PasswordEncoder encoder = PasswordEncoder.getInstance();
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
		UserService userService = new UserService();
		
		HttpSession session = req.getSession();
		
		Optional<Cookie> maybeCookie = Arrays.stream(req.getCookies()).filter(e->e.getName().equals("lang")).findFirst();
		if(maybeCookie.isEmpty()) {
			res.addCookie(new Cookie("lang", "ru"));
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", "ru");
		}else {
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", maybeCookie.get().getValue());
		}
		
		
		String forward = null;
		
		User us = userService.getUser(req.getParameter("login"));
		if(us == null) {
				res.setHeader("message_info", "wrong_cred");
				forward = Path.PAGE_LOGIN;
		}else {
			try {
				if(us.getPassword().equals(encoder.encode(req.getParameter("password"), us.getSalt()))) {
					HashSet<Integer> loggedUsers = (HashSet<Integer>)session.getServletContext().getAttribute("loggedUsers");
					
					if(loggedUsers.contains(us.getId())) {
						res.setHeader("message_info", "already_logged");
						return Path.PAGE_LOGIN;
					}
					
					loggedUsers.add(us.getId());
					
					int hour = LocalTime.now().getHour();
					String greeting = null;
					if(hour < 6) {
						greeting = "header.night";
					}else if(hour > 5 && hour < 12) {
						greeting = "header.morning";
					}else if(hour > 11 & hour < 18) {
						greeting = "header.day";
					}else {
						greeting = "header.evening";
					}
					
					session.setAttribute("greeting", greeting);
					session.setAttribute("name", userService.getFirstName(us.getId()));
					session.setAttribute("role", Role.valueOf(us.getRole()));
					session.setAttribute("user_id", us.getId());
					
					System.out.println("new user logged in : "+us.getId());
					
					if(us.getRole().equals("ADMIN")) {
						forward = Path.PAGE_ADMIN;
						return forward;
					}
					forward = "redirect:"+Path.PAGE_INDEX;
				}else {
					res.setHeader("message_info", "wrong_cred");
					forward = Path.PAGE_LOGIN;
				}
			}catch(InvalidKeySpecException e) {
				e.printStackTrace();
			}
		}
		System.out.println("inside login command");
		return forward;
	}
}