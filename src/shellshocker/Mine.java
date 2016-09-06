/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Creates mine objects that can be placed by the user and triggered by enemies to damage them. 
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */

public class Mine implements Location
{
    private int type,xPos,yPos,length,width;
    private Image mine;
    
   /**
    * Constructor for the mine object.
    * @param t The type of the mine.
    * @param x the x-coordinate of the mine.
    * @param y the y-coordinate of the mine.
    * @author Richard Dong.
    */
    public Mine(int t, int x, int y)
    {
        try
        {
            type=t;
            xPos=x;
            yPos=y;
            length=50;
            width=50;
            mine=Toolkit.getDefaultToolkit().getImage("resources/Mine.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Returns the type of the object. 
     * @return type - the type of the object.
     * @author Richard Dong
     */
    public int getType()
    {
        try
        {
            return type;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
         
    /**
     * Returns the x-coordinate of the mine object.
     * @return xPos the x-coordinate of the mine object.
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
    * Returns the y-coordinate of the mine object.
    * @return yPos - the y-coordinate of the mine object. 
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
     * Returns the horizontal length of the mine object.
     * @return length - the horizontal length of the mine object.
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
     * Returns the vertical width of the mine object.
     * @return width - the the vertical width of the mine object.
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
    * Draws the mine object in the specified graphics context.
    * @param window the specified graphics context.
    * @author Richard Dong
    */
    @Override
    public void draw(Graphics window)
    {
        try
        {
            window.drawImage(mine,getXPos(),getYPos(),null);      
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }    
}