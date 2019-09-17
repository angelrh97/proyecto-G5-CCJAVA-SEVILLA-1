<%@page import="modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="modelo.Modelo"%>
<%@page import="modelo.Artista"%>
<%@page import="modelo.ArtistasPorCategoria"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="mm" class="modelo.Modelo"></jsp:useBean>

<%
	Modelo m = new Modelo();	

	List<Categoria> categorias = m.getCategorias();

	Map<String, List<ArtistasPorCategoria>> listaArtistasCategorias = m.getArtistasPorCat();

%>
<%@include file="lib/header.jsp"%>
	<h1>Todos los artistas </h1>
	<section id="portada" class="portada">
		<div class="container">
			<div class="valign"></div><div class="inline titulo">
				<h1>Tu Red Social<br> de Arte</h1>
			</div>
		</div>	
		<div class="slider-portada">
		  <div class="fondo fondo_1"></div>
		  <div class="fondo fondo_2"></div>
		  <div class="fondo fondo_3"></div>
		</div>
	</section>
	<div class="container">
	<%
		for(Map.Entry<String, List<ArtistasPorCategoria>> entry:
					listaArtistasCategorias.entrySet()) {
			String nombreDeCategoria = entry.getKey();
			List<ArtistasPorCategoria> artistasDeCategoria = entry.getValue();
			%>
			<p><%=nombreDeCategoria %></p>
			<table border="1">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellidos</th>						
						<th>Email</th>
						<th>Telefono</th>
						<th>Pais de Residencia</th>
					</tr>
				</thead>
				<tbody>
				
					<%
						for(ArtistasPorCategoria ac: artistasDeCategoria) {
					%>
						<tr>
							<td><%=ac.getNombre() %></td>
							<td><%=ac.getApellidos() %></td>
							<td><%=ac.getEmail() %></td>
							<td><%=ac.getTelefono() %></td>
							<td><%=ac.getResidencia() %></td>		
							<%-- <td><%=ac.getNombreCategoria() %></td> --%>						
						</tr>
					<%							
						}
					%>
				</tbody>
			</table>
			<br>
			<%
			
		}
	%>
	</div>	
<%@include file="lib/footer.jsp"%>