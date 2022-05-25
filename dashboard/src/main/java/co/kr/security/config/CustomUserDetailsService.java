package co.kr.security.config;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.kr.security.UserVO;
import co.kr.security.service.impl.SecurityMapper;

@Service("customDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Resource(name="securityMapper")
	private SecurityMapper securityMapper;
	
	@SuppressWarnings("deprecation")
	@Autowired
	public PasswordEncoder incoder;
	
	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		
		UserVO vo = securityMapper.login(user_id);
//		System.out.println(vo);
		
		if(vo==null){
			throw new UsernameNotFoundException((user_id) + "없는 아이디");
		}
		return vo;
	}

	//회원가입
	public void signUp(HashMap<String, Object>params) throws UsernameNotFoundException{

		//비밀번호 암호화
		String encodePassword = incoder.encodePassword((String)params.get("password"), (String)params.get("user_id"));
//		params.put("login_count", login_count);
		Integer enabled = Integer.parseInt((String) params.get("enabled"));
		System.out.println(enabled.getClass().getName());
		Integer login_count = Integer.parseInt((String)params.get("login_count"));
		params.put("login_count", login_count);
		params.put("enabled", enabled);
		params.put("encode_password", encodePassword);
		
		securityMapper.user_signUp(params);
	}

}