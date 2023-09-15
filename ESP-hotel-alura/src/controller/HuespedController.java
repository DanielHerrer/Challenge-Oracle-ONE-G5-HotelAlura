package controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;


public class HuespedController {
	
	private HuespedDAO huespedDAO;
	 
	public HuespedController() {
		Connection connection = new ConnectionFactory().recuperarConexion();
		this.huespedDAO = new HuespedDAO(connection);
	}
	 
	public void guardar(Huesped huespedes) throws SQLException, IllegalArgumentException {
		this.huespedDAO.guardar(huespedes);
	}
	
	public List<Huesped> buscar() {
		return this.huespedDAO.buscar();
	}
	
	public List<Huesped> buscarId(String id) {
		return this.huespedDAO.buscarId(id);
	}
	
	public List<Huesped> buscarApellido(String apellido) {
		return this.huespedDAO.buscarApellido(apellido);
	}
	
	public void actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer idReserva, Integer id) throws SQLException {
		this.huespedDAO.actualizar(nombre, apellido, fechaN, nacionalidad, telefono, idReserva, id);
	}
	
	public void eliminar(Integer id) throws SQLException {
		this.huespedDAO.eliminar(id);
	}
	
	public void eliminarPorReserva(Integer id) throws SQLException {
		this.huespedDAO.eliminarPorReserva(id);
	}
	
}