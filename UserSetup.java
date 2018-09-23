package schedular;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserSetup {
	static int numOfShifts = 0;
	String startMinutes, endMinutes, shiftFileName = "shiftData1.bin";
	Openfile g = new Openfile();
	float startTime, endTime, breaklength;
	static Shift shiftArray[] = new Shift[200], transArray[];
	int daycheck;
	JLabel ShiftEditLabel[] = new JLabel[4];
	Font labelFont = new Font("TimesRoman", 1, 30), buttonFont = new Font("default", Font.BOLD, 16), subLabelFont = new Font("TimesRoman", 1, 24);;
	float[] values;
	JFrame shiftEditWindow = new JFrame();
	JPanel shiftEditPanel[] = new JPanel[11];
	JLabel emEr = new JLabel();
	JButton breakLength[] = new JButton[5];
	public static String names[];
	static int weeklyHours[], empNum = 12, numOfValidShifts;
	static BlueButton submit = new BlueButton("Submit");
	JButton dayEditClose = new JButton("Done") ;
	JTextField getName = new JTextField();
	JTextField gethours = new JTextField();
	BlueButton confirmEmpNum = new BlueButton("Yes");
	CreateShift createShift;

	JButton AM1 = new JButton("AM"),empNumSubmit = new JButton("SUBMIT"),PM1 = new JButton("PM"),AM2 = new JButton("AM"), shiftEditSubmitall = new JButton("Submit Shift to all days"), PM2 = new JButton("PM"), shiftEditSubmit = new JButton("Submit Shift");

	boolean am1 = true, fileExsists = false, am2 = false;
	JFrame frame = new JFrame();
	JFrame empEditFrame = new JFrame();
	JLabel er = new JLabel();
	String prefix[] = {"Sun", "Mon", "Tues", "Wednes","Thurs", "Fri","Satur"};
	JLabel dayLabel[]= new JLabel[7];
	JLabel day = new JLabel();
	JButton dayEditSub = new JButton("Submit");
	//JPanel[] dayEditPanel = new JPanel[numDayEditPanel];
	JFrame dayEditWindow = new JFrame();
	JButton[] dayEdit = new JButton[7];
	JButton[] num = new JButton[12];
	JButton[] num2 = new JButton[12];
	JButton[] min = new JButton[12];
	JButton[] min2 = new JButton[12];
	JPanel erPanel = new JPanel();
	Scanner dataScanner;
	JPanel empSubPanel = new JPanel(), empConfirmPanel = new JPanel();
	JLabel empNumLabel = new JLabel("Number of Employees: ");
	JButton newShift = new JButton("new Shift");
	//JFormattedTextField empNumField = new JFormattedTextField(NumberFormat.getNumberInstance());
	volatile static boolean isSetup = false;
	JTextField empNumField = new JTextField();
	public UserSetup() {

	try {
		dataScanner = new Scanner(new File("EmployeeData.txt"));
		fileExsists = true;
	}
	catch(Exception e) {
		
	}
//-----------------------------------------------------------------------------------------------------------		
		frame.setSize(JFrame.MAXIMIZED_HORIZ, JFrame.MAXIMIZED_VERT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		empSubPanel.setBackground(new Color(230, 240, 245));
		erPanel.setBackground(empSubPanel.getBackground());
		frame.setLayout(new GridLayout(6, 1));
		frame.add(empNumLabel);
		empNumLabel.setFont(labelFont);
		frame.getContentPane().setBackground(empSubPanel.getBackground());
		frame.add(empNumField);
		frame.add(empSubPanel);
		empNumField.setHorizontalAlignment(JTextField.CENTER);
		frame.add(erPanel);
		erPanel.add(er);
		er.setFont(subLabelFont);
		empNumField.setFont(labelFont);
		
		empNumSubmit.setFont(buttonFont);
		empNumSubmit.setSize(20, 50);
		empNumSubmit.setBackground(Color.blue.darker());
		empNumSubmit.setForeground(Color.WHITE);
		empSubPanel.add(empNumSubmit);
		empSubPanel.setLayout(new GridBagLayout());
		frame.add(empConfirmPanel);
//------------------------------------------------------------------------------------------------------------		
		empNumSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {// if is number
				    Integer.parseInt(empNumField.getText());
				    empNum = Integer.parseInt(empNumField.getText());
				    er.setText("You would like " + empNum + " Employees to be scheduled?");
				    empConfirmPanel.add(confirmEmpNum);
				    confirmEmpNum.setVisible(!false);
				} catch (NumberFormatException e1) {
					
					empNumField.setText("");
				    er.setText("Please enter a number");
				    confirmEmpNum.setVisible(false);
				}
				
				try {
					ObjectInputStream is = new ObjectInputStream(new FileInputStream(shiftFileName));
						try {
							List<Shift> savedShifts = (List<Shift>) is.readObject();
							Object[] tempArray = savedShifts.toArray();
							System.out.println(savedShifts.size());
							
							 for (int i =0; i < savedShifts.size(); i++) {
								    final int i2 = i;
								 
								 	
								 	
								 	
								 		shiftArray[i] = (Shift) tempArray[i];
								 		numOfShifts++;
								 	System.out.println(i);
								 	 shiftArray[i].assign.isFixed =shiftArray[i].isFixed;
								 	
								 	
								 	shiftArray[i].button.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e)
										{
											if(shiftArray[i2].IsDeleted == false) {
												shiftArray[i2].IsDeleted = true;
												shiftArray[i2].button.setBackground(Color.red);
											}
											else {
												shiftArray[i2].button.setBackground(null);
												shiftArray[i2].IsDeleted = false;
											}
											
											
										}
											
										});
							 }
						        
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						is.close();
					
					
				}catch(FileNotFoundException e2){
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
				
				
			}});
