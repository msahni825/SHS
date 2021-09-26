import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindByNamePage extends JFrame {
	
	private JPanel contentPane;
	DefaultListModel model;
	private JTextField textField;
	Logfile lgf=new Logfile();
	

	/**
	 * Create the frame.
	 */
	public FindByNamePage(String username) {
		//fillComboBoxId();
		model = new DefaultListModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		System.out.println("vcx");
		//System.out.println(id);
		
		JLabel label = new JLabel("");
		label.setBounds(22, 131, 46, 14);
		contentPane.add(label);
		
		
		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	            //System.out.println("Index Selected: " + index);
	            String s = textField.getText().toString();
	            System.out.println("Value Selected: " + s);
//	            SelectedDoctorProfileDetails details =  new SelectedDoctorProfileDetails(s);
	            SelectedDoctorProfileDetails details = new SelectedDoctorProfileDetails(s, username);
	            details.setVisible(true);
			}
		});
		btnView.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnView.setBounds(60, 91, 89, 20);
		contentPane.add(btnView);
		
		JLabel lblSelectDrBy = new JLabel("Select DR. by NAME:");
		lblSelectDrBy.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectDrBy.setBounds(49, 11, 156, 28);
		contentPane.add(lblSelectDrBy);
		
		textField = new JTextField();
		
		textField.setBounds(203, 16, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SearchDoctor sd = new SearchDoctor(username);
				sd.setVisible(true);
			}
		});
		
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(234, 91, 89, 23);
		contentPane.add(btnBack);

			}
}