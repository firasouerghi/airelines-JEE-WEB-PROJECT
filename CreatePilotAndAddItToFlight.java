package com.airlines.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.Service.PilotService;
import com.airlines.entities.Pilot;
import com.airlines.entities.PilotRank;


@WebServlet("/CreatePilotAndAddItToFlight")
public class CreatePilotAndAddItToFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    PilotService ps ;
    
    public CreatePilotAndAddItToFlight() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 
		 * Getting Pilot Details From The Forms 
		 * 
		 * */
		String fn= request.getParameter("first_name");

		String ln= request.getParameter("last_name");

		Integer license= Integer.parseInt(request.getParameter("license"));

		String Pr= request.getParameter("pilot_rank");

		String fid= request.getParameter("fid");
		
		/*
		 * 
		 * Creating & Adding  Pilot Details to the Pilot Object 
		 * 
		 * */
		
		Pilot p=new Pilot();
		p.setFirstname(fn);
		p.setLastname(ln);
		p.setPiloteLicense(license);
		p.setRank(PilotRank.valueOf(Pr));
		
		
		/*
		 * 
		 * Persisting The pilot to database and adding the one to many relation 
		 * 
		 * */
		
		ps.AddNewPilotToFlight(p, fid);
		
		
		/*
		 * 
		 * redirect the form back to the Flight Servlet
		 * 
		 * */
		
		response.sendRedirect("GetFlights");
		
		//doGet(request, response);
	}

}
