import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Integer;
import java.lang.String;
/*
Things still needed to be done:
*Put images for characters in the photo directory
*Figure out if the paths actually make an image
*Decide on the stats like health, and damage each character starts out with
*			-These will be set in the constructors probably
*/
public class Characters{
  private int charNum;
  private int maxTypes;
  private int health;
  private int damage;
  private String[] charImg = {"character.png", "/CharPhotos/Char1.png", //Array for holding the photo paths
  "/CharPhotos/Char2.png", "/CharPhotos/Char3.png", "/CharPhotos/Char4.png"};

  public Characters(){
      maxTypes = 5;
      Random rand = new Random(); 				//Create random number generator
      charNum = rand.nextInt(maxTypes); 		//Returns a random int between 0 and 5
                                  				//Random number generated will choose a random character
  }

  public Characters(int determined){
      maxTypes = 5;
      charNum  = determined; 	  				
                                  	
  }

  private int getcharNum(){           			//Used to return the initial random number made by the constructor
      return charNum;
  }
  private int getMax(){           				//Used to return the max types made by the constructor
      return maxTypes;	
  }
  public String getImage(){       				//Returns the image of the item for it's use in game
      return charImg[getcharNum()];
  }
  public int getDamage(){             			//These are all going to be accessors for the different types of Characters that might be made
  		return damage;		               		//Return whatever health bonus that item gives
  }
  public int getHealth(){
      	return health;
  }

}