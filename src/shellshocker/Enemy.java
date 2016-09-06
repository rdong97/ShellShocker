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
 * Creates enemy objects that have patrol areas of the game.<link>
 * The enemies serve the purpose of impeding progress for the user-based character
 * and can be destroyed.
 * @author Richard Dong
 */
public class Enemy extends EnemySprite
{   
    private Image enemyW,enemyE;    
    
    /**
     * Constructor for the enemy object.<link> Each enemy object has attributes of
     * enemy type, x and y coordinates for location, horizontal and vertical speed vectors,
     * and a default health value.
     * @param t 
     * @param x the x-coordinate of the location of the enemy.
     * @param y the y-coordinate of the location of the enemy. 
     * @param xSpd the horizontal vector of the speed of the enemy.
     * @param ySpd the vertical vector of the speed of the enemy.
     * @param h 
     * @author Richard Dong
     */
    public Enemy(int t, int x, int y, int xSpd, int ySpd,int h)
    {
        super(t,x,y,xSpd,ySpd,50,50,h,false,0,100);
        try
        {
            enemyW=Toolkit.getDefaultToolkit().getImage("resources/EnemyW.png");
            enemyE=Toolkit.getDefaultToolkit().getImage("resources/EnemyE.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Draws the enemy objects in the specified Graphics context.
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
                    window.drawImage(enemyW, getXPos(),getYPos(),getLength(),getWidth(), null);
                }
                else if(getType()==1)
                {
                    window.drawImage(enemyE, getXPos(),getYPos(),getLength(),getWidth(), null);
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
     * Finds the next location of the enemy object based on current location and speed.
     * @author Richard Dong
     */
    @Override
    public void findNextLoc()
    {
        try
        {
            if(getActive())
            {
                setXPos(getXPos()+getXSpd());
                setYPos(getYPos()+getYSpd());
                if(getXSpd()>0)
                {
                    setType(1);
                }
                if(getXSpd()<0)
                {
                    setType(0);
                }
                setTimeCount(getTimeCount()+1);
            }
            
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
     
                        
   /**
    * Fires the weapons of the enemy based on the location of the user.
    * @param w The Weapon object.
    * @param r The user-controlled character. 
    * @author Richard Dong
    */
    @Override
    public void fireWeapons(Weapon w,Ragnar r)
    {
        try
        {
            if(getTimeCount()%150==0)
            {
                int weaponCount=2;
                int currentWeapon=0;
                if(getXPos()<r.getXPos()&&getYPos()<r.getYPos())
                {
                    MusicPlayer.startLaserSound();
                    for(double radians=0;radians<=Math.PI*0.5;radians+=Math.PI/2/weaponCount)
                    {
                        int speed=10;
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
                    for(double radians=Math.PI*1.5;radians<=2*Math.PI;radians+=Math.PI/2/weaponCount)
                    {
                        int speed=10;
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
                    for(double radians=Math.PI*0.5;radians<=Math.PI;radians+=Math.PI/2/weaponCount)
                    {
                        int speed=10;
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
                    for(double radians=Math.PI;radians<=1.5*Math.PI;radians+=Math.PI/2/weaponCount)
                    {
                        int speed=10;
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
