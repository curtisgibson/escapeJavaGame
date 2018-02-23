package game;

import java.awt.Graphics2D;
import city.cs.engine.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author      Curtis, Gibson, curtis.gibson@city.ac.uk
 * @version     1.0
 * @since       Increment 2
*/
/**
 *  Outputs live information about scores to the users display.
*/
public class MoneyView extends UserView implements ActionListener {
    
    private Game game;
    private GameLevel gameLevel;
    private Robber robber;
    private Image background;
        public JFrame frameMenu;
    public JFrame frameLogin;
    public JFrame frameInstructions;
    public JFrame frameGame;
    private Timer timer;
    public double time = 40000;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
        
    /**
     *
     * @param gamelevel Takes game level as a parameter. Gets level number
     * @param game      Takes game as a parameter. 
     * @param width     Takes width value as a parameter
     * @param height    Takes height as a parameter 
     */
    public MoneyView(GameLevel gamelevel, Game game, int width, int height) 
    {
        super(gamelevel, width, height);
        this.game = (Game)game;
        this.gameLevel = gamelevel;
        this.robber = gameLevel.getPlayer();
        background = new ImageIcon("data/background4.jpg").getImage().getScaledInstance(1920, 700, Image.SCALE_DEFAULT);
        
        timer = new Timer(1,this);
        timer.start();
    }
    
    /**
     *  Draws background for the window
     * @param g Takes g as a parameter for the graphics
     */
    @Override
    protected void paintBackground(Graphics2D g) 
    {
        g.drawImage(background, 0, 0, this);
    }
    
    /**
     *  Paints player information on to the game play screen
     * @param g Takes g as a parameter for the graphics
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(Color.red);
        Font sHUD = new Font ("Money", Font.BOLD,16);
        g.setFont(sHUD);
        g.drawString("Money: " + gameLevel.getPlayer().getMoney(), 10,20);
        g.drawString("Health: " + gameLevel.getPlayer().getHealthCount(), 150,20);
        g.drawString("Lives: " + gameLevel.getPlayer().getLivesCount(), 250,20);
        g.drawString("Level: " + this.game.getLevel(),350,20);
        g.drawString("Time: " + time,450,20);
    }

    /**
     *  Gets the time from variable and outputs as given format.
     * @return  Return the time in a given format.
     */
    public String getTime() {
        return "" + numberFormat.format(time);
    }
    
    /**
     *  Stores the time in double. 
     * @return  Returns the time stored in the double
     */
    public double getTimerAsDouble() {
        return time;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        time -= 1;
        
        if (time <= 0) {
            System.exit(0);
        }
    }
    
    /**
     *  Restarts the time once a new level is implemented.
     */
    public void restartTimer() {
        timer.restart();
    }
    
    /**
     *  Updates the game level to g. Shows the view as well as the game play
     * @param g Takes g as a parameter 
     */
    public void updateGameLevel(GameLevel g){
        this.gameLevel = g;
        time = 40000;
    }
}
