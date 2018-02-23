package game;

import city.cs.engine.*;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  A new object of spikes with image and shape boundaries.
*/
public class Spikes extends DynamicBody
{
    // Remember:  using the keyword static below means the fields shape and image belong to the class, rather than any instance. 
    // That means there is a single shape and image for all instances of the class.
    private static final Shape shape = new PolygonShape(
        -1.045f,0.477f, -1.125f,-0.477f, 1.11f,-0.473f, 1.053f,0.481f, -1.019f,0.481f);
    private static final BodyImage image =
        new BodyImage("data/spikes.png", 0.75f);
    
    /**
     *
     * @param world Takes world as a parameter
     */
    public Spikes(World world) 
    {
        super(world, shape);
        addImage(image);
    }
}
