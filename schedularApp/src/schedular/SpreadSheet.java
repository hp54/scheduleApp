package schedular;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SpreadSheet {
	DecimalFormat df = new DecimalFormat("#.##");
	Random ran = new Random();
	JFrame Error = new JFrame();
	employee[] employees = new employee[100];
	float numcheck,  numcheck2, dayHours, monHours, tuesHours, wedHours, thursHours, friHours, satHours, sunHours;
	float thisDayHours[]= new float[7];
	JButton newSchedule = new JButton("new Schedule");
	int j = 8, trialCheck, empCheck = 0, dayCheck = 0, m = 0,n = 0,x = 0,i = 10,empNum,fleeble = 0,numDayEditPanel,whatDay, whatEmp;
	int shiftNum = UserSetup.numOfValidShifts;
	Shift close[] = new Shift[shiftNum];
	int numOfEmployees = UserSetup.empNum;
	boolean am1 = true, switchCheck = false,am2 = false;
	String prefix[] = {"Sun", "Mon", "Tues", "Wednes","Thurs", "Fri","Satur"};
	JLabel dayLabel[]= new JLabel[7];
	JLabel day = new JLabel();
	JButton dayEditSub = new JButton("Submit");
	JPanel[] dayEditPanel = new JPanel[numDayEditPanel];
	JFrame dayEditWindow = new JFrame();
	

	JPanel[] setup = new JPanel[9];
	JButton AM1 = new JButton("AM"),PM1 = new JButton("PM"),AM2 = new JButton("AM"),PM2 = new JButton("PM");	
	JButton[] min = new JButton[12];
	JButton[] min2 = new JButton[12];
	JButton[] dayEdit = new JButton[7];
	JButton[] num = new JButton[12];
	JButton[] num2 = new JButton[12];
	ActionListener[][] action = new ActionListener[7][8];
	JButton[][] ShiftArray = new JButton[7][numOfEmployees];
	JButton[][] ShiftArraysub = new JButton[7][numOfEmployees], shiftChange = new JButton[7][numOfEmployees];
	JPanel[][] panelHolder = new JPanel[numOfEmployees + 2][8];    
	JFrame frame = new JFrame();
	JLabel promptLabel = new JLabel();
	JLabel shiftPromptLabel = new JLabel();
	GridLayout layout = new GridLayout(numOfEmployees +2,j);
	JFrame prompt = new JFrame();
	JFrame shiftPrompt = new JFrame();
	JButton add = new JButton("New Employee");
	JTextField promptField = new JTextField();
	JTextField shiftPromptField = new JTextField();
	GridLayout promptlayout = new GridLayout(4, 1);
	JFrame dayEditWindow2 = new JFrame();
	JLabel  empLabel[] = new JLabel[numOfEmployees], ErrorLabel1= new JLabel(), ErrorLabel2 = new JLabel();
	JButton noShift = new JButton();
	float empHours[] = new float[numOfEmployees];
	float minHours[]= new float[numOfEmployees];
	employee Employees[] = new employee[100];
	JButton promptBut = new JButton ("submit");
	String name[] = new String[numOfEmployees];
	//---------------------------------------------------------------------	
	public SpreadSheet() {
		for(int temp = 0; temp < numOfEmployees; temp ++) {
			minHours[temp] = UserSetup.weeklyHours[temp];
			name[temp] = UserSetup.names[temp];
		}
		promptField.setSize(30, 8);
		shiftPromptField.setSize(30, 8);
		frame.setSize(JFrame.MAXIMIZED_HORIZ, JFrame.MAXIMIZED_VERT);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setLayout(layout);
		prompt.add(promptLabel);
		prompt.add(promptField);
		Error.setSize(500, 300);
		Error.setLayout(new GridLayout(2,1));
		Error.add(ErrorLabel1);
		Error.add(ErrorLabel2);
		
	//-------------------------------------------------------------------------------
		//adds some preprogrammed shifts into the the program
	/*	for(int temp = 0; temp < 14; temp++) {
			close[temp] = new Shift((float)12.25, (float)21.25, fleeble, 1);
			close[temp+1] = new Shift((float)12.25, (float)21.25, fleeble, 1);
			fleeble++;
			temp++;
			if(temp == 13) {
				fleeble = 0;
			}
		}
		for(int temp = 14; temp < 21; temp++) {
			close[temp] = new Shift(11, 20, fleeble, 1);
			fleeble++;	
			if(temp == 20) {
				fleeble = 0;
			}
		}
		for(int temp = 21; temp < 28; temp++) {
			close[temp] = new Shift(10, 19, fleeble, 1);
			fleeble++;	
			if(temp == 27) {
				fleeble = 0;
			}
		}
		for(int temp = 28; temp < 35; temp++) {
			close[temp] = new Shift(9, 18, fleeble, 1);
			fleeble++;	
			if(temp == 34) {
				fleeble = 0;
			}
		}
		for(int temp = 35; temp < 42; temp++) {
			close[temp] = new Shift(8, 17, fleeble, 1);
			fleeble++;	
			if(temp == 41) {
				fleeble = 0;
			}
		}
		for(int temp = 42; temp < 49; temp++) {
			close[temp] = new Shift(7, 16, fleeble, 1);
			fleeble++;	
			if(temp == 48) {
				fleeble = 0;
			}
		}*/
		for(int temp = 0; temp < shiftNum; temp++) {
			close[temp] = UserSetup.transArray[temp];
	
		}
		
		
	//-------------------------------------------------------------------------------	
		//adds panels to main frame
		for(int m = 0; m < numOfEmployees +2; m++) {
			   for(int n = 0; n < j; n++) {
			      panelHolder[m][n] = new JPanel();
			      panelHolder[m][n].setBorder(BorderFactory.createLineBorder(Color.black));
			      if(m != 0 && n != 0) {
				      }
				      else {
				    	  panelHolder[m][n].setBackground(new Color(230, 240, 245));
				      }
			      frame.add(panelHolder[m][n]);
			   }
			}
		//Designs and adds buttons to the main frame
		for( m = 0; m < 7; m++) {
			   for( n = 0; n < numOfEmployees; n++) {
			      ShiftArray[m][n] = new JButton();
			      ShiftArraysub[m][n] = new JButton();
			      ShiftArraysub[m][n].setText("Move/Switch Shift");
			      ShiftArray[m][n].setPreferredSize(new Dimension(80, 60));
			      panelHolder[n+1][m+1].add(ShiftArray[m][n]);
			      panelHolder[n+1][m+1].setLayout(new GridLayout(1,1));
			      ShiftArray[m][n].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							int daySource = 0, empID = 0;
							
							for( m = 0; m < 7; m++) {
								for( n = 0; n < numOfEmployees; n++) {
									
									if(e.getSource() == ShiftArray[m][n]) {
										whatDay = m;
										whatEmp = n;
										
										if(!switchCheck) 
										
										{
											setup[8].add(ShiftArraysub[m][n]);
											setup[8].add(noShift);
											daySource = m;
											empID = n;
											shiftPromptField.setText(ShiftArray[m][n].getText());
											for(int b = 0; b < close.length; b++) {
												
												
												if(close[b].day == daySource && close[b].empID == empID) {
													for(int b2 = 0; b2 < 12; b2++) {
														num[b2].setEnabled(true);
														num2[b2].setEnabled(true);
														min[b2].setEnabled(true);
														min2[b2].setEnabled(true);
													}
													num[(int)close[b].startTime-1].setEnabled(false);
													min[close[b].startMin/5].setEnabled(false);
													num2[(int)close[b].endTime-1].setEnabled(false);
													min2[close[b].endMin/5].setEnabled(false);
														
														
														
														
					//ShiftArray[close[temp].day][close[temp].empID].setText(((int)close[temp].startTime) + ":" + close[temp].startMinutes+"-" + ((int)close[temp].endTime) + ":" + close[temp].endMinutes);

													
													
												}
												
												
												
											}
											shiftPrompt.setVisible(true);
										
										
										
										
										
										
										}
										else { 
											
											
											Shift tempShift1, tempShift2;
											int empSave1 = -7, empSave2 = -7;
											 float templength1 = 0, templength2 = 0;
											 for(int r = 0; r < close.length; r++ ) {
												 if(close[r].day == dayCheck && close[r].empID == empCheck) {
													 if(ShiftArray[dayCheck][empCheck].getText().equals("X")||ShiftArray[dayCheck][empCheck].getText().equals("")) {
														 templength1 = 0;
													 }
													 else {
													 templength1 = close[r].length; 
													 empSave1 = r;
													 }
													 
												 }
												 if(close[r].day == m && close[r].empID == n) {
													 
													 if(ShiftArray[m][n].getText().equals("X") || ShiftArray[m][n].getText().equals("")) {
														 templength2 = 0;
													 }
													 else {
													 templength2 = close[r].length; 
													 empSave2 = r;
													 }
													 
												 }
											 }
											 
											 if(empSave1 != -7) {
											 close[empSave1].empID = n;}
											 if(empSave2 != -7) {
												 close[empSave2].empID = empCheck;}
											 empHours[empCheck] = empHours[empCheck] - templength1 + templength2;
											 empHours[n] = empHours[n] + templength1 - templength2;
											 empLabel[empCheck].setText(name[empCheck] + " hours " + df.format(empHours[empCheck]));
											 empLabel[n].setText(name[n] + " hours " + df.format(empHours[n]));
											 
											 
											 
											 
											 
											//System.out.println( dayCheck + " " + empCheck + " " + m +" " + n );
											String temp = ShiftArray[dayCheck][empCheck].getText();
											Color tempColor = ShiftArray[dayCheck][empCheck].getBackground();
											 ShiftArray[dayCheck][empCheck].setBackground(ShiftArray[m][n].getBackground());
											 ShiftArray[dayCheck][empCheck].setText(ShiftArray[m][n].getText());
											 ShiftArray[m][n].setText(temp);
											 ShiftArray[m][n].setBackground(tempColor);
											
											 
											 
											 
											 //empHours[empCheck] = 
											 
											 
											 
											 
											 
											 
											 
											 
											
											 for( int m1 = 0; m1 < 7; m1++) {
												   for(int  n1 = 0; n1 < numOfEmployees; n1++) {
													   ShiftArray[m1][n1].setEnabled(true);
												   }}
											
											
											 switchCheck = false;
											
										}
										
										
										
										
										
								}
							}
							
								
							
							
							
							
							
							
							
							
							}
						   }
							});
			     ShiftArraysub[m][n].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							for( m = 0; m < 7; m++) {
								for( n = 0; n < numOfEmployees; n++) {
									if(e.getSource() == ShiftArraysub[m][n]) {
										for(int t = 0; t < numOfEmployees; t++) {
											for(int t1 = 0; t1 < 7; t1++) {
												if(t1 == m) {
													
												}
												else {
											ShiftArray[t1][t].setEnabled(false);
											switchCheck = true;
											empCheck = n;
											dayCheck = m;
												}
										}}
										
										for(int b = 0; b < close.length; b++) {
											if(close[b].empID == n && close[b].day ==m) {
												
												
												
												
												
												
												
												
												
											}
										}
										
										
										
									/*
										ShiftArray[m][n].setText(((int)close[temp].startTime) + ":" + close[temp].startMinutes+"-" + ((int)close[temp].endTime) + ":" + close[temp].endMinutes);
										ShiftArray[close[temp].day][close[temp].empID].setBackground(new Color(((int)close[temp].endTime*10), ((int)close[temp].startTime*21), ((int)close[temp].endTime)*(int)close[temp].startTime/3));
										empHours[close[temp].empID] = empHours[close[temp].empID] +close[temp].length;
										empLabel[close[temp].empID].setText(name[close[temp].empID] + " hours " + df.format(empHours[close[temp].empID]));
										
										*/
										
										if(shiftPromptField.getText().equals("x") || shiftPromptField.getText().equals("X")) {
											ShiftArray[m][n].setBackground(Color.RED);
										}
										else {
											
										}
										shiftPromptLabel.setText("");
										shiftPromptField.setText("");
										setup[8].removeAll();
										
										shiftPrompt.dispose();	
									}
								}
							}	
							}
							});
			     }
			}
		noShift.setText("NO SHIFT");
		noShift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setup[8].removeAll();
				shiftPrompt.dispose();
				if(ShiftArray[whatDay][whatEmp].getText().equals("X")) {
					ShiftArray[whatDay][whatEmp].setText("");
					ShiftArray[whatDay][whatEmp].setBackground(null);
				}
				else {
				ShiftArray[whatDay][whatEmp].setText("X");
				ShiftArray[whatDay][whatEmp].setBackground(Color.RED);
				 for(int r = 0; r < close.length; r++ ) {
					 if(close[r].day == whatDay && close[r].empID == whatEmp) {
						empHours[whatEmp] = empHours[whatEmp] - close[r].length; 
						empLabel[whatEmp].setText(name[whatEmp] + " hours " + df.format(empHours[whatEmp]));
						 
					 }
				 }
				
				
				}
			}});
