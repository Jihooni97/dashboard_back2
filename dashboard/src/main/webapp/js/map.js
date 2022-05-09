/**
 * 22.04.01 시작
 * 사용한 버전
 * jquery-3.6.0.min.js 
 * v6.13.0/build/ol.js
 */

var apikey = '83B7B5FB-6EDC-3BA5-B947-57B80DB11FDA';
var olCenter = new ol.proj.fromLonLat([ 127.027583, 37.497928 ]); // 좌표 변환
var base_url = new ol.source.XYZ({ url : 'http://api.vworld.kr/req/wmts/1.0.0/83B7B5FB-6EDC-3BA5-B947-57B80DB11FDA/Base/{z}/{y}/{x}.png'});
var satellite_url = new ol.source.XYZ({ url : 'http://api.vworld.kr/req/wmts/1.0.0/83B7B5FB-6EDC-3BA5-B947-57B80DB11FDA/Satellite/{z}/{y}/{x}.jpeg'});
var hybrid_url = new ol.source.XYZ({ url : 'http://api.vworld.kr/req/wmts/1.0.0/83B7B5FB-6EDC-3BA5-B947-57B80DB11FDA/Hybrid/{z}/{y}/{x}.png'});

//var base_url = new ol.source.XYZ({url: 'http://xdworld.vworld.kr:8080/2d/Base/service/{z}/{x}/{y}.png'});
//var satellite_url = new ol.source.XYZ({url: 'http://xdworld.vworld.kr:8080/2d/Satellite/service/{z}/{x}/{y}.jpeg'});
//var hybrid_url = new ol.source.XYZ({url: 'http://xdworld.vworld.kr:8080/2d/Hybrid/service/{z}/{x}/{y}.png'});

var map;
var draw;
var source = new ol.source.Vector({ wrapX : false });
var vector = new ol.layer.Vector({ source : source }); //draw

// 좌표
// vmap좌표계로 변환 new ol.proj.fromLonLat();
var position = new Array([14142266.321026677, 4508278.219870723],
		[14141731.26182868, 4508981.440880848], [14140554.132059623, 4507926.609657245],
		[14140095.509889912, 4508675.692767707], [14140814.017489258, 4509302.476166378]);
var vectorLayer;
var width = 3;

//tile
var Base = new ol.layer.Tile({ source : base_url })
var Satellite = new ol.layer.Tile({ source : satellite_url })
var Hybrid = new ol.layer.Tile({ source : hybrid_url })

//style
var style = new ol.style.Style({
	image : new ol.style.Circle({
		radius : width * 2,
		fill : new ol.style.Fill({
			color : 'rgba(51, 255, 51)',
		})
	})
})

//window.onload = function()
$(function() {
	init_map();
	createMaker();
	draw_features();
//	addInteraction();
	clickFeature();
	overlay();
	GeoJSON();
	remove();
	remove_all();
})


//맵생성
function init_map() {
	var vectorSource = new ol.source.Vector({});
	vectorLayer = new ol.layer.Vector({
		source : vectorSource
	})

	map = new ol.Map({
		target : "vmap",
		layers : [ Base, vector ],
		view : new ol.View({
			center : olCenter,
			zoom : 13
		})
	})
	map.getView().setMinZoom(6);
	
	var mapTypeSelect = document.getElementById('layer-select');
	mapTypeSelect.onchange = function(){
		var mapType = mapTypeSelect.value;
		mapChange(mapType);
	}
}

/* 지도유형 변경 */
function mapChange(option) {
	map.removeLayer(Hybrid);
	switch (option) {
	case 'Base':
		map.getLayers().getArray()[0].A.source = base_url;
		break;
	case 'Satellite':
		map.getLayers().getArray()[0].A.source = satellite_url;
		break;
	case 'Hybrid':
		map.getLayers().getArray()[0].A.source = satellite_url;
		map.addLayer(Hybrid);
		break;
	}
	map.render(); // 렌더링(새로고침)
}

// overlay
function overlay(){
	
//	const pos = new ol.proj.fromLonLat([16.3725, 48.208889]);

	//**********************************************//
	//vienna 마커생성(href)
	var marker = new ol.Overlay({
		positon : olCenter,
//		positioning : 'olCenter-olCenter',
		element : document.getElementById('marker'),
		stopEvent : false,
	});
	map.addOverlay(marker);
	
	//Vienna 라벨
	var vienna = new ol.Overlay({
		position : olCenter,
		element : document.getElementById('vienna'),
	});
	map.addOverlay(vienna);
	//**********************************************//
}

