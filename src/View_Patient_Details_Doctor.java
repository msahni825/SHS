import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;

public class View_Patient_Details_Doctor extends JFrame {

	private JPanel contentPane;
	String name;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					View_Patient_Details_Doctor frame = new View_Patient_Details_Doctor();
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
	public View_Patient_Details_Doctor(String patient_id, String user_name) {
		String name = "";
		String contact = "";
		String location = "";
		String bloodgroup = "";
		String gender = "";
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement s2=c.createStatement();
			ResultSet rs = s2.executeQuery("Select * from patient where uniqueId = '"+patient_id+"'");
			while(rs.next())
			{
				name = rs.getString("name");
				contact = rs.getString("contact");
				location = rs.getString("location");
				bloodgroup = rs.getString("bloodgroup");
				gender = rs.getString("gender");
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
		
		final String name1 = new String(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPatientDetails = new JLabel("Patient Details:");
		lblPatientDetails.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPatientDetails.setBounds(10, 23, 111, 14);
		contentPane.add(lblPatientDetails);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 64, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(10, 89, 60, 14);
		contentPane.add(lblContact);
		
		JLabel lblNewLabel = new JLabel("Bloodgroup");
		lblNewLabel.setBounds(10, 114, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 140, 46, 14);
		contentPane.add(lblGender);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(10, 163, 60, 14);
		contentPane.add(lblLocation);
		
		JLabel lblNewLabel_1 = new JLabel(name);
		lblNewLabel_1.setBounds(140, 64, 222, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(contact);
		lblNewLabel_2.setBounds(140, 89, 230, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(bloodgroup);
		lblNewLabel_3.setBounds(140, 114, 222, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(gender);
		lblNewLabel_4.setBounds(140, 140, 222, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(location);
		lblNewLabel_5.setBounds(140, 163, 222, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btn_Prescribe = new JButton("Prescribe Medicine");
		btn_Prescribe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Diagnosis dg = new Diagnosis(name1, patient_id, user_name);
				dg.setVisible(true);	
			}
		});
		btn_Prescribe.setBounds(256, 227, 168, 23);
		contentPane.add(btn_Prescribe);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(user_name);
				List_Of_Patients lop = new List_Of_Patients(user_name);
				lop.setVisible(true);
				
			}
		});
		btnBack.setBounds(10, 227, 101, 23);
		contentPane.add(btnBack);
		
		JButton btnReferDoctor = new JButton("Refer Doctor");
		btnReferDoctor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ReferDoctor rd;
				try {
					rd = new ReferDoctor(user_name, patient_id);
					rd.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				
			}
		});
		btnReferDoctor.setBounds(128, 227, 111, 23);
		contentPane.add(btnReferDoctor);
		
		JButton btnViewHistory = new JButton("View Patient History");
		btnViewHistory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewPatientHistory vph = new ViewPatientHistory(patient_id, user_name);
				vph.setVisible(true);
				
			}
		});
		btnViewHistory.setBounds(128, 188, 149, 23);
		contentPane.add(btnViewHistory);
		
		
	}
}
