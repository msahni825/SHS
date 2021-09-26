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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AddRoom extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JComboBox dept_comboBox;
	DefaultListModel model;
	Connection c;
	Statement s2;
	Logfile lgf=new Logfile();

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRoom frame = new AddRoom();
					frame.setVisible(true);
				} 
				catch (Exception e) {
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
	private void fillComboBoxId()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("Select * from department ");
			while(rs.next())
			{
				String name = rs.getString("dept_name");
				dept_comboBox.addItem(name);
				System.out.println(name);
			}
			
			System.out.println("name");
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
	}

	public AddRoom() {
		dept_comboBox = new JComboBox();
		dept_comboBox.setBounds(194, 44, 128, 20);
		fillComboBoxId();
		model = new DefaultListModel();
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		dept_comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//list.setVisible(true);
				String selected_id = dept_comboBox.getSelectedItem().toString();
				System.out.println(selected_id);
				System.out.println("HI");
				//fillJListHod(list, selected_id);
				
			}
		});
		contentPane.add(dept_comboBox);
		
		String id = (String)dept_comboBox.getSelectedItem();
		System.out.println(id);
		
		JLabel lblSelectDepartment = new JLabel("Select Department: ");
		lblSelectDepartment.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectDepartment.setBounds(63, 46, 159, 14);
		contentPane.add(lblSelectDepartment);
		
		JLabel lblEnterRoomNo = new JLabel("Enter Room No.");
		lblEnterRoomNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterRoomNo.setBounds(63, 109, 135, 14);
		contentPane.add(lblEnterRoomNo);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String dname = dept_comboBox.getSelectedItem().toString();
				int rno = Integer.parseInt(textField.getText());
				String allocation = "Not Allocated";
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
					s2=c.createStatement();
					ResultSet rs = s2.executeQuery("Select * from room where department = '"+dname+"'");
					String room_id = "";
					String temp = "";
					while(rs.next())
					{
						room_id = rs.getString("room_id");
					}
					System.out.println(room_id + "current");
					for(int i = 0;i < room_id.length(); i++)
					{
						if(i < dname.length())
						{
		
						}
						else
						{
							temp = room_id.charAt(i) + temp;
						}
					}
					int r_id = 0;
					if(!temp.equals(""))
					{
						r_id = Integer.parseInt(temp);
						r_id = r_id + 1;
					}
					System.out.println(r_id + "Room id");
					for(int i = r_id; i< rno + r_id ; i++)
					{
						String rid = dname + i;
						try {
							s2.executeUpdate("Insert into room(department, room_id, room_status) values ('"+dname+"', '"+rid+"', '"+allocation+"')");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("Exception is here!!");
							lgf.logfile(" Exception Caught");

						}
					}
					JOptionPane.showMessageDialog(contentPane, "Department Successfully Inserted!", "Success", JOptionPane.INFORMATION_MESSAGE);		

					}
				catch(Exception e1)
				{
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				
		}				
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(63, 199, 89, 23);
		contentPane.add(btnAdd);
		
		textField = new JTextField();
		textField.setBounds(194, 107, 128, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setBounds(285, 200, 89, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				AdminPage adminpage = new AdminPage();
				adminpage.setVisible(true);
				
			}
		});
		
		
		contentPane.add(dept_comboBox);
		
		
	}
}