//-------------------------------------------------------------------------------------------------------
		//Designs the employee labels on the far left of the spreadsheet
		for(int temp = 0; temp <numOfEmployees ; temp++) {
			panelHolder[temp +1][0].setLayout(new GridLayout(2, 1));
			empLabel[temp] = new JLabel(name[temp]);
			
			panelHolder[temp +1][0].add(empLabel[temp]);
		
		}
	//---------------------------------------------------------------------------------------
		//initializes and adds objects used on the top row of the spreadsheet
		for(int temp = 0; temp < 7; temp++) {
			dayLabel[temp] = new JLabel(prefix[temp] + "day");
			panelHolder[0][temp+1].add(dayLabel[temp]);
			panelHolder[0][temp+1].setLayout(new GridLayout(2,1));
			dayEdit[temp] = new JButton("edit day");
			panelHolder[0][temp+1].add(dayEdit[temp]);
			dayEdit[temp].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					
					for(int k = 0; k<7; k++) {
						if(e.getSource() == dayEdit[k]) {
							new DayView(k);
						}
					}
				}
			});
		}
		panelHolder[numOfEmployees+1][7].add(newSchedule);
		newSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				boolean isMet = false;
				
				x =0;
				trialCheck = 0;
				while(isMet==false){
					trialCheck++;
					
					
					if(trialCheck > 100000) {
						ErrorLabel1.setText("The Program Has tried " + (10000 + ran.nextInt(1834)+45)+ " Possible Schedules");
						ErrorLabel2.setText("You Might want to Check Your Conditions");
						Error.setVisible(true);
						Error.setVisible(true);
						return;
					}
					
					
					if(x != numOfEmployees) {
						
						for(int temp = 0; temp < numOfEmployees; temp++) {
							empHours[temp]=0;
							x = 0;
						}
						
						
						
						
						
					for(int temp = 0; temp < shiftNum; temp++) {
						close[temp].isFilled = false;
					}
					for(int temp = 0; temp < 7; temp++) {
						for(int temp1 = 0; temp1 < numOfEmployees; temp1++) {
							if(ShiftArray[temp][temp1].getText().equals("x") || ShiftArray[temp][temp1].getText().equals("X")){
								ShiftArray[temp][temp1].setBackground(Color.RED);
							}
							else {
								ShiftArray[temp][temp1].setText("");
								ShiftArray[temp][temp1].setBackground(null);
							}
						}
					}
				int numFixed = 0;
					for(int temp1 = 0; temp1 < shiftNum; temp1++) {
						if(close[temp1].isFixed) {
							numFixed++;
						}
					}
					int fixedShifts[] = new int[numFixed];
					int p = 0;
					for(int temp = 0; temp < shiftNum; temp++) {
						if(close[temp].isFixed) {
							if(ShiftArray[close[temp].day][close[temp].empID].getText().equals("")) {
								System.out.println("employee: " + close[temp].empID);
								ShiftArray[close[temp].day][close[temp].empID].setText(((int)close[temp].startTime) + ":" + close[temp].startMinutes+"-" + ((int)close[temp].endTime) + ":" + close[temp].endMinutes);
								ShiftArray[close[temp].day][close[temp].empID].setBackground(new Color(((int)close[temp].endTime*10), ((int)close[temp].startTime*21), ((int)close[temp].endTime)*(int)close[temp].startTime/3));
								empHours[close[temp].empID] = empHours[close[temp].empID] +close[temp].length;
								empLabel[close[temp].empID].setText(name[close[temp].empID] + " hours " + df.format(empHours[close[temp].empID]));
								close[temp].isFilled = true;
							}
							fixedShifts[p] = temp;
							p++;
							
						}
					}
					
					
				for(int temp = 0; temp < shiftNum; temp++) {
					boolean checkIt = false;
					for(int b = 0; b< numFixed; b ++) {
						if(fixedShifts[b] == temp) {
							checkIt = true;
						}
					}
					
					if(checkIt) {
						
					}
					else {
					
				int random = ran.nextInt((7));
				int random2 = ran.nextInt((numOfEmployees));
				
				while(close[temp].isFilled == false) {
					
				if(ShiftArray[close[temp].day][random2].getText().equals("")) {
					
					close[temp].empID = random2;
					
					
					
					for(int blah = 0; blah < 5; blah++) {
					if(empHours[close[temp].empID] >= minHours[close[temp].empID] && ShiftArray[close[temp].day][random2].getText().equals(""))
					{
						
						
						random2 = ran.nextInt((numOfEmployees));

						
						if(ShiftArray[close[temp].day][random2].getText().equals("")) {
						close[temp].empID = random2;
						}
					}
					else {
						blah = 5;
					}}
					
					
					
				ShiftArray[close[temp].day][close[temp].empID].setText(((int)close[temp].startTime) + ":" + close[temp].startMinutes+"-" + ((int)close[temp].endTime) + ":" + close[temp].endMinutes);
				ShiftArray[close[temp].day][close[temp].empID].setBackground(new Color(((int)close[temp].endTime*10), ((int)close[temp].startTime*21), ((int)close[temp].endTime)*(int)close[temp].startTime/3));
				empHours[close[temp].empID] = empHours[close[temp].empID] +close[temp].length;
				empLabel[close[temp].empID].setText(name[close[temp].empID] + " hours " + df.format(empHours[close[temp].empID]));
				close[temp].isFilled = true;
				close[temp].empID = random2;

			}
				else {
				 random2 = ran.nextInt((numOfEmployees));
				 
				}}}}
				
			for(int temp2 = 0; temp2 < numOfEmployees; temp2++) {
						if(minHours[temp2] <= empHours[temp2]) {
							x++;
						}
					}
			}
					
					else{
						isMet = true;
						System.out.println(trialCheck);
						}
					
					}
			}
				
			});
