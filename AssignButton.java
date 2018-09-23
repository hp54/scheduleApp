package schedular;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AssignButton extends JButton{
 volatile boolean isFixed;
	int empID = -3;
	
	public AssignButton(String label) {
		setText(label);
		
		
		
		
		
		
		
	}
	public int update() {
		addActionListener(new  ActionListener() {
			public void actionPerformed(ActionEvent e3)
			{	System.out.println("called");
				final JFrame frame = new JFrame();
				if(!isFixed) {
				JButton assignEmp[] = new JButton[UserSetup.empNum];
				JPanel assignPanel[] = new JPanel[UserSetup.empNum];
				frame.getContentPane().removeAll();
				frame.dispose();
				frame.revalidate();
				frame.setSize(600, 400);
				frame.setLayout(new GridLayout((UserSetup.empNum), 1));
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				for(int k = 0; k < UserSetup.empNum; k++) {
					
					
					
					assignPanel[k] = new JPanel();
					 assignEmp[k] = new JButton("Assign: " + UserSetup.names[k] + "!");
					assignPanel[k].add(new JLabel(UserSetup.names[k])); 
					assignPanel[k].add(assignEmp[k]);
					frame.add(assignPanel[k]);
					assignEmp[k].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e2)
						{
							frame.getContentPane().removeAll();
							frame.dispose();
							frame.revalidate();
							isFixed = true;
							setBackground(Color.green);
							
							for(int k = 0; k < UserSetup.empNum; k++) {
							if(e2.getSource()==assignEmp[k]) {
								empID= k;
								System.out.println(empID);
								setText("Remove Assignment to " + UserSetup.names[k]);
								
							}
							}
						}
							
						});
					
					
				}

				
				
				
				frame.setVisible(true);
				
			}else {
				isFixed = false;
				setText("assign to Employee");
				setBackground(null);
				
				
			}
				
			}
			
				
			});
		return empID;
	}
	
	
}
