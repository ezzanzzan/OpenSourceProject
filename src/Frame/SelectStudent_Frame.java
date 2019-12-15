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
		setTitle("����å �뿩 ��� ���α׷�");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(700,600);					
		setLocationRelativeTo(null);		
		setResizable(false);	

		panel = new JPanel(){
			public void paintComponent(Graphics g) {
				Dimension d=getSize();
				ImageIcon image = new ImageIcon("C:\\Users\\õ����\\Desktop\\select.jpg");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setLayout(null);

		Container contentPane=getContentPane();  //��� �̹���
		contentPane.add(panel);
		
		JButton rental_B = new JButton("�뿩");
		rental_B.setForeground(Color.BLACK);
		rental_B.setBackground(Color.WHITE);
		rental_B.setFont(new Font("���õ������ Bold", Font.PLAIN, 25));
		rental_B.setBounds(140, 300, 160, 160);
		panel.add(rental_B);
		
		// �뿩 ��ư�� Ŭ�� �� �߻��ϴ� �̺�Ʈ
		rental_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookRental_Frame();
				setVisible(false);
			}
		});
		
		JButton return_B = new JButton("�ݳ�");
		return_B.setForeground(Color.BLACK);
		return_B.setBackground(Color.WHITE);
		return_B.setFont(new Font("���õ������ Bold", Font.PLAIN, 25));
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
		button.setBorderPainted(false);			// �׵θ� ����
		button.setContentAreaFilled(false);		// ���뿵�� ä��� ����
		button.setFocusPainted(false);			// ���� �Ǿ��� �� �׵θ� ��� ����
		button.setOpaque(false);				// �����ϰ�
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
