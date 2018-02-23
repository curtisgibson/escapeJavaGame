package game;

import city.cs.engine.*;

/*
    This is a simple object within the game. It draws the object using points and assigns a image
*/
/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  Police object created and given image and shape.
*/
public class PoliceMan extends DynamicBody 
{
    // Remember:  using the keyword static below means the fields shape and image belong to the class, rather than any instance. 
    // That means there is a single shape and image for all instances of the class.
    private static final Shape shape = new PolygonShape(
        0.025f*3,0.449f*3, 0.154f*3,0.036f*3, 0.137f*3,-0.478f*3, -0.129f*3,-0.478f*3, -0.137f*3,-0.026f*3, -0.036f*3,0.447f*3);
    private static final BodyImage image =
        new BodyImage("data/PoliceMan.png", 3);

    /**
     *
     * @param world Takes world as a parameter
     */
    public PoliceMan(World world) 
    {
        super(world, shape);
        addImage(image);
    }
}
