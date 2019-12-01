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

	// �л����� �α���
	public static void LoginS_DB(String loginid, String loginpw) {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

			// �α��� Ȯ�� ����
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

				new SelectStudent_Frame();
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

	// �����ڷ� �α���
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

			if(setadmin==null||setadmin.length()==0) {		// ������ id,pw ���� x
				new AdminJoin_Frame();
			}
			
			else {
				if(pw.equals(loginpw)) {

					JOptionPane.showMessageDialog(null,"������ �α����� �Ϸ�Ǿ����ϴ�.");
					new SelectAdmin_Frame();
				}

				// �α��� �� id,pw�� ��ġ���� ���� ��
				else
					JOptionPane.showMessageDialog(null,"�߸� �Է��ϼ̽��ϴ�.");
			}

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
	
	public static void reset() {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

			// �α��� Ȯ�� ����
			stmt.executeUpdate("update test1_1 set login = null ");

			String queryLang = "delete from test1_3 where setadmin='set';";
			int rowNum = stmt.executeUpdate(queryLang);
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
