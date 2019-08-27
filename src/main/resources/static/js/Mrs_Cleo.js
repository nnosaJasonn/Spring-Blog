// old code from the beginning of my JS days, needs a MAJOR refactor

var inputArray =[];
var inputArrToStr;
var nameArray = '';
var headArray = [];
var backupInputArray;

/*displays button values in input field*/
function input(n) {
    inputArray.push(n);
    inputArrToStr = inputArray.join('');
    var inputs = $('#Input_Field:text').val(inputArrToStr);
    console.log(inputArrToStr);
    return inputArrToStr;
}


/*adds buttons for name inputs*/
function numOfNames() {
    var items = $('#name_counter').val();
    var noSpace = items.replace(/\s+/g, '');
    nameArray = noSpace.split(',');
    for(var i=0;i<nameArray.length; i++){
        var listText = nameArray[i];

        var attribute = '' + nameArray[i] + '';

        $("#names").append('<button class="btn m-1 btn-dark clear_btn" onclick=input(' + "'" + attribute + "'" + ')>' + listText + '</button>');
    }
    console.log(nameArray);
    console.log(inputArray);
    console.log(inputArrToStr);
    return nameArray

}


/*builds table head and table rows*/
function buildTable(){
    inputArray = inputArrToStr.split(',');
    let rowNum = Math.pow(2, nameArray.length);
    headArray = nameArray;

    for (let i=0; i<inputArray.length; i++){
        if(inputArray !== ''){
            headArray.push(inputArray[i]);
        }
    } headArray.forEach(function(element, index) {
        $('#header').append('<th>' + element + '</th>')
    });
    for( i=0; i<rowNum; i++){
        /* tr = document.createElement('TR');*/
        $('#truthTable').append('<tr class="truthData" id="' + i + '"></tr>');
    }
    makeData(headArray);
}


