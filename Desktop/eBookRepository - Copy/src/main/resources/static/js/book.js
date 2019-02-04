function edit(id){

    var bookId = localStorage.setItem("id", id);
    window.location.replace('EditBook.html');

}

$(document).ready(function(){


    $.ajax({
        type:'GET',
        url:'/api/ebooks/all',
        success: function(data){
            console.log(data);
            for (var i=0; i<data.length; i++){
                $('#ebookTable').append('<tr><td><a onclick="edit('+data[i].id+')">'+ data[i].title +'</td>'
                +'<td>'+ data[i].author +'</td>'
                +'<td>'+ data[i].keywords +'</td>'
                +'<td>'+ data[i].publicationYear +'</td>'
                +'<td>'+ data[i].filename +'</td>'
                +'<td>'+ data[i].MIME +'</td>'
                +'</tr>');
            }
        }

    });

    $("#btnSearch").click(function (event) {

            //stop submit the form, we will post it manually.
        event.preventDefault();

        searchLuceneTermQuery();

    });

});

function searchLuceneTermQuery() {

    var field = $('#luceneTermQuery select[name=searchSelect]').val();
    var value = $('#luceneTermQuery input[name=search]').val();
    var data = JSON.stringify({"field":field, "value":value});

    console.log('nestooooo' + data);

    $.ajax({
        type: "POST",
        url: "/search/term",
        data: data,
        contentType: 'application/json',
        success: function (data) {

        /*	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $.each(result, function(key, value) {
                  $('#result').append('<li>' + key + ': ' + value + '</li>');
                });
            }*/

            $('#ebookTable').empty();
               for (var i=0; i<data.length; i++){
               console.log(data);
                            $('#ebookTable').append('<thead>'
                            +'<tr class="filters">'
                            +'<th><input type="text" class="form-control" placeholder="Title" disabled></th>'
                                                                         +'<th><input type="text" class="form-control" placeholder="Author" disabled></th>'
                                                                         +'<th><input type="text" class="form-control" placeholder="Keywords" disabled></th>'
                                                                         +'<th><input type="text" class="form-control" placeholder="Publication year" disabled></th>'
                                                                         +'<th><input type="text" class="form-control" placeholder="Filename" disabled></th>'
                                                                         +'<th><input type="text" class="form-control" placeholder="MIME" disabled></th>'
                                                                         +'</tr>'
                                                                         +'</thead>'
                            +'<tr><td>'+ data[i].title +'</td>'
                            +'<td>'+ data[i].author +'</td>'
                            +'<td>'+ data[i].keywords +'</td>'
                            +'<td>'+ data[i].publicationYear +'</td>'
                            +'<td>'+ data[i].filename +'</td>'
                            +'<td>'+ data[i].MIME +'</td>'
                            +'<td><input type="button" value="Download book"></td>'
                            +'</tr>');
                        }
            console.log("SUCCESS : ", data);
            $("#btnSubmitLuceneTermQuery").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSearch").prop("disabled", false);

        }
    });

}