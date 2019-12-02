package Frame;

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

public class AdminMember_Frame extends JFrame {

	static DefaultTableModel model;
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

	public static DefaultTableModel getModel() {
		return model;
	}

	public AdminMember_Frame() {
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

		// 버튼 gui
		JButton delete_B = new JButton("삭제하기");
		delete_B.setBackground(Color.DARK_GRAY);
		delete_B.setForeground(Color.WHITE);
		delete_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		delete_B.setBounds(772, 25, 200, 50);
		panel.add(delete_B);

		JButton allList_B = new JButton("\uC804\uCCB4 \uB9AC\uC2A4\uD2B8");
		allList_B.setForeground(Color.WHITE);
		allList_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		allList_B.setBackground(Color.DARK_GRAY);
		allList_B.setBounds(400, 25, 200, 50);
		panel.add(allList_B);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\\uCC9C\uC740\uC815\\Downloads\\back (3).png"));
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

		JTextField hakbun = new JTextField(11);
		hakbun.setBounds(611, 30, 240, 45);
		hakbun.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));

		JLabel label = new JLabel("학번");
		label.setBounds(559, 30, 40, 45);
		label.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		panel2.add(label);
		panel2.add(hakbun);
		getContentPane().setLayout(null);

		contentPane.add(panel);
		contentPane.add(panel2);

		String columNames[] = {"학번", "이름", "비밀번호", "전화번호", "대여도서1", "대여도서2", "대여도서3"};
		DefaultTableModel model = new DefaultTableModel(columNames, 0);

		JTable table = new JTable(model);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		table.setRowHeight(40);
		this.model = model;

		Book_DB.PrintBook(table);
		Book_DB.allMemberDataAddRow(model);	
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 1394, 672);
		contentPane.add(scrollPane);

		JButton Search_B = new JButton("검색");
		Search_B.setBounds(1208, 30, 125, 45);
		panel2.add(Search_B);
		Search_B.setForeground(Color.WHITE);
		Search_B.setBackground(Color.DARK_GRAY);
		Search_B.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));

		// 검색 버튼 누르면 발생하는 이벤트 
		Search_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {
				String hakbunStr=hakbun.getText();
				Book_DB.Remove_Data(model);
				Book_DB.rentalMemberIndex(hakbunStr, table);
			}
		});

		// 삭제 버튼을 누르면 발생하는 이벤트
		delete_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {

				String selectMember[] = Book_DB.getTableData(table);

				if (selectMember == null)  {
					JOptionPane.showMessageDialog(null,"학생을 선택해주세요.");
					return;
				}
				else
				{
					Book_DB.memberDelete_DB(selectMember);

				}

			}
		});

		// 전체리스트 버튼 누르면 발생하는 이벤트
		allList_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {
				Book_DB.allMemberDataAddRow(model);
			}
		});

		setVisible(true);
	}

}
