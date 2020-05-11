
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
                var chatCol = document.createElement('td');
                var naamText = document.createTextNode(naam);
                var statusText= document.createTextNode(status);
                var chatText = document.createElement("button");
                chatText.innerHTML = "chat";


                naamCol.appendChild(naamText);
                statusCol.appendChild(statusText);
                chatCol.appendChild(chatText);

                friendRow.appendChild(naamCol);
                friendRow.appendChild(statusCol);
                friendRow.appendChild(chatCol);

                chatCol.onclick = function(){
                    openChat(serverResponse.id[i]);
                }

                friendstable.appendChild(friendRow);


            }

        }
    }
}

function openChat(string){
    var chats = document.getElementById("chats");
    var container = document.createElement("div");
    container.id = "container";
    var hidebutton = document.createElement("button");
    var showbutton = document.createElement("button");
    hidebutton.innerHTML = "Hide chat";
    showbutton.innerHTML = "Show chat with " + string;
    showbutton.id= 'sh'+getContainerId();
    hidebutton.onclick =function () {
        console.log(getContainerId());
        $(document.getElementById(getContainerId())).hide();
    }
    showbutton.onclick = function () {
        $(document.getElementById(getContainerId())).show();
        $(document.getElementById('sh'+getContainerId())).hide();
    }
    var messages = document.createElement("div");
    setsessionId(string);
    var title = document.createElement("H1");
    var textArea = document.createElement("textarea");
    var button = document.createElement("button");
    button.innerHTML = "Send";
    button.value = "Send";
    button.name = "Send";
    button.onclick = function(){
        var message = textArea.value;
        console.log(message);
        postMessage();
        function postMessage() {
            $.ajax({
                type: "POST",
                url: "MessageServlet?message="+message+"&id="+messages.id,
                async:false,
                success: function(response){
                    getMessages()
                }
            });
        }
    }
    function setsessionId(string) {
        $.ajax({
            type: "POST",
            url: "IdServlet?friend="+string,
            datatype: "json",
            async: false,
            success: function (data) {
                setMessageid(data);
                setContainerid("container" + data)

            }
        })
        function setContainerid(data) {
            container.id = data;
        }
        function setMessageid(data){
            console.log(data);
            messages.id = data;

        }
    }
    function getsessionId(){
        return messages.id;
    }
    function getContainerId() {
        return container.id;
    }
    function getMessages() {
        $.ajax({
            type: "GET",
            url: "MessageServlet?id="+getsessionId(),
            success: function(response){
               document.getElementById(getsessionId()).innerText =response;

               setTimeout(getMessages, 10000);
            }
        })
    }
    title.id = "title"+getContainerId();
    getFrStatus();
    function getFrStatus(){
        $.ajax({
            type:"GET",
            url: "FriendStatusServlet?id="+string,
            success: function (response) {
                document.getElementById("title"+getContainerId()).innerText = string +  " : " +response;

                setTimeout(getFrStatus,2000);
            }
        })
    }







    container.appendChild(title);
    container.appendChild(hidebutton);
    container.appendChild(messages);
    container.appendChild(textArea);
    container.appendChild(button);
    chats.appendChild(container);
    chats.appendChild(showbutton);
    getMessages();
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