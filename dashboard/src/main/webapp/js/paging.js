//$(function(){
//	boardList(1);
//	
//})
//
//function boardList(page){
//	var data = {"nowPage" : page}
//	
//	$.ajax({
//		url : '/paging.do',
//		type : "POST",
//		data : data,
//		dataType : 'JSON',
//		cache : false,
//		success : function(result){
//			
//			$("#table").empty();
//			$("#pageBtn").empty();
//			
//			var totalPage = Math.ceil(result.count/5); //전체 데이터 5개씩 끊어서 페이지 수 구하기
//			var pageBlock = Math.ceil(totalPage/5); //한블럭당 5페이지씩 보여지도록
//			var nowPageBlock = Math.ceil(page/5); //현재 페이지블럭
//			var endPageNumber = nowPageBlock * 5 //현재 페이지 끝번호 (5페이지로 나누니까 5)
//			var startPageNumber = endPageNumber - 4; //현재 페이지 시작번호(1)
//			
//			if(totalPage < endPageNumber){ // 공백 페이지번호 지우기
//				endPage = totalPage;
//			}
//			
//			//list 함수 호출(list.js)
//			list();
//			
//			//뒤로가기버튼
//			if(page>1){
//				var prevBtn = "<button onclick='boardList(" + (page-1) + ")'> 이전 </button>" 
//				$("#pageBtn").append(prevBtn);
//			}	
//			
//			//페이지 번호
//			for(var i = startPageNumber; i <= endPageNumber; i++){
//				 var pageBtn = "<button onclick='boardList("+i+")'>"+i+"</button>"
//				$("#pageBtn").append(pageBtn);
//			}
//			
//			//다음 버튼			
//			if(page != totalPage){
//				var nextBtn = "<button onclick='boardList(" + (page+1) + ")'> 다음 </button>" 
//				$("#pageBtn").append(nextBtn);
////				if(page == endPageNumber){
//					
////				}
//			}
//			
//		},
//		
//	})
//}