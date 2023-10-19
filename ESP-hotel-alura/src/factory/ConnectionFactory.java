package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	// Configurar aqui con los datos de la base de datos
	private String jdbcUrl = "jdbc:mysql://localhost:3306/hotel_alura?serverTimezone=UTC";  
	private String user = "root"; 	
	private String password = "root"; 	

	public DataSource dataSource;
	
	public ConnectionFactory() {		
		/**
		 * Se usa ComboPooled (c3po) en vez de DriverManager, 
		 *  debido a una mayor escalabilidad de nuestra aplicacion, 
		 *   posibilidad de multiples conexiones y tiempo de espera entre ellas
		 */
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(jdbcUrl);
		comboPooledDataSource.setUser(user); 
		comboPooledDataSource.setPassword(password); 

		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexion() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}