//----------------------------------------------------------------------------------------------------------------------
		//panelHolder[numOfEmployees + 1][7].add(add);
	//creates the prompt shift prompt and day edit windows
		prompt.setSize(300, 200);
		prompt.setLayout(promptlayout);
		shiftPrompt.setSize(300, 200);
		shiftPrompt.setLayout(promptlayout);
		shiftPrompt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		shiftPrompt.setSize((frame.getWidth()/4*3),(frame.getHeight()/4*3));
		shiftPrompt.setLayout(new GridLayout(12, 1));
		shiftPrompt.add(day);
		dayEditWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		dayEditWindow.setSize((frame.getWidth()/4*3),(frame.getHeight()/4*3));
		dayEditWindow.setLayout(new GridLayout(12, 1));
		dayEditWindow.add(day);
		for(int k2 = 0; k2 < setup.length; k2++) {
			setup[k2] = new JPanel();
		}
			setup[1].setLayout(new GridLayout(1, 13));
			setup[2].setLayout(new GridLayout(1, 13));
			setup[0].add(new JLabel("Start time"));
			setup[4].add(new JLabel("End time"));
			setup[5].setLayout(new GridLayout(1, 13));	
			setup[6].setLayout(new GridLayout(1, 13));	
			setup[3].add(AM1);
			setup[3].add(PM1);
			setup[7].add(AM2);
			setup[7].add(PM2);
			dayEditWindow.add(setup[0]);
			dayEditWindow.add(setup[2]);
			dayEditWindow.add(setup[3]);
			dayEditWindow.add(setup[4]);
			dayEditWindow.add(setup[5]);

			shiftPrompt.add(setup[0]);
			shiftPrompt.add(setup[1]);
			shiftPrompt.add(setup[2]);
			shiftPrompt.add(setup[3]);
			shiftPrompt.add(setup[4]);
			shiftPrompt.add(setup[5]);
			shiftPrompt.add(setup[6]);
			shiftPrompt.add(setup[7]);
			shiftPrompt.add(setup[8]);
		
			for(int k = 0; k < 12; k++) {
				int jib = k + 1;
				num[k] = new JButton("" + jib);
				num2[k] = new JButton("" + jib);
				
				if(k == 0) {
					min[k] = new JButton(":00");
					min2[k] = new JButton(":00");
				}
				else if(k == 1) {
					min[k] = new JButton(":05");
					min2[k] = new JButton(":05");
				}
				else {
				min[k] = new JButton(":" + (jib*5 - 5));
				min2[k] = new JButton(":" + (jib*5 - 5));
				}
				min[k].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						for(int l = 0; l < 12; l++) {
							if(e.getSource() == min[l]) {
								for(int gat = 0; gat < 12; gat++) {
									min[gat].setEnabled(true);
									min[l].setEnabled(false);
								}
							}
						}
					}});
				min2[k].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						for(int l = 0; l < 12; l++) {
							if(e.getSource() == min2[l]) {
								for(int gat = 0; gat < 12; gat++) {
									min2[gat].setEnabled(true);
									min2[l].setEnabled(false);
								}
							}
						}
					}});
				
				
				num[k].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						for(int l = 0; l < 12; l++) {
							if(e.getSource() == num[l]) {
								for(int gat = 0; gat < 12; gat++) {
									num[gat].setEnabled(true);
									num[l].setEnabled(false);
								}
							}
						}
					}});
				num2[k].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						for(int l = 0; l < 12; l++) {
							if(e.getSource() == num2[l]) {
								for(int gat = 0; gat < 12; gat++) {
									num2[gat].setEnabled(true);
									num2[l].setEnabled(false);
								}
							}
						}
					}});
				
				setup[1].add(num[k]);	
				setup[2].add(min[k]);
				

				
				setup[5].add(num2[k]);
				setup[6].add(min2[k]);

				
				}
