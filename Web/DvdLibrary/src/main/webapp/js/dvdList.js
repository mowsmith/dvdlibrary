$(document).ready(function () {
    loadDvds();
    // on click for our add button
    $('#add-button').click(function (event) {
// we don’t want the button to actually submit
// we'll handle data submission via ajax
        event.preventDefault();
        // Make an Ajax call to the server. HTTP verb = POST, URL = contact
        $.ajax({
            type: 'POST',
            url: 'dvd',
            // Build a JSON object from the data in the form
            data: JSON.stringify({
                title: $('#add-title').val(),
                director: $('#add-director').val(),
                studio: $('#add-studio').val(),
                rating: $("input:radio[name='mpaaRating']:checked").val(),
                date: $('#add-releaseDate').val(),
                userNote: $('#add-user-note').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#add-title').val('');
            $('#add-director').val('');
            $('#add-studio').val('');
            $("#G").prop('checked', true);
            $('#add-releaseDate').val('');
            $('#add-user-note').val('');
            $('#validationErrors').empty();
            loadDvds();
        }).error(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        });
    });

    $('#search-button').click(function (event) {
// we don’t want the button to actually submit
// we'll handle data submission via ajax
        event.preventDefault();
        // Make an Ajax call to the server. HTTP verb = POST, URL = contact

        $.ajax({
            type: 'POST',
            url: 'search/dvds',
            // Build a JSON object from the data in the form
            data: JSON.stringify({
                title: $('#search-title').val() || '',
                director: $('#search-director').val() || '',
                studio: $('#search-studio').val() || '',
                rating: $("input:radio[name='mpaaRating']:checked").val() || '',
                date: $('#search-releaseDate').val() || '',
                userNote: $('#search-user-note').val() || ''
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-title').val('');
            $('#search-director').val('');
            $('#search-studio').val('');
            $('#search-releaseDate').val('');
            $('#search-user-note').val('');

            if ($("#G").prop('checked')) {
                $("#G").prop('checked', false);
            } else if ($("#PG").prop('checked')) {
                $("#PG").prop('checked', false);
            } else if ($("#PG13").prop('checked')) {
                $("#PG13").prop('checked', false);
            } else if ($("#R").prop('checked')) {
                $("#R").prop('checked', false);
            } else if ($("#NC17").prop('checked')) {
                $("#NC17").prop('checked', false);
            } else if ($("#NR").prop('checked')) {
                $("#NR").prop('checked', false);
            }

            fillDvdTable(data, status);
        });
    });

    // onclick handler for edit button
    $('#edit-button').click(function (event) {
        // prevent the button press from submitting the whole page
        event.preventDefault();

        // Ajax call -
        // Method - PUT
        // URL - contact/{id}
        // Just reload all of the Contacts upon success
        $.ajax({
            type: 'PUT',
            url: 'dvd/' + $('#edit-dvd-id').val(),
            data: JSON.stringify({
                title: $('#edit-dvd-title').val(),
                director: $('#edit-dvd-director').val(),
                studio: $('#edit-dvd-studio').val(),
                rating: $("#editModal input:radio[name='mpaaRating']:checked").val(),
                date: $('#edit-dvd-releaseDate').val(),
                userNote: $('#edit-dvd-userNote').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadDvds();
        });
    });
});
// Functions

function fillDvdTable(data, status) {
    // clear the previous list
    clearDvdTable();
    // grab the tbody element that will hold the new list of contacts
    var dTable = $('#contentRows');
    // Make an Ajax GET call to the 'contacts' endpoint. Iterate through
    // each of the JSON objects that are returned and render them to the
    // summary table.
    $.each(data, function (index, dvd) {
        dTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.id,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(dvd.title)
                                ) // ends the <a> tag
                        ) // ends the <td> tag for the contact name
                .append($('<td>').text(dvd.director))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.id,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteDvd(' + dvd.id + ')'
                                })
                                .text('Delete')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Delete
                ); // ends the <tr> for this Contact
    }); // ends the 'each' function
}

function loadDvds() {
    // Make an Ajax GET call to the 'contacts' endpoint. Iterate through
    // each of the JSON objects that are returned and render them to the
    // summary table.
    $.ajax({
        url: "dvds"
    }).success(function (data, status) {
        fillDvdTable(data, status);
    });
}


function deleteDvd(id) {
    var answer = confirm("Do you really want to delete this DVD?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + id
        }).success(function () {
            loadDvds();
        });
    }
}

function clearDvdTable() {
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.id);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-director').text(dvd.director);
        modal.find('#dvd-studio').text(dvd.studio);
        modal.find('#dvd-mpaaRating').text(dvd.mpaaRating);
        modal.find('#dvd-releaseDate').text(dvd.date);
        modal.find('#dvd-userNote').text(dvd.userNote);
    });
});

$('#editModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.id);
        modal.find('#edit-dvd-id').val(dvd.id);
        modal.find('#edit-dvd-title').val(dvd.title);
        modal.find('#edit-dvd-director').val(dvd.director);
        modal.find('#edit-dvd-studio').val(dvd.studio);
        modal.find('#edit-dvd-releaseDate').val(dvd.date);
        modal.find('#edit-dvd-userNote').val(dvd.userNote);
        switch (dvd.mpaaRating) {
            case "G":
                modal.find('#G').prop('checked', true);
                break;
            case "PG":
                modal.find('#PG').prop('checked', true);
                break;
            case "PG13":
                modal.find('#PG13').prop('checked', true);
                break;
            case "R":
                modal.find('#R').prop('checked', true);
                break;
            case "NC17":
                modal.find('#NC17').prop('checked', true);
                break;
            case "NR":
                modal.find('#NR').prop('checked', true);
                break;
        }
    });
});
var testDvdData = [
    {
        title: "Swiss Army Man",
        rating: "R",
        director: "The Daniels",
        studio: "A24",
        userNote: "Best movie ever!",
        id: 1,
        date: "2016-06-24"
    },
    {
        title: "Swiss",
        rating: "G",
        director: "The Cheeses",
        studio: "Sandwich",
        userNote: "Best cheese ever!",
        id: 2,
        date: "1990-06-11"
    }
];
var dummyDetailsDvd = {
    title: "Swiss Army Man",
    rating: "R",
    director: "The Daniels",
    studio: "A24",
    userNote: "Best movie ever!",
    id: 1,
    date: "2016-06-24"
};
var dummyEditDvd = {
    title: "Swiss",
    rating: "R",
    director: "The Daniels",
    studio: "A24",
    userNote: "Best movie ever!",
    id: 1,
    date: "2016-06-24"
}
;