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

		// �˻� ��ư ���� �� Ŭ�� �� �̺�Ʈ
		JButton Search_B = new JButton("�˻�");
		Search_B.setForeground(Color.WHITE);
		Search_B.setBackground(Color.DARK_GRAY);
		Search_B.setBounds(1230, 30, 125, 45);
		Search_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		
		getContentPane().setLayout(null);
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
		BookReturn_Frame.model = model;

		Book_DB.PrintBook(table);
		Book_DB.rentalListAddRow(model);		
		JScrollPane scrollPane = new JScrollPane(table);

		scrollPane.setBounds(0, 100, 1394, 672);
		contentPane.add(scrollPane);
		
		// �ݳ��ϱ� ��ư ���� �� Ŭ���� �̺�Ʈ
		JButton return_B = new JButton("�ݳ��ϱ�");
		return_B.setBackground(Color.DARK_GRAY);
		return_B.setForeground(Color.WHITE);
		return_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		return_B.setBounds(600, 25, 200, 50);
		panel.add(return_B);
		
		return_B.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				
				// �ݳ��� ����å ����
				String selectBook[] = Book_DB.getTableData(table);
				
				if (selectBook == null)  {
					JOptionPane.showMessageDialog(null,"������ �������ּ���.");
					return;
				}
				else if (selectBook[4].equals("�뿩����"))  {
					JOptionPane.showMessageDialog(null,"�̹� �ݳ� �� å�Դϴ�.");
					return;
				}
				Book_DB.return_DB(selectBook);
			}
		});
		
		// �˻� ��ư Ŭ�� �� �̺�Ʈ
		Search_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {
				String titleStr = title.getText();
				String writerStr = writer.getText();
				String publisherStr = publisher.getText();
				String codeStr = code.getText();
				if (titleStr.equals("") && writerStr.equals("") && publisherStr.equals("")&&codeStr.equals(""))  {
					JOptionPane.showMessageDialog(null,"�˻� ������ �ϳ� �̻� �Է����ּ���.");
					return;
				}
				else  {
					if (!codeStr.equals(""))  {		// �ڵ� �˻�
						Book_DB.Remove_Data(model);
						Book_DB.rentalBookIndex(codeStr, model);
						return;
					}	
					else {		// ����, ����, ���ǻ� �˻�
						Book_DB.Remove_Data(model);
						Book_DB.rentalBookIndex(titleStr, writerStr, publisherStr, table);
					}
				}
				
			}
		});
		
		// �ڷΰ��� ��ư Ŭ�� �� �̺�Ʈ
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\\uCC9C\uC740\uC815\\Downloads\\back (3).png"));
		button.setBounds(1300, 17, 68, 65);
		button.setBorderPainted(false);			// �׵θ� ����
		button.setContentAreaFilled(false);		// ���뿵�� ä��� ����
		button.setFocusPainted(false);			// ���� �Ǿ��� �� �׵θ� ��� ����
		button.setOpaque(false);				// �����ϰ�
		panel.add(button);


		button.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				new SelectStudent_Frame();
				setVisible(false);
			}
			});
		
	}

}
