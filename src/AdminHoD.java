import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AdminHoD extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminHoD frame = new AdminHoD();
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
	public AdminHoD(String user_name) {
		
		table = new JTable();
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int row=table.rowAtPoint(e.getPoint());

				int col= table.columnAtPoint(e.getPoint());
				String selected_id = table.getValueAt(row,0).toString();
//				JOptionPane.showMessageDialog(null,"Value in the cell clicked : " +table_1.getValueAt(row,0).toString());

				System.out.println("Value in the cell clicked :"+ " "  +table.getValueAt(row,0).toString());
				
				
				if(selected_id.equals(user_name))
				{
					View_Doctor_Profile vdp = new View_Doctor_Profile(selected_id, 1);
					vdp.setVisible(true);
				}
				else
				{
					View_Doctor_Profile vdp = new View_Doctor_Profile(selected_id, 0);
					vdp.setVisible(true);
					 
				}
				
			}
		});
		String dept_name = "";
		try
		{

			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement s2=c.createStatement();
			ResultSet rs = s2.executeQuery("Select category from doctor where username = '"+user_name+"'");
			while(rs.next())
			{
				dept_name = rs.getString("category");
			}
			
			ResultSet rs1 = s2.executeQuery("Select username, name, doctor_position, category, contact from doctor where category = '"+dept_name+"'");
		
			table.setModel(DbUtils.resultSetToTableModel(rs1));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				List_Of_Patients lop = new List_Of_Patients(user_name);
				lop.setVisible(true);
			}
		});
		btnBack.setBounds(168, 214, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblHodAdminResponsibility = new JLabel("HoD Admin Responsibility");
		lblHodAdminResponsibility.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblHodAdminResponsibility.setBounds(129, 27, 165, 14);
		contentPane.add(lblHodAdminResponsibility);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 414, 133);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
	}
}
