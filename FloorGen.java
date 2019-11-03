/*
Benjamin West
bpw15@my.fsu.edu
Floor generator class
Each "Floor" object uses a 2d integer array to track the generated floor. 
Each non-zero element in the array represents a room:
'1' is the "starting" room, containing upstairs
'2' is a room containing downstairs
'3' is a room that doesn't contain any stairs 
The non-zero (room) elements spacial relation to each other represents the floor-plan, and shows which rooms border each other
Examples
1. This is the floor_array before a floor has been generated
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
2. This is the floor_array after a random floor has been generated 
0 0 0 0 0 0
0 0 0 1 3 3
0 0 0 3 3 2
0 0 0 0 3 0
0 0 0 0 0 0
0 0 0 0 0 0
The starting room is at coordinates 1,3
A room with downstairs is at coordinates 2,5
The starting room borders two rooms, there will be doors on the east and south walls
The two rooms it borders are at 1,4 and 2,3
*/

import java.security.SecureRandom;


public class FloorGen{

	//declare the randomizer object 
	SecureRandom random_gen = new SecureRandom();
	
	//the floor size (# of rooms x # of rooms)
	private int the_size=6;
	
	//integers that track the postion of the starting room in the floor 
	private int x_start;
	private int y_start;
	
	private int[][] floor_array = new int[the_size][the_size];

	//constructor
	public FloorGen()
	{
	
        for (int i = 0; i < floor_array.length; i++) {
            for (int j = 0; j < floor_array[i].length; j++) {
                floor_array[i][j] = 0;
            }
        }
		
	}
	
	//generates randomly the "start room", then rooms with stairs
	//todo, possibly use the room type "numbers" to represent room layouts
	public void Generate()
	{
		for (int i = 0; i < floor_array.length; i++) {
            for (int j = 0; j < floor_array[i].length; j++) {
            	floor_array[i][j]=0;
            }
        }
		
		int x_down,y_down;
		
		//generate start room
		x_start=random_gen.nextInt(the_size);
		y_start=random_gen.nextInt(the_size);
		
		
		//print the coordinates of the starting room, for testing
		//System.out.println("start coordinates: "+x_start+", "+y_start); 
		
		//set the array to store the postion of the starting room 
		floor_array[x_start][y_start]=1;
		
		//loop that will run until a downstair-containing room is generated that is different from the starting room
		while(true)
		{
			
			x_down=random_gen.nextInt(the_size);
			y_down=random_gen.nextInt(the_size);
			
			if(x_down==x_start && y_down==y_start)
			{
			
			}
			else
			{
				break;
			}
		}
		
		//print the coordinates of the generated downstair roomm, for testing 
		//System.out.println("downstair coordinates: "+x_down+", "+y_down); 
		
		//set the array to store the postion of the downstair room 
		floor_array[x_down][y_down]=2;
	
	
		//fill the space in between with rooms 
		if(x_down==x_start)
		{
			if(y_down-1==y_start || y_start-1==y_down)
			{
				
			}
			else if(y_down>y_start)
			{
				for(int i=y_start+1; i<y_down; i++)
				{
					floor_array[x_down][i]=3;
				}
			}
			else
			{
				for(int i=y_down+1; i<y_start; i++)
				{
					floor_array[x_down][i]=3;
				}
			}
		}
		else if(y_down==y_start)
		{

			if(x_down-1==x_start || x_start-1==x_down)
			{
			
			}
			else if(x_down>x_start)
			{
				for(int i=x_start+1; i<x_down; i++)
				{
					floor_array[i][y_down]=3;
				}
			}
			else
			{
				for(int i=x_down+1; i<x_start; i++)
				{
					floor_array[i][y_down]=3;
				}
			}

		}
		else if(x_down>x_start)
		{
			for(int i=x_start+1; i<x_down; i++)
			{
				floor_array[i][y_down]=3;
			}
			
			
			if(y_down>y_start)
			{
				for(int i=y_start+1; i!=y_down+1; i++)
				{
					
					floor_array[x_start][i]=3;
				
				}
			
			
			}
			else
			{
				for(int i=y_down; i!=y_start; i++)
				{
					floor_array[x_start][i]=3;
					
				}
				
			}
			

		}
		else //x_start>x_down
		{
			for(int i=x_down+1; i<x_start; i++)
			{
				floor_array[i][y_down]=3;
			}
			
			if(y_down>y_start)
			{
				for(int i=y_start+1; i!=y_down+1; i++)
				{
					
					floor_array[x_start][i]=3;
				
				}
			
			
			}
			else
			{
				for(int i=y_down; i!=y_start; i++)
				{
					floor_array[x_start][i]=3;
					
				}
				
			}
		
		}
		
		
		//pass to add rooms to the floor
		
		int picker; //integer that will hold randomly generated number 
		
		//for each element in the array (each room)
		for (int i = 1; i < floor_array.length-1; i++) {
            for (int j = 1; j < floor_array[i].length-1; j++) {
                
				
				if(floor_array[i][j]==0)
				{
					picker=random_gen.nextInt(10);
				
				
				
					if(picker<5)
					{
						
						if(floor_array[i-1][j]>0)
						{
							floor_array[i][j]=3;
						}
						if(floor_array[i][j-1]>0)
						{
							floor_array[i][j]=3;
						}
						if(floor_array[i+1][j]>0)
						{
							floor_array[i][j]=3;
						}
						if(floor_array[i][j+1]>0)
						{
							floor_array[i][j]=3;
						}
						
					}
				}	
            }
			
		    
        }
		
		
		
		
	}

