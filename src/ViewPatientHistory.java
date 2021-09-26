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

import javax.swing.JButton;

public class ViewPatientHistory extends JFrame {

	private JPanel contentPane;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewPatientHistory frame = new ViewPatientHistory();
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
	public ViewPatientHistory(String patient_id, String user_name) {
		String diagnosis = "";
		String medicines = "";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
			Statement s2=c.createStatement();
			ResultSet rs = s2.executeQuery("Select * from patient where uniqueId = '"+patient_id+"'");
			while(rs.next())
			{
				diagnosis = rs.getString("diagnosis");
				medicines = rs.getString("prescribed_medicines");
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			System.out.println("Exception is here!!");
			lgf.logfile(" Exception Caught");

		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPatientHistory = new JLabel("Patient History");
		lblPatientHistory.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPatientHistory.setBounds(161, 32, 110, 14);
		contentPane.add(lblPatientHistory);
		
		JLabel lblDiseaseTestsdiagnosis = new JLabel("Disease/ Tests (Diagnosis)");
		lblDiseaseTestsdiagnosis.setBounds(10, 74, 150, 14);
		contentPane.add(lblDiseaseTestsdiagnosis);
		
		JLabel lblNewLabel = new JLabel(diagnosis);
		lblNewLabel.setBounds(197, 74, 227, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prescribed Medicines");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 151, 150, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(medicines);
		lblNewLabel_2.setBounds(197, 151, 227, 46);
		contentPane.add(lblNewLabel_2);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				View_Patient_Details_Doctor vpdd = new View_Patient_Details_Doctor(patient_id, user_name);
				vpdd.setVisible(true);
				
				
			}
		});
		btnBack.setBounds(128, 227, 166, 23);
		contentPane.add(btnBack);
	}
}
