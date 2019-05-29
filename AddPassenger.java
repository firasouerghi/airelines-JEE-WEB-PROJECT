package com.airlines.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.Service.PassengerService;
import com.airlines.entities.FlightClass;
import com.airlines.entities.Gender;
import com.airlines.entities.Passenger;


@WebServlet("/AddPassenger")
public class AddPassenger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	@EJB
	PassengerService ps ;
    public AddPassenger() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*
		 * j'ai juste ajouter le driver de mysql buildpath 
		 * et dans facet jpa choisir la case générique  et la case runtime environnement
		 * */
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		/*
		 * 
		 * Getting the Passenger details from the form
		 * 
		 * */
		
		String fn=request.getParameter("first_name") ;
		String ln=request.getParameter("last_name") ;
		String gender=request.getParameter("gender") ;
		
		Integer Day =Integer.parseInt(request.getParameter("day")) ;
		Integer Month =Integer.parseInt(request.getParameter("month")) ;
		Integer Year =Integer.parseInt(request.getParameter("year")) ;
		Calendar cal=Calendar.getInstance();						
		cal.set(Calendar.MONTH,Month);
	    cal.set(Calendar.DAY_OF_MONTH,Day);
		cal.set(Calendar.YEAR,Year);
				
		Date db = cal.getTime();
				
		
		/*
		 * 
		 * Create and add passenger details to the passenger object  
		 * 
		 */
		
		Passenger p =new Passenger() ;
			
		p.setFirstname(fn);
		p.setLastname(ln);
		p.setGender(Gender.valueOf(gender));
		p.setFc(FlightClass.FirstClass);
		p.setDob(db);
		/*
		 * 
		 * Persisting the Passenger in the database 
		 * 
		 * */
		
		ps.AddPassenger(p);
		
		/*
		 * 
		 * redirecting the browser to the GetPassengers Servlet 
		 *
		 */
		
		
		response.sendRedirect("GetPassengers");
		
	
	}

}
