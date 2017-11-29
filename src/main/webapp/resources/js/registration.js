function registration(){
	$("#message").empty();
	var username = $("#username").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var passwordRepeat = $("#passwordRepeat").val();

	var form = {
		name: username,
		email: email,
		password: password,
		passwordRepeat: passwordRepeat
	};
	
	$.ajax({
		type: "POST",
		url: "registration",
		data: JSON.stringify(form),
		contentType: "application/json",
		success: function(data){
			var messageDiv = $("#message");
			if(data.successful){
				messageDiv.append("<p>Succesful registration</p>");
			} else {
				$.each(data.errors, function(key, value){
					messageDiv.append("<p>" + value + "</p>");
				});
			}
		}
	});
}