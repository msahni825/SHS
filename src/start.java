import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

public class start extends JFrame {

	private JPanel contentPane;
	Logfile lgf=new Logfile();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					start frame = new start();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
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
	public start() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdmin = new JButton("ADMIN");
		
		btnAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				close();
				login_Admin loginAdmin = new login_Admin();
				loginAdmin.setVisible(true);
				
			}
		});
		btnAdmin.setBounds(181, 112, 89, 23);
		contentPane.add(btnAdmin);
		
		JButton btnNewButton = new JButton("PATIENT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				close();
				login_Patient loginPatient = new login_Patient();
				loginPatient.setVisible(true);
			}
		});
		btnNewButton.setBounds(181, 146, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DOCTOR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				close();
				login_Doctor loginDoctor = new login_Doctor();
				loginDoctor.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(181, 180, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblShs = new JLabel("SMART HEALTH PROJECT");
		lblShs.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblShs.setBounds(105, 35, 238, 23);
		contentPane.add(lblShs);
	}
}
