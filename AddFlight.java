package com.airlines.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.Service.FlightService;
import com.airlines.entities.Airplane;
import com.airlines.entities.AirplaneModel;
import com.airlines.entities.Flight;
import com.airlines.entities.FlightDestinations;

@WebServlet("/AddFlight")

public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	FlightService fs ;
	
	public AddFlight() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		/*
		 * 
		 *Getting Flight details from the forms
		 *
		 * */
		String Origine=request.getParameter("from_destination") ;
		String destination=request.getParameter("to_destination") ;
		String Price=request.getParameter("price") ;
		Integer year=Integer.parseInt( request.getParameter("year") ) ;
		Integer month=Integer.parseInt(request.getParameter("month")) ;
		Integer day=Integer.parseInt(request.getParameter("day")) ;
		Integer hour=Integer.parseInt(request.getParameter("hour")) ;
		Integer minute=Integer.parseInt(request.getParameter("minute")) ;
		/*
		 * Airplane details
		 * */
		
		String model=request.getParameter("airplane_model") ;
		Integer capacity =Integer.parseInt(request.getParameter("airplane_seating")) ;
		

		/*
		 * 
		 *Creating & Adding Airplane details to the plane  Object 
		 * 
		 * */
		Airplane plane=new Airplane() ;
		

		plane.setPlaneModel(AirplaneModel.valueOf(model));
		plane.setSeatingCapacity(capacity);
	
	
		
		/*
		 * 
		 * Creating & Adding Flight details to the Flight Object 
		 * 
		 * */
		Flight f=new Flight();
		
		f.setOrigine(FlightDestinations.valueOf(Origine));	
		f.setDestination(FlightDestinations.valueOf(destination));
		f.setPrice(Integer.parseInt(Price));
		Calendar cal=Calendar.getInstance() ;
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);		
		cal.set(Calendar.MINUTE, minute);		
		Date dof=cal.getTime() ;
		f.setFlightTime(dof);
		f.setFlightAirplane(plane);
		
		/*
		 * 
		 * Persisting the Flight and the plane one to one relation cascading type
		 * 
		 * 
		 * */
		fs.addFlight(f);
		
		/*
		 * 
		 * redirecting to the GetFlights Servlet to see the added flight 
		 * 
		 * 
		 * */

		response.sendRedirect("GetFlights");
	}

}
