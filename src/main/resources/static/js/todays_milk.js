
$(document).ready(function(){
    $('.collapsible').collapsible();
});


    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();

    today = mm + '-' + dd + '-' + yyyy;

    console.log(today);
    var request = $.ajax({
        'url': "/"+today+'/pumps.json',
    });
    request.done(function (pumps) {

        console.log(pumps);
        var html = '';
        pumps.forEach(function (pump) {
            html += `<td>${pump.date}</td>`;
            html += `<td>${pump.time}</td>`;
            html += `<td>${pump.volumeInmL}</td>`;
        });
        $('#milk-data').html(html);

    });
