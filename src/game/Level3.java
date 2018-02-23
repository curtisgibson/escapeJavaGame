/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Fixture;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.jbox2d.common.Vec2;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 2
*/
/**
 *  Level 3 of the game is produced with platforms and objects.
*/
public class Level3 extends GameLevel {
    
    private PoliceMan police;
    private Spikes spikes;
    private MoneyStash stash;
    private BonusCoin bonus;

    private static final int NUM_MONEY = 1500;
    
    /**
    * Populates the world with objects and players.
    * This method creates each object which makes up the platforms for the game.
    * Also creates, instances of the police object, stash of money and spikes into the level.
    *     
    * @param game Takes game as a parameter. Brings methods and objects
    */
    @Override
    public void populate(Game game) 
    {
        super.populate(game);

        // make the ground
        Shape groundShape = new BoxShape(25, 0.5f);
        Body ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -17.5f));
        
        // Walls
        Shape leftWallShape = new BoxShape(0.5f, 35-17.5f, new Vec2(-25.5f, 36-19));
        Fixture leftWall = new SolidFixture(ground, leftWallShape); //left Wall
        Shape rightWallShape = new BoxShape(0.5f, 35-17.5f, new Vec2(25.5f, 36-19));
        Fixture rightWall = new SolidFixture(ground, rightWallShape);//Right Wall
        Shape topWallShape = new BoxShape(26f,0.5f);//Roof Shape
        Body roof = new StaticBody(this, topWallShape);
        roof.setPosition(new Vec2(0,18f)); 
        
        Shape boxShape2 = new BoxShape(0.1f, 2); //Vertical Platforms
        Shape boxShape3 = new BoxShape(2, 0.1f); //Horizontal Platforms
        
        //Array for vertical platforms
        List<Vec2> pointV = Arrays.asList(new Vec2(2,2), new Vec2(2,6), new Vec2(2,10),new Vec2(-7,6),
                new Vec2(17,2),new Vec2(17,6),new Vec2(17,10));
        
        for (int i=0; i<7; i++)
            {
                Vec2 coordsV = pointV.get(i);
                Body platform = new StaticBody(this, boxShape2);
                platform.setPosition(coordsV);
                platform.setFillColor(new Color(193,49,39));
                platform.setLineColor(new Color(193,49,39));  
            }
        
        //Array for Horizonal Platforms
        List<Vec2> point = Arrays.asList(new Vec2(0,0), new Vec2(4,0),
        new Vec2(12,6), new Vec2(19,0), new Vec2(23,0), new Vec2(23,12), new Vec2(0,13), 
        new Vec2(),new Vec2(),new Vec2(), 
        
        new Vec2(10,-6),new Vec2(21,-8),new Vec2(15,-13),new Vec2(6,-12),new Vec2(23,-15),
        new Vec2(),new Vec2(),new Vec2(),new Vec2(),new Vec2(),
        
        new Vec2(-9,4),new Vec2(-23,8),new Vec2(-12,13),new Vec2(-19,5),new Vec2(),
        new Vec2(),new Vec2(),
        
        new Vec2(-17,-1),new Vec2(-19,-9),new Vec2(-11,-13),new Vec2(-4,-6),new Vec2(-1,-11),
        new Vec2(-10,-4),new Vec2(),new Vec2(),new Vec2(),new Vec2(), new Vec2());
        
        for (int i=0; i<38; i++)
        {
              Vec2 coords = point.get(i);
              Body platform = new StaticBody(this, boxShape3);
              platform.setPosition(coords);
              platform.setFillColor(new Color(193,49,39));
              platform.setLineColor(new Color(193,49,39));
        }
        
        Random numberGenerator = new Random(); //Random number generator
     
        //Array for Police Officers
        List<Vec2> policeP = Arrays.asList(new Vec2(-9,4.5f),new Vec2(-19,-8.7f),new Vec2(18,0.2f));
        
        for (int i=0; i<3; i++)
        {
            Vec2 policeA = policeP.get(i);
            police = new PoliceMan(this);
            police.setPosition(policeA);
            
            police.addCollisionListener(new PoliceCatch(player)); //Collision Catcher
        }
        
            //Creates instance of Bonus Coin
            bonus = new BonusCoin(this);
            bonus.setPosition(new Vec2(23,-14));
            //Creates a collision listener with event
            bonus.addCollisionListener(new BonusCatch(player));

        //Array for Money Stashed
        List<Vec2> moneyS = Arrays.asList(new Vec2(-23,13.5f),new Vec2(24,13.5f),new Vec2(0,-6.5f));
        
        for (int i=0; i<3; i++)
        {
            Vec2 stashA = moneyS.get(i);
            stash = new MoneyStash(this);
            stash.setPosition(stashA);
            
            stash.addCollisionListener(new StealMoney(player));
        }
        
        List<Vec2> spikeBottom = Arrays.asList(new Vec2(0,-16),new Vec2(2,-16),new Vec2(4,-16),new Vec2(6,-16)
        ,new Vec2(8,-16),new Vec2(10,-16),new Vec2(12,-16),new Vec2(14,-16),new Vec2(16,-16),new Vec2(18,-16),
        new Vec2(20,-16),new Vec2(22,-16),new Vec2(-2,-16),new Vec2(-4,-16),new Vec2(-6,-16),new Vec2(-8,-16),
        new Vec2(-10,-16),new Vec2(-12,-16),new Vec2(-14,-16),new Vec2(-16,-16),new Vec2(-18,-16),
        new Vec2(-20,-16));
        
        //Random Assign Spikes
        for (int i=0; i<22; i++)
        {
            Vec2 stashB = spikeBottom.get(i);
            spikes = new Spikes(this);
            spikes.setPosition(stashB);
            
            spikes.addCollisionListener(new SpikesCrash(player)); //Collision Catcher
        }
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0,1);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(23.7f,1.4f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getMoney() == NUM_MONEY;
    }

    /**
     *  Stores the player being used and is returned.
     * @return  Returns player currently in use
     */
    @Override
    public Robber getPlayer() {
        return player;
    }
}
