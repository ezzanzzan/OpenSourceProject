package Frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Select_Frame extends JFrame {

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
	public Select_Frame() {
		setTitle("전공책 대여 사업 프로그램");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(800,600);						
		setLocationRelativeTo(null);		
		setResizable(false);	
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		JLabel Title=new JLabel("◆◇ DRAG 전공책 대여 사업 프로그램입니다 ◇◆");
		Title.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 25));
		Title.setBounds(152,77,510,41);
		panel.add(Title);
		
		JButton rental_B = new JButton("대여");
		rental_B.setForeground(Color.BLACK);
		rental_B.setBackground(Color.WHITE);
		rental_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 25));
		rental_B.setBounds(208, 230, 160, 160);
		panel.add(rental_B);
		
		// 대여 버튼을 클릭 시 발생하는 이벤트
		rental_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookRental_Frame();
			}
		});
		
		
		JButton return_B = new JButton("반납");
		return_B.setForeground(Color.BLACK);
		return_B.setBackground(Color.WHITE);
		return_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 25));
		return_B.setBounds(438, 230, 160, 160);
		panel.add(return_B);
		
		return_B.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new BookReturn_Frame();
			}
		});

		setContentPane(panel);
		setVisible(true);
		
	}
}
