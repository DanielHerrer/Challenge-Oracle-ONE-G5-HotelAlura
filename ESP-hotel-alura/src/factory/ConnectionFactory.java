package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;

	public ConnectionFactory() {
		/**
		 * Se usa ComboPooled (c3po) en vez de DriverManager, 
		 * debido a una mayor escalabilidad de nuestra aplicacion, 
		 * posibilidad de multiples conexiones y tiempo de espera entre ellas
		 */
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_alura?serverTimezone=UTC"); // Configurar aqui tu localhost
		comboPooledDataSource.setUser("root"); // Configurar aqui tu usuario 
		comboPooledDataSource.setPassword("root"); // Configurar aqui tu contraseña

		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexion() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
