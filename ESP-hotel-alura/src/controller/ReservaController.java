package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
 private ReservaDAO reservaDAO;
 
 public ReservaController() {
		Connection connection = new ConnectionFactory().recuperarConexion();
		this.reservaDAO = new ReservaDAO(connection);
	}
 
	public void guardar(Reserva reserva) throws IllegalArgumentException, SQLException {
		this.reservaDAO.guardar(reserva);
	}
		
	public List<Reserva> buscar() {
		return this.reservaDAO.buscar();
	}
	
	public List<Reserva> buscarId(String id) {
		return this.reservaDAO.buscarId(id);
	}
	
	public void actualizar(Date fechaE, Date fechaS, String habitacion, String valor, String formaPago, Integer id) throws SQLException {
		this.reservaDAO.actualizar(fechaE, fechaS, habitacion, valor, formaPago, id);
	}
	
	public void eliminar(Integer id) throws SQLException {
		this.reservaDAO.eliminar(id);
	}
	
	/*
	public void eliminarPorHuesped(Integer id) {
		this.reservaDAO.eliminarPorHuesped(id);
	}*/
}
