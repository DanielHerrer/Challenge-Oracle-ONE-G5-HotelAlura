package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;

public class HuespedDAO {
	
	private Connection connection;
	
	public HuespedDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardar(Huesped huesped) throws SQLException, IllegalArgumentException {
		
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_Reserva) VALUES (?, ?, ?, ?, ?, ?)";
			
			Date fechaActual = Date.valueOf(LocalDate.now());
	        
	        // Convierte las fechas a LocalDate
	        LocalDate localFechaNacimiento = huesped.getFechaNacimiento().toLocalDate();
	        LocalDate localFechaActual = fechaActual.toLocalDate();
	        // Calcula la diferencia en años entre las fechas
	        Period periodo = Period.between(localFechaNacimiento, localFechaActual);
			
	        // Si la fecha de nacimiento es posterior a la fecha actual
			if (huesped.getFechaNacimiento().after(fechaActual)) {
				throw new IllegalArgumentException("La fecha de nacimiento es incorrecta.");
				
			// Si el usuario indica una edad menor a 18 años
			} else if (periodo.getYears() < 18) {
				throw new IllegalArgumentException("El huesped que solicita la reserva debe ser mayor de 18 años.");	
			}
			
			// Si falla lanza un NumberFormatException
			double numeroTelefono = Double.valueOf(huesped.getTelefono());
			
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				pstm.setString(1, huesped.getNombre());
				pstm.setString(2, huesped.getApellido());
				pstm.setDate(3, huesped.getFechaNacimiento());
				pstm.setString(4, huesped.getNacionalidad());
				pstm.setString(5, huesped.getTelefono());
				pstm.setInt(6, huesped.getIdReserva());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						huesped.setId(rst.getInt(1));
					}
				}
			}
			
		} catch (NumberFormatException ne) {
            throw new NumberFormatException("El número telefónico no es correcto."); 
        } catch (IllegalArgumentException ie) {
			throw ie;
        } catch (SQLException e) {
			throw e;
		}
	}
	
	public List<Huesped> buscar() {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		try {
			String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_Reserva FROM huespedes";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> buscarId(String id) {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		try {

			String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_Reserva FROM huespedes WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> buscarApellido(String apellido) {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		try {

			String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_Reserva FROM huespedes WHERE apellido LIKE ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, "%"+apellido+"%");
				pstm.execute();

				transformarResultSetEnHuesped(huespedes, pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer idReserva, Integer id) throws SQLException {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, id_Reserva = ? WHERE id = ?")) {
			stm.setString(1, nombre);
			stm.setString(2, apellido);
			stm.setDate(3, fechaN);
			stm.setString(4, nacionalidad);
			stm.setString(5, telefono);
			stm.setInt(6, idReserva);
			stm.setInt(7, id);
			stm.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void eliminar(Integer id) throws SQLException {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM huespedes WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void eliminarPorReserva(Integer id) throws SQLException {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM huespedes WHERE id_Reserva = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	private void transformarResultSetEnHuesped(List<Huesped> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Huesped huespedes = new Huesped(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));
				reservas.add(huespedes);
			}
		}				
	}
	
}