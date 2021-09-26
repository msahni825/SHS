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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AutoAssignDoctor extends JFrame {

	private JPanel contentPane;
	JComboBox comboBox;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */

	public void fillComboBox() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
		Statement s2=c.createStatement();
		ResultSet rs = s2.executeQuery("Select * from department");
		while(rs.next())
		{
			String dept_name = rs.getString("dept_name");
			comboBox.addItem(dept_name);
		}
	}
	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public AutoAssignDoctor(String user_name) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAutoassignDoctor = new JLabel("Auto-Assign Doctor");
		lblAutoassignDoctor.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblAutoassignDoctor.setBounds(144, 27, 124, 21);
		contentPane.add(lblAutoassignDoctor);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 79, 67, 14);
		contentPane.add(lblDepartment);
		
		JLabel lblPreferredTime = new JLabel("Preferred Time");
		lblPreferredTime.setBounds(10, 125, 124, 14);
		contentPane.add(lblPreferredTime);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner.setBounds(144, 122, 67, 20);
		contentPane.add(spinner);
		
		comboBox = new JComboBox();
		fillComboBox();
		comboBox.setBounds(144, 76, 124, 20);
		contentPane.add(comboBox);
		
		JButton btnAutoassign = new JButton("Auto-Assign");
		btnAutoassign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				List doctor_names = new ArrayList();
				List doctor_usernames = new ArrayList();
				List doctor_count = new ArrayList();
				List doctor_pos = new ArrayList();
				int time = (Integer)spinner.getValue();
				
				String deptname = comboBox.getSelectedItem().toString();
				int count = 0;
				String timings = "";
				String name = "";
				String username = "";
				String doc_pos = "";
				//Object time;
				if(deptname.equals(""))
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
					ResultSet rs = s2.executeQuery("Select * from doctor where category = '"+deptname+"'");
					while(rs.next())
					{
						name = rs.getString("name");
						username = rs.getString("username");
						count = rs.getInt("patients_count");
						System.out.println(count);
						timings = rs.getString("timings");
						doc_pos = rs.getString("doctor_position");
						String times[] = timings.split("-");
						
						int start = Integer.parseInt(times[0]);
						int end = Integer.parseInt(times[1]);
						System.out.println(start +" " + end);
						if (time >= start && time < end && count > 0)
						{
							System.out.println("afsf");
							doctor_names.add(name);
							doctor_usernames.add(username);
							doctor_count.add(count);
							doctor_pos.add(doc_pos);
						}
					}
					System.out.println(doctor_count.size());
					for(int i =0;i<doctor_count.size();i++)
					{
						System.out.println(doctor_count.get(i));
					}
					
					if(doctor_count.size() != 0)
					{
						int max = (Integer)Collections.max(doctor_count);
						System.out.println("\nds"+max);
						int max_index = doctor_count.indexOf(max);
						
						String assigned_doctor = (String)doctor_names.get(max_index);
						count = max - 1;
						System.out.println(count);
						String assigned_doctor_username = (String)doctor_usernames.get(max_index);
						s2.executeUpdate("Update patient SET doctor_username = '"+assigned_doctor_username+"' where uniqueId = '"+user_name+"' ");
						s2.executeUpdate("Update doctor SET patients_count = '"+count+"' where username = '"+assigned_doctor_username+"'");
						JOptionPane.showMessageDialog(contentPane, "Appointment Done!\n"+"Assigned Doctor:"+assigned_doctor, "Success", JOptionPane.INFORMATION_MESSAGE);
						PatientPage pp = new PatientPage(user_name);
						pp.setVisible(true);
					}
					else
					{
							JOptionPane.showMessageDialog(contentPane, "No Doctors Available For This Time Slot..!\n" + "Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} 
				catch (ClassNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				
			}
			}
		});
		btnAutoassign.setBounds(144, 209, 105, 23);
		contentPane.add(btnAutoassign);
	}
}
