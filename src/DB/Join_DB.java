// 회원가입 시 입력된 정보를 DB에 저장

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

	// 학생 회원가입
	public static void JoinS_DB(Member inform) {

		int r = 0;
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

			// rs = 입력된 id가 있는 위치
			ResultSet rs = stmt.executeQuery("select id,name,pw,phone from test1_1 where id = '"+inform.getId()+"';");

			String id=null;

			while(rs.next()) {
				id=rs.getString("id");
			}

			if(id!=null) {					// 이미 존재하는 id일 때
				stmt.executeUpdate("update test1_1 set id:='"+inform.getId()+"', name:='"+inform.getName()+"', pw:='"+inform.getPw()+"',phone:='"+inform.getPhone()+"'where id='"+inform.getId()+"';");
				for(Member obj : Member.list) {
					if(obj.getId().equals(inform.getId())) {	// 자료구조에 있는 회원데이터 변경
						obj.setName(inform.getName());
						obj.setPw(inform.getPw());
						obj.setPhone(inform.getPhone());

						JOptionPane.showMessageDialog(null,"회원가입이 완료된 학번입니다. 회원정보가 수정됩니다.");
						break;

					}
				}
				return;
			}


			r = stmt.executeUpdate("insert into test1_1 (id,name,pw,phone) value ('" +
					inform.getId() + "','" + inform.getName() + "','" + inform.getPw() +"','" +inform.getPhone()+ "')" );

			if( r == 1 )
			{
				JOptionPane.showMessageDialog(null,"회원가입이 완료되었습니다.");
			}else{
				JOptionPane.showMessageDialog(null,"회원가입이 실패되었습니다.");
			}

			// 자료구조에 회원 저장
			Member.list.add(new Member(inform.getId(),inform.getName(),inform.getPw(),inform.getPhone(),"","",""));

		}
		catch (ClassNotFoundException cnfe)  {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
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

	// 관리자 회원가입
	public static void JoinA_DB(Admin inform) {

		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();

			// 로그인 확인 삭제
			stmt.executeUpdate("update test1_1 set login = null ");

			ResultSet rs=stmt.executeQuery("select * from test1_3;");

			String id=null;
			String pw=null;

			while(rs.next()) {
				id=rs.getString("id");
				pw=rs.getString("pw");
			}

			// id가 존재할 경우 -> 수정
			if(id!=null) {
				stmt.executeUpdate("update test1_3 set id:='"+inform.getId()+"', pw:='"+inform.getPw()+"',setadmin='set';");
				return;
			}
			else
				stmt.executeUpdate("insert into test1_3 (id,pw,setadmin) value ('" + inform.getId() + "','" + inform.getPw() + "','set')" );

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
