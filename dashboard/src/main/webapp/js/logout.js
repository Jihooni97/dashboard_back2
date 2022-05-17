//로그아웃
function logout(){
	
	if(confirm("로그아웃 하시겠습니까?")){
		document.location.href = "/logout";
	}
}