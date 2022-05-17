  package co.kr.board.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.kr.board.service.BoardService;
import co.kr.board.vo.ExcelVO;
import co.kr.security.UserVO;

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
		String local = (String)param.get("local");
		int count = boardService.count(param);
		//잘라서 보여줄 시작부분
		int offset = (nowPage - 1) * 5;
		int limit = 5;
		
		//리스트
		List<HashMap<String, Object>> selectList = boardService.selectList(offset, limit, local);
//		List<HashMap<String, Object>> list = boardService.paging(offset, limit);
		json.addObject("local", local);
		json.addObject("selectList", selectList);
		json.addObject("count", count);
		
		return json;
	}
	
	@RequestMapping(value = "/kriging.do")
	public String kriging(){
		
		return "/board/kriging";
	}
	
	//미사용
	@RequestMapping(value="/excelDown2.do")
	public ModelAndView excelDown2(HttpServletResponse response, @RequestParam HashMap<String, Object>param) throws IOException{
		ModelAndView json = new ModelAndView("jsonView");
		
		String local = (String)param.get("local");
		
		List<ExcelVO> list = boardService.allSelectList(local);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("sheet");
		
		XSSFRow row = null;
		XSSFCell cell = null;
		int rowNum = 0;
		
		row = sheet.createRow(rowNum++);
		
		cell = row.createCell(0);
		cell.setCellValue("site_code");
		
		cell = row.createCell(1);
		cell.setCellValue("날짜");
		
		cell = row.createCell(2);
		cell.setCellValue("수심(gl)");
		
		cell = row.createCell(3);
		cell.setCellValue("전기전도도(ec)");
		
		cell = row.createCell(4);
		cell.setCellValue("수온(temp)");
		
		for(ExcelVO vo : list){
			row = sheet.createRow(rowNum++);
			
			cell = row.createCell(0);
			cell.setCellValue(vo.getSite_code());
			
			cell = row.createCell(1);
			cell.setCellValue(vo.getData_time());
			
			cell = row.createCell(2);
			cell.setCellValue(vo.getGl());
			
			cell = row.createCell(3);
			cell.setCellValue(vo.getEc());
			
			cell = row.createCell(4);
			cell.setCellValue(vo.getTemp());	
		}
		
		String fileName = "gw_data_value_org.xlsx";
		String encordedFilename = URLEncoder.encode(fileName, "UTF-8");
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=" + encordedFilename);
		
		workbook.write(response.getOutputStream());
		workbook.close();
		
		return json;
		
	}
	
	//excel download
	@RequestMapping(value = "/excelDown.do")
	public void excelDown(HttpServletResponse response, @RequestParam HashMap<String, Object>param, HttpServletRequest request) throws IOException{
		

//		String gg = request.getParameter("local"); 
		String local = (String)param.get("local");
 
		List<ExcelVO> list = boardService.allSelectList(local);
//		List<ExcelVO> list = boardService.allSelectList();
		
//		List<HashMap<String, Object>> list = boardService.allSelectList(local)
		
		XSSFWorkbook workbook = new XSSFWorkbook(); //Excel workbook 생성
		XSSFSheet sheet = workbook.createSheet("sheet"); //sheet 생성
		System.out.println(workbook);
		//sheet -> row -> cell 순서로 접근하여 값 입력
		XSSFRow row = null;
		XSSFCell cell = null;
		int rowNum = 0;
		
		//스타일
//		XSSFCellStyle headStyle = workbook.createCellStyle();
//		headStyle.setBorderRight();
		
		//헤더 생성
		row = sheet.createRow(rowNum++);
		
		cell = row.createCell(0);
		cell.setCellValue("site_code");
		
		cell = row.createCell(1);
		cell.setCellValue("날짜");
		
		cell = row.createCell(2);
		cell.setCellValue("수심(gl)");
		
		cell = row.createCell(3);
		cell.setCellValue("전기전도도(ec)");
		
		cell = row.createCell(4);
		cell.setCellValue("수온(temp)");
		
		//반복문으로 데이터 뽑아오기
		for(ExcelVO vo : list){
			row = sheet.createRow(rowNum++);
			
			cell = row.createCell(0);
			cell.setCellValue(vo.getSite_code());
			
			cell = row.createCell(1);
			cell.setCellValue(vo.getData_time());
			
			cell = row.createCell(2);
			cell.setCellValue(vo.getGl());
			
			cell = row.createCell(3);
			cell.setCellValue(vo.getEc());
			
			cell = row.createCell(4);
			cell.setCellValue(vo.getTemp());
			
		}
		
		//파일명
		String fileName = "gw_data_value_org.xlsx";
		//인코딩
		String encordedFilename = URLEncoder.encode(fileName, "UTF-8");
		
		//컨텐츠 타입과 파일명 설정
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=" + encordedFilename);
		
		//엑셀 출력
		workbook.write(response.getOutputStream());
		workbook.close();
	}
	
	@RequestMapping(value="/login.do")
	public String login(){
		
		return "/board/loginPage";
	}
	
	@RequestMapping(value="/signUpPage.do")
	public String signUpPage() {
		return "/board/signUpPage";
	}
	
	@RequestMapping(value="/signUp.do", method = RequestMethod.POST)
	public String signUp(@RequestParam HashMap<String, Object>param) {
		boardService.user_signUp(param);
		
		return "/board/loginPage";
	}
	
	@RequestMapping(value="/error.do")
	public String errorPage(){
		return "/board/errorPage";
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