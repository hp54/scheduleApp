package schedular;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Shift implements Serializable {
	float startTimeFloat, endTime, hourendTime,    breakTime, length;
	boolean isFilled, IsDeleted = false;
	volatile boolean isFixed;
	volatile ActionListener plz;
	volatile int empID;
	int startTime, day,  startMin, endMin;
	String startMinutes, endMinutes;
	volatile JButton button;
	AssignButton assign = new AssignButton("");
	volatile JPanel panel;
	public Shift(float start, float end, int theDay, float breek) {
	
		this.hourendTime = end;
		this.day = theDay;
		this.breakTime = breek;
		this.isFilled = false;
		this.startTimeFloat = start;
		this.startTime = (int) startTimeFloat;
		this.length = (hourendTime - startTimeFloat) - breakTime;
		this.startMin = ((int) (Math.round((startTimeFloat - startTime)*60)));
		this.endMin = ((int) (Math.round((hourendTime - (int)hourendTime)*60)));
		this.startMinutes = String.valueOf((int) (Math.round((startTimeFloat - startTime)*60)));
		
		if((int)startTimeFloat == 12) {
			 startTime= 12;
		}
		else {
			startTime = startTime%12;
		}
		
		if(startMinutes.equals("0")) {
			startMinutes = "00";
		}
		if(startMinutes.equals("5")) {
			startMinutes = "05";
		}
		
		if(hourendTime == 12) {
			endTime = hourendTime;
		}
		else {
			endTime = hourendTime%12;
		}
		this.endMinutes = String.valueOf((int) (Math.round((hourendTime - (int)hourendTime)*60)));
		if(endMinutes.equals("0")) {
			endMinutes = "00";
		}
		if(endMinutes.equals("5")) {
			endMinutes = "05";
		}
		this.panel = new JPanel();
		if(isFixed == true) {assign.setText("Remove Assignment to " + UserSetup.names[empID]);
			assign.setBackground(Color.green);
		
		}
		else {
			assign.setText("Assign to Employee");
		}
		
	this.button = new JButton((int)startTime +":"+ startMinutes + "-" + (int)endTime +":"+ endMinutes + "Length: " + length);
	panel.add(button);
	panel.add(assign);
	panel.add(new JPanel());
	button.addActionListener(new ActionListener() {
		
	public void actionPerformed(ActionEvent e)
		{
			if(IsDeleted == false) {
			IsDeleted = true;
			button.setBackground(Color.red);
			}
			else {
				button.setBackground(null);
				IsDeleted = false;
			}
			
			
		}
			
		});
	
	
	
	
	
				
			

			
			
			
	
	}
	
	/*needed values
	 * Total Work Hours in the week
	 * Number of Employees
	 * Number of hours needed for each employee 
	 * Days employees have worked 
	 * hours employees have worked 
	 * 
	 * 
	 * 
	 * Things to consider:
	 * employees can't work twice in one day
	 * employees want days off adjacent to each other 
	 * 
	 * FUN SOLUTIONS
	 * 
	 * additional day structure in the edit day window
	 * user will be in charge of structuring shifts for the day
	 * This will make it so the user keeps the scheduled shifts but also allows 
	 * the program to pick from a selected pool of shifts for the schedule
	 * 
	 * 
	 * if(weeklyhours > totalRequiredHours){
	 * }
	 * else{
	 * 
	 */
	
	public int[] getShift(int daysworked, int hoursworked, int day, int hoursneeded){
		return null;
		
	}
}
