package com.taxi.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taxi.web.Path;
import com.taxi.web.model.entity.Car;
import com.taxi.web.model.entity.CarClass;
import com.taxi.web.model.entity.Ride;
import com.taxi.web.model.service.CarService;
import com.taxi.web.model.service.RideService;

public class RideConfirmedCommand extends Command {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Override
	public synchronized String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		CarService carService = new CarService();
		RideService rideService = new RideService();
		
		List<Car> resCars = new ArrayList<Car>(); 
		
		Ride r = (Ride)session.getAttribute("ride");
		if(r == null) {
			System.out.println("ride is null");
			return Path.PAGE_ERROR;
		}
		
		
		switch(req.getParameter("opt")) {
		case "1":
			HashMap<String, String> option1 = (HashMap<String, String>) session.getAttribute("option1");
			
			for(int i = 0; i < Integer.parseInt(option1.get("numOfCars")); i++) {
				r.setPrice(Math.round(Float.parseFloat(((String) session.getAttribute("dist")).substring(0, ((String) session.getAttribute("dist")).indexOf(" ")))
						* (CarClass.valueOf((String) option1.get("carClass")).getPricePerKm()) - (int)session.getAttribute("disc")));
				
				Car c = carService.getActiveCar((String)option1.get("carClass"));
				
				r.setCarId(c.getId());
				rideService.addRide(r);
				resCars.add(c);
			}
			break;
		case "2":
			HashMap<String, String> option2 = (HashMap<String, String>) session.getAttribute("option2");
			
			for(int i = 0; i < Integer.parseInt(option2.get("numOfCars")); i++) {
				r.setPrice(Math.round(Float.parseFloat(((String) session.getAttribute("dist")).substring(0, ((String) session.getAttribute("dist")).indexOf(" ")))
						* (CarClass.valueOf((String) option2.get("carClass")).getPricePerKm())- (int)session.getAttribute("disc")));
				
				Car c = carService.getActiveCar((String)option2.get("carClass"));
				
				r.setCarId(c.getId());
				rideService.addRide(r);
				resCars.add(c);
			}
			break;
		default:
			System.out.println("inside default case");
			
			Car c = carService.getActiveCar((String)session.getAttribute("carClass"));
			
			r.setCarId(c.getId());
			rideService.addRide(r);
			resCars.add(c);

			break;
		}
			
		
		req.setAttribute("cars", resCars);
		
		session.removeAttribute("option1");
		session.removeAttribute("option2");
		session.removeAttribute("dist");
		session.removeAttribute("time");
		session.removeAttribute("ride");
		session.removeAttribute("carClass");
		
		return Path.PAGE_RIDE_CONFIRMED;
	}
}