package Bean;

public class Admin {

	private String id;		// id
	private String pw;		// 비밀번호
	private String setadmin;	// id,pw 입력설정
	
	public Admin(String id,String pw) {
		this.id=id;
		this.pw=pw;
		this.setadmin="set";
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


	public String getSetadmin() {
		return setadmin;
	}

	public void setSetadmin(String setadmin) {
		this.setadmin = setadmin;
	}

	public static void main(String[] args) {
		

	}

}
