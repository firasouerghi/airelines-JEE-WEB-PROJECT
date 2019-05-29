package com.airlines.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQuery(name="Flight.findById",query="SELECT f FROM Flight f WHERE f.id=:id")
@XmlRootElement
public class Flight implements Serializable {

	@Transient // we dont need it in our DB
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private FlightDestinations origine;

	@Enumerated(EnumType.STRING)
	private FlightDestinations destination;

	private Integer Price;

	@Temporal(TemporalType.TIMESTAMP)
	private Date flightTime;
	
	/*
	 * 
	 * Hibernate underneath JPA cannot use java.util.List or
	 *  java.util.Collection for fetching eagerly associations
	 *   with more than two levels of nesting for collections while 
	 *   using generated fetch graph to optimize query performance.
	 * 
	 * problem  :cannot simultaneously fetch multiple bags jpa wildfly
	 * 
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="F_P_JOIN",joinColumns=@JoinColumn(name="FLIGHT_FK"),inverseJoinColumns=@JoinColumn(name="PASSENGER_FK"))
	private Set<Passenger> FlightPassengerList ;
	

	public Flight(Integer id, FlightDestinations origine, FlightDestinations destination, Integer price,
			Date flightTime) {
		super();
		this.id = id;
		this.origine = origine;
		this.destination = destination;
		Price = price;
		this.flightTime = flightTime;
	}

	public Set<Passenger> getFlightPassengerList() {
		return FlightPassengerList;
	}

	public void setFlightPassengerList(Set<Passenger> flightPassengerList) {
		FlightPassengerList = flightPassengerList;
	}

	/*
	 * propagaded and cascaded from flight automatically
	 * 
	 * nous permet de persister l'avion (Plane) automatiquement lorsque on persiste le vole (Flight) 
	 */
	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="Airplaine_fk") //add the a column to the flight table containing the airplaine id of this flight  
	private Airplane FlightAirplane ;
	/*
	 * 
	 *
	 * 
	 * 
	 * L’attribut fetch d’une association permet d’indiquer une
	récupération immédiate des entités associées (FetchType.EAGER)
	*
	*
	*
	Le mode de récupération par défaut est le mode EAGER pour les
	attributs (ils sont chargés en même temps que l’entité)
	*
	*
	*
	*
	*
	*/
		
	@OneToMany(mappedBy="Pilotsflight",fetch=FetchType.EAGER) 
	private List<Pilot> pilots ;
	
	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}

	public Airplane getFlightAirplane() {
		return FlightAirplane;
	}

	public void setFlightAirplane(Airplane flightAirplane) {
		FlightAirplane = flightAirplane;
	}

	public Flight() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public FlightDestinations getOrigine() {
		return origine;
	}

	public void setOrigine(FlightDestinations origine) {
		this.origine = origine;
	}

	public FlightDestinations getDestination() {
		return destination;
	}

	public void setDestination(FlightDestinations destination) {
		this.destination = destination;
	}

	public Integer getPrice() {
		return Price;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", origine=" + origine + ", destination=" + destination + ", Price=" + Price
				+ ", flightTime=" + flightTime + ", FlightPassengerList=" + FlightPassengerList + ", FlightAirplane="
				+ FlightAirplane + ", pilots=" + pilots + ", getFlightPassengerList()=" + getFlightPassengerList()
				+ ", getPilots()=" + getPilots() + ", getFlightAirplane()=" + getFlightAirplane() + ", getId()="
				+ getId() + ", getOrigine()=" + getOrigine() + ", getDestination()=" + getDestination()
				+ ", getPrice()=" + getPrice() + ", getFlightTime()=" + getFlightTime() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public void setPrice(Integer price) {
		Price = price;
	}

	public Date getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
