<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DashBoard</title>

<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- openlayer -->
<script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.13.0/build/ol.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.14.1/css/ol.css" type="text/css">
<!-- bootstrap -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
<!-- chart.js -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.2.1/Chart.bundle.js"></script>
<!-- weather -->
<link rel="stylesheet" type="text/css" href="/css/weather-icons-master/css/weather-icons.min.css" />
<!-- Pointer events polyfill for old browsers, see https://caniuse.com/#feat=pointer -->
<script src="https://unpkg.com/elm-pep@1.0.6/dist/elm-pep.js"></script>
<!-- The lines below are only needed for old environments like Internet Explorer and Android 4.x -->
<script src="https://cdn.polyfill.io/v3/polyfill.min.js?features=fetch,requestAnimationFrame,Element.prototype.classList,TextDecoder"></script>

<link rel="stylesheet" type="text/css" href="/css/dashboard.css" />
<script type="text/javascript" src="/js/map.js"></script>
<script type="text/javascript" src="/js/weather.js"></script>
<script type="text/javascript" src="/js/chart.js"></script>
<!-- <script type="text/javascript" src="/js/list.js"></script> -->
<script type="text/javascript" src="/js/list_test.js"></script>
</head>

<body>
	<div id="container">

		<!-- 상단 -->
		<div id="top">

			<div id="top_contents">
				<!-- 날씨 -->
				<div id="weather"></div>
				<!-- 온도 -->
				<div id="temp"></div>
				<!-- 시간 -->
				<div id="time">
					<h4 id="clock"></h4>
				</div>
				<!-- 제목 -->
				<div id="board">
					<h1>DashBoard</h1>
				</div>

			</div>

		</div>

		<div id="boardBody">

			<!-- 맵 -->
			<div id="mapBoard">
				<!-- map -->
				<div id="vmap"></div>

				<div style="display: none;">
					<!-- overlay -->
					<a class="overlay" id="vienna" target="_blank"
						href="https://www.google.co.kr/maps/search/%EA%B0%95%EB%82%A8%EC%97%AD/data=!3m1!4b1?hl=ko">강남역</a>
					<div id="marker" title="Marker"></div>
					<div id="popup" title="Welcome to OpenLayers"></div>
				</div>

				<!-- 지역 -->
				<div id="localSelect">
					<select id="local">
						<option value="KB001">KB001</option>
						<option value="KB002">KB002</option>
						<option value="KB003">KB003</option>
						<option value="KB004">KB004</option>
						<option value="KB005">KB005</option>
					</select>
				</div>
				
				<!-- polygon -->
				<div class="right">
					<form class="form-inline">
						<!-- <label for="type">Geometry type: &nbsp;</label> -->
						<select class="form-control mr-2 mb-2 mt-2" id="type"
							style="margin-left: 10px;">
							<option value="None">None</option>
							<option value="Point">Point</option>
							<!-- <option value="LineString">LineString</option> -->
							<option value="Polygon">Polygon</option>
							<option value="Circle">Circle</option>
							<!-- <option value="Marker">Marker</option> -->
						</select>
					</form>
				</div>

				<!-- mapType change -->
				<select id="layer-select">
					<option value="Base">일반</option>
					<option value="Satellite">위성</option>
					<option value="Hybrid">하이브리드</option>
				</select>
				<button id="exportBtn">getGeoJSON</button>
				<button id="createMaker">마커 생성</button>
				<button id="remove">마커 지우기</button>
				<button id="remove_all">모든 마커 지우기</button>
			</div>

			<!-- 리스트 -->
			<div id="selectList">
				<table class="table" id="listTable">
					<tr>
						<td>site_code</td>
						<td>날짜</td>
						<td>수심(gl)</td>
						<td>전기전도도(ec)</td>
						<td>수온(temp)</td>
					</tr>
					<tbody id="table"></tbody>
				</table>
				<div id="pageBtn"></div>
			</div>
			
			<!-- excel -->
			<div id="excel">
<!-- 				<a href="/excelDown.do"><button id="excelDownload">엑셀변환</button></a> -->
				<button id="excelDown" onclick="excel()">엑셀변환</button>
			</div>

		</div>

		<!-- 왼쪽 -->
		<div id="leftBoard">

			<!-- 그래프 -->
			<div id="graph">
				<canvas id="chart1"></canvas>
			</div>

			<!-- 그래프 -->
			<div id="graph">
				<canvas id="chart2"></canvas>
			</div>

			<!-- 그래프 -->
			<div id="graph">
				<canvas id="chart3"></canvas>
			</div>

		</div>

		<!-- 오른쪽 -->
		<div id="rightBoard"></div>

	</div>
</body>
</html>