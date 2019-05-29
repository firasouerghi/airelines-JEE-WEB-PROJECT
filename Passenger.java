package com.airlines.entities;

import java.io.Serializable;
import java.util.Date;
 import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@NamedQuery(name="Passenger.findById",query="SELECT p FROM Passenger p WHERE p.id=:id")
@XmlRootElement // convert passenger entity to xml format to send it to the rest client via the rest web service 
public class Passenger implements Serializable {

	@Transient //we dont need it in our DB
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Passenger(Integer id, String firstname, String lastname, Date dob, Gender gender, FlightClass fc) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.gender = gender;
		this.fc = fc;
	}

	private String firstname;

	private String lastname;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private FlightClass fc;
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
	@ManyToMany(mappedBy="FlightPassengerList",fetch=FetchType.EAGER)   
	private Set<Flight> passengerFlightList ;

	
	
	public Set<Flight> getPassengerFlightList() {
		return passengerFlightList;
	}
	public void setPassengerFlightList(Set<Flight> passengerFlightList) {
		this.passengerFlightList = passengerFlightList;
	}
	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob
				+ ", gender=" + gender + ", fc=" + fc + ", passengerFlightList=" + passengerFlightList
				+ ", getPassengerFlightList()=" + getPassengerFlightList() + ", getId()=" + getId()
				+ ", getFirstname()=" + getFirstname() + ", getLastname()=" + getLastname() + ", getDob()=" + getDob()
				+ ", getGender()=" + getGender() + ", getFc()=" + getFc() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public Passenger() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public FlightClass getFc() {
		return fc;
	}

	public void setFc(FlightClass fc) {
		this.fc = fc;
	}

}
