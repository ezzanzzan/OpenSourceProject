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
		setTitle("전공책 대여 사업 프로그램");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setSize(800,800);					
		setLocationRelativeTo(null);		
		setResizable(false);	

		panel = new JPanel(){
			public void paintComponent(Graphics g) {
				Dimension d=getSize();
				ImageIcon image = new ImageIcon("C:\\Users\\천은정\\Desktop\\배경화면.jpg");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setLayout(null);

		Container contentPane=getContentPane();  //배경 이미지
		contentPane.add(panel);

		// 학번 text
		JLabel Hakbun_Lable=new JLabel("학번");
		Hakbun_Lable.setForeground(Color.WHITE);
		Hakbun_Lable.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		Hakbun_Lable.setBounds(145, 324, 40, 25);	
		panel.add(Hakbun_Lable);

		// pw text
		JLabel pw_Lable = new JLabel("PW");
		pw_Lable.setForeground(Color.WHITE);
		pw_Lable.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		pw_Lable.setBounds(145, 404, 40, 25);	
		panel.add(pw_Lable);

		// 학번 text box
		JTextField id = new JTextField(20); // 학번 text box
		id.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		id.setBackground(new Color(248, 248, 255));
		id.setBounds(235, 314, 370, 45);	
		panel.add(id);       

		// pw text box
		JPasswordField pw = new JPasswordField(20);
		pw.setFont(new Font("굴림", Font.PLAIN, 20));
		pw.setBackground(new Color(248, 248, 255));
		pw.setBounds(235, 394, 370, 45);	
		panel.add(pw);

		// login button 생성 & 클릭 이벤트 발생 시
		JButton login_B = new JButton("LOGIN");	
		login_B.setBackground(new Color(255, 255, 255));
		login_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		login_B.setBounds(234,465,160,55);		
		panel.add(login_B);

		// 학생으로 로그인
		login_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login_DB.LoginS_DB(id.getText(),pw.getText());
				setVisible(false);	
			}
		});


		// join button 생성 & 클릭 이벤트 발생 시
		JButton join_S = new JButton("JOIN");	
		join_S.setBackground(new Color(255, 255, 255));
		join_S.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		join_S.setBounds(445,465,160,55);		
		panel.add(join_S);

		join_S.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				new Join_Frame();
				setVisible(false);
			}
		});

		JButton join_M = new JButton("관리자로 로그인");
		join_M.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		join_M.setBackground(Color.WHITE);
		join_M.setBounds(235, 543, 222, 55);
		panel.add(join_M);
		
		// 관리자로 로그인
		join_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login_DB.LoginA_DB(id.getText(), pw.getText());
				setVisible(false);	
			}
		});

		// 관리자 id,pw 초기화
		JButton reset_B = new JButton("\uCD08\uAE30\uD654");
		reset_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
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
