import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.sql.*;

public class login_Patient extends JFrame {

	private JPanel contentPane;
	private JTextField username_Patient;
	private JPasswordField passwordField_Patient;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_Patient frame = new login_Patient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void close()
	{
		WindowEvent closeEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeEvent);
	}


	/**
	 * Create the frame.
	 */
	public login_Patient() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPatientLogin = new JLabel("Patient Login:");
		lblPatientLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPatientLogin.setBounds(43, 47, 158, 22);
		contentPane.add(lblPatientLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(43, 110, 62, 14);
		contentPane.add(lblUsername);
		
		username_Patient = new JTextField();
		username_Patient.setBounds(115, 107, 255, 20);
		contentPane.add(username_Patient);
		username_Patient.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(43, 149, 62, 14);
		contentPane.add(lblPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String userId = "";
				String password = "";
				String username = username_Patient.getText();
				String pass = passwordField_Patient.getText();
				System.out.println(username + pass);
				if(username.equals("")|| pass.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please enter missing details.");
				}
				else
				{	
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
				
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
					Statement st=c.createStatement();
					ResultSet rs=st.executeQuery("Select * from loginPatient where uniqueId = '"+username+"'");
					while(rs.next())
					{
						userId = rs.getString("uniqueId");
						password = rs.getString("password");
						
					}
				} 
				catch (ClassNotFoundException | SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				System.out.println(userId + password + " " + username + pass);
				if(username.equals(userId) && pass.equals(password))
				{
					close();
					PatientPage pp = new PatientPage(username);
					pp.setVisible(true);
				}
				else
				{
					username_Patient.setText("");
					passwordField_Patient.setText("");
					JOptionPane.showMessageDialog(contentPane, "Invalid Credentials!!","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			}
		});
		
		btnSubmit.setBounds(112, 207, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnRegister = new JButton("Register");
		
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RegisterPatient registerPatient = new RegisterPatient();
				registerPatient.setVisible(true);
			}
		});
		
		btnRegister.setBounds(265, 207, 89, 23);
		contentPane.add(btnRegister);
		
		passwordField_Patient = new JPasswordField();
		passwordField_Patient.setBounds(115, 146, 255, 20);
		contentPane.add(passwordField_Patient);
	}

}
