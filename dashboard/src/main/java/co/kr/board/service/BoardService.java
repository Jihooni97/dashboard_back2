package co.kr.board.service;

import java.util.HashMap;
import java.util.List;

import co.kr.board.vo.ExcelVO;
import co.kr.security.UserVO;

public interface BoardService {

	public List<HashMap<String, Object>> selectChart();
	
	public List<HashMap<String, Object>> selectList(int offset, int limit, String local);

	public int count(HashMap<String, Object> param);

	public List<ExcelVO> allSelectList(String local);
//	public List<ExcelVO> allSelectList();
	
	//로그인
	public UserVO findOne(String userId);

	public void user_signUp(HashMap<String, Object> param);

//	public List<HashMap<String, Object>> allSelectList(String local);

}
