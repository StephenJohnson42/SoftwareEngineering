import java.security.SecureRandom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Integer;
import java.lang.String;

public class beneaththemanor extends JFrame
{
	public Icon stones = new ImageIcon("stones.png");
	public Icon blackspace = new ImageIcon("black.png");
	public Icon character = new ImageIcon("character.png");
	public JButton[] track=new JButton[100];
    public startGame start=new startGame();
    public KeyListener mover;
    public int trackmover;
    
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
                add(track[i]);
            }
            int temp=0;
            for (int i=11; i<90; i++) {
            	if (temp!=8) {
            		track[i].setIcon(blackspace);
            		temp++;
            	}
            	else {
            		temp=0;
            		i++;
            	}
                
            }
            
            trackmover=50;
            track[trackmover].setIcon(character);
            
            mover =  new KeyListener(){
            	@Override
                public void keyPressed(KeyEvent e)
                {	
            		int key = e.getKeyCode();

            	    if (key == KeyEvent.VK_LEFT) {
            	    	track[trackmover].setIcon(blackspace);
            	        trackmover--;
            	        track[trackmover].setIcon(character);
            	    }

            	    if (key == KeyEvent.VK_RIGHT) {
            	    	track[trackmover].setIcon(blackspace);
            	        trackmover++;
            	        track[trackmover].setIcon(character);
            	    }

            	    if (key == KeyEvent.VK_UP) {
            	    	track[trackmover].setIcon(blackspace);
            	        trackmover=trackmover-10;
            	        track[trackmover].setIcon(character);
            	    }

            	    if (key == KeyEvent.VK_DOWN) {
            	    	track[trackmover].setIcon(blackspace);
            	    	trackmover=trackmover+10;
            	    	track[trackmover].setIcon(character);
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
        }
    }
   
