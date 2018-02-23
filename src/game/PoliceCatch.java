package game;

import city.cs.engine.*;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  A collision listener between police and the robber.
*/
public class PoliceCatch implements CollisionListener 
{
    private Robber robber;
    
    /**
     *  
     * @param robber    Takes robber as a parameter
     */
    public PoliceCatch(Robber robber)
    {
        this.robber = robber;
    }
    
    /**
     *  If a collision is heard between robber and object, destroys the other object. 
     * @param e     Takes e as a parameter for collision events
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