import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class HoDPage extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					List_Of_Patients frame = new List_Of_Patients();
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
	
	public void fillJList(JTable table, String user_name)
	{
		try
		{

			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement s2=c.createStatement();
			ResultSet rs = s2.executeQuery("Select uniqueId, name, location from patient where doctor_username = '"+user_name+"'");
			table.setModel(DbUtils.resultSetToTableModel(rs));
//			while(rs.next())
//			{
//				System.out.println("adsdasd");
//				String name = rs.getString("name");
//				String id = rs.getString("uniqueId");
//				String add = name+"-"+id;
//				System.out.println(add+"ss");
//			}
			
			rs.close();
			s2.close();
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
	
	public boolean isCellEditable(int row, int column) {
        return false;
    }
	public HoDPage(String user_name) {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 38, 372, 193);

		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseListener() {
	
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				if (table_1.isEditing())
//				    table_1.getCellEditor().stopCellEditing();
				int row=table_1.rowAtPoint(e.getPoint());

				int col= table_1.columnAtPoint(e.getPoint());
				String selected_id = table_1.getValueAt(row,0).toString();
//				JOptionPane.showMessageDialog(null,"Value in the cell clicked : " +table_1.getValueAt(row,0).toString());

				System.out.println("Value in the cell clicked :"+ " "  +table_1.getValueAt(row,0).toString());

				View_Patient_Details_Doctor vpdd = new View_Patient_Details_Doctor(selected_id, user_name);
				vpdd.setVisible(true);
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		scrollPane.setViewportView(table_1);

		fillJList(table_1, user_name);
		
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListOfPatients = new JLabel("LIST OF PATIENTS");
		lblListOfPatients.setBounds(160, 12, 142, 15);
		contentPane.add(lblListOfPatients);
		
		JButton btnSort = new JButton("Sort By Name");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_1.getModel());
				table_1.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
				sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
//				sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
		});
		btnSort.setBounds(286, 276, 114, 25);
		contentPane.add(btnSort);
		
		JButton btnBack = new JButton("LOGOUT");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_Doctor ld = new login_Doctor();
				ld.setVisible(true);
			}
		});
		btnBack.setBounds(309, 7, 91, 25);
		contentPane.add(btnBack);
		
		JButton btnLoadData = new JButton("Sort By ID");
		btnLoadData.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_1.getModel());
				table_1.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
			
			
		});
		btnLoadData.setBounds(160, 276, 91, 25);
		contentPane.add(btnLoadData);
		
		
		contentPane.add(scrollPane);
		
		JButton btn_Sort_Type = new JButton("Sort By Type");
		btn_Sort_Type.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_1.getModel());
				table_1.setRowSorter(sorter);

				List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
				sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
		});
		btn_Sort_Type.setBounds(28, 278, 113, 23);
		contentPane.add(btn_Sort_Type);
		
		JButton btnViewProfile = new JButton("View Profile");
		btnViewProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				View_Doctor_Profile vdp = new View_Doctor_Profile(user_name, 0);
				vdp.setVisible(true);
				
			}
		});
		btnViewProfile.setBounds(28, 8, 113, 23);
		contentPane.add(btnViewProfile);
		
		JButton btnAdminResponsibility = new JButton("Admin Responsibility");
		btnAdminResponsibility.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdminHoD ahd = new AdminHoD(user_name);
				ahd.setVisible(true);
				
			}
		});
		btnAdminResponsibility.setBounds(135, 242, 154, 23);
		contentPane.add(btnAdminResponsibility);
	}
}