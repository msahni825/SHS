import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class ViewDoctorProfile_Admin extends JFrame {

	private JPanel contentPane;
	Logfile lgf=new Logfile();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewDoctorProfile_Admin frame = new ViewDoctorProfile_Admin();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ViewDoctorProfile_Admin(String doctor_username) throws SQLException {
		
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
		String name = "";
		String contact = "";
		String address = "";
		String email = "";
		String doc_pos = "";
		String timings = "";
		
		String category = "";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Statement st;
			
			st = c.createStatement();
			ResultSet rs = st.executeQuery("Select * from doctor where username = '"+doctor_username+"'");
			
			while(rs.next())
			{
				name = rs.getString("name");
				contact = rs.getString("contact");
				address = rs.getString("address");
				email = rs.getString("email");
				doc_pos = rs.getString("doctor_position");
				category = rs.getString("category");
				timings = rs.getString("timings");
				
			}
		}
			// TODO Auto-generated catch block
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception is here!!");
				lgf.logfile(" Exception Caught");

					
			}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblPatientProfile = new JLabel("Doctor Profile");
		lblPatientProfile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPatientProfile.setBounds(10, 11, 94, 16);
		contentPane.add(lblPatientProfile);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 51, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setBounds(10, 76, 46, 14);
		contentPane.add(lblNewLabel);
				
		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(10, 101, 46, 14);
		contentPane.add(lblContact);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(10, 126, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblGender = new JLabel("Department");
		lblGender.setBounds(10, 151, 62, 14);
		contentPane.add(lblGender);
		
		JLabel lblLocation = new JLabel("Position");
		lblLocation.setBounds(10, 176, 46, 14);
		contentPane.add(lblLocation);
		
		JLabel lblDepartment = new JLabel("Timings");
		lblDepartment.setBounds(10, 201, 62, 14);
		contentPane.add(lblDepartment);
		
		JLabel lblNewLabel_3 = new JLabel(name);
		lblNewLabel_3.setBounds(146, 51, 247, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(address);
		lblNewLabel_4.setBounds(146, 76, 247, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(contact);
		lblNewLabel_5.setBounds(146, 101, 247, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(email);
		lblNewLabel_6.setBounds(146, 126, 247, 14);
		contentPane.add(lblNewLabel_6);
		

		
		JLabel lblNewLabel_8 = new JLabel(category);
		lblNewLabel_8.setBounds(146, 151, 247, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(doc_pos);
		lblNewLabel_9.setBounds(146, 176, 247, 14);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(timings);
		lblNewLabel_10.setBounds(146, 201, 247, 14);
		contentPane.add(lblNewLabel_10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ViewDetails_Admin vda;
				try {
					vda = new ViewDetails_Admin();
					vda.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				
			}
		});
		btnBack.setBounds(175, 226, 89, 23);
		contentPane.add(btnBack);

	}
}
