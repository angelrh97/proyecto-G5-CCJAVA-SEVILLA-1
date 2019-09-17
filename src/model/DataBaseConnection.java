package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.DataBaseConnection;
import modelo.Usuario;

public class DataBaseConnection {
	private Connection conn;
	private String user;
	private String password;
	
	public DataBaseConnection(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	
	/////////// CONEXION CON LA BASE DE DATOS "proyectocc" ///////////////
	public Connection getConn() throws ClassNotFoundException {
		conn = null;
		
		Properties info;
		String urlConn;
		
		
		urlConn = "jdbc:mysql://localhost:3306/proyectocc?"
				+ "useUnicode=true&useJDBCCompliantTimezoneShift="
				+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		info = new Properties();
		info.put("user", this.user);
		info.put("password", this.password);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(urlConn, info);
			System.out.println("Conectado a la base de datos");
		} catch (SQLException e) {
			Logger.getLogger(DataBaseConnection.class.getName())
				.log(Level.INFO, null, e);
				
		}
		
		return conn;
	}
	////////////////////////////////////  LOGIN DE USUARIO ADMINISTRADOR  //////////////////////////////////
	
	
	public ResultSet dameLogin(Usuario usuario) throws SQLException {
		ResultSet rs = null;
		String query = "SELECT `login`, `password` FROM `admin`"
				+ "WHERE `login` = ? AND `password` = ?;"  ;
		PreparedStatement stmt;
		
		try {
			conn = getConn();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getPassword());
			rs = stmt.executeQuery();
			
		} catch (ClassNotFoundException e) {
			Logger.getLogger(DataBaseConnection.class.getName())
			.log(Level.INFO, null, e);
		}
		
		return rs;
	}
	
	//////////////////////////////// LISTA DE USUARIOS ADMINISTRADORES /////////////////////////////////
	
	public ResultSet dameListaUsuarios() throws SQLException {
		ResultSet rs = null;
		String query = "SELECT a.usuario, a.password FROM admin a";
		Statement stmt;
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(DataBaseConnection.class.getName())
				.log(Level.INFO, null, e);
		}
		
		return rs;
	}
	
	//////////////////////// METODO DE ALTA-REGISTRO DE ARTISTA ////////////////////////////////
	
	public void altaArtista(Artista artista) throws SQLException {
		ResultSet rs = null;
		String queryAltaArtista ="INSERT INTO `proyectocc`.`artista` "
				+ "(`nombre`, `apellidos`, `email`, `telefono`, `residencia`, `descripcion`, `idcategoria`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;
		
		try {
			conn = getConn();
			conn.setAutoCommit(false);
			
			// Para recuperar el id generado
			
			stmt = conn.prepareStatement(queryAltaArtista);
			stmt.setString(1, artista.getNombre() );
			stmt.setString(2, artista.getApellidos());
			stmt.setString(3, artista.getEmail());
			stmt.setString(4, artista.getTelefono());
			stmt.setString(5, artista.getResidencia());
			stmt.setString(6, artista.getDescripcion());
			stmt.setInt(7, artista.getIdCategoria());
			
			// Comprobar si se ha insertado el registro de artista y guardar el id generado
			int totalInsertado = stmt.executeUpdate();
			
			if(totalInsertado == 0) {
				throw new SQLException("Error en el alta del artista");
			} 
			
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(DataBaseConnection.class.getName()).log(Level.INFO, null, e);
		}
		
		finally {
			conn.setAutoCommit(true);
		}
	}
	
	//////////////////////////// LISTA DE ARTISTAS /////////////////////////////////	
	
	public ResultSet dameTodosArtistas() throws SQLException {
		ResultSet rs = null;
		String query = "SELECT * FROM proyectocc.artista";

		Statement stmt;
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
        } 
		catch (ClassNotFoundException e) {
			Logger.getLogger(DataBaseConnection.class.getName())
			.log(Level.INFO, null, e);
		}
		return rs;
		
	}
	
	//////////////////////////// CATEGORIAS DE ARTISTAS /////////////////////////////////
	
	public ResultSet dameCategorias() throws SQLException {
		ResultSet rs = null;
		String query = "SELECT * FROM proyectocc.categoria";

		Statement stmt;
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
        } 
		catch (ClassNotFoundException e) {
			Logger.getLogger(DataBaseConnection.class.getName())
			.log(Level.INFO, null, e);
		}
		return rs;
	}
	
	//////////////////////////// ARTISTAS DE CATEGORIA ///////////////////////////////////////
	
	public ResultSet dameArtistasCategoria(int idCategoria) throws SQLException {
		ResultSet rs = null;
		
		String query = "SELECT a.id, a.nombre, a.apellidos, a.email, a.telefono, a.residencia, c.nombreCategoria AS `categoriaArtista` "
				+ "FROM artista a INNER JOIN categoria c ON a.idCategoria = c.id AND c.id = ?";
		
		PreparedStatement stmt;
		
		try {
			conn = getConn();
			stmt  = conn.prepareStatement(query);
			stmt.setInt(1, idCategoria);
			rs = stmt.executeQuery();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLogger(DataBaseConnection.class.getName())
			.log(Level.INFO, null, e);
		}
		
		return rs;
	}
	
	///////////////////////////// ARTISTAS CON CATEGORIA ////////////////////////////
	
	public ResultSet dameArtistasConCategoria() throws SQLException {
		ResultSet rs = null;
		
		String query = "SELECT a.nombre, a.apellidos, a.email, a.telefono, a.residencia, c.nombreCategoria AS `categoriaArtista` "
				+ "FROM artista a INNER JOIN categoria c ON a.idCategoria = c.id";
				;
		
		PreparedStatement stmt;
		
		try {
			conn = getConn();
			stmt  = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLogger(DataBaseConnection.class.getName())
			.log(Level.INFO, null, e);
		}
		
		return rs;
	}
	
	
	///////////////////////// ELIMINAR UN ARTISTA //////////////////////////
	
	public void eliminarArtista(int idArtista) throws SQLException {
		
		String queryEliminarArtista = "DELETE FROM `proyectocc`.`artista` WHERE (`id` = ?);";
		
		PreparedStatement stmt;
		
		try {
			conn = getConn();
			stmt = conn.prepareStatement(queryEliminarArtista);
			stmt.setInt(1, idArtista);
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Logger.getLogger(DataBaseConnection.class.getName())
			.log(Level.INFO, null, e);
		}
	}
}