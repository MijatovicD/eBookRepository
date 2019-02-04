$(document).ready(function () {

    var bookId = localStorage.getItem("id");

    $.ajax({
        type: 'GET',
        url: '/api/ebooks/'+ bookId,
        success: function(data){
                console.log(data);
                document.getElementById('title').value=data.title;
                document.getElementById('keywords').value=data.keywords;
                document.getElementById('author').value=data.author;
                document.getElementById('publicationYear').value=data.publicationYear;
                document.getElementById('mime').value=data.mime;
        }

    });


    $('#btnSubmit').click(function(event){
        localStorage.removeItem("id");
    });

});