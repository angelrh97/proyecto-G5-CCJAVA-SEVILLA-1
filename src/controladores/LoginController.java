package controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Modelo;
import modelo.Usuario;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int contador = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(contador == 3) {
			contador = 0;
			request.getRequestDispatcher( "/index.jsp" ).forward( request, response );
		}
		else {
			request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
			contador++;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		Modelo m;
		m = new Modelo();
		
		String usu;
		String pass;
		usu = request.getParameter("usuario");
	    pass = request.getParameter("password");
		
		Usuario usuario = new Usuario(usu,pass);
		
		List<Usuario> listaUsuarios = m.getListaUsuarios();
		
		boolean usuarioValido = false;
		
		for(Usuario u: listaUsuarios) {
			if(u.getUsuario().equals(usu) && 
					u.getPassword().equals(pass)) {
				request.getRequestDispatcher("registroArtistas.jsp").forward(request, response);
				usuarioValido = true;
				break;
			}
		}
		
		if(usuarioValido == false) {	
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
