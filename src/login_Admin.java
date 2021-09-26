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

public class login_Admin extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField passwordField;
	Logfile lgf=new Logfile();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_Admin frame = new login_Admin();
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
	public login_Admin() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminLogin = new JLabel("Admin Login: ");
		lblAdminLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAdminLogin.setBounds(39, 46, 125, 20);
		contentPane.add(lblAdminLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(39, 106, 69, 14);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.setBounds(110, 103, 253, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(39, 150, 61, 14);
		contentPane.add(lblPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String password = passwordField.getText();
				String user_name = username.getText();
				
				if(user_name.equals("hello") && password.equals("qwerty"))
				{
					close();
					AdminPage adminPage = new AdminPage();
					adminPage.setVisible(true);
				}
				else
				{
					username.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(contentPane, "Invalid Username or Password..!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSubmit.setBounds(168, 204, 89, 23);
		contentPane.add(btnSubmit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(110, 147, 253, 20);
		contentPane.add(passwordField);
		
	}

}
