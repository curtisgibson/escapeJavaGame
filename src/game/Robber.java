package game;

import city.cs.engine.*;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  Contains the main player, the attributes and boundaries of the player.
*/
public class Robber extends Walker {

    private int healthCount;
    private int livesCount;
    private int money;
    private String nextLevel;
    
    private static final Shape shape = new PolygonShape(
        0.199f*2.5f,0.476f*2.5f, 0.275f*2.5f,-0.126f*2.5f, 0.106f*2.5f,-0.486f*2.5f, -0.185f*2.5f,-0.486f*2.5f, 
            -0.294f*2.5f,0.019f*2.5f, 0.006f*2.5f,0.474f*2.5f);
    
    private static final BodyImage image =
        new BodyImage("data/robber.png", 2.5f);
    
//    static {
//        try {
//            moneySound = new SoundClip("data/music.wav");
//            //moneySound.loop();
//        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
//            System.out.println(e);
//        }
//    }
//    
    /**
     *  Assigns the health, lives and money to the user when playing the game. 
     * @param world Takes world as a parameter. Brings objects and methods.
     * @param game  Takes game as a parameter. 
     */
    public Robber(World world, Game game) {
        super(world, shape);
        addImage(image);
        healthCount = 3;
        livesCount = 1;
        //this.game = game;
        money = 0;
        
        SolidFixture fixture = new SolidFixture(this, shape);
        fixture.setFriction(0.5f);
    }

    /**
     *  Decrements the health count of the robber by one every time they collide.
     *  If the health count is less than 1, the user will die 
     */
    public void decrementHealthCount() {
        if (livesCount >1 && healthCount <=1) {
            healthCount=3;
            livesCount--;
        }   else if(healthCount <= 1) //Health less than = to 0 
        {
            System.out.println("Ouch! Back off! Health = 0");
            System.out.println("You Died. Game Over!");
            System.exit(0); //Exit the game
        } else {
            healthCount--; //Reduce by 1
            System.out.println("Ouch! Back off! Health = " + healthCount); 
            //Collision with police decrements health of 1
        }
    }
    
    /**
     *  The robbers life count will decrement by 1 if they collide with a object.
     *  If the lives count also equals to 0, then the game will be over.
     *  The system will then exit
     */
    public void decrementLives()
    {
        livesCount--;
        
        if (livesCount == 0) {
            System.out.println("You got caught. Game Over! Lives = " + livesCount);
            System.exit(0);
        } else {
            System.out.println("Ouch!  Lives = " + livesCount);
        }
    }
    
    /**
     *  The robbers lives count will increment by one if they collect an object.
     */
    public void incrementLives() {
        livesCount++;
    }
    
    /**
     *  The user's money will increment by 500 every time they collect a stash. 
     *  It will output to the console the users total value
     */
    public void incrementMoney() {
        money += 500; //When steals the pile, add £500 to balance
        System.out.println("Cha Ching - You have £" + money);
    }
    
    /**
     *  If the user collects the ATM, their money will increment by 1500.
     *  They're value will also be output to the console
     */
    public void incrementMoneyAtm() {
        money += 1500;
        System.out.println("Cha Ching - You just robber the bank! You have £" + money);
    }
    
    /**
     *  Stores information about the value of money.
     * @param money Stores the value of money the robber has collected
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     *  Stores a value.
     * @return  Returns the amount of money the user has collected
     */
    public int getMoney() {
        return money;
    }
    
    /**
     *  Stores the value of the current level and goes to the next.
     * @return  Returns the next level
     */
    public String getNextLevel() {
        return nextLevel;
    }
    
    /**
     *  Set's the healthCount to healthCount when updated.
     * @param healthCount   healthCount is being held in an integer
     */
    public void setHealthCount(int healthCount) {
        this.healthCount = healthCount;
    }
    
    /**
     *  Gets the health count and returns the value.
     * @return  Returns the new value of healthCount once updated
     */
    public int getHealthCount() {
        return healthCount;
    }
    
    /**
     *  Sets the lives count to the value of lives count.
     * @param livesCount    sets the lives count when the suer is generated 
     */
    public void setLivesCount(int livesCount) {
        this.livesCount = livesCount;
    }
    
    /**
     *  Stores and gets the lives count and returns the value.
     * @return  Returns the updated lives count during the game play
     */
    public int getLivesCount() {
        return livesCount;
    }   
}
