/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;

/**
 * Creates boundaries which enemies and the user cannot move through.<link>Boundary objects have attributes of location.
 * @author Richard Dong.
 * @version 2.0. Last edited 2/6/2014
 */
public abstract class Boundary implements Location
{
    private int type, xPos, yPos, length, width;

    /**
     * Constructor for creating the Boundary object.
     * @param t the type of boundary.
     * @param x the x-coordinate of the boundary.
     * @param y the y-coordinate of the boundary.
     * @param w the vertical width of the boundary.
     * @param l the horizontal length of the boundary
     * @author Richard Dong
     * @version 1.0. Last edited 12/14/2013
     */
    public Boundary(int t, int x, int y, int l, int w)
    {
        try
        {
            type=t;
            xPos=x;
            yPos=y;
            length=l;
            width=w;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }   
    }
    
    /**
     * Returns the x-coordinate of the boundary.
     * @return xPos the x-coordinate of the boundary.
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
     * Returns the y-coordinate of the boundary.
     * @return yPos the y-coordinate of the boundary.
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
     * Returns the horizontal length of the boundary.
     * @return Length the length of the boundary.
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
     * Returns the vertical width of the boundary.
     * @return width - the vertical width of the boundary.
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
     * Returns the type of the boundary.
     * @return type - the type of the boundary.
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
     * When implemented, will draw the boundary object in the specified graphics context.
     * @param window the specified graphics context.
     * @author Richard Dong
     */
    public abstract void draw(Graphics window);
}
