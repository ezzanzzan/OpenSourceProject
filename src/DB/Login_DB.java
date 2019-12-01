// 로그인 시 입력된 학번과 비밀번호로 DB 연동 검색

package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Bean.*;
import Frame.*;

public class Login_DB {
	private Statement stmt = null;

	// 학생으로 로그인
	public static void LoginS_DB(String loginid, String loginpw) {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

			// 로그인 확인 삭제
			stmt.executeUpdate("update test1_1 set login = null ");

			ResultSet rs=stmt.executeQuery("select pw from test1_1 where id = '"+loginid+"';");

			String pw=null;

			while(rs.next()) {
				pw=rs.getString("pw");
			}

			if(pw.equals(loginpw)) {

				JOptionPane.showMessageDialog(null,"로그인이 완료되었습니다.");

				// 로그인 한 아이디 확인
				stmt.executeUpdate("update test1_1 set login='login' where id='" + loginid +"'" );

				new SelectStudent_Frame();
			}

			// 로그인 시 학번과 비밀번호가 일치하지 않을 때
			else
				JOptionPane.showMessageDialog(null,"학번이나 비밀번호를 잘못 입력하셨습니다.");


		}
		catch(ClassNotFoundException cnfe){
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		}

		catch(SQLException se){
			System.out.println(se.getMessage());
		}

		try{

			conn.close();
		}
		catch(SQLException ee){
			System.out.println(ee.getMessage());
		}


	}

	// 관리자로 로그인
	public static void LoginA_DB(String loginid, String loginpw) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

			stmt.executeUpdate("update test1_1 set login = null ");

			ResultSet rs=stmt.executeQuery("select * from test1_3;");

			String id=null;
			String pw=null;
			String setadmin=null;

			while(rs.next()) {
				id=rs.getString("id");
				pw=rs.getString("pw");
				setadmin=rs.getString("setadmin");
			}

			if(setadmin==null||setadmin.length()==0) {		// 관리자 id,pw 지정 x
				new AdminJoin_Frame();
			}
			
			else {
				if(pw.equals(loginpw)) {

					JOptionPane.showMessageDialog(null,"관리자 로그인이 완료되었습니다.");
					new SelectAdmin_Frame();
				}

				// 로그인 시 id,pw가 일치하지 않을 때
				else
					JOptionPane.showMessageDialog(null,"잘못 입력하셨습니다.");
			}

		}
		catch(ClassNotFoundException cnfe){
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		}

		catch(SQLException se){
			System.out.println(se.getMessage());
		}

		try{

			conn.close();
		}
		catch(SQLException ee){
			System.out.println(ee.getMessage());
		}
	}
	
	public static void reset() {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

			// 로그인 확인 삭제
			stmt.executeUpdate("update test1_1 set login = null ");

			String queryLang = "delete from test1_3 where setadmin='set';";
			int rowNum = stmt.executeUpdate(queryLang);
		}
		catch(ClassNotFoundException cnfe){
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		}

		catch(SQLException se){
			System.out.println(se.getMessage());
		}

		try{

			conn.close();
		}
		catch(SQLException ee){
			System.out.println(ee.getMessage());
		}


	}

}
