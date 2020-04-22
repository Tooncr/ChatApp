
window.onload = setStatus;
setInterval(getFriends, 2000);
var statusbutton= document.getElementById('statusbutton');
statusbutton.onclick = addStatus;
var friendbutton= document.getElementById('friendbutton');
friendbutton.onclick = addFriend;

var getStatus = new XMLHttpRequest();
var NewStatus = new XMLHttpRequest();



function addStatus() {
        var statusText = document.getElementById("statustext").value;
        console.log(statusText);
        var information = "status= " + encodeURIComponent(statusText);
        console.log(information);

        NewStatus.open("POST", "ManageStatusServlet", true);
        NewStatus.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        NewStatus.send(information);
        NewStatus.onreadystatechange = getData;
}

function addFriend() {
    var friendText = document.getElementById("friendtext").value;
    console.log(friendText);
    var information = "name=" + friendText;
    console.log(information);

    NewStatus.open("POST", "ManageFriendsServlet", true);
    NewStatus.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    NewStatus.send(information);
}

function setStatus(){
    NewStatus.open("GET", "ManageStatusServlet",true);
    NewStatus.onreadystatechange = getData;
    NewStatus.send(null);
}

function getFriends(){
    NewStatus.open("GET", "ManageFriendsServlet", true);
    NewStatus.onreadystatechange = getFriendData;
    NewStatus.send(null);
}

function getFriendData() {
    if(NewStatus.readyState === 4){
        if(NewStatus.status === 200){
            var serverResponse = JSON.parse(NewStatus.responseText);
            var friendstable = document.getElementById("table");
            while(friendstable.childNodes.length>2){
                friendstable.removeChild(friendstable.lastChild);
            }
            for(i in serverResponse.friends){
                var friendRow = document.createElement('tr');
                var naam = serverResponse.friends[i];
                var status = serverResponse.status[i];
                var naamCol = document.createElement('td');
                var statusCol = document.createElement('td');
                var naamText = document.createTextNode(naam);
                var statusText= document.createTextNode(status);
                naamCol.appendChild(naamText);
                statusCol.appendChild(statusText)
                friendRow.appendChild(naamCol);
                friendRow.appendChild(statusCol);
                friendstable.appendChild(friendRow);
            }

        }
    }
}

function getData() {
    if(NewStatus.readyState === 4){
        if(NewStatus.status === 200){
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