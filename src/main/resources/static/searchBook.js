function redirectToIndex(page) {
	location.replace("http://localhost:8080/" + page)
}

function searchBookByName() {
	var bookName = document.getElementById("bookName").value;
	$.ajax({
		url : '/inventoryService/books/'+bookName,
		type : 'GET',
		success : function(response) {
			showSearchResult(response);
		},
		error : function(error) {
			console.log(error);
			$("#results").html('Error: Could not find the book in the inventory!');
			$("#myalert").show();
		}
	});
	event.preventDefault();
}

function showSearchResult(item) {
//	document.getElementById("results").innerHTML = item.id + " <----- " + item.name + " -----> " + item.quantity;
	
	var tablearea = document.getElementById('results');
	tablearea.innerHTML = '';
	table = document
			.createElement('table');
	
	table.setAttribute('border', '1');

	table.setAttribute('cellpadding', '5');
	table.setAttribute('width', '60%');
	table.setAttribute('margin-left', 'auto');
	table.setAttribute('margin-right', 'auto');

	var tr = document.createElement('tr');
	tr.appendChild(document.createElement('td'));
	tr.appendChild(document.createElement('td'));
	tr.appendChild(document.createElement('td'));
	tr.appendChild(document.createElement('td'));
	tr.appendChild(document.createElement('td'));
	tr.cells[0].appendChild(document.createTextNode('Book Id'));
	tr.cells[1].appendChild(document.createTextNode(' '));
	tr.cells[2].appendChild(document.createTextNode('Book Name'));
	tr.cells[3].appendChild(document.createTextNode(' '));
	tr.cells[4].appendChild(document.createTextNode('Quantity Available'));
	table.appendChild(tr);
	
	var tr = document.createElement('tr');
	tr.appendChild(document.createElement('td'));
	tr.appendChild(document.createElement('td'));
	tr.appendChild(document.createElement('td'));
	tr.appendChild(document.createElement('td'));
	tr.appendChild(document.createElement('td'));

	tr.cells[0].appendChild(document.createTextNode(item.id));
	tr.cells[1].appendChild(document.createTextNode(' '));
	tr.cells[2].appendChild(document.createTextNode(item.name));
	tr.cells[3].appendChild(document.createTextNode(' '));
	tr.cells[4].appendChild(document.createTextNode(item.quantity));

	table.appendChild(tr);
	
	tablearea.appendChild(table);
}