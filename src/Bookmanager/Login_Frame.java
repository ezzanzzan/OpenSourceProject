package Bookmanager;

import Bookmanager.*;
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
		setTitle("전공책 대여 사업 프로그램");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(400,300);					
		setLocationRelativeTo(null);		
		setResizable(false);	

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		JLabel Title=new JLabel("◆◇ DRAG 전공책 대여 사업 프로그램입니다 ◇◆");
		Title.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 13));
		Title.setBounds(58,40,1000,25);
		panel.add(Title);

		// 학번 text
		JLabel Hakbun_Lable=new JLabel("학번");
		Hakbun_Lable.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 12));
		Hakbun_Lable.setBounds(90, 101, 80, 25);	
		panel.add(Hakbun_Lable);

		// pw text
		JLabel pw_Lable = new JLabel("PW");
		pw_Lable.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 12));
		pw_Lable.setBounds(90, 130, 80, 25);	
		panel.add(pw_Lable);

		// 학번 text box
		JTextField id = new JTextField(20); // 학번 text box
		id.setBackground(new Color(248, 248, 255));
		id.setBounds(130, 100, 160, 25);	
		panel.add(id);       

		// pw text box
		JPasswordField pw = new JPasswordField(20);
		pw.setBackground(new Color(248, 248, 255));
		pw.setBounds(130, 130, 160, 25);	
		panel.add(pw);

		// login button 생성 & 클릭 이벤트 발생 시
		JButton login_B = new JButton("LOGIN");	
		login_B.setBackground(new Color(255, 255, 255));
		login_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 11));
		login_B.setBounds(130,170,70,30);		
		panel.add(login_B);

		login_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login_Bean loginbean = new Login_Bean(id.getText(),pw.getText());
				new Login_DAO(loginbean);
				
				setVisible(false);	

				
			}
		});

		// join button 생성 & 클릭 이벤트 발생 시
		JButton join_B = new JButton("JOIN");	
		join_B.setBackground(new Color(255, 255, 255));
		join_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 11));
		join_B.setBounds(220,170,70,30);		
		panel.add(join_B);

		join_B.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				new Join_Frame();
			}
		});

		setContentPane(panel);
	}

}
