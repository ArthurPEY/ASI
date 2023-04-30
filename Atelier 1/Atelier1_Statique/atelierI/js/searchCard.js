
const urlParams = new URLSearchParams(window.location.search);

let cards 
function init(data,info){
    let listcard=[]
    for (const card of data){
        if (card.name==info){
            cards={
                family_src:card.smallImgUrl,
                family_name:card.family,
                img_src:card.imgUrl,
                name:card.name,
                description:card.description,
                hp: card.hp,
                energy:card.energy,
                attack:card.attack,
                defence:card.defence  
            }
            if (!(listcard.includes(cards))){
                listcard.push(cards)}         
        }
    }
    if (listcard.length==0){
        console.log("fdfd")
        cards = {
            family_src:"http://www.superherobroadband.com/app/themes/superhero/assets/img/bird-cost.png",
            family_name:"Default",
            img_src:"http://www.superherobroadband.com/app/themes/superhero/assets/img/bird-cost.png",
            name:"??",
            description: 
            "I don't find it.Are you sure about it's name",
            hp: "?? HP",
            energy:"??? Energy",
            attack:"?? Attack",
            defence: "?? defence"  
        };
        listcard.push(cards)
    }
    
    return listcard 
}

function affichage(card){
    let template = document.querySelector("#selectedCard");
    let clone = document.importNode(template.content, true);
    
    newContent= clone.firstElementChild.innerHTML
                .replace(/{{family_src}}/g, card.family_src)
                .replace(/{{family_name}}/g, card.family_name)
                .replace(/{{img_src}}/g, card.img_src)
                .replace(/{{name}}/g, card.name)
                .replace(/{{description}}/g, card.description)
                .replace(/{{hp}}/g, card.hp)
                .replace(/{{energy}}/g, card.energy)
                .replace(/{{attack}}/g, card.attack)
                .replace(/{{defense}}/g, card.defence);
    clone.firstElementChild.innerHTML= newContent;
    
    let cardContainer= document.querySelector("#cardContainer");
    cardContainer.appendChild(clone);
  }
  function affichages(listcard){
    for(const card of listcard){
        affichage(card)
    }
  }


if (window.location.search && urlParams.get('method') === 'POST') {
  // La page a été appelée avec la méthode POST
  console.log('La page a été appelée avec la méthode POST');   
  affichage(cards) 
  
}
else {

  // La page a été appelée avec la méthode GET (ou sans méthode spécifiée)
    console.log('La page a été appelée avec la méthode GET');
    const urlParams = new URLSearchParams(window.location.search);
    const searchParam = urlParams.get('search');

    if (searchParam == null){
        cards = {
            family_src:"http://www.superherobroadband.com/app/themes/superhero/assets/img/bird-cost.png",
            family_name:"Default",
            img_src:"http://www.superherobroadband.com/app/themes/superhero/assets/img/bird-cost.png",
            name:"??",
            description: 
            "Who am I? Who are you? Who are you looking for? I will find him if he is on my list. Bring out the name of the one you are looking for",
            hp: "?? HP",
            energy:"??? Energy",
            attack:"?? Attack",
            defense: "?? defence"  
        };
        affichage(cards) 
    }
    else{
        
        fetch('http://vps.cpe-sn.fr:8083/cards')
        .then(response => response.json())
        .then(jsondata =>{affichages(init(jsondata,searchParam))}) 
        .catch(error => {console.error(error)});

        }

  
}








