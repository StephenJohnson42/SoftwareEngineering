
//test class to test the floor generator
public class roomtest {
    
public static void main(String[] args) {

	FloorGen test_floor = new FloorGen(); 
	test_floor.Generate();
	//The draw function is just for testing purposes
	test_floor.draw();
	
	System.out.println("*****");
	System.out.println("The starting room is: "+test_floor.get_starting_x()+","+test_floor.get_starting_y());
	System.out.println("Room 2,2 contains downstairs: "+test_floor.check_down_stair(2,2));
	System.out.println("Room 2,2 has a room to the north: "+test_floor.check_door_north(2,2));
	System.out.println("Room 2,2 has a room to the south: "+test_floor.check_door_south(2,2));
	System.out.println("Room 2,2 has a room to the east: "+test_floor.check_door_east(2,2));
	System.out.println("Room 2,2 has a room to the west: "+test_floor.check_door_west(2,2));
	System.out.println("*****");
}

}