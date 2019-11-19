// 로그인 시 입력된 학번과 비밀번호로 DB 연동 검색

package Bookmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class LoginDAO {
	private Statement stmt = null;
	private int result=0;

	public LoginDAO(LoginBean inform) {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");

			String qu="select * from test1_1";
			stmt = (Statement) conn.createStatement();
			ResultSet rs=stmt.executeQuery(qu);

			while(rs.next()) {

				String id=rs.getString("id");
				String pw=rs.getString("pw");

				if(id.equals(inform.getId())&&pw.equals(inform.getPw())) {
					JOptionPane.showMessageDialog(null,"로그인이 완료되었습니다.");
					result=1;
					break;
				}
			}

			// 로그인 시 학번과 비밀번호가 일치하지 않을 때
			if(result==0)
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
