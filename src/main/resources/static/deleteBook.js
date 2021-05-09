function redirectToIndex(page) {
	location.replace("http://localhost:8080/" + page)
}

function searchBookByName() {
	var bookId = document.getElementById("bookId").value;
	$.ajax({
		url : '/inventoryService/books/'+bookId,
		type : 'DELETE',
		success : function(response) {
			$("#results").html('Book Deleted Successfully!');
			$("#myalert").show();
		},
		error : function(error) {
			console.log(error);
			$("#results").html('Error: Could not delete book from the inventory!');
			$("#myalert").show();
		}
	});
	event.preventDefault();
}

