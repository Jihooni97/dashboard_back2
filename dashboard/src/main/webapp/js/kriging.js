var apikey = '83B7B5FB-6EDC-3BA5-B947-57B80DB11FDA';
var url = 'http://api.vworld.kr/req/wmts/1.0.0/' + apikey + '/Base/{z}/{y}/{x}.png';
var olCenter = new ol.proj.fromLonLat([ 127.027583, 37.497928 ]); //좌표 변환
//var map;
var colorGreen = 'rgba(51, 255, 51)';

$(function(){
	map();
})

function map() {
	var x = [
		14289539.88, 14289633.01, 14289713.44, 14289812.93, 14289912.41,
		14289598.08, 14289698.63, 14289809.75, 14289883.83, 14289952.62,
		14289747.31, 14289760.01, 14289779.06, 14289793.88, 14289815.04,
		14289836.21, 14289864.78, 14289920.87, 14289698.63, 14289665.82,
		14289758.95, 14289697.57, 14289771.65, 14289732.49, 14289804.46,
		14289828.8, 14289839.38, 14289853.14, 14289885.95, 14289853.14,
		14289878.54, 14289876.42, 14289942.04, 14289929.34, 14289912.41
		];
	
	var y = [
		4437041.79, 4437076.72, 4437109.52, 4437148.68, 4437185.72,
		4436892.57, 4436923.26, 4436970.88, 4436999.46, 4436999.46,
		4436877.75, 4436834.36, 4436785.67, 4436736.99, 4436681.96,
		4436626.92, 4436563.42, 4436512.62, 4436824.83, 4436761.33,
		4436740.17, 4436667.14, 4436638.57, 4436577.18, 4436553.90,
		4436927.49, 4436879.87, 4436834.36, 4436790.97, 4436723.23,
		4436687.25, 4436629.04, 4436890.45, 4436744.40, 4436600.47
		];
	var t = new Array;
	//new ol.proj.fromLonLat();
	//ol.proj.transform() 'EPSG:4326', 'EPSG:3857' //vworld map 좌표 EPSG:3857
	
	var canvas = document.createElement('canvas');
	canvas.width = canvas
	
	var map = new ol.Map({
		target : "vmap",
		layers : [ new ol.layer.Tile({
			source : new ol.source.XYZ({
				url : url
			})
		})],
		view : new ol.View({
			center : olCenter,
			zoom : 13
		})
	});
	
	var imageSource = new ol.source.Image({
		
	})
	
	var imageLayer = new ol.layer.Image({
		source : imageSource
	})
	
//	var feature = new ol.Feature({
//		geometry : new ol.geom.Point()
//	})
//	
//	var style = new ol.style.Style({
//		
//	})
}