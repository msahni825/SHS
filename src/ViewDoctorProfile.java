import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ViewDoctorProfile extends JFrame {

	/**
	 * Launch the application.
	 */
	String username;
	Logfile lgf=new Logfile();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDoctorProfile frame = new ViewDoctorProfile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewDoctorProfile() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblViewDoctorsProfile = new JLabel("View Doctors Profile By:");
		
		lblViewDoctorsProfile.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblViewDoctorsProfile.setBounds(99, 11, 232, 14);
		getContentPane().add(lblViewDoctorsProfile);
		
		JButton btnCategory = new JButton("Category");
		btnCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//close();
				CategoryPage categorypage = new CategoryPage(username);
				categorypage.setVisible(true);
			}
		});
		
		btnCategory.setBounds(99, 52, 175, 23);
		getContentPane().add(btnCategory);
		
		JButton btnNameOfDoctor = new JButton("Name of Doctor");
		btnNameOfDoctor.setBounds(99, 87, 175, 23);
		getContentPane().add(btnNameOfDoctor);
		
		JButton btnAddressOfDoctor = new JButton("Address of Doctor");
		btnAddressOfDoctor.setBounds(99, 126, 175, 23);
		getContentPane().add(btnAddressOfDoctor);
		
	}

}
		
		

