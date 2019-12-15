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
		
		getContentPane().setLayout(null);
		contentPane.add(panel);

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

		scrollPane.setBounds(0, 100, 1394, 771);
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
		
		// �ڷΰ��� ��ư Ŭ�� �� �̺�Ʈ
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\\uCC9C\uC740\uC815\\Downloads\\back-arrow.png"));
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
