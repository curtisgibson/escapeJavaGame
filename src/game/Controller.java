package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  Controls the Robber object using key presses.
*/
public class Controller extends KeyAdapter {
    private static final float JUMPING_SPEED = 10;
    private static final float WALKING_SPEED = 5;
    
    /**
    * Creates a new instance of the walker body called body.
    * Held locally within the class.
    */
    private Walker body;
    
    /**
     *  Initalises this body to a body.
     * @param body  Links the walker body
     */
    public Controller(Walker body)
    {
        this.body = body;
    }
    
    /**
    * When key is presses, a action is performed.
    * 
    * Once the user presses a button listed, the associated action is performed.
    * For example, key Q is pressed, the application will close.
    *
    * @param e first parameter of KeyEvent
    */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) { // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_SPACE) { // J = jump
            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.jump(JUMPING_SPEED);
            }
        } else if (code == KeyEvent.VK_LEFT) {
            //System.out.println("A");
            body.startWalking(-WALKING_SPEED); // 1 = walk left
        } else if (code == KeyEvent.VK_RIGHT) {
            //System.out.println("D");
            body.startWalking(WALKING_SPEED); // 2 = walk right
        }
        else if (code == MouseEvent.MOUSE_CLICKED) {
            body.jump(JUMPING_SPEED);
        }
    }

    /**
     *  Gets the body currently in play.
     * @return  Returns the body being used
     */
    public Walker getBody() {
        return body;
    }

    /**
     *  Sets the body in the game to the body already being used.
     * @param body  Links walker body to method 
     */
    public void setBody(Walker body) {
        this.body = body;
    }

    /**
     * Handle key release events.
     * @param e Takes e as a parameter for the KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            //System.out.println("Stop A");
            body.stopWalking();
        } else if (code == KeyEvent.VK_RIGHT) {
            //System.out.println("Stop D");
            body.stopWalking();
        }
    }
}
