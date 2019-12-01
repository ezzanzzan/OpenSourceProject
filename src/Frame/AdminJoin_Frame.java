package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		setSize(800,600);	
		setLocationRelativeTo(null);	
		setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		panel.setLayout(null);

		JLabel Title=new JLabel("\u25C6\u25C7 \uAD00\uB9AC\uC790 \uD68C\uC6D0\uAC00\uC785 \u25C7\u25C6");
		Title.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 25));
		Title.setBounds(269,59,292,65);
		panel.add(Title);

		// id text
		JLabel ID_Label=new JLabel("ID");
		ID_Label.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		ID_Label.setBounds(173, 180, 47, 25);	
		panel.add(ID_Label);

		// pw text
		JLabel PW_Label = new JLabel("PW");
		PW_Label.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		PW_Label.setBounds(173,247,47,25);	
		panel.add(PW_Label);

		// id text box
		JTextField id = new JTextField(20); 
		id.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		id.setBackground(new Color(248, 248, 255));
		id.setBounds(282, 170, 257, 41);	
		panel.add(id);

		// pw text box
		JTextField pw = new JTextField(20);
		pw.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		pw.setBackground(new Color(248, 248, 255));
		pw.setBounds(282, 239, 257, 41);	
		panel.add(pw);

		// join button 생성 & 클릭 이벤트 발생 시
		JButton Join_B = new JButton("JOIN");	
		Join_B.setBackground(Color.WHITE);
		Join_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 20));
		Join_B.setBounds(282,428,257,50);		
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
		
		setContentPane(panel);
		
		setVisible(true);		
	}

}
