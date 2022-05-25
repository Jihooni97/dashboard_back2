package co.kr.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * getAuthorities() 리턴타입 Collection<? extends GrantedAuthority> 계정의 권한 목록을 리턴
 * getPassword() 리턴타입 String 계정의 비밀번호를 리턴
 * getUsername() 리턴타입 String 계정의 고유한 값을 리턴(ex : DB PK값, 중복이 없는 이메일 값)
 * isAccountNonExpired() 리턴타입 boolean 계정의 만료 여부 리턴 (true 만료안됨)
 * isAccountNonLocked() 리턴타입 boolean 계정의 잠김 여부 리턴 (true 잠기지 않음)
 * isCredentialsNonExpired() 리턴타입 boolean 비밀번호 만료 여부 리턴 (true 만료안됨)
 * isEnabled() 리턴타입 boolean 계정의 활성화 여부 리턴 (true 활성화됨)
 * */

public class UserVO implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private String user_id;
	private String password;
	private String authorities;
	private String name;
	private boolean enabled;
//	private String tel;
//	private String email;
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(authorities));
		return auth;
	}
	@Override
	public String getUsername() {
		return user_id;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}	
	
	
}
