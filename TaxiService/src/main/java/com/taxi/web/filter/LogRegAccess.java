package com.taxi.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogRegAccess implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)req).getSession();
		if(session.getAttribute("role") == null) {
			chain.doFilter(req, res);
		}else {
			((HttpServletResponse)res).sendRedirect("/");
		}
	}
}