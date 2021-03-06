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

public class GeneralPagesAccess implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)req).getSession();
		
		if(session.getAttribute("role") == null) {
			chain.doFilter(req, res);
		}else if(session.getAttribute("role").toString().equals("ADMIN")){
			System.out.println("here");
			((HttpServletResponse)res).sendRedirect("/controller?command=adminPage");
		}else {
			((HttpServletResponse)res).sendRedirect("/");
		}
	}
}