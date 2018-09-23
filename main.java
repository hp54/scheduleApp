package schedular;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
static double x;
	public static void main(String args[]) {
		new UserSetup();
		//new DayView(2);
	//	new CreateShift(3);
			UserSetup.isSetup = false;

		UserSetup.submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				new SpreadSheet();
				
			}
			});
		
		
		
	}
	
	
}
