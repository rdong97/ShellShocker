/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Creates objects that simulate explosive weapons.<link> Weapons will be
 * able to change location, speed, and direction.<link> Projectiles that
 * the weapons fire include lasers, bullets, shells, and missiles that will
 * inflict damage to enemies and the user-controlled character.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2013
 */
public class Weapon implements Rotatable
{
    private int weaponType;
    private double xPos,yPos, drawX, drawY, xSpeed, ySpeed,weaponRotate;
    private Image laser, bullet, shell;
    private ArrayList<Weapon>weaponList = new ArrayList<>();
    private BufferedImage toDraw;
    private BufferedImage l0,l45,l90,l135,l180,l225,l270,l315;
    
    /**
     * Default constructor that initializes lists.<link> Also takes in images
     * for the different weapons.
     * @author Richard Dong
     */
    public Weapon()
    {
        try
        {
            laser=Toolkit.getDefaultToolkit().getImage("resources/Laser.png");
            bullet=Toolkit.getDefaultToolkit().getImage("resources/Bullet.png");
            shell=Toolkit.getDefaultToolkit().getImage("resources/Shell.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Constructor creates a weapon object with variables of weapon type, 
     * x and y coordinates for location, x and y vectors for speed, and a rotation
     * constant.
     * @param type the weapon type as designated by an integer.
     * @param x the weapon's x-coordinate location.
     * @param y the weapon's y-coordinate location.
     * @param dX the weapon's drawing x-coordinate location.
     * @param dY the weapon's drawing y-coordinate location.
     * @param xSpd the weapon's horizontal speed vector.
     * @param ySpd the weapon's vertical speed vector.
     * @param rotate the weapon's rotation constant.
     * @param img 
     * @author Richard Dong
     */
    public Weapon(int type, double x, double y, double dX, double dY, double xSpd, double ySpd, double rotate,BufferedImage img)
    {
        try
        {
            weaponType=type;
            xPos=x;
            yPos=y;
            drawX=dX;
            drawY=dY;
            xSpeed=xSpd;
            ySpeed=ySpd;
            weaponRotate=rotate;
            toDraw=img;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }     
    }
    
    /**
     * Draws all currently active weapons in the specified Graphics context.
     * @param window the specified Graphics context.
     * @author Richard Dong
     */
    @Override
    public void draw(Graphics window)
    {
        try
        {
            update();
            for(Weapon w:weaponList)
            {
                window.drawImage(w.getToDraw(),(int)w.getDrawX(),(int)w.getDrawY(),null);    
            }  
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        
    }
    
    /**
     * Returns the correct weapon BufferedImage based on rotation.
     * @param rotation the rotation parameter for finding the correct weapon.
     * @return a BufferedImage of correct rotation.
     * @author Richard Dong
     */
    public BufferedImage getCorrect(double rotation)
    {
        try
        {
            if(rotation==0)
            {
                return l0;     
            }
            else if(rotation==Math.PI/4)
            {
                return l45;
            }
            else if(rotation==Math.PI/2)
            {
                return l90;
            }
            else if(rotation==Math.PI*0.75)
            {
                return l135;
            }
            else if(rotation==Math.PI)
            {
                return l180;
            }
            else if(rotation==Math.PI*1.25)
            {
                return l225;
            }
            else if(rotation==Math.PI*1.5)
            {
                return l270;
            }
            else if(rotation==Math.PI*1.75)
            {
                return l315;
            }
            else if(rotation==Math.PI*2)
            {
                return l0;
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return null;
    }
    
    /**
     * Generates common rotated images.
     * @author Richard Dong
     */
    public void generateCommonImages()
    {
        try
        {
            for(double rotationRequired=-Math.PI/4;rotationRequired<=2*Math.PI;rotationRequired+=Math.PI/4)
            {
                BufferedImage image=new BufferedImage(200,200,BufferedImage.TYPE_INT_ARGB);
                image.getGraphics().drawImage(laser,100,95,null);
                double locationX = image.getWidth() / 2;
                double locationY = image.getHeight() / 2;
                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
                if(rotationRequired==2*Math.PI)
                {
                    l0=op.filter(image,null);
                }
                else if(rotationRequired==Math.PI/4)
                {
                    l45=op.filter(image,null);
                }
                else if(rotationRequired==Math.PI/2)
                {
                    l90=op.filter(image,null);
                }
                else if(rotationRequired==Math.PI*0.75)
                {
                    l135=op.filter(image,null);
                }
                else if(rotationRequired==Math.PI)
                {
                    l180=op.filter(image,null);
                }
                else if(rotationRequired==Math.PI*1.25)
                {
                    l225=op.filter(image,null);
                }
                else if(rotationRequired==Math.PI*1.5)
                {
                    l270=op.filter(image,null);
                }
                else if(rotationRequired==Math.PI*1.75)
                {
                    l315=op.filter(image,null);
                }
            }      
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Returns the BufferedImage of the weapon.
     * @return a BufferedImage of the weapon object.
     */
    public BufferedImage getToDraw()
    {       
        try
        {
            return toDraw;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return null;
    }
    /**
     * Rotates an image by a specified amount.
     * @param rotationRequired the amount to rotate the image.
     * @return temp - the rotated image.
     * @author Richard Dong
     */
    @Override
    public BufferedImage rotateImage(double rotationRequired)
    {
        try
        {
            BufferedImage image=new BufferedImage(200,200,BufferedImage.TYPE_INT_ARGB);
            if(Ragnar.getLevel()>2)
            {
                image.getGraphics().drawImage(shell,100,95,null);
            }
            else
            {
                image.getGraphics().drawImage(bullet,100,95,null);
            }
            
            double locationX = image.getWidth() / 2;
            double locationY = image.getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            return op.filter(image,null);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return null;
    }
    
    /**
     * Returns the weapon type.
     * @return weaponType - the type of weapon: shell, laser, missile, etc.
     * @author Richard Dong
     */
    public int getType()
    {
        try
        {
            return weaponType;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    
    /**
     * Returns the weapon's x-coordinate of the position.
     * @return xCoor - the x-coordinate of the position of the weapon.
     * @author Richard Dong
     */
    @Override
    public double getXPos()
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
     * Returns the weapon's y-coordinate of the position.
     * @return yCoor - the y-coordinate of the position of the weapon.
     * @author Richard Dong
     */
    @Override
    public double getYPos()
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
     * Returns the weapon's drawing x-coordinate.
     * @return drawX - the x-coordinate which the weapon is drawn.
     */
    @Override
    public double getDrawX()
    {
        try
        {
            return drawX;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    
    /**
     * Returns the weapon's drawing y-coordinate.
     * @return drawY - the y-coordinate which the weapon is drawn.
     */
    @Override
    public double getDrawY()
    {
        try
        {
            return drawY;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    
    /**
     * Returns the weapon's horizontal vector of speed.
     * @return xSpeed - the horizontal vector of the speed of the weapon.
     * @author Richard Dong
     */
    public double getXSpd()
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
     * Returns the weapon's vertical vector of speed.
     * @return ySpeed - the vertical vector of the speed of the weapon.
     * @author Richard Dong
     */
    public double getYSpd()
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
     * Returns the weapon's rotation constant.
     * @return weaponRotate - the rotation constant of the weapon.
     * @author Richard Dong
     */
    public double getRotate()
    {
        try
        {
            return weaponRotate;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    
    /**
     * Returns the list of currently active weapons.
     * @return weaponList - the list of currently active weapons.
     * @author Richard Dong
     */
    public ArrayList<Weapon> getWeaponList()
    {
        return weaponList;
    }
    
    /**
     * Sets the x-coordinate of the position of the weapon to the passed parameter.
     * @param x the x-coordinate the position is to be set to.
     * @author Richard Dong
     */
    @Override
    public void setXPos(double x)
    {
        xPos=x;
    }
    
    /**
     * Sets the y-coordinate of the position of the weapon to the passed parameter.
     * @param y the y-coordinate the position is to be set to.
     * @author Richard Dong
     */
    @Override
    public void setYPos(double y)
    {
        yPos=y;
    }
    
    /**
     * Sets the drawing x-coordinate of the object to the passed parameter.
     * @param x the parameter which the x-coordinate will be set to.
     * @author Richard Dong
     */
    @Override
    public void setDrawX(double x)
    {
        drawX=x;
    }
    
    /**
     * Sets the drawing y-coordinate of the object to the passed parameter.
     * @param y the parameter which the y-coordinate will be set to.
     * @author Richard Dong
     */
    @Override
    public void setDrawY(double y)
    {
        drawY=y;
    }
    /**
     * Sets the horizontal vector of the speed of the weapon to the passed parameter.
     * @param x the horizontal vector of the speed is to be set to.
     * @author Richard Dong
     */
    public void setXSpd(double x)
    {
        xSpeed=x;
    }
    
    /**
     * Sets the vertical vector of the speed of the weapon to the passed parameter.
     * @param y the vertical vector of the speed is to be set to.
     * @author Richard Dong
     */
    public void setYSpd(double y)
    {
        ySpeed=y;
    } 
    /**
     * Updates the drawing and reference locations of each weapon.
     * @author Richard Dong
     */
    public void update()
    {
        for(Weapon w:weaponList)
        {
            w.setXPos(w.getXPos()+w.getXSpd());
            w.setYPos(w.getYPos()+w.getYSpd());
            w.setDrawX(w.getDrawX()+w.getXSpd());
            w.setDrawY(w.getDrawY()+w.getYSpd());
        }
    } 
}