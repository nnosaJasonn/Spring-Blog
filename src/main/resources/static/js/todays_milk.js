

    var today = new Date();
var hh = String(today.getHours());
var min = String(today.getMinutes());
    console.log(hh);
    console.log(mm);
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();
    today = mm + '-' + dd + '-' + yyyy;

//    temporary


document.getElementById("month").value = mm;
document.getElementById("day").value = dd;
document.getElementById("year").value= yyyy;
document.getElementById("hours").value=hh;
document.getElementById("minutes").value = min;
    console.log(today);
    // today = "08-26-2019";
    function displayMilk() {
        var request = $.get({
            'url': "/" + today + '/pumps.json',
        });
        request.done(function (pumps) {

            console.log(pumps);
            var html = '';
            let total = 0;
            let count = 0;
            pumps.forEach(function (pump) {
                html += `<tr></tr></th><td>${pump.date}</td>`;
                html += `<td>${pump.time}</td>`;
                html += `<td>${pump.volumeInmL} mL</td></tr>`;
                total += pump.volumeInmL;
                count +=1;
            });
            console.log(total);

            $('#total').html(total + " mL");
            $("#avg").html((total/count).toFixed(2) + " mL");
            $('#milk-data').html(html);

        });
    }
    displayMilk();

    $("#new-milk").mousedown(function(e){
       e.preventDefault();

       let month = $("#month").val();
       let day = $("#day").val();
        let year = $("#year").val();


       let hour = $("#hours").val();
       let minutes = $("#minutes").val();

       let volume = $("#volume").val();
       volume = parseInt(volume);
       let date = month + "-" + day + "-" + year;
       let time = "" + hour + minutes;
       time = parseInt(time);
       let whiteRussian;
       if($("#lebowski").prop("checked")){
           whiteRussian = true;
       } else {
           whiteRussian = false;
       }

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.post({
            url: `/milk/${volume}/${date}/${time}/${whiteRussian}`,
            contentType: "application/json; charset=utf-8",
            beforeSend: function (jqXHR) {
                jqXHR.setRequestHeader('X-CSRF-Token', token,)
            },
            dataType: "json",

        })

        $('#new-milk').mouseup(function (e) {
            e.preventDefault();
            displayMilk();
        })

    });