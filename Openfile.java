package schedular;
import java.util.Formatter;

public class Openfile {

	
	String fileName = "EmployeeData.txt";
	 private Formatter x;

	
	 
	 public void openFile() {
		 try { 
			 x = new Formatter(fileName);

		 }
		 catch(Exception e) {
			 System.out.println("File already exsists");
		 }
}	
	public void addRecords(int Hours, String Name, int ID) {
			x.format("%s%s%s%n", "" + ID + " ", Name, " " + Hours);
		}
	
		
	
	public void closeFile() {
		
		x.close();
	}
}
