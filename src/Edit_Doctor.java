import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Edit_Doctor extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField contactField;
	private JTextField emailField;
	private JTextField daysField;
	private JTextField timingsField;
	private JTextField textField_5;
	JComboBox dept = new JComboBox();
	JComboBox doctor_precedence = new JComboBox();
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Edit_Doctor frame = new Edit_Doctor();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public void close()
	{
		WindowEvent closeEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeEvent);
	}
	/**
	 * Create the frame.
	 */
	
	private void fillComboBoxId()
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
				dept.addItem(dept_name);
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
	}
	
	public Edit_Doctor(String user_name) {
		
		String name = "";
		String address = "";
		String contact = "";
		String email = "";
		String category = "";
		String doctor_position = "";
		String username = "";
		//String scheduled_days = "";
		String timings = "";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement s2=c.createStatement();
			ResultSet rs = s2.executeQuery("Select * from doctor where username = '"+user_name+"'");
			while(rs.next())
			{
				name = rs.getString("name");
				address = rs.getString("address");
				contact = rs.getString("contact");
				email = rs.getString("email");
				category = rs.getString("category");
				doctor_position = rs.getString("doctor_position");
				//scheduled_days = rs.getString("scheduled_days");
				username = rs.getString("username");
				timings = rs.getString("timings");
			}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditDoctor = new JLabel("EDIT DOCTOR");
		lblEditDoctor.setBounds(33, 12, 146, 15);
		contentPane.add(lblEditDoctor);
		
		JLabel lblNam = new JLabel("NAME:");
		lblNam.setBounds(89, 39, 70, 15);
		contentPane.add(lblNam);
		
		JLabel lblAddress = new JLabel("ADDRESS:");
		lblAddress.setBounds(89, 66, 70, 15);
		contentPane.add(lblAddress);
		
		JLabel lblContact = new JLabel("CONTACT:");
		lblContact.setBounds(89, 93, 70, 15);
		contentPane.add(lblContact);
		
		JLabel lblEmail = new JLabel("EMAIL:");
		lblEmail.setBounds(89, 117, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblCategorydept = new JLabel("CATEGORY/DEPT.:");
		lblCategorydept.setBounds(89, 144, 146, 15);
		contentPane.add(lblCategorydept);
		
//		JLabel lblScheduleDays = new JLabel("SCHEDULE DAYS:");
//		lblScheduleDays.setBounds(89, 171, 132, 15);
//		contentPane.add(lblScheduleDays);
//		
		JLabel lblTimings = new JLabel("TIMINGS:");
		lblTimings.setBounds(89, 198, 132, 15);
		contentPane.add(lblTimings);
		
		JLabel lblDoctorPrecedence = new JLabel("DOCTOR PRECEDENCE:");
		lblDoctorPrecedence.setBounds(89, 220, 167, 15);
		contentPane.add(lblDoctorPrecedence);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				View_Doctor_Profile vdp =  new View_Doctor_Profile(user_name, 1);
				vdp.setVisible(true);
			}
		});
		btnBack.setBounds(12, 293, 117, 25);
		contentPane.add(btnBack);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String updated_name = nameField.getText();
				String updated_address = addressField.getText();
				String updated_contact = contactField.getText();
				String updated_email = emailField.getText();
				//String updated_timings = timingsField.getText();
				
				String updated_category = (String)dept.getSelectedItem();
				String updated_position = (String)doctor_precedence.getSelectedItem();
				if(updated_name.equals("")|| updated_address.equals("")|| updated_contact.equals("")||updated_email.equals(""))
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
					st.executeUpdate("Update doctor SET name='"+updated_name+"', address='"+updated_address+"', contact='"+updated_contact+"',email='"+updated_email+"',category='"+updated_category+"', doctor_position='"+updated_position+"' where username='"+user_name+"'");
					JOptionPane.showMessageDialog(contentPane, "Updated Successfully...!","Success", JOptionPane.INFORMATION_MESSAGE);
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
		
		btnUpdate.setBounds(285, 293, 117, 25);
		contentPane.add(btnUpdate);
		
		nameField = new JTextField(name);
		nameField.setBounds(266, 37, 114, 19);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		addressField = new JTextField(address);
		addressField.setBounds(266, 64, 114, 19);
		contentPane.add(addressField);
		addressField.setColumns(10);
		
		contactField = new JTextField(contact);
		contactField.setBounds(266, 91, 114, 19);
		contentPane.add(contactField);
		contactField.setColumns(10);
		
		emailField = new JTextField(email);
		emailField.setBounds(266, 115, 114, 19);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
//		daysField = new JTextField(scheduled_days);
//		daysField.setBounds(266, 169, 114, 19);
//		contentPane.add(daysField);
//		daysField.setColumns(10);
//		
		textField_5 = new JTextField();
		textField_5.setBounds(266, 196, 114, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		fillComboBoxId();
		dept.setSelectedItem(category);
		dept.setBounds(263, 139, 117, 24);
		contentPane.add(dept);
		
		doctor_precedence.setSelectedItem(doctor_position);
		doctor_precedence.addItem("Junior Residents");
		doctor_precedence.addItem("Senior Residents");
		doctor_precedence.addItem("Specialists");
		doctor_precedence.addItem("Senior Specialists");
		doctor_precedence.setBounds(266, 215, 114, 24);
		contentPane.add(doctor_precedence);
	}
}
