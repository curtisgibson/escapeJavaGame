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
 * @since       Increment 1
*/
/**
 *  Level 1 of the game is produced with platforms and objects.
*/
public class Level1 extends GameLevel {
    
    private PoliceMan police;
    private Spikes spikes;
    private MoneyStash stash;
    
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
        
        Shape platformDiag = new BoxShape (2.5f,0.1f);
        Body platformDiag1 = new StaticBody(this, platformDiag);
        platformDiag1.setPosition(new Vec2(-21, 1));
        platformDiag1.rotateDegrees(45);
        platformDiag1.setFillColor(new Color(193,49,39));
        platformDiag1.setLineColor(new Color(193,49,39));  
        
        Shape platformDiag2 = new BoxShape (2,0.1f);
        Body platformDiag3 = new StaticBody(this, platformDiag2);
        platformDiag3.setPosition(new Vec2(15.4f, 11.9f));
        platformDiag3.rotateDegrees(35);
        platformDiag3.setFillColor(new Color(193,49,39));
        platformDiag3.setLineColor(new Color(193,49,39)); 
        
        Shape boxShape2 = new BoxShape(0.1f, 2); //Vertical Platforms
        Shape boxShape3 = new BoxShape(4, 0.1f); //Horizontal Platforms

        //Array for vertical platforms
        List<Vec2> pointV = Arrays.asList(new Vec2(2,2), new Vec2(2,6), new Vec2(2,10),new Vec2(9,16),
                new Vec2(6,-1),new Vec2(12,3));
        
        for (int i=0; i<6; i++)
            {   
                Vec2 coordsV = pointV.get(i);
                Body platform = new StaticBody(this, boxShape2);
                platform.setPosition(coordsV);
                platform.setFillColor(new Color(193,49,39));
                platform.setLineColor(new Color(193,49,39));  
            }
        
        //Array for Horizonal Platforms
        List<Vec2> point = Arrays.asList(new Vec2(1,13), new Vec2(10,8), new Vec2(20,4), 
        new Vec2(21,13), new Vec2(10,-3),new Vec2(7,-11),new Vec2(21,-1),new Vec2(18,-8),
        new Vec2(21,-14),
        
        new Vec2(-2,0),new Vec2(-13,4),new Vec2(-9,10),new Vec2(-21,13),
        
        new Vec2(-15,-3),new Vec2(-13,-13),new Vec2(-1,-8),new Vec2(-19,-8));
        
        for (int i=0; i<17; i++)
        {
              Vec2 coords = point.get(i);
              Body platform = new StaticBody(this, boxShape3);
              platform.setPosition(coords);
              platform.setFillColor(new Color(193,49,39));
              platform.setLineColor(new Color(193,49,39));
        }
        
        Random numberGenerator = new Random(); //Random number generator
     
        //Array for Police Officers
        List<Vec2> policeP = Arrays.asList(new Vec2(-18,-2.5f),new Vec2(-12,-8),
                new Vec2(7,8.3f),new Vec2(-2,1), new Vec2(20,-7));
        
        for (int i=0; i<5; i++)
        {
            Vec2 policeA = policeP.get(i);
            police = new PoliceMan(this);
            police.setPosition(policeA);
            
            police.addCollisionListener(new PoliceCatch(player)); //Collision Catcher
        }
        
        //Array for Money Stashed
        List<Vec2> moneyS = Arrays.asList(new Vec2(-23,13.5f),new Vec2(24,13.5f),new Vec2(0,-6.5f));
        
        for (int i=0; i<3; i++)
        {
            Vec2 stashA = moneyS.get(i);
            stash = new MoneyStash(this);
            stash.setPosition(stashA);
            
            stash.addCollisionListener(new StealMoney(player));
        }
        
        //Random Assign Spikes
        for (int i=0; i<5; i++)
        {
            int x = numberGenerator.nextInt(50)-25;
            int y = numberGenerator.nextInt(35)-17;
            spikes = new Spikes(this);
            spikes.setPosition(new Vec2(x, y));
            
            spikes.addCollisionListener(new SpikesCrash(player)); //Collision Catcher
        }
    }
    
    @Override
    public Vec2 startPosition() {
        return new Vec2(-23,-17);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(23.7f,0.3f);
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
