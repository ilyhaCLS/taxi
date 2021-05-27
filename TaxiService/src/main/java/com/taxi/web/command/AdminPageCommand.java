package com.taxi.web.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taxi.web.Path;
import com.taxi.web.model.service.RideService;

public class AdminPageCommand extends Command {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
		RideService rideService = new RideService();
		
		LocalDateTime start = LocalDateTime.now().minusDays(1);
		LocalDateTime finish = LocalDateTime.now();
		
		System.out.println("from: "+start);
		System.out.println("to: "+finish);
		
		List<Integer> dayInfo =  rideService.getDayInfo(start, finish);
		int sum = dayInfo.stream().collect(Collectors.summingInt(Integer::intValue));
		System.out.println(sum);
		
		req.setAttribute("ridesInADay", dayInfo.size());
		
		req.setAttribute("sumInADay", sum);
		
		return Path.PAGE_ADMIN;
	}
}