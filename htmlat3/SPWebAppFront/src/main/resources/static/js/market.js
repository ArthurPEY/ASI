function getCookieLogin() {
  let cookie = {};
  document.cookie.split(';').forEach(function(el) {
    let [key,value] = el.split('=');
    cookie[key.trim()] = value;
  })
  return cookie["id"];
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
	        date:"142h",
	        button: "Read",
			id:card.id,
			price:card.price,
			family:card.family,
			affinity:card.affinity,
			hp:card.hp,
			energy:card.energy,
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
                    .replace(/{{date}}/g, card.date)
                    .replace(/{{comment}}/g, card.comment)
                    .replace(/{{like}}/g, card.like)
                    .replace(/{{button}}/g, card.button)
					.replace(/{{cardid}}/g, card.id)
					.replace(/{{price}}/g, card.price)
					.replace(/{{family}}/g, card.family)
					.replace(/{{affinity}}/g, card.affinity)
					.replace(/{{hp}}/g, card.hp)
					.replace(/{{energy}}/g, card.energy)
					.replace(/{{attack}}/g, card.attack)
					.replace(/{{defence}}/g, card.defence);
        clone.firstElementChild.innerHTML= newContent;
    
        let cardContainer= document.querySelector("#gridContainer");
        cardContainer.appendChild(clone);
    }
}





function acheter(cardId){
	toSend = [loginId,cardId]


	fetch('http://127.0.0.1:80/market/buy', {
	    method: 'POST',
	      headers: {
   		'Content-Type': 'application/json'
  		},
	    body: JSON.stringify(toSend)
	})
	   .then(response => response.json())
	   .then(response => {console.log(JSON.stringify(response))})
	location.reload()
	
	return;
  }

function requestSellCard(){
	fetch('http://127.0.0.1:80/market/requestcardsell',{
	    method: 'POST'})
	  .then(response => response.json())
	  .then(jsondata =>{affichage(init(jsondata))}) 
	  return;
}

window.onload = function() {
    requestSellCard();
}



