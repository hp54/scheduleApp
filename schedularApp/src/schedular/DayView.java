package schedular;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class DayView extends JFrame {
JFrame dayFrame = new JFrame();
String prefix[] = {"Sun", "Mon", "Tues", "Wednes", "Thurs", "Fri", "Satur"};
JLabel Title = new JLabel();	
Font labelFont = new Font("TimesRoman", 1, 30);
JLabel test = new JLabel("test");
JPanel fillPanel[] = new JPanel[24];
JPanel dayInfo = new JPanel();
GridBagConstraints gbc = new GridBagConstraints();
	public DayView(int day) {
		for(int k = 0; k< fillPanel.length; k++) {
			fillPanel[k] = new JPanel();
			fillPanel[k].setBackground(Color.GREEN);
			fillPanel[k].setBorder(BorderFactory.createLineBorder(Color.black));
		}
		setSize(JFrame.MAXIMIZED_HORIZ, JFrame.MAXIMIZED_VERT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		dayInfo.setBackground(Color.red);
		Title.setText(prefix[day] + "day");
		Title.setFont(labelFont);
		add(Title, BorderLayout.PAGE_START);
		add(fillPanel[1], BorderLayout.LINE_START);
		add(dayInfo,  BorderLayout.CENTER);
	
		
			setVisible(true);
	}
	
	
}