prompt.add(new JLabel("Name:"));
prompt.add(promptField);
prompt.add(promptBut);
PM2.setEnabled(false);
AM1.setEnabled(false);
num[6].setEnabled(false);
num2[8].setEnabled(false);
for(int temp = 0; temp < numOfEmployees; temp++) {
	empHours[temp]= 0;
}

trialCheck = 0;

	boolean isMet = false;
	do {
		trialCheck++;
		
		
		if(trialCheck > 100000) {
			ErrorLabel1.setText("The Program Has tried " + (10000 + ran.nextInt(1834)+45)+ " Possible Schedules");
			ErrorLabel2.setText("You Might want to Check Your Conditions");
			Error.setVisible(true);
			Error.setVisible(true);
			return;
		}
		
		
		if(x != numOfEmployees) {
			
			for(int temp = 0; temp < numOfEmployees; temp++) {
				empHours[temp]=0;
				x = 0;
			}
			
			
			
			
			
		for(int temp = 0; temp < shiftNum; temp++) {
			close[temp].isFilled = false;
		}
		for(int temp = 0; temp < 7; temp++) {
			for(int temp1 = 0; temp1 < numOfEmployees; temp1++) {
				if(ShiftArray[temp][temp1].getText().equals("x") || ShiftArray[temp][temp1].getText().equals("X")){
					ShiftArray[temp][temp1].setBackground(Color.RED);
				}
				else {
					ShiftArray[temp][temp1].setText("");
					ShiftArray[temp][temp1].setBackground(null);
				}
			}
		}
	int numFixed = 0;
		for(int temp1 = 0; temp1 < shiftNum; temp1++) {
			if(close[temp1].isFixed) {
				numFixed++;
			}
		}
		int fixedShifts[] = new int[numFixed];
		int p = 0;
		for(int temp = 0; temp < shiftNum; temp++) {
			if(close[temp].isFixed) {
				if(ShiftArray[close[temp].day][close[temp].empID].getText().equals("")) {
					System.out.println("employee: " + close[temp].empID);
					ShiftArray[close[temp].day][close[temp].empID].setText(((int)close[temp].startTime) + ":" + close[temp].startMinutes+"-" + ((int)close[temp].endTime) + ":" + close[temp].endMinutes);
					ShiftArray[close[temp].day][close[temp].empID].setBackground(new Color(((int)close[temp].endTime*10), ((int)close[temp].startTime*21), ((int)close[temp].endTime)*(int)close[temp].startTime/3));
					empHours[close[temp].empID] = empHours[close[temp].empID] +close[temp].length;
					empLabel[close[temp].empID].setText(name[close[temp].empID] + " hours " + df.format(empHours[close[temp].empID]));
					close[temp].isFilled = true;
				}
				fixedShifts[p] = temp;
				p++;
				
			}
		}
		
		
	for(int temp = 0; temp < shiftNum; temp++) {
		boolean checkIt = false;
		for(int b = 0; b< numFixed; b ++) {
			if(fixedShifts[b] == temp) {
				checkIt = true;
			}
		}
		
		if(checkIt) {
			
		}
		else {
		
	int random = ran.nextInt((7));
	int random2 = ran.nextInt((numOfEmployees));
	
	while(close[temp].isFilled == false) {
		
	if(ShiftArray[close[temp].day][random2].getText().equals("")) {
		
		close[temp].empID = random2;
		
		
		
		for(int blah = 0; blah < 5; blah++) {
		if(empHours[close[temp].empID] >= minHours[close[temp].empID] && ShiftArray[close[temp].day][random2].getText().equals(""))
		{
			
			
			random2 = ran.nextInt((numOfEmployees));

			
			if(ShiftArray[close[temp].day][random2].getText().equals("")) {
			close[temp].empID = random2;
			}
		}
		else {
			blah = 5;
		}}
		
		
		
	ShiftArray[close[temp].day][close[temp].empID].setText(((int)close[temp].startTime) + ":" + close[temp].startMinutes+"-" + ((int)close[temp].endTime) + ":" + close[temp].endMinutes);
	ShiftArray[close[temp].day][close[temp].empID].setBackground(new Color(((int)close[temp].endTime*10), ((int)close[temp].startTime*21), ((int)close[temp].endTime)*(int)close[temp].startTime/3));
	empHours[close[temp].empID] = empHours[close[temp].empID] +close[temp].length;
	empLabel[close[temp].empID].setText(name[close[temp].empID] + " hours " + df.format(empHours[close[temp].empID]));
	close[temp].isFilled = true;
	close[temp].empID = random2;

}
	else {
	 random2 = ran.nextInt((numOfEmployees));
	 
	}}}}
	
for(int temp2 = 0; temp2 < numOfEmployees; temp2++) {
			if(minHours[temp2] <= empHours[temp2]) {
				x++;
			}
		}
}
		
		else{
			isMet = true;
			System.out.println(trialCheck);
			}
		
		}while(isMet == false);

	
		//------------------------------------------------
