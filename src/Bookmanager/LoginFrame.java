package Bookmanager;

import java.awt.*;
import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginFrame extends JFrame{

	public LoginFrame() {		// 생성자
		// Dimension res = Toolkit.getDefaultToolkit().getScreenSize();	// 화면 크기

		JFrame frame= new JFrame("전공책 대여 사업 프로그램");	// JFrame frame 생성

		// login panel 생성
		JPanel panel = new JPanel();		

		panel.setLayout(null);

		JLabel Title=new JLabel("◆◇ DRAG 전공책 대여 사업 프로그램입니다 ◇◆");
		Title.setBounds(58,40,1000,25);
		panel.add(Title);

		// 학번 text
		JLabel Hakbun_Lable=new JLabel("학번");
		Hakbun_Lable.setBounds(90, 100, 80, 25);	
		panel.add(Hakbun_Lable);

		// pw text
		JLabel pw_Lable = new JLabel("PW");
		pw_Lable.setBounds(90, 130, 80, 25);	
		panel.add(pw_Lable);

		// 학번 text box
		JTextField id = new JTextField(20); // 학번 text box
		id.setBounds(130, 100, 160, 25);	
		panel.add(id);       

		// pw text box
		JPasswordField pw = new JPasswordField(20);
		pw.setBounds(130, 130, 160, 25);	
		panel.add(pw);

		// login button 생성 & 클릭 이벤트 발생 시
		JButton login_B = new JButton("LOGIN");	
		login_B.setBounds(130,170,70,30);		
		panel.add(login_B);

		login_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoginBean loginbean = new LoginBean(id.getText(),pw.getText());
				new LoginDAO(loginbean);
			}
		});

		// join button 생성 & 클릭 이벤트 발생 시
		JButton join_B = new JButton("JOIN");	
		join_B.setBounds(220,170,70,30);		
		panel.add(join_B);

		join_B.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				new JoinFrame();
			}
		});

		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);	

		frame.setSize(400,300);					
		frame.setLocationRelativeTo(null);		
		frame.setResizable(false);				

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

	public static void main(String[] args) {

		// 첫 시작
		new LoginFrame();	
	
	}


}