//Feature 클릭 시 팝업창 띄우기
function clickFeature(){
	//팝업
	var popup = new ol.Overlay({
		element : document.getElementById('popup'),
	});
	map.addOverlay(popup);
	
	//맵 클릭시 팝업창 띄우기
	map.on('click', function(e){
		
		//클릭한 Feature 가져오기
		var iconFeature = map.getFeaturesAtPixel(e.pixel);
		
		if(iconFeature.length != 0){ //Feature가 있다면
			var element = popup.getElement(); //div id #popup 오버레이의 DOM요소를 가져옴
			var coordinate = e.coordinate; //클릭한 좌표 가져오기
			//toStringHDMS 반구, 도, 분, 초를 사용하여 지리 좌표의 형식 지정
			//toLonLat 좌표 경도/위도로 변환
			var hdms = new ol.coordinate.toStringHDMS(new ol.proj.toLonLat(coordinate));
			$(element).popover('dispose'); //bootstrap (요소의 팝오버 파괴)
			popup.setPosition(coordinate); //클릭한 좌표에 오버레이 위치 설정
			$(element).popover({  //팝오버 활성화
				container : element, //사용자 정의 지정
				placement : 'top', //팝오버 위치
				animation : false, //css페이드 전환 적용 여부
				html : true, //팝오버에 html 삽입. false인 경우 jq의 text메서드를 사용하여 DOM에 컨텐츠 삽입
				content : '<p>The location you clicked was</p><code>' + hdms + '</code>',
			});
			$(element).popover('show'); //요소의 팝오버를 표시
		}
	})
}

//팝업창 띄우기(미사용)
function clickFeature2(){
	//팝업
	var popup = new ol.Overlay({
		element : document.getElementById('popup'),
	});
	map.addOverlay(popup);
	
	//맵 클릭시 팝업창 띄우기
	map.on('click', function(e){
		var element = popup.getElement(); //div id #popup 오버레이의 DOM요소를 가져옴
		var coordinate = e.coordinate; //클릭한 좌표 가져오기
		
		//toStringHDMS 반구, 도, 분, 초를 사용하여 지리 좌표의 형식 지정
		//toLonLat 좌표 경도/위도로 변환
		var hdms = new ol.coordinate.toStringHDMS(new ol.proj.toLonLat(coordinate));
		
		$(element).popover('dispose'); //bootstrap (요소의 팝오버 파괴)
		popup.setPosition(coordinate); //클릭한 좌표에 오버레이 위치 설정
		$(element).popover({  //팝오버 활성화
			container : element, //사용자 정의 지정
			placement : 'top', //팝오버 위치
			animation : false, //css페이드 전환 적용 여부
			html : true, //팝오버에 html 삽입. false인 경우 jq의 text메서드를 사용하여 DOM에 컨텐츠 삽입
			content : '<p>The location you clicked was</p><code>' + hdms + '</code>',
		});
		$(element).popover('show'); //요소의 팝오버를 표시
	})
}


function draw_features(){
	// select option type 선택
	var typeSelect = document.getElementById('type');

	function addInteraction() {
		var value = typeSelect.value;
		if (value !== 'None') {	
			draw = new ol.interaction.Draw({
				source : source,
				type : value
			});
			map.addInteraction(draw);
		}
// 		else {
//			clickMaker();
//		}
	}
	
	/*change 이벤트*/
	typeSelect.onchange = function(){
		map.removeInteraction(draw);
		addInteraction();
	}
//	document.getElementById('undo').addEventListener('click', function(){
//		draw.removeLastPoint();
//	});
	addInteraction();
	map.render();
}

// 지도에 마우스로 클릭시 해당 위치에 마커 생성
function clickMaker() {
	map.on('click', function(e) {

		var coordinate = e.coordinate;
		var vectorSource = new ol.source.Vector({ wrapX : false });
		var feature = new ol.Feature({
			geometry : new ol.geom.Point([ coordinate[0], coordinate[1] ]),
		})
		feature.setStyle(style);
		vectorSource.addFeature(feature);
		vectorLayer = new ol.layer.Vector({
			source : vectorSource,
		})
		// console.log(coordinate);
		map.addLayer(vectorLayer);
	})

}

//Feature 정보 추출하기
function GeoJSON() {
	$("#exportBtn").click(
			function() {
				var json = new ol.format.GeoJSON().writeFeatures(vector.getSource().getFeatures());
				console.log(json);
			})
}

// 버튼 클릭시 마커 생성
function createMaker() {
	$("#createMaker").on("click", function() {

		var vectorSource = new ol.source.Vector({});
		for (var i = 0; i < position.length; i++) {
			var feature = new ol.Feature({
				geometry : new ol.geom.Point(position[i])
			})

			feature.setStyle(style);
			vectorSource.addFeature(feature);
		}

		vectorLayer = new ol.layer.Vector({
			source : vectorSource
		})

		map.addLayer(vectorLayer);
	})
}

//마커 1개씩 지우기
function remove(){
	$("#remove").on("click", function(){
		map.removeLayer(map.getAllLayers()[1])
	})
}

// 모든 마커 지우기
function remove_all() {
	$("#remove_all").on("click", function() {
		var length = map.getAllLayers().length;
		for (var i = 0; i < length; i++) {
			map.removeLayer(map.getAllLayers()[1])
		}
	})
}