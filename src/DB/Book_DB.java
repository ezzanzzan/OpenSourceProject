package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Bean.*;
import Frame.*;

public class Book_DB {

	private Statement stmt = null;
	public static boolean a=false;


	// 테이블 초기화 작업을 하는 메소드
	public static void PrintBook(JTable table) {	

		Connection conn=null;
		Statement stmt =null;

		if(a==false) {
			try  {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from test1_2;");

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String name, writer, publisher, code, forrent;

				while (rs.next())  {
					name = rs.getString("name");
					writer = rs.getString("writer");
					publisher = rs.getString("publisher");
					code = rs.getString("code");
					forrent = rs.getString("forrent");

					Book.list.add(new Book(name, writer, publisher, code, forrent));
					String str[] = {name, writer, publisher, code, forrent};

					model.addRow(str);   
				}

				rs = stmt.executeQuery("select * from test1_1;");

				while (rs.next())  {
					Member.list.add(new Member(rs.getString("id"), rs.getString("name"), rs.getString("pw"),rs.getString("phone"), rs.getString("rent1"), rs.getString("rent2"), rs.getString("rent3")));
				}

				a=true;
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
	}

	// 책 반납 버튼 실행 시 회원이 대여한 책만 출력하는 메소드
	public static void rentalListAddRow (DefaultTableModel model)  { 

		Remove_Data(model);

		Connection conn=null;
		Statement stmt=null;

		String rent1=null;
		String rent2=null;
		String rent3=null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();
			ResultSet rs=stmt.executeQuery("select rent1,rent2,rent3 from test1_1 where login = 'login'");

			while(rs.next()) {
				rent1=rs.getString("rent1");
				rent2=rs.getString("rent2");
				rent3=rs.getString("rent3");
			}

			// 대여한 책이 없을경우
			if(rent1==null&&rent2==null&&rent3==null) {
				JOptionPane.showMessageDialog(null,"반납할 책이 없습니다.");
			}

			else {
				for (Book obj : Book.list)  {
					String s=obj.getTitle()+"("+obj.getCode()+")";
					if (s.equals(rent1)&&obj.getForrent().equals("대여중"))  {
						String data[] = {obj.getTitle(), obj.getWriter(), obj.getPublisher(), obj.getCode(), obj.getForrent()};
						model.addRow(data);
					} 
					if (s.equals(rent2)&&obj.getForrent().equals("대여중"))  {
						String data[] = {obj.getTitle(), obj.getWriter(), obj.getPublisher(), obj.getCode(), obj.getForrent()};
						model.addRow(data);
					} 					
					if (s.equals(rent3)&&obj.getForrent().equals("대여중"))  {
						String data[] = {obj.getTitle(), obj.getWriter(), obj.getPublisher(), obj.getCode(), obj.getForrent()};
						model.addRow(data);
					} 
				}}

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

	// DB에 도서저장하는 메소드
	public static void bookSave_DB (String str1, String str2, String str3, String str4)  
	{  
		Connection conn = null;
		Statement stmt = null;

		// 도서 입력이 완벽하게 되지 않았을 경우
		if (str1.equals("") || str2.equals("") || str3.equals("") || str4.equals(""))  {
			JOptionPane.showMessageDialog(null,"등록할 도서 정보를 입력해주세요.");
			return;
		}


		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select code from test1_2 where code = '" + str4 + "';");

			// 입력된 코드 일 경우
			if (rs.next())  
			{
				int rowNum = stmt.executeUpdate("update test1_2 set name:='" + str1 + "', writer:='" + str2 + "', publisher:='" + str3 + "' where code='" + str4 + "';");

				// 자료구조에 책 추가 갱신작업
				for (Book obj : Book.list)  {
					if (obj.getCode().equals(str4))  {
						obj.setTitle(str1);
						obj.setWriter(str2);
						obj.setPublisher(str3);
						break;
					}
				}

				int rowCount = AdminBook_Frame.getModel().getRowCount(); // 도서 테이블 화면 도서변경정보 갱신
				for (int cnt = 0; cnt < rowCount; cnt++)  {
					if (str4.equals(AdminBook_Frame.getModel().getValueAt(cnt, 3)))  {
						AdminBook_Frame.getModel().setValueAt(str1, cnt, 0);
						AdminBook_Frame.getModel().setValueAt(str2, cnt, 1);
						AdminBook_Frame.getModel().setValueAt(str3, cnt, 2);
						break;
					}
				}

				JOptionPane.showMessageDialog(null,str4+"의 도서 정보가 수정되었습니다.");
				return;
			}

			// 새로운 책 추가
			else  {
				String queryLang = "insert into test1_2 (name, writer, publisher, code, forrent) values('" + str1 + "', '" + str2 + "', '" + str3 + "', '" + str4 + "', '대여가능');";
				int rowNum = stmt.executeUpdate(queryLang);
				JOptionPane.showMessageDialog(null,"새로운 도서가 등록되었습니다.");
			}

			Book.list.add(new Book(str1, str2, str3, str4, "대여가능"));
			String rowData[] = {str1, str2, str3, str4, "대여가능"};
			AdminBook_Frame.getModel().addRow(rowData);
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
			catch (Exception a)  {
			}
			try  {
				conn.close();
			}
			catch (Exception a)  {
			}
		}
	}

	// DB에 저장되어 있는 도서 삭제 메소드
	public static void bookDelete_DB (String selectBook[])  {

		Connection conn = null;
		Statement stmt = null;
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = conn.createStatement();

			// 학생 대여 취소
			String s=selectBook[0]+"("+selectBook[3]+")";
			String rent=null;
			String id=null;

			ResultSet rs=stmt.executeQuery("select id,rent1 from test1_1 where rent1='"+s+"';");
			while(rs.next()) {
				id=rs.getString("id");
				rent=rs.getString("rent1");	}
			if(rent!=null) {
				stmt.executeUpdate("update test1_1 set rent1=null where id = '" + id + "';");
			}

			rent=null;
			rs=stmt.executeQuery("select id,rent2 from test1_1 where rent2='"+s+"';");
			while(rs.next()) {
				id=rs.getString("id");
				rent=rs.getString("rent2");	}
			if(rent!=null) {
				stmt.executeUpdate("update test1_1 set rent2=null where id = '" + id + "';");
			}

			rent=null;
			rs=stmt.executeQuery("select id,rent3 from test1_1 where rent3='"+s+"';");
			while(rs.next()) {
				id=rs.getString("id");
				rent=rs.getString("rent3");	}
			if(rent!=null) {
				stmt.executeUpdate("update test1_1 set rent3=null where id = '" + id + "';");
			}

			rs = stmt.executeQuery("select name from test1_2 where code = '" + selectBook[3] + "';");

			// DB 도서 삭제
			String queryLang = "delete from test1_2 where code = '" + selectBook[3] + "';";

			int rowNum = stmt.executeUpdate(queryLang);
			JOptionPane.showMessageDialog(null,selectBook[3]+"의 책이 삭제완료되었습니다.");

			// 자료구조에 저장되어 있는 도서 데이터 삭제
			for (int cnt = 0; cnt < Book.list.size(); cnt++)  { 
				Book obj = Book.list.get(cnt);
				if (obj.getCode().equals(selectBook[3]))  {
					Book.list.remove(cnt);
					break;
				}
			}

			// 삭제한 도서와 같은 테이블 튜플삭제
			int rowCount = AdminBook_Frame.getModel().getRowCount(); 
			for (int cnt = 0; cnt < rowCount; cnt++)  {
				if (AdminBook_Frame.getModel().getValueAt(cnt, 3).equals(selectBook[3]))
					AdminBook_Frame.getModel().removeRow(cnt);
			}


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

	// DB에 저장되어 있는 회원정보 삭제 메소드
	public static void memberDelete_DB (String selectMember[])  { 

		Connection conn = null;
		Statement stmt = null;
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select name from test1_1 where id = '" + selectMember[0] + "';");

			String queryLang = "delete from test1_1 where id = '" + selectMember[0] + "';";
			int rowNum = stmt.executeUpdate(queryLang);
			JOptionPane.showMessageDialog(null,selectMember[0]+"의 학생이 삭제되었습니다.");

			// 자료구조에 저장되어 있는 회원 데이터 삭제
			for (int cnt = 0; cnt < Member.list.size(); cnt++)  { 
				Member obj = Member.list.get(cnt);
				if (obj.getId().equals(selectMember[0]))  {
					Member.list.remove(cnt);
					break;
				}
			}   

			// 삭제한 회원와 같은 테이블 튜플삭제
			int rowCount = AdminMember_Frame.getModel().getRowCount(); 
			for (int cnt = 0; cnt < rowCount; cnt++)  {
				if (AdminMember_Frame.getModel().getValueAt(cnt, 0).equals(selectMember[0]))
					AdminMember_Frame.getModel().removeRow(cnt);
			}

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

	// 책 선택된 테이블 데이터 가져오는 메소드
	public static String[] getTableData (JTable table)  { 
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowNum = table.getSelectedRow();
		if (rowNum == -1)
			return null;
		int colCount = table.getColumnCount();
		String tableData[] = new String[colCount];
		for (int cnt = 0; cnt < colCount; cnt++)  {
			Object obj = model.getValueAt(rowNum, cnt);
			tableData[cnt] = String.valueOf(obj);
		}
		return tableData;
	}

	// 회원이 대여 가능한 상태인지 확인하는 메소드
	public static boolean rental_list()  {

		Connection conn=null;
		Statement stmt=null;

		String arr[] = new String[3];
		String rent1=null;
		String rent2=null;
		String rent3=null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = (Statement) conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from test1_1 where login = 'login'");

			String id=null;

			while(rs.next()) {
				rent1=rs.getString("rent1");
				rent2=rs.getString("rent2");
				rent3=rs.getString("rent3");
				id=rs.getString("id");
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

		// 3권 모두 대여중일 때
		if(rent1!=null&&rent2!=null&&rent3!=null) {
			return false;			
		}

		// 대여 가능할 때
		else 
			return true;	



	}

	// 책 대여를 처리하는 메소드
	public static void rental_DB(String selectBook[]) {

		Connection conn = null;
		Statement stmt = null;
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = conn.createStatement();
			stmt.executeUpdate("update test1_2 set forrent:= '대여중' where code = '" + selectBook[3] + "';");

			ResultSet rs = stmt.executeQuery("select rent1, rent2, rent3,id from test1_1 where login = 'login';");

			String rent1 = null;
			String rent2 = null;
			String rent3 = null;
			String id=null;

			while (rs.next())  {
				rent1 = rs.getString("rent1");
				rent2 = rs.getString("rent2");
				rent3 = rs.getString("rent3");
				id=rs.getString("id");
			}
			if (rent1 == null)  {
				stmt.executeUpdate("update test1_1 set rent1:= '" + selectBook[0] + "(" + selectBook[3] + ")' where id = '" + id + "';");
			}
			else if (rent2 == null)  {
				stmt.executeUpdate("update test1_1 set rent2:= '" + selectBook[0] + "(" + selectBook[3] + ")' where id = '" + id + "';");
			}
			else  {
				stmt.executeUpdate("update test1_1 set rent3:= '" + selectBook[0] + "(" + selectBook[3] + ")' where id = '" + id + "';");
			}

			// 자료구조에 있는 도서데이터 '대여중'으로 갱신작업
			for (Book obj : Book.list)  {
				if (obj.getCode().equals(selectBook[3]))  {
					obj.setForrent("대여중");
					break;
				}
			}

			// 자료구조에 있는 회원데이터에 대여한 책 추가하는 갱신작업
			for (Member obj : Member.list)  { 
				if (obj.getId().equals(id))  {
					if (obj.rentBook[0] == null)
						obj.rentBook[0] = selectBook[0] + "(" + selectBook[3] + ")";
					else if (obj.rentBook[1] == null)
						obj.rentBook[1] = selectBook[0] + "(" + selectBook[3] + ")";
					else
						obj.rentBook[2] = selectBook[0] + "(" + selectBook[3] + ")";
					break;
				}
			}
			int rowCount = BookRental_Frame.model.getRowCount(); // 도서 테이블 화면 대여중으로 화면 갱신
			for (int cnt = 0; cnt < rowCount; cnt++)  {
				if (selectBook[3].equals(BookRental_Frame.model.getValueAt(cnt, 3)))  {
					BookRental_Frame.model.setValueAt("대여중", cnt, 4);
					break;
				}
			}
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
			catch (Exception a)  {
			}
			try  {
				conn.close();
			}
			catch (Exception a)  {
			}
		}
	}

	// 도서 검색기능 메소드
	public static void rentalBookIndex (String title, String writer, String publisher, JTable table)  { 
		if (title.equals("") && writer.equals("") && publisher.equals(""))  {
			JOptionPane.showMessageDialog(null,"검색 조건을 하나 이상 입력해주세요.");
			return;
		}
		int size = Book.list.size();
		String tempData1[][] = new String[size][5];
		String tempData2[][] = new String[size][5];
		int cntData1 = 0;
		int cntData2 = 0;
		DefaultTableModel model = (DefaultTableModel) table.getModel();   
		if (!(title.equals("")))  {
			for (Book obj:Book.list)  {
				if (!(-1 == obj.getTitle().indexOf(title)))  {
					tempData1[cntData1][0] = obj.getTitle();
					tempData1[cntData1][1] = obj.getWriter();
					tempData1[cntData1][2] = obj.getPublisher();
					tempData1[cntData1][3] = obj.getCode();
					tempData1[cntData1][4] = obj.getForrent();
					cntData1++;
				}
			}
			if (tempData1[0][0] == null)  {
				JOptionPane.showMessageDialog(null,"검색된 회원정보가 없습니다.");
				return;
			}
		}

		if (!(writer.equals("")))  {
			if (tempData1[0][0] == null)  {
				for (Book obj:Book.list)  {
					if (!(-1 == obj.getWriter().indexOf(writer)))  {
						tempData1[cntData1][0] = obj.getTitle();
						tempData1[cntData1][1] = obj.getWriter();
						tempData1[cntData1][2] = obj.getPublisher();
						tempData1[cntData1][3] = obj.getCode();
						tempData1[cntData1][4] = obj.getForrent();
						cntData1++;
					}
				}
				if (tempData1[0][0] == null)  {
					JOptionPane.showMessageDialog(null,"검색된 회원정보가 없습니다.");
					return;
				}   
			}
			else  {
				for (int cnt = 0; cnt < cntData1; cnt++)  {
					if (!(-1 == tempData1[cnt][1].indexOf(writer)))  {
						tempData2[cntData2] = tempData1[cnt];
						cntData2++;
					}
				}
				if (tempData2[0][0] == null)  {
					JOptionPane.showMessageDialog(null,"검색된 회원정보가 없습니다.");
					return;
				}   
			}
		}

		if (!(publisher.equals("")))  {
			if (tempData2[0][0] == null)  {
				if (tempData1[0][0] == null)  {
					int count = 0;
					for (Book obj:Book.list)  {
						if (!(-1 == obj.getPublisher().indexOf(publisher)))  {
							String arr[] = {obj.getTitle(), obj.getWriter(), obj.getPublisher(), obj.getCode(), obj.getForrent()};
							model.addRow(arr);
						}
					}
					if (count == 0)
						JOptionPane.showMessageDialog(null,"검색된 도서정보가 없습니다.");
				}
				else  {
					int count = 0;
					for (int cnt = 0; cnt < cntData1; cnt++)  {
						if (!(-1 == tempData1[cnt][2].indexOf(publisher)))  {
							model.addRow(tempData1[cnt]);
							count++;
						}
					}
					if (count == 0)
						JOptionPane.showMessageDialog(null,"검색된 도서정보가 없습니다.");
				}
			}
			else  {
				for (int cnt = 0; cnt < cntData2; cnt++)  {
					if (!(-1 == tempData2[cnt][2].indexOf(publisher)))
						model.addRow(tempData2[cnt]);
				}
			}
		}
		else  {
			if (tempData2[0][0] == null)  {     //제목 or 저자만 입력했을시               
				for (int cnt = 0; cnt < cntData1; cnt++)  {
					if (tempData1[cnt][0] == null)
						return;
					model.addRow(tempData1[cnt]);
				}
			}
			else  { // 제목 and 저자 입력했을시
				for (int cnt = 0; cnt < cntData2; cnt++)  {
					if (tempData2[cnt][0] == null)
						return;
					model.addRow(tempData2[cnt]);
				}
			}
		}   
	}

	// 회원 검색기능 메소드
	public static void rentalMemberIndex (String id, JTable table)  { 
		if (id.equals(""))  {
			JOptionPane.showMessageDialog(null,"검색 조건을 입력해주세요.");
			return;
		}
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int cnt = 0;
		for (Member obj : Member.list)  {
			if (!(-1 == obj.getId().indexOf(id)))  {
				String data[] = {obj.getId(), obj.getName(), obj.getPw(),obj.getPhone(),obj.getRent1(),obj.getRent2(),obj.getRent3()};
				model.addRow(data);
				cnt++;
			}
		}
		if (cnt == 0)
			JOptionPane.showMessageDialog(null,"검색결과가 없습니다.");

	}


	// 코드로 도서검색 메소드
	public static void rentalBookIndex (String codeStr, DefaultTableModel model)  {
		int cnt = 0;
		for (Book obj : Book.list)  {
			if (obj.getCode().equals(codeStr))  {
				String data[] = {obj.getTitle(), obj.getWriter(), obj.getPublisher(), obj.getCode(), obj.getForrent()};
				model.addRow(data);
				cnt++;
			}
		}
		if (cnt == 0)  {
			JOptionPane.showMessageDialog(null,"검색된 도서코가 없습니다.");
		}
	}




	// 책 반납 처리하는 메소드
	public static void return_DB (String selectBook[])  {

		Connection conn = null;
		Statement stmt = null;
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=UTF-8&serverTimezone=UTC","root","1234");
			stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery("Select rent1,rent2,rent3,id from test1_1 where login = 'login';");

			String rent1 = null;
			String rent2 = null;
			String rent3 = null;
			String id=null;

			while (rs.next()){
				rent1 = rs.getString("rent1");
				rent2 = rs.getString("rent2");
				rent3 = rs.getString("rent3");
				id=rs.getString("id");
			}

			String a = selectBook[0] + "(" + selectBook[3] + ")";

			if (a.equals(rent1))
				stmt.executeUpdate("update test1_1 set rent1:= null where login = 'login';");
			if (a.equals(rent2))
				stmt.executeUpdate("update test1_1 set rent2:= null where login = 'login';");
			if (a.equals(rent3))
				stmt.executeUpdate("update test1_1 set rent3:= null where login = 'login';");


			stmt.executeUpdate("update test1_2 set forrent:='대여가능' where code = '"+selectBook[3]+"';");

			// 자료구조에 있는 도서데이터 '대여가능'으로 갱신
			for (Book obj : Book.list)  {
				if (obj.getCode().equals(selectBook[3]))  {
					obj.setForrent("대여가능");
					break;
				}
			}

			// 자료구조에 있는 회원데이터에서 반납하는 회원의 3개의 대여목록에서 반납도서 삭제
			for (Member obj : Member.list)  { 
				if (obj.getId().equals(id))  {
					if (a.equals(obj.rentBook[0]))
						obj.rentBook[0] = null;
					if (a.equals(obj.rentBook[1]))
						obj.rentBook[1] = null;
					if (a.equals(obj.rentBook[2]))
						obj.rentBook[2] = null;
					break;
				}
			}

			// 도서 테이블 화면 대여가능으로 화면 갱신
			int rowCount = BookReturn_Frame.model.getRowCount(); 
			for (int cnt = 0; cnt < rowCount; cnt++)  {
				if (selectBook[3].equals(BookReturn_Frame.model.getValueAt(cnt, 3)))  {
					BookReturn_Frame.model.setValueAt("대여가능", cnt, 4);
					break;
				}
			}
			JOptionPane.showMessageDialog(null,"반납이 완료되었습니다.");
		}
		catch (ClassNotFoundException cnfe)  {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		}
		catch (SQLException se)  {
			System.out.println(se.getMessage());
		}

	}

	// 테이블의 모든 튜플 삭제 메소드
	public static void Remove_Data (DefaultTableModel model)  { 
		int rowCount = model.getRowCount();
		for (int cnt = 0; cnt < rowCount; cnt++)  {
			model.removeRow(0);
		}
	}

	// 대여중인 도서데이터만 테이블 튜플에 추가하는 메소드
	public static void rentalListAddRowA (DefaultTableModel model)  { 
		Remove_Data(model);
		for (Book obj : Book.list)  {
			if (obj.getForrent().equals("대여중"))  {
				String data[] = {obj.getTitle(), obj.getWriter(), obj.getPublisher(), obj.getCode(), obj.getForrent()};
				model.addRow(data);
			}   
		}
	}

	// 모든 도서 데이터 테이블 튜플에 추가하는 메소드
	public static void allBookDataAddRow (DefaultTableModel model)  {
		Remove_Data(model);
		for (Book obj : Book.list)  {
			String data[] = {obj.getTitle(), obj.getWriter(), obj.getPublisher(), obj.getCode(), obj.getForrent()};
			model.addRow(data);
		}
	}

	// 모든 회원 데이터 테이블 튜플에 추가하는 메소드
	public static void allMemberDataAddRow (DefaultTableModel model)  {
		Remove_Data(model);
		for (Member obj : Member.list)  {
			String data[] = {obj.getId(), obj.getName(), obj.getPw(), obj.getPhone(), obj.rentBook[0], obj.rentBook[1], obj.rentBook[2]};
			model.addRow(data);
		}
	}

}

