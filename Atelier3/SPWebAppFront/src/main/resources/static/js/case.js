async function getCookieLogin() {
    let cookie = {};
    document.cookie.split(';').forEach(function(el) {
      let [key,value] = el.split('=');
      cookie[key.trim()] = value;
    })
    return cookie["id"];
  }
var loginId = getCookieLogin() /*Variable global*/

var nombreCard = 0;

const prizes = 13;
const rouletteNode = document.querySelector(".roulette");

async function getArgent() {
	loginId = getCookieLogin()
	const response = await fetch(`http://127.0.0.1:80/user/getmoney/${loginId}`,{
	    method: 'POST'})
	return response.json()
}

async function requestBaseHero(){
      var loginId = getCookieLogin()
      const reponse = await fetch('http://127.0.0.1:80/hero/basehero',{
          method: 'POST'})
	  return reponse.json()
  }



async function affichageCarte(){

	
 
 	const cardlist = await requestBaseHero()

	nombreCard = cardlist.length

	console.log(nombreCard)	

	let template = document.querySelector("#Card");

	for(const card of cardlist){

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
	
	
	
		  let el = document.createElement("div");
	      el.classList.add("prize-item");
		  el.innerHTML = newContent;
	      rouletteNode.appendChild(el);
	  }
      
  }


async function openCaseBack(cardIndex){
	loginId = await getCookieLogin()
	toSend=[loginId,cardIndex]
	const response = await fetch('http://127.0.0.1:80/hero/case', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(toSend)
            })
	return;
}


async function openCase(){

	await affichageCarte()

	var money = await getArgent()
	
	

	let options = {
	            stopCallback: function ({ detail: { prize } }) {
	                console.log("stop");
	                console.log(`Selected prize index is: ${prize.index}`);
	            },
	
	            startCallback: () => console.log("start")
	        };
	
	let roulette = new Roulette(".roulette", options);
	
	console.log(money)
	
		        document.getElementById("start").addEventListener("click", function (e) {
					
					if (parseInt(money)>=100){
					var index = Math.floor(Math.random() * (nombreCard))
					openCaseBack(index)
		            e.preventDefault();
		            roulette.fps = 120;
		            roulette.acceleration = 600;
		            let options = { random: false };
		            options.tracks = 1;
		            roulette.rotateTo(index, options);
					}
				else{
					console.log("Pas assez d'argent")
		        }});
	}
	



openCase()
