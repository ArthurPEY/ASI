function reload(){ // Fonction pour utilisation dans setTimeout
	location.reload()
}

const sendInfo = () => {
	
	let name = document.getElementById('usernameInput').value
	let password = document.getElementById('passwordInput').value


	let user = {
	"name":name,
	"password":password,
	"money":1000
	}
	
	fetch('http://127.0.0.1:80/user/adduser', {
	    method: 'POST',
	      headers: {
   		'Content-Type': 'application/json'
  		},
	    body: JSON.stringify(user)
	})
	.then(location.reload())

  }
  
const sendLogin = () => {
	let username = document.getElementById('usernameInputLogin').value
	let password = document.getElementById('passwordInputLogin').value
	
	let user_pass = [username,password]
		fetch('http://127.0.0.1:80/user/login', {
	    method: 'POST',
	      headers: {
   		'Content-Type': 'application/json'
  		},
	    body: JSON.stringify(user_pass)
	})
		.then(response => response.json())
		.then(response => document.cookie = `id=${response}`)
		.then(setTimeout(reload, 200))
	
}





function getCookieLogin() {
    let cookie = {};
    document.cookie.split(';').forEach(function(el) {
        let [key,value] = el.split('=');
        cookie[key.trim()] = value;
    })
    return cookie["id"];
}
    
function printLoginForm(Cookie) {
    if (Cookie> 0) {
        // Masquage des balises HTML de connexion
        document.getElementById("connexion").style.display = "none";
        document.getElementById("deconnexion").style.display = "block";
        getMoney();
    } 
    else {
        // Affichage des balises HTML de connexion 
        document.getElementById("connexion").style.display = "block";
        document.getElementById("deconnexion").style.display = "none";
    }
    
}

function load(){
    var myCookie = getCookieLogin();
    printLoginForm(myCookie);
}

function resetCookie(){
	document.cookie = 'id=-1'
	location.reload();
	load()
    
}


window.addEventListener('load', function() {
  load();
});


const getMoney = () => {
	loginId = getCookieLogin()
	fetch(`http://127.0.0.1:80/user/getmoney/${loginId}`,{
	    method: 'POST'})
		.then(response=>response.json())
		.then(data => {document.getElementById("moneyP").innerHTML = `Argent : ${data}`,console.log(data)})
}



