function getCookieLogin() {
  let cookie = {};
  document.cookie.split(';').forEach(function(el) {
    let [key,value] = el.split('=');
    cookie[key.trim()] = value;
  })
  return cookie["id"];
}

function addcard(){
try{
    const name = document.getElementById('nameInput').value
    const description = document.getElementById('descriptionInput').value
    const img_url = document.getElementById('urlInput').value
    const family = document.getElementById('familySelect').value
    const affinity = document.getElementById('affinitySelect').value;
    const Hp = document.getElementById('healthInput').value
    const Energy = document.getElementById('energyInput').value
    const Attack = document.getElementById('attackInput').value
    const Defence = document.getElementById('defenceInput').value
    const owner = parseInt(getCookieLogin())
    console.log(typeof owner);

    // Définir les données à envoyer
    const data = 
    {   
        "owner":owner,
        "name":name,
        "family":family,
        "description":description,
        "affinity":affinity,
        "imgUrl":img_url,
        "hp":Hp,
        "energy":Energy,
        "attack":Attack,
        "defence":Defence
    }
console.log(data);
    
if (data.name!=null){
    // Configurer la requête avec la méthode fetch
    fetch('http://127.0.0.1:80/hero/addhero', {
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
         console.log(response);
        } else {
        console.log('Erreur lors de l\'envoi de la requête');
        console.log(response);
        }
    })
    .catch(error => console.error(error));        
}
}
catch{
    console.log("pasok");
}
    





}
