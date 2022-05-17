$(function() {
	cancel();
	
	$("#signUpBtn").on("click", function() {
		var formData = $("#signUp-form").serialize();
		
		$.ajax({
			url : "/signUp.do",
			data : formData,
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
})


function cancel() {
	$("#cancelBackBtn").on("click", function(){
		history.back();	
	})
}