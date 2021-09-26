import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CategoryPage extends JFrame {

	private JPanel contentPane;
	JList list;
	DefaultListModel model;
	String username;
	Logfile lgf=new Logfile();
	/**
	 * Launch the application.
	 */
	
	JComboBox comboBox_dept_name = new JComboBox();

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
				comboBox_dept_name.addItem(name);
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
	
	private void fillJListHod(JList list, String name)
	{
		System.out.println(name);

		try 
		{
			model.clear();
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("adsdasd");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM doctor WHERE category = '"+name+"' and patients_count > 0");
			System.out.println(rs.getFetchSize());
			while(rs.next())
			{
				String hod = rs.getString("name");
				System.out.println(hod);
				model.addElement(hod);
			}
			list.setModel(model);
			System.out.println(username);
			rs.close();
			st.close();
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
	
	public CategoryPage(String username) {
		
		fillComboBoxId();
		model = new DefaultListModel();
		 list = new JList();
		 list.setVisible(false);
		 System.out.println(username);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list.setBounds(190, 110, 186, 79);
		contentPane.add(list);
		
		comboBox_dept_name.setBounds(190, 79, 185, 20);
		comboBox_dept_name.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				list.setVisible(true);
				String selected_id = comboBox_dept_name.getSelectedItem().toString();
				fillJListHod(list, selected_id);
				
			}
		});
		contentPane.add(comboBox_dept_name);
		
		String id = (String)comboBox_dept_name.getSelectedItem();
		System.out.println(id);
		
		JLabel lblSelectDrBy = new JLabel("Select Dr. by category ");
		lblSelectDrBy.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectDrBy.setBounds(38, 21, 186, 14);
		contentPane.add(lblSelectDrBy);
		
		JLabel label = new JLabel("");
		label.setBounds(22, 131, 46, 14);
		contentPane.add(label);
		
		JLabel lblNameOfDr = new JLabel("NAME of DR.");
		lblNameOfDr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNameOfDr.setBounds(38, 131, 107, 14);
		contentPane.add(lblNameOfDr);
		
		JButton btnView = new JButton("VIEW");

		btnView.addActionListener(new ActionListener() {
			String username1;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					int index = list.getSelectedIndex();
		            System.out.println("Index Selected: " + index);
		            String s = (String) list.getSelectedValue();
		            System.out.println("Value Selected: " + s);
//		            SelectedDoctorProfileDetails details =  new SelectedDoctorProfileDetails(s);
		            System.out.println(username1+"hello");
		            SelectedDoctorProfileDetails details = new SelectedDoctorProfileDetails(s,username);
		            details.setVisible(true);
			}
		});
		
		btnView.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnView.setBounds(150, 215, 89, 23);
		contentPane.add(btnView);
		
		JLabel lblCategory = new JLabel("CATEGORY:");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(38, 82, 107, 14);
		contentPane.add(lblCategory);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				SearchDoctor sd = new SearchDoctor(username);
				sd.setVisible(true);
				
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnBack.setBounds(287, 216, 89, 23);
		contentPane.add(btnBack);

	}
}
