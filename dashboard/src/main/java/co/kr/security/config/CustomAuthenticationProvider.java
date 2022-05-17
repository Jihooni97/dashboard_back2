package co.kr.security.config;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
//	@Resource(name="customService")
//	private CustomService customService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}

}
