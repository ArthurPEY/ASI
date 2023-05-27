function getCookieLogin() {
    let cookie = {};
    document.cookie.split(';').forEach(function(el) {
      let [key,value] = el.split('=');
      cookie[key.trim()] = value;
    })
    return cookie["id"];
  }
  var loginId = getCookieLogin() /*Variable global*/


async function getCombatInfo(combatId){ // Fonction de récupération du nom de créateur de la room
    const reponse = await  fetch('http://127.0.0.1:80/combat/getcombat', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(combatId)
            })
    return reponse.json();
}


async function getHero(cardId){ // Fonction de récupération d'un hero a partir de son ID
    const reponse = await  fetch(`http://127.0.0.1:80/hero/gethero/${cardId}`,
								 {method: 'POST',})
    return reponse.json();
}

async function getCombatResult(combatId){
	const reponse = await  fetch('http://127.0.0.1:80/combat/startcombat', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(combatId)
            })
    return reponse.json();
}

async function getName(id){ // Fonction de récupération du nom de créateur de la room
    const reponse = await fetch(`http://127.0.0.1:80/user/getuser/${id}`,{method: 'GET'})
    return reponse.json();
}


async function attackOp(){
	document.getElementById("myCard").style.bottom = "1vh";
	await new Promise(r => setTimeout(r, 100));
	document.getElementById("myCard").style.bottom = "-5vh";
}

async function attackMe(){
	document.getElementById("opponentCard").style.top = "7vh";
	await new Promise(r => setTimeout(r, 100));
	document.getElementById("opponentCard").style.top = "1vh";
}






function updateHealthBar(id,value) {
  if(id == "me"){
  var healthValue = document.getElementById('myHealth');
  var healthBarFill = document.getElementById("myHealthBarFill");}

  else{
  var healthValue = document.getElementById('opHealth');
  var healthBarFill = document.getElementById("opHealthBarFill");}

  value = Math.max(0, Math.min(value, 100));

  healthBarFill.style.width = value + '%';
  healthValue.textContent = value.toString();
}





const getLastItem = Path => Path.substring(Path.lastIndexOf('/') + 1)

async function affichageCombat(){

	var cardList = []

	const loginId = getCookieLogin() // User courant

	const url = window.location.href  
	var combatId = getLastItem(url); // Id combat

	const combatInfo = await getCombatInfo(combatId) // Recuperation des proprietes du combat

	console.log(combatInfo)

	const HeroP1 = await getHero(combatInfo.heroIdP1)
	const HeroP2 = await getHero(combatInfo.heroIdP2)

	const userP1 = await getName(combatInfo.player1)
	const userP2 = await getName(combatInfo.player2)

	cardList.push(HeroP1)
	cardList.push(HeroP2)

	console.log(loginId == combatInfo.player1)
	
	if (loginId == combatInfo.player1){
		var myName = userP1.name
		var myId = userP1.id
		var myHero = HeroP1
		
		var opName = userP2.name
		var opId = userP2.id
		var opHero = HeroP2
		console.log(myName)
	}
	else{
		var myName = userP2.name
		var myId = userP2.id
		var myHero = HeroP2

		var opName = userP1.name
		var opId = userP1.id
		var opHero = HeroP1

	}

	console.log(myName)

	document.getElementById("myName").innerHTML = myName
	document.getElementById("opponentName").innerHTML = opName


    let template = document.querySelector("#Card");
  
    for(const card of cardList){
        
        let clone = document.importNode(template.content, true);
        console.log(card)
        newContent= clone.firstElementChild.innerHTML
                    .replace(/{{family_src}}/g, card.family_src)
                    .replace(/{{family_name}}/g, card.name)
                    .replace(/{{image_src}}/g, card.imgUrl)
                    .replace(/{{ownerid}}/g, card.owner)
                    .replace(/{{like}}/g, card.like)
					.replace(/{{cardid}}/g, card.id)
					.replace(/{{price}}/g, card.price)
					.replace(/{{family}}/g, card.family)
					.replace(/{{affinity}}/g, card.affinity)
					.replace(/{{hp}}/g, card.hp)
					.replace(/{{attack}}/g, card.attack)
					.replace(/{{defence}}/g, card.defence)
					.replace(/{{vitesse}}/g, card.vitesse);
        clone.firstElementChild.innerHTML= newContent;
    
		if (card.owner == loginId){var cardContainer= document.querySelector("#myCard");}
		else{var cardContainer = document.querySelector("#opponentCard")}
		cardContainer.appendChild(clone);
    }
	console.log("combat result")
	var CombatResult = await getCombatResult(combatId);
	console.log("combat recup")
	var myRes = CombatResult[0]
	var opRes = CombatResult[1]
	if (loginId != combatInfo.player1){
		var myRes = CombatResult[1]
		var opRes = CombatResult[0]}

	console.log(myRes)
	console.log(opRes)
    updateHealthBar("me",myRes[0]);
	updateHealthBar("op",opRes[0]);

	var k=0
	await new Promise(r => setTimeout(r, 2000));
	while (myRes[k] > 0 || opRes[k] > 0){
		k = k+1
		console.log(myRes[k])
		if (myHero.vitesse > opHero.vitesse){
			await new Promise(r => setTimeout(r, 1000));

			if(opRes[k] == undefined){break;}
			attackOp()
			//document.getElementById(`hp${opId}`).innerHTML = opRes[k]
			updateHealthBar("op",opRes[k]);
			if(opRes[k]<=0){break;}

			await new Promise(r => setTimeout(r, 1000));


			if(myRes[k] == undefined){break;}
			attackMe()
			//document.getElementById(`hp${myId}`).innerHTML = myRes[k]
			updateHealthBar("me",myRes[k]);
			if(myRes[k]<=0){break;}
		}
		else{
			await new Promise(r => setTimeout(r, 1000));

			if(myRes[k] == undefined){break;}
			attackMe()
			//document.getElementById(`hp${myId}`).innerHTML = myRes[k]
			updateHealthBar("me",myRes[k]);
			if(myRes[k]<=0){break;}

			await new Promise(r => setTimeout(r, 1000));

			
			if(opRes[k] == undefined){break;}
			attackOp()
			//document.getElementById(`hp${opId}`).innerHTML = opRes[k]
			updateHealthBar("op",opRes[k]);
			if(opRes[k]<=0){break;}
		}
	}

	myLastHealth = myRes[myRes.length - 1]

	

	if (myLastHealth>0){
		document.querySelector("#myResult").innerHTML = "Gagné !"
		await new Promise(r => setTimeout(r, 4000))
		window.location.href = `http://127.0.0.1:80/front/combat/win/${combatId}`}
	else{
		document.querySelector("#myResult").innerHTML = "Perdu"
		await new Promise(r => setTimeout(r, 4000))
		window.location.href = `http://127.0.0.1:80/front/combat/loose/${combatId}`}
	
}
    
document.addEventListener("DOMContentLoaded", function(){
    affichageCombat()
});
    