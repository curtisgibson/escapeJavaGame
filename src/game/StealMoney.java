package game;

import city.cs.engine.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  Collision listener between money and the robber.
*/
public class StealMoney implements CollisionListener 
{
    private Robber robber;
    private SoundClip moneySound;
    
    /**
     *
     * @param robber    Takes robber as a parameter.
     */
    public StealMoney(Robber robber)
    {
        this.robber = robber;
        
        try {
            moneySound = new SoundClip("data/chaChing.wav");
            //moneySound.loop();
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
    }
    
    /**
     *  If a collision is heard between robber and object, destroys the other object.
     * @param e Takes e as a parameter for the collision event
     */
    @Override
    public void collide(CollisionEvent e) 
        {
        if (e.getOtherBody() == robber) 
        {
            robber.incrementMoney(); //Decrements health of robber by 1
            e.getReportingBody().destroy(); //Destroys the object fo police
            moneySound.play();
        }
    }
}