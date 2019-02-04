$(document).ready(function () {
	 var token = localStorage.getItem("token");
       $.ajax({
            type: 'GET',
            url: '/api/category/all',
            contentType: 'application/json',
            dataType: 'json',
            success: function(data){
                console.log(data);
                var select = document.getElementById('categories');
                    for (var i in data) {
                            var option = document.createElement("option");
                            option.value = data[i].id;
                            option.text = data[i].name;
                            select.appendChild(option);
                    }
            }
       });

        $.ajax({
                    type: 'GET',
                    url: '/api/languages/all',
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function(data){
                        console.log(data);
                        var select = document.getElementById('languages');
                            for (var i in data) {
                                    var option = document.createElement("option");
                                    option.value = data[i].id;
                                    option.text = data[i].name;
                                    select.appendChild(option);
                            }
                    }
               });

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        uploadData();

    });

    $("#btnSubmitLuceneQueryLanguage").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchLuceneQueryLanguage();

    });

    $("#btnSubmitLuceneTermQuery").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchLuceneTermQuery();

    });

});

function uploadData() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    var token = localStorage.getItem("token");
    console.log(data);

    var categorySelected = $('#categories').find(":selected").val();

    console.log(categorySelected);
    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/index/add",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
                    console.log(data);
                    console.log(categorySelected);
                    $('#result').empty();
                    $("#result").text(data);
                    console.log("SUCCESS : ", data);
                    $("#btnSubmit").prop("disabled", false);
        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }

    });

}

function searchLuceneQueryLanguage() {

    var value = $('#luceneQueryLanguage input[name=value]').val();
    var data = JSON.stringify({"value":value});
    console.log(data);
    $("#btnSubmitLuceneQueryLanguage").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/queryParser",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                console.log(data);
                $.each(result, function(key, value) {
                    console.log('nesto' + data);
                  $('#result').append('<li>' + key + ': ' + value + '</li>');
                });
            }
            console.log("SUCCESS : ", data);
            $("#btnSubmitLuceneQueryLanguage").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmitLuceneQueryLanguage").prop("disabled", false);

        }
    });

}

function searchLuceneTermQuery() {

    var field = $('#luceneTermQuery input[name=field]').val();
    var value = $('#luceneTermQuery input[name=value]').val();
    var data = JSON.stringify({"field":field, "value":value});

    console.log('nestooooo' + data);
    $("#btnSubmitLuceneTermQuery").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/term",
        data: data,
        contentType: 'application/json',
        success: function (data) {

        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $.each(result, function(key, value) {
                  $('#result').append('<li>' + key + ': ' + value + '</li>');
                });
            }
            console.log("SUCCESS : ", data);
            $("#btnSubmitLuceneTermQuery").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmitLuceneTermQuery").prop("disabled", false);

        }
    });

}