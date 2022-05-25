package co.kr.security.service.impl;

import java.util.HashMap;

import co.kr.security.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("securityMapper")
public interface SecurityMapper {
	
	public UserVO login(String user_id);

	public void resetFailureCount(String user_id);

	public void resetCnt(String user_id);

	public void user_signUp(HashMap<String, Object> params);

	public int exists(String user_id);

	public int selectCnt(Object uuser);

	public void lock(String user_id);

	public void plusCnt(String user_id);
	
}