	//method that prints a visual representation of the floor, for testing purposes
	public void draw()
	{
		for (int i = 0; i < floor_array.length; i++) {
            for (int j = 0; j < floor_array[i].length; j++) {
                
				if(floor_array[i][j]==0)
				{
					System.out.printf(". "); // '.' for empty space 
				}
				else
				{
					System.out.printf("%d ",floor_array[i][j]);
				}
            }
			
		    System.out.printf("\n");
        }
		
	}
	
	
	//accessor methods
	
	//methods that return the starting room coordinates
	public int get_starting_x()
	{
		return x_start;
	}
	
	public int get_starting_y()
	{
		return y_start;
	}
	
	
	
	//methods that return true if stairs are in the current room (possibly be an int if checking for down vs up stairs)
	
	//this function returns the starting room so it is commented out
	/*
	//This may change, if we have set layouts for rooms
	public boolean check_up_stair(int xpos, int ypos)
	{
		if(floor_array[xpos][ypos]==1)
		{
			return true;	
		}
		else
		{
			return false;
		}
			
	}
	*/
	

	public boolean check_down_stair(int xpos, int ypos)
	{
		if(floor_array[xpos][ypos]==2)
		{
			return true;	
		}
		else
		{
			return false;
		}
	
	}
	

	//method to check if there is a room to the north of the current room
	public boolean check_door_north(int xpos, int ypos)
	{
		if(ypos==5)
		{
			return false; //if the room is in the top row of room of the floorplan it cannot have a room to the north of it
		}
		else if(floor_array[xpos][ypos+1]>0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	//method to check if there is a room to the south of the current room
	public boolean check_door_south(int xpos, int ypos)
	{
		if(ypos==0)
		{
			return false; //if the room is in the bottom row of room of the floorplan it cannot have a room to the south of it
		}
		else if(floor_array[xpos][ypos-1]>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//method to check if there is a room to the east of the current room
	public boolean check_door_east(int xpos, int ypos)
	{
		if(xpos==5)
		{
			return false; //if the room is in the right-most column of room of the floorplan it cannot have a room to the east of it
		}
		else if(floor_array[xpos+1][ypos]>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//method to check if there is a room to the west of the current room
	public boolean check_door_west(int xpos, int ypos)
	{
		if(xpos==0)
		{
			return false; //if the room is in the left-most column of room of the floorplan it cannot have a room to the west of it
		}
		else if(floor_array[xpos-1][ypos]>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
