package game;

import city.cs.engine.*;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 2
*/
/**
 *  BonusCoin object created and assigned image.
*/
public class BonusCoin extends DynamicBody 
{
    private static final Shape shape = new PolygonShape(
        0.41f*2.5f,0.428f*2.5f, -0.403f*2.5f,0.417f*2.5f, -0.446f*2.5f,-0.436f*2.5f, 0.393f*2.5f,
            -0.41f*2.5f, 0.44f*2.5f,0.422f*2.5f);
    private static final BodyImage image =
        new BodyImage("data/bonus.gif", 2.5f);
    
    /**
     *
     * @param world Takes world as a parameter.
     */
    public BonusCoin(World world) 
    {
        super(world, shape);
        addImage(image);
    }
}
