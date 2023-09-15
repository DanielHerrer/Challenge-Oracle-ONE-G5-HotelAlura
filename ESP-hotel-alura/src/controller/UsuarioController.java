package controller;

import java.sql.Connection;
import java.util.List;

import dao.UsuarioDAO;
import factory.ConnectionFactory;
import modelo.Usuario;


public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;
	 
	public UsuarioController() {
		Connection connection = new ConnectionFactory().recuperarConexion();
		this.usuarioDAO = new UsuarioDAO(connection);
	}
 
	public void guardar(Usuario usuario) {
		this.usuarioDAO.guardar(usuario);
	}
	
	public List<Usuario> buscar() {
		return this.usuarioDAO.buscar();
	}
	
	public List<Usuario> buscarId(String id) {
		return this.usuarioDAO.buscarId(id);
	}
	
	public List<Usuario> buscarLogin(String usuario, String contrasenia) {
		return this.usuarioDAO.buscarLogin(usuario, contrasenia);
	}
	
	public void actualizar(String usuario, String contrasenia, Integer id) {
		this.usuarioDAO.Actualizar(usuario, contrasenia, id);
	}
	
	public void eliminar(Integer id) {
		this.usuarioDAO.Eliminar(id);
	}
	
}