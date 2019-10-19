import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Integer;
import java.lang.String;

public class beneaththemanor extends JFrame
{
	public Icon stones = new ImageIcon(getClass().getResource("stones.png"));
	public Icon blackspace = new ImageIcon(getClass().getResource("black.png"));
	public Icon arrowR = new ImageIcon(getClass().getResource("arrowR.png"));
	public Icon arrowU = new ImageIcon(getClass().getResource("arrowU.png"));
	public Icon character = new ImageIcon(getClass().getResource("character.png"));
	public Icon startpanel = new ImageIcon(getClass().getResource("start.png"));
	public JButton[] track=new JButton[100];
	public int[] nums=new int[100];
	public int indoor;
	public int outdoor;
    public startGame start=new startGame();
    public KeyListener mover;
    public int trackmover;
    public ActionListener initialclick;
    int roomcounter;
    Random rand = new Random();
    
    public beneaththemanor()
    {
        super("Beneath the Manor");
        add(start);
    }
    
    class startGame extends JPanel
    {
        //sets up the board
        public startGame(){
            setLayout(new GridLayout(10,10,0,0));
            
           
            
            for (int i=0; i<100; i++) {
                track[i]=new JButton();
                track[i].setIcon(stones);
                nums[i]=0;
                add(track[i]);
            }
            int temp=0;
            for (int i=11; i<90; i++) {
            	if (temp!=8) {
            		track[i].setIcon(blackspace);
            		temp++;
            		nums[i]=1;
            	}
            	else {
            		temp=0;
            		i++;
            	}
                
            }
            
            roomcounter=1;
            trackmover=50;
            indoor=trackmover;
            outdoor=29;
            nums[outdoor]=1;
            track[outdoor].setIcon(arrowR);
            
            track[trackmover].setIcon(startpanel);
            initialclick =  new ActionListener(){
                public void actionPerformed(ActionEvent event)
                {	
                	track[trackmover].setIcon(character);
                }
            };
            track[trackmover].addActionListener(initialclick); 
            
            mover =  new KeyListener(){
            	@Override
                public void keyPressed(KeyEvent e)
                {	
            		int key = e.getKeyCode();
            	
            		track[trackmover].removeActionListener(initialclick);
            		
            		int x;
            		
            	    if (key == KeyEvent.VK_LEFT) {
            	    	x=nums[trackmover-1];
            	    	if ((trackmover-1)==outdoor){
            	    		newRoom();
            	    	}
            	    	else if (x==1) {
            	    	track[trackmover].setIcon(blackspace);
            	        trackmover--;
            	        track[trackmover].setIcon(character);
            	    	}
            	    }

            	    if (key == KeyEvent.VK_RIGHT) {
            	    	x=nums[trackmover+1];
            	    	if ((trackmover+1)==outdoor){
            	    		newRoom();
            	    	}
            	    	else if (x==1) {
            	    	track[trackmover].setIcon(blackspace);
            	        trackmover++;
            	        track[trackmover].setIcon(character);
            	    	}
            	    }

            	    if (key == KeyEvent.VK_UP) {
            	    	x=nums[trackmover-10];
            	    	if ((trackmover-10)==outdoor){
            	    		newRoom();
            	    	}
            	    	else if (x==1) {
            	    	track[trackmover].setIcon(blackspace);
            	        trackmover=trackmover-10;
            	        track[trackmover].setIcon(character);
            	    	}
            	    }

            	    if (key == KeyEvent.VK_DOWN) {
            	    	x=nums[trackmover+10];
            	    	if ((trackmover+10)==outdoor){
            	    		newRoom();
            	    	}
            	    	else if (x==1) {
            	    	track[trackmover].setIcon(blackspace);
            	    	trackmover=trackmover+10;
            	    	track[trackmover].setIcon(character);
            	    	}
            	    }
                }

				@Override
				public void keyTyped(KeyEvent e) {
				
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
	
					
				}
            };
            track[trackmover].addKeyListener(mover); 
 
            }
        
        public void newRoom(){
        	for (int i=0; i<100; i++) {
                track[i].setIcon(stones);
                nums[i]=0;
            }
            int temp=0;
            for (int i=11; i<90; i++) {
            	if (temp!=8) {
            		track[i].setIcon(blackspace);
            		temp++;
            		nums[i]=1;
            	}
            	else {
            		temp=0;
            		i++;
            	}
            }
            
           if (roomcounter==0) {
        	   indoor=outdoor+90;
               trackmover=indoor;
            	outdoor=((rand.nextInt(8)+1)*10+9); 
            	roomcounter=1;
            	track[outdoor].setIcon(arrowR);
            	
           }
            
            else if (roomcounter==1) {
            	indoor=outdoor-9;
            	trackmover=indoor;
            	
            	outdoor=((rand.nextInt(8)+1)); 
            	roomcounter=0;
            	track[outdoor].setIcon(arrowU);
            	
            }
           track[indoor].setIcon(character);
           
            nums[outdoor]=1;
            
            
        }
        
        }
    }
