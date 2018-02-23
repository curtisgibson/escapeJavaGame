package game;

import city.cs.engine.*;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  Money objects are given image and shape.
*/
public class MoneyStash extends DynamicBody 
{
    private static final Shape shape = new PolygonShape(
        -1.283f,-0.448f, 1.293f,-0.505f, 0.592f,0.51f, -0.361f,0.474f, -1.283f,-0.275f);
    private static final BodyImage image =
        new BodyImage("data/money.gif", 1);

    /** 
     *  
     * @param world Takes world as a parameter
     */
    public MoneyStash(World world) 
    {
        super(world, shape);
        addImage(image);
    }
}