/*truthobject and determines the connective*/
var truthObject = {}
var str2Arr = []
function makeData(headArray){
    let input = headArray.length;

    if(input === 2) {
        truthObject = {
            A: ['T','F']
        };
    } else if(input === 3) {
        truthObject = {
            A: ['T', 'T', 'F', 'F'],
            B: ['T', 'F', 'T', 'F',]
        }
    } else if(input === 4) {
        truthObject = {
            A: ['T', 'T', 'T', 'T', 'F', 'F', 'F', 'F'],
            B: ['T', 'T', 'F', 'F', 'T', 'T', 'F', 'F'],
            C: ['T', 'F', 'T', 'F', 'T', 'F', 'T', 'F']
        }
    } else if (input === 5) {
        truthObject = {
            A: ['T', 'T', 'T', 'T','T', 'T', 'T', 'T', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'],
            B: ['T', 'T', 'T', 'T', 'F', 'F', 'F', 'F','T', 'T', 'T', 'T', 'F', 'F', 'F', 'F'],
            C: ['T', 'T', 'F', 'F', 'T', 'T', 'F', 'F','T', 'T', 'F', 'F', 'T', 'T', 'F', 'F'],
            D: ['T', 'F', 'T', 'F', 'T', 'F', 'T', 'F','T', 'F', 'T', 'F', 'T', 'F', 'T', 'F']
        }
    }

    var arr2Str = inputArray.join(' ');
    str2Arr = arr2Str.split(' ');
    var compares = [];
    var connective = '';
    for(i = 0; i<str2Arr.length; i++) {

        if (str2Arr[i] === 'v' || str2Arr[i] === '&' || str2Arr[i] === '->' || str2Arr[i] === '<->' ||  str2Arr[i] === '~' ) {
            connective = str2Arr[i];
        } else {
            compares.push(str2Arr[i].toUpperCase())
        }
    } console.log(compares);

    console.log(str2Arr);
    console.log(connective);
    console.log(inputArray);
    console.log(truthObject);
    evaluate(connective, truthObject, compares);
    return str2Arr;

}


/*decides which function*/
function evaluate(connective, truthObject, compares) {
    switch(connective) {
        case 'v':
            orTable(truthObject, compares);
            break;
        case '&':
            andTable(truthObject, compares);
            break;
        case '->':
            ifTable(truthObject, compares);
            break;
        case '<->':
            ifandifTable(truthObject, compares);
            break;
        case'~':
            negation(truthObject, compares);
            break;
    }
    return compares;
}

/*makes table data for disjunction connective*/
function orTable(truthObject, compares) {
    console.log(truthObject);
    cArray = []
    let length = Math.pow(2, headArray.length);
    for (let i = 0; i<length; i++){
        if (truthObject[compares[0]][i] === 'F' && truthObject[compares[1]][i] === 'F'){
            cArray.push('F')
        } else {
            cArray.push('T')
        }
    }
    putItOnAPage(cArray, length)
}

/*makes table data for biconditional connective*/
function ifandifTable(truthObject, compares) {
    var length = Math.pow(2, headArray.length);
    cArray =[];
    for (i=0; i<length; i++){
        if ((truthObject[compares[0]][i] === 'T' && truthObject[compares[1]][i] === 'T') || ((truthObject[compares[0]][i] === 'F' && truthObject[compares[1]][i] === 'F'))){
            cArray.push('T')
        } else {
            cArray.push('F')
        }
    } putItOnAPage(cArray, length)
}

/*makes table data for conjunction connective*/
function andTable(truthObject, compares) {
    var length = Math.pow(2, headArray.length);
    cArray =[];
    for (i=0; i<length; i++){
        if (truthObject[compares[0]][i] === 'T' && truthObject[compares[1]][i] === 'T'){
            cArray.push('T')
        } else {
            cArray.push('F')
        }
    }
    putItOnAPage(cArray, length)
}

/*makes table data for conditional connective*/
function ifTable(truthObject, compares) {
    console.log(compares);
    var length = Math.pow(2, headArray.length);
    cArray =[];
    for (i=0; i<length; i++){
        if (truthObject[compares[0]][i] === 'T' && truthObject[compares[1]][i] === 'F'){
            cArray.push('F')
        } else {
            cArray.push('T')
        }
    }
    putItOnAPage(cArray, length)
}

/*makes table data for conditional connective*/
function negation(truthObject, compares) {
    var length = Math.pow(2, headArray.length);
    console.log(compares);
    cArray =[]
    for (let i=0; i<length; i++){
        if (truthObject[compares[0]][i] === 'T'){
            cArray.push('F')
        } else if (truthObject[compares[0]][i] === 'F'){
            cArray.push('T')
        }
    } putItOnAPage(cArray, length)
}




/*builds the actual truth table*/
function putItOnAPage(cArray, length) {
    if (headArray.length === 2) {
        for (i=0; i<length; i++){
            $('#' + [i]).html('<td>' + truthObject.A[i] + '</td>')
            if (cArray[i] === 'T'){
                $(`#${i}`).addClass('table-success')
            } else {
                $(`#${i}`).addClass('table-danger')
            }
        }
    }
    if (headArray.length === 3) {
        for (i=0; i<length; i++) {
            $('#' + [i]).html('<td>' + truthObject.A[i] + '</td>' + '<td>' + truthObject.B[i] + '</td>' + '<td>' + cArray[i] +'</td>')
            if (cArray[i] === 'T'){
                $(`#${i}`).addClass('table-success')
            } else {
                $(`#${i}`).addClass('table-danger')
            }
        }
    }
    if (headArray.length === 4) {
        for(i=0; i<length; i++) {
            $('#' + [i]).html('<td>' + truthObject.A[i] + '</td>' + '<td>' + truthObject.B[i] + '</td>' + '<td>' + truthObject.C[i] +'</td>' + '<td>' + cArray[i] +'</td>')
            if (cArray[i] === 'T'){
                $(`#${i}`).addClass('table-success')
            } else {
                $(`#${i}`).addClass('table-danger')
            }
        }
    }
    if (headArray.length === 5) {
        for(i=0; i<length; i++) {
            $('#' + [i]).html('<td>' + truthObject.A[i] + '</td>' + '<td>' + truthObject.B[i] + '</td>' + '<td>' + truthObject.C[i] +'</td>' + '<td>' + truthObject.D[i] +'</td>' + '<td>' + cArray[i] +'</td>')
            console.log(cArray[i])
            if (cArray[i] === 'T'){
                $(`#${i}`).addClass('table-success')
            } else {
                $(`#${i}`).addClass('table-danger')
            }
        }
    }

}

/*sounds*/
function playCleo(e){
    console.log(e);
    if(e.currentTarget.id === "addNames") {
        var audio = document.getElementById('myAudio');
        audio.play()
    } else if (e.currentTarget.id === "generate") {
        audio = document.getElementById('herAudio');
        audio.play()
    } else {
        audio = document.getElementById('bad-move');
        audio.play()
    }
}
/*clear button*/
function clear(){

    $('#names').empty();
    inputArrToStr = '';
    nameArray = '';
    inputArray = [];
    $('#header').html('');
    $('#truthTable').html('');
    return backupInputArray
}


$('#hover').click(function(){
    $('#miss-cleo').toggleClass('miss-cleo')
});

$('#clear').click(clear).click(playCleo)

$('#generate').click(buildTable)
/*$('#generate')*/.click(playCleo);


$('#addNames').click(playCleo).click(numOfNames);