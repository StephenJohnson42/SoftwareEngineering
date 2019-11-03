import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Integer;
import java.lang.String;

public class Items{
  private int itemNum;
  private String[] itemImg = {"item0.png", "item1.png", "item2.png", "item3.png", "item4.png", "item5.png", "item6.png", 
                              "item7.png", "item8.png", "item9.png"};
  public Items(){
      Random rand = new Random(); //Create random number generator
      itemNum = rand.nextInt(10); //Returns a random int between 0 and 9
                                  //Random numbers will be however many items we want
  }
  private int getNum(){
      return itemNum;
  }
  public String getImage(){
      return itemImg[getNum()];
  }
  
}
