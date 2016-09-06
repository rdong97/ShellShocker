/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Creates a flamethrower weapon that the user-controlled character can use
 * to damage enemy objects.<link> The flamethrower has attributes of 
 * location, size, and rotation.
 * @author Richard Dong
 */
public class Flamethrower extends Point implements Rotatable
{
    private Image flame0,flame1,flame2,flame3,smallFlame;
    private double baseX, baseY,rotation,flameSize;
    private boolean active,activateSound;
    private int timeCount;
    private ArrayList<Point>hitPointList=new ArrayList<>();
    
    /**
     * Default constructor for the flamethrower object.<link> Takes in images
     * for animation.
     * @author Richard Dong
     */
    public Flamethrower()
    {
        try
        {
            flame0=Toolkit.getDefaultToolkit().getImage("resources/Flame0.png");
            flame1=Toolkit.getDefaultToolkit().getImage("resources/Flame1.png");
            flame2=Toolkit.getDefaultToolkit().getImage("resources/Flame2.png");
            flame3=Toolkit.getDefaultToolkit().getImage("resources/Flame3.png");
            smallFlame=Toolkit.getDefaultToolkit().getImage("resources/SmallFlame.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Creates a flamethrower object with attributes of location, size, and rotation.
     * @param x the x-coordinate of the flamethrower object.
     * @param y the y-coordinate of the flamethrower object.
     * @param size the size of the flamethrower object.
     * @author Richard Dong
     */
    public Flamethrower(int x, int y, int size)
    {
        try
        {
            baseX=x;
            baseY=y;
            flameSize=size;
            active=true;
            activateSound=false;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    /**
     * Rotates the flamethrower image by the specified rotation parameter
     * @param rotation the amount to rotate the image.
     * @return a BufferedImage that has been rotated to the specified amount.
     * @author Richard Dong
     */
    @Override
    public BufferedImage rotateImage(double rotation)
    {
        try
        {
            if(active&&!activateSound)
            {
                MusicPlayer.startFlamethrower();
                activateSound=true;
            }
            if(!active&&activateSound==true)
            {
                MusicPlayer.stopFlamethrower();
                activateSound=false;
            }
            BufferedImage image=new BufferedImage(250,140,BufferedImage.TYPE_INT_ARGB);
            if(active==false)
            {
                image.getGraphics().drawImage(smallFlame,150,130,null);
            }
            else if(timeCount/4==0)
            {
                image.getGraphics().drawImage(flame0,150,130,null);
            }
            else if(timeCount/4==1)
            {
                image.getGraphics().drawImage(flame1,150,130,null);
            }
            else if(timeCount/4==2)
            {
                image.getGraphics().drawImage(flame2,150,130,null);
            }
            else if(timeCount/4==3)
            {
                image.getGraphics().drawImage(flame3,150,130,null);
            }

            double locationX = image.getWidth() / 2;
            double locationY = 133;
            AffineTransform tx = AffineTransform.getRotateInstance(getRotation(), locationX, locationY);
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
     * Draws the flamethrower object in the specified Graphics context.
     * @param window the specified Graphics context
     * @author Richard Dong
     */
    @Override
    public void draw(Graphics window)
    {
        try
        {
            timeCount++;
            if(timeCount>=16)
            {
                timeCount=8;
            }
            window.drawImage(rotateImage(getRotation()),(int) baseX-200,(int) baseY-220,(int) baseX+300,(int) baseY+60,0,0,250,140, null);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        

    }
    /**
     * Returns an ArrayList of points that comprise of the damage area of the flamethrower.
     * @return hitPointList - the list of points in the damage zone.
     * @author Richard Dong
     */
    public ArrayList<Point> getHitPoints()
    {
        try
        {
            generateHitPoints();
            return hitPointList;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return null;
    }
    /**
     * Creates an ArrayList of hit points of the flamethrower.
     * @author Richard Dong
     */
    public void generateHitPoints()
    {
        try
        {
            hitPointList.clear();
            for(int index=4; index<=20; index++)
            {
                int factor=index*12+10;
                int xCoor=(int)getXPos()+52+(int)(factor*Math.cos(getRotation()));
                int yCoor=(int)getYPos()+52+(int)(factor*Math.sin(getRotation()));
                hitPointList.add(new Point(xCoor,yCoor));
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }   
    }
    
    /**
     * Returns the x-coordinate reference point of the flamethrower object.
     * @return baseX - the x-coordinate of the reference point.
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
     * Returns the y-coordinate reference point of the flamethrower object.
     * @return baseY - the y-coordinate of the reference point.
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
     * Returns the rotation of the flamethrower object.
     * @return rotation - the rotation of the object.
     * @author Richard Dong
     */
    public double getRotation()
    {
        try
        {
            return rotation;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        return 0;
    }
    
    /**
     * Sets the state of the flamethrower object.
     * @param b the value to set the state to.
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
     * Returns the state of the flamethrower object.
     * @return active - the state of the reference point.
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
     * Updates the location and rotation of the flamethrower object.
     * @param x the reference x-coordinate of the flamethrower object.
     * @param y the reference y-coordinate of the flamethrower object.
     * @param r the rotation of the flamethrower object.
     * @author Richard Dong
     */
    public void update(int x, int y,double r)
    {
        try
        {
            baseX=x;
            baseY=y;
            rotation=r;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }


    /**
     * Returns the x-coordinate where the object image is drawn.
     * @return the x-coordinate where the object image is drawn.
     * @author Richard Dong
     */
    @Override
    public double getDrawX() {
        return baseX-200;
    }
    
    /**
     * Returns the y-coordinate where the object image is drawn.
     * @return the y-coordinate where the object image is drawn.
     * @author Richard Dong
     */
    @Override
    public double getDrawY() {
        return baseY-220;
    }

    /**
     * Sets the x-coordinate reference point where the object is located.
     * @param x the x-coordinate reference point.
     * @author Richard Dong
     */
    @Override
    public void setXPos(double x) {
        baseX=x;
    }

    /**
     * Sets the y-coordinate reference point where the object is located.
     * @param y the y-coordinate reference point.
     * @author Richard Dong
     */
    @Override
    public void setYPos(double y) {
        baseY=y;
    }

    /**
     * Sets the x-coordinate drawing location to the passed parameter.
     * @param x the x-coordinate to set to.
     * @author Richard Dong
     */
    @Override
    public void setDrawX(double x) {}

    /**
     * Sets the y-coordinate drawing location to the passed parameter.
     * @param y the y-coordinate to set to.
     * @author Richard Dong
     */
    @Override
    public void setDrawY(double y) {}
}
