package com.airlines.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.Service.FlightService;
import com.airlines.entities.Flight;
import com.airlines.entities.Pilot;

@WebServlet("/GetFlights")
public class GetFlights extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetFlights() {
		super();
	}

	@EJB
	FlightService fs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Flight> fList = (List<Flight>) fs.getFlights();
		request.setAttribute("Flight_List", fList);
		
		// forwarding the request and the response to our JSP
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Views/FlightList.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
