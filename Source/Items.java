//Made by Stephen Johnson
import java.util.Random; 
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Integer;
import java.lang.String;
/*
Things still needed to be done:
*Get images for the items
*Decide on stats for the items
*Finalize class structure for the items
*Figure out how we will update the stats during runtime
*/
public class Items{
  private int itemNum;
  private int maxTypes;
  private String[] itemImg = {"sword.png", "/ItemPhotos/item1.png", 
  "/ItemPhotos/item2.png", "/ItemPhotos/item3.png", "/ItemPhotos/item4.png", 
  "/ItemPhotos/item5.png", "/ItemPhotos/item6.png", "/ItemPhotos/item7.png", 
  "/ItemPhotos/item8.png", "/ItemPhotos/item9.png"};
  
  public Items(){
      maxTypes = 1;
      Random rand = new Random(); //Create random number generator
      itemNum = rand.nextInt(maxTypes); //Returns a random int between 0 and 9
                                  //Random numbers will be however many items we want
  }
  public Items(int determined){
      maxTypes = 1;
      itemNum = determined; //Returns a random int between 0 and 9
                                  //Random numbers will be however many items we want
  }
  private int getNum(){           //Used to return the initial random number made by the constructor
      return itemNum;
  }
   private int getMax(){           //Used to return the max types made by the constructor
      return maxTypes;
  }
  public String getImage(){       //Returns the image of the item for it's use in game
      return itemImg[getNum()];
  }
  public int getHealthMod(){             //These are all going to be accessors for the different types of items that might be made
      int match = getNum();                //Return whatever health bonus that item gives
      if(match == 0)
          return 10;
      if(match == 1)
          return 10;
      if(match == 2)
          return 10;
      return 0;
  }
  public int getDamageMod(){
      int match = getNum();                //Return whatever damage bonus that item gives
      if(match == 0)
          return 10;
      if(match == 1)
          return 10;
      return 0;
  }
  public int stat3(){
      int match = getNum();                //Return whatever health bonus that item gives
      if(match == 0)
          return 10;
      if(match == 1)
          return 10;
      return 0;
  }
  public int stat4(){
      int match = getNum();                //Return whatever health bonus that item gives
      if(match == 0)
          return 10;
      if(match == 1)
          return 10;
      return 0;
  }
  
}
