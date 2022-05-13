package co.kr.board.vo;

public class ExcelVO {
	public String site_code;
	public String data_time;
	public double gl;
	public double ec;
	public double temp;
	
	public String getSite_code() {
		return site_code;
	}
	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}
	public String getData_time() {
		return data_time;
	}
	public void setData_time(String data_time) {
		this.data_time = data_time;
	}
	public double getGl() {
		return gl;
	}
	public void setGl(double gl) {
		this.gl = gl;
	}
	public double getEc() {
		return ec;
	}
	public void setEc(double ec) {
		this.ec = ec;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public ExcelVO(String site_code, String data_time, double gl, double ec, double temp) {
		super();
		this.site_code = site_code;
		this.data_time = data_time;
		this.gl = gl;
		this.ec = ec;
		this.temp = temp;
	}
	
	
	
	@Override
	public String toString() {
		return "ExcelVO [site_code=" + site_code + ", data_time=" + data_time + ", gl=" + gl + ", ec=" + ec + ", temp="
				+ temp + "]";
	}
	
	public ExcelVO() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	
}
