$(document).ready(function(){

	$("#btnLogin").on("click", function(event){
		var username = $("#username").val().trim();
		var password = $("#password").val().trim();

		var user = {
				"username": username,
				"password": password
		}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url :"/api/auth/login",
			data : JSON.stringify(user),
			dataType : "json",
			success:function(data){
				console.log(data);
				var token = data.access_token;

				localStorage.setItem("token",token);
				window.location.replace("http://localhost:8080/Book.html");
			},
			error : function(e) {
				alert("Error")
				console.log("ERROR: ", e);
			}
		});

		event.preventDefault();
		return false;
	});
});