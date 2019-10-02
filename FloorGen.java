/*
Benjamin West
bpw15@my.fsu.edu

proof of concept "Floor generator" 
*/

import java.security.SecureRandom;


public class FloorGen{

	//declare the randomizer object 
	SecureRandom random_gen = new SecureRandom();
	
	int[][] floor_array = new int[8][8];

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
	public void Generate()
	{
		int x_start,y_start;
		int x_down,y_down;
		
		//generate start room
		x_start=random_gen.nextInt(8);
		y_start=random_gen.nextInt(8);
		
		//print the coordinates of the starting room, for testing
		System.out.println("start coordinates: "+x_start+", "+y_start); 
		
		//set the array to store the postion of the starting room 
		floor_array[x_start][y_start]=1;
		
		//loop that will run until a downstair-containing room is generated that is different from the starting room
		while(true)
		{
			x_down=random_gen.nextInt(8);
			y_down=random_gen.nextInt(8);
			
			if(x_down==x_start && y_down==y_start)
			{
			
			}
			else
			{
				break;
			}
		}
		
		//print the coordinates of the generated downstair roomm, for testing 
		System.out.println("downstair coordinates: "+x_down+", "+y_down); 
		
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
		
		//add a change in direction 
		//possibly do a couple passes to add random rooms to the map
		//is_adjacent as a possible method?
		
		
		
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
	
	

}