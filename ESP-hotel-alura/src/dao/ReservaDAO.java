package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardar(Reserva reserva) throws IllegalArgumentException, SQLException {
		try {
			// Si la fecha de salida es anterior a la fecha de entrada.
			if (reserva.getfechaS().before(reserva.getfechaE())) {
				throw new IllegalArgumentException("La fecha de salida es anterior a la fecha de entrada.");
			}
			
			String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, tipo_habitacion, valor, forma_Pago) VALUES (?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setDate(1, reserva.getfechaE());
				pstm.setDate(2, reserva.getfechaS());
				pstm.setString(3, reserva.getTipoHabitacion());
				pstm.setString(4, reserva.getvalor());
				pstm.setString(5, reserva.getformaPago());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public List<Reserva> buscar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, tipo_habitacion, valor, forma_pago FROM reservas";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarId(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {

			String sql = "SELECT id, fecha_entrada, fecha_salida, tipo_habitacion, valor, forma_Pago FROM reservas WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarApellido(String apellido) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {

			String sql = "SELECT r.id, r.fecha_entrada, r.fecha_salida, r.tipo_habitacion, r.valor, r.forma_Pago FROM reservas r INNER JOIN huespedes h ON r.id = h.id_reserva WHERE h.apellido LIKE ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, "%" + apellido + "%");
				pstm.execute();

				transformarResultSetEnReserva(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminar(Integer id) throws SQLException {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservas WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	// No se puede eliminar una reserva debido a que un registro huesped contiene un foreign key de id Reserva
	/*
	public void eliminarPorHuesped(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservas WHERE id IN (SELECT id_Reserva FROM huespedes WHERE id_Reserva = ?)")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/
	
	public void actualizar(Date fechaE, Date fechaS, String tipoHabitacion, String valor, String formaPago, Integer id) throws SQLException {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, tipo_habitacion = ?, valor = ?, forma_Pago = ? WHERE id = ?")) {
			stm.setDate(1, fechaE);
			stm.setDate(2, fechaS);
			stm.setString(3, tipoHabitacion);
			stm.setString(4, valor);
			stm.setString(5, formaPago);
			stm.setInt(6, id);
			stm.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
						
	private void transformarResultSetEnReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva produto = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5), rst.getString(6));

				reservas.add(produto);
			}
		}
	}

}