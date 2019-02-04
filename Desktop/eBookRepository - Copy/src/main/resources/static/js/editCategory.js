$(document).ready(function () {

    var id = localStorage.getItem("category_id");

    $.ajax({
        type: 'GET',
        url: '/api/category/'+id,
        success: function(data){
            console.log(data);
            document.getElementById('name').value=data.name;
        }
    });

     $('#btnSubmit').click(function(event){

            event.preventDefault();

            editCategory();

     });

function editCategory(){

    var name = $('#name').val().trim();
    var data = JSON.stringify({'name': name});

           $.ajax({
                type: 'PUT',
                url: '/api/category/update/'+ id,
                data: data,
                contentType: 'application/json',
                dataType: 'json',
                success: function(data){
                    alert('Successfuly edited category');
                    localStorage.removeItem('category_id');
                    window.location.replace('categories.html');

                },
                error : function(e) {
                       	console.log("ERROR: ", e);
               	}

           });

    }
});