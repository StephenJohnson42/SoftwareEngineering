//Brendan Gressel
//done, prints fram to screen

import java.awt.event.*;  
import java.awt.*;
import javax.swing.*;    

public class SEGame {
    
	
	public static volatile boolean menu_loop=true;
	
    public static void main(String[] args) {
        
		
		
		//create the menu!
		
		//create a jframe for the menu window
		JFrame menu_window=new JFrame("Beneath the Manor"); 
		
		//create a panel to hold the menu buttons 
		JPanel menu_button_holder = new JPanel();
		
		//create an imageicon for the window icon 
		ImageIcon window_icon = new ImageIcon(SEGame.class.getResource("character.png"));
		
		//set the window icon 
		menu_window.setIconImage(window_icon.getImage());
		
		//make a jlabel with the menu title image 
		JLabel imgLabel = new JLabel(new ImageIcon(SEGame.class.getResource("menu_background2.png")));
		
		//create the buttons 
		JButton Start_button=new JButton("Start New Game");  
		//JButton Load_button=new JButton("Load Game");  
		JButton Credits_button=new JButton("Credits");  
		JButton Quit_button=new JButton("Quit");  
		//Start_button.setBorderPainted(false);
		Start_button.setFocusPainted(false);
		//Start_button.setContentAreaFilled(false);
		Start_button.setForeground(Color.GRAY);
		Start_button.setBackground(Color.BLACK);
		Credits_button.setForeground(Color.GRAY);
		Credits_button.setBackground(Color.BLACK);
		Quit_button.setForeground(Color.GRAY);
		Quit_button.setBackground(Color.BLACK);
		
		
		//set the button text fonts 
		Start_button.setFont(new Font("Serif", Font.BOLD, 24));
		//Load_button.setFont(new Font("Serif", Font.BOLD, 24));
		Credits_button.setFont(new Font("Serif", Font.BOLD, 24));
		Quit_button.setFont(new Font("Serif", Font.BOLD, 24));
		
		
		//create the action listeners
		//quit button action listener 
		Quit_button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				//exit the program when the quit button is pressed 
				System.exit(0);
			}  
		});
		//show the credits message action listener 
		Credits_button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				//show credits messages 
				JOptionPane.showMessageDialog(menu_window, "Group 4 Project\nSoftware engineering\nFall 2019","Credits",JOptionPane.INFORMATION_MESSAGE,window_icon);
				JOptionPane.showMessageDialog(menu_window, "Brenden Gressel\nStephen Johnson\nLogan Leone\nBen West","Credits",JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(menu_window, "All graphical tiles were created by David E. Gervais,\n and are published under the Creative Commons licence","Credits",JOptionPane.INFORMATION_MESSAGE,window_icon);
			}  
		});
		//start button action listener
		Start_button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				//stop the menu loop and continue on to start the game 
				menu_loop=false;
				
			}  
		});
		/*//load button action listener 
		Load_button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				
				loading_game=true;
				menu_loop=false;
				
			}  
		});
		*/	
		
		menu_window.setLayout(new BorderLayout()); 
		
		//add the buttons to the button frame 
		menu_button_holder.add(Start_button);
		//menu_button_holder.add(Load_button);
		menu_button_holder.add(Credits_button);
		menu_button_holder.add(Quit_button);
		
		//set the layout of the button panel
		menu_button_holder.setLayout(new GridLayout(1,4));
		
		//add all the components to the menu window	
		menu_window.add(imgLabel);
		menu_window.add(menu_button_holder, BorderLayout.SOUTH);

		
		//set menu_window constraints 
		menu_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu_window.setSize(960,400);
		menu_window.setResizable(false);
		
		//menu_window.getContentPane().setBackground(new Color(39,115,11));
	
	
		//set menu_window visibility 
		menu_window.setVisible(true);
		
		//wait for somem buttons 
		while(menu_loop==true)
			{	
			}
		
		
		beneaththemanor frame = new beneaththemanor();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 740); // set frame size
        frame.setVisible(true); // display frame
		frame.setResizable(false);
        
    }
}
