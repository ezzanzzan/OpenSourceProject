package Frame;

import Bean.*;
import DB.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class Join_Frame extends JFrame {
	
	private Statement stmt = null;
	private ResultSet rs = null;
	private int r = 0;
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
	public Join_Frame() {
		
		setTitle("회원 가입");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(800,800);					
		setLocationRelativeTo(null);		
		setResizable(false);	

		panel = new JPanel(){
			public void paintComponent(Graphics g) {
				Dimension d=getSize();
				ImageIcon image = new ImageIcon("C:\\Users\\천은정\\Desktop\\회원가입.jpg");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setLayout(null);

		Container contentPane=getContentPane();  //배경 이미지
		contentPane.add(panel);

		// 학번 text
		JLabel ID_Label=new JLabel("학번");
		ID_Label.setForeground(Color.WHITE);
		ID_Label.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		ID_Label.setBounds(175, 301, 47, 25);	
		panel.add(ID_Label);

		// 이름 text
		JLabel Name_Label = new JLabel("이름");
		Name_Label.setForeground(Color.WHITE);
		Name_Label.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		Name_Label.setBounds(175, 361, 47, 25);
		panel.add(Name_Label);

		// pw text
		JLabel PW_Label = new JLabel("PW");
		PW_Label.setForeground(Color.WHITE);
		PW_Label.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		PW_Label.setBounds(175,421,47,25);	
		panel.add(PW_Label);

		// phone text
		JLabel Phone_Lable = new JLabel("PHONE");
		Phone_Lable.setForeground(Color.WHITE);
		Phone_Lable.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		Phone_Lable.setBounds(175,481,80,25);	
		panel.add(Phone_Lable);

		// 학번 text box
		JTextField id = new JTextField(20); 
		id.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		id.setBackground(new Color(248, 248, 255));
		id.setBounds(284, 291, 257, 41);	
		panel.add(id);       

		// 이름 text box
		JTextField name = new JTextField(20);
		name.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		name.setBackground(new Color(248, 248, 255));
		name.setBounds(284, 351, 257, 41);	
		panel.add(name);

		// pw text box
		JTextField pw = new JTextField(20);
		pw.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		pw.setBackground(new Color(248, 248, 255));
		pw.setBounds(284, 411, 257, 41);	
		panel.add(pw);

		// phone text box
		JTextField phone = new JTextField(20);
		phone.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		phone.setBackground(new Color(248, 248, 255));
		phone.setBounds(284, 471, 257, 41);	
		panel.add(phone);

		// join button 생성 & 클릭 이벤트 발생 시
		JButton Join_B = new JButton("JOIN");	
		Join_B.setBackground(Color.WHITE);
		Join_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		Join_B.setBounds(284,549,257,50);		
		panel.add(Join_B);

		Join_B.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {

				// 입력된 정보 DB와 연결
				Member joinbean=new Member(id.getText(),name.getText(),pw.getText(),phone.getText(),"","","");
				Join_DB.JoinS_DB(joinbean);
				
				new Login_Frame();
				setVisible(false);		
			}
		});

		
		setVisible(true);		
	}
}
