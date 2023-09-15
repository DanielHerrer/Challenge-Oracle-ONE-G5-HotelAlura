package modelo;

public class Usuario {

	private Integer id;
	private String usuario;
	private String contrasenia;
	
	public Usuario(String usuario, String contrasenia) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Usuario(Integer id, String usuario, String contrasenia) {
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
}