dayEditSub.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0)
	{
		for(int ugh = 0; ugh < 12; ugh++) {
			if(num[ugh].isEnabled()== false) {
				numcheck = ugh +1;
			}
			if(num2[ugh].isEnabled()== false) {
				numcheck2 = ugh +1;
			}
		}
		if(!am1) {
			numcheck = numcheck + 12;
		}
		if(!am2) {
			numcheck2 = numcheck2 + 12;
		}
			dayHours = numcheck2 - numcheck;
		for(int temp = 0; temp < 7; temp++) {	
		if(day.getText().contains(prefix[temp])) {
			thisDayHours[temp] = dayHours;
			dayLabel[temp].setText(prefix[temp] + "day Hours: " + dayHours);
		}}
			dayEditWindow.setVisible(false);
		
		
	}
});
AM1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0)
	{
		am1 =true;
		AM1.setEnabled(false);
		PM1.setEnabled(true);
	}
});
AM2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0)
	{
		am2 = true;
		AM2.setEnabled(false);
		PM2.setEnabled(true);
	}
});
PM2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0)
	{
		am2 = false;
		AM2.setEnabled(true);
		PM2.setEnabled(false);
	}
});
PM1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0)
	{
		am1 = false;
		AM1.setEnabled(true);
		PM1.setEnabled(false);
	}
});

		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
			prompt.setAlwaysOnTop(true);
			prompt.setVisible(true);
			}
		});
		promptBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			empLabel[empNum].setText(promptField.getText());
			prompt.setVisible(false);
		}
	});
		
		PM2.setEnabled(false);
		AM1.setEnabled(false);
		num[6].setEnabled(false);
		num2[8].setEnabled(false);
		frame.setVisible(true);
	}
	
	
}
