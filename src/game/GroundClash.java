package game;

import city.cs.engine.*;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 2
*/
/**
 *  A collision listener between ground and robber. 
*/
public class GroundClash implements CollisionListener 
{
    private Robber robber;
    
    /**
     *
     * @param robber    Takes robber as a parameter for collision
     */
    public GroundClash(Robber robber)
    {
        this.robber = robber;
    }
    
    /**
     *  Collision between robber and the body, results in being destroyed.
     * @param e Takes e as a parameter as a collision event.
     */
    @Override
    public void collide(CollisionEvent e) 
        {
        if (e.getOtherBody() == robber) 
        {
            robber.decrementLives(); //Decrements health of robber by 1
            e.getReportingBody().destroy(); //Destroys the object fo police
        }
    }
}