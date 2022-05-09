  package co.kr.board.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Select;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.kr.board.service.BoardService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class BoardController {
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@RequestMapping(value = "/main.do")
	public String mainPage(){
		return "/board/main";
	}
	
	@RequestMapping(value = "/chart.do" , method = RequestMethod.POST)
	public ModelAndView chart(){
		ModelAndView json = new ModelAndView("jsonView");

		List<HashMap<String, Object>> chartPage = boardService.selectChart();
		json.addObject("resultData", chartPage);
	
		return json;
	} 

	@RequestMapping(value = "/selectList.do", method = RequestMethod.POST)
	public ModelAndView selectList(@RequestParam HashMap<String, Object>param){
		ModelAndView json = new ModelAndView("jsonView");
		
		
		//페이징
		int nowPage = Integer.parseInt(param.get("nowPage").toString());
		int count = boardService.count(param);
		//잘라서 보여줄 시작부분
		int offset = (nowPage - 1) * 5;
		int limit = 5;
		
		//리스트
		List<HashMap<String, Object>> selectList = boardService.selectList(offset, limit);
//		List<HashMap<String, Object>> list = boardService.paging(offset, limit);
		json.addObject("selectList", selectList);
		json.addObject("count", count);
		
		return json;
	}
	
//	@RequestMapping(value = "/paging.do", method = RequestMethod.POST)
//	public ModelAndView paging(@RequestParam HashMap<String, Object>param){
//		ModelAndView json = new ModelAndView("jsonView");
//		
//		int nowPage = Integer.parseInt(param.get("nowPage").toString());
//		int count = boardService.count(param);
//		int offset = (nowPage - 1) * 5;
//		List<HashMap<String, Object>> list = boardService.paging(offset);
//		json.addObject("list", list);
//		json.addObject("count", count);
//		
//		return json;
//	}	
	
	@RequestMapping(value = "/kriging.do")
	public String kriging(){
		
		return "/board/kriging";
	}
	
	   //기상청 API 데이터
/*	   @RequestMapping(value = "/data2.do", method = RequestMethod.GET)
	   public ModelAndView data2(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> params) throws IOException, org.json.simple.parser.ParseException {
	      ModelAndView jsonView = new ModelAndView("jsonView");
	      
	      //API통신위해 필요한 자료들
	      String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
	      String serviceKey = "rHX4%2B4I4xWtQqhajT38gBBHen%2Fbgnl6BzDH%2BAFOUZaS%2B02PMAS%2BZQuMwtWzDMMjc%2F%2FBCRBEU1o3viDjh37frVQ%3D%3D";
//	      String pageNo = "1";
	      String numOfRows = "100";
	      String dataType = "json";
	      String base_date = "20220413";
	      String base_time = "1700";
	      String nx = "55";
	      String ny = "127";
	      
	      //API URL 조합
	      StringBuilder urlBuilder = new StringBuilder(apiUrl);
	      urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey); //URLEncoder 인코딩
//	      urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));  
	      urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));  
	      urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));   
	      urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8"));
	      urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8"));
	      urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8"));
	      urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8"));
	      URL url = new URL(urlBuilder.toString());
	      System.out.println(url);
	      
	      //GET방식으로 통신, 데이터타입은 JSON
	      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	      connection.setRequestMethod("GET");
	      connection.setRequestProperty("Content-type", "application/json");
	      
	      BufferedReader reader;
	      
	      if (connection.getResponseCode() >= 200 && connection.getResponseCode() <=300) {
//	         System.out.println("response 코드가 200이상~300이하일때");
	         reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	      }else{ 
//	         System.out.println("아닐때");
	         reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	      }
	      
	      StringBuilder SB = new StringBuilder();
	      String line;
	      
	      while ((line = reader.readLine()) != null) {
	         SB.append(line);
	      }
	      reader.close();
	      connection.disconnect();
	      String data = SB.toString();
	      System.out.println(data);
	      
	      JSONParser parser = new JSONParser();
	      org.json.simple.JSONObject obj = (org.json.simple.JSONObject) parser.parse(data);
	      //System.out.println(obj.toJSONString());
	      org.json.simple.JSONObject parse_response = (org.json.simple.JSONObject) obj.get("response");
	      org.json.simple.JSONObject parse_body = (org.json.simple.JSONObject) parse_response.get("body");
	      org.json.simple.JSONObject parse_items = (org.json.simple.JSONObject) parse_body.get("items");
	      org.json.simple.JSONArray parse_item = (org.json.simple.JSONArray) parse_items.get("item");
	      
	      int dataSize = parse_item.size();
	      
	      jsonView.addObject("dataSize", dataSize);
	      jsonView.addObject("data", parse_items);
	      return jsonView;
	   }*/
}