//Reference taken from stack overflow : https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logfile {

	public void logfile(String msg){
		Logger lg = Logger.getLogger("logfile");  
	    FileHandler fh;  

	    try {  
	        
	        fh = new FileHandler("C:/Users/Pavilion/eclipse-workspace/SHS/logfile");  
	        lg.addHandler(fh);
	        SimpleFormatter fmt = new SimpleFormatter();  
	        fh.setFormatter(fmt);  

	        lg.info("My first log");  
	        lg.info(msg);  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } 
	}

	
}
