$(document).ready(function() {

    var userId = localStorage.getItem("user_id");

    $.ajax({
        type: 'GET',
        url: '/api/users/'+ userId,
        success: function(data){
                console.log(data);
                document.getElementById('firstname').value=data.firstname;
                document.getElementById('lastname').value=data.lastname;
                document.getElementById('username').value=data.username;
                document.getElementById('password').value=data.password;
                document.getElementById('rePassword').value=data.password;
                var select = document.getElementById('type');
                var option = document.createElement("option");
                option.value = data.type;
                option.text = data.type;
                select.appendChild(option);
                getCategories();

        }

    });
/*
$.ajax({
                    type: 'GET',
                    url: '/api/category/user'+ userId,
                    dataType: 'json',
                    contentType: 'application/json',
                    success: function(data){
                            console.log(data);
                            var selectCategory = document.getElementById('categories');
                                for (var i in data){
                                     var optionCategory = document.createElement("option");
                                     optionCategory.value = data[i].id;
                                     optionCategory.text = data[i].name;
                                     selectCategory.appendChild(optionCategory);
                                }
                    }
                });
*/

    $('#btnSubmit').click(function(event){

        event.preventDefault();

        editUser();

        localStorage.removeItem("user_id");
    });

function getCategories(){

    $.ajax({
        type: 'GET',
        url : '/api/category/all',
        contentType: 'application/json',
        dataType: 'json',
        success: function(data){
            console.log(data);
              var selectCategory = document.getElementById('categories');
              for (var i in data){
                  var optionCategory = document.createElement("option");
                  optionCategory.value = data[i].id;
                  optionCategory.text = data[i].name;
                  selectCategory.appendChild(optionCategory);
              }
        }
    });
}


function editUser(){

     var firstname = $('#firstname').val().trim();
     var lastname = $('#lastname').val().trim();
     var username = $('#username').val().trim();
     var password = $('#password').val().trim();
     var rePass = $('#rePassword').val().trim();
     var type = $('#type').val();

    if(password != rePass){
        alert('Password and repeated password did not match!');
        return false;
    }

     var data = JSON.stringify({'firstname': firstname, 'lastname': lastname, 'username': username, 'password': password, 'type': type});

               $.ajax({
                    type: 'PUT',
                    url: '/api/users/update/'+ userId,
                    data: data,
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function(data){
                        alert('Successfuly edited user');
                        localStorage.removeItem('user_id');
                        window.location.replace('user.html');

                    },
                    error : function(e) {
                           	console.log("ERROR: ", e);
                   	}

               });
     }
});