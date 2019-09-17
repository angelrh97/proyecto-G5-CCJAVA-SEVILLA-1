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
	<section id="noticias" class="noticias">
		<article class="margin-responsive">
			<div class="container">	
				<figure class="fondos fondo_1 animated_elem">
					<img src="img/fondo_01.jpg">
				</figure>
				<figure class="videos video_1 animated_elem">
					<img src="img/video_01.jpg">
				</figure>
				<div class="row justify-content-start text-left">
					<div class="col-md-6 col-11 texto texto_1 animated_elem">
						<div class="barra_gris"></div>
						<h2>Arte,<br> nuestra pasión</h2>
						<p>Cuidamos al máximo cada detalle para que tu perfil sea el más llamativo de todo el internet.</p>
						<a href="todosArtistas.jsp" class="text-md-right text-center"><p><span class="pregunta">Ver perfiles</span></p></a>
					</div>	
				</div>
			</div>
		</article>
		<article>
			<div class="container">
				<div class="row">
					<div class="col-md-6 fondo_sin_video animated_elem">
						<figure>
							<img src="img/piedras.jpg">
						</figure>
					</div>
					<div class="col-md-6 col-10 offset-2 offset-md-0 texto texto_2 animated_elem text-left align-self-center">
						<div class="barra_gris"></div>
						<h2>Muestrate a todo el mundo<br> Versión totalmente gratuita </h2>
						<p>Apostamos por la gente y la intervención de jóvenes promesas en todas las disciplinas artísticas.</p>
						<a href="todosArtistas.jsp" class="text-right"><p><span class="pregunta">Ver perfiles</span></p></a>
					</div>	
				</div>	
			</div>	
		</article>	
	</section>
</main>
<%@include file="lib/footer.jsp"%>