function edit(id){
    console.log(id);

    var categoryId = localStorage.setItem("category_id", id);
    window.location.replace('EditCategory.html');

}

$(document).ready(function(){

    $.ajax({
        type:'GET',
        url:'/api/category/all',
        contentType:'application/json',
        dataType:'json',
        success:function(data){
            for (var i=0; i<data.length; i++){
                console.log(data);
                $('#categoriesTable').append('<tr><td>'+ data[i].id +'</td><td>'+ data[i].name +'</td><td><button onclick="edit(' + data[i].id + ')">Edit</button></td></tr>')

            }

        }

    });

});