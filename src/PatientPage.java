import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PatientPage extends JFrame {
	private JPanel contentPane;

	Logfile lgf=new Logfile(); 
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PatientPage frame = new PatientPage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	String username;
	public void close()
	{
		WindowEvent closeEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeEvent);
	}

	/**
	 * Create the frame.
	 */
	public PatientPage(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setBounds(61, 36, 127, 16);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblWelcome);
		
		JButton btnSearchDoctor = new JButton("Search Doctor");
		btnSearchDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//close();
				SearchDoctor searchdoctor = new SearchDoctor(username);
				searchdoctor.setVisible(true);
			}
		});
		
		btnSearchDoctor.setBounds(61, 63, 297, 33);
		contentPane.add(btnSearchDoctor);
		
		JButton viewYourProfile = new JButton("View Profile");
		viewYourProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(username + "IN Patient page");
				ViewPatientProfile vpp = new ViewPatientProfile(username);
				vpp.setVisible(true);
			}
		});
		
		
		viewYourProfile.setBounds(61, 107, 297, 30);
		contentPane.add(viewYourProfile);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				start st = new start();
				st.setVisible(true);
				
			}
		});
		btnLogout.setBounds(61, 148, 297, 30);
		contentPane.add(btnLogout);
		
		JButton btnAutoAssignDoctor = new JButton("Auto Assign Doctor");
		btnAutoAssignDoctor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AutoAssignDoctor aad;
				try {
					aad = new AutoAssignDoctor(username);
					aad.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				
				
			}
		});
		btnAutoAssignDoctor.setBounds(61, 189, 297, 33);
		contentPane.add(btnAutoAssignDoctor);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
				login_Patient lp = new login_Patient();
				lp.setVisible(true);
				
			}
		});
		
		btnBack.setBounds(161, 255, 89, 23);
		contentPane.add(btnBack);
	}

}
