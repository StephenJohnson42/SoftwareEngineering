import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Integer;
import java.lang.String;

/*
Still need to find images for monsters the different types of monsters.
Need to set difinitive stats for the different monsters.
*/

public class Monsters{
    private int monsterNum;
    private int maxTypes;
    private int health;
    private int attackStat;
    private int defenseStat;
    private String[] moveList;  //Possible option for having more than one attack option
    //private String[] monsterImg = {"monster.png", "/MonsterPhotos/Monster2.png", "/MonsterPhotos/Monster3.png",
    //"/MonsterPhotos/Monster4.png", "/MonsterPhotos/Monster5.png"};


public Monsters(){
    //Chooses a random monster out of the options we have to spawn
    maxTypes = 5;
    Random rand = new Random();
    monsterNum = rand.nextInt(maxTypes);
}

public Monsters(int determined){
    //Chooses a specific number of monster instead of a random moster
    maxTypes = 5;

    //Error checking: if determined is higher than maxTypes, just use a random monster.
    if(determined > maxTypes){
        Random rand = new Random();
        monsterNum = rand.nextInt(maxTypes);
    }else{
        monsterNum = determined;
    }
}

//Accessor functions
private int getMonsterNum(){
    return monsterNum;                      //Returns the number that the monster is
}

private int getmaxTypes(){
    return maxTypes;                        //Return the max number of monsters
}

public int getAttackStat(){
    return attackStat;                      //Return the monsters attack stat
}

public int getDefenseStat(){
    return defenseStat;                     //Return the monsters defense stat
}

public int getHealth(){
    return health;                          //Return the health stat of the monster
}   

}