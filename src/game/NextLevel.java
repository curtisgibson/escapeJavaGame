package game;

import city.cs.engine.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  Collision listener between the exit sign and robber.
*/
public class NextLevel implements CollisionListener 
{
    private Robber robber;
    private Game game;
    private World world;
    
    /**
     *
     * @param robber    Takes robber as a parameter
     * @param game      Takes game as a parameter
     * @param world     Takes world as a parameter
     */
    public NextLevel(Robber robber, Game game, World world)
    {
        this.robber = robber;
        this.game = game;
        this.world = world;
    }

    /**
     *  Goes to the next level if the current level is completed
     * @param e     Takes e as a parameter for collision events
     */
    @Override
    public void collide(CollisionEvent e) 
        {
        System.out.println("collision");
        if (e.getOtherBody() == robber) 
        {
            System.out.println("collision2");
            if (game.isCurrentLevelCompleted()){
                System.out.println("collision3");
                try {
                    game.goNextLevel();
                } catch (IOException ex) {
                    Logger.getLogger(NextLevel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //e.getOtherBody().destroy();
        }
    }    
}