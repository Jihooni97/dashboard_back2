package co.kr.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserVO implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	//아이디
	private String userId;
	//비밀번호
	private String password;
	//사용자 이름
	private String name;
	//이메일
	private String email;
	//권한
	private String authorities;
	//전화번호
	private String tel;
	private int login_count;
	private boolean enabled;
	
	@Override
	public String getUsername() {
		return userId;
	}
	@Override
	public String getPassword(){
		return password;
	}
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(authorities));
		return auth;
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
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getLogin_count() {
		return login_count;
	}
	public void setLogin_count(int login_count) {
		this.login_count = login_count;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public UserVO() {
		super();
	}
	
}
