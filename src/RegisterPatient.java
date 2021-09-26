import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.GenericDeclaration;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.sql.*;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class RegisterPatient extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField address;
	private JTextField contact;
	private JTextField email;
	private JTextField bloodgroup;
	private JLabel lblGender;
	private JLabel lblLocation;

	private JButton btnRegister;
	private JLabel lblPassword;
	private JLabel lblDepartment;
	private JComboBox comboBox_Department;
	private JPasswordField passwordField;
	private JButton btnBack;

	ButtonGroup G = new ButtonGroup();
	ButtonGroup G1 = new ButtonGroup();
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnOpd;
	private JRadioButton rdbtnLocal;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
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
                String name = rs.getString("dept_name");
                comboBox_Department.addItem(name);
                //System.out.println(name);
            }
            
            System.out.println("query");
            
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
            System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

        }
    }

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPatient frame = new RegisterPatient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					//System.out.println("Exception is here!!");
					//lgf.logfile(" Exception Caught");

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
	public RegisterPatient() {
		comboBox_Department = new JComboBox();
		fillComboBoxId();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblPatientRegistration = new JLabel("Patient Registration");
		lblPatientRegistration.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblPatientRegistration, "2, 2");
		
		JLabel lblName = new JLabel("Name:");
		contentPane.add(lblName, "2, 4, right, default");
		
		name = new JTextField();
		contentPane.add(name, "4, 4, fill, default");
		name.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		contentPane.add(lblAddress, "2, 6, right, default");
		
		address = new JTextField();
		contentPane.add(address, "4, 6, fill, default");
		address.setColumns(10);
		
		JLabel lblContact = new JLabel("Contact:");
		contentPane.add(lblContact, "2, 8, right, default");
		
		contact = new JTextField();
		contentPane.add(contact, "4, 8, fill, default");
		contact.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		contentPane.add(lblEmail, "2, 10, right, default");
		
		email = new JTextField();
		contentPane.add(email, "4, 10, fill, default");
		email.setColumns(10);
		
		JLabel lblBloodgroup = new JLabel("Bloodgroup:");
		contentPane.add(lblBloodgroup, "2, 12, right, default");
		
		bloodgroup = new JTextField();
		contentPane.add(bloodgroup, "4, 12, fill, default");
		bloodgroup.setColumns(10);
		lblGender = new JLabel("Gender:");
		contentPane.add(lblGender, "2, 14");
		
		rdbtnMale = new JRadioButton("Male");
		contentPane.add(rdbtnMale, "4, 14");
		
		rdbtnFemale = new JRadioButton("Female");
		contentPane.add(rdbtnFemale, "4, 16");
		
		lblLocation = new JLabel("Location:");
		contentPane.add(lblLocation, "2, 20");
		
		rdbtnOpd = new JRadioButton("OPD");
		contentPane.add(rdbtnOpd, "4, 20");
		
		rdbtnLocal = new JRadioButton("LOCAL");
		contentPane.add(rdbtnLocal, "4, 22");
		
		G.add(rdbtnFemale);
		G.add(rdbtnMale);
		G1.add(rdbtnLocal);
		G1.add(rdbtnOpd);
		
		lblPassword = new JLabel("Password:");
		contentPane.add(lblPassword, "2, 24, right, default");
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField, "4, 24, fill, default");
		
		lblDepartment = new JLabel("Department:");
		contentPane.add(lblDepartment, "2, 26, right, default");
		
		contentPane.add(comboBox_Department, "4, 26, fill, default");
		
	
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String nameField = name.getText();
				String addressField = address.getText();
				String contactField = contact.getText();
				//Integer contactField = Integer.parseInt(ctField);
				String emailField = email.getText();
				String bloodgroupField = bloodgroup.getText();
				String password = passwordField.getText();
				String departmentField = (String)comboBox_Department.getSelectedItem();
				String locationField = null;
				String genderField = null;
				String room_status = ""; 
				
				if (rdbtnOpd.isSelected())
					locationField = rdbtnOpd.getText();
				else
					locationField = rdbtnLocal.getText();
//				
				if (rdbtnFemale.isSelected())
					genderField = rdbtnFemale.getText();
				else
					genderField = rdbtnMale.getText();
//				

				
		if(emailField.equals("") || addressField.equals("") || nameField.equals("") || contactField.equals("")|| genderField.equals("")|| bloodgroupField.equals("")||locationField.equals("")|| departmentField.equals("")||passwordField.equals(""))
		{
			JOptionPane.showMessageDialog(null,"Please enter the details.");
		}
		else
		{
	System.out.println(nameField + contactField + genderField + bloodgroupField + locationField + departmentField);
	if(nameField.equals("") || contactField.equals("") || genderField.equals("") || bloodgroupField.equals("") || locationField.equals("") || departmentField.equals(""))
	{
		JOptionPane.showMessageDialog(contentPane, "Please Enter the Details!", "Error", JOptionPane.ERROR_MESSAGE);
	}
	else
	{
		String unique_Id = departmentField + "_" + contactField;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
				Statement s2=c.createStatement();
				if(locationField.equals("LOCAL"))
				{
					ResultSet rs = s2.executeQuery("Select * from room where room_status = '"+"Not Allocated"+"' and department = '"+departmentField+"'");
					String room_id = "";
					while(rs.next())
					{
						room_id = rs.getString("room_id");
					}
					
					System.out.println(room_id);
					
					s2.executeUpdate("Insert into patient(name, address, contact, email, bloodgroup, gender, location, department, uniqueId, room_id) values ('"+nameField+"', '"+addressField+"', '"+contactField+"', '"+emailField+"', '"+bloodgroupField+"', '"+genderField+"', '"+locationField+"', '"+departmentField+"', '"+unique_Id+"', '"+room_id+"')");
					s2.executeUpdate("Update room SET room_status = '"+"Allocated"+"' where room_id = '"+room_id+"'");
					s2.executeUpdate("Insert into loginpatient(uniqueId, password) values ('"+unique_Id+"', '"+password+"')");
					JOptionPane.showMessageDialog(contentPane, "Successfully Registered!\n"+"Asigned Room:" + room_id , "Success", JOptionPane.INFORMATION_MESSAGE);
					name.setText("");
					address.setText("");
					contact.setText("");
					email.setText("");
					bloodgroup.setText("");
					comboBox_Department.setSelectedIndex(0);
					passwordField.setText("");

				}
				else
				{
					if(locationField.equals("OPD"))
					{
						s2.executeUpdate("Insert into patient(name, address, contact, email, bloodgroup, gender, location, department, uniqueId, room_status) values ('"+nameField+"', '"+addressField+"', '"+contactField+"', '"+emailField+"', '"+bloodgroupField+"', '"+genderField+"', '"+locationField+"', '"+departmentField+"', '"+unique_Id+"', '"+"Not Allocated"+"')");
						s2.executeUpdate("Insert into loginpatient(uniqueId, password) values ('"+unique_Id+"', '"+password+"')");
						name.setText("");
						address.setText("");
						contact.setText("");
						email.setText("");
						bloodgroup.setText("");
						comboBox_Department.setSelectedIndex(0);
						passwordField.setText("");

					}
				}
			}
				catch (SQLException | ClassNotFoundException e1) 
				{
					// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("Exception is here!!");
						lgf.logfile(" Exception Caught");

				}
			}
		}}
	});
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				login_Patient loginP = new login_Patient();
				loginP.setVisible(true);
				
			}
		});
		contentPane.add(btnBack, "2, 29");
		
		contentPane.add(btnRegister, "4, 29");	
	}

}
