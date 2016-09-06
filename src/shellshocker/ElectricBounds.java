/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Creates objects that the user and enemies cannot pass through.<link> If the
 * user's character makes contact with the objects, health points are lost.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public class ElectricBounds extends Boundary implements Location
{
    private Image bound0,bound1,bound2,bound3;
   
    /**
     * Creates an electric bound object.
     * @param t the type of electric bound.
     * @param x the x-coordinate of the location of the bound.
     * @param y the y-coordinate of the location of the bound.
     * @param length the horizontal length of the bound.
     * @param height the vertical height of the bound.
     */
    public ElectricBounds(int t,int x,int y,int length,int height)
    {
        super(t,x,y,length,height);
        try
        {
            bound0=Toolkit.getDefaultToolkit().getImage("resources/Electric0.png");
            bound1=Toolkit.getDefaultToolkit().getImage("resources/Electric1.png");
            bound2=Toolkit.getDefaultToolkit().getImage("resources/Electric2.png");
            bound3=Toolkit.getDefaultToolkit().getImage("resources/Electric3.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Draws all electric bounds in the bounds list in the specified Graphics context.
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
                window.drawImage(bound0,getXPos(),getYPos(),getLength(),getWidth(),null);
            }
            else if(getType()==1)
            {
                window.drawImage(bound1,getXPos(),getYPos(),getLength(),getWidth(),null);
            }
            else if(getType()==2)
            {
                window.drawImage(bound2,getXPos(),getYPos(),getLength(),getWidth(),null);
            }
            else if(getType()==3)
            {
                window.drawImage(bound3,getXPos(),getYPos(),getLength(),getWidth(),null);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
}
