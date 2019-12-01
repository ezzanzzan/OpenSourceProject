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
import javax.swing.border.EmptyBorder;

public class SelectAdmin_Frame extends JFrame {

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
	public SelectAdmin_Frame() {
		setTitle("����å �뿩 ��� ���α׷�");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(800,600);						
		setLocationRelativeTo(null);		
		setResizable(false);	

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);

		JLabel Title=new JLabel("�ߡ� DRAG ����å �뿩 ��� ���α׷��Դϴ� �ޡ�");
		Title.setFont(new Font("���õ������ Bold", Font.PLAIN, 25));
		Title.setBounds(152,77,510,41);
		panel.add(Title);

		JButton student_B = new JButton("�л� ����");
		student_B.setForeground(Color.BLACK);
		student_B.setBackground(Color.WHITE);
		student_B.setFont(new Font("���õ������ Bold", Font.PLAIN, 25));
		student_B.setBounds(208, 230, 160, 160);
		panel.add(student_B);

		// �л� ���� ��ư�� Ŭ�� �� �߻��ϴ� �̺�Ʈ
		student_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMember_Frame();
				setVisible(false);
			}
		});

		JButton book_B = new JButton("���� ����");
		book_B.setForeground(Color.BLACK);
		book_B.setBackground(Color.WHITE);
		book_B.setFont(new Font("���õ������ Bold", Font.PLAIN, 25));
		book_B.setBounds(438, 230, 160, 160);
		panel.add(book_B);

		// ���� ���� ��ư�� Ŭ�� �� �߻��ϴ� �̺�Ʈ
		book_B.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new AdminBook_Frame();
				setVisible(false);
			}
		});

		setContentPane(panel);
		setVisible(true);

	}
}

