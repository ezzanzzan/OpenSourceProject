package Frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DB.Book_DB;

public class BookRental_Frame extends JFrame {

	public static DefaultTableModel model;
	private JPanel panel;

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

	/**
	 * Create the frame.
	 */
	public BookRental_Frame() {
		setTitle("전공책 대여 사업 프로그램");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(800,500);	
		setLocationRelativeTo(null);	
		setResizable(false);

		Container contentPane = getContentPane();
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 50);
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 411, 784, 50);

		JButton rental_B = new JButton("대여하기");
		rental_B.setBackground(Color.DARK_GRAY);
		rental_B.setForeground(Color.WHITE);
		rental_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		rental_B.setBounds(308, 10, 175, 30);

		JTextField title = new JTextField(11);
		title.setBounds(62, 9, 150, 24);
		title.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		JTextField writer = new JTextField(4);
		writer.setBounds(265, 9, 85, 24);
		writer.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		JTextField publisher = new JTextField(9);
		publisher.setBounds(426, 9, 96, 24);
		publisher.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		JTextField code = new JTextField(4);
		code.setBounds(572, 9, 115, 24);
		code.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		JButton Search_B = new JButton("검색");
		Search_B.setForeground(Color.WHITE);
		Search_B.setBackground(Color.DARK_GRAY);
		Search_B.setBounds(698, 8, 74, 25);
		Search_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		getContentPane().setLayout(null);
		panel.setLayout(null);

		panel.add(rental_B);
		contentPane.add(panel);
		panel2.setLayout(null);

		JLabel label = new JLabel("제목");
		label.setBounds(24, 12, 27, 18);
		label.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		panel2.add(label);
		panel2.add(title);
		JLabel label_1 = new JLabel("저자");
		label_1.setBounds(225, 12, 28, 18);
		label_1.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		panel2.add(label_1);
		panel2.add(writer);
		JLabel label_2 = new JLabel("출판사");
		label_2.setBounds(371, 12, 43, 18);
		label_2.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		panel2.add(label_2);
		panel2.add(publisher);
		panel2.add(Search_B);
		JLabel label_3 = new JLabel("코드");
		label_3.setBounds(534, 12, 26, 18);
		label_3.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		panel2.add(label_3);
		panel2.add(code);
		contentPane.add(panel2);

		String columNames[] = {"도서명", "저자", "출판사", "코드", "대여상태"};
		DefaultTableModel model = new DefaultTableModel(columNames, 0);

		JTable table = new JTable(model);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
		table.setRowHeight(25);
		this.model = model;

		Book_DB.PrintBook(table);
		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setBounds(0, 50, 784, 361);
		contentPane.add(scrollPane);

		// 대여 버튼을 누르면 발생하는 이벤트
		rental_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {
				
				// 대여할 전공책 선택
				String selectBook[] = Book_DB.getTableData(table);
				if (selectBook == null)  {
					JOptionPane.showMessageDialog(null,"도서를 선택해주세요.");
					return;
				}
				else if (selectBook[4].equals("대여중"))  {
					JOptionPane.showMessageDialog(null,"이미 대여중인 책입니다.");
					return;
				}
				
				// 대여가능한지 확인
				boolean b = Book_DB.rental_list();
				if (b==false)  {
					JOptionPane.showMessageDialog(null,"대여가능 수를 초과하였습니다.");
					return;
				}
				
				Book_DB.rental_DB(selectBook);
				JOptionPane.showMessageDialog(null,"책 대여가 완료되었습니다.");

			}
		});
		
		// 검색 버튼을 누르면 발생하는 이벤트
		Search_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {
				String titleStr = title.getText();
				String writerStr = writer.getText();
				String publisherStr = publisher.getText();
				String codeStr = code.getText();
				if (titleStr.equals("") && writerStr.equals("") && publisherStr.equals(""))  {
					if (!codeStr.equals(""))  {
						Book_DB.Remove_Data(model);
						Book_DB.rentalBookIndex(codeStr, model);
						return;
					}	
					else  {
						System.out.println("검색조건을 한개 이상 입력해주세요");
						return;
					}
				}
				Book_DB.Remove_Data(model);
				Book_DB.rentalBookIndex(titleStr, writerStr, publisherStr, table);
				
			}
		});
		
		setVisible(true);

	}

}
