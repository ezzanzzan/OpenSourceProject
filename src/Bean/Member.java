// ȸ������ ������

package Bean;

import java.util.ArrayList;

public class Member {
	private String id;		// �й�
	private String name;	// �̸�
	private String pw;		// ��й�ȣ
	private String phone;	// ��ȭ��ȣ
	public String login;	// �α��� ����
	
	public String rentBook[]=new String[3];
	
	public static ArrayList<Member> list = new ArrayList<Member>();
	
	public Member(String id, String name, String pw, String phone,String rent1, String rent2, String rent3) {	// ������
		this.id=id;
		this.name=name;
		this.pw=pw;
		this.phone=phone;
		rentBook[0]=rent1;
		rentBook[1]=rent2;
		rentBook[2]=rent3;
		login=null;
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
	public String getRent1()
	{
		return rentBook[0];
	}
	public String getRent2()
	{
		return rentBook[1];
	}
	public String getRent3()
	{
		return rentBook[2];
	}
	
}
