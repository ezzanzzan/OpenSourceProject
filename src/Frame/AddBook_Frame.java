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

public class AddBook_Frame extends JFrame {

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
	public AddBook_Frame() {
		
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

		// ������ text
		JLabel bookname_Label=new JLabel("\uB3C4\uC11C\uBA85");
		bookname_Label.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		bookname_Label.setBounds(173, 180, 80, 25);	
		panel.add(bookname_Label);

		// ���� text
		JLabel writer_Label = new JLabel("\uC800\uC790");
		writer_Label.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		writer_Label.setBounds(173, 240, 80, 25);
		panel.add(writer_Label);

		// ���ǻ� text
		JLabel publisher_Label = new JLabel("\uCD9C\uD310\uC0AC");
		publisher_Label.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		publisher_Label.setBounds(173,300,80,25);	
		panel.add(publisher_Label);

		// �ڵ� text
		JLabel code_Lable = new JLabel("\uCF54\uB4DC");
		code_Lable.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		code_Lable.setBounds(173,360,80,25);	
		panel.add(code_Lable);

		// ������ text box
		JTextField bookname = new JTextField(20); 
		bookname.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		bookname.setBackground(new Color(248, 248, 255));
		bookname.setBounds(282, 170, 257, 41);	
		panel.add(bookname);       

		// ���� text box
		JTextField writer = new JTextField(20);
		writer.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		writer.setBackground(new Color(248, 248, 255));
		writer.setBounds(282, 230, 257, 41);	
		panel.add(writer);

		// ���ǻ� text box
		JTextField publisher = new JTextField(20);
		publisher.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		publisher.setBackground(new Color(248, 248, 255));
		publisher.setBounds(282, 290, 257, 41);	
		panel.add(publisher);

		// �ڵ� text box
		JTextField code = new JTextField(20);
		code.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		code.setBackground(new Color(248, 248, 255));
		code.setBounds(282, 350, 257, 41);	
		panel.add(code);

		// �߰� ��ư ���� & Ŭ�� �̺�Ʈ �߻� ��
		JButton Add_B = new JButton("�߰��ϱ�");	
		Add_B.setBackground(Color.WHITE);
		Add_B.setFont(new Font("���õ������ Bold", Font.PLAIN, 20));
		Add_B.setBounds(282,428,257,50);		
		panel.add(Add_B);

		Add_B.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				String str1 = bookname.getText();
				String str2 = writer.getText();
				String str3 = publisher.getText();
				String str4 = code.getText();
				Book_DB.bookSave_DB(str1, str2, str3, str4);
				setVisible(false);
	
			}
		});
		
		setContentPane(panel);
		setVisible(true);
	}

}
