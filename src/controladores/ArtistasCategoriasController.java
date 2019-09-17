package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import modelo.Artista;
import modelo.Modelo;

/**
 * Servlet implementation class ArtistasCategoriasController
 */
@WebServlet("/ArtistasCategoriasController")
public class ArtistasCategoriasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistasCategoriasController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		
//		int idCategoriaRecibida = Integer.parseInt(request.getParameter("idcategoria"));
//				
//		//Instanciar el modelo para que nos devuelva los empleados del Dpto recibido
//		
//		Modelo m = new Modelo();
//		
//		List<Artista> artistasCategoria = m.getArtistasDeCategoria(idCategoriaRecibida);
//		
//		//El objeto de java empleadosDelDpto hay que convertirlo a un String en formato JSON con la libreria Gson
//		
//		PrintWriter out = response.getWriter();
//		
//		Gson gson = new Gson();
//		
//		out.print(gson.toJson(artistasCategoria));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
