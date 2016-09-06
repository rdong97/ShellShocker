/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Creates a smart enemy object that impedes the movement of the user-controlled 
 * character.<link> Smart enemies are capable of following the main character.
 * @version 1.0 Last edited 12/14/2013
 * @author Richard Dong
 */
public class SmartEnemy extends EnemySprite
{   
    private Image smartE, smartW;

    /**
     * Constructor for creating the smartEnemy object.
     * @param type the version of smartEnemy.
     * @param x the x-coordinate of the location of the object.
     * @param y the y-coordinate of the location of the object.
     * @param h the health status of the object.
     * @author Richard Dong
     */
    public SmartEnemy(int type, int x, int y, int xSpeed, int ySpeed, int h)
    {
        super(type,x,y,xSpeed,ySpeed,100,100,h,false,0,100);
        try
        {
            smartE=Toolkit.getDefaultToolkit().getImage("resources/SmartE.png");
            smartW=Toolkit.getDefaultToolkit().getImage("resources/SmartW.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }

    @Override
    public void findNextLoc()
    {     
        try
        {
            setXPos(getXPos()+getXSpd());
            setYPos(getYPos()+getYSpd());
            if(getXSpd()>=0)
            {
                setType(1);
            }
            else if(getXSpd()<0)
            {
                setType(0);
            }
            setTimeCount(getTimeCount()+1);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }   
    }
    /**
     * Draws all smartEnemy objects in the specified Graphics context.
     * @param window the specified Graphics context.
     * @author Richard Dong
     */
    @Override
    public void draw(Graphics window)
    {
        try
        {
            window.setColor(Color.RED);      
            setTimeCount(getTimeCount()+1);
            if(getActive())
            {
                if(getType()==0)
                {
                    window.drawImage(smartW, getXPos(),getYPos(),getLength(),getWidth(), null);
                }
                else if(getType()==1)
                {
                    window.drawImage(smartE, getXPos(),getYPos(),getLength(),getWidth(), null);
                }

                window.drawRect(getXPos(),getYPos()-20,getLength(),5);
                window.fillRect(getXPos(),getYPos()-20, (int)(getHealth()/(double)getHealthLimit()*getLength()),5);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    public void track(int x, int y)
    {   
        try
        {
            if(getActive())
            {
                int xDiff=getXPos()-x;
                int yDiff=getYPos()-y;
                if(xDiff<0)
                {
                    setXSpd(2);
                }
                else if(xDiff>0)
                {
                    setXSpd(-2);
                }
                else
                {
                    setXSpd(0);
                }
                if(yDiff<0)
                {
                    setYSpd(2);
                }
                else if(yDiff>0)
                {
                    setYSpd(-2);
                }
                else
                {
                    setYSpd(0);
                }
            }     
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     *
     * @param w
     * @param r
     */
    @Override
    public void fireWeapons(Weapon w,Ragnar r)
    {      
        try
        {
            if(getTimeCount()%150==0)
            {
                int weaponCount=4;
                int currentWeapon=0;
                if(getXPos()<r.getXPos()&&getYPos()<r.getYPos())
                {
                    MusicPlayer.startLaserSound();
                    for(double radians=0;radians<=Math.PI*0.5;radians+=Math.PI/weaponCount)
                    {
                        int speed=15;
                        double xSpd=speed*Math.cos(radians);
                        double ySpd=speed*Math.sin(radians);
                        double dxPos=getXPos()-100+getLength()/2;
                        double dyPos=getYPos()-50;
                        double xPos=dxPos+100+100*Math.cos(radians);
                        double yPos=dyPos+100+100*Math.sin(radians);
                        w.getWeaponList().add(new Weapon(currentWeapon,xPos,yPos,dxPos,dyPos,xSpd,ySpd,radians,w.getCorrect(radians)));
                    }
                }
                if(getXPos()<r.getXPos()&&getYPos()>r.getYPos())
                {
                    MusicPlayer.startLaserSound();
                    for(double radians=Math.PI*1.5;radians<=2*Math.PI;radians+=Math.PI/weaponCount)
                    {
                        int speed=15;
                        double xSpd=speed*Math.cos(radians);
                        double ySpd=speed*Math.sin(radians);
                        double dxPos=getXPos()-100+getLength()/2;
                        double dyPos=getYPos()-50;
                        double xPos=dxPos+100+100*Math.cos(radians);
                        double yPos=dyPos+100+100*Math.sin(radians);
                        w.getWeaponList().add(new Weapon(currentWeapon,xPos,yPos,dxPos,dyPos,xSpd,ySpd,radians,w.getCorrect(radians)));
                    }
                }
                if(getXPos()>r.getXPos()&&getYPos()<r.getYPos())
                {
                    MusicPlayer.startLaserSound();
                    for(double radians=Math.PI*0.5;radians<=Math.PI;radians+=Math.PI/weaponCount)
                    {
                        int speed=15;
                        double xSpd=speed*Math.cos(radians);
                        double ySpd=speed*Math.sin(radians);
                        double dxPos=getXPos()-100+getLength()/2;
                        double dyPos=getYPos()-50;
                        double xPos=dxPos+100+100*Math.cos(radians);
                        double yPos=dyPos+100+100*Math.sin(radians);
                        w.getWeaponList().add(new Weapon(currentWeapon,xPos,yPos,dxPos,dyPos,xSpd,ySpd,radians,w.getCorrect(radians)));
                    }
                }
                if(getXPos()>r.getXPos()&&getYPos()>r.getYPos())
                {
                    MusicPlayer.startLaserSound();
                    for(double radians=Math.PI;radians<=1.5*Math.PI;radians+=Math.PI/weaponCount)
                    {
                        int speed=15;
                        double xSpd=speed*Math.cos(radians);
                        double ySpd=speed*Math.sin(radians);
                        double dxPos=getXPos()-100+getLength()/2;
                        double dyPos=getYPos()-50;
                        double xPos=dxPos+100+100*Math.cos(radians);
                        double yPos=dyPos+100+100*Math.sin(radians);
                        w.getWeaponList().add(new Weapon(currentWeapon,xPos,yPos,dxPos,dyPos,xSpd,ySpd,radians,w.getCorrect(radians)));
                    }
                }
            } 
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        
    }   
}