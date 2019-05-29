package com.airlines.entities;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@NamedQuery(name="Pilot.findById",query="SELECT p FROM Pilot p WHERE p.id=:id")
public class Pilot implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id ;
	
	private String Firstname ;
	private String Lastname ;
	
	private Integer piloteLicense ;
	
	@Enumerated(EnumType.STRING)
	private PilotRank Rank ;
	
	
	@ManyToOne //onetomany relation pilot est l'entite maitre n pilote pour un seul vole flight entite esclave 
	@JoinColumn(name="flight_fk")
	private Flight Pilotsflight;

	public Flight getPilotsflight() {
		return Pilotsflight;
	}

	public void setPilotsflight(Flight pilotsflight) {
		Pilotsflight = pilotsflight;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", Firstname=" + Firstname + ", Lastname=" + Lastname + ", piloteLicense="
				+ piloteLicense + ", Rank=" + Rank + ", Pilotsflight=" + Pilotsflight + ", getPilotsflight()="
				+ getPilotsflight() + ", getId()=" + getId() + ", getFirstname()=" + getFirstname() + ", getLastname()="
				+ getLastname() + ", getPiloteLicense()=" + getPiloteLicense() + ", getRank()=" + getRank()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public Integer getPiloteLicense() {
		return piloteLicense;
	}

	public void setPiloteLicense(Integer piloteLicense) {
		this.piloteLicense = piloteLicense;
	}

	public PilotRank getRank() {
		return Rank;
	}

	public void setRank(PilotRank rank) {
		Rank = rank;
	}


	public Pilot(Integer id, String firstname, String lastname, Integer piloteLicense, PilotRank rank) {
		super();
		this.id = id;
		Firstname = firstname;
		Lastname = lastname;
		this.piloteLicense = piloteLicense;
		Rank = rank;
	}

	public Pilot() {
		super();
	}
   
}
