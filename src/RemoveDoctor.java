import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.*;
import java.util.Vector;

public class RemoveDoctor extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	String name = "";
	String contact = "";
	String category = "";
	String email = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveDoctor frame = new RemoveDoctor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RemoveDoctor() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("Select * from doctor");
			contentPane.setLayout(null);
			
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setBounds(10, 34, 395, 217);
			contentPane.add(scrollPane);
			
			table = new JTable(populateTable(rs));
			table.setBounds(401, 244, -385, -204);
			contentPane.add(table);
			
			while(rs.next())
			{
				 name =rs.getString("name");
				 contact = rs.getString("contact");
				 email = rs.getString("email");
				 category = rs.getString("category");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
		JLabel lblDoctorDetails = new JLabel("Doctor Details:");
		lblDoctorDetails.setBounds(10, 11, 109, 14);
		lblDoctorDetails.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblDoctorDetails);
	}

	public DefaultTableModel populateTable(ResultSet rs) {
		// TODO Auto-generated method stub
		
		try 
		{
			ResultSetMetaData metaData = rs.getMetaData();
			Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }
		    
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }

		    return new DefaultTableModel(data, columnNames);

			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
