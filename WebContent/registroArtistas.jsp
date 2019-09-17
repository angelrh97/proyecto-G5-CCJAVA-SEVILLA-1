<%@page import="java.util.List"%>
<%@page import="modelo.Artista"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="m" class="modelo.Modelo"></jsp:useBean>
<%
	
	List<Artista> listaArtistas = m.getListaArtistas();

%>
<%@include file="lib/header.jsp"%>
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
	<!-- <form action="AltaArtistaController" method="POST">  -->
	<form class="formulario">
	    	<h1>Registro de Artista</h1>
	    	<input type="hidden" value="" id="idArtista" name="idArtista">
	      	<input type="text" id="nombre" placeholder="Nombre" value="" required>
	      	<input type="text" id="apellidos" placeholder="Apellidos" value="" required>
	      	<input type="text" id="email" placeholder="Email" value="" required>
	      	<input type="text" id="telefono" placeholder="Telefono" value="" required>
	      	<input type="text" id="residencia" placeholder="Residencia" value="" required>
	      	<input type="text" id="descripcion" placeholder="Descripcion" value="" required>
	      	<select id="categorias" name="categorias">
	      	
	      		<option></option> 
	    		<c:forEach items = "${ m.categorias }" var = "c">
	    			<option value = "${c.getId() }"><c:out value = "${c.getNombreCategoria() }"></c:out></option>
	    		</c:forEach>
	      	
	      	</select>	
	      	<button  type="button" id="btnRegistrar" name="btnRegistrar">Registrar</button>
	      	     	<!-- onload="registroArtistas.jsp" -->
	</form>
	
	<script>
	
 		var idCategoriaSeleccionada = 0;
	   
	    document.getElementById("categorias").addEventListener("change", function() {
			var indice = this.selectedIndex;

			var valueOptionSelected = this.options[indice].value;
			
			var textOptionSelected = this.options[indice].text;
		
			idCategoriaSeleccionada = valueOptionSelected;
			
	    });
			/*var http = new XMLHttpRequest();
			
			http.onreadystatechange = function() {
				if(this.readyState == 4 && this.status == 200) {
					var rtaRecibida = this.responseText;
					
					var objRtaRecibida = JSON.parse(rtaRecibida);
					
					var filasTabla = "";
					for(var i = 0; i < objRtaRecibida.length; i++) {
						filasTabla += "<tr><td>"  
									+ objRtaRecibida[i].nombre + "</td><td>"
									+ objRtaRecibida[i].apellidos + "</td><td>"
									+ objRtaRecibida[i].email + "</td><td>"
									+ objRtaRecibida[i].telefono + "</td></td>"
									+ objRtaRecibida[i].residencia + "</td></tr>"
					}
					
					document.getElementById("tablaArtistasCategoria").innerHTML = filasTabla;
					
				}
			};
			
			http.open("GET", "ArtistasCategoriasController?ididCategoria="
					+idCategoriaSeleccionada, true);
			http.send();
			
	    }); */
	    
	    document.getElementById("btnRegistrar").addEventListener("click", function() {
						
			var objArtista = new Object(); 
			objArtista.id = document.getElementById("idArtista").value === "" ? 0 : document.getElementById("idArtista").value;
			objArtista.nombre = document.getElementById("nombre").value;
			objArtista.apellidos = document.getElementById("apellidos").value;
			objArtista.email = document.getElementById("email").value;
			objArtista.telefono = document.getElementById("telefono").value;
			objArtista.residencia = document.getElementById("residencia").value;
			objArtista.descripcion = document.getElementById("descripcion").value;
			objArtista.idCategoria = idCategoriaSeleccionada;
						
								
			var http = new XMLHttpRequest();
			
			var stringJSONObjArtista = JSON.stringify(objArtista); 
			console.log(stringJSONObjArtista);
			
			// Formular la peticion
			http.open("POST", "AltaArtistaController", true);
			http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			http.send("artista="+stringJSONObjArtista);
			});
			 	
	</script>
	
	<h3>Listado de Artistas Registrados</h3>
	<table border="1">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Eliminar</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(Artista artista: listaArtistas) {
				%>
					<tr>
						<td><%=artista.getNombre() %></td>
						<td><%=artista.getApellidos() %></td>
						
						<td><a href="EliminarArtistaController?idArtista=<%=artista.getId() %>">Eliminar</a></td>						
					</tr>
				<% 
				}
			%>
		</tbody>
	</table>
	</div>
<%@include file="lib/footer.jsp"%>