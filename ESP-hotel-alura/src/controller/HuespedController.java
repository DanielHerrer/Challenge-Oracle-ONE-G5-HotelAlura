package controller;
import java.sql.Connection;
import java.sql.Date;
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
	 
		public void guardar(Huesped huespedes) {
			this.huespedDAO.guardar(huespedes);
		}
		public List<Huesped> listarHuespedes() {
			return this.huespedDAO.listarHuespedes();
		}
		
		public List<Huesped> listarHuespedesId(String id) {
			return this.huespedDAO.buscarId(id);
		}
		
		public void actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer idReserva, Integer id) {
			this.huespedDAO.Actualizar(nombre, apellido, fechaN, nacionalidad, telefono, idReserva, id);
		}
		
		public void eliminar(Integer id) {
			this.huespedDAO.Eliminar(id);
		}
}
