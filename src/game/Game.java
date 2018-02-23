package game;

import city.cs.engine.*;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 1
*/
/**
 *  Game and levels are created and menu windows are implemented. 
*/
public class Game {
    
    private GameLevel world;
    private MoneyView view;
    private int level;
    private Controller controller;
    public JFrame frameMenu;
    public JFrame frameLogin;
    public JFrame frameInstructions;
    public JFrame frameGame;
    private SoundClip gameMusic;
    private Timer timer;
    private double time = 40000;
    public static Robber player;
    private int money;

    /** 
     * Initialise a login page upon startup. 
     * Sets the visability of the window to true upon startup.
     */
    public Game() {
        loginForm panel = new loginForm(this);
        
        frameLogin = new JFrame("JailSweep - Login");
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setLocationByPlatform(true);
        //frameLogin.getContentPane().add(panel);
        frameLogin.getContentPane().add(panel);
        frameLogin.setResizable(false);
        frameLogin.pack();
        frameLogin.setVisible(true);  
    }
    
    /** 
     * Initialises a splash screen once the user has logged in.
     * Opens a new window once prompted.
     */
    public void openSplashScreen() {
        MainMenu panel = new MainMenu(this);
        
        frameMenu = new JFrame("JailSweep - Home");
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenu.setLocationByPlatform(true);
        frameMenu.getContentPane().add(panel);
        frameMenu.setResizable(false);
        frameMenu.pack();
        frameMenu.setVisible(true);
    }
    
    /**
     *  Holds the instructions interface and creates a new window to display.
     *  Initialises a new panel to make the instructions panel.
     */
    public void openInstructions() {
        Instructions panel = new Instructions(this);
        
        frameInstructions = new JFrame("JailSweep - Instructions");
        frameInstructions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameInstructions.setLocationByPlatform(true);
        frameInstructions.getContentPane().add(panel);
        frameInstructions.setResizable(false);
        frameInstructions.pack();
        frameInstructions.setVisible(true);  
    }
    
    /**
    * Game shall be ran from beginGame().
    * A new world is created in a MoneyView to display live scores.
    */
    public void beginGame() {
        level = 1;
        world = new Level1(); //CHANGE THIS FOR DIFFERENT LEVEL        
        world.populate(this);

        try {
            gameMusic = new SoundClip("data/music.wav");
            gameMusic.loop();
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }
        
        view = new MoneyView(world, this, 1000, 700);
        // display the view in a frame
        frameGame = new JFrame("Event handling");
        // quit the application when the game window is closed
        frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGame.setLocationByPlatform(true);
        // display the world in the window
        frameGame.add(view);

        MenuPanel menuPanel = new MenuPanel(this);
        frameGame.add(menuPanel, BorderLayout.SOUTH);
        // don't let the game window be resized
        frameGame.setResizable(false);
        // size the game window to fit the world view
        frameGame.pack();
        // make the window visible
        frameGame.setVisible(true);
        // get keyboard focus
        frameGame.setFocusable(true);
        frameGame.requestFocusInWindow();
        // give keyboard focus to the frame whenever the mouse enters the view
        //view.addMouseListener(new GiveFocus(frame));
        controller = new Controller(world.getPlayer());
        frameGame.addKeyListener(controller);
        
        //Uncomment to make the view track the Robber
        //world.addStepListener(new Tracker(view, world.getPlayer()));

        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 500, 500);
        world.start();
    }

    /** The player in the current level.
     * @return  The player used in the world
     */
    public Robber getPlayer() {
        return world.getPlayer();
    }
    
    /** Is the current level of the game finished?
     * @return  Return a boolean if the world has been completed 
     */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }
    
    /** Gets the current level of the game
     * @return  Return an integer of the level 
     */
    public int getLevel(){
        return level;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * Get the current world that is in play
     * @return  Return the world that the game is using
     */
    public World getWorld(){
        return world;
    }
    
    /** 
     * Advance to the next level of the game once level is complete. 
     * Refreshes the time for each level.
     * @throws java.io.IOException  If no file is found.
     */
    public void goNextLevel() throws IOException {
        if (level == 5) {
            String file_name = "data/highScore.txt";
            
            WriteFile data = new WriteFile(file_name , true );
            data.writeToFile("Name: " + money);
            System.out.println( "Text File Written To" );
            
            System.exit(0);
        } else  if (level == 1 && world.isCompleted()) {
            level++;
            // get a new world
            world = new Level2();
            // fill it with bodies
            world.populate(this);
            time = time;

            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            // show the new world in the view
            view.setWorld(world);
            view.updateGameLevel(world);
            world.start();
        } else if (level == 2 && world.isCompleted()) {
            level++;
            // get a new world
            world = new Level3();
            System.out.println("Now in Level 3");
            // fill it with bodies
            world.populate(this);
            time = time;
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            
            // show the new world in the view
            view.setWorld(world);
            view.updateGameLevel(world);
            world.start();
        } else if (level == 3 && world.isCompleted()) {
            level++;
            // get a new world
            world = new Level4();
            // fill it with bodies
            world.populate(this);
            time = time;
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            
            view.setWorld(world);
            view.updateGameLevel(world);
            world.start();
        } else {
            level++;
            // get a new world
            world = new Level5();
            // fill it with bodies
            world.populate(this);
            time = time;
            // switch the keyboard  control to the new player
            controller.setBody(world.getPlayer());
            // show the new world in the view
            view.setWorld(world);
            view.updateGameLevel(world);
            world.start();
        }
    }

    /** 
     * Run the game. 
     * @param args  String arguments
     */
    public static void main(String[] args) {
        new Game();
    }
}