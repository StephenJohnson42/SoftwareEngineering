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
	public Icon door = new ImageIcon(getClass().getResource("door.png"));
	public Icon wall = new ImageIcon(getClass().getResource("wall.png"));
	public Icon coins = new ImageIcon(getClass().getResource("coins.png"));
	public Icon enemy = new ImageIcon(getClass().getResource("enemy.png"));
	public Icon potion = new ImageIcon(getClass().getResource("potion.png"));
	public Icon trap = new ImageIcon(getClass().getResource("pit_trap.png"));
	public Icon sword = new ImageIcon(getClass().getResource("sword.png"));
	public Icon trap_withmc = new ImageIcon(getClass().getResource("pit_trap_with_mc.png"));
	public Icon rubble = new ImageIcon(getClass().getResource("rubble.png"));
	public Icon fire = new ImageIcon(getClass().getResource("fire.png"));
	public boolean swordAcquired;
	public boolean enemyFighting;
	
	
	public Items[] charItems=new Items[5];			//Array for holding the items
	public int itemCounter;
	public int potionCount;							//How many items are being held
	public int potionspot; 							//Spot for potions
	public int trapspot;							//Spot for traps
	public int rubblespot;							//Spot for rubble
	public int itemspot;							//Spot for item
	
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
        	itemCounter = 0;
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
            
            swordAcquired=false;
            enemyFighting=false;
            gold=0;
            lives=3;
            level=1;
            potionCount=0;
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
            while (nums[coinspot]==0 && coinspot!=5 && coinspot!=59 && coinspot!=50 && coinspot!=95 && coinspot!=1)
            	coinspot=rand.nextInt(99)+1;
            if(coinspot == 5 || coinspot == 59 || coinspot == 50 || coinspot == 95 || coinspot == 11 || coinspot == 1) {        
            }
            else {
	            track[coinspot].setIcon(coins);
	            nums[coinspot]=2;
            }
            
            //Potion spot testing
            int potionchance = rand.nextInt(4);
            if(potionchance == 0) {
	            potionspot=0;
	            while (nums[potionspot]==0 && potionspot!=5 && potionspot!=59 && potionspot!=50 && potionspot!=95 && potionspot!=1)
	            	potionspot=rand.nextInt(99)+1;
	            if(potionspot == 5 || potionspot == 59 || potionspot == 50 || potionspot == 95 || potionspot == 11 || potionspot == 1) {     
	            }
	            else {
		            track[potionspot].setIcon(potion);
		            nums[potionspot]=3;
	            }
            }
            //End of potion spot testing
          //Item spot testing
            if (swordAcquired==false) {
	            int itemchance = rand.nextInt(4);
	            if(itemchance == 0) {
		            itemspot=0;
		            while (nums[itemspot]==0 && itemspot!=5 && itemspot!=59 && itemspot!=50 && itemspot!=95 && itemspot!=1)
		            	itemspot=rand.nextInt(99)+1;
		            if(itemspot == 5 || itemspot == 59 || itemspot == 50 || itemspot == 95 || itemspot == 11 || itemspot == 1) {     
		            }
		            else {
			            Items Item =new Items();
			            Icon item = new ImageIcon(getClass().getResource(Item.getImage()));
			            track[itemspot].setIcon(item);
			            nums[itemspot]=6;
		            }
	            }
            }
            //End of Item spot testing
            
            //Trap  spot testing
            int trapchance = rand.nextInt(1);
            if(trapchance == 0) {
	            trapspot=0;
	            while (nums[trapspot]==0 && trapspot!=5 && trapspot!=59 && trapspot!=50 && trapspot!=95 && trapspot!=1)
	            	trapspot=rand.nextInt(99)+1;
	            if(trapspot == 5 || trapspot == 59 || trapspot == 50 || trapspot == 95 || trapspot == 11 || trapspot == 1) {            
	            }
	            else
	            	nums[trapspot]=4;
            }
            //End of Trap spot testing
			
          //rubble  spot testing
            for(int i = 0; i < 7; i++) {
	            int rubblechance = rand.nextInt(1);
	            if(rubblechance == 0) {
		            rubblespot=0;
		            while (nums[rubblespot]==0 && rubblespot!=5 && rubblespot!=59 && rubblespot!=50 && rubblespot!=95 && rubblespot!=1)
		            	rubblespot=rand.nextInt(99)+1;
		            if(rubblespot == 85 || rubblespot == 15 || rubblespot == 51 || rubblespot == 58
		            		|| rubblespot == 5 || rubblespot == 59 || rubblespot == 50 || rubblespot == 95 || rubblespot == 11 || rubblespot == 1) {
			            break;
		            }
		            track[rubblespot].setIcon(rubble);
		            nums[rubblespot]=7;
	            }
            }
            //End of rubble spot testing
            
			track[trackmover].setFocusable(true);
			track[trackmover].setIcon(character);
			
            mover =  new KeyListener(){
            	@Override
                public void keyPressed(KeyEvent e)
                {	
            		int key = e.getKeyCode();            	
            		int x;
					if (key == KeyEvent.VK_P) {
            	    	
							if(potionCount>0)
							{
							health = health + 10;
							Hud.CheckHealth();
							potionCount = potionCount - 1;
							Hud.UpdateStats();
							}
            	    	}
				
            	    if (key == KeyEvent.VK_LEFT) {
            	    	x=nums[trackmover-1];
            	    	if ((trackmover-1)==50 && outdoor[3]==1){
            	    		newRoom(50);
            	    		if (enemyFighting==true) {
            	    			track[41].setIcon(enemy);
            	            	nums[41]=1;}
            	    	}
            	    	else if (x==1) {
            	    		if(nums[trackmover] == 4 )
            	    			track[trackmover].setIcon(trap);
            	    		else
            	    			track[trackmover].setIcon(blackspace);
	            	        trackmover--;
	            	        track[trackmover].setIcon(character);
	            	        
	            	        if (enemyFighting==true) {
	            	        	int firespot=0;
	            	        	
	            	        	for (int i=0; i<level; i++) {
	            	        	 while (nums[firespot]==0 && firespot!=5 && firespot!=59 && firespot!=50 && firespot!=95 && firespot!=1)
	            	        		 firespot=rand.nextInt(99)+1;
	            	             if(firespot == 41 ||firespot == 5 || firespot == 59 || firespot == 50 || firespot == 95 || firespot == 11 || firespot == 1) {
	            	 	            
	            	             }
	            	             else {
		            	 	            track[firespot].setIcon(fire);
		            	 	           nums[firespot]=4;
		            	             }
	            	        	}
	            	             if (firespot==trackmover) {
	            	            	 health=health-10;
	            	            	 nums[firespot]=5;
	            	            	 Hud.UpdateStats();
	            	             }
	            	             
	            	             if (trackmover==51 && swordAcquired)
	            	             {
	            	            	 enemyFighting=false;
	            	             }
	            	        }
            	    	}
            	    	else if (x==2) {
            	    		gold=gold+100;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	        trackmover--;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    	else if(x==3) {
							potionCount = potionCount + 1;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	        trackmover--;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    	else if(x==4) {
            	    		health = health - 10;
            	    		Hud.CheckHealth();
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Don't Reset Tile
                	        trackmover--;
                	        track[trapspot].setIcon(trap_withmc);
                	        nums[trackmover]=4;
            	    	}
            	    	else if(x==5) {
            	    		health = health - 10;
            	    		Hud.CheckHealth();
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Don't Reset Tile
                	    	trackmover--;
                	    	 track[trackmover].setIcon(fire);
                	    	nums[trackmover]=1;
            	    	}
            	    	else if(x==6) {
            	    		Items Item =new Items();
            	    		charItems[itemCounter] = Item;			//Array for holding the items
            	    		itemCounter++;
            	    		Hud.UpdateStats();
            	    		swordAcquired=true;
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	        trackmover--;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    	
            	    }

            	    if (key == KeyEvent.VK_RIGHT) {
            	    	x=nums[trackmover+1];
            	    	if ((trackmover+1)==59 && outdoor[2]==1){
            	    		newRoom(59);
            	    		if (enemyFighting==true) {
            	    			track[41].setIcon(enemy);
            	            	nums[41]=1;}
            	    	}
            	    	else if (x==1) {
            	    		if(nums[trackmover] == 4 )
            	    			track[trackmover].setIcon(trap);
            	    		else
            	    			track[trackmover].setIcon(blackspace);
	            	        trackmover++;
	            	        track[trackmover].setIcon(character);
	            	        
	            	        if (enemyFighting==true) {
	            	        	int firespot=0;
	            	        	
	            	        	for (int i=0; i<level; i++) {
	            	        	 while (nums[firespot]==0 && firespot!=5 && firespot!=59 && firespot!=50 && firespot!=95 && firespot!=1)
	            	        		 firespot=rand.nextInt(99)+1;
	            	             if(firespot == 41 ||firespot == 5 || firespot == 59 || firespot == 50 || firespot == 95 || firespot == 11 || firespot == 1) {
	            	 	            
	            	             }
	            	             else {
	            	 	            track[firespot].setIcon(fire);
	            	 	           nums[firespot]=5;
	            	             }
	            	        	}
	            	            
	            	             if (firespot==trackmover) {
	            	            	 health=health-10;
	            	            	 
	            	            	 Hud.UpdateStats();
	            	             }
	            	             
	            	             if (trackmover==51 && swordAcquired)
	            	             {
	            	            	 enemyFighting=false;
	            	             }
	            	        }
            	    	}
            	    	else if (x==2) {
            	    		gold=gold+100;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	        trackmover++;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    	else if(x==3) {
            	    		//health = health + 50;
							potionCount = potionCount + 1;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	        trackmover++;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    	else if(x==4) {
            	    		health = health - 10;
            	    		Hud.CheckHealth();
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Don't Reset Tile
                	        trackmover++;
                	        track[trapspot].setIcon(trap_withmc);
                	        nums[trackmover]=4;
            	    	}
            	    	else if(x==5) {
            	    		health = health - 10;
            	    		Hud.CheckHealth();
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Don't Reset Tile
                	    	trackmover++;
                	    	 track[trackmover].setIcon(fire);
                	    	nums[trackmover]=1;
            	    	}
            	    	else if(x==6) {
            	    		Items Item =new Items();
            	    		charItems[itemCounter] = Item;			//Array for holding the items
            	    		itemCounter++;
            	    		Hud.UpdateStats();
            	    		swordAcquired=true;
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	        trackmover++;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    }

            	    if (key == KeyEvent.VK_UP) {
            	    	x=nums[trackmover-10];
            	    	if ((trackmover-10)==5 && outdoor[0]==1){
            	    		newRoom(5);
            	    		if (enemyFighting==true) {
            	    			track[41].setIcon(enemy);
            	            	nums[41]=1;}
            	    	}
            	    	else if (nums[1]==1 && (trackmover-10)==1) {
            	    		if (gold>=1000) {
            	    			gold=gold-1000;
            	    			newFloor();
            	    			Hud.UpdateStats();
            	    		}
            	    	}
            	    	else if (x==1) {
            	    		if(nums[trackmover] == 4 )
            	    			track[trackmover].setIcon(trap);
            	    		else
            	    			track[trackmover].setIcon(blackspace);
            	    		trackmover=trackmover-10;;
	            	        track[trackmover].setIcon(character);
	            	        
	            	        if (enemyFighting==true) {
	            	        	
	            	        	
	            	        	int firespot=0;
	            	        	for (int i=0; i<level; i++) {
	            	        	 while (nums[firespot]==0 && firespot!=5 && firespot!=59 && firespot!=50 && firespot!=95 && firespot!=1)
	            	        		 firespot=rand.nextInt(99)+1;
	            	             if(firespot == 41 ||firespot == 5 || firespot == 59 || firespot == 50 || firespot == 95 || firespot == 11 || firespot == 1) {
	            	 	            
	            	             }
	            	             else {
		            	 	            track[firespot].setIcon(fire);
		            	 	           nums[firespot]=5;
		            	             }
	            	        	}
	            	             if (firespot==trackmover) {
	            	            	 health=health-10;
	            	            	 Hud.UpdateStats();
	            	             }
	            	             
	            	             if (trackmover==51 && swordAcquired)
	            	             {
	            	            	 enemyFighting=false;
	            	             }
	            	        }
            	    	}
            	    	else if (x==2) {
            	    		gold=gold+100;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	        trackmover=trackmover-10;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    	else if(x==3) {
							potionCount = potionCount + 1;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	        trackmover=trackmover-10;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    	else if(x==4) {
            	    		health = health - 10;
            	    		Hud.CheckHealth();
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Don't Reset Tile
                	        trackmover=trackmover-10;
                	        track[trapspot].setIcon(trap_withmc);
                	        nums[trackmover]=4;
            	    	}
            	    	else if(x==5) {
            	    		health = health - 10;
            	    		Hud.CheckHealth();
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Don't Reset Tile
                	    	trackmover=trackmover-10;;
                	    	 track[trackmover].setIcon(fire);
                	    	nums[trackmover]=1;
            	    	}
            	    	else if(x==6) {
            	    		Items Item =new Items();
            	    		charItems[itemCounter] = Item;			//Array for holding the items
            	    		itemCounter++;
            	    		Hud.UpdateStats();
            	    		swordAcquired=true;
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	        trackmover=trackmover-10;
                	        track[trackmover].setIcon(character);
                	        nums[trackmover]=1;
            	    	}
            	    	
            	    }

            	    if (key == KeyEvent.VK_DOWN) {
            	    	x=nums[trackmover+10];
            	    	if ((trackmover+10)==95 && outdoor[1]==1){
            	    		newRoom(95);
            	    		if (enemyFighting==true) {
            	    			track[41].setIcon(enemy);
            	            	nums[41]=1;}
            	    	}
            	    	else if (x==1) {
            	    		if(nums[trackmover] == 4 )
            	    			track[trackmover].setIcon(trap);
            	    		else
            	    			track[trackmover].setIcon(blackspace);
            	    		trackmover=trackmover+10;;
	            	        track[trackmover].setIcon(character);
	            	        
	            	        if (enemyFighting==true) {
	            	        	int firespot=0;
	            	        	
	            	        	for (int i=0; i<level; i++) {
	            	        	 while (nums[firespot]==0 && firespot!=5 && firespot!=59 && firespot!=50 && firespot!=95 && firespot!=1)
	            	        		 firespot=rand.nextInt(99)+1;
	            	             if(firespot == 41 ||firespot == 5 || firespot == 59 || firespot == 50 || firespot == 95 || firespot == 11 || firespot == 1) {
	            	 	            
	            	             }
	            	             else {
		            	 	            track[firespot].setIcon(fire);
		            	 	           nums[firespot]=5;
		            	             }
	            	        	}
	            	             if (firespot==trackmover) {
	            	            	 health=health-10;
	            	            	 Hud.UpdateStats();
	            	             }
	            	             
	            	             if (trackmover==51 && swordAcquired)
	            	             {
	            	            	 enemyFighting=false;
	            	             }
	            	        }
            	    	}
            	    	else if (x==2) {
            	    		gold=gold+100;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	    	trackmover=trackmover+10;
                	    	track[trackmover].setIcon(character);
                	    	nums[trackmover]=1;
            	    	}
            	    	else if(x==3) {
            	    		//health = health + 50;
							potionCount = potionCount + 1;
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
                	    	trackmover=trackmover+10;
                	    	track[trackmover].setIcon(character);
                	    	nums[trackmover]=1;
            	    	}
            	    	else if(x==4) {
            	    		health = health - 10;
            	    		Hud.CheckHealth();
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Don't Reset Tile
                	    	trackmover=trackmover+10;
                	    	 track[trapspot].setIcon(trap_withmc);
                	    	nums[trackmover]=4;
            	    	}
            	    	else if(x==5) {
            	    		health = health - 10;
            	    		Hud.CheckHealth();
            	    		Hud.UpdateStats();
            	    		track[trackmover].setIcon(blackspace);	//Don't Reset Tile
                	    	trackmover=trackmover+10;
                	    	 track[trackmover].setIcon(fire);
                	    	nums[trackmover]=1;
            	    	}
            	    	else if(x==6) {
            	    		Items Item =new Items();
            	    		charItems[itemCounter] = Item;			//Array for holding the items
            	    		itemCounter++;
            	    		Hud.UpdateStats();
            	    		swordAcquired=true;
            	    		track[trackmover].setIcon(blackspace);	//Reset the tiles
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
        
            enemyFighting=false;
            
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
				if(gold<1000)
				{
					track[1].setIcon(door);
					enemyFighting=true;
				}
				else
				{
					track[1].setIcon(stairs);
					enemyFighting=true;
				}
            }
            
            coinspot=0;
            while (nums[coinspot]==0 && coinspot!=5 && coinspot!=59 && coinspot!=50 && coinspot!=95 && coinspot!=1)
            	coinspot=rand.nextInt(99)+1;
            if(coinspot == 5 || coinspot == 59 || coinspot == 50 || coinspot == 95 || coinspot == 11 || coinspot == 1) {
	            
            }
            else {
	            track[coinspot].setIcon(coins);
	            nums[coinspot]=2;
            }
            
            
            //Potion spot testing
            int potionchance = rand.nextInt(4);
            if(potionchance == 0) {
	            potionspot=0;
	            while (nums[potionspot]==0 && potionspot!=5 && potionspot!=59 && potionspot!=50 && potionspot!=95 && potionspot!=1)
	            	potionspot=rand.nextInt(99)+1;
	            if(potionspot == 5 || potionspot == 59 || potionspot == 50 || potionspot == 95 || potionspot == 11 || potionspot == 1) {     
	            }
	            else {
		            track[potionspot].setIcon(potion);
		            nums[potionspot]=3;
	            }
            }
            //End of potion spot testing
          //Item spot testing
            if (swordAcquired==false) {
            	int itemchance = rand.nextInt(4);
	            if(itemchance == 0) {
		            itemspot=0;
		            while (nums[itemspot]==0 && itemspot!=5 && itemspot!=59 && itemspot!=50 && itemspot!=95 && itemspot!=1)
		            	itemspot=rand.nextInt(99)+1;
		            if(itemspot == 5 || itemspot == 59 || itemspot == 50 || itemspot == 95 || itemspot == 11 || itemspot == 1) {     
		            }
		            else {
			            Items Item =new Items();
			            Icon item = new ImageIcon(getClass().getResource(Item.getImage()));
			            track[itemspot].setIcon(item);
			            nums[itemspot]=6;
		            }
	            }
            }
            //End of Item spot testing
          //Trap spot testing
            int trapchance = rand.nextInt(1);
            if(trapchance == 0) {
	            trapspot=0;
	            while (nums[trapspot]==0 && trapspot!=5 && trapspot!=59 && trapspot!=50 && trapspot!=95 && trapspot!=1)
	            	trapspot=rand.nextInt(99)+1;
	            if(trapspot == 5 || trapspot == 59 || trapspot == 50 || trapspot == 95 || trapspot == 11 || trapspot == 1) {
		            
	            }
	            else
	            	nums[trapspot]=4;
	            
            }
            //End of Trap spot testing
          //Rubble spot testing
            for(int i = 0; i < 7; i++) {
	            int rubblechance = rand.nextInt(1);
	            if(rubblechance == 0) {
		            rubblespot=0;
		            while (nums[rubblespot]==0 && rubblespot!=5 && rubblespot!=59 && rubblespot!=50 && rubblespot!=95 && rubblespot!=1)
		            	rubblespot=rand.nextInt(99)+1;
		            if(rubblespot == 85 || rubblespot == 15 || rubblespot == 51 || rubblespot == 58
		            		|| rubblespot == 5 || rubblespot == 59 || rubblespot == 50 || rubblespot == 95 || rubblespot == 11 || rubblespot == 1) {
			            break;
		            }
		            track[rubblespot].setIcon(rubble);
		            nums[rubblespot]=7;
		            
	            }
            }
            //End of rubble spot testing
            track[trackmover].setIcon(character);
        }
        
        public void newFloor(){
        	
        	enemyFighting=false;
			swordAcquired=false;
			itemCounter = 0;
        	floor.Generate();
        	floor.draw();
        	level = level+1;
        	
        	//checks if the player won
        	if (level==6)
        		Hud.YouWin();
        	
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
        public JLabel potionDisplay = new JLabel("Potions: " + potionCount, JLabel.CENTER);
        public JLabel healthDisplay = new JLabel("Health: " + health, JLabel.CENTER);
        public JLabel swordDisplay = new JLabel("Swords: " + itemCounter, JLabel.CENTER);
        
		public GameHud(){
		setPreferredSize(new Dimension(640, 200));
		setBackground(Color.BLACK);
		GridLayout grid=new GridLayout(1,5,0,0);
    	grid.setHgap(0); 
        grid.setVgap(0);
        setLayout(grid);
        
		levelDisplay.setForeground(Color.WHITE);
        levelDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(levelDisplay);
		
		healthDisplay.setForeground(Color.WHITE);
        healthDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(healthDisplay);
		
		goldDisplay.setForeground(Color.WHITE);
        goldDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(goldDisplay);
		
        potionDisplay.setForeground(Color.WHITE);
        potionDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(potionDisplay);
        
        swordDisplay.setForeground(Color.WHITE);
        swordDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        add(swordDisplay);
        
        
		}
		
		public void UpdateStats() {
			goldDisplay.setText("Gold: " + gold);
			levelDisplay.setText("Floor: " + level);
			healthDisplay.setText("Health: " + health);
			swordDisplay.setText("Swords: " + itemCounter);
			potionDisplay.setText("Potions: " + potionCount);
		}
		
	
		
		public void CheckHealth() {
			if (health<=0)
			{
				JOptionPane.showMessageDialog(this, "You died!","Credits",JOptionPane.INFORMATION_MESSAGE);  //TODO create skull icon for message
				System.exit(0);
				
			}
			
			if (health>100)
			{
				health=100;
			}
		}
		
		public void YouWin() {
			
			System.exit(0);
		}
		
	}
}


