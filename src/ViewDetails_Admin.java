import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

//import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import java.sql.*;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class ViewDetails_Admin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnViewDoctor;
	private JButton btnBack;
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
	public ViewDetails_Admin() throws SQLException 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 66, 613, 361);
		contentPane.add(scrollPane);
		
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
						vppa = new ViewPatientProfile_Admin(selected_id, false);
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
		scrollPane.setViewportView(table);
		
		btnViewDoctor = new JButton("View Doctor");
		
		btnViewDoctor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try 
				{
					
					Statement st = c.createStatement();
					ResultSet rs = st.executeQuery("Select username, name, doctor_position, category from doctor");
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}	
				
			}
		});
		btnViewDoctor.setBounds(101, 32, 128, 23);
		contentPane.add(btnViewDoctor);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				close();
				AdminPage adminPage = new AdminPage();
				adminPage.setVisible(true);
				
			}
		});
		btnBack.setBounds(478, 32, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnViewPatient = new JButton("View Patient");
		btnViewPatient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Statement st;
				
				try 
				{
					st = c.createStatement();
					ResultSet rs = st.executeQuery("Select uniqueId, name, doctor_username, location from patient");
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Exception is here!!");
					lgf.logfile(" Exception Caught");

				}
				
				
			}
		});
		btnViewPatient.setBounds(304, 32, 107, 23);
		contentPane.add(btnViewPatient);
		
		JLabel lblViewwDetails = new JLabel("VIEW DETAILS:");
		lblViewwDetails.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblViewwDetails.setBounds(49, 11, 128, 14);
		contentPane.add(lblViewwDetails);
	}
}
