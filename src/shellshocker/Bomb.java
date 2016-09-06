/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Creates a bomb object simulating an explosive device capable of 
 * destroying all opponents of the user-controlled character.<link> A bomb
 * has attributes of location and speed.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public class Bomb implements Location
{
    private int xPos,yPos,xSpd,ySpd,maxVel;
    private int length, width;
    private Image bomb;
 
    /**
     * Constructor for bomb object.<link> Bombs have attributes of location and speed.
     * @param x the x-coordinate of the location of the bomb.
     * @param y the y-coordinate of the location of the bomb.
     * @param xSpeed the horizontal speed of the bomb.
     * @param ySpeed the vertical speed of the bomb
     * @author Richard Dong
     */
    public Bomb(int x, int y, int xSpeed, int ySpeed)
    {
        try
        {
            xPos=x;
            yPos=y;
            xSpd=xSpeed;
            ySpd=ySpeed;
            length=50;
            width=50;
            bomb=Toolkit.getDefaultToolkit().getImage("resources/Bomb.png");
            maxVel=8;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    } 
    
    /**
     * Draws the bomb in the specified Graphics window.
     * @param window the specified Graphics window.
     */
    @Override
    public void draw(Graphics window)
    {
        try
        {
            updateBombs();
            window.drawImage(bomb,getXPos(),getYPos(),getLength(),getWidth(),null);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    /**
     * Sets the x-coordinate of the bomb to the passed parameter.
     * @param x the value in which the x-coordinate is to be set to.
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
     * Sets the y-coordinate of the bomb to the passed parameter.
     * @param y the value in which the y-coordinate is to be set to.
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
     * Sets the horizontal speed of the bomb to the passed parameter.
     * @param x the value in which the horizontal vector will be set to.
     * @author Richard Dong
     */
    public void setXSpd(int x)
    {
        try
        {
            xSpd=x;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Sets the vertical speed of the bomb to the passed parameter.
     * @param y the value in which the vertical vector will be set to.
     * @author Richard Dong
     */
    public void setYSpd(int y)
    {
        try
        {
            ySpd=y;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Updates the attributes of the bombs, such as location and speed.
     * @author Richard Dong
     */
    public void updateBombs()
    {
        try
        {
            if(Math.abs(getYSpd())<maxVel)
            {
                if((int)(Math.random()*1000)%4==0)
                {
                    setYSpd(getYSpd()+1);
                }
            }
            setXPos(getXPos()+getXSpd());
            setYPos(getYPos()+getYSpd());   
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }  
    
    /**
     * Returns the x-coordinate of the bomb object.
     * @return xPos - the x-coordinate of the bomb object.
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
     * Returns the y-coordinate of the bomb object.
     * @return yPos - the y-coordinate of the bomb object.
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
     * Returns the horizontal vector of the speed of the bomb object.
     * @return xSpd - the horizontal vector of the speed of the bomb object.
     * @author Richard Dong
     */
    public int getXSpd()
    {
        try
        {
            return xSpd; 
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;     
    }
    
    /**
     * Returns the vertical vector of the speed of the bomb object.
     * @return ySpd - the vertical vector of the speed of the bomb object.
     * @author Richard Dong
     */
    public int getYSpd()
    {
        try
        {
            return ySpd; 
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;     
    }
    
    /**
     * Returns the horizontal length of the bomb object.
     * @return length - the horizontal dimension of the object.
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
     * Returns the vertical width of the bomb object.
     * @return width - the vertical dimension of the object.
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
}
