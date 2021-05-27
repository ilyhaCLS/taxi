package com.taxi.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taxi.web.Path;
import com.taxi.web.model.dao.RideDAO;
import com.taxi.web.model.dao.UserDAO;
import com.taxi.web.model.entity.Ride;
import com.taxi.web.model.entity.UserInfo;

public class AccountCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();

		UserInfo usInfo = new UserDAO().findUserInfoByUserId((Integer) session.getAttribute("user_id"));
		List<Ride> rides = new RideDAO().findRidesByUserId((Integer) session.getAttribute("user_id"));

		req.setAttribute("first", usInfo.getFirst());
		req.setAttribute("last", usInfo.getLast());
		req.setAttribute("totalSpent", usInfo.getTotalSpent());
		req.setAttribute("rides", rides);

		return Path.PAGE_ACCOUNT;
	}
}