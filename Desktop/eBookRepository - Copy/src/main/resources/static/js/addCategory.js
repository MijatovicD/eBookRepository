$(document).ready(function(){

      $("#btnSubmit").click(function (event) {

           //stop submit the form, we will post it manually.
           event.preventDefault();

           addCategory();

       });

function addCategory(){


      var name = $('#name').val().trim();

      var data = JSON.stringify({'name': name});
      $.ajax({
            type: 'POST',
            url: '/api/category/add',
            contentType : "application/json",
            dataType: 'json',
            data : data,
            success: function(data){
                console.log(data);
                alert('Successfuly added category');
            },
            error : function(e) {
             	console.log("ERROR: ", e);
         	}

      });
}

});

