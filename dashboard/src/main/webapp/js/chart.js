$(function(){
	selectChart();
})


var glData = [];
var tempData = [];
var ecData = [];
var chartLabels = [];
var mix= [];

function selectChart() {
	$.ajax({
		url : "/chart.do",
		type : "POST",
		dataType : "JSON",
		cache : false,
		success : function(result) {
			for (var i = 0; i < result.resultData.length; i++) {
				var year = result.resultData[i].data_time.substr(0,4);
				var month = result.resultData[i].data_time.substr(4,2);
				var day = result.resultData[i].data_time.substr(6,2);
				var hour = result.resultData[i].data_time.substr(8,2);
				var minute = result.resultData[i].data_time.substr(10,2);
				var second = result.resultData[i].data_time.substr(12,2);
//				var day2 = '0' + (day-1);

				glData.push(result.resultData[i].gl); //y축
				ecData.push(result.resultData[i].ec);
				tempData.push(result.resultData[i].temp);
//				chartLabels.push(year + "년" + month + "월" + day + "일" + hour + "시" + minute + "분" + second + "초"); //x축
				chartLabels.push(year + '-' + month + '-' + day + ' ' + hour);
			};
			
			createChart('chart1', glData, 'gl');
			createChart('chart2', ecData, 'ec');
			createChart('chart3', tempData, 'temp');
		}
	});
}

function createChart(chart_id, data, type) {
	var ctx = document.getElementById(chart_id);
	
	var dataSet = {
			labels : chartLabels,
			datasets : [ {
				label : type,
				backgroundColor : 'rgb(255, 99, 132)',
				borderColor : 'rgb(255, 99, 132)',
				borderWidth : 1,
				data : data,
				pointRadius : 1,
			} ]
	};

	var options = {
			scales : {
				xAxes : [{
					ticks : {
						fontSize : 10
					},
					type : 'time',
					time : {
						displayFormats: {
//							month : 'YYYY MM',
	                        day : 'MM월 DD일'
	                    }
					}
				}],
				
				yAxes : [{
					ticks : {
						fontSize : 10
					},
				}]
			}
	};
		
	var config = {
			type : 'line',
			data : dataSet,
			options : options,
	};
		
	var chart = new Chart(ctx, config);
};