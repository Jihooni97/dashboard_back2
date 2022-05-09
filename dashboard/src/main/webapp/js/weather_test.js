$(function(){
	getWeather();
	getClock();
	startClock();
})

function getWeather(){
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth()+1;
	var date = now.getDate();
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	var today = now.getDay();
	
	if (month < 10) {
		month = '0' + month;
	}else{
		month = "" + month;
	}
	
	if (date < 10) {
		date = '0' + date;
	}else{
		date = "" + date;
	}
	
	if (hours < 10) {
		hours = '0' + hours;
	}else{
		hours = "" + hours;
	}
	if (minutes < 10) {
		minutes = '0' + minutes;
	}else{
		minutes = "" + minutes;
	}
// var a = encodeURI('아아아');
	var tmFc = year + month + date + hours;
	var xhr = new XMLHttpRequest();
	var apiURL = 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst';
	var serviceKey = 'rHX4%2B4I4xWtQqhajT38gBBHen%2Fbgnl6BzDH%2BAFOUZaS%2B02PMAS%2BZQuMwtWzDMMjc%2F%2FBCRBEU1o3viDjh37frVQ%3D%3D';
	var queryParams = apiURL;
		queryParams += '?' + encodeURIComponent('serviceKey') + '=' + serviceKey; /*인증키*/
		queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /*한페이지 결과 수*/
		queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1') /*페이지 수*/
		queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('JSON'); /*응답자료형식 / XML/JSON*/
		queryParams += '&' + encodeURIComponent('stnId') + '=' + encodeURIComponent('108'); /**/
// queryParams += '&' + encodeURIComponent('tmFc') + '=' +
// encodeURIComponent('201310170600'); /**/
		queryParams += '&' + encodeURIComponent('tmFc') + '=' + encodeURIComponent(tmFc) +encodeURIComponent('00'); /**/
//		xhr.open('GET', url + queryParams);
//		xhr.onreadystatechange = function(){
//			if(this.readyState == 4){
//				alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
//			}
//		};
//		xhr.send();
//	var x = '60';
//	var y = '127';
//	var proxyUrl = '../proxy.jsp?resourceUrl=';
//	var weatherAPI = 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=' + key + '&numOfRows=10&pageNo=1&base_date=' + apiDay +'&base_time=' + apiTime +'&nx=' + x + '&ny=' + y;
		
	$.ajax({
		url : queryParams,
		type : 'GET',
		async : false,
		success : function(result){
			console.log(xhr);
		}
	})
}


function getClock(){
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth()+1;
	var date = now.getDate();
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	var today = now.getDay();
	
	var week = ['일', '월', '화', '수', '목', '금', '토'];
	var todayLabel = week[today];
	
	var clock = document.getElementById("clock");
//	if(hours < 10){
//		hours = '0' + hours;
//	}
//	if(minute < 10){
//		minute = '0' + minute;
//	}
	clock.innerText = year + "-" + month + "-" + date + "\u00A0" + todayLabel + "\n" + hours + ":" + minutes + ":" + seconds;
	
}

function startClock(){
	setInterval( getClock, 1000 );
}