package com.airlines.Service;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airlines.entities.Airplane;
import com.airlines.entities.Flight;
import com.airlines.entities.Passenger;
import com.airlines.entities.Pilot;

@Stateless
@LocalBean
public class FlightService {

	public FlightService() {
	
	}

	@PersistenceContext(unitName = "Airlines")
	EntityManager em;

	public void addFlight(Flight f) {

		em.persist(f);
		//em.persist(p);<---grace a la proprité cascade l'avion est pérsisté automatiquement apres la persistance du vole 

	}

	public void AddPilotToFlight(String PilotId, String FlightId) {

		TypedQuery<Flight> fquery = em.createNamedQuery("Flight.findById", Flight.class);
		fquery.setParameter("id", Integer.parseInt(FlightId));
		Flight f = fquery.getSingleResult();

		TypedQuery<Pilot> pquery = em.createNamedQuery("Pilot.findById", Pilot.class);
		pquery.setParameter("id", Integer.parseInt(PilotId));
		Pilot p = pquery.getSingleResult();

		List<Pilot> pList = f.getPilots();

		pList.add(p);

		f.setPilots(pList);

		p.setPilotsflight(f);
	}

	public List<Flight> getFlights() {

		TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight  f", Flight.class);
		List<Flight> fls = query.getResultList();
		return fls;
	}
	

	public void AddPassengerToFlight(String pid ,String fid) {
		
		TypedQuery<Flight> fquery = em.createNamedQuery("Flight.findById", Flight.class);
		fquery.setParameter("id", Integer.parseInt(fid));
		Flight f = fquery.getSingleResult();
    	
		TypedQuery<Passenger> pquery = em.createNamedQuery("Passenger.findById",Passenger.class);
		pquery.setParameter("id", Integer.parseInt(pid));
		Passenger p = pquery.getSingleResult();
    	
		//add the passenger to the flight's Passenger List
		
		Set<Passenger> PList= f.getFlightPassengerList() ;
		
		PList.add(p);
		
		f.setFlightPassengerList(PList);
		
		//add  the flight for the passenger  Flight's List 
		p.getPassengerFlightList().add(f) ;
	}
	
	
	public Flight GetFlight(Integer FlightId) {

		TypedQuery<Flight> fquery = em.createNamedQuery("Flight.findById", Flight.class);
		fquery.setParameter("id",FlightId);
		
		Flight f=null ;
		try {
			f = fquery.getSingleResult();
		} catch (Exception e) {
			return null ;
		}
		
		
	
		return f;
	}

}
