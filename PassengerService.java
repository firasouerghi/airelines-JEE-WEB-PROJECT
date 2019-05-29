package com.airlines.Service;

import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airlines.entities.Flight;
import com.airlines.entities.Passenger;


@Stateless
@LocalBean
public class PassengerService {

    /**
     * Default constructor. 
     */
    public PassengerService() {
    }

    @PersistenceContext(unitName="Airlines")
    private EntityManager em ;
    
    
    public Passenger AddPassenger(Passenger p) {
    	em.persist(p);
    	return p;
    }
    public List<Passenger> getPassengers(){
    	TypedQuery<Passenger> pquery=em.createQuery("SELECT p FROM Passenger p", Passenger.class);
    	List<Passenger>psList= pquery.getResultList();
    	return psList ;
    }
    public void AddFlightTicketToPassenger(String pid ,String fid) {
    	
    	TypedQuery<Flight> fquery = em.createNamedQuery("Flight.findById", Flight.class);
		fquery.setParameter("id", Integer.parseInt(fid));
		Flight f = fquery.getSingleResult();
    	
		TypedQuery<Passenger> pquery = em.createNamedQuery("Passenger.findById",Passenger.class);
		pquery.setParameter("id", Integer.parseInt(pid));
		Passenger p = pquery.getSingleResult();
		
		Set<Flight> flist= p.getPassengerFlightList() ;
		flist.add(f);
		p.setPassengerFlightList(flist) ;
		
		f.getFlightPassengerList().add(p) ;
		
		
		
    }


    public Passenger GetPassenger(Integer PassengerId) {

		TypedQuery<Passenger> pquery = em.createNamedQuery("Passenger.findById", Passenger.class);
		pquery.setParameter("id",PassengerId);
		Passenger p=null ;
		try {
			p= pquery.getSingleResult();
		} catch (Exception e) {
			return null ;
		}
		
		return p;
	}

}
