$(window).on('load', function() {
	if ($("footer").length) {
		var h = $(window).innerHeight();
		var hb = $("body").innerHeight() + $("footer").innerHeight();
		if (h > hb) $("footer").css({'width': '100%', 'position': 'absolute', 'top': 'auto', 'left': '0', 'right': 'auto', 'bottom': '0'});
	}
});

$(function() {
	$(window).on('scroll', scroll_funcs);
	$(window).on('load', scroll_funcs);
	function scroll_funcs() {
		show_recaptcha();
		a_top();
	}

	function show_recaptcha() {
		if ($('.g-recaptcha').length) {
			if ($(window).scrollTop() >= $('.g-recaptcha').position().top - $(window).height()) {
				$(".grecaptcha-badge").addClass("show");
			} else {
				$(".grecaptcha-badge").removeClass("show");
			}
		}
	}

	function a_top() {
		scrollTop = $(window).scrollTop();
		if  (scrollTop > 200) $(".scrolltop").fadeIn(400);
		else $(".scrolltop").fadeOut(400);
	}

	$(".scrolltop").on('click', function() {
		$("html, body").animate({ scrollTop: 0 }, "slow");
		return false;
	})
	if ($(window).width()<768){
		$('#menu').removeClass('menu').addClass('menu_principal');
		$('.hamburger').removeClass('no_visible');
	}
	$('.hamburger').click(function () {
		$('.hamburger').toggleClass('open');
		$('.menu_principal').toggleClass('abierto');
	})
	$(window).on('resize', function(){
		if ($(window).width()<768){
			$('#menu').removeClass('menu').addClass('menu_principal');
			$('.hamburger').removeClass('no_visible');
		} else {
			$('#menu').removeClass('menu_principal').addClass('menu');
			$('.hamburger').addClass('no_visible');
		}
	})
	$('.slider-portada').slick({
		slidesToShow: 1, 
		slidesToScroll: 1,
		autoplay: true,
		arrows: false
	});
	// Animacion de secciones
	var scrollTop = 0;
	var anim_elems = $(".animated_elem").toArray();
	var start_line = $(window).innerWidth() >= 576 ? $(window).innerHeight()/4*3 : $(window).innerHeight()/4*3;
	
	$(window).on('scroll', apply_anim);
	$(window).on('load', apply_anim);
	function apply_anim() {
		scrollTop = $(window).scrollTop();
		$(anim_elems).each(function(index, elem) {
			elem = $(elem);
			if (
				scrollTop + start_line >= elem.offset().top
				&& scrollTop <= elem.offset().top + elem.innerHeight()
			) {
				elem.addClass('animated');
				anim_elems.splice(index, 1);
				return false;
			}
		});
	}

	//Lazy load images
	$('.lazy').Lazy({
		effect: 'fadeIn',
		visibleOnly: false,
		afterLoad: function(elem) {
			//console.log('Element '+elem.attr('src')+' loaded');
		},
		onError: function(element) {
			console.log('error loading ' + element.data('src'));
		}
	});
	
	$('.alert').on('click', function() {
		$(this).remove();
	});
	initialize_inputfile(document, window, 0);
	if (!isMobileBrowser() && typeof $('select').select2 === 'function') {
		$('select').select2();
	}
	if (typeof $("form").validate == "function") {
		$("form").validate({
			'errorPlacement': function(error, element) {
				if (element.attr("type") == "checkbox") error.insertAfter(element.next());
				else error.insertAfter(element);
			}
		});
		jQuery.extend(jQuery.validator.messages, {
			"required":"Dato obligatorio",
			"remote":"Por favor, corrige este dato",
			"email":"Introduce una dirección de e-mail válida",
			"url":"Introduce una URL válida",
			"date":"Introduce una fecha válida",
			"dateISO":"Introduce una fecha(ISO) válida",
			"number":"Introduce una número válido",
			"digits":"Introduce sólo dígitos",
			"creditcard":"Introduce un nº de tarjeta de crédito válida",
			"equalTo":"Introduce el mismo valor otra vez",
			"maxlength":jQuery.validator.format("Introduce como máximo {0} caracteres"),
			"minlength":jQuery.validator.format("Introduce como mínimo {0} caracteres"),
			"rangelength":jQuery.validator.format("Introduce un valor entre {0} y {1} caracteres de longitud"),
			"range":jQuery.validator.format("Introduce un valor entre {0} y {1}"),
			"max":jQuery.validator.format("Introduce un valor menor o igual a {0}"),
			"min":jQuery.validator.format("Introduce un valor mayor o igual a {0}")
		});
	}
});

function recaptcha_submit(data) {
	console.log(data);
	var form = $(".g-recaptcha").parent('form');
	console.log(form);
}

function ajax_call(type, url_call, datatype, request_data, callback) {
	$("#loader").addClass('show');
	var request_data = type != "GET" ? request_data : '';
	$.ajax({
		crossDomain: true,
		crossOrigin: true,
		cache: false,
		type: type,
		url: url_call,
		headers: {
			'Access-Control-Allow-Origin': '*'
		},
		data: request_data,
		success: function(data, status, xhr) {
			$("#loader").removeClass('show');
			callback(data, request_data);
		},
		dataType: datatype,
		statusCode: {
			403: function() {
				console.log("Forbidden access: "+url);
			},
			404: function() {
				console.log("Not found: "+url);
			},
			500: function() {
				console.log("Script error: "+url);
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("Request failed: " + textStatus);
			console.log(jqXHR.responseJSON);
		}
	});
}

function isMobileBrowser() {
	if ( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) return true;
	else return false;
}

function getUrlParameter(sParam) {
	var sPageURL = window.location.search.substring(1),
		sURLVariables = sPageURL.split('&'),
		sParameterName,
		i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
		}
	}
}

function initialize_inputfile(document, window, index) {
	var selector = $('input[type=file]');
	selector.on('change', function() {
		var label = $(this).next(),
			labelVal = label.html();
		var fileName = '';
		var files = $(this)[0].files;
		if (files && files.length > 1) {
			fileName = ($(this).attr('data-multiple-caption') || '').replace('{count}', files.length);
		} else {
			fileName = $(this).prop('value').split('\\').pop();
		}
		if (fileName) 
			label.children('span').html(fileName);
		else
			label.html(labelVal);
	});
	// Firefox bug fix
	selector.on('focus', function(){ selector.addClass('has-focus'); });
	selector.on('blur', function(){ selector.removeClass('has-focus'); });
}

function number_format(number, decimals, dec_point, thousands_sep) {
	number = (number + '').replace(/[^0-9+\-Ee.]/g, '');
	var n = !isFinite(+number) ? 0 : +number,
		prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
		sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
		dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
	s = '',
	toFixedFix = function (n, prec) {
		var k = Math.pow(10, prec);
		return '' + (Math.round(n * k) / k).toFixed(prec);
	};

	// Fix for IE parseFloat(0.55).toFixed(0) = 0;
	s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
	if (s[0].length > 3) {
		s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
	}
	if ((s[1] || '').length < prec) {
		s[1] = s[1] || '';
		s[1] += new Array(prec - s[1].length + 1).join('0');
	}
	return s.join(dec);
}

/*!
 * IE10 viewport hack for Surface/desktop Windows 8 bug
 */
(function () {
	'use strict';
	if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
		var msViewportStyle = document.createElement('style')
		msViewportStyle.appendChild(document.createTextNode('@-ms-viewport{width:auto!important}'))
		document.querySelector('head').appendChild(msViewportStyle)
	}
})();