package com.taxi.web.listener;

import java.util.HashSet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	@Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        
        Integer userId = (Integer)httpSessionEvent.getSession().getAttribute("user_id");
        loggedUsers.remove(userId);
        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        
        System.out.println("session destroyed for user id: "+ userId);
    }
}