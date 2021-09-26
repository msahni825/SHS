import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JButton;

public class DischargePatient extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnBack;
	String location="";
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDetails_Admin frame = new ViewDetails_Admin();
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
	 * @throws SQLException 
	 */
	public DischargePatient() throws SQLException 
	{
		scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 66, 613, 317);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		table = new JTable();
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				int row=table.rowAtPoint(arg0.getPoint());

				int col= table.columnAtPoint(arg0.getPoint());
				String selected_id = table.getValueAt(row,0).toString();
//				JOptionPane.showMessageDialog(null,"Value in the cell clicked : " +table_1.getValueAt(row,0).toString());
				System.out.println(selected_id);
				
				String selected_column = table.getColumnName(0);
				System.out.println(selected_column);
				System.out.println("Value in the cell clicked :"+ " "  +table.getValueAt(row,0).toString());
				
				if(selected_column.equals("uniqueId"))
				{
					ViewPatientProfile_Admin vppa;
					try {
						vppa = new ViewPatientProfile_Admin(selected_id, true);
						vppa.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Exception is here!!");
						lgf.logfile(" Exception Caught");

					}
				}
				else
				{
					ViewDoctorProfile_Admin vdpa;
					try {
						vdpa = new ViewDoctorProfile_Admin(selected_id);
						vdpa.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Exception is here!!");
						lgf.logfile(" Exception Caught");

					}	
				}
			}
		});
		String location1 = "LOCAL";
		String stats = "Allocated";
		Statement st;
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
		try 
		{
			st = c.createStatement();
			
			ResultSet rs = st.executeQuery("Select uniqueId, name, doctor_username, patient_type, location, room_status from patient where location = '"+location1+"'");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			//close();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
		scrollPane.setViewportView(table);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		contentPane.add(scrollPane);
		
		
		
//		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				close();
				AdminPage adminPage = new AdminPage();
				adminPage.setVisible(true);
				
			}
		});
		btnBack.setBounds(294, 404, 89, 23);
		contentPane.add(btnBack);
		
//		
		
		JLabel lblViewwDetails = new JLabel("VIEW DETAILS:");
		lblViewwDetails.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblViewwDetails.setBounds(49, 11, 128, 14);
		contentPane.add(lblViewwDetails);
	}
}
