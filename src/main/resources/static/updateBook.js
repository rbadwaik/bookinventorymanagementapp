
function redirectToIndex(page) {
	location.replace("http://localhost:8080/" + page)
}


$(document).ready(function() {
	
    $('form').on('submit',function(event) {
        var formID = $(this).closest("form").attr("id");
        var bookId = document.getElementById("bookId").value;
    	var bookName = document.getElementById("bookName").value;
    	var quantity = document.getElementById("quantity").value;
    	
    	var data = {"id" : bookId,
    				"name" : bookName,
    				"quantity" : quantity
    				}
		
		if(formID == "formUpdateBook") {
			$.ajax({
				url: '/inventoryService/books/' + bookId,
				data: JSON.stringify(data),
				type: 'PUT',
				contentType: "application/json; charset=utf-8",
				success: function(response) {
					$("#results").html('Book Updated Successfully!');
					$("#myalert").show();
				},
            
				error: function(error) {
					console.log(error);
					$("#results").html('Error: Could not update book in the inventory!');
					$("#myalert").show();
				}
			});
			event.preventDefault();
		}
        
    });
});