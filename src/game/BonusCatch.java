package game;

import city.cs.engine.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 2
*/
/**
 *  This is a collision listener with the spikes and the robber. 
*/
public class BonusCatch implements CollisionListener 
{
    /**
    * Creates a new instance of the Robber player.
    * Held locally within the class.
    */
    private Robber robber;
    private SoundClip bonusSound;

    /**
     *
     * @param robber    Takes robber as a parameter 
     */
    public BonusCatch(Robber robber)
    {
        this.robber = robber;
        
        try {
            bonusSound = new SoundClip("data/bonusSound.wav");
            //moneySound.loop();
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }
    
    /**
     *  Collision between robber and the body, results in being destroyed.
     * @param e Takes e as a parameter for Collision event
     */
    @Override
    public void collide(CollisionEvent e) 
        {
            System.out.println("collide");
        if (e.getOtherBody() == robber) 
        {
            robber.incrementLives();//Decrements health of robber by 1
            e.getReportingBody().destroy(); //Destroys the object fo police
            bonusSound.play();
        }
        
        
    }
}