package schedular;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateShift {
static JFrame shiftEditWindow = new JFrame();
static JButton[] dayEdit = new JButton[7];
static JButton[] num = new JButton[12];
static JButton[] num2 = new JButton[12];
static JButton[] min = new JButton[12];
static JButton[] min2 = new JButton[12];
static JButton breakLength[] = new JButton[5];
static JPanel shiftEditPanel[] = new JPanel[11];
static boolean am1, am2;
static boolean gotValues;
static float startTime, endTime, breaklength;
static int day;
static volatile Shift shiftOut;
static String startMinutes, endMinutes;
static JLabel ShiftEditLabel[] = new JLabel[4];
static Font labelFont = new Font("TimesRoman", 1, 30), buttonFont = new Font("default", Font.BOLD, 16), subLabelFont = new Font("TimesRoman", 1, 24);;
static JButton AM1 = new JButton("AM"),empNumSubmit = new JButton("SUBMIT"),PM1 = new JButton("PM"),AM2 = new JButton("AM");
static JButton shiftEditSubmitall = new JButton("Submit Shift to all days"), PM2 = new JButton("PM"), shiftEditSubmit = new JButton("Submit Shift");

	
	public CreateShift(int dayIn) {
		day = dayIn;
		gotValues = false;
		float values[] = new float[4];
		shiftOut = null;
		shiftEditWindow.setSize(JFrame.MAXIMIZED_HORIZ, JFrame.MAXIMIZED_VERT);
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
				
				shiftEditWindow.setVisible(false);
				values[0] = startTime;
				values[1] = endTime;
				values[2] = (float)day;
				values[3] = breaklength;
				gotValues = true;
				shiftEditWindow.getContentPane().removeAll();
				shiftEditWindow.dispose();
				shiftEditWindow.revalidate();
				
				shiftOut = new Shift(startTime, endTime, day, breaklength);
				System.out.println((startTime)+ " " + (endTime)+  " " + (day) + " " +(breaklength));
			//	startTime = (Float) null;
				//		endTime = (Float) null;
					//	day = (Integer) null;
						//breaklength = (Float) null;
						
				return;
			
			}});
		shiftEditSubmitall.addActionListener(new ActionListener() {
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
				
				shiftEditWindow.setVisible(false);
				values[0] = startTime;
				values[1] = endTime;
				values[2] = (float)day;
				values[3] = breaklength;
				gotValues = true;
				shiftEditWindow.getContentPane().removeAll();
				shiftEditWindow.dispose();
				shiftEditWindow.revalidate();
				
				shiftOut = new Shift(startTime, endTime, day, breaklength);
				System.out.println((startTime)+ " " + (endTime)+  " " + (day) + " " +(breaklength));
			//	startTime = (Float) null;
				//		endTime = (Float) null;
					//	day = (Integer) null;
						//breaklength = (Float) null;
						
				return;
			
			}});
		
		
		
		shiftEditWindow.setVisible(true);
		
	
	
		

	}
public static Shift getShift(float[] data) {
	
	return new Shift(data[0], data[1], (int) data[2], data[3]);
	
}
	
}
