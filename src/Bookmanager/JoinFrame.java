package Bookmanager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class JoinFrame extends JFrame {
	private Statement stmt = null;
	private ResultSet rs = null;
	private int r = 0;

	public JoinFrame() {


		JFrame frame= new JFrame("회원 가입");	// JFrame frame 생성

		// JPanel panel 생성
		JPanel panel = new JPanel();					

		panel.setLayout(null);

		JLabel Title=new JLabel("◆◇ 회원가입 ◇◆");
		Title.setBounds(147,30,1000,25);
		panel.add(Title);

		// 학번 text
		JLabel ID_Label=new JLabel("학번");
		ID_Label.setBounds(90, 70, 80, 25);	
		panel.add(ID_Label);

		// 이름 text
		JLabel Name_Label = new JLabel("이름");
		Name_Label.setBounds(90, 100, 80, 25);
		panel.add(Name_Label);

		// pw text
		JLabel PW_Label = new JLabel("PW");
		PW_Label.setBounds(90,130,80,25);	
		panel.add(PW_Label);

		// phone text
		JLabel Phone_Lable = new JLabel("PHONE");
		Phone_Lable.setBounds(80,160,80,25);	
		panel.add(Phone_Lable);

		// 학번 text box
		JTextField id = new JTextField(20); 
		id.setBounds(130, 70, 160, 25);	
		panel.add(id);       

		// 이름 text box
		JTextField name = new JTextField(20);
		name.setBounds(130, 100, 160, 25);	
		panel.add(name);

		// pw text box
		JTextField pw = new JTextField(20);
		pw.setBounds(130, 130, 160, 25);	
		panel.add(pw);

		// phone text box
		JTextField phone = new JTextField(20);
		phone.setBounds(130, 160, 160, 25);	
		panel.add(phone);

		// join button 생성 & 클릭 이벤트 발생 시
		JButton Join_B = new JButton("Join");	
		Join_B.setBounds(160,200,70,30);		
		panel.add(Join_B);

		Join_B.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {

				// 입력된 정보 DB와 연결
				JoinBean joinbean=new JoinBean(id.getText(),name.getText(),pw.getText(),phone.getText());
				new JoinDAO(joinbean);
				
				frame.setVisible(false);		
			}
		});

		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);					

		frame.setSize(400,300);					
		frame.setLocationRelativeTo(null);		
		frame.setResizable(false);				
	}


	public static void main(String[] args) {

	}

}
