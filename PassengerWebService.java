


package com.airlines.webservice.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.airlines.Service.PassengerService;
import com.airlines.entities.Passenger;

@Path("/Passengers")
public class PassengerWebService {

	
	@PersistenceContext(unitName="Airlines")
	EntityManager em ;
	@EJB
	PassengerService ps ;
	@Context
	UriInfo purinfo ;
	
	
	
	public PassengerWebService() {
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Passenger> getPassengers() {
		
		List<Passenger> pList =ps.getPassengers();
		
		return pList ;
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idp}")
	public Passenger getFlight(@javax.ws.rs.PathParam("idp") Integer idp) {
		
		Passenger p =ps.GetPassenger(idp);
		if (p==null) {
			throw new NotFoundException("Flight not found" ) ;
			
		}
		return p ;
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPassenger(Passenger p) {
		p=ps.AddPassenger(p);
		
		UriBuilder pUriBuilder=purinfo.getAbsolutePathBuilder();
		URI pUri=pUriBuilder.path(String.valueOf(p.getId())).build();
				
				return Response.created(pUri).build() ;
	}
		
	
	
}

