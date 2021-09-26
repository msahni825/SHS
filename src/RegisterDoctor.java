import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.jdbc.ResultSet;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;

public class RegisterDoctor extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField address;
	private JTextField contact;
	private JLabel lblEmail;
	private JTextField email;
	private JLabel lblCategorydepartment;
	private JLabel lblTimings;
	private JTextField timings;
	private JLabel lblDoctorPrecedence;
	private JButton btnRegister;
	private JComboBox comboBox_category;
	private JButton btnLogout;
	private JButton btnBack;
	private JComboBox comboBox_doc_precedence;
	private JComboBox combobox_doc_type;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JLabel lblUsername;
	private JTextField usernameField;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
	int range;
	int countvalue;
	private JLabel lblTypeOfDr;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterDoctor frame = new RegisterDoctor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void close()
	{
		WindowEvent closeEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeEvent);
	}
	private void fillComboBoxId()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
        
            Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
            Statement st=c.createStatement();
            ResultSet rs=(ResultSet) st.executeQuery("Select * from department");
            while(rs.next())
            {
                String name = rs.getString("dept_name");
                comboBox_category.addItem(name);
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
	
	/**
	 * Create the frame.
	 */
	/**
	 * 
	 */
	public RegisterDoctor() {
		comboBox_category = new JComboBox();
		fillComboBoxId();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 455);
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblDoctorRegistration = new JLabel("Doctor Registration");
		lblDoctorRegistration.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblDoctorRegistration, "2, 2");
		
		JLabel lblName = new JLabel("Name:");
		contentPane.add(lblName, "2, 4, right, default");
		
		name = new JTextField();
		contentPane.add(name, "4, 4, fill, default");
		name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Address:");
		contentPane.add(lblNewLabel, "2, 6, right, default");
		
		address = new JTextField();
		contentPane.add(address, "4, 6, fill, default");
		address.setColumns(10);
		
		JLabel label = new JLabel("Contact:");
		contentPane.add(label, "2, 8, right, default");
		
		contact = new JTextField();
		contentPane.add(contact, "4, 8, fill, default");
		contact.setColumns(10);
		
		lblEmail = new JLabel("E-mail:");
		contentPane.add(lblEmail, "2, 10, right, default");
		
		email = new JTextField();
		contentPane.add(email, "4, 10, fill, default");
		email.setColumns(10);
		
		lblCategorydepartment = new JLabel("Category/Department:");
		contentPane.add(lblCategorydepartment, "2, 12, right, default");
		
		
		contentPane.add(comboBox_category, "4, 12, fill, default");
		
		lblTimings = new JLabel("Timings:");
		contentPane.add(lblTimings, "2, 16, right, default");
		
		timings = new JTextField();
		contentPane.add(timings, "4, 16, fill, default");
		timings.setColumns(10);
		
		lblDoctorPrecedence = new JLabel("Doctor Precedence:");
		contentPane.add(lblDoctorPrecedence, "2, 18, right, default");
		
		comboBox_doc_precedence = new JComboBox();
		comboBox_doc_precedence.addItem("Junior Residents");
		comboBox_doc_precedence.addItem("Senior Residents");
		comboBox_doc_precedence.addItem("Specialists");
		comboBox_doc_precedence.addItem("Senior Specialists");
		
		contentPane.add(comboBox_doc_precedence, "4, 18, fill, default");
		
		usernameField = new JTextField();
		contentPane.add(usernameField, "4, 20, fill, default");
		usernameField.setColumns(10);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				start st = new start();
				st.setVisible(true);
			}
		});
		
		
		btnRegister = new JButton("Register");
		
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String nameField = name.getText();
				//String password = passwordField.getText();
				String addressField = address.getText();
				String contactField = contact.getText();
				String emailField = email.getText();
				String categoryField = (String)comboBox_category.getSelectedItem();
				String doctypeField = (String)combobox_doc_type.getSelectedItem();
				//String scheduleField = scheduledDays.getText();
				String timingField = timings.getText();
				String a[]=timingField.split("-");
				range=Integer.parseInt(a[1])-Integer.parseInt(a[0]);
				if(range>0)
				{
					countvalue=range*4;
				}
				
				String doctorPrecedenceField = (String)comboBox_doc_precedence.getSelectedItem();
				String userField = usernameField.getText();
				String passField = passwordField.getText();
				
				if(nameField.equals("")|| passField.equals("")|| addressField.equals("")||
						contactField.equals("")|| emailField.equals("")||categoryField.equals("")||
						doctypeField.equals("")||userField.equals("")||
						doctorPrecedenceField.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please enter missing details.");
				}
				else
				{
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
					Statement s2=c.createStatement();
					s2.executeUpdate("Insert into doctor (name, address, contact, email, category, timings, doctor_position, username,patients_count,doctor_type) values ('"+nameField+"', '"+addressField+"', '"+contactField+"', '"+emailField+"', '"+categoryField+"', '"+timingField+"', '"+doctorPrecedenceField+"', '"+userField+"','"+countvalue+"','"+doctypeField+"')");
					s2.executeUpdate("Insert into logindoctor (username, password) values ('"+userField+"', '"+passField+"')");
//					s2.executeUpdate("Insert into "+categoryField+"(name, address, contact, email, category, scheduled_days, timings, doctor_position,passwordfield) values ('"+nameField+"', '"+addressField+"', '"+contactField+"', '"+emailField+"', '"+categoryField+"', '"+scheduleField+"', '"+timingField+"', '"+doctorPrecedenceField+"','"+password+"')");
					JOptionPane.showMessageDialog(contentPane, "Doctor Registered Successfully...!","Success", JOptionPane.INFORMATION_MESSAGE);
					name.setText("");
					address.setText("");
					passwordField.setText("");
					contact.setText("");
					email.setText("");
					//scheduledDays.setText("");
					usernameField.setText("");
					timings.setText("");
					comboBox_category.setSelectedItem(0);
					comboBox_doc_precedence.setSelectedItem(0);
					combobox_doc_type.setSelectedItem(0);
					
					
				}
				catch (SQLException | ClassNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
			}
		}
	});
		btnBack = new JButton("Back");
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				AdminPage adminPage = new AdminPage();
				adminPage.setVisible(true);
			}
		});
		
		lblUsername = new JLabel("Username");
		contentPane.add(lblUsername, "2, 20, right, default");
		
		
		
		lblPassword = new JLabel("Password");
		contentPane.add(lblPassword, "2, 22, right, default");
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField, "4, 22, fill, default");
		
		lblTypeOfDr = new JLabel("Type of DR.");
		contentPane.add(lblTypeOfDr, "2, 24, right, default");
		
		combobox_doc_type = new JComboBox();
		combobox_doc_type.addItem("Senior Surgeon");
		combobox_doc_type.addItem("Surgeon");
		combobox_doc_type.addItem("Not a Surgeon");
		contentPane.add(combobox_doc_type, "4, 24, fill, default");
		
		contentPane.add(btnBack, "2, 26");
		contentPane.add(btnRegister, "4, 26");
		
		
		contentPane.add(btnLogout, "4, 28");
	}

}
