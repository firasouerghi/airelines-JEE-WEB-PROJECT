package com.airlines.entities;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;


@Entity
public class Airplane implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Airplane(Integer id, AirplaneModel planeModel, Integer seatingCapacity) {
		super();
		this.id = id;
		this.planeModel = planeModel;
		this.seatingCapacity = seatingCapacity;
	}

	@Enumerated(EnumType.STRING)
	private AirplaneModel planeModel;

	private Integer seatingCapacity;

	@OneToOne(mappedBy="FlightAirplane")
	private Flight flight ;


	@Override
	public String toString() {
		return "Airplane [id=" + id + ", planeModel=" + planeModel + ", seatingCapacity=" + seatingCapacity
				+ ", flight=" + flight + ", getPlaneModel()=" + getPlaneModel() + ", getFlight()=" + getFlight()
				+ ", getId()=" + getId() + ", getSeatingCapacity()=" + getSeatingCapacity() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public AirplaneModel getPlaneModel() {
		return planeModel;
	}

	public void setPlaneModel(AirplaneModel planeModel) {
		this.planeModel = planeModel;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	
	public Airplane() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

}
