package com.airlines.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.Service.PassengerService;
import com.airlines.entities.Passenger;

import sun.print.PrinterJobWrapper;


@WebServlet("/GetPassengers")
public class GetPassengers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PassengerService ps ;
    public GetPassengers() {
        super();
     }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Passenger> pList=(List<Passenger>)ps.getPassengers();
		request.setAttribute("Passengers_List", pList);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Views/PassengerList.jsp");
		view.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
