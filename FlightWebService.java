package com.airlines.webservice.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


import com.airlines.Service.FlightService;
import com.airlines.entities.Flight;

@Path("/Flights")
public class FlightWebService {

	
	@PersistenceContext(unitName="Airlines")
	EntityManager em ;
	@EJB
	FlightService fs ;
	@Context
	UriInfo furinfo ;
	
	
	
	public FlightWebService() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)////*******************************************/
	public List<Flight> getFlights() {
		
		List<Flight> fList =fs.getFlights() ;
		
		return fList ;
		}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idf}")
	public Flight getFlight(@javax.ws.rs.PathParam("idf") Integer idf) {
		
		Flight f =fs.GetFlight(idf);
		if (f==null) {
			throw new NotFoundException("Passenger not found" ) ;
			
		}
		return f ;
		
	}
	
}
