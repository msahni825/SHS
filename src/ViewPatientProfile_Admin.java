import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;

public class ViewPatientProfile_Admin extends JFrame {

	private JPanel contentPane;
	Logfile lgf=new Logfile();
	
	JButton btnReassignmentDoctor;
	JButton btnDischargePatient_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewPatientProfile_Admin frame = new ViewPatientProfile_Admin();
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
	public ViewPatientProfile_Admin(String patient_id, Boolean flag) throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
		String name = "";
		String unique_id = "";
		String contact = "";
		String address = "";
		String email = "";
		String bloodgroup = "";
		String gender = "";
		String location = "";
		String department = "";
		String patienttype = "";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Statement st;
			
			st = c.createStatement();
			ResultSet rs = st.executeQuery("Select * from patient where uniqueId = '"+patient_id+"'");
			
			while(rs.next())
			{
				name = rs.getString("name");
				contact = rs.getString("contact");
				address = rs.getString("address");
				email = rs.getString("email");
				bloodgroup = rs.getString("bloodgroup");
				department = rs.getString("department");
				gender = rs.getString("gender");
				location = rs.getString("location");
				patienttype = rs.getString("patient_type");
			}
		}
		
			// TODO Auto-generated catch block
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception is here!!");
				lgf.logfile(" Exception Caught");

					
			}
		
		final String name1 = new String(name);
		//final String patienttype1 = new String(patienttype);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPatientProfile = new JLabel("Patient Profile");
		lblPatientProfile.setBounds(10, 11, 94, 16);
		lblPatientProfile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		contentPane.add(lblPatientProfile);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 51, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setBounds(10, 76, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel(unique_id);
		label.setBounds(114, 12, 46, 14);
		contentPane.add(label);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(10, 101, 46, 14);
		contentPane.add(lblContact);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(10, 126, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("BloodGroup");
		lblNewLabel_2.setBounds(10, 151, 62, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 176, 46, 14);
		contentPane.add(lblGender);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(10, 201, 46, 14);
		contentPane.add(lblLocation);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 226, 62, 14);
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
		
		JLabel lblNewLabel_7 = new JLabel(bloodgroup);
		lblNewLabel_7.setBounds(146, 151, 247, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(gender);
		lblNewLabel_8.setBounds(146, 176, 247, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(location);
		lblNewLabel_9.setBounds(146, 201, 247, 14);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(department);
		lblNewLabel_10.setBounds(146, 226, 247, 14);
		contentPane.add(lblNewLabel_10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!flag)
				{
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
				else
				{
					try {
						DischargePatient dp = new DischargePatient();
						dp.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Exception is here!!");
						lgf.logfile(" Exception Caught");

					}
				}
				
				
			}
		});
		btnBack.setBounds(10, 297, 69, 23);
		contentPane.add(btnBack);
		
		
//		if(flag)
//		{
//			btnReassignmentDoctor.setVisible(false);
//			btnDischargePatient_1.setVisible(true);
//			
//		}
//		else
//		{
//			btnDischargePatient_1.setVisible(false);
//			btnReassignmentDoctor.setVisible(true);
//		}
//		
		btnReassignmentDoctor = new JButton("ReAssignment Doctor");
		btnReassignmentDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReassignmentDoctor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
					ReAssignment_Doctor rad;
					try {
						rad = new ReAssignment_Doctor(patient_id);
						rad.setVisible(true);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Exception is here!!");
						lgf.logfile(" Exception Caught");

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Exception is here!!");
						lgf.logfile(" Exception Caught");

					}		
			}
		});
		btnReassignmentDoctor.setBounds(89, 297, 169, 23);
		contentPane.add(btnReassignmentDoctor);
		
		btnDischargePatient_1 = new JButton("Discharge Patient");
		btnDischargePatient_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDischargePatient_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Statement st;
					st = c.createStatement();
					int rs = st.executeUpdate("Delete from patient where uniqueId = '"+patient_id+"'");
					int rs1 = st.executeUpdate("Delete from loginpatient where uniqueId = '"+patient_id+"'");
					System.out.println(rs);
					
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}	
				JOptionPane.showMessageDialog(contentPane, "DISCHARGED DONE of...!\n" + name1,"Success", JOptionPane.INFORMATION_MESSAGE);		
				//System.out.println(rs);
				
		}
	});
	
		btnDischargePatient_1.setBounds(268, 297, 156, 23);
		contentPane.add(btnDischargePatient_1);
			
	}
}

