package Frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.Login_DB;

public class Login_Frame extends JFrame {

	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Login_Frame() {
		setTitle("����å �뿩 ��� ���α׷�");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setSize(800,800);					
		setLocationRelativeTo(null);		
		setResizable(false);	

		panel = new JPanel(){
			public void paintComponent(Graphics g) {
				Dimension d=getSize();
				ImageIcon image = new ImageIcon("C:\\Users\\õ����\\Desktop\\���ȭ��.jpg");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setLayout(null);

		Container contentPane=getContentPane();  //��� �̹���
		contentPane.add(panel);

		// �й� text
		JLabel Hakbun_Lable=new JLabel("�й�");
		Hakbun_Lable.setForeground(Color.WHITE);
		Hakbun_Lable.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		Hakbun_Lable.setBounds(145, 324, 40, 25);	
		panel.add(Hakbun_Lable);

		// pw text
		JLabel pw_Lable = new JLabel("PW");
		pw_Lable.setForeground(Color.WHITE);
		pw_Lable.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		pw_Lable.setBounds(145, 404, 40, 25);	
		panel.add(pw_Lable);

		// �й� text box
		JTextField id = new JTextField(20); // �й� text box
		id.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		id.setBackground(new Color(248, 248, 255));
		id.setBounds(235, 314, 370, 45);	
		panel.add(id);       

		// pw text box
		JPasswordField pw = new JPasswordField(20);
		pw.setFont(new Font("����", Font.PLAIN, 20));
		pw.setBackground(new Color(248, 248, 255));
		pw.setBounds(235, 394, 370, 45);	
		panel.add(pw);

		// login button ���� & Ŭ�� �̺�Ʈ �߻� ��
		JButton login_B = new JButton("LOGIN");	
		login_B.setBackground(new Color(255, 255, 255));
		login_B.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		login_B.setBounds(234,465,160,55);		
		panel.add(login_B);

		// �л����� �α���
		login_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login_DB.LoginS_DB(id.getText(),pw.getText());
				setVisible(false);	
			}
		});


		// join button ���� & Ŭ�� �̺�Ʈ �߻� ��
		JButton join_S = new JButton("JOIN");	
		join_S.setBackground(new Color(255, 255, 255));
		join_S.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		join_S.setBounds(445,465,160,55);		
		panel.add(join_S);

		join_S.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				new Join_Frame();
				setVisible(false);
			}
		});

		JButton join_M = new JButton("�����ڷ� �α���");
		join_M.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		join_M.setBackground(Color.WHITE);
		join_M.setBounds(235, 543, 222, 55);
		panel.add(join_M);
		
		// �����ڷ� �α���
		join_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login_DB.LoginA_DB(id.getText(), pw.getText());
				setVisible(false);	
			}
		});

		// ������ id,pw �ʱ�ȭ
		JButton reset_B = new JButton("\uCD08\uAE30\uD654");
		reset_B.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		reset_B.setBackground(Color.WHITE);
		reset_B.setBounds(469, 543, 136, 55);
		panel.add(reset_B);
		
		reset_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_DB.reset();
			}
		});



		setVisible(true);
	}
}
