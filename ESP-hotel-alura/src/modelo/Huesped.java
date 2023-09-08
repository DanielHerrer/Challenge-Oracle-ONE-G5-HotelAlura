package modelo;

import java.sql.Date;

public class Huesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer idReserva;
	
	public Huesped(String nombreX, String apellidoX,  Date fechaNacimientoX, String nacionalidadX, String telefonoX,
			Integer idReservaX) {
		super();
		nombre = nombreX;
		apellido = apellidoX;
		fechaNacimiento = fechaNacimientoX;
		nacionalidad = nacionalidadX;
		telefono = telefonoX;
		idReserva = idReservaX;
	}
	
	public Huesped(Integer idX, String nombreX, String apellidoX, Date fechaNacimientoX, String nacionalidadX,
			String telefonoX, Integer idReservaX) {
		super();
		id = idX;
		nombre = nombreX;
		apellido = apellidoX;
		fechaNacimiento = fechaNacimientoX;
		nacionalidad = nacionalidadX;
		telefono = telefonoX;
		idReserva = idReservaX;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer idX) {
		id = idX;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreX) {
		nombre = nombreX;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellidoX) {
		apellido = apellidoX;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidadX) {
		nacionalidad = nacionalidadX;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimientoX) {
		fechaNacimiento = fechaNacimientoX;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefonoX) {
		telefono = telefonoX;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReservaX) {
		idReserva = idReservaX;
	}
	
}
