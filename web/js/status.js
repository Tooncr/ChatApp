console.log("loads");
window.onload = setStatus;

var statusbutton= document.getElementById('statusbutton');
statusbutton.onclick = addStatus;

var getStatus = new XMLHttpRequest();
var NewStatus = new XMLHttpRequest();

function addStatus() {
        var statusText = document.getElementById("statustext").value;
        var information = "status= " + encodeURIComponent(statusText);

        NewStatus.open("POST", "ManageStatusServlet", true);
        NewStatus.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        NewStatus.send(information);

        document.getElementById("statustext").value = '';
}

function setStatusOnline(){
    NewStatus.open("GET", "ManageStatusServlet?status=online",true);
    NewStatus.onreadystatechange = setStatus;
    NewStatus.send(null);
}

function setStatusOffline() {
    NewStatus.open("GET", "ManageStatusServlet?status=offline",true);
    NewStatus.onreadystatechange = setStatus;
    NewStatus.send(null);
}

function setStatusAway() {
    NewStatus.open("GET", "ManageStatusServlet?status=away",true);
    NewStatus.onreadystatechange = setStatus;
    NewStatus.send(null);
}

function setStatusCustom() {
    NewStatus.open("GET", "ManageStatusServlet?status=custom",true);
    NewStatus.onreadystatechange = setStatus;
    NewStatus.send(null);
}

function setStatus() {
    if(NewStatus.readyState === 4){
        console.log("test");
        if(NewStatus.status === 200){
            console.log("ok");
            var serverResponse = JSON.parse(NewStatus.responseText);
            console.log(serverResponse);
            var statusXML = serverResponse.status;
            console.log(statusXML);

            var statusDiv = document.getElementById("status");
            var statusParagraph = statusDiv.childNodes[0];

            if(statusParagraph == null){
                statusParagraph = document.createElement('p');
                statusParagraph.id = "statusText";
                var statusText = document.createTextNode(statusXML);
                statusParagraph.appendChild(statusText);
                statusDiv.appendChild(statusParagraph);
            }else{
                var statusText = document.createTextNode(statusXML);
                statusParagraph.removeChild(statusParagraph.childNodes[0]);
                statusParagraph.appendChild(statusText);
            }
        }
    }
}