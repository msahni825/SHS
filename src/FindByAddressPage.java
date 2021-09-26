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
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class FindByAddressPage extends JFrame {

	private JPanel contentPane;
	JList list;
	DefaultListModel model;
	private JTextField textField;
	Logfile lgf =new Logfile();
	/**
	 * Launch the application.
	 */
	
	private void fillJListHod(JList list, String address)
	{
		System.out.println(address);

		try 
		{
			model.clear();
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("adsdasd");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM doctor WHERE address = '"+address+"'");
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

	public FindByAddressPage(String username) {
		//fillComboBoxId();
		model = new DefaultListModel();
		 list = new JList();
		 list.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		System.out.println("vcx");
		
		list.setBounds(190, 110, 186, 79);
		contentPane.add(list);
		//System.out.println(id);
		
		JLabel label = new JLabel("");
		label.setBounds(22, 131, 46, 14);
		contentPane.add(label);
		
		JLabel lblNameOfDr = new JLabel("NAME of DR.");
		lblNameOfDr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNameOfDr.setBounds(38, 131, 107, 14);
		contentPane.add(lblNameOfDr);
		
		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = list.getSelectedIndex();
	            System.out.println("Index Selected: " + index);
	            String s = (String) list.getSelectedValue();
	            System.out.println("Value Selected: " + s);
	            if(index== -1)
	            	JOptionPane.showMessageDialog(contentPane, "No such address exists..!", "Error", JOptionPane.ERROR_MESSAGE);
//	            SelectedDoctorProfileDetails details =  new SelectedDoctorProfileDetails(s);
	            else
	            {
	            	SelectedDoctorProfileDetails details = new SelectedDoctorProfileDetails(s, username);
	            	details.setVisible(true);
	            }
	            	
			}
		});
		btnView.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnView.setBounds(150, 215, 89, 23);
		contentPane.add(btnView);
		
		JLabel lblSelectDrBy = new JLabel("Select DR. By Address");
		lblSelectDrBy.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectDrBy.setBounds(40, 28, 216, 20);
		contentPane.add(lblSelectDrBy);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(38, 82, 107, 23);
		contentPane.add(lblAddress);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("breaking");
				list.setVisible(true);
				String selected_id = textField.getText().toString();
				fillJListHod(list, selected_id);
			}
		});
		textField.setBounds(190, 76, 133, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
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
		btnBack.setBounds(265, 216, 89, 23);
		contentPane.add(btnBack);
		
	}

}