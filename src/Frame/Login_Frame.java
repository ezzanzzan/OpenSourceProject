package Frame;

import DB.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;

public class Login_Frame extends JFrame {

	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Frame frame = new Login_Frame();
				
					frame.setVisible(true);
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

		setSize(800,600);					
		setLocationRelativeTo(null);		
		setResizable(false);	

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);

		JLabel Title=new JLabel("�ߡ� DRAG ����å �뿩 ��� ���α׷��Դϴ� �ޡ�");
		Title.setFont(new Font("���õ������ Bold", Font.PLAIN, 25));
		Title.setBounds(152,77,510,41);
		panel.add(Title);

		// �й� text
		JLabel Hakbun_Lable=new JLabel("�й�");
		Hakbun_Lable.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		Hakbun_Lable.setBounds(150, 195, 40, 25);	
		panel.add(Hakbun_Lable);

		// pw text
		JLabel pw_Lable = new JLabel("PW");
		pw_Lable.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		pw_Lable.setBounds(150, 275, 40, 25);	
		panel.add(pw_Lable);

		// �й� text box
		JTextField id = new JTextField(20); // �й� text box
		id.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		id.setBackground(new Color(248, 248, 255));
		id.setBounds(240, 185, 370, 45);	
		panel.add(id);       

		// pw text box
		JPasswordField pw = new JPasswordField(20);
		pw.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		pw.setBackground(new Color(248, 248, 255));
		pw.setBounds(240, 265, 370, 45);	
		panel.add(pw);

		// login button ���� & Ŭ�� �̺�Ʈ �߻� ��
		JButton login_B = new JButton("LOGIN");	
		login_B.setBackground(new Color(255, 255, 255));
		login_B.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		login_B.setBounds(239,336,160,55);		
		panel.add(login_B);

		// �л����� �α���
		login_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new Login_DB(id.getText(),pw.getText());
				
				setVisible(false);	
			}
		});


		// join button ���� & Ŭ�� �̺�Ʈ �߻� ��
		JButton join_S = new JButton("JOIN");	
		join_S.setBackground(new Color(255, 255, 255));
		join_S.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		join_S.setBounds(450,336,160,55);		
		panel.add(join_S);

		join_S.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				new Join_Frame();
			}
		});

		setContentPane(panel);
		
		JButton join_M = new JButton("\uAD00\uB9AC\uC790\uB85C \uB85C\uADF8\uC778");
		join_M.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		join_M.setBackground(Color.WHITE);
		join_M.setBounds(240, 414, 370, 55);
		panel.add(join_M);

	}
}
