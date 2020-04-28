var webSocket;
var comments;

window.onload = openSocket;

function openSocket(){
    webSocket = new WebSocket("ws://localhost:8080/echo");

    webSocket.onopen = function(event){
    };

    webSocket.onmessage = function(event){
        if(!isNaN(event.data) ){
            comments = document.getElementById("review"+event.data);
        }else{
            writeResponse(event.data)
        }

    };

    webSocket.onclose = function(event){

    }
}


function send(){
    var review = document.getElementById("review").value;
    var name = document.getElementById("name").value;
    var comment = document.getElementById("comment").value;
    var rating = document.getElementById("rating").value;
    var text = "Name: " + name + "\n Comment: " + comment+ "\n Rating: "+ rating + "," + review;


    comments = document.getElementById("review" + review);
    webSocket.send(text);
}

function closeSocket() {
    webSocket.close();
}

function writeResponse(text) {
    comments.innerHTML += "<br/>" + text + "<br/>";
}