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

		// ��ư gui
		JButton delete_B = new JButton("�����ϱ�");
		delete_B.setBackground(Color.DARK_GRAY);
		delete_B.setForeground(Color.WHITE);
		delete_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		delete_B.setBounds(772, 25, 200, 50);
		panel.add(delete_B);

		JButton allList_B = new JButton("\uC804\uCCB4 \uB9AC\uC2A4\uD2B8");
		allList_B.setForeground(Color.WHITE);
		allList_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		allList_B.setBackground(Color.DARK_GRAY);
		allList_B.setBounds(400, 25, 200, 50);
		panel.add(allList_B);

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
				setVisible(false);
				new SelectAdmin_Frame();
			}
		});

		JTextField hakbun = new JTextField(11);
		hakbun.setBounds(611, 30, 240, 45);
		hakbun.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));

		JLabel label = new JLabel("�й�");
		label.setBounds(559, 30, 40, 45);
		label.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		panel2.add(label);
		panel2.add(hakbun);
		getContentPane().setLayout(null);

		contentPane.add(panel);
		contentPane.add(panel2);

		String columNames[] = {"�й�", "�̸�", "��й�ȣ", "��ȭ��ȣ", "�뿩����1", "�뿩����2", "�뿩����3"};
		DefaultTableModel model = new DefaultTableModel(columNames, 0);

		JTable table = new JTable(model);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));
		table.setRowHeight(40);
		this.model = model;

		Book_DB.PrintBook(table);
		Book_DB.allMemberDataAddRow(model);	
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 1394, 672);
		contentPane.add(scrollPane);

		JButton Search_B = new JButton("�˻�");
		Search_B.setBounds(1208, 30, 125, 45);
		panel2.add(Search_B);
		Search_B.setForeground(Color.WHITE);
		Search_B.setBackground(Color.DARK_GRAY);
		Search_B.setFont(new Font("THE�ܰ��μ���", Font.PLAIN, 20));

		// �˻� ��ư ������ �߻��ϴ� �̺�Ʈ 
		Search_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {
				String hakbunStr=hakbun.getText();
				Book_DB.Remove_Data(model);
				Book_DB.rentalMemberIndex(hakbunStr, table);
			}
		});

		// ���� ��ư�� ������ �߻��ϴ� �̺�Ʈ
		delete_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {

				String selectMember[] = Book_DB.getTableData(table);

				if (selectMember == null)  {
					JOptionPane.showMessageDialog(null,"�л��� �������ּ���.");
					return;
				}
				else
				{
					Book_DB.memberDelete_DB(selectMember);

				}

			}
		});

		// ��ü����Ʈ ��ư ������ �߻��ϴ� �̺�Ʈ
		allList_B.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)  {
				Book_DB.allMemberDataAddRow(model);
			}
		});

		setVisible(true);
	}

}
