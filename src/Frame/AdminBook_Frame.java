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
		
		// ȭ�� gui
		setTitle("����å �뿩 ��� ���α׷�");

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
		title.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		JTextField writer = new JTextField(4);
		writer.setBounds(390, 30, 200, 45);
		writer.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		JTextField publisher = new JTextField(9);
		publisher.setBounds(680, 30, 240, 45);
		publisher.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		JTextField code = new JTextField(4);
		code.setBounds(990, 30, 200, 45);
		code.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		JButton Search_B = new JButton("�˻�");
		Search_B.setForeground(Color.WHITE);
		Search_B.setBackground(Color.DARK_GRAY);
		Search_B.setBounds(1230, 30, 125, 45);
		Search_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		getContentPane().setLayout(null);

		// ��ư gui
		JButton rentalList_B = new JButton("�뿩����Ʈ");
		rentalList_B.setBackground(Color.DARK_GRAY);
		rentalList_B.setForeground(Color.WHITE);
		rentalList_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		rentalList_B.setBounds(109, 25, 165, 50);
		panel.add(rentalList_B);
		
		JButton allList_B = new JButton("��ü����Ʈ");
		allList_B.setForeground(Color.WHITE);
		allList_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		allList_B.setBackground(Color.DARK_GRAY);
		allList_B.setBounds(335, 25, 165, 50);
		panel.add(allList_B);
		
		JButton addBook_B = new JButton("���� �߰�");
		addBook_B.setBounds(766, 25, 165, 50);
		addBook_B.setForeground(Color.WHITE);
		addBook_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		addBook_B.setBackground(Color.DARK_GRAY);
		panel.add(addBook_B);
		
		JButton deleteBook_B = new JButton("���� ����");
		deleteBook_B.setBounds(981, 25, 164, 50);
		deleteBook_B.setForeground(Color.WHITE);
		deleteBook_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		deleteBook_B.setBackground(Color.DARK_GRAY);
		panel.add(deleteBook_B);

		// Ȩ ��ư Ŭ�� �� �̺�Ʈ
		JButton button = new JButton("");
		button.setBounds(1314, 15, 68, 65);
		panel.add(button);
		button.setIcon(new ImageIcon("C:\\Users\\\uCC9C\uC740\uC815\\Downloads\\house (1).png"));
		button.setBorderPainted(false);			// �׵θ� ����
		button.setContentAreaFilled(false);		// ���뿵�� ä��� ����
		button.setFocusPainted(false);			// ���� �Ǿ��� �� �׵θ� ��� ����
		button.setOpaque(false);
		button.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login_Frame();
			}
		});
		
		contentPane.add(panel);

		JLabel label = new JLabel("����");
		label.setBounds(30, 30, 40, 45);
		label.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		panel2.add(label);
		panel2.add(title);
		JLabel label_1 = new JLabel("����");
		label_1.setBounds(340, 30, 40, 45);
		label_1.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		panel2.add(label_1);
		panel2.add(writer);
		JLabel label_2 = new JLabel("���ǻ�");
		label_2.setBounds(610, 30, 60, 45);
		label_2.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		panel2.add(label_2);
		panel2.add(publisher);
		panel2.add(Search_B);
		JLabel label_3 = new JLabel("�ڵ�");
		label_3.setBounds(940, 30, 40, 45);
		label_3.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		panel2.add(label_3);
		panel2.add(code);
		contentPane.add(panel2);

		String columNames[] = {"������", "����", "���ǻ�", "�ڵ�", "�뿩����"};
		DefaultTableModel model = new DefaultTableModel(columNames, 0);

		JTable table = new JTable(model);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		table.setRowHeight(40);
		this.model = model;

		Book_DB.PrintBook(table);
		Book_DB.allBookDataAddRow(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 1394, 672);
		contentPane.add(scrollPane);
		
		// ���� �߰� ��ư Ŭ�� �� �̺�Ʈ
		addBook_B.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				new AddBook_Frame();
			}
		});

		// ���� ���� ��ư Ŭ�� �� �̺�Ʈ
		deleteBook_B.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				
				// ���� �� ���� ����
				String selectBook[] = Book_DB.getTableData(table);
				
				if (selectBook == null)  {
					JOptionPane.showMessageDialog(null,"������ �������ּ���.");
					return;
				}
				else
				{
					Book_DB.bookDelete_DB(selectBook);
				}
			}
		});
		
		// �뿩 ����Ʈ ��ư Ŭ�� �� �̺�Ʈ
		rentalList_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book_DB.rentalListAddRowA(model);
			}
		});

		// ��ü ����Ʈ ��ư Ŭ�� �� �̺�Ʈ
		allList_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book_DB.allBookDataAddRow(model);
			}
		});
		
		// �˻� ��ư Ŭ�� �� �̺�Ʈ
		Search_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
						System.out.println("�˻������� �Ѱ� �̻� �Է����ּ���");
						return;
					}
				}
				Book_DB.Remove_Data(model);
				Book_DB.rentalBookIndex(titleStr, writerStr, publisherStr, table);
			}
		});
	}
}
