function getCookieLogin() {
    let cookie = {};
    document.cookie.split(';').forEach(function(el) {
      let [key,value] = el.split('=');
      cookie[key.trim()] = value;
    })
    return cookie["id"];
  }
  var loginId = getCookieLogin() /*Variable global*/




async function getRoomInfo(roomId){ // Fonction de récupération du nom de créateur de la room
    const reponse = await  fetch('http://127.0.0.1:80/room/getroom', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(roomId)
            })
    return reponse.json();
}



const getLastItem = Path => Path.substring(Path.lastIndexOf('/') + 1)

async function getRoom(){

        loginId = getCookieLogin();
		
		const url = window.location.href      
		const roomId = getLastItem(url);

		const roomInfo = await getRoomInfo(roomId);
		console.log(roomInfo);

		const player1Id = roomInfo.player1;
		const player2Id = roomInfo.player2;

		if(player1Id == loginId){
			console.log("En attente")
			if (player2Id != null){
				fetch('http://127.0.0.1:80/room/delroom', {
	                method: 'POST',
	                headers: {'Content-Type': 'application/json'},
	                body: JSON.stringify(roomId)
            	})
				.then(reponse => window.location.href = `http://127.0.0.1:80/front/selection/${roomId}`)
			}
		}
		else{window.location.href = `http://127.0.0.1:80/front/room`}
}

async function delRoom(roomId){
	const toSend = JSON.stringify(roomId)
	const del = await fetch('http://127.0.0.1:80/room/delroom', {
		                method: 'POST',
		                headers: {'Content-Type': 'application/json'},
		                body: toSend
	            	})

	return 

}



async function goBack(){

	loginId = getCookieLogin()

	const url = window.location.href      
	const roomId = getLastItem(url);

	const room = await getRoomInfo(roomId)
	const delR = await delRoom(roomId)

	mise = room.mise

	console.log(mise)

	toSend=[loginId,mise]
	console.log(room)

	const del = await fetch('http://127.0.0.1:80/user/setmoney', {
	                method: 'POST',
	                headers: {'Content-Type': 'application/json'},
	                body: JSON.stringify(toSend)
            	})
			.then(reponse => window.location.href = `http://127.0.0.1:80/front/room/`)


}


var intervalId = window.setInterval(function(){
  getRoom();
}, 1000);
