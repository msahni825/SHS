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
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AddDepartment extends JFrame {

	private JPanel contentPane;
	private JTextField deptIdField;
	private JTextField deptNameField;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDepartment frame = new AddDepartment();
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

	/**
	 * Create the frame.
	 */
	public AddDepartment() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddDepartment = new JLabel("ADD DEPARTMENT:");
		lblAddDepartment.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAddDepartment.setBounds(39, 30, 194, 14);
		contentPane.add(lblAddDepartment);
		
		JLabel lblDepartmentName = new JLabel("Department Name:");
		lblDepartmentName.setBounds(33, 96, 113, 14);
		contentPane.add(lblDepartmentName);
		
		deptNameField = new JTextField();
		deptNameField.setBounds(156, 93, 251, 20);
		contentPane.add(deptNameField);
		deptNameField.setColumns(10);
		
		deptIdField = new JTextField();
		deptIdField.setBounds(156, 141, 251, 20);
		contentPane.add(deptIdField);
		deptIdField.setColumns(10);
		
		JLabel lblDepartmentHod = new JLabel("Department id:");
		lblDepartmentHod.setBounds(38, 144, 108, 14);
		contentPane.add(lblDepartmentHod);
				
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(146, 212, 106, 23);
		btnAdd.addActionListener(new ActionListener() {
			
			int temp=0;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String did = deptIdField.getText();
				String dname = deptNameField.getText();
				String dhod = "";
				try
				{
					if(dname.equals("")|| did.equals(""))
					{
						JOptionPane.showMessageDialog(null,"Please enter missing details.");
						throw new Exception();
					}
					else
					{
						try
						{
							if(temp==1)
							{
							Class.forName("com.mysql.jdbc.Driver");
							Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
							Statement s2=c.createStatement();
							s2.executeUpdate("Insert into department(dept_name, dept_id, dept_hod) values ('"+dname+"', '"+did+"', '"+dhod+"')");
							JOptionPane.showMessageDialog(contentPane, "Department Successfully Inserted!", "Success", JOptionPane.INFORMATION_MESSAGE);
							deptIdField.setText("");
							deptNameField.setText("");
							}
							else
							{
								throw new Exception();
								
							}
						}
						catch(Exception e1)
						{
							e1.printStackTrace();
							System.out.println("Exception is here!!");
							lgf.logfile("Add Department Exception 1");
						}
					}
					
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile("Add Department Exception 1");
				}
		}
	});
		contentPane.add(btnAdd);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(291, 212, 106, 23);
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				AdminPage adminPage = new AdminPage();
				adminPage.setVisible(true);
				
			}
		});
		contentPane.add(btnBack);
		
	
	}
}
