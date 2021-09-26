import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Toolkit;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	 */
	public AdminPage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Add Doctor");
		btnNewButton.setBounds(54, 63, 301, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
				RegisterDoctor registerDoctor = new RegisterDoctor();
				registerDoctor.setVisible(true);
				
			}
		});
		contentPane.setLayout(null);
		
		JLabel lblWelcomeadmin = new JLabel("Welcome ADMIN!!");
		lblWelcomeadmin.setBounds(145, 23, 144, 17);
		lblWelcomeadmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblWelcomeadmin);
		contentPane.add(btnNewButton);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(54, 264, 301, 23);
		
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				start st = new start();
				st.setVisible(true);
			}
		});
		
		contentPane.add(btnLogout);
		int temp=0;
		JButton btnViewDetails = new JButton("View Details");
		btnViewDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				ViewDetails_Admin viewDetail;
				
				try {
//					if(temp==1)
//					{
					close();
					viewDetail = new ViewDetails_Admin();
					viewDetail.setVisible(true);
//				} 
				
//				else
//					{
//					throw new SQLException();
//					}
			}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Exception is here!!");
					 lgf.logfile(" Exception caught");
				}
				
				
			}
		});
		btnViewDetails.setBounds(54, 97, 301, 23);
		contentPane.add(btnViewDetails);
		
		JButton btnAddDepartment = new JButton("Add Department");
		btnAddDepartment.setBounds(54, 131, 301, 23);
		btnAddDepartment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				close();
				AddDepartment addDepartment = new AddDepartment();
				addDepartment.setVisible(true);
				
			}
		});
		contentPane.add(btnAddDepartment);
		
		JButton btnUpdateDepartment = new JButton("Update Department");
		btnUpdateDepartment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//close();
				UpdateDepartment ud = new UpdateDepartment();
				ud.setVisible(true);
			}
		});
		btnUpdateDepartment.setBounds(54, 165, 301, 23);
		contentPane.add(btnUpdateDepartment);
		
		JButton btnDischargePatient = new JButton("Discharge Patient");
		btnDischargePatient.setBounds(54, 63, 301, 23);
		btnDischargePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
				DischargePatient dischargepatient = null;
				try {
//					if(temp==1)
//					{
//					
					dischargepatient = new DischargePatient();
					//}
//					else
//					{
//					
//						throw new SQLException();
//					}
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
//					System.out.println("Exception is here!!");
//					 lgf.logfile("Admin Exception 2");
//	
				}
				dischargepatient.setVisible(true);
				
			}
		});
		
		btnDischargePatient.setBounds(54, 199, 301, 23);
		contentPane.add(btnDischargePatient);
		
		JButton btnAddRoomsIn = new JButton("Add Rooms in Hospital ");
		btnAddRoomsIn.setBounds(54, 63, 301, 23);
		btnAddRoomsIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
				AddRoom addroom = new AddRoom();
				addroom.setVisible(true);
				
			}
		});
		
		btnAddRoomsIn.setBounds(54, 230, 301, 23);
		contentPane.add(btnAddRoomsIn);
		
			}
}
