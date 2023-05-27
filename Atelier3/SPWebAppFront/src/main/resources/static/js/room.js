function getCookieLogin() {
    let cookie = {};
    document.cookie.split(';').forEach(function(el) {
      let [key,value] = el.split('=');
      cookie[key.trim()] = value;
    })
    return cookie["id"];
  }
  var loginId = getCookieLogin() /*Variable global*/



async function getName(id){ // Fonction de récupération du nom de créateur de la room
    const reponse = await fetch(`http://127.0.0.1:80/user/getuser/${id}`,{method: 'GET'})
    return reponse.json();
}

async function affichage(room){
    let template = document.querySelector("#roomInfo");

    for(const roomInfo of room){
        let clone = document.importNode(template.content, true);
    	console.log(roomInfo);

        const data = await getName(roomInfo.player1);

        newContent = clone.firstElementChild.innerHTML
                    .replace(/{{roomId}}/g, roomInfo.id)
                    .replace(/{{player1}}/g, data.name)
					.replace(/{{mise}}/g, roomInfo.mise)

        clone.firstElementChild.innerHTML= newContent;
        let cardContainer= document.querySelector("#tableRoom");
        cardContainer.appendChild(clone);  
    }
}

function requestRoom() {
	var loginId = getCookieLogin()
	fetch(`http://127.0.0.1:80/room/allroom`,{
	    method: 'POST'})
	  .then(response => response.json())
	  .then(response => affichage(response)) 
      .catch(console.log("erreur"))

}

function createRoom(){
    try{
        loginId = getCookieLogin();
        const mise = document.querySelector("#bet").value

        toSend = [loginId,mise]

        if (loginId > 0){
            fetch('http://127.0.0.1:80/room/newroom', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(toSend)
            })
			.then(response => response.json())
            .then(response => window.location.href = `http://127.0.0.1:80/front/attente/${response}`)
        }
    }
    catch{

    }

}


function joinRoom(roomId){
	loginId = getCookieLogin();
	


		if (loginId > 0){
				toSend=[roomId,loginId]
	            fetch('http://127.0.0.1:80/room/joinroom', {
	                method: 'POST',
	                headers: {'Content-Type': 'application/json'},
	                body: JSON.stringify(toSend)
	            })
	            .then(reponse => window.location.href = `http://127.0.0.1:80/front/selection/${roomId}`)
		}
}


