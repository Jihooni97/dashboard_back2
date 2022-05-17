$(function(){
	
})

function enterKey(){
	if(window.event.keyCode == 13){
		login();
	}
}

function login(){
	var that = this;
	
	$("#loginForm").submit();
}

