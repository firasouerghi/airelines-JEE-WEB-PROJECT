package com.airlines.Service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airlines.entities.Flight;
import com.airlines.entities.Pilot;

@Stateless
@LocalBean
public class PilotService {

    @PersistenceContext(unitName="Airlines")
    EntityManager em ;
    public PilotService() {
        // TODO Auto-generated constructor stub
    }

    public void addPilot(Pilot p) {
    	em.persist(p);
    }
    public void AddNewPilotToFlight(Pilot p, String FlightId) {

		addPilot(p); 
    	TypedQuery<Flight> fquery = em.createNamedQuery("Flight.findById", Flight.class);
		fquery.setParameter("id", Integer.parseInt(FlightId));
		Flight f = fquery.getSingleResult();

		List<Pilot> pList = f.getPilots();

		pList.add(p);

		f.setPilots(pList);

		p.setPilotsflight(f);
	}

    }

