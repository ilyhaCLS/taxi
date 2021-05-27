package com.taxi.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import com.taxi.web.Path;

public class LangCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String newLang = (String) req.getParameter("newLang");
		HttpSession session = req.getSession();

		switch (newLang) {
		case "ru":
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", newLang);
			res.addCookie(new Cookie("lang", "ru"));
			break;
		case "en":
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", newLang);
			res.addCookie(new Cookie("lang", "en"));
			break;
		default:
			break;
		}
		return "redirect:"+Path.PAGE_INDEX;
	}
}