package co.kr.security.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.kr.security.config.CustomUserDetailsService;

@Controller
public class SecurityController {
	
	@Resource(name="customDetailsService")
	private CustomUserDetailsService customService;

	@RequestMapping(value="/login.do")
	public String login(){
		
		return "/board/loginPage";
	}
	
	@RequestMapping(value="/signUpPage.do")
	public String signUpPage() {
		return "/board/signUpPage";
	}
	
	@RequestMapping(value="/signUp.do", method = RequestMethod.POST)
	public ModelAndView signUp(@RequestParam HashMap<String, Object>params) {
		ModelAndView json = new ModelAndView("jsonView");
		
		customService.signUp(params);
		
		return json;
	}
	
	@RequestMapping(value="/error.do")
	public String errorPage(){
		return "/board/errorPage";
	}
	
	//id중복체크
//	@RequestMapping(value="idCheck.do")
//	@ResponseBody
//	public void idCehck(Model model, HttpServletRequest request, HttpServletResponse response){
//		HashMap<String, Object> params = new HashMap<>();
//		params.put("user_id", request.getParameter("user_id"));
//		
//	}
	
//	@RequestMapping(value="/cmmn/member/idCheck.do")	
//	@ResponseBody 
//	public void idCheck(ModelMap model, HttpServletRequest request,HttpServletResponse response)  {
//		HashMap<String, Object> params = new HashMap<String, Object>();
//		params.put("user_id", )
//	}
	
}
