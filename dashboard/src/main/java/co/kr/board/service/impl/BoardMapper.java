package co.kr.board.service.impl;

import java.util.HashMap;
import java.util.List;

import co.kr.board.vo.ExcelVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("boardMapper")
public interface BoardMapper {
	
	public List<HashMap<String, Object>> selectChart();
	
	public List<HashMap<String, Object>> selectList(HashMap<String, Object> page);
	
	public int count(HashMap<String, Object> param);
	
	public List<ExcelVO> allSelectList(String local);
//	public List<ExcelVO> allSelectList();

//	public List<HashMap<String, Object>> allSelectList(HashMap<String, Object>param);
		
}
