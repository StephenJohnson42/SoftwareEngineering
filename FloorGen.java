/*
Benjamin West
bpw15@my.fsu.edu

proof of concept "Floor generator" 
*/

import java.security.SecureRandom;


public class FloorGen{

	//declare the randomizer object 
	SecureRandom random_gen = new SecureRandom();
	
	int the_size=6;
	
	int x_start;
	int y_start;
	
	int[][] floor_array = new int[the_size][the_size];

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

	//method that prints a visual representation of the floor 
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
	
	
	//Methods only needed if array is private
	
	/*
	
	//methods that return true if stairs are in the current room (possibly be an int if checking for down vs up stairs)
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
	

	
	//These methods aren't done
	
	//method to check if there is a room to the north of the current room
	public boolean check_door_north(int xpos, int ypos)
	{
		
	}
	
	//method to check if there is a room to the south of the current room
	public boolean check_door_south(int xpos, int ypos)
	{
		
	}
	
	//method to check if there is a room to the east of the current room
	public boolean check_door_east(int xpos, int ypos)
	{
		
	}
	
	//method to check if there is a room to the west of the current room
	public boolean check_door_west(int xpos, int ypos)
	{
		
	}
	
	*/
	
}