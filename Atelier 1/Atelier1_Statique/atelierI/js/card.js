let cardlist;
cardlist = [
    {
        family_src:"https://imgc.allpostersimages.com/img/print/affiches/marvel-super-hero-squad-iron-man-standing_a-G-9448041-4985690.jpg",
        family_name:"Jose",
        image_src:"https://imgc.allpostersimages.com/img/print/affiches/marvel-super-hero-squad-iron-man-standing_a-G-9448041-4985690.jpg",
        date:"14h",
        comment:"3 comments",
        like: "17 likes",
        button: "Buy"
    },
    {
        family_src:"https://media.giphy.com/media/l4q8hciiYNT5RGi4w/giphy.gif",
        family_name:"John",
        image_src:"https://media.giphy.com/media/l4q8hciiYNT5RGi4w/giphy.gif",
        date:"1h",
        comment:"345 comments",
        like: "1000 likes",
        button: "Read"
    },
    {
        family_src:"http://www.superherobroadband.com/app/themes/superhero/assets/img/superhero.gif",
        family_name:"Elliot",
        image_src:"http://www.superherobroadband.com/app/themes/superhero/assets/img/superhero.gif",
        date:"142h",
        comment:"0 comment",
        like: "1 likes",
        button: "Read"
    }

];

function init(data){
    let max = 0
    let list=[]
    let urll = []
    for (const card of data){
        console.log(card)
        if (!(urll.includes(card.imgUrl))){
            infocard={
                family_src:card.smallImgUrl,
                family_name:card.name,
                image_src:card.imgUrl,
                date:"142h",
                comment:"0 comment",
                like: "1 likes",
                button: "Read"
            }
            if (!(list.includes(infocard))){
                list.push(infocard)
                max+=1;
                urll.push(card.imgUrl)

        

            }
                    
        
            if (max>1000) {
                break
            }
        }
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
                    .replace(/{{button}}/g, card.button);
        clone.firstElementChild.innerHTML= newContent;
    
        let cardContainer= document.querySelector("#gridContainer");
        cardContainer.appendChild(clone);
    }
}

fetch('http://vps.cpe-sn.fr:8083/cards')
  .then(response => response.json())
  .then(jsondata =>{affichage(init(jsondata))}) 
  .catch(error => {console.error(error)});









