/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Creates a reactor object that, when destroyed, results in the end of the ShellShocker game.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public class Reactor implements Location
{
    private int xPos, yPos, health, length, width,healthLimit;
    private boolean active;
    private Image reactor,shield;
    
   /**
    * Constructor that creates a reactor object that has location and health.
    * @param x the x-coordinate of the reactor object.
    * @param y the y-coordinate of the reactor object.
    * @param h the health of the reactor object.
    * @author Richard Dong
    */
    public Reactor(int x, int y, int h)
    {
        try
        {
            xPos=x;
            yPos=y;
            health=h;
            active=true;
            length=300;
            width=300;
            reactor=Toolkit.getDefaultToolkit().getImage("resources/Reactor.png");
            shield=Toolkit.getDefaultToolkit().getImage("resources/Shield.png");
            healthLimit=1000;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }         
    }
    
   /**
    * Returns the x-coordinate of the reactor object.
    * @return xPos - the x-coordinate of the reactor object.
    * @author Richard Dong
    */
    @Override
    public int getXPos() 
    {
        try
        {
            return xPos;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }

    /**
    * Returns the y-coordinate of the reactor object.
    * @return yPos - the y-coordinate of the reactor object.
    * @author Richard Dong
    */
    @Override
    public int getYPos() 
    {
        try
        {
            return yPos;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }

    /**
    * Returns the vertical width of the reactor object.
    * @return width - the vertical width of the reactor object.
    * @author Richard Dong
    */
    @Override
    public int getWidth() 
    {
        try
        {
            return width;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }

    /**
    * Returns the horizontal length of the reactor object.
    * @return length - the horizontal length of the reactor object.
    * @author Richard Dong
    */
    @Override
    public int getLength() 
    {
        try
        {
            return length;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
   /**
    * Returns the health of the reactor object.
    * @return health - the health of the reactor object.
    * @author Richard Dong
    */
    public int getHealth()
    {
        try
        {
            return health;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
   /**
    * Returns the state of the reactor object shield.
    * @return active - the state of the reactor object shield.
    * @author Richard Dong
    */
    public boolean getActive()
    {
        try
        {
            return active;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return false;
    }
    
    /**
     * Sets the state of the reactor shield to the passed parameter.
     * @param b the state which the reactor shield is to be set to.
     * @author Richard Dong
     */
    public void setActive(boolean b)
    {
        try
        {
            active=b;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Sets the health of the reactor to the passed parameter.
     * @param h the health which the reactor is to be set to.
     * @author Richard Dong
     */
    public void setHealth(int h)
    {
        try
        {
            health=h;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }

    /**
    * Draws the reactor in the specified Graphics context.
    * @param window the specified Graphics context.
    * @author Richard Dong
    */
    @Override
    public void draw(Graphics window) 
    {
        try
        {
            if(active)
            {
                window.drawImage(shield,getXPos()-60,getYPos()-60,getLength()+120,getWidth()+120,null);
            }
            window.drawImage(reactor,getXPos(),getYPos(),getLength(),getWidth(), null);
            window.drawRect(getXPos(), getYPos()-20,getLength(),5);
            window.fillRect(getXPos(),getYPos()-20, (int)(getHealth()/(double)healthLimit*getLength()),5);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
}
