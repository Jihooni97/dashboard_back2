package co.kr.board.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.board.service.BoardService;
import co.kr.security.UserVO;
import co.kr.board.vo.ExcelVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Resource(name="boardMapper")
	private BoardMapper boardMapper;

	@Override
	public List<HashMap<String, Object>> selectChart() {
		return boardMapper.selectChart();
	}

	@Override
	public List<HashMap<String, Object>> selectList(int offset, int limit, String local) {
		HashMap<String, Object>page = new HashMap<>();
		page.put("local", local);
		page.put("offset", offset);
		page.put("limit", limit);
		return boardMapper.selectList(page);
	}

	@Override
	public int count(HashMap<String, Object> param) {
		return boardMapper.count(param);
	}

	@Override
	public List<ExcelVO> allSelectList(String local) {
		return boardMapper.allSelectList(local);
	}
//	@Override
//	public List<ExcelVO> allSelectList() {
//		return boardMapper.allSelectList();
//	}

	@Override
	public UserVO findOne(String userId) {
		return boardMapper.findOne(userId);
	}

	@Override
	public void user_signUp(HashMap<String, Object> param) {
		boardMapper.user_signUp(param);		
	}

//	@Override
//	public List<HashMap<String, Object>> allSelectList(String local) {
//		HashMap<String, Object> param = new HashMap<>();
//		param.put("local", local);
//		return boardMapper.allSelectList(param);
//	}

}
