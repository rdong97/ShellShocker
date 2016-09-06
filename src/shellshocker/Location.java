/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;

/**
 * Interface that, when implemented, creates methods that relate to objects on 
 * a coordinate plane.<link> These objects will have a position and dimensions.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public interface Location 
{
    /**
     * Returns the x-coordinate of the location of the object.
     * @return an integer that represents the x-coordinate of the object.
     * @author Richard Dong
     */
    public int getXPos();
    
    /**
     * Returns the y-coordinate of the location of the object.
     * @return an integer that represents the y-coordinate of the object.
     * @author Richard Dong
     */
    public int getYPos();
    
    
    /**
     * Returns the vertical width of the size of the object.
     * @return an integer that represents the x-coordinate of the object.
     * @author Richard Dong
     */
    public int getWidth();
    
    /**
     * Returns the horizontal length of the size of the object.
     * @return an integer that represents the x-coordinate of the object.
     * @author Richard Dong
     */
    public int getLength();
    
    /**
     * Draws the object in the specified Graphics context.
     * @param window the specified Graphics context.
     * @author Richard Dong
     */
    public void draw(Graphics window);
}
