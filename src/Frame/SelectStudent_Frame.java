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
import javax.swing.UIManager;

public class SelectStudent_Frame extends JFrame {

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
	public SelectStudent_Frame() {
		setTitle("전공책 대여 사업 프로그램");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(700,600);					
		setLocationRelativeTo(null);		
		setResizable(false);	

		panel = new JPanel(){
			public void paintComponent(Graphics g) {
				Dimension d=getSize();
				ImageIcon image = new ImageIcon("C:\\Users\\천은정\\Desktop\\select.jpg");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setLayout(null);

		Container contentPane=getContentPane();  //배경 이미지
		contentPane.add(panel);
		
		JButton rental_B = new JButton("대여");
		rental_B.setForeground(Color.BLACK);
		rental_B.setBackground(Color.WHITE);
		rental_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 25));
		rental_B.setBounds(140, 300, 160, 160);
		panel.add(rental_B);
		
		// 대여 버튼을 클릭 시 발생하는 이벤트
		rental_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookRental_Frame();
				setVisible(false);
			}
		});
		
		JButton return_B = new JButton("반납");
		return_B.setForeground(Color.BLACK);
		return_B.setBackground(Color.WHITE);
		return_B.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 25));
		return_B.setBounds(400, 300, 160, 160);
		panel.add(return_B);
		
		return_B.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new BookReturn_Frame();
				setVisible(false);
			}
		});
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\\uCC9C\uC740\uC815\\Downloads\\back-arrow.png"));
		button.setBounds(45, 39, 68, 65);
		button.setBorderPainted(false);			// 테두리 제거
		button.setContentAreaFilled(false);		// 내용영역 채우기 없음
		button.setFocusPainted(false);			// 선택 되었을 때 테두리 사용 안함
		button.setOpaque(false);				// 투명하게
		panel.add(button);

		button.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login_Frame();
			}
		});


		setVisible(true);
		
	}
}
