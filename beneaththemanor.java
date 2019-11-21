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
	public Icon character = new ImageIcon(getClass().getResource("character.png"));
	public Icon startpanel = new ImageIcon(getClass().getResource("start.png"));
	public Icon stairs = new ImageIcon(getClass().getResource("stairs.png"));
	public Icon wall = new ImageIcon(getClass().getResource("wall.png"));
	public Icon coins = new ImageIcon(getClass().getResource("coins.png"));
	public Icon enemy = new ImageIcon(getClass().getResource("enemy.png"));
	public Icon potion = new ImageIcon(getClass().getResource("potion.png"));
	
	public Items[] charItems=new Items[10];			//Array for holding the items
	public int itemCounter;							//How many items are being held
	public int health;
	public int damage;
	
	
	public JButton[] track=new JButton[100];
	public int[] nums=new int[100];
	public int indoor;
	public int[] outdoor=new int[4]; //NSEW
    public startGame start=new startGame();
	public GameHud Hud=new GameHud();
    public KeyListener mover;
    public int trackmover;
    public ActionListener initialclick;
    public int gold;
	public int level;
    public int lives;
    public int coinspot;
    public Random rand;
	
    FloorGen floor;
    int x, y;
    
    public beneaththemanor()
    {
        super("Beneath the Manor");
		setLayout(new BorderLayout());
        add(start, BorderLayout.CENTER);
		add(Hud, BorderLayout.SOUTH);
    }
    
    class startGame extends JPanel
    {
        //sets up the board
        public startGame(){
        	//Set up Character
        	health = 100; 
        	damage = 50;
        	//End of Character setup
        	GridLayout grid=new GridLayout(10,10,0,0);
        	grid.setHgap(0); 
            grid.setVgap(0);
            setLayout(grid);
           
         
            for (int i=0; i<100; i++) {
                track[i]=new JButton();
                track[i].setIcon(stones);
				track[i].setBorder(null); //removes the grid
				track[i].setFocusable(false);
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
            
            floor = new FloorGen();
            floor.Generate();
            floor.draw();
			
            outdoor[0]=0;
            outdoor[1]=0;
            outdoor[2]=0;
            outdoor[3]=0;
            
            trackmover=45;
            
            gold=0;
            lives=3;
            level=1;
            itemCounter=0;
            x= floor.get_starting_x();
            y= floor.get_starting_y();
            
            if (floor.check_door_north(x,y)) {
            	outdoor[0]=1;
                nums[5]=1;
                track[5].setIcon(blackspace);
            }
            if (floor.check_door_east(x,y)) {
            	outdoor[2]=1;
                nums[59]=1;
                track[59].setIcon(blackspace);
            }
            if (floor.check_door_west(x,y)) {
            	outdoor[3]=1;
                nums[50]=1;
                track[50].setIcon(blackspace);
            }
            if (floor.check_door_south(x,y)) {
            	outdoor[1]=1;
                nums[95]=1;
                track[95].setIcon(blackspace);
            }
		
            coinspot=0;
            rand = new Random();
            while (nums[coinspot]==0)
            	coinspot=rand.nextInt(99)+1;
            
            track[coinspot].setIcon(coins);
            nums[coinspot]=2;
            
			track[trackmover].setFocusable(true);
			track[trackmover].setIcon(character);
			
            mover =  new KeyListener(){
            	@Override
                public void keyPressed(KeyEvent e)
                {	
            		int key = e.getKeyCode();
            	
            		int x;
            		
            	    if (key == KeyEvent.VK_LEFT) {
            	    	x=nums[trackmover-1];
            	    	if ((trackmover-1)==50 && outdoor[3]==1){
            	    		newRoom(50);
            	    	}
            	    	else if (x==1) {
            	    	track[trackmover].setIcon(blackspace);
            	        trackmover--;
            	        track[trackmover].setIcon(character);
            	    	}
            	    	else if (x==2) {
            	    		gold=gold+100;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);
                	        trackmover--;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    }

            	    if (key == KeyEvent.VK_RIGHT) {
            	    	x=nums[trackmover+1];
            	    	if ((trackmover+1)==59 && outdoor[2]==1){
            	    		newRoom(59);
            	    	}
            	    	else if (x==1) {
            	    	track[trackmover].setIcon(blackspace);
            	        trackmover++;
            	        track[trackmover].setIcon(character);
            	    	}
            	    	else if (x==2) {
            	    		gold=gold+100;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);
                	        trackmover++;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    }

            	    if (key == KeyEvent.VK_UP) {
            	    	x=nums[trackmover-10];
            	    	if ((trackmover-10)==5 && outdoor[0]==1){
            	    		newRoom(5);
            	    	}
            	    	else if (nums[1]==1 && (trackmover-10)==1) {
            	    		newFloor();
            	    	}
            	    	else if (x==1) {
            	    	track[trackmover].setIcon(blackspace);
            	        trackmover=trackmover-10;
            	        track[trackmover].setIcon(character);
            	    	}
            	    	else if (x==2) {
            	    		gold=gold+100;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);
                	        trackmover=trackmover-10;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    }

            	    if (key == KeyEvent.VK_DOWN) {
            	    	x=nums[trackmover+10];
            	    	if ((trackmover+10)==95 && outdoor[1]==1){
            	    		newRoom(95);
            	    	}
            	    	else if (x==1) {
            	    	track[trackmover].setIcon(blackspace);
            	    	trackmover=trackmover+10;
            	    	track[trackmover].setIcon(character);
            	    	}
            	    	else if (x==2) {
            	    		gold=gold+100;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);
                	    	trackmover=trackmover+10;
                	    	track[trackmover].setIcon(character);
                	    	nums[trackmover]=1;
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
        
        public void newRoom(int previousRoom){
        	
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
            
            
         
            if (previousRoom==5) {
            	y++;
        	   indoor=95;
        	   nums[indoor]=1;
               trackmover=85;
               
            }
            else if (previousRoom==95) {
            	y--;
            	indoor=5;
            	nums[indoor]=1;
            	trackmover=15;
            }
            else if (previousRoom==50) {
            	x--;
            	indoor=59;
            	nums[indoor]=1;
            	trackmover=58;
            }
            else {
            	x++;
            	indoor=50;
            	nums[indoor]=1;
            	trackmover=51;
            }
            
            
            
            outdoor[0]=0;
            outdoor[1]=0;
            outdoor[2]=0;
            outdoor[3]=0;
            
            if (floor.check_door_north(x,y)) {
            	outdoor[0]=1;
                nums[5]=1;
                track[5].setIcon(blackspace);
            }
            if (floor.check_door_east(x,y)) {
            	outdoor[2]=1;
                nums[59]=1;
                track[59].setIcon(blackspace);
            }
            if (floor.check_door_west(x,y)) {
            	outdoor[3]=1;
                nums[50]=1;
                track[50].setIcon(blackspace);
            }
            if (floor.check_door_south(x,y)) {
            	outdoor[1]=1;
                nums[95]=1;
                track[95].setIcon(blackspace);
            }
            
            if (floor.check_down_stair(x, y)) {
            	nums[1]=1;
            	track[1].setIcon(stairs);
            }
            
            coinspot=0;
            
            while (nums[coinspot]==0)
            	coinspot=rand.nextInt(99)+1;
            
            track[coinspot].setIcon(coins);
            nums[coinspot]=2;
            
            track[trackmover].setIcon(character);
            
        }
        
        public void newFloor(){
        	
			
        	floor.Generate();
        	floor.draw();
        	level = level+1;
			Hud.UpdateStats();
			
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
            
            outdoor[0]=0;
            outdoor[1]=0;
            outdoor[2]=0;
            outdoor[3]=0;
            
            trackmover=81;
            
            x= floor.get_starting_x();
            y= floor.get_starting_y();
            
            if (floor.check_door_north(x,y)) {
            	outdoor[0]=1;
                nums[5]=1;
                track[5].setIcon(blackspace);
            }
            if (floor.check_door_east(x,y)) {
            	outdoor[2]=1;
                nums[59]=1;
                track[59].setIcon(blackspace);
            }
            if (floor.check_door_west(x,y)) {
            	outdoor[3]=1;
                nums[50]=1;
                track[50].setIcon(blackspace);
            }
            if (floor.check_door_south(x,y)) {
            	outdoor[1]=1;
                nums[95]=1;
                track[95].setIcon(blackspace);
            }
            
            track[91].setIcon(wall);
            track[trackmover].setIcon(character);
            
        }
        
    }
    
	class GameHud extends JPanel
	{
		public JLabel goldDisplay = new JLabel("Gold: " + gold, JLabel.CENTER);
		public JLabel levelDisplay = new JLabel("Floor: " + level, JLabel.CENTER);
        public JLabel livesDisplay = new JLabel("Lives: " + lives, JLabel.CENTER);
        public JLabel itemsDisplay = new JLabel("Items: " + itemCounter, JLabel.CENTER);
        public JLabel healthDisplay = new JLabel("Health: " + health, JLabel.CENTER);
        public JLabel damageDisplay = new JLabel("Damage: " + damage, JLabel.CENTER);
        
		public GameHud(){
		setPreferredSize(new Dimension(640, 200));
		setBackground(Color.BLACK);
		GridLayout grid=new GridLayout(1,6,0,0);
    	grid.setHgap(0); 
        grid.setVgap(0);
        setLayout(grid);
        goldDisplay.setForeground(Color.WHITE);
        goldDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(goldDisplay);
        livesDisplay.setForeground(Color.WHITE);
        livesDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(livesDisplay);
		levelDisplay.setForeground(Color.WHITE);
        levelDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(levelDisplay);
        itemsDisplay.setForeground(Color.WHITE);
        itemsDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(itemsDisplay);
        healthDisplay.setForeground(Color.WHITE);
        healthDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(healthDisplay);
        damageDisplay.setForeground(Color.WHITE);
        damageDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(damageDisplay);
        
        
		}
		
		public void UpdateStats() {
			goldDisplay.setText("Gold: " + gold);
			livesDisplay.setText("Lives: " + lives);
			levelDisplay.setText("Floor: " + level);
			healthDisplay.setText("Health: " + health);
			damageDisplay.setText("Damage: " + damage);
		}
		
	}
}


