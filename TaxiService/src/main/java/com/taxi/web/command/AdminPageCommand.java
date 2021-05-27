package com.taxi.web.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taxi.web.Path;
import com.taxi.web.model.dao.RideDAO;

public class AdminPageCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
		RideDAO rideDAO = new RideDAO();
		
		LocalDateTime start = LocalDateTime.now().minusDays(1);
		LocalDateTime finish = LocalDateTime.now();
		
		System.out.println("from: "+start);
		System.out.println("to: "+finish);
		
		ArrayList<Integer> dayInfo =  rideDAO.infoForADay(start, finish);
		int sum = dayInfo.stream().collect(Collectors.summingInt(Integer::intValue));
		System.out.println(sum);
		
		req.setAttribute("ridesInADay", dayInfo.size());
		
		req.setAttribute("sumInADay", sum);
		
		return Path.PAGE_ADMIN;
	}
}