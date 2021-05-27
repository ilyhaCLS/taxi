package com.taxi.web.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taxi.web.Path;
import com.taxi.web.model.dao.RideDAO;
import com.taxi.web.model.entity.Ride;

public class ShowRidesCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		RideDAO rideDAO = new RideDAO();

		ArrayList<Ride> rides = null;

		if (req.getParameter("q").equals("all")) {
			switch (String.valueOf(req.getParameter("order"))) {
			case "desc":
				System.out.println("desc");
				rides = rideDAO.findAllRides("creation_time", "DESC");
				break;
			case "asc":
				System.out.println("asc");
				rides = rideDAO.findAllRides("creation_time", "ASC");
				break;
			case "exp":
				System.out.println("exp");
				rides = rideDAO.findAllRides("price", "DESC");
				break;
			case "cheap":
				System.out.println("cheap");
				rides = rideDAO.findAllRides("price", "ASC");
				break;
			case "date":
				rides = rideDAO.findAllRidesInPeriod(LocalDateTime.parse(req.getParameter("from")),
						LocalDateTime.parse(req.getParameter("until")));
				break;
			default:
				return Path.PAGE_ERROR;
			}
		}

		if (req.getParameter("q").equals("us")) {
			rides = rideDAO.findRidesByUserId(Integer.parseInt(req.getParameter("user_id")));
		}

		req.setAttribute("rides", rides);

		return Path.PAGE_ORDERS;
	}
}