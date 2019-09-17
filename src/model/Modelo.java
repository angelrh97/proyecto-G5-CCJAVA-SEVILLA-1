package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import modelo.DataBaseConnection;
import modelo.Usuario;
import modelo.Artista;

/**
 * Session Bean implementation class Modelo
 */
@Stateless
@LocalBean
public class Modelo {
	
	private Artista artista;
	private int idArtista;
	private List<Artista> listaArtistas;
	private List<Artista> artistasDeCategoria;
	private Map<String, List<ArtistasPorCategoria>> artistasPorCat;
	private List<Categoria> categorias;
	private Usuario usuario;
	private List<Usuario> listaUsuarios;

	/**
     * Default constructor. 
     */
    public Modelo() {
        // TODO Auto-generated constructor stub
    }
    
    /////////////////////// ADMIN-USUARIO LOGIN ///////////////////////////

    public Usuario getUsuario(Usuario usu) {		
	    DataBaseConnection conexion = new DataBaseConnection("root", "Temp2019$$");
		usuario = new Usuario();
		ResultSet rs = null;
		try {
			rs = conexion.dameLogin(usu);
			if(rs.next() && rs.getRow() != 0) {
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setPassword(rs.getString("password"));
			}
			else {
				usuario = null;
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    
    /////////////////////////// ARTISTA ///////////////////////////////////
    
	public Artista getArtista() {
		return artista;
	}
	
	
	public void setArtista(Artista artista) {
		
		DataBaseConnection conexion;
		conexion = new DataBaseConnection("root", "Temp2019$$");
		
		try {
			conexion.altaArtista(artista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(DataBaseConnection.class.getName()).log(Level.INFO, null, e);
		} 
		this.artista = artista;
	}
	
	 ///////////////////////////  LISTA DE ARTISTAS //////////////////////
	
	public List<Artista> getListaArtistas() {
		
		DataBaseConnection conexion;
		conexion = new DataBaseConnection("root","Temp2019$$");
		
		try {
			ResultSet rs = conexion.dameTodosArtistas();
			listaArtistas = new ArrayList<>();
			while(rs.next()) {
				listaArtistas.add(new Artista(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		
		return listaArtistas;
	}

	public void setListaArtistas(List<Artista> listaArtistas) {
		this.listaArtistas = listaArtistas;
	}
	
	
	/////////////////////////// CATEGORIAS //////////////////////////

	public List<Categoria> getCategorias() {
		
		DataBaseConnection conexion;
		conexion = new DataBaseConnection("root","Temp2019$$");
		
		try {
			ResultSet rs = conexion.dameCategorias();
			categorias = new ArrayList<>();
			while(rs.next()) {
				categorias.add(new Categoria(rs.getInt("id"),rs.getString("nombreCategoria")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	/////////////////////// LISTA DE USUARIOS ///////////////////////////////////
	
	public List<Usuario> getListaUsuarios() {
    	DataBaseConnection conexion;
    	conexion = new DataBaseConnection("root", "Temp2019$$");
    	
    	try {
			ResultSet rs = conexion.dameListaUsuarios();
			listaUsuarios = new ArrayList<>();
			while(rs.next()) {
				listaUsuarios.add(new Usuario(
						rs.getString("usuario"),  
						rs.getString("password")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		
		return listaUsuarios;
	}

	
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	///////////////////////// ARTISTAS DE CATEGORIAS ////////////////////////
	
	public List<Artista> getArtistasDeCategoria(int idCategoria) {
    	DataBaseConnection dataBaseConnection;
    	dataBaseConnection = new DataBaseConnection("root", "Temp2019$$");
    	
    	try {
			ResultSet rs = dataBaseConnection.dameArtistasCategoria(idCategoria);
			artistasDeCategoria = new ArrayList<>();
			while(rs.next()) {
				artistasDeCategoria.add(new Artista(
						rs.getInt("id"),
						rs.getString("nombre"), 
						rs.getString("apellidos"),
						rs.getString("email"),
						rs.getString("telefono"), 
						rs.getString("residencia"))); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
    	
		return artistasDeCategoria;
	}

	public void setArtistasDeCategoria(List<Artista> artistasDeCategoria) {
		this.artistasDeCategoria = artistasDeCategoria;
	}
	
	/////////////////////////// ARTISTAS POR CATEGORIA //////////////////////
	
	public Map<String, List<ArtistasPorCategoria>> getArtistasPorCat() {
		
		DataBaseConnection conexion;
		conexion = new DataBaseConnection("root", "Temp2019$$");
		
		try {
			ResultSet rs = conexion.dameArtistasConCategoria();
			List<ArtistasPorCategoria> artistasCategoria = new ArrayList<>();
			while(rs.next()) {
				artistasCategoria.add(new ArtistasPorCategoria(
						rs.getString("nombre"),
						rs.getString("apellidos"),
						rs.getString("email"),
						rs.getString("telefono"),
						rs.getString("residencia"),
						rs.getString("categoriaArtista")));
			}
			
			artistasPorCat = artistasCategoria.stream().collect(Collectors
					.groupingBy(ArtistasPorCategoria::getNombreCategoria));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		
		return artistasPorCat;
	}

	public void setArtistasPorCat(Map<String, List<ArtistasPorCategoria>> artistasPorCat) {
		this.artistasPorCat = artistasPorCat;
	}
	
	
	/*
	 * public Map<String, List<EmpleadoPorDpto>> getEmpleadosPorDpto() {
		DataBaseConnection conexion;
		conexion = new DataBaseConnection("root", "Temp2019$$");
		
		try {
			ResultSet rs = conexion.dameEmpleados();
			List<EmpleadoPorDpto> empleadosDpto;
			empleadosDpto = new ArrayList<>();
			while(rs.next()) {
				empleadosDpto.add(new EmpleadoPorDpto(
						rs.getInt("id"),
						rs.getString("nombre"), 
						rs.getString("apellidos"), 
						rs.getDouble("salario"), 
						rs.getString("Nombre Del Dpto"))); 
			}
			empleadosPorDpto = empleadosDpto.stream()
					.collect(Collectors
							.groupingBy(EmpleadoPorDpto::getNombreDpto));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 */
	 
	
	

	//////////////////////////////// PARA ELIMINAR UN ARTISTA ////////////////
	public int getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(int idArtista) {
		DataBaseConnection dataBaseConnection;
		dataBaseConnection = new DataBaseConnection("root", "Temp2019$$");
		
		try {
			dataBaseConnection.eliminarArtista(idArtista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
	}



	///////////////////////// LISTA COMPLETA DE TODOS LOS ARTISTAS ////////////////////

	
}
