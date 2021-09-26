import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ReAssignment_Doctor extends JFrame {

	private JPanel contentPane;
	JComboBox comboBox;
	JList list;
	DefaultListModel model;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	
	public void fillComboBox() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
		Statement s2=c.createStatement();
		
		ResultSet rs2 = s2.executeQuery("Select * from department");
		while(rs2.next())
		{
			String dept_name = rs2.getString("dept_name");
			comboBox.addItem(dept_name);
		}
		
	}
	public void fillJList(JList list, String selected_category)
	{
		try
		{
			model.clear();
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM doctor WHERE category = '"+selected_category+"'");

			while(rs.next())
			{
				String name = rs.getString("name");
				int count = rs.getInt("patients_count");
				if (count > 0)
				{
					model.addElement(name);
				}
			}
			list.setModel(model);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
	}
	public ReAssignment_Doctor(String patient_id) throws ClassNotFoundException, SQLException {

		
		model = new DefaultListModel();
		 list = new JList();
		 list.setVisible(false);
		 
		String allotted_doctor_username = "";
		String allotted_doctor_name = "";
		String patient_name = "";
		
		comboBox = new JComboBox();
		fillComboBox();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement s2=c.createStatement();
			ResultSet rs = s2.executeQuery("Select * from patient where uniqueId = '"+patient_id+"'");
			while(rs.next())
			{
				allotted_doctor_username = rs.getString("doctor_username");
				patient_name = rs.getString("name");
				
			}
			rs.close();
			
			ResultSet rs1 = s2.executeQuery("Select * from doctor where username = '"+allotted_doctor_username+"'");
			while(rs1.next())
			{
				allotted_doctor_name = rs1.getString("name");
			}
			rs1.close();
			
			
			
		}
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
		
		JLabel lblReassignmentDoctor = new JLabel("Reassignment Doctor");
		lblReassignmentDoctor.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblReassignmentDoctor.setBounds(147, 29, 165, 21);
		contentPane.add(lblReassignmentDoctor);
		
		JLabel lblNewLabel = new JLabel("Patient Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 62, 104, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Allotted Doctor");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 87, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 112, 104, 14);
		contentPane.add(lblDepartment);
		
		JLabel lblDoctorsList = new JLabel("Doctors List");
		lblDoctorsList.setBounds(10, 137, 104, 14);
		contentPane.add(lblDoctorsList);
		
		
		list.setBounds(147, 136, 277, 72);
		contentPane.add(list);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				list.setVisible(true);
				String selected_id = comboBox.getSelectedItem().toString();
				System.out.println(selected_id);
				fillJList(list, selected_id);
			}
		});
		comboBox.setBounds(147, 109, 277, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel(allotted_doctor_name);
		lblNewLabel_2.setBounds(147, 87, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(patient_name);
		lblNewLabel_3.setBounds(147, 61, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnReassign = new JButton("Reassign");
		btnReassign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selected_name = list.getSelectedValue().toString();
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
					Statement s2=c.createStatement();
					ResultSet rs = s2.executeQuery("Select * from doctor where name = '"+selected_name+"'");
					String us_name = "";
					int old_count = -1;
					while(rs.next())
					{
						us_name = rs.getString("username");
						old_count = rs.getInt("patients_count");
					}
					old_count = old_count -1 ;
					rs.close();
					s2.executeUpdate("Update patient SET doctor_username='"+us_name+"' where uniqueId='"+patient_id+"'");
					s2.executeUpdate("Update doctor SET patients_count='"+old_count+"' where username ='"+us_name+"'");
					
					String old_doc_user = "";
					ResultSet rss = s2.executeQuery("Select * from patient where uniqueId = '"+patient_id+"'");
					while(rss.next())
					{
						old_doc_user = rss.getString("doctor_username");
						
					}
					ResultSet rst = s2.executeQuery("Select * from doctor where username = '"+old_doc_user+"'");
					int cnt = -1;
					while(rst.next())
					{
						cnt = rst.getInt("patients_count");
					}
					cnt = cnt + 1;
					s2.executeUpdate("Update doctor SET patients_count='"+cnt+"' where username ='"+old_doc_user+"'");
					JOptionPane.showMessageDialog(contentPane, "REASSIGNMENT DONE...!\n" + "New Assigned Doctor: " + selected_name,"Success", JOptionPane.INFORMATION_MESSAGE);
				}	
				catch(Exception e1)
				{
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
			}
		});
		btnReassign.setBounds(147, 227, 89, 23);
		contentPane.add(btnReassign);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewPatientProfile_Admin vppa;
				try 
				{
					vppa = new ViewPatientProfile_Admin(patient_id, false);
					vppa.setVisible(true);
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
			}
		});
		btnBack.setBounds(307, 227, 89, 23);
		contentPane.add(btnBack);
	}
}
