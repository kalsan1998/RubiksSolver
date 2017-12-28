const SQUARE_DIM = 50;
const FACE_DIM = SQUARE_DIM * 3 + 6;

var selectedColour = null;

window.onload = function () {
    var colourChoices = $(".colourChoice");
    for (i = 0; i < colourChoices.length; i++) {
        var colourChoice = colourChoices[i];
        colourChoice.onclick = function () {
            selectedColour = this.style.backgroundColor;
        }
    }
};

function Piece(colour, pos1, pos2, location) {
    this.location = location;
    var div = document.createElement("div");
    div.addEventListener("click", function (event) {
        changeColor(this, selectedColour);
    });
    div.setAttribute("class", "cubePiece " + location);
    div.setAttribute("style",
        "width: " + SQUARE_DIM + "px;" +
        "height: " + SQUARE_DIM + "px;" +
        "background-color:" + colour + ";" +
        "border : 3px solid black; " +
        "position : absolute;" +
        "top :" + pos1 + "px;" +
        "left : " + pos2 + "px"
    );
    this.div = div;
}

function Face(location, defaultColour, pos1, pos2) {
    this.defaultColour = defaultColour;
    this.location = location;
    var div = document.createElement("div");
    div.setAttribute("class", "cubeFace");
    div.setAttribute("id", location + "Face");
    div.setAttribute("style",
        "height:" + FACE_DIM + "px;" +
        "width:" + FACE_DIM + "px;" +
        "position: absolute;" +
        "top :" + pos1 + "px;" +
        "left :" + pos2 + "px"
    );
    this.div = div;
    this.pieces = new Array(9);
    var heightDesc;
    var widthDesc;
    for (i = 0; i < 3; i++) {
        if (i === 0) {
            heightDesc = "top";
        } else if (i === 1) {
            heightDesc = "mid";
        } else {
            heightDesc = "bottom";
        }
        for (j = 0; j < 3; j++) {
            if (j === 0) {
                widthDesc = "Left";
            } else if (j === 1) {
                widthDesc = "Mid";
            } else {
                widthDesc = "Right";
            }
            var piece = new Piece(defaultColour, i * SQUARE_DIM, j * SQUARE_DIM, heightDesc + widthDesc);
            this.pieces[(i * 3) + j] = piece;
            div.appendChild(piece.div);
        }
    }
    document.getElementById("cubeLaidOutContainer").appendChild(div);
}

function createFaces() {
    return (
        [new Face('right', 'green', FACE_DIM, 2 * FACE_DIM),
            new Face('up', 'red', 0, FACE_DIM),
            new Face('left', 'blue', FACE_DIM, 0),
            new Face('back', 'yellow', FACE_DIM, 3 * FACE_DIM),
            new Face('down', 'orange', 2 * FACE_DIM, FACE_DIM),
            new Face('front', 'white', FACE_DIM, FACE_DIM)]);
}

var faces = createFaces();

function changeColor(element, colour) {
    if (colour !== null) {
        element.style.backgroundColor = colour;
    }
}

function resetFaces(){
    for(i = 0; i <faces.length ; i++) {
        var face = faces[i];
        var pieces = face.div.childNodes;
        for(j = 0; j < pieces.length; j++){
            pieces[j].style.backgroundColor = face.defaultColour;
        }
    }
}

function postJSONCube() {
    var cubeInput = $("[name=cubeInfo]");
    cubeInput[0].value = getCubeJSON();
    document.getElementsByName("solveForm")[0].submit();
}

function getCubeJSON(){
    var cube = new Object;
    for(i = 0; i<faces.length; i++){
        var face = faces[i];
        var pieces = face.pieces;
        var piecesJSON = new Object();
        for(j = 0; j< pieces.length; j++){
            var piece = pieces[j];
            piecesJSON[piece.location] = piece.div.style.backgroundColor;
        }
        cube[face.location] = piecesJSON;
    }
    return JSON.stringify(cube);
}

function setCubeFromJSON(jsonCube) {
    var cube = JSON.parse(jsonCube);
}


