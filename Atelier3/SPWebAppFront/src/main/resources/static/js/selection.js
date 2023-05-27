function getCookieLogin() {
    let cookie = {};
    document.cookie.split(';').forEach(function(el) {
      let [key,value] = el.split('=');
      cookie[key.trim()] = value;
    })
    return cookie["id"];
  }
  var loginId = getCookieLogin() /*Variable global*/
  
  var cardIdSelected = 0 

  var cards


function affichageCarte(){

	var card
	
	console.log(cardIdSelected)  

	for(const c of cards){
		if(c.id == cardIdSelected){
			card = c
		}
	}

	console.log(card)
	let template = document.querySelector("#Card");
    let clone = document.importNode(template.content, true);
    
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
				.replace(/{{energy}}/g, card.energy)
				.replace(/{{hp}}/g, card.hp)
				.replace(/{{attack}}/g, card.attack)
				.replace(/{{defence}}/g, card.defence)
				.replace(/{{vitesse}}/g, card.vitesse);
    clone.firstElementChild.innerHTML= newContent;

	cardContainer = document.querySelector("#right");

	while (cardContainer.firstChild) {
	  cardContainer.removeChild(cardContainer.firstChild);
	}

	cardContainer.appendChild(clone);

	
}



function affichage(cardlist){
      cards = cardlist
      let template = document.querySelector("#cardInfo");
	  cardIdSelected = cardlist[0].id
      for(const card of cardlist){

		  if (card.vitesse>=15){
          
	          let clone = document.importNode(template.content, true);
	      
	          newContent= clone.firstElementChild.innerHTML
						  .replace(/{{cardId}}/g, card.id)
	                      .replace(/{{name}}/g, card.name)
	                      .replace(/{{description}}/g, card.description)
	                      .replace(/{{family}}/g, card.family)
	                      .replace(/{{affinity}}/g, card.affinity)
	                      .replace(/{{attack}}/g, card.attack)
	                      .replace(/{{defence}}/g, card.defence)
	                      .replace(/{{hp}}/g, card.hp)
	                      .replace(/{{vitesse}}/g, card.vitesse);
	
	 
	          clone.firstElementChild.innerHTML = newContent;
	      
	          let cardContainer= document.querySelector("#tablecard");
	          cardContainer.appendChild(clone);
		}
      }
  }
  
  
async function requestcard(){
      var loginId = getCookieLogin()
      const rep = fetch(`http://127.0.0.1:80/hero/requestcard/${loginId}`,{
          method: 'POST'})
        .then(response => response.json())
        .then(response => {affichage(response)}) 
  }
  
   

async function selectRow(row) {

  var tableRows = document.getElementsByTagName('tr');
  for (var i = 0; i < tableRows.length; i++) {
    tableRows[i].removeAttribute("id", 'selected');
  }
	row.setAttribute("id", 'selected');
	cardIdSelected = row.querySelector('td[name="cardId"]').textContent

	affichageCarte(cardIdSelected)

	console.log(cardIdSelected)
}

const getLastItem = Path => Path.substring(Path.lastIndexOf('/') + 1)

function setCard(){

	
	const loginId = getCookieLogin()

	const url = window.location.href  
	const combatId = getLastItem(url);

	toSend=[combatId,loginId,cardIdSelected]


	console.log(toSend);

	fetch('http://127.0.0.1:80/combat/setcard', {
	                method: 'POST',
	                headers: {'Content-Type': 'application/json'},
	                body: JSON.stringify(toSend)
	            })
				.then(intervalId = window.setInterval(function(){
					  checkRedirect(combatId);
					}, 1000))

	window.selectRow = null; // bloque la fonction
}
  

async function getCombatInfo(combatId){ // Fonction de récupération du nom de créateur de la room
    const reponse = await  fetch('http://127.0.0.1:80/combat/getcombat', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(combatId)
            })
    return reponse.json();
}

async function checkRedirect(combatId){



		document.querySelector("#status").innerHTML = "En attente de l'autre joueur..."

		const combatInfo = await getCombatInfo(combatId);
		console.log(combatInfo);

		const cardIdP1 = combatInfo.heroIdP1;
		const cardIdP2 = combatInfo.heroIdP2;

		console.log(cardIdP1)
		console.log(cardIdP2)

		if(cardIdP1 != null && cardIdP2 != null){
			console.log("pret a lancer")
			window.location.href = `http://127.0.0.1:80/front/combat/${combatId}`
}


}

  window.onload = function() {
      requestcard();
  }
  
  
  
  
  
  
  