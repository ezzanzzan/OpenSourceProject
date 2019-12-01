package Frame;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Bean.*;
import DB.*;

public class AdminJoin_Frame extends JFrame {

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
	public AdminJoin_Frame() {

		setTitle("회원 가입");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(800,800);					
		setLocationRelativeTo(null);		
		setResizable(false);	

		panel = new JPanel(){
			public void paintComponent(Graphics g) {
				Dimension d=getSize();
				ImageIcon image = new ImageIcon("C:\\Users\\천은정\\Desktop\\관리자로그인.jpg");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setLayout(null);

		Container contentPane=getContentPane();  //배경 이미지
		contentPane.add(panel);

		// id text
		JLabel ID_Label=new JLabel("ID");
		ID_Label.setForeground(Color.WHITE);
		ID_Label.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		ID_Label.setBounds(176, 324, 47, 25);	
		panel.add(ID_Label);

		// pw text
		JLabel PW_Label = new JLabel("PW");
		PW_Label.setForeground(Color.WHITE);
		PW_Label.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		PW_Label.setBounds(176,391,47,25);	
		panel.add(PW_Label);

		// id text box
		JTextField id = new JTextField(20); 
		id.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		id.setBackground(new Color(248, 248, 255));
		id.setBounds(285, 314, 257, 41);	
		panel.add(id);

		// pw text box
		JTextField pw = new JTextField(20);
		pw.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		pw.setBackground(new Color(248, 248, 255));
		pw.setBounds(285, 383, 257, 41);	
		panel.add(pw);

		// join button 생성 & 클릭 이벤트 발생 시
		JButton Join_B = new JButton("JOIN");	
		Join_B.setBackground(Color.WHITE);
		Join_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		Join_B.setBounds(285,517,257,50);		
		panel.add(Join_B);

		Join_B.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {

				// 입력된 정보 DB와 연결
				Admin joinbean=new Admin(id.getText(),pw.getText());
				Join_DB.JoinA_DB(joinbean);
				
				new Login_Frame();
				setVisible(false);		
			}
		});
		
		setVisible(true);		
	}

}
