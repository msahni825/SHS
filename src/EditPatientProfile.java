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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class EditPatientProfile extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField contactField;
	private JTextField emailField;
	JComboBox dept_comboBox = new JComboBox();
	JRadioButton rdbtnMale;
	JRadioButton rdbtnFemale;
	Logfile lgf=new Logfile();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditPatientProfile frame = new EditPatientProfile();
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
	
	private void fillComboBoxDept()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("Select * from department");
			while(rs.next())
			{
				String dept_name = rs.getString("dept_name");
				dept_comboBox.addItem(dept_name);
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
	}
	
	public EditPatientProfile(String user_name) {
		
		String name = "";
		String address = "";
		String contact = "";
		String email = "";
		String bloodgroup = "";
		String department = "";
		String gender = "";
		
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
				department = rs.getString("department");
				gender = rs.getString("gender");
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Your Profile");
		label.setFont(new Font("Times New Roman", Font.BOLD, 13));
		label.setBounds(23, 11, 82, 14);
		contentPane.add(label);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(23, 50, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(23, 86, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(23, 126, 46, 14);
		contentPane.add(lblContact);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(23, 167, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNewLabel = new JLabel("Gender");
		lblNewLabel.setBounds(23, 212, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(23, 289, 66, 14);
		contentPane.add(lblDepartment);
		
		nameField = new JTextField(name);
		nameField.setBounds(157, 47, 208, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		addressField = new JTextField(address);
		addressField.setColumns(10);
		addressField.setBounds(157, 86, 208, 20);
		contentPane.add(addressField);
		
		contactField = new JTextField(contact);
		contactField.setColumns(10);
		contactField.setBounds(157, 123, 208, 20);
		contentPane.add(contactField);
		
		emailField = new JTextField(email);
		emailField.setColumns(10);
		emailField.setBounds(157, 164, 208, 20);
		contentPane.add(emailField);
	
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String updated_name = nameField.getText();
				String updated_address = addressField.getText();
				String updated_contact = contactField.getText();
				String updated_email = emailField.getText();
				String updated_gender = null;
				String updated_department = (String)dept_comboBox.getSelectedItem();
				
				if (rdbtnFemale.isSelected())
					updated_gender = rdbtnFemale.getText();
				else if(rdbtnMale.isSelected())
					updated_gender = rdbtnMale.getText();
				if(updated_name.equals("")|| updated_address.equals("")|| updated_contact.equals("")||updated_email.equals("")||updated_department.equals(""))
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
					st.executeUpdate("Update patient SET name='"+updated_name+"', address='"+updated_address+"', contact='"+updated_contact+"',email='"+updated_email+"',gender='"+updated_gender+"', department='"+updated_department+"' where uniqueId='"+user_name+"'");
					JOptionPane.showMessageDialog(contentPane, "Updated Successfully...!","Success", JOptionPane.INFORMATION_MESSAGE);
					close();
					ViewPatientProfile vpp = new ViewPatientProfile(user_name);
					vpp.setVisible(true);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				
			}
			}
		});
		btnUpdate.setBounds(238, 333, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				ViewPatientProfile vpp = new ViewPatientProfile(user_name);
				vpp.setVisible(true);
			}
		});
		btnBack.setBounds(79, 333, 89, 23);
		contentPane.add(btnBack);
		
		fillComboBoxDept();
		dept_comboBox.setSelectedItem(department);
		dept_comboBox.setBounds(157, 286, 208, 20);
		contentPane.add(dept_comboBox);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(159, 208, 109, 23);
		contentPane.add(rdbtnMale);
		
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(159, 238, 109, 23);
		contentPane.add(rdbtnFemale);
		
		if (gender.equals("Male"))
		{
			rdbtnMale.setSelected(true);
		}
		else
			rdbtnFemale.setSelected(true);
	}
	
}
