/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Creates enemy objects that have attributes of location, speed, and health.<link>
 * The enemies serve the purpose of impeding progress for the user-based character
 * and can be destroyed.
 * @author Richard Dong.
 * @version 2.0. Last edited 2/6/2014
 */
public abstract class EnemySprite implements Location
{
    private int xPos, yPos, xSpeed, ySpeed, length, width, timeCount, type, health,healthLimit;
    private boolean active;
    public Scanner scan;
    public ArrayList<Integer>typeList,xCoordinateList,yCoordinateList;
    public String[]strs;
    
    /**
     * Constructor for creating the enemy object, which has attributes of location,
     * activity, movement, and also rates at which to fire projectiles.
     * @param t The type of enemy.
     * @param x the x-coordinate of the enemy.
     * @param y the y-coordinate of the enemy. 
     * @param xSpd the horizontal vector of the speed of the enemy.
     * @param ySpd the vertical vector of the speed of the enemy.
     * @param l the horizontal length of the enemy.
     * @param w the vertical width of the enemy.
     * @param h the health value of the enemy.
     * @param a the boolean which determines whether the enemy remains 
     * active or becomes inactive.
     * @param time the time count for the enemy.
     * @param limit the maximum health value of the enemy. 
     * @author Richard Dong
     */
    public EnemySprite(int t, int x, int y, int xSpd, int ySpd,int l, int w, int h,boolean a,int time,int limit)
    {
        try
        {
            type=t;
            xPos=x;
            yPos=y;
            xSpeed=xSpd;
            ySpeed=ySpd;
            health=h;
            width=w;
            length=l;
            active=a;
            timeCount=time;
            healthLimit=limit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Returns the x-coordinate of the enemy.
     * @return xPos - the x-coordinate of the enemy.
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
     * Returns the y-coordinate of the enemy.
     * @return yPos - the y-coordinate of the enemy.
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
     * Returns the horizontal vector of the speed of the enemy.
     * @return xSpd - the horizontal vector of the speed of the enemy
     * @author Richard Dong
     */
    public int getXSpd()
    {
        try
        {
            return xSpeed;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Returns the vertical vector of the speed of the enemy.
     * @return ySpd - the vertical vector of the speed of the enemy
     * @author Richard Dong
     */
    public int getYSpd()
    {
        try
        {
            return ySpeed;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }
            
    /**
     * Returns the horizontal length of the enemy.
     * @return length - the horizontal length of the speed of the enemy
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
     * Returns the vertical width of the speed of the enemy.
     * @return width - the vertical width of the speed of the enemy
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
     * Returns the current health value of the enemy.
     * @return health - the current health value of the enemy.
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
     * Returns the maximum health value of the enemy.
     * @return healthLimit - the maximum health value of the enemy.
     * @author Richard Dong
     */
    public int getHealthLimit()
    {
        try
        {
            return healthLimit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Returns the current time count of the enemy.<link>The time count keeps track 
     * of when the enemy object fires projectiles.
     * @return timeCount - the current time count of the enemy.
     * @author Richard Dong
     */
    public int getTimeCount()
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
     * Returns the type of the enemy.
     * @return type - the type of the enemy.
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
     * Returns the state of activity of the enemy.
     * @return active - the state of activity of the enemy.
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
     * Sets the current health value of the enemy.
     * @param h the current health value of the enemy.
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
     * Sets the maximum health value of the enemy.
     * @param h the maximum health value of the enemy.
     * @author Richard Dong
     */
    public void setHealthLimit(int h)
    {
        try
        {
            healthLimit=h;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
         
    /**
     * Sets the type of the enemy.
     * @param t the current health value of the enemy.
     * @author Richard Dong
     */
    public void setType(int t)
    {
        try
        {
            type=t;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
         
    /**
     * Sets the state of activity of the enemy.
     * @param b the state of activity  of the enemy.
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
     * Sets the horizontal vector speed of the enemy to the passed parameter.
     * @param x the horizontal vector speed of the enemy.
     * @author Richard Dong
     */
    public void setXSpd(int x)
    {
        try
        {
            xSpeed=x;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
         
    /**
     * Sets the vertical vector speed of the enemy to the passed parameter.
     * @param y the vertical vector speed of the enemy.
     * @author Richard Dong
     */
    public void setYSpd(int y)
    {
        try
        {
            ySpeed=y;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
         
    /**
     * Sets the x-coordinate of the enemy to the passed parameter.
     * @param x the x-coordinate speed of the enemy.
     * @author Richard Dong
     */
    public void setXPos(int x)
    {
        try
        {
            xPos=x;;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Sets the y-coordinate of the enemy to the passed parameter.
     * @param y the y-coordinate speed of the enemy.
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
     * Sets the time count of the enemy to the passed parameter.
     * @param t the time count of the enemy.
     * @author Richard Dong
     */
    public void setTimeCount(int t)
    {
        try
        {
            timeCount=t;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
         
    /**
     * Fires the weapons of the enemy based on the condition of the user-controlled character.
     * @param w the weapon of the enemy.
     * @param r the user controlled character.
     * @author Richard Dong
     */
    public abstract void fireWeapons(Weapon w, Ragnar r);
       
    /**
     * Draws the enemy object in the specified graphics context.
     * @param window the specified graphics context.
     * @author Richard Dong
     */
    @Override
    public abstract void draw(Graphics window);
         
    /**
     * Finds the next location of the enemy object based on current location and speed.
     * @author Richard Dong
     */
    public abstract void findNextLoc();      
}
