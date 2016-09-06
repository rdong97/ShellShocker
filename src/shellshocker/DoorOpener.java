/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Creates an object that links to a door by allowing access when the object is destroyed. 
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */

public class DoorOpener implements Location
{
    private Image doorOpener;
    private int xPos,yPos,openLength,openWidth,openHealth;
    private int healthLimit;   

   /**
    * Constructor for creating the DoorOpener object.
    * @param x the x-coordinate of the object.
    * @param y the y-coordinate of the object.
    * @param length the horizontal length of the object.
    * @param width the vertical width of the object.
    * @param health the health value of the target.
    * @author Richard Dong
    */
    public DoorOpener(int x, int y, int length, int width, int health)
    {
        try
        {
            xPos=x;
            yPos=y;
            openLength=length;
            openWidth=width;
            openHealth=health;
            healthLimit=100;
            doorOpener=Toolkit.getDefaultToolkit().getImage("resources/DoorOpener.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Draws the doorOpener objects in the specified Graphics context. 
     * @param window the specified Graphics context. 
     * @author Richard Dong
     */
    @Override
    public void draw(Graphics window)
    {
        try
        {
            window.drawImage(doorOpener, getXPos(),getYPos(),getLength(),getWidth(), null);
            window.setColor(Color.RED);
            window.drawRect(getXPos(), getYPos()-20,getLength(),5);
            window.fillRect(getXPos(),getYPos()-20, (int)(getHealth()/(double)healthLimit*getLength()),5);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }   
    }

    /**
     * Returns the x-coordinate of the object.
     * @return xPos - the x-coordinate of the object.
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
    * Returns the y-coordinate of the object.
    * @return yPos the y-coordinate of the object. 
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
     * Returns the horizontal length of the DoorOpener object.
     * @return openLength - the horizontal length of the DoorOpener object.
     * @author Richard Dong
     */
    @Override
    public int getLength()
    {
        try
        {
            return openLength;
        }   
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }
         
    /**
     * Returns the vertical width of the DoorOpener object.
     * @return openWidth - the the vertical width of the DoorOpener object.
     * @author Richard Dong
     */
    @Override
    public int getWidth()
    {
        try
        {
            return openWidth;
        }   
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }
    
    /**
     * Returns the health value of the DoorOpener object. 
     * @return openHealth the health value of the DoorOpener object. 
     * @author Richard Dong
     */
    public int getHealth()
    {
        try
        {
            return openHealth;
        }   
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Sets the x-coordinate of the object.
     * @param x the x-coordinate of the object.
     * @author Richard Dong
     */
    public void setXPos(int x)
    {
        try
        {
            xPos=x;
        }   
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Sets the y-coordinate of the object.
     * @param y the y-coordinate of the object.
     * @author Richard Dong
     */
    public void setYPos(int y)
    {
        try
        {
            yPos=y;
        }   
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Sets the horizontal length of the object.
     * @param l the horizontal length of the object. 
     * @author Richard Dong
     */
    public void setLength(int l)
    {
        try
        {
            openLength=l;
        }   
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
       
    /**
     * Sets the vertical width of the object
     * @param w the vertical width of the object.
     * @author Richard Dong
     */
    public void setWidth(int w)
    {
        try
        {
            openWidth=w;
        }   
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Sets the health value of the object.
     * @param h the health value of the object.
     * @author Richard Dong
     */
    public void setHealth(int h)
    {
        try
        {
            openHealth=h;
        }   
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
}
