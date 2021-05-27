package com.taxi.web.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taxi.web.Path;
import com.taxi.web.model.entity.Ride;
import com.taxi.web.model.service.RideService;

public class ShowRidesCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		RideService rideService = new RideService();

		List<Ride> rides = null;

		if (req.getParameter("q").equals("all")) {
			switch (String.valueOf(req.getParameter("order"))) {
			case "desc":
				System.out.println("desc");
				rides = rideService.getSortedRides("creation_time", "DESC");
				break;
			case "asc":
				System.out.println("asc");
				rides = rideService.getSortedRides("creation_time", "ASC");
				break;
			case "exp":
				System.out.println("exp");
				rides = rideService.getSortedRides("price", "DESC");
				break;
			case "cheap":
				System.out.println("cheap");
				rides = rideService.getSortedRides("price", "ASC");
				break;
			case "date":
				rides = rideService.getRidesByPeriod(LocalDateTime.parse(req.getParameter("from")),
						LocalDateTime.parse(req.getParameter("until")));
				break;
			default:
				return Path.PAGE_ERROR;
			}
		}

		if (req.getParameter("q").equals("us")) {
			rides = rideService.getRides(Integer.parseInt(req.getParameter("user_id")));
		}

		req.setAttribute("rides", rides);

		return Path.PAGE_ORDERS;
	}
}