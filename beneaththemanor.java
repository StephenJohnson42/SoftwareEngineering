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
	public JButton[] track=new JButton[100];
    public startGame start=new startGame();
    
    
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
        }
    }
    
}
