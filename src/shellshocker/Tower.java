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
 * Creates tower objects.<link> The tower objects have attributes of location.<link>
 * The purpose of the tower is the same as that of an enemy: to impede the 
 * user-controlled character.
 * @author Richard Dong
 */
public class Tower extends EnemySprite
{
    private Image towerN,towerS,towerE,towerW; 
       
    /**
     * Constructor for tower objects that have attributes of location and type.
     * @param t the version of tower. 
     * @param x the x-coordinate of the tower.
     * @param y the y-coordinate of the tower.
     * @param h
     * @author Richard Dong
     */
    public Tower(int t, int x, int y, int h)
    {
        super(t,x,y,0,0,100,100,h,false,0,100);
        try
        {
            towerN=Toolkit.getDefaultToolkit().getImage("resources/TowerN.png");
            towerS=Toolkit.getDefaultToolkit().getImage("resources/TowerS.png");
            towerE=Toolkit.getDefaultToolkit().getImage("resources/TowerE.png");
            towerW=Toolkit.getDefaultToolkit().getImage("resources/TowerW.png");
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        
    }
    /**
     * Draws the Tower objects in the specified Graphics context.
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
                    window.drawImage(towerN,getXPos(),getYPos(),getLength(),getWidth(), null);
                }
                else if(getType()==1)
                {
                    window.drawImage(towerS,getXPos(),getYPos(),getLength(),getWidth(), null);
                }
                else if(getType()==2)
                {
                    window.drawImage(towerE,getXPos(),getYPos(),getLength(),getWidth(), null);
                }
                else if(getType()==3)
                {
                    window.drawImage(towerW,getXPos(),getYPos(),getLength(),getWidth(), null);
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
                int weaponCount=2;
                int currentWeapon=0;
                int x=r.getXPos();
                int y=r.getYPos();
                double start;
                double end;
                if(getType()==0)
                {
                    if(y>getYPos()+getWidth())
                    {
                        MusicPlayer.startLaserSound();
                        if(x<getXPos())
                        {
                            start=Math.PI/2;
                            end=Math.PI;
                        }
                        else
                        {
                            start=0;
                            end=Math.PI/2;

                        }
                        for(double radians=start;radians<=end;radians+=Math.PI/2/weaponCount)
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
                if(getType()==1)
                {
                    if(y<getYPos())
                    {
                        MusicPlayer.startLaserSound();
                        if(x<getXPos())
                        {
                            start=Math.PI;
                            end=1.5*Math.PI;
                        }
                        else
                        {
                            start=1.5*Math.PI;
                            end=2*Math.PI;
                        }
                        for(double radians=start;radians<=end;radians+=Math.PI/2/weaponCount)
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
                if(getType()==2)
                {
                    
                    if(x+r.getLength()<getXPos())
                    {
                        MusicPlayer.startLaserSound();
                        if(y+r.getWidth()>getYPos())
                        {
                            start=Math.PI/2;
                            end=Math.PI;
                        }
                        else
                        {
                            start=Math.PI;
                            end=1.5*Math.PI;
                        }
                        for(double radians=start;radians<=end;radians+=Math.PI/2/weaponCount)
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
                
                if(getType()==3)
                {
                    if(x>getXPos())
                    {
                        MusicPlayer.startLaserSound();
                        if(y+r.getWidth()>getYPos())
                        {
                            start=0;
                            end=Math.PI/2;
                        }
                        else
                        {
                            start=1.5*Math.PI;
                            end=2*Math.PI;
                        }
                        for(double radians=start;radians<=end;radians+=Math.PI/2/weaponCount)
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
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }      
    }


    /**
     *
     */
    @Override
    public void findNextLoc() 
    {
    }
}
