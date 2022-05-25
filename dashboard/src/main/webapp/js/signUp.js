$(function() {
	
	$.isUserID = function(value){
		var chk = /^[a-z0-9_-]{5,12}$/;
		return chk.test(value);
	}
	
	$.isUserPW = function(value){
		var chk = /^.*(?=^.{9,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		return chk.test(value);
	}
	
	signUpRequest();	
	cancel();
	
})

function signUpRequest(){
	$("#signUpBtn").on("click", function() {
		var formData = $("#signUp-form").serialize();
		
		var Data = {"user_id" : $("#user_id").val(), 
					"password" : $("#password").val(), 
					"name" : $("#name").val(),
					"email" : $("#email").val(), 
					"tel" : $("#tel").val(), 
					"authorities" : $("#authorities_box").val(), 
					"login_count" : $("#login_count").val(),
					"enabled" : $("#enabled").val(),
					"gender" : $("#gender").val(),
					"login_count" : $("#login_count").val()
					};
		
		$.ajax({
			url : "/signUp.do",
			data : Data,
			dataType : "JSON",
			type : "POST",
			async:false,
			success : function(result) {
				alert("회원가입 완료");
				location.href = "/login.do"
			},
			error : function() {
				alert("신규등록 error");
			}
		})
	})
}


function cancel() {
	$("#cancelBackBtn").on("click", function(){
		history.back();	
	})
}

function fn_idCheck(){
	
	if($.trim($("#user_id").val())==""){
		alert("아이디를 입력해주세요");
		$("#user_id").focus();
		return;
	}
	if($.isUserID($.trim($("#user_id").val()))==false){
		alert("아이디는 5~12자의 영문소문자, 숫자만 가능합니다.");
		$("#user_id").focus();
		return;
	}
	
	var param = "?user_id=" + $.trim($("#user_id").val());
	
	$.ajax({
		type : "GET",
		url : "/idCheck.do"+param,
		dataType : "text",
		async : false,
		success : function(res){
			if($.trim(res)=="Y"){
				alert("사용가능한 아이디입니다.");
				$("#checkId").val($.trim($("#user_id").val()));
				return;
			}else{
				alert("이미 등록된 아이디 입니다.");
				$("#checkId").val("");
				return;
			}
		},
		error : function(){
			alert("ID중복체크 실패");
			return;
		}
	});
}