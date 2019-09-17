<%@include file="lib/header.jsp"%>
<main>
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
<section id="login" class="login">
	<div class="container">
		<form action="LoginController" method = "POST">
	   		<h1>Administrador</h1>
	     		<div class="usuario-login">
	       			<input type="text" name="usuario" id="usuario" placeholder="Usuario">
	    		</div>
	     		<div class="contraseña-login">
	       			<input type="password" name="password" id="password" placeholder="Contraseña">
	     		</div>
	     		<button type="submit">Login</button>
	   	</form>
	 </div>	
   </section>
</main>	    	
<%@include file="lib/footer.jsp"%>