import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class UpdateDepartment extends JFrame {

	private JPanel contentPane;
	JList list;
	DefaultListModel model;
	Logfile lgf=new Logfile();
	/**
	 * Launch the application.
	 */
	
	JComboBox comboBox_dept_name = new JComboBox();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					UpdateDepartment frame = new UpdateDepartment();
					frame.setVisible(true);
					
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			}
		});
	}

//	public void close()
//	{
//		WindowEvent closeEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
//		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeEvent);
//	}

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
		String position = "Senior Specialists";
		try 
		{
			model.clear();
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("adsdasd");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM doctor WHERE category = '"+name+"' AND doctor_position = '"+position+"'");
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
	/**
	 * Create the frame.
	 */
	public UpdateDepartment() {
		
		fillComboBoxId();
		model = new DefaultListModel();
		 list = new JList();
		 list.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeptId = new JLabel("Dept. Id:");
		lblDeptId.setBounds(33, 82, 77, 14);
		contentPane.add(lblDeptId);
		System.out.println("vcx");
		
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
		
		JLabel lblNewLabel = new JLabel("Dept. HOD");
		lblNewLabel.setBounds(33, 136, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblUpdateDepartment = new JLabel("Update Department");
		lblUpdateDepartment.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUpdateDepartment.setBounds(33, 22, 139, 14);
		contentPane.add(lblUpdateDepartment);
		
		String id = (String)comboBox_dept_name.getSelectedItem();
		System.out.println(id);

		
//		fillJListHod(list, id);
		
		
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectedName = (String)comboBox_dept_name.getSelectedItem();
				String selectedHod = list.getSelectedValue().toString();
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					String doc_user = "";
					Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
					Statement st=c.createStatement();
					ResultSet rs = st.executeQuery("Select * from doctor where name = '"+selectedHod+"'");
					while(rs.next())
					{
						doc_user = rs.getString("username");
					}
					st.executeUpdate("Update department SET dept_hod='"+selectedHod+"', dept_hod_username = '"+doc_user+"' where dept_name='"+selectedName+"'");
					JOptionPane.showMessageDialog(contentPane, "HOD Assigned!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				
			}
		});
		btnUpdate.setBounds(61, 216, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//close();
				AdminPage adminpage = new AdminPage();
				adminpage.setVisible(true);
				
			}
		});
		btnBack.setBounds(212, 216, 89, 23);
		contentPane.add(btnBack);
		
//		JButton btnBack = new JButton("BACK");
//		btnBack.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				close();
//				AdminPage adminpage = new AdminPage();
//				adminpage.setVisible(true);
//				
//			}
//		});
		
		
	}
}
