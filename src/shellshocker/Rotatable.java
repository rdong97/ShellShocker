/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Interface that, when implemented, creates methods that relate to rotating objects
 * in the coordinate plane.<link> These objects will have a position, rotation, and dimensions.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public interface Rotatable 
{
    
    /**
     * Returns an image that has been rotated the specified amount of radians.
     * @param rotationRequired the amount to rotate the object image.
     * @return a BufferedImage of the rotated image.
     * @author Richard Dong
     */
    public BufferedImage rotateImage(double rotationRequired);
    
    /**
     * Draws the rotated image in the specified Graphics context.
     * @param window The specified Graphics context.
     * @author Richard Dong
     */
    public void draw(Graphics window);
    
    /**
     * Returns the x-coordinate where the object image is drawn.
     * @return the x-coordinate where the object image is drawn.
     * @author Richard Dong
     */
    public double getDrawX();
    
    /**
     * Returns the y-coordinate where the object image is drawn.
     * @return the y-coordinate where the object image is drawn.
     * @author Richard Dong
     */
    public double getDrawY();
    
    /**
     * Returns the x-coordinate reference point where the object is located.
     * @return the x-coordinate where the object is located.
     * @author Richard Dong
     */
    public double getXPos();
    
    /**
     * Returns the y-coordinate reference point where the object is located.
     * @return the y-coordinate where the object is located.
     * @author Richard Dong
     */
    public double getYPos();
    
    /**
     * Sets the x-coordinate reference point to the passed parameter.
     * @param x the x-coordinate to set to.
     * @author Richard Dong
     */
    public void setXPos(double x);
    
    /**
     * Sets the y-coordinate reference point to the passed parameter.
     * @param y the y-coordinate to set to.
     * @author Richard Dong
     */
    public void setYPos(double y);
    
    /**
     * Sets the x-coordinate drawing location to the passed parameter.
     * @param x the x-coordinate to set to.
     * @author Richard Dong
     */
    public void setDrawX(double x);
    
    /**
     * Sets the y-coordinate drawing location to the passed parameter.
     * @param y the y-coordinate to set to.
     * @author Richard Dong
     */
    public void setDrawY(double y);
}
