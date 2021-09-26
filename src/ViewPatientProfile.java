import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;

public class ViewPatientProfile extends JFrame {

	private JPanel contentPane;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewPatientProfile frame = new ViewPatientProfile();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public void close()
	{
		WindowEvent closeEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeEvent);
	}
	
	public ViewPatientProfile(String user_name) {
		
		String name = "";
		String address = "";
		String contact = "";
		String email = "";
		String bloodgroup = "";
		String gender = "";
		String location = "";
		String username = "";
		String department = "";
		System.out.println(user_name + "In view patient page");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement s2=c.createStatement();
			ResultSet rs = s2.executeQuery("Select * from patient where uniqueId = '"+user_name+"'");
			while(rs.next())
			{
				name = rs.getString("name");
				address = rs.getString("address");
				contact = rs.getString("contact");
				email = rs.getString("email");
				bloodgroup = rs.getString("bloodgroup");
				gender = rs.getString("gender");
				location = rs.getString("location");
				username = rs.getString("uniqueId");
				department = rs.getString("department");
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
		System.out.println(name + address + contact + email);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourProfile = new JLabel("Your Profile");
		lblYourProfile.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblYourProfile.setBounds(27, 11, 82, 14);
		contentPane.add(lblYourProfile);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblName.setBounds(27, 50, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel.setBounds(27, 85, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contact");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(27, 120, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(27, 160, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Bloodgroup");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(27, 198, 62, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblGender.setBounds(27, 234, 46, 14);
		contentPane.add(lblGender);
		
		JLabel lblNewLabel_4 = new JLabel("Location");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(27, 275, 62, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Department");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(27, 311, 62, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblUniqueid = new JLabel("UniqueID");
		lblUniqueid.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblUniqueid.setBounds(27, 349, 62, 14);
		contentPane.add(lblUniqueid);
		
		JLabel lblNewLabel_6 = new JLabel(address);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(151, 85, 217, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel label = new JLabel(name);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label.setBounds(151, 50, 217, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel(contact);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_1.setBounds(151, 120, 217, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel(email);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_2.setBounds(151, 160, 217, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel(bloodgroup);
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_3.setBounds(151, 198, 217, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel(gender);
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_4.setBounds(151, 234, 217, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel(location);
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_5.setBounds(151, 275, 217, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel(department);
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_6.setBounds(151, 311, 217, 14);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel(username);
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_7.setBounds(151, 349, 217, 14);
		contentPane.add(label_7);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				PatientPage pp = new PatientPage(user_name);
				System.out.println(user_name);
				pp.setVisible(true);
				
			}
		});
		btnBack.setBounds(76, 395, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditPatientProfile epp = new EditPatientProfile(user_name);
				epp.setVisible(true);
				
			}
		});
		btnEdit.setBounds(267, 395, 89, 23);
		contentPane.add(btnEdit);
	}

}
