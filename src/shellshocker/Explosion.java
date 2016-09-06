/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Creates an explosion object that displays a series of images 
 * at specific time intervals to imitate an animation of an explosion.
 * @author NNLLJJ
 * @version 2.0. Last edited 2/6/2014
 */
public class Explosion implements Location
{
    private Image exp0,exp1,exp2,exp3;
    private int xPos,yPos,length,width,timeCount;
    
    /**
     * Constructor for the explosion object. 
     * @param x the x-coordinate of the object.
     * @param y the y-coordinate of the object.
     * @param l the horizontal length of the object.
     * @param w the vertical width of the object
     * @author Richard Dong
     */
    public Explosion(int x, int y, int l,int w)
    {
        try
        {
            xPos=x;
            yPos=y;
            length=l;
            width=w;
            timeCount=0;
            exp0=Toolkit.getDefaultToolkit().getImage("resources/Explosion0.png");
            exp1=Toolkit.getDefaultToolkit().getImage("resources/Explosion1.png");
            exp2=Toolkit.getDefaultToolkit().getImage("resources/Explosion2.png");
            exp3=Toolkit.getDefaultToolkit().getImage("resources/Explosion3.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Returns the time the object has been active.
     * @return timeCount - the time the object has been active.
     * @author Richard Dong
     */
    public int getTime()
    {
        try
        {
            return timeCount;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;     
    }
    
    /**
     * Returns the x-coordinate of the object.
     * @return xPos - the x-coordinate of the object.
     * author Richard Dong
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
     * @return yPos - the y-coordinate of the object. 
     * @author Ricahrd Dong
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
     * Returns the horizontal length of the object.
     * @return length - the horizontal length of the object.
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
     * Returns the vertical width of the object.
     * @return width - the vertical width of the object.
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
    * Draws the object in the specified graphics context.
    * @param window the specified graphics context.
    * @author Richard Dong
    */
    @Override
    public void draw(Graphics window)
    {  
        try
        {
            if(getTime()/3==0)
            {
                window.drawImage(exp0, getXPos(), getYPos(),getLength(),getWidth(), null);
            }
            else if(getTime()/3==1)
            {
                window.drawImage(exp1, getXPos(), getYPos(),getLength(),getWidth(), null);
            }
            else if(getTime()/3==2)
            {
                window.drawImage(exp2, getXPos(), getYPos(),getLength(),getWidth(), null);
            }
            else if(getTime()/3==3)
            {
                window.drawImage(exp3, getXPos(), getYPos(),getLength(),getWidth(), null);
            }       
            timeCount++;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }   
    }  
}