// 회원가입 생성자

package Bookmanager;

public class JoinBean {
	private String id;		// 학번
	private String name;	// 이름
	private String pw;		// 비밀번호
	private String phone;	// 전화번호
	
	public JoinBean(String id, String name, String pw, String phone) {	// 생성자
		this.id=id;
		this.name=name;
		this.pw=pw;
		this.phone=phone;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