//---------------------------------------------------------------------------------------------------------------------		
		confirmEmpNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//====================================================================================================
				
				names = new String[empNum];
				weeklyHours = new int[empNum];
				empEditFrame.setSize(frame.getWidth()/2, frame.getHeight()/2);
				if(fileExsists) {
					String testHours[] = new String[empNum];
					String testID[] = new String[empNum];
					int whoCares = 0;
				while(whoCares < empNum) {
					
					if(dataScanner.hasNextLine()) {
						try {
					testID[whoCares] = dataScanner.next();
					names[whoCares] = dataScanner.next();
					testHours[whoCares] = dataScanner.next();
					weeklyHours[whoCares] = Integer.parseInt(testHours[whoCares]);
					
					whoCares++;
					}
					catch(Exception e2) {
						
					}}
					else {
						names[whoCares] = ("Employee" +(whoCares + 1));
						
						weeklyHours[whoCares] = 0;
						whoCares++;
						
					}
				}
				}
				//==========================================================================================
				empEditFrame.setLayout(new GridLayout(7, 1));
				empEditFrame.add(new JLabel("Employee Name"));
				empEditFrame.add(getName);
				empEditFrame.add(new JLabel("Minimum Weekly Hours"));
				empEditFrame.add(gethours);
				empEditFrame.add(emEr);
				frame.getContentPane().removeAll();
				frame.repaint();
				frame.revalidate();
				frame.setLayout(new GridLayout((empNum +1), 8));
				JButton empEdit[] = new JButton[empNum];
				JButton empEditSub[] = new JButton[empNum];
				JPanel panels[][] = new JPanel[empNum+1][8];
				
				//=======================================================================================
				for(int m = 0; m < empNum+1 ; m++) {
					   for(int n = 0; n < 8; n++) {
					      panels[m][n] = new JPanel();
					      if(m != 0 && n != 0) {
					      panels[m][n].setBackground(new Color(200, 240, 245));
					      }
					      else {
					    	  panels[m][n].setBackground(new Color(230, 240, 245));
					      }
					      panels[m][n].setBorder(BorderFactory.createLineBorder(Color.black));
					      frame.add(panels[m][n]);
					   }
					}
				//========================================================================
				//initializes and adds objects used on the top row of the spreadsheet
				for(int temp = 0; temp < 7; temp++) {
					dayLabel[temp] = new JLabel(prefix[temp] + "day");
					panels[0][temp+1].add(dayLabel[temp]);
					panels[0][temp+1].setLayout(new GridLayout(2,1));
					//labels the top row as the days of the week
					dayEdit[temp] = new JButton("edit day");
					panels[0][temp+1].add(dayEdit[temp]);
					//adds the day edit button
					
					dayEdit[temp].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							dayEditWindow.setSize(JFrame.MAXIMIZED_HORIZ, JFrame.MAXIMIZED_VERT);
							dayEditWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							dayEditWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
							dayEditWindow.setLayout(new GridLayout(18, 1));
							dayEditWindow.add(day);
							dayEditWindow.add(newShift);
							dayEditWindow.add(dayEditClose);
							dayEditWindow.setVisible(true);
							for(int k = 0; k<7; k++) {
								if(e.getSource() == dayEdit[k]) {
									daycheck = k;
									
									if(k == 0) {
										day.setText("Sunday");
									}
									if(k == 1) {
										day.setText("Monday");
									}
									if(k == 2) {
										day.setText("Tuesday");
									}
									if(k == 3) {
										day.setText("Wednesday");
									}
									if(k == 4) {
										day.setText("Thursday");
									}
									if(k == 5) {
										day.setText("Friday");
									}
									if(k == 6) {
										day.setText("Saturday");
										
									}
									for(int ugh = 0; ugh < numOfShifts; ugh++) {
									
										
										//System.out.println(shiftArray[ugh].day);
										
										if(shiftArray[ugh].day == daycheck) { 
											dayEditWindow.add(shiftArray[ugh].panel);
											shiftArray[ugh].assign.update();
										
											//dayEditWindow.add(new JLabel((int)shiftArray[ugh].startTimeFloat + ":" + shiftArray[ugh].startMinutes + "-"+ (int)shiftArray[ugh].endTime + ":" + shiftArray[ugh].endMinutes + " break length: " + shiftArray[ugh].breakTime));
										}
										
										
									}
								}
							}
						}
					});
				}
				dayEditClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						dayEditWindow.getContentPane().removeAll();
						dayEditWindow.repaint();
						dayEditWindow.revalidate();
						dayEditWindow.setVisible(false);
					}
						});
				//=====================================================================================================
				panels[empNum][7].add(submit);
				JLabel EmpLabel[] = new JLabel[empNum];
				for(int m = 0; m < empNum ; m++) { 
					
					empEdit[m] = new JButton("Edit Employee");
					empEditSub[m] = new JButton("submit");
					try {
					if(names[m].equals("null")) {
					EmpLabel[m] = new JLabel("Employee " + (m+ 1));
					}
					else {
						EmpLabel[m] = new JLabel(names[m] + " Min Hours: " + weeklyHours[m]);
					}}
					catch(Exception e4){
						EmpLabel[m] = new JLabel("Employee " + (m+ 1));

					}
					panels[m +1][0].setLayout(new GridLayout(2, 1));
					panels[m +1][0].add(EmpLabel[m]);
					panels[m +1][0].add(empEdit[m]);
					empEdit[m].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e2)
						{
							
							for(int d = 0; d < empNum; d++) {
								if(e2.getSource() == empEdit[d]) {
									if(names[d].equals("null")) {
							getName.setText(EmpLabel[d].getText());
									}
									else {
										getName.setText(names[d]);
									}
							empEditFrame.add(empEditSub[d]);}
							}
							
							empEditFrame.setVisible(true);
						}});}
				//======================================================================================================
			/*	shiftEditWindow.setSize(JFrame.MAXIMIZED_HORIZ, JFrame.MAXIMIZED_VERT);
				shiftEditWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				shiftEditWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
				shiftEditWindow.setLayout(new GridLayout(11, 1));
				for(int tempint = 0; tempint< 11; tempint++) {
					if(tempint < 4){
					ShiftEditLabel[tempint] = new JLabel();
						ShiftEditLabel[tempint].setFont(labelFont);
					}
					if(tempint <5) {
						
						breakLength[tempint] = new JButton();
						int timeint = (tempint)*15;
						breakLength[tempint].setText("" + timeint);
						breakLength[tempint].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							for(int l = 0; l < 5; l++) {
								if(e.getSource() == breakLength[l]) {
									for(int gat = 0; gat < 5; gat++) {
										breakLength[gat].setEnabled(true);
										breakLength[l].setEnabled(false);
									}
								}
							}
						}
							
						});
					}
					shiftEditPanel[tempint] = new JPanel();
					shiftEditWindow.add(shiftEditPanel[tempint]);
				}
				ShiftEditLabel[0].setText("START TIME");
				ShiftEditLabel[1].setText("END TIME");
				ShiftEditLabel[3].setText("BREAK LENGTH");
				shiftEditPanel[0].add(ShiftEditLabel[0]);
				shiftEditPanel[4].add(ShiftEditLabel[1]);
				shiftEditPanel[8].add(ShiftEditLabel[3]);
				shiftEditPanel[9].setLayout(new GridLayout(1, 4));
				shiftEditPanel[10].add(shiftEditSubmit);
				shiftEditPanel[10].add(shiftEditSubmitall);
				shiftEditPanel[9].add(breakLength[0]);
				shiftEditPanel[9].add(breakLength[1]);
				shiftEditPanel[9].add(breakLength[2]);
				shiftEditPanel[9].add(breakLength[3]);
				shiftEditPanel[9].add(breakLength[4]);

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
					//=================================================================================
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
					//====================================================================================
					shiftEditPanel[1].add(num[k]);
					shiftEditPanel[2].add(min[k]);
					shiftEditPanel[3].add(AM1);
					shiftEditPanel[3].add(PM1);
					shiftEditPanel[5].add(num2[k]);
					shiftEditPanel[6].add(min2[k]);
					shiftEditPanel[7].add(AM2);
					shiftEditPanel[7].add(PM2);					
				}
				//================================================================================================
				shiftEditPanel[1].setLayout(new GridLayout(1, 13));	
				shiftEditPanel[5].setLayout(new GridLayout(1, 13));
				shiftEditPanel[2].setLayout(new GridLayout(1, 13));	
				shiftEditPanel[6].setLayout(new GridLayout(1, 13));
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
				
				newShift.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						shiftEditWindow.setVisible(true);
						
						
					}});
				//===========================================================================================
				shiftEditSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						for(int ugh = 0; ugh < 12; ugh++) {
							if(ugh<5) {
								if(breakLength[ugh].isEnabled()== false) {
									breaklength = ((float)ugh * 15/60);
								}
							}
							
							if(num[ugh].isEnabled()== false) {
								startTime = ugh +1;
							}
							if(num2[ugh].isEnabled()== false) {
								endTime = ugh +1;
							}
						}
							for(int ugh = 0; ugh < 12; ugh++) {
								if(min[ugh].isEnabled()== false) {
									startTime = startTime + (((((float)ugh +1)*5) -5)/60);
									startMinutes = min[ugh].getText();
								}
								if(min2[ugh].isEnabled()== false) {
									endTime = endTime + (((((float)ugh +1)*5) -5)/60);
									endMinutes = min2[ugh].getText();
								}
							
							}
						
						if(!am1) {
							if((int)startTime == 12) {
								
							}
							else {
							startTime = startTime + 12;
							}
						}
						if(!am2) {
							if((int)endTime == 12) {
								
							}
							else {
								endTime = endTime + 12;
							}
							
						}
							shiftArray[numOfShifts] = new Shift(startTime, endTime, daycheck, breaklength);
							shiftEditWindow.setVisible(false);
							dayEditWindow.add(shiftArray[numOfShifts].panel);
							dayEditWindow.setVisible(true);
							numOfShifts++;
							
					}});
					*/
				
				newShift.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						createShift = new CreateShift(daycheck);
					 
						
					}});
					CreateShift.shiftEditSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						shiftArray[numOfShifts] = createShift.shiftOut;
						shiftEditWindow.setVisible(false);
						dayEditWindow.add(shiftArray[numOfShifts].panel);
						dayEditWindow.setVisible(true);
						numOfShifts++;
						createShift = null;
						
					}
						
					});
				//====================================================================================================
				CreateShift.shiftEditSubmitall.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						
						for(int blah = 0; blah < 7; blah++) {
							shiftArray[numOfShifts] = new Shift(createShift.startTime, createShift.endTime, blah, createShift.breaklength);
							numOfShifts++;
						}
						shiftEditWindow.setVisible(false);
						dayEditWindow.add(shiftArray[numOfShifts-1].panel);
							dayEditWindow.setVisible(true);
							
							
					}});
				//===================================================================================
				
			
				
				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e2)
					{
						
						g.openFile();
						for(int temp = 0; temp < empNum; temp++) {
							g.addRecords(weeklyHours[temp], names[temp], temp);
						}
						
						g.closeFile();
						numOfValidShifts = 0;
						int checkthing = 0;
						for(int k = 0; k < numOfShifts; k++) {
								if(!shiftArray[k].IsDeleted) {
									numOfValidShifts++;
									
									}
							}
						try {
							
							
							ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(shiftFileName));
							Shift writeArray[] = new Shift[numOfValidShifts];
							System.out.println(numOfValidShifts);
							for(int plz = 0; plz < numOfShifts; plz++) {
								shiftArray[plz].isFixed = shiftArray[plz].assign.isFixed;
								System.out.println(shiftArray[plz].isFixed);
								if(shiftArray[plz].isFixed && shiftArray[plz].assign.empID != -3) {
									shiftArray[plz].empID = shiftArray[plz].assign.empID;
								}
								if(!shiftArray[plz].IsDeleted) {
								writeArray[checkthing]=shiftArray[plz];
								checkthing++;
								}
							}
							
								transArray = writeArray;
								List<Shift> storageList =  Arrays.asList(writeArray);
								os.writeObject(storageList);
								os.close();
							
							
						}catch(FileNotFoundException e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
						
						
						isSetup = true;
					}
						
					}); 
					for(int d = 0; d < empNum; d++) {
					empEditSub[d].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e3)
						{
							for(int k = 0; k < empNum;k++) {
								if(e3.getSource()==empEditSub[k]) {
							try {// if is number
							    Integer.parseInt(gethours.getText());
							    weeklyHours[k] = Integer.parseInt(gethours.getText());
							    if(getName.getText().contains(" ")) {
							    	emEr.setText("Sorry but at the moment no spaces in Employee names I'll fix it tho");
							    }
							    else {
							    names[k] = getName.getText();
							    EmpLabel[k].setText(getName.getText() + " Min Hours: " + weeklyHours[k]);
							    empEditFrame.setVisible(false);
							    empEditFrame.remove(empEditSub[k]);
							    frame.setEnabled(true);
							    emEr.setText("");
							    }
							    
							} catch (NumberFormatException e1) {
								
								emEr.setText("Pleas enter a Number for weekly hours");
							}}}
							
						}});
					}
				
				frame.setVisible(true);
			}});
		
		
		
		
		
	}
	
}
