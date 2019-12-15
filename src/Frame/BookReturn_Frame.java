package Frame;

import DB.*;
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
import javax.swing.table.DefaultTableModel;

import DB.Book_DB;

public class BookReturn_Frame extends JFrame {

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
	public BookReturn_Frame() {
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
		
		getContentPane().setLayout(null);
		contentPane.add(panel);

		String columNames[] = {"도서명", "저자", "출판사", "코드", "대여상태"};
		DefaultTableModel model = new DefaultTableModel(columNames, 0);

		JTable table = new JTable(model);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		table.setRowHeight(40);
		BookReturn_Frame.model = model;

		Book_DB.PrintBook(table);
		Book_DB.rentalListAddRow(model);		
		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setBounds(0, 100, 1394, 771);
		contentPane.add(scrollPane);
		
		// 반납하기 버튼 생성 및 클릭시 이벤트
		JButton return_B = new JButton("반납하기");
		return_B.setBackground(Color.DARK_GRAY);
		return_B.setForeground(Color.WHITE);
		return_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		return_B.setBounds(600, 25, 200, 50);
		panel.add(return_B);
		
		return_B.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				
				// 반납할 전공책 선택
				String selectBook[] = Book_DB.getTableData(table);
				
				if (selectBook == null)  {
					JOptionPane.showMessageDialog(null,"도서를 선택해주세요.");
					return;
				}
				else if (selectBook[4].equals("대여가능"))  {
					JOptionPane.showMessageDialog(null,"이미 반납 된 책입니다.");
					return;
				}
				Book_DB.return_DB(selectBook);
			}
		});
		
		// 뒤로가기 버튼 클릭 시 이벤트
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
				new SelectStudent_Frame();
				setVisible(false);
			}
			});
		
	}

}
