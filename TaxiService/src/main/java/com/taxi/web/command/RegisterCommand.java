package com.taxi.web.command;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import com.taxi.web.Path;
import com.taxi.web.model.dao.UserDAO;
import com.taxi.web.model.entity.User;
import com.taxi.web.model.entity.UserInfo;
import com.taxi.web.model.entity.User.UserBuilder;
import com.taxi.web.security.PasswordEncoder;

public class RegisterCommand extends Command {
	private static final long serialVersionUID = 1L;

	private PasswordEncoder encoder = PasswordEncoder.getInstance();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession session = req.getSession();

		Optional<Cookie> maybeCookie = Arrays.stream(req.getCookies())
				.filter(e -> e.getName().equals("lang"))
				.findFirst();
		if (maybeCookie.isEmpty()) {
			res.addCookie(new Cookie("lang", "ru"));
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", "ru");
		}else {
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", maybeCookie.get().getValue());
		}

		String forward = Path.PAGE_INDEX;

		byte[] salt = new byte[32];
		new SecureRandom().nextBytes(salt);
		User us = null;
		try {
			us = new UserBuilder().setLogin(req.getParameter("login"))
					.setPassword(encoder.encode(req.getParameter("password"), salt)).setSalt(salt).build();

		} catch (InvalidKeySpecException e1) {
			e1.printStackTrace();
		}

		UserInfo usInfo = new UserInfo();
		usInfo.setFirst(req.getParameter("first"));
		usInfo.setLast(req.getParameter("last"));

		try {
			new UserDAO().regNewUser(us, usInfo);
			res.setHeader("message_info", "successfully_registered");
			forward = Path.PAGE_LOGIN;
		} catch (SQLException e) {
			res.setHeader("message_info", "already_registered");
			forward = Path.PAGE_REGISTER;
		}

		return forward;
	}
}