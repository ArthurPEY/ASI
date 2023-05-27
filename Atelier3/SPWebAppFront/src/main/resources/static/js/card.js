function getCookieLogin() {
  let cookie = {};
  document.cookie.split(';').forEach(function(el) {
    let [key,value] = el.split('=');
    cookie[key.trim()] = value;
  })
  return cookie["id"];
}

function reload(){ // Fonction pour utilisation dans setTimeout
	location.reload()
}

var loginId = getCookieLogin() /*Variable global*/

function init(data){
    let list=[]
    for (const card of data){
        console.log(card)
        
	    infocard={
	        family_src:card.smallImgUrl,
	        family_name:card.name,
	        image_src:card.imgUrl,
	        energy:card.energy,
	        button: "Read",
			id:card.id,
			price:card.price,
			family:card.family,
			affinity:card.affinity,
			hp:card.hp,
			vitesse:card.vitesse,
			attack:card.attack,
			defence:card.defence
	    }
	    list.push(infocard)  
    }
    return list   
}

function affichage(cardlist){
    let template = document.querySelector("#selectedCard");
  
    for(const card of cardlist){
        
        let clone = document.importNode(template.content, true);
    
        newContent= clone.firstElementChild.innerHTML
                    .replace(/{{family_src}}/g, card.family_src)
                    .replace(/{{family_name}}/g, card.family_name)
                    .replace(/{{image_src}}/g, card.image_src)
                    .replace(/{{energy}}/g, card.energy)
                    .replace(/{{comment}}/g, card.comment)
                    .replace(/{{like}}/g, card.like)
                    .replace(/{{button}}/g, card.button)
					.replace(/{{cardid}}/g, card.id)
					.replace(/{{price}}/g, card.price)
					.replace(/{{family}}/g, card.family)
					.replace(/{{affinity}}/g, card.affinity)
					.replace(/{{hp}}/g, card.hp)
					.replace(/{{vitesse}}/g, card.vitesse)
					.replace(/{{attack}}/g, card.attack)
					.replace(/{{defence}}/g, card.defence);
        clone.firstElementChild.innerHTML= newContent;
    
        let cardContainer= document.querySelector("#gridContainer");
        cardContainer.appendChild(clone);
    }
}


var requestcard = () => {
	var loginId = getCookieLogin()
	fetch(`http://127.0.0.1:80/hero/requestcard/${loginId}`,{
	    method: 'POST'})
	  .then(response => response.json())
	  .then(jsondata =>{affichage(init(jsondata))}) 

}

 


function vendre(cardId){
	console.log(cardId)

	price = document.getElementById(`sellPrice${cardId}`).value
	toSend = [cardId,price]
	
	console.log(toSend)

	fetch('http://127.0.0.1:80/market/sell', {
	    method: 'POST',
	      headers: {
   		'Content-Type': 'application/json'
  		},
	    body: JSON.stringify(toSend)
	})
		.then(setTimeout(reload, 200))
	   
	
	return;
  }


/*{
    "name":"Jose",
    "image_src" : "https://imgc.allpostersimages.com/img/print/affiches/marvel-super-hero-squad-iron-man-standing_a-G-9448041-4985690.jpg",
    "date": "14h",
    "comment": "3 comments",
    "like": "17 likes",
    "button": "Buy",
    "description":"testDesc",
    "family":"testFamily",
    "affinity": "testAffinity"
}*/

window.onload = function() {
    requestcard();
}






