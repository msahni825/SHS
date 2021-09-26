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
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class FindByIDPage extends JFrame {

	private JPanel contentPane;
	JList list;
	DefaultListModel model;
	private JTextField textField;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FindByIDPage frame = new FindByIDPage();
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
	
	private void fillJListHod(JList list, String name)
	{
		System.out.println(name);

		try 
		{
			model.clear();
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("adsdasd");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM doctor WHERE username = '"+name+"'");
			System.out.println(rs.getFetchSize());
			while(rs.next())
			{
				System.out.println("adsdasd");
				String hod = rs.getString("name");
				System.out.println(hod);
				model.addElement(hod);
			}
			list.setModel(model);
			
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
	public FindByIDPage(String username) {
		model = new DefaultListModel();
		 list = new JList();
		 list.setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		System.out.println("asdfgh");
		
		textField = new JTextField();
	
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("breaking");
				
				String selected_id = textField.getText().toString();
				System.out.println(selected_id + "hello");
				fillJListHod(list, selected_id);
				list.setVisible(true);
			}
		});
		
		textField.setBounds(129, 72, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		list.setBounds(129, 131, 115, 66);
		contentPane.add(list);
		
		JLabel lblSearchDoctorBy = new JLabel("Search Doctor By Dr. ID");
		lblSearchDoctorBy.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearchDoctorBy.setBounds(129, 11, 257, 14);
		contentPane.add(lblSearchDoctorBy);
		
		JLabel lblEnterId = new JLabel("Enter ID:");
		lblEnterId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterId.setBounds(30, 74, 132, 14);
		contentPane.add(lblEnterId);
		
		
		JLabel lblNameOfDr = new JLabel("Name of Dr.");
		lblNameOfDr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNameOfDr.setBounds(30, 131, 91, 14);
		contentPane.add(lblNameOfDr);
		
		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = list.getSelectedIndex();
	            System.out.println("Index Selected: " + index);
	            String s = (String) list.getSelectedValue();
	            System.out.println("Value Selected: " + s);
	            if(index== -1)
	            	JOptionPane.showMessageDialog(contentPane, "No such id exists..!", "Error", JOptionPane.ERROR_MESSAGE);
//	            SelectedDoctorProfileDetails details =  new SelectedDoctorProfileDetails(s);
	            else
	            {
	            	SelectedDoctorProfileDetails details = new SelectedDoctorProfileDetails(s, username);
	            	details.setVisible(true);
	            }
	            	
			}
		});
		
		
		
		
		btnView.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnView.setBounds(129, 227, 89, 23);
		contentPane.add(btnView);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SearchDoctor sd = new SearchDoctor(username);
				sd.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(246, 228, 89, 23);
		contentPane.add(btnBack);
	}
}