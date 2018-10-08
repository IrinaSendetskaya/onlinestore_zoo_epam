$(document).ready(function() {
	$(function() {
		
		$.validator.setDefaults({
			errorClass: 'error',
		});
		
		$.validator.addMethod("regex", function(value, element, regexpr) {
			return regexpr.test(value);
		});
		
		$("#formLogin").validate({
			rules : {
				nickname : {
					required : true,
					minlength : 3,
					maxlength : 15,
   				    regex : /^[a-zA-Z0-9]{3,15}$/,
				},
				password : {
					required : true,
					minlength : 4,
					maxlength : 12,
					regex : /^[a-zA-Z0-9]{4,12}$/,
				}
			},
			messages : {
				nickname : {
					required : "Это поле должно быть заполнено!",
					minlength : "Логин должен содержать 3-15 символов",
					maxlength : "Логин должен содержать 3-15 символов",
					regex : "Логин должен содержать только буквы и цифры"
				},
				password : {
					required : "Это поле должно быть заполнено!",
					minlength : "Пароль должен содержать 4-12 символов",
					maxlength : "Пароль должен содержать 4-12 символов",
					regex : "Пароль должен содержать только латинские буквы и цифры"
				}
			},
		});
		$("#formLogin").valid();
		
	});
});