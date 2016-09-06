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
 * Creates a boss enemy that guards the final level of the ShellShocker game.<link> 
 * The boss enemy differs from other enemies because it has more health, weapons, 
 * and cannot be avoidable in order to complete the mission goal in the game.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014.
 */
public class Boss extends EnemySprite
{
    private Image bossW,bossE,bossWDam,bossEDam;
    
    /**
     * Constructor for creating the Boss object.
     * @param t The type of enemy.
     * @param x The x-coordinate of the location of the enemy.
     * @param y The y-coordinate of the location of the enemy. 
     * @param xSpd The horizontal vector speed of the enemy.
     * @param ySpd The vertical vector speed of the enemy. 
     * @param h The health value of the enemy. 
     * @author Richard Dong
     */
    public Boss(int t,int x, int y, int xSpd, int ySpd, int h)
    {
        super(t,x,y,xSpd,ySpd,500,500,h,false,0,1000);
        try
        {
            bossW=Toolkit.getDefaultToolkit().getImage("resources/BossW.png");
            bossE=Toolkit.getDefaultToolkit().getImage("resources/BossE.png");
            bossWDam=Toolkit.getDefaultToolkit().getImage("resources/BossWDam.png");
            bossEDam=Toolkit.getDefaultToolkit().getImage("resources/BossEDam.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Draws the boss object in the specified Graphics context.
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
                if(getHealth()>100)
                {
                    if(getType()==0)
                    {
                        window.drawImage(bossW, getXPos(),getYPos(),getLength(),getWidth(), null);
                    }
                    else if(getType()==1)
                    {
                        window.drawImage(bossE, getXPos(),getYPos(),getLength(),getWidth(), null);
                    }
                }
                else
                {
                    if(getType()==0)
                    {
                        window.drawImage(bossWDam, getXPos(),getYPos(),getLength(),getWidth(), null);
                    }
                    else if(getType()==1)
                    {
                        window.drawImage(bossEDam, getXPos(),getYPos(),getLength(),getWidth(), null);
                    }
                }
                
                window.drawRect(getXPos(), getYPos()-20,getLength(),5);
                window.fillRect(getXPos(),getYPos()-20, (int)(getHealth()/(double)getHealthLimit()*getLength()),5);
            } 
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
   /**
    * Fires the weapons of the boss based on the location of the user.
    * @param w The Weapon object.
    * @param r The user-controlled character. 
    * @author Richard Dong
    */
    @Override
    public void fireWeapons(Weapon w, Ragnar r) 
    {
        try
        {
            if(getTimeCount()%100==0)
            {
                int weaponCount=4;
                int currentWeapon=0;
                if(getXPos()<r.getXPos()&&getYPos()<r.getYPos())
                {
                    MusicPlayer.startLaserSound();
                    for(double radians=0;radians<=Math.PI*0.5;radians+=Math.PI/weaponCount)
                    {
                        for(int constant=-250;constant<=250;constant+=100)
                        {
                            int speed=15;
                            double xSpd=speed*Math.cos(radians);
                            double ySpd=speed*Math.sin(radians);
                            double dxPos=constant+getXPos()-100+getLength()/2;
                            double dyPos=-constant+getYPos()-100+getWidth()/2;
                            double xPos=dxPos+100+100*Math.cos(radians);
                            double yPos=dyPos+100+100*Math.sin(radians);
                            w.getWeaponList().add(new Weapon(currentWeapon,xPos,yPos,dxPos,dyPos,xSpd,ySpd,radians,w.getCorrect(radians)));
                        } 
                    }
                }
                if(getXPos()<r.getXPos()&&getYPos()>r.getYPos())
                {
                    MusicPlayer.startLaserSound();
                    for(double radians=Math.PI*1.5;radians<=2*Math.PI;radians+=Math.PI/weaponCount)
                    {
                        for(int constant=-250;constant<=250;constant+=100)
                        {
                            int speed=15;
                            double xSpd=speed*Math.cos(radians);
                            double ySpd=speed*Math.sin(radians);
                            double dxPos=constant+getXPos()-100+getLength()/2;
                            double dyPos=constant+getYPos()-100+getWidth()/2;
                            double xPos=dxPos+100+100*Math.cos(radians);
                            double yPos=dyPos+100+100*Math.sin(radians);
                            w.getWeaponList().add(new Weapon(currentWeapon,xPos,yPos,dxPos,dyPos,xSpd,ySpd,radians,w.getCorrect(radians)));
                        }
                    }
                }
                if(getXPos()>r.getXPos()&&getYPos()<r.getYPos())
                {
                    MusicPlayer.startLaserSound();
                    for(double radians=Math.PI*0.5;radians<=Math.PI;radians+=Math.PI/weaponCount)
                    {
                        for(int constant=-250;constant<=250;constant+=100)
                        {
                            int speed=15;
                            double xSpd=speed*Math.cos(radians);
                            double ySpd=speed*Math.sin(radians);
                            double dxPos=constant+getXPos()-100+getLength()/2;
                            double dyPos=constant+getYPos()-100+getWidth()/2;
                            double xPos=dxPos+100+100*Math.cos(radians);
                            double yPos=dyPos+100+100*Math.sin(radians);
                            w.getWeaponList().add(new Weapon(currentWeapon,xPos,yPos,dxPos,dyPos,xSpd,ySpd,radians,w.getCorrect(radians)));
                        }
                    }
                }
                if(getXPos()>r.getXPos()&&getYPos()>r.getYPos())
                {
                    MusicPlayer.startLaserSound();
                    for(double radians=Math.PI;radians<=1.5*Math.PI;radians+=Math.PI/weaponCount)
                    {
                        for(int constant=-250;constant<=250;constant+=100)
                        {
                            int speed=15;
                            double xSpd=speed*Math.cos(radians);
                            double ySpd=speed*Math.sin(radians);
                            double dxPos=constant+getXPos()-100+getLength()/2;
                            double dyPos=-constant+getYPos()-100+getWidth()/2;
                            double xPos=dxPos+100+100*Math.cos(radians);
                            double yPos=dyPos+100+100*Math.sin(radians);                           
                            w.getWeaponList().add(new Weapon(currentWeapon,xPos,yPos,dxPos,dyPos,xSpd,ySpd,radians,w.getCorrect(radians)));
                        }
                    }
                }
            } 
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Adjusts the boss speed based on the location given in the passed parameters.
     * @param x the target x-coordinate of a movement algorithm.
     * @param y the target y-coordinate of a movement algorithm.
     */
    public void track(int x, int y)
    {   
        try
        {
            if(getActive())
            {
                int xDiff=getXPos()+getLength()/2-x;
                int yDiff=getYPos()+getWidth()/2-y;
                if(xDiff<500&&xDiff>0)
                {
                    setXSpd(2);
                }
                else if(xDiff>800)
                {
                    setXSpd(-2);
                }
                if(xDiff>-500&&xDiff<0)
                {
                   
                    setXSpd(-2);
                }
                else if(xDiff<-800)
                {
                    setXSpd(2);
                }
                if(yDiff>-500&&yDiff<0)
                {
                    setYSpd(-2);                         
                }
                else if(yDiff<-800)
                {
                    setYSpd(2);
                }
                
                if(yDiff<500&&yDiff>0)
                {
                    setYSpd(2);
                }
                else if(yDiff>800)
                {
                    setYSpd(-2);
                }    
            }     
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Finds the next location of the enemy object based on current location and speed.
     * @author Richard Dong
     */
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
}
