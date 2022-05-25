$(function(){
	list(1);
})

function list(page){
	var data = {"nowPage" : page, "local" : $("[name=local]").val()};
	
	$.ajax({
		url : "/selectList.do",
		type : "POST",
		data : data,
		dataType : "JSON",
		cache : false,
		success : function(result) {
			$("#table").empty();
			$("#pageBtn").empty();
			
//			for(var i = 0; i < result.selectList.length; i++){
//				var year = result.selectList[i].data_time.substr(2,2);
//				var month = result.selectList[i].data_time.substr(4,2);
//				var day = result.selectList[i].data_time.substr(6,2);
//				var hour = result.selectList[i].data_time.substr(8,2);
//				var minute = result.selectList[i].data_time.substr(10,2);
//				var second = result.selectList[i].data_time.substr(12,2);
//			}
			
			
			
			//paging	
			var totalPage = Math.ceil(result.count/5.0); // 전체 데이터 5개씩 끊어서 페이지 수 구하기
			var pageBlock = Math.ceil(totalPage/5.0); // 한블럭당 5페이지씩 보여지도록
			var nowPageBlock = Math.ceil(page/5.0); // 현재 페이지블럭
			var endPageNumber = nowPageBlock * 5 // 현재 페이지 끝번호 (5페이지로 나누니까 5)
			var startPageNumber = endPageNumber - 4; // 현재 페이지 시작번호(1)
			
			if(totalPage < endPageNumber){ // 공백 페이지번호 지우기
				endPage = totalPage;
			}
			
			for(var i = 0; i < result.selectList.length; i++){
				
				var year = result.selectList[i].data_time.substr(2,2);
				var month = result.selectList[i].data_time.substr(4,2);
				var day = result.selectList[i].data_time.substr(6,2);
				var hour = result.selectList[i].data_time.substr(8,2);
				var minute = result.selectList[i].data_time.substr(10,2);
				var second = result.selectList[i].data_time.substr(12,2);
				
				var html = '<tr>';
				html += '<td>' + result.selectList[i].site_code + '</td>';
				html += '<td>' + year + "년" + month + "월" + day + "일" + hour + "시" + '</td>';
				html += '<td>' + result.selectList[i].gl + '</td>';
				html += '<td>' + result.selectList[i].ec + '</td>';
				html += '<td>' + result.selectList[i].temp + '</td>';
				html += '<td>' + '</td>';
				html += '</tr>';
				$('#table').append(html);
			}
			
			// 뒤로가기버튼
			if(page>1){
				var prevBtn = "<button onclick='list(" + (page-1) + ")'> 이전 </button>" 
				$("#pageBtn").append(prevBtn);
			}	
			
			// 페이지 번호
			for(var i = startPageNumber; i <= endPageNumber; i++){
				 var pageBtn = "<button onclick='list("+i+")'>"+i+"</button>"
				$("#pageBtn").append(pageBtn);
			}
			
			// 다음 버튼
			if(page != totalPage){
				var nextBtn = "<button onclick='list(" + (page+1) + ")'> 다음 </button>" 
				$("#pageBtn").append(nextBtn);
			}
		}
	});
	
}

function reload(){
	var typeSelect = document.getElementById('local');
	
	typeSelect.onchange = function(){
		window.location.re
	}
}