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
            setLayout(new GridLayout(50,50,0,0));
        }
    }
    
}
