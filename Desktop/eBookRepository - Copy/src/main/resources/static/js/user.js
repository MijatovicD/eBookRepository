function edit(id){

    var bookId = localStorage.setItem("user_id", id);
    window.location.replace('editUser.html');

};

$(document).ready(function() {


	var token = localStorage.getItem("token");
	var logout = $('#logout');
	
	if(token == null){
		logout.hide();
	}else {
		logout.click(function(event){
			localStorage.removeItem("token");
		});
	}
	
    $.ajax({
        type:'GET',
        contentType: 'application/json',
        headers: {"Authorization" : "Bearer " + token},
        url:'/api/users/all',
        dataType: 'json',
        success: function(data){
            console.log(data);
            for (var i=0; i<data.length; i++){
                $('#userTable').append('<tr><td><a onclick="edit('+data[i].id+')">'+ data[i].firstname +'</td>'
                +'<td>'+ data[i].lastname +'</td>'
                +'<td>'+ data[i].username +'</td>'
                +'<td>'+ data[i].password +'</td>'
                +'<td>'+ data[i].type +'</td>'
                +'</tr>');
            }
        }

    });

});