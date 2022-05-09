package co.kr.board.service;

import java.util.HashMap;
import java.util.List;

public interface BoardService {

	public List<HashMap<String, Object>> selectChart();
	
	public List<HashMap<String, Object>> selectList(int offset, int limit);

	public int count(HashMap<String, Object> param);

//	public List<HashMap<String, Object>> paging(int offset, int limit);


}
