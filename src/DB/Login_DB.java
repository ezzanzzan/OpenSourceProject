// �α��� �� �Էµ� �й��� ��й�ȣ�� DB ���� �˻�

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

				JOptionPane.showMessageDialog(null,"�α����� �Ϸ�Ǿ����ϴ�.");

				// �α��� �� ���̵� Ȯ��
				stmt.executeUpdate("update test1_1 set login='login' where id='" + loginid +"'" );

				new Select_Frame();
			}

			// �α��� �� �й��� ��й�ȣ�� ��ġ���� ���� ��
			else
				JOptionPane.showMessageDialog(null,"�й��̳� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.");


		}
		catch(ClassNotFoundException cnfe){
			System.out.println("�ش� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
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
