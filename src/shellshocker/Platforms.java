/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


/**
 * Creates platform objects that the user, enemies, and boxes can exist 
 * without falling to areas of the game map.<link> Platform objects have
 * attributes of location.
 * @author Richard Dong
 * @version 1.0. Last edited 12/14/2013
 */
public class Platforms extends Boundary
{
    private Image platform0,platform1,platform2; 
    
  
    /**
     * Creates a platform object with the attributes of type and position.
     * @param t type of platform.
     * @param x x-coordinate of the location of the platform.
     * @param y y-coordinate of the location of the platform.
     * @author Richard Dong
     */
    public Platforms(int t,int x,int y)
    {
        super(t,x,y,400,40);
        try
        {
            platform0=Toolkit.getDefaultToolkit().getImage("resources/Platform0.png");
            platform1=Toolkit.getDefaultToolkit().getImage("resources/Platform1.png");
            platform2=Toolkit.getDefaultToolkit().getImage("resources/Platform2.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
   
    /**
     * Draws all platforms in the platform list in the specified Graphics context.
     * @param window the specified Graphics context.
     * @author Richard Dong
     */
    public void draw(Graphics window)
    {
        try
        {
            if(getType()==0)
            {
                window.drawImage(platform0,getXPos(),getYPos(),getLength(),getWidth(),null);
            }
            else if(getType()==1)
            {
                window.drawImage(platform1,getXPos(),getYPos(),getLength(),getWidth(),null);
            }
            else if(getType()==2)
            {
                window.drawImage(platform2,getXPos(),getYPos(),getLength(),getWidth(),null);
        } 
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }    
    }
}
