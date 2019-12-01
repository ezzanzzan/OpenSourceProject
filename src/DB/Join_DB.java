// ȸ������ �� �Էµ� ������ DB�� ����

package DB;

import Bean.Admin;
import Bean.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Join_DB {

	// �л� ȸ������
	public static void JoinS_DB(Member inform) {

		int r = 0;
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

			// rs = �Էµ� id�� �ִ� ��ġ
			ResultSet rs = stmt.executeQuery("select id,name,pw,phone from test1_1 where id = '"+inform.getId()+"';");

			String id=null;

			while(rs.next()) {
				id=rs.getString("id");
			}

			if(id!=null) {					// �̹� �����ϴ� id�� ��
				stmt.executeUpdate("update test1_1 set id:='"+inform.getId()+"', name:='"+inform.getName()+"', pw:='"+inform.getPw()+"',phone:='"+inform.getPhone()+"'where id='"+inform.getId()+"';");
				for(Member obj : Member.list) {
					if(obj.getId().equals(inform.getId())) {	// �ڷᱸ���� �ִ� ȸ�������� ����
						obj.setName(inform.getName());
						obj.setPw(inform.getPw());
						obj.setPhone(inform.getPhone());

						JOptionPane.showMessageDialog(null,"ȸ�������� �Ϸ�� �й��Դϴ�. ȸ�������� �����˴ϴ�.");
						break;

					}
				}
				return;
			}


			r = stmt.executeUpdate("insert into test1_1 (id,name,pw,phone) value ('" +
					inform.getId() + "','" + inform.getName() + "','" + inform.getPw() +"','" +inform.getPhone()+ "')" );

			if( r == 1 )
			{
				JOptionPane.showMessageDialog(null,"ȸ�������� �Ϸ�Ǿ����ϴ�.");
			}else{
				JOptionPane.showMessageDialog(null,"ȸ�������� ���еǾ����ϴ�.");
			}

			// �ڷᱸ���� ȸ�� ����
			Member.list.add(new Member(inform.getId(),inform.getName(),inform.getPw(),inform.getPhone(),"","",""));

		}
		catch (ClassNotFoundException cnfe)  {
			System.out.println("�ش� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		}
		catch (SQLException se)  {
			System.out.println(se.getMessage());
		}
		finally  {
			try  {
				stmt.close();
			}
			catch (Exception e)  {
			}
			try  {
				conn.close();
			}
			catch (Exception e)  {
			}
		}
	}

	// ������ ȸ������
	public static void JoinA_DB(Admin inform) {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

			// �α��� Ȯ�� ����
			stmt.executeUpdate("update test1_1 set login = null ");

			ResultSet rs=stmt.executeQuery("select * from test1_3;");

			String id=null;
			String pw=null;

			while(rs.next()) {
				id=rs.getString("id");
				pw=rs.getString("pw");
			}

			// id�� ������ ��� -> ����
			if(id!=null) {
				stmt.executeUpdate("update test1_3 set id:='"+inform.getId()+"', pw:='"+inform.getPw()+"',setadmin='set';");
				return;
			}
			else
				stmt.executeUpdate("insert into test1_3 (id,pw,setadmin) value ('" + inform.getId() + "','" + inform.getPw() + "','set')" );

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
