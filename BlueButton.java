package schedular;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class BlueButton extends JButton {

	Font buttonFont = new Font("default", Font.BOLD, 16);
	
	public BlueButton(String Label) {
		
		setText(Label);
		setFont(buttonFont);
		setSize(20, 50);
		setBackground(Color.blue.darker());
		setForeground(Color.WHITE);
		
		
	}
}
