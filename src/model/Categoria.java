package modelo;

public class Categoria {
	
	private int id;
	private String nombreCategoria;
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria(int id, String nombreCategoria) {
		super();
		this.id = id;
		this.nombreCategoria = nombreCategoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

}
