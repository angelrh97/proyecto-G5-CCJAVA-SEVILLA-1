package controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import modelo.Artista;
import modelo.Modelo;

/**
 * Servlet implementation class AltaUsuario
 */
@WebServlet("/AltaArtistaController")
public class AltaArtistaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaArtistaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("registroArtistas.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Modelo m = new Modelo();
		
		Gson gs = new Gson();
		Artista artista = gs.fromJson(request.getParameter("artista"), Artista.class);
		
		m.setArtista(artista);
		
		//request.getRequestDispatcher("registroArtistas.jsp").forward(request, response);
		
		//PrintWriter out = response.getWriter(); 
		
		//out.print(gs.toJson(artista));
		
	}

}
