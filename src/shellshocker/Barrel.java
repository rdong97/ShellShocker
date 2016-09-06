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

/**
 * Implements the rotating barrel that is directed by the mouse of the user and 
 * can be used to direct the user's attacks.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014.
 */
public class Barrel implements Rotatable
{
    private Image barrel;
    private Flamethrower flame;
    private Ragnar ragnar;
    private int mouseX, mouseY;
    private double rotationRequired;
    private double drawLocationX, drawLocationY;
    private double baseX, baseY;
    
    /**
     * Assigns updated variable names to the specified variables. 
     * @author Richard Dong
     * @param f the flamethrower class and its attributes.
     * @param r the location of the user controlled character. 
     */
    public Barrel(Flamethrower f, Ragnar r)
    {
        try
        {
            flame=f;
            ragnar=r;
            barrel=Toolkit.getDefaultToolkit().getImage("resources/Barrel.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Updates the variable names of the specified variables for later use.
     * @param mousex the x-coordinates of the mouse.
     * @param mousey the y-coordinates of the mouse.
     * @param basx the x-coordinates of the barrel at its position.
     * @param basy the x-coordinates of the barrel at its basic position.
     * @author Richard Dong
     */
    public void update(int mousex, int mousey,int basx,int basy)
    {
        try
        {
            mouseX=mousex;
            mouseY=mousey;
            baseX=basx;
            baseY=basy;
            drawLocationX=basx-52;
            drawLocationY=basy-15;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Draws the barrel of the user-controlled character.<link> The barrel will
     * rotate according to the position of the mouse.
     * @param window The specified Graphics context.
     * @author Richard Dong
     */
    @Override
    public void draw(Graphics window)
    {
        try     
        {
            calculateRotate();
            if(rotationRequired!=0)
            {
                window.drawImage(rotateImage(rotationRequired),(int) drawLocationX, (int)drawLocationY, null);
            }

            else
            {
                window.drawImage(barrel,(int)drawLocationX,(int)drawLocationY+45,null);
                rotationRequired=0;
            }  
                flame.update((int)drawLocationX,(int)drawLocationY,rotationRequired);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Calculates the amount that the barrel must rotate in order to match
     * the position of the mouse.
     * @return rotationRequired-The amount of rotation the barrel
     * must make to match the position of the mouse. 
     * @author Richard Dong
     */
    public double calculateRotate()
    {
        try
        {
            double xDiff=mouseX-(ragnar.getXPos()+ragnar.getLength()/2);
            double yDiff=-(mouseY-(ragnar.getYPos()+35));
            if(xDiff!=0)
            {
                rotationRequired=-Math.atan(yDiff/xDiff);
            }
            else
            {
                rotationRequired=-Math.PI/2;     
            }
            if(yDiff>0)
            {
                if(xDiff<0)
                {
                    rotationRequired=rotationRequired+Math.PI;
                }
            }
            else
            { 
                rotationRequired=0;
            } 
            return rotationRequired;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }
    
    /**
     * Transforms the basic image of the barrel into a rotated image of the barrel. 
     * @param rotationRequired The amount of rotation the barrel
     * must make to match the position of the mouse. 
     * @return op.filter(image, null)-The image rotated to the correct position.
     * @author Richard Dong
     */
    @Override
    public BufferedImage rotateImage(double rotationRequired) 
    {
        try
        {
            BufferedImage image=new BufferedImage(104,104,BufferedImage.TYPE_INT_ARGB);
            image.getGraphics().drawImage(barrel,0,47,null);
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
     * Returns the x-coordinate of the location at which the rotated image is drawn.
     * @return drawLocationX-the location which to draw the rotated image.
     * @author Richard Dong
     */
    @Override
    public double getDrawX() 
    {
        try
        {
            return drawLocationX;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }

    /**
     * Returns the y-coordinate of the location at which the rotated image is drawn.
     * @return drawLocationY-the location which to draw the rotated image.
     * @author Richard Dong
     */
    @Override
    public double getDrawY() 
    {
        try
        {
            return drawLocationY;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }
    
    /**
     * Returns the x-coordinate of the location of the base of the barrel.
     * @return baseX-the x-coordinate of the location of the base of the barrel.
     * @author Richard Dong
     */
    @Override
    public double getXPos() 
    {
        try
        {
            return baseX;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }

    /**
     * Returns the y-coordinate of the location of the base of the barrel.
     * @return baseY-the y-coordinate of the location of the base of the barrel.
     * @author Richard Dong
     */
    @Override
    public double getYPos() 
    {
        try
        {
            return baseY;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }

    /**
     * Sets the base x-coordinate equal to the passed parameter.
     * @param x the value the base x-coordinate is to be set to.
     * @author Richard Dong
     */
    @Override
    public void setXPos(double x) 
    {
        try
        {
            baseX=x;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }

    /**
     * Sets the base y-coordinate equal to the passed parameter.
     * @param y the value the base y-coordinate is to be set to.
     * @author Richard Dong
     */
    @Override
    public void setYPos(double y) 
    {
        try
        {
            baseY=y;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Sets the drawing x-coordinate equal to the passed parameter.
     * @param x the value the drawing x-coordinate is to be set to.
     * @author Richard Dong
     */
    @Override
    public void setDrawX(double x) 
    {
        try
        {
            drawLocationX=x;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }

    /**
     * Sets the drawing y-coordinate equal to the passed parameter.
     * @param y the value the drawing y-coordinate is to be set to.
     * @author Richard Dong
     */
    @Override
    public void setDrawY(double y) 
    {
        try
        {
            drawLocationY=y;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
}
