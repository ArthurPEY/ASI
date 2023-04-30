const urlParams = new URLSearchParams(window.location.search);

function envoi(data){
    // Configurer la requête avec la méthode fetch
    fetch('http://vps.cpe-sn.fr:8083/card', {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json',
        'accept': '*/*'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
        console.log('Requête envoyée avec succès');
        } else {
        console.log('Erreur lors de l\'envoi de la requête');
        }
    })
    .catch(error => console.error(error));        
    }

function init(jsondata,data){
    let bool = true;
    for (const card of jsondata){
        if (card.name==data.name){
            console.log("pasok");
            bool =false;  
        }
    }
    
    
    return bool 
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
    const name = urlParams.get('name');
    const description = urlParams.get('description');
    const img_url = urlParams.get('img-url');
    const family = urlParams.get('family');
    const affinity = urlParams.get('affinity');
    const Hp = urlParams.get('Hp');
    const Energy = urlParams.get('Energy');
    const Attack = urlParams.get('Attack');
    const Defence = urlParams.get('Defence');

    
    
// Définir les données à envoyer
const data = {
    name: name,
    description: description,
    family: family,
    affinity: affinity,
    imgUrl:img_url,
    smallImgUrl:img_url,
    id:666,
    energy:Energy,
    hp: Hp,
    defence: Defence,
    attack: Attack,
    price: 666,
    userId: 666
};


if (data.name!=null){

    fetch('http://vps.cpe-sn.fr:8083/cards')
    .then(response => response.json())
    .then(jsondata =>{if (init(jsondata,data)){
        console.log("okif")
        envoi(data);
    }}) 
    .catch(error => {console.error(error)});
}
}
