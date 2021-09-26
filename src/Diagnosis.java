import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Diagnosis extends JFrame {

	private JPanel contentPane;
	ButtonGroup G = new ButtonGroup();
	JRadioButton jRadioButton1 = new JRadioButton();
	JRadioButton jRadioButton2 = new JRadioButton();
	JLabel L1;
	//String pat_type="";
	Connection c;
	Statement st;
	String criticalField="";
	Logfile lgf=new Logfile();
	

	/**
	 * Launch the application.
	 */
	public void close()
	{
		WindowEvent closeEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeEvent);
	};
	
	

	public Diagnosis(String patient_name, String patient_id, String user_name) {
		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnCritical = new JRadioButton("Critical");
		JRadioButton rdbtnNonCritical = new JRadioButton("Non Critical");
		
		jRadioButton1.setText("Critical");
		jRadioButton1.setText("Non-Critical"); 
		jRadioButton1.setBounds(120, 30, 120, 50); 
		jRadioButton2.setBounds(250, 30, 80, 50); 
		G.add(jRadioButton1); 
	    G.add(jRadioButton2);
	    G.add(rdbtnCritical);
		rdbtnCritical.setBounds(198, 229, 109, 23);
		contentPane.add(rdbtnCritical);
		//JRadioButton rdbtnNonCritical = new JRadioButton("Non Critical");
		G.add(rdbtnNonCritical);
		rdbtnNonCritical.setBounds(198, 253, 109, 23);
		contentPane.add(rdbtnNonCritical);
		
		
		JLabel lblPatientDiagnosis = new JLabel("PATIENT DIAGNOSIS - ID (" + patient_id + ")");
		lblPatientDiagnosis.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientDiagnosis.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPatientDiagnosis.setBounds(83, 12, 263, 31);
		contentPane.add(lblPatientDiagnosis);
		
		JLabel lblPatientId = new JLabel(patient_name);
		lblPatientId.setBounds(35, 54, 107, 15);
		contentPane.add(lblPatientId);
		
		JLabel lblDiagnosis = new JLabel("DIAGNOSIS:");
		lblDiagnosis.setBounds(35, 88, 94, 15);
		contentPane.add(lblDiagnosis);
		
		JTextArea diagnosis_textArea = new JTextArea();
		diagnosis_textArea.setBounds(198, 88, 211, 61);
		contentPane.add(diagnosis_textArea);
		
		JTextArea medicine_textArea = new JTextArea();
		medicine_textArea.setBounds(198, 164, 211, 49);
		contentPane.add(medicine_textArea);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String diagnosis_pt = diagnosis_textArea.getText();
				String prescribed_md = medicine_textArea.getText();
				//JRadioButton rdbtnCritical = new JRadioButton("Critical");
				if(rdbtnCritical.isSelected())
				{
					criticalField = rdbtnCritical.getText();
				}
				else
				{
					criticalField = rdbtnNonCritical.getText();
				}
				
				System.out.println(criticalField);
			
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println(diagnosis_pt);
				System.out.println(prescribed_md);
				
				c=DriverManager.getConnection("jdbc:mysql://localhost/oopd","root","root");
				st=c.createStatement();
				st.executeUpdate("Update patient SET diagnosis = '"+diagnosis_pt+"', prescribed_medicines = '"+prescribed_md+"',patient_type = '"+criticalField+"' where uniqueId ='"+patient_id+"'");
				st.executeUpdate("Update patient SET status = '"+"Treated"+"' where uniqueId ='"+patient_id+"'");
				JOptionPane.showMessageDialog(contentPane, " \n SAVED...! ","Success", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
				System.out.println("Exception is here!!");
				lgf.logfile(" Exception Caught");

			}
			}
		
		});
		btnSave.setBounds(271, 300, 117, 25);
		contentPane.add(btnSave);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View_Patient_Details_Doctor vpdd = new View_Patient_Details_Doctor(patient_id, user_name);
				vpdd.setVisible(true);
			}
		});
		btnBack.setBounds(23, 300, 117, 25);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel(patient_name);
		lblNewLabel.setBounds(223, 54, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrescription = new JLabel("Prescription");
		lblPrescription.setBounds(35, 164, 76, 14);
		contentPane.add(lblPrescription);
		
		
		JLabel lblStateOfPatient = new JLabel("State of Patient");
		lblStateOfPatient.setBounds(35, 238, 131, 38);
		contentPane.add(lblStateOfPatient);
		
		L1 = new JLabel("Qualification"); 
		
	}
}