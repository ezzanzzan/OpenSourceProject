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

	public Login_DB(String loginid, String loginpw) {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

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

				new Book_Frame();
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
}
