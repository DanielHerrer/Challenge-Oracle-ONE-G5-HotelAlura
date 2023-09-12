package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class UsuarioDAO {
	
	public static String usuarioConectado;
	private Connection connection;
	
	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardar(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios(usuario, contrasenia) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, usuario.getUsuario());
				pstm.setString(2, usuario.getContrasenia());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						usuario.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Usuario> buscar() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			String sql = "SELECT id, usuario, contrasenia FROM usuarios";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnUsuario(usuarios, pstm);
			}
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Usuario> buscarId(String id) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {

			String sql = "SELECT id, nombre, contrasenia FROM usuarios WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEnUsuario(usuarios, pstm);
			}
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Usuario> buscarLogin(String user, String pass) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {

			String sql = "SELECT id, usuario, contrasenia FROM usuarios WHERE usuario = ? AND contrasenia = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, user);
				pstm.setString(2, pass);
				pstm.execute();

				transformarResultSetEnUsuario(usuarios, pstm);
			}
			
			if(!usuarios.isEmpty()) {
				usuarioConectado = user;
			}
			
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Eliminar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM usuarios WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Actualizar(String usuario, String contrasenia, Integer id) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE usuarios SET usuario = ?, contrasenia = ? WHERE id = ?")) {
			stm.setString(1, usuario);
			stm.setString(2, contrasenia);
			stm.setInt(3, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
						
	private void transformarResultSetEnUsuario(List<Usuario> usuarios, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Usuario usuario = new Usuario(rst.getInt(1), rst.getString(2), rst.getString(3));
				usuarios.add(usuario);
			}
		}
	}
}

