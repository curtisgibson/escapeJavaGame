package game;

import city.cs.engine.*;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 2
*/
/**
 *  Dynamic body class for Cash Machine and given image and shape. 
*/
public class CashMachine extends DynamicBody 
{
    private static final Shape shape = new PolygonShape(
        -0.16f*2.7f,0.495f*2.7f, -0.339f*2.7f,0.49f*2.7f, -0.392f*2.7f,-0.477f*2.7f, 0.198f*2.7f,
            -0.479f*2.7f, 0.198f*2.7f,0.473f*2.7f, -0.147f*2.7f,0.495f*2.7f);
    private static final BodyImage image =
        new BodyImage("data/cash.png", 2.7f);

    /**
     *
     * @param world Takes world as a parameter
     */
    public CashMachine(World world) 
    {
        super(world, shape);
        addImage(image);
    }
}
