/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  Abstract class populating the game levels each time. 
*/
public abstract class GameLevel extends World {
    public static Robber player; //Main Player 
    private ExitSign exit;
   
    public abstract Robber getPlayer();
        
    /**
     *  Populates the world when the method is called.
     * @param game  Takes game as a parameter. Brings methods and objects.
     */
    public void populate(Game game) 
    {        
        player = new Robber(this,game);
        player = this.getPlayer();
        player.setPosition(startPosition()); //(-23,-17)
        
        exit = new ExitSign(this);
        exit.setPosition(doorPosition()); //(23.7f,0.3f)
        exit.addCollisionListener(new NextLevel(player, game, this));
    }
    
    /** The initial position of the player.
     * @return  The value of coordinates where the robber will start
     */
    public abstract Vec2 startPosition();
    
    /** The position of the exit door.
     * @return  The value of coordinates where the door is placed
     */
    public abstract Vec2 doorPosition();
    
    /** Is this level complete?
     * @return  Return boolean if the level is complete
     */
    public abstract boolean isCompleted();
}
