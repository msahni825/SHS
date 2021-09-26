import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;

public class View_Doctor_Profile extends JFrame {

	private JPanel contentPane;
	JButton btnUpdate;
	JButton btnDelete;
	Logfile lgf=new Logfile();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					View_Doctor_Profile frame = new View_Doctor_Profile();
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
	
	public View_Doctor_Profile(String user_name, int flag) {
		
		String name = "";
		String address = "";
		String contact = "";
		String email = "";
		String category = "";
		String doctor_position = "";
		String username = "";
		String doctor_type = "";
		String timings = "";
		//String scheduled_days = "";
		
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
				doctor_type = rs.getString("doctor_type");
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
		setBounds(100, 100, 440, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourProfile = new JLabel("YOUR PROFILE");
		lblYourProfile.setBounds(145, 12, 129, 15);
		contentPane.add(lblYourProfile);
		
		JLabel lblNewLabel = new JLabel("NAME:");
		lblNewLabel.setBounds(56, 39, 70, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ADDRESS:");
		lblNewLabel_1.setBounds(56, 66, 70, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CONTACT:");
		lblNewLabel_2.setBounds(56, 93, 70, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("EMAIL:");
		lblNewLabel_3.setBounds(56, 120, 70, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblCategory = new JLabel("CATEGORY:");
		lblCategory.setBounds(56, 143, 129, 15);
		contentPane.add(lblCategory);
		
		JLabel lblTimings = new JLabel("TIMINGS:");
		lblTimings.setBounds(56, 197, 70, 15);
		contentPane.add(lblTimings);
		
		JLabel lblDoctorPrecedence = new JLabel("DOCTOR PRECEDENCE:");
		lblDoctorPrecedence.setBounds(56, 224, 129, 15);
		contentPane.add(lblDoctorPrecedence);
		
		JLabel lblDoctorType = new JLabel("DOCTOR TYPE:");
		lblDoctorType.setBounds(56, 250, 129, 15);
		contentPane.add(lblDoctorType);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				List_Of_Patients lop = new List_Of_Patients(user_name);
			}
		});
		btnBack.setBounds(182, 345, 117, 25);
		contentPane.add(btnBack);
		
		
		btnUpdate = new JButton("EDIT");
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				Edit_Doctor ed =  new Edit_Doctor(user_name);
				ed.setVisible(true);
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
					Statement s2=c.createStatement();
					s2.executeUpdate("Delete from doctor where username = '"+user_name+"' ");
					View_Doctor_Profile vdp = new View_Doctor_Profile(user_name, 0);
					vdp.setVisible(true);
					JOptionPane.showMessageDialog(contentPane, "Deleted Successfully..!","Success", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				
			}
		});
		
		if(flag == 0)
		{
			btnUpdate.setVisible(false);
			btnDelete.setVisible(true);
			btnDelete.setBounds(56, 346, 89, 23);
			contentPane.add(btnDelete);
		}
		else
		{
			btnUpdate.setVisible(true);
			btnDelete.setVisible(false);
			btnUpdate.setBounds(182, 304, 117, 25);
			contentPane.add(btnUpdate);
		}
		
		
		
		
		JLabel lblNewLabel_4 = new JLabel(name);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_4.setBounds(240, 38, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(address);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_5.setBounds(200, 66, 189, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(contact);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(210, 93, 179, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(email);
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7.setBounds(220, 120, 169, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(category);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_8.setBounds(210, 143, 169, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_10 = new JLabel(doctor_position);
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_10.setBounds(230, 224, 169, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel(timings);
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_11.setBounds(240, 210, 169, 14);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel(doctor_type);
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_12.setBounds(250, 260, 169, 14);
		contentPane.add(lblNewLabel_12);
		
//		JLabel lblDoctorType = new JLabel("DOCTOR TYPE:");
//		lblDoctorType.setBounds(56, 250, 117, 14);
//		contentPane.add(lblDoctorType);
	}
}
