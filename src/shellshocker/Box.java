/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Creates box objects.<link> When the user-controlled character comes in contact
 * with a box object, attributes of the box determine the effect, which can be
 * increases in points, health, shield, bombs, fuel, mines, and ammo.
 * @author Richard Dong
 */
public class Box implements Location
{
    private Image healthBox,ammoBox,bombBox,fuelBox,pointBox,shieldBox,mineBox;
    private int xPos,yPos,boxType,boxLength,boxWidth;


    /**
     * Constructor for the box objects, which have attributes of type and location
     * @param t type of box.
     * @param x x-coordinate of the location of the box.
     * @param y y-coordinate of the location of the box.
     */
    public Box(int t,int x,int y)
    {
        try
        {
            boxType=t;
            xPos=x;
            yPos=y;
            healthBox=Toolkit.getDefaultToolkit().getImage("resources/HealthBox.png");
            ammoBox=Toolkit.getDefaultToolkit().getImage("resources/AmmoBox.png");
            bombBox=Toolkit.getDefaultToolkit().getImage("resources/BombBox.png");
            fuelBox=Toolkit.getDefaultToolkit().getImage("resources/FuelBox.png");
            pointBox=Toolkit.getDefaultToolkit().getImage("resources/PointBox.png");
            shieldBox=Toolkit.getDefaultToolkit().getImage("resources/ShieldBox.png");
            mineBox=Toolkit.getDefaultToolkit().getImage("resources/MineBox.png");
            boxLength=100;
            boxWidth=100;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }

    public int getType()
    {
        try
        {
            return boxType;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }
    
    /**
     * Returns the x-coordinate of the location of the box object.
     * @return xCoor - the x-coordinate of the location of the box object.
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
     * Returns the y-coordinate of the location of the box object.
     * @return yCoor - the y-coordinate of the location of the box object.
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
     * Returns the horizontal length of the box object.
     * @return boxLength - the horizontal length of the box object.
     * @author Richard Dong
     */
    @Override
    public int getLength()
    {
        try
        {
            return boxLength;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }
    
    /**
     * Returns the vertical width of the box object.
     * @return boxWidth - the vertical width of the box object.
     * @author Richard Dong
     */
    @Override
    public int getWidth()
    {
        try
        {
            return boxWidth;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }
    
//0:point 1:health 2:ammo 3:fuel 4:shield 5:bomb 6:mine
    
    /**
     * Draws all boxes in the specified Graphics context from the list of active boxes.
     * @param window the specified Graphics context.
     * @author Richard Dong
     */
    @Override
    public void draw(Graphics window)
    {  
        try
        {
            Image toDraw=null;
            if(getType()==0)
            {
                toDraw=pointBox;
            }
            else if(getType()==1)
            {
                toDraw=healthBox;
            }
            else if(getType()==2)
            {
                toDraw=ammoBox;
            }
            else if(getType()==3)
            {
                toDraw=fuelBox;
            }
            else if(getType()==4)
            {
                toDraw=shieldBox;
            }
            else if(getType()==5)
            {
                toDraw=bombBox;
            }
            else if(getType()==6)
            {
                toDraw=mineBox;
            }
            window.drawImage(toDraw,getXPos(),getYPos(),getLength(),getWidth(),null);  
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }  
}
