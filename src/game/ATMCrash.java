package game;

import city.cs.engine.*;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 2
*/
/**
 * Collision event for the ATM object.
 */
public class ATMCrash implements CollisionListener 
{
    /**
    * Creates a new instance of the Robber player
    * Held locally within the class
    */
    private Robber robber;
    
    /**
     *
     * @param robber    Takes robber as a parameter
     */
    public ATMCrash(Robber robber)
    {
        this.robber = robber;
    }
    
    /**
     *  Collision between robber and the body, results in being destroyed.
     * @param e Takes e as a parameter to the Collision Event
     */
    @Override
    public void collide(CollisionEvent e) 
        {
        if (e.getOtherBody() == robber) 
        {
            robber.incrementMoneyAtm(); //Decrements health of robber by 1
            e.getReportingBody().destroy(); //Destroys the object fo police
        }
    }
}