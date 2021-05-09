$(function() {
	getAllBooksInInventory();
});

function redirectToIndex(page) {
	location.replace("http://localhost:8080/" + page)
}

function getAllBooksInInventory() {
	$.ajax({
		url : '/inventoryService/books',
		type : 'GET',
		success : function(response) {
			showAllBooks(response);
		},
		error : function(error) {
			console.log(error);
		}
	});
	event.preventDefault();
}

function showAllBooks(values) {

	var tablearea = document.getElementById('books');
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

	for (var i = 0; i < values.length; i++) {

		var tr = document.createElement('tr');
		tr.appendChild(document.createElement('td'));
		tr.appendChild(document.createElement('td'));
		tr.appendChild(document.createElement('td'));
		tr.appendChild(document.createElement('td'));
		tr.appendChild(document.createElement('td'));

		var item = values[i];
		tr.cells[0].appendChild(document.createTextNode(item.id));
		tr.cells[1].appendChild(document.createTextNode(' '));
		tr.cells[2].appendChild(document.createTextNode(item.name));
		tr.cells[3].appendChild(document.createTextNode(' '));
		tr.cells[4].appendChild(document.createTextNode(item.quantity));

		table.appendChild(tr);
	}

	tablearea.appendChild(table);
}