package modelo;

public class ArtistasPorCategoria {
//	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private String residencia;
	private String nombreCategoria;
	
	public ArtistasPorCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtistasPorCategoria(/*int id,*/ String nombre, String apellidos, String email, String telefono,
			String residencia, String nombreDpto) {
		super();
//		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.residencia = residencia;
		this.nombreCategoria = nombreDpto;
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreDpto) {
		this.nombreCategoria = nombreDpto;
	}
	
	

}
