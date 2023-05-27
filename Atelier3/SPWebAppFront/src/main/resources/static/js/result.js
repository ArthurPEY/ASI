function getCookieLogin() {
  let cookie = {};
  document.cookie.split(';').forEach(function(el) {
    let [key,value] = el.split('=');
    cookie[key.trim()] = value;
  })
  return cookie["id"];
}
var loginId = getCookieLogin() /*Variable global*/


const getLastItem = Path => Path.substring(Path.lastIndexOf('/') + 1)

async function redirect(){

	loginId = getCookieLogin()

	const url = window.location.href  
	const combatId = getLastItem(url); // Id combat

	var combatInfo = await getCombatInfo(combatId)

	var message = document.querySelector(".message").innerHTML

	console.log(combatInfo);

	if(combatInfo.winner == loginId){document.querySelector("#msg").innerHTML = `Vous avez gagné ${combatInfo.mise}$ !`}
	else{document.querySelector("#msg").innerHTML = `Vous avez perdu ${combatInfo.mise}$ :(`}

	await new Promise(r => setTimeout(r, 4000));
	window.location.href = 'http://127.0.0.1:80/front/collection'
}

async function getCombatInfo(combatId){ // Fonction de récupération du nom de créateur de la room
    const reponse = await  fetch('http://127.0.0.1:80/combat/getcombat', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(combatId)
            })
    return reponse.json();
}