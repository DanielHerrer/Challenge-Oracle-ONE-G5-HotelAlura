package modelo;

import java.sql.Date;

public class Reserva {
	
	private Integer id;
	private Date fechaE;
	private Date fechaS;
	private String tipoHabitacion;
	private String valor;
	private String formaPago;
	
	
	public Reserva(Date fechaE, Date fechaS, String tipoHabitacion, String valor, String formaPago) {		
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.tipoHabitacion = tipoHabitacion;
		this.valor = valor;
		this.formaPago = formaPago;
	}
		
	public Reserva(Integer id, Date fechaE, Date fechaS, String tipoHabitacion, String valor, String formaPago) {
		this.id = id;
		this.fechaE = fechaE;
		this.fechaS = fechaS;
		this.tipoHabitacion = tipoHabitacion;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getfechaE() {
		return fechaE;
	}

	public Date getfechaS() {
		return fechaS;
	}
	
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public String getvalor() {
		return valor;
	}

	public String getformaPago() {
		return formaPago;
	}
	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return String.format("La reserva generada fue: %d, %s, %s, %s, %s", this.id, this.fechaE, this.fechaS, this.valor, this.formaPago);
//	}
//	
	
}