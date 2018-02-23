package game;

import city.cs.engine.*;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  Creates Exit sign object and assigned an image. 
*/
public class ExitSign extends StaticBody 
{
    // Remember:  using the keyword static below means the fields shape and image belong to the class, rather than any instance. 
    // That means there is a single shape and image for all instances of the class.
    private static final Shape shape = new PolygonShape(
        -0.482f*2.5f,0.482f*2.5f, -0.497f*2.5f,-0.498f*2.5f, 0.495f*2.5f,-0.497f*2.5f, 0.458f*2.5f,0.48f*2.5f, -0.47f*2.5f,0.49f*2.5f);
    private static final BodyImage image =
        new BodyImage("data/exit.png", 2.5f);

    private Game game;
    
    /**
     *
     * @param world Takes world as a parameter
     */
    public ExitSign(World world) 
    {
        super(world, shape);
        addImage(image);
    }
}
