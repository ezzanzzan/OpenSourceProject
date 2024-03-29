package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DB.*;

public class AdminBook_Frame extends JFrame {

	static DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static DefaultTableModel getModel() {
		return model;
	}
	
	public AdminBook_Frame() {
		
		// 화면 gui
		setTitle("전공책 대여 사업 프로그램");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(1400,900);	
		setLocationRelativeTo(null);	
		setResizable(false);

		setVisible(true);

		Container contentPane = getContentPane();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1394, 100);
		panel.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 771, 1394, 100);
		panel2.setLayout(null);

		JTextField title = new JTextField(11);
		title.setBounds(80, 30, 240, 45);
		title.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		JTextField writer = new JTextField(4);
		writer.setBounds(390, 30, 200, 45);
		writer.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		JTextField publisher = new JTextField(9);
		publisher.setBounds(680, 30, 240, 45);
		publisher.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		JTextField code = new JTextField(4);
		code.setBounds(990, 30, 200, 45);
		code.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		JButton Search_B = new JButton("검색");
		Search_B.setForeground(Color.WHITE);
		Search_B.setBackground(Color.DARK_GRAY);
		Search_B.setBounds(1230, 30, 125, 45);
		Search_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		getContentPane().setLayout(null);

		// 버튼 gui
		JButton rentalList_B = new JButton("대여리스트");
		rentalList_B.setBackground(Color.DARK_GRAY);
		rentalList_B.setForeground(Color.WHITE);
		rentalList_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		rentalList_B.setBounds(109, 25, 165, 50);
		panel.add(rentalList_B);
		
		JButton allList_B = new JButton("전체리스트");
		allList_B.setForeground(Color.WHITE);
		allList_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		allList_B.setBackground(Color.DARK_GRAY);
		allList_B.setBounds(335, 25, 165, 50);
		panel.add(allList_B);
		
		JButton addBook_B = new JButton("도서 추가");
		addBook_B.setBounds(766, 25, 165, 50);
		addBook_B.setForeground(Color.WHITE);
		addBook_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		addBook_B.setBackground(Color.DARK_GRAY);
		panel.add(addBook_B);
		
		JButton deleteBook_B = new JButton("도서 삭제");
		deleteBook_B.setBounds(981, 25, 164, 50);
		deleteBook_B.setForeground(Color.WHITE);
		deleteBook_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		deleteBook_B.setBackground(Color.DARK_GRAY);
		panel.add(deleteBook_B);

		// 뒤로 가기 버튼 클릭 시 이벤트
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\\uCC9C\uC740\uC815\\Downloads\\back-arrow.png"));
		button.setBounds(1300, 17, 68, 65);
		button.setBorderPainted(false);			// 테두리 제거
		button.setContentAreaFilled(false);		// 내용영역 채우기 없음
		button.setFocusPainted(false);			// 선택 되었을 때 테두리 사용 안함
		button.setOpaque(false);				// 투명하게
		panel.add(button);

		button.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SelectAdmin_Frame();
			}
		});
		
		contentPane.add(panel);

		JLabel label = new JLabel("제목");
		label.setBounds(30, 30, 40, 45);
		label.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		panel2.add(label);
		panel2.add(title);
		JLabel label_1 = new JLabel("저자");
		label_1.setBounds(340, 30, 40, 45);
		label_1.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		panel2.add(label_1);
		panel2.add(writer);
		JLabel label_2 = new JLabel("출판사");
		label_2.setBounds(610, 30, 60, 45);
		label_2.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		panel2.add(label_2);
		panel2.add(publisher);
		panel2.add(Search_B);
		JLabel label_3 = new JLabel("코드");
		label_3.setBounds(940, 30, 40, 45);
		label_3.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		panel2.add(label_3);
		panel2.add(code);
		contentPane.add(panel2);

		String columNames[] = {"도서명", "저자", "출판사", "코드", "대여상태"};
		DefaultTableModel model = new DefaultTableModel(columNames, 0);

		JTable table = new JTable(model);
		
		table.setBackground(Color.WHITE);
		table.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		table.setRowHeight(40);
		this.model = model;

		Book_DB.PrintBook(table);
		Book_DB.allBookDataAddRow(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 1394, 672);
		contentPane.add(scrollPane);
		
		// 도서 추가 버튼 클릭 시 이벤트
		addBook_B.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				new AddBook_Frame();
			}
		});

		// 도서 삭제 버튼 클릭 시 이벤트
		deleteBook_B.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				
				// 삭제 할 도서 선택
				String selectBook[] = Book_DB.getTableData(table);
				
				if (selectBook == null)  {
					JOptionPane.showMessageDialog(null,"도서를 선택해주세요.");
					return;
				}
				else
				{
					Book_DB.bookDelete_DB(selectBook);
				}
			}
		});
		
		// 대여 리스트 버튼 클릭 시 이벤트
		rentalList_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book_DB.rentalListAddRowA(model);
			}
		});

		// 전체 리스트 버튼 클릭 시 이벤트
		allList_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book_DB.allBookDataAddRow(model);
			}
		});
		
		// 검색 버튼 클릭 시 이벤트
		Search_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {
				String titleStr = title.getText();
				String writerStr = writer.getText();
				String publisherStr = publisher.getText();
				String codeStr = code.getText();
				if (titleStr.equals("") && writerStr.equals("") && publisherStr.equals("")&&codeStr.equals(""))  {
					JOptionPane.showMessageDialog(null,"검색 조건을 하나 이상 입력해주세요.");
					return;
				}
				else  {
					if (!codeStr.equals(""))  {		// 코드 검색
						Book_DB.Remove_Data(model);
						Book_DB.rentalBookIndex(codeStr, model);
						return;
					}	
					else {		// 도서, 저자, 출판사 검색
						Book_DB.Remove_Data(model);
						Book_DB.rentalBookIndex(titleStr, writerStr, publisherStr, table);
					}
				}
				
			}
		});
	}
}
