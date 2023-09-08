package modelo;

public class Usuario {

	private Integer id;
	private String usuario;
	private String contrasenia;
	
	public Usuario(String usuarioX, String contraseniaX) {
		usuario = usuarioX;
		contrasenia = contraseniaX;
	}

	public Usuario(Integer idX, String usuarioX, String contraseniaX) {
		id = idX;
		usuario = usuarioX;
		contrasenia = contraseniaX;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idX) {
		id = idX;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuarioX) {
		usuario = usuarioX;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contraseniaX) {
		contrasenia = contraseniaX;
	}
	
}