/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Creates door objects that can be opened by the user to allow access to different
 * areas of the game map.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public class Doors extends Boundary implements Location
{
    private DoorOpener doorOpen;
    private Image doorType0,doorType1;
    
    /**
     * Creates an door object.
     * @param t the type of door.
     * @param x the x-coordinate of the location of the door.
     * @param y the y-coordinate of the location of the door.
     * @param length the horizontal length of the door.
     * @param width the vertical width of the door.
     * @author Richard Dong
     */
    public Doors(int t, int x, int y,int length, int width, DoorOpener dO)
    {
        super(t,x,y,length,width);
        try
        {
            doorOpen=dO;
            doorType0=Toolkit.getDefaultToolkit().getImage("resources/Door0.png");
            doorType1=Toolkit.getDefaultToolkit().getImage("resources/Door1.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }   

    /**
     * Draws all doors in the door list in the specified Graphics context.
     * @param window the specified Graphics context.
     * @author Richard Dong
     */
    @Override
    public void draw(Graphics window)
    {
        try
        {
            if(getType()==0)
            {
                window.drawImage(doorType0,getXPos(),getYPos(),getLength(),getWidth(),null);
            }
            else if(getType()==1)
            {
                window.drawImage(doorType1,getXPos(),getYPos(),getLength(),getWidth(),null);
            }
            if(doorOpen!=null)
            {
                doorOpen.draw(window);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Returns the corresponding doorOpener object.
     * @return doorOpen - the doorOpener object.
     * @author Richard Dong
     */
    public DoorOpener getOpener()
    {
        try
        {
            return doorOpen;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return null;
    }
}
