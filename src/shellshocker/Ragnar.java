/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;


import java.awt.Graphics;
import java.awt.*;
import java.util.ArrayList;

/**
 * User-controlled character that has changing attributes.<link> Attributes include
 * location, speed, direction, and information regarding quantities of weapons, health,
 * shield, and bombs.
 * @author Richard Dong
 */
public class Ragnar implements Location
{
    private Image ragnarFacingRightNotOn,ragnarFacingRightOn,ragnarFacingLeftNotOn,ragnarFacingLeftOn;
    private Image shield;
    private int xPos,yPos,xSpd,ySpd;
    private static int level,savedHealth,savedPoints,savedLevel;
    private int version;
    private int ragnarlength=150;
    private int ragnarwidth=80;
    private int maxVel=12;
    private int storedXPos;
    private int storedYPos;
    private static int checkPoint;
    private int healthCount,ammoCount,fuelCount,bombCount,shieldCount,pointCount,mineCount;
    private int healthLimit,ammoLimit,fuelLimit,bombLimit,shieldLimit,pointLimit,mineLimit;
    
    /**
     * Default constructor for the class.<link> Takes in the images of the user-controlled
     * character and sets positional and speed values.
     * @author Richard Dong
     */
    public Ragnar()
    {   
        try
        {
            ragnarFacingRightNotOn=Toolkit.getDefaultToolkit().getImage("resources/TankFaceRightNotOn.png");
            ragnarFacingRightOn=Toolkit.getDefaultToolkit().getImage("resources/TankFaceRightOn.png");
            ragnarFacingLeftNotOn=Toolkit.getDefaultToolkit().getImage("resources/TankFaceLeftNotOn.png");
            ragnarFacingLeftOn=Toolkit.getDefaultToolkit().getImage("resources/TankFaceLeftOn.png");
            shield=Toolkit.getDefaultToolkit().getImage("resources/Shield.png");
            xSpd=0;
            ySpd=0;
            pointCount=0;
            pointLimit=1000;
            ammoLimit=100;
            ammoCount=100;
            shieldCount=10000;
            shieldLimit=10000;
            mineCount=5;
            mineLimit=10;
            bombCount=5;
            bombLimit=10;
            fuelCount=10000;
            fuelLimit=10000;
            healthCount=1000;
            healthLimit=10000;
            level=0;
        }   
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        
    }
    public static int getCheckPoint()
    {
        return checkPoint;
    }
    public void setCheckPoint(int c)
    {
        if(checkPoint==2&&c==3)
        {
            MusicPlayer.startBoss();
            MusicPlayer.stopLevelMusic();
        }
        checkPoint=c;
        savedHealth=healthCount;
        savedPoints=pointCount;
        savedLevel=level;
    }
    public static int getSavedHealth()
    {
        return savedHealth;
    }  
    public static int getSavedPoints()
    {
        return savedPoints;
    }
    public static int getSavedLevel()
    {
        return savedLevel;
    }
    /**
     * Sets the x-coordinate of the position of the character to the passed parameter
     * @param x the value that xPos will be set equal to.
     * @author Richard Dong
     */
    public void setXPos(int x)
    {
        try
        {
            xPos=x;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }       
    }
    
    /**
     * Sets the y-coordinate of the position of the character to the passed parameter.
     * @param y the value that yPos will be set equal to.
     * @author Richard Dong
     */
    public void setYPos(int y)
    {
        try
        {
            yPos=y;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Sets the horizontal vector of the speed of the character to the passed parameter.
     * @param x the value that xSpd will be set equal to.
     * @author Richard Dong
     */
    public void setXSpd(int x)
    {
        try
        {
            xSpd=x;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Sets the vertical vector of the speed of the character to the passed parameter
     * only if the parameter has an absolute value less than the maximum velocity.
     * @param y the value that xSpd will be set equal to.
     * @author Richard Dong
     */
    public void setYSpd(int y)
    {
        try
        {
            if(Math.abs(y)<maxVel)
            {
                ySpd=y;
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        
    } 
    /**
     * Returns the horizontal length of the character.
     * @return ragnarLength the horizontal length of the character.
     * @author Richard Dong
     */
    @Override
    public int getLength()
    {
        try
        {
            return ragnarlength;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Returns the vertical length of the character.
     * @return ragnarLWidth the vertical width of the character.
     * @author Richard Dong
     */
    @Override
    public int getWidth()
    {
        try
        {
            return ragnarwidth;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Returns the x-coordinate of the position of the character.
     * @return xPos the x-coordinate of the position of the character.
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
     * Returns the y-coordinate of the position of the character.
     * @return yPos the y-coordinate of the position of the character.
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
     * Returns the horizontal vector of the speed of the character.
     * @return xSpd the horizontal vector of the speed of the character.
     * @author Richard Dong
     */
    public int getXSpd()
    {
        try
        {
            return xSpd;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Returns the vertical vector of the speed of the character.
     * @return ySpd the vertical vector of the speed of the character.
     * @author Richard Dong
     */
    public int getYSpd()
    {
        try
        {
            return ySpd;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Stores the current position of the character.
     * @author Richard Dong
     */
    public void storePos()
    {
        try
        {
            storedXPos=xPos;
            storedYPos=yPos;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    public int getVersion()
    {
        try
        {
            return version;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    /**
     * Sets the x-coordinate of the position of the character back to the 
     * stored x-coordinate value.
     * @author Richard Dong
     */
    public void revertXPos()
    {
        try
        {
            setXPos(storedXPos);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Sets the y-coordinate of the position of the character back to the 
     * stored y-coordinate value.
     * @author Richard Dong
     */
    public void revertYPos()
    {
        try
        {
            setYPos(storedYPos);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    public void updateVersion(int v)
    {
        try
        {
            version=v;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    /**
     * Draws the character in the specified Graphics context.<link> Different images will be used to simulate the
     * direction of the character.
     * @param window the specified Graphics context.
     * @author Richard Dong
     */
    @Override
    public void draw(Graphics window)
    {
        try
        {
            if(getShield()>1000)
            {
                window.drawImage(shield,xPos-15,yPos-25,ragnarlength+30,ragnarwidth+50,null);
            }
            if(version==0)
            {
                window.drawImage(ragnarFacingRightNotOn, xPos,yPos,ragnarlength,ragnarwidth, null);
            }
            else if(version==1)
            {
                window.drawImage(ragnarFacingRightOn, xPos,yPos,ragnarlength,ragnarwidth, null);
            }
            else if(version==2)
            {
                window.drawImage(ragnarFacingLeftNotOn, xPos,yPos,ragnarlength,ragnarwidth, null);
            }
            else if(version==3)
            {
                window.drawImage(ragnarFacingLeftOn, xPos,yPos,ragnarlength,ragnarwidth, null);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }    
    }
    /**
     * Returns the amount of ammo remaining to the user-controlled character.
     * @return ammoLimit - the amount of ammo remaining to the character.
     * @author Richard Dong
     */
    public int getAmmoLimit()
    {
        try
        {
            return ammoLimit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Returns the amount of health remaining to the user-controlled character.
     * @return healthLimit - the amount of health remaining to the character.
     * @author Richard Dong
     */
    public int getHealthLimit()
    {
        try
        {
            return healthLimit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Returns the amount of points collected by the user-controlled character.
     * @return pointLimit - the amount of points collected by the character.
     * @author Richard Dong
     */
    public int getPointLimit()
    {
        try
        {
            return pointLimit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    public int getMineLimit()
    {
        try
        {
            return mineLimit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    /**
     * Returns the amount of fuel remaining to the user-controlled character.
     * @return fuelLimit - the amount of fuel remaining to the character.
     * @author Richard Dong
     */
    public int getFuelLimit()
    {
        try
        {
            return fuelLimit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Returns the amount of shield remaining to the user-controlled character.
     * @return shieldLimit - the amount of shield remaining to the character.
     * @author Richard Dong
     */
    public int getShieldLimit()
    {
        try
        {
            return shieldLimit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    /**
     * Returns the amount of bombs remaining to the user-controlled character.
     * @return bombLimit - the amount of bombs remaining to the character.
     * @author Richard Dong
     */
    public int getBombLimit()
    {
        try
        {
            return bombLimit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return 0;
    }
    
    
    /**
     * Sets the amount of ammo to the passed parameter value.
     * @param limit the amount that ammoLimit is to be set to.
     * @author Richard Dong
     */
    public void setAmmoLimit(int limit)
    {
        try
        {
            ammoLimit=limit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Sets the amount of health to the passed parameter value.
     * @param limit the amount that healthLimit is to be set to.
     * @author Richard Dong
     */
    
    public void setHealthLimit(int limit)
    {
        try
        {
            healthLimit=limit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Sets the amount of points to the passed parameter value.
     * @param limit the amount that pointLimit is to be set to.
     * @author Richard Dong
     */
    public void setPointLimit(int limit)
    {
        try
        {
            pointLimit=limit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Sets the amount of fuel to the passed parameter value.
     * @param limit the amount that fuelLimit is to be set to.
     * @author Richard Dong
     */
    public void setFuelLimit(int limit)
    {
        try
        {
            fuelLimit=limit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    public void setMineLimit(int limit)
    {
        try
        {
            mineLimit=limit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    /**
     * Sets the amount of shield to the passed parameter value.
     * @param limit the amount that shieldLimit is to be set to.
     * @author Richard Dong
     */
    public void setShieldLimit(int limit)
    {
        try
        {
            shieldLimit=limit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Sets the amount of bombs to the passed parameter value.
     * @param limit the amount that bombLimit is to be set to.
     * @author Richard Dong
     */
    public void setBombLimit(int limit)
    {
        try
        {
            bombLimit=limit;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  bombLimit=limit;
    }
    /**
     * Sets the character's next position based on the current position and speed vectors.
     * @author Richard Dong
     */
    public void setNextPos()
    {
        try
        {
            yPos=yPos+ySpd;
            xPos=xPos+xSpd;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    public void setVersion(int v)
    {
        try
        {
            version=v;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Simulates gravity conditions by increasing the downward vertical speed vector.
     * @author Richard Dong
     */
    public void gravity()
    {
        try
        {
           if(Math.abs(ySpd)<maxVel)
            {
                if((int)(Math.random()*1000)%4==0)
                {
                    ySpd++;
                }
            } 
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }   
    }
    public void setLevel(int l)
    {
        try
        {
            for(int i=0;i<l;i++)
            {
                if(i==1)
                {
                    setPoints(800);
                    setFuel(8000);
                    setShield(8000);
                    setBomb(5);
                    setMine(5);
                    setHealth(8000);
                    setAmmo(80);
                }
                levelUp();
                if(level<4)
                {
                    setPoints((int)(getPoints()*1.2));
                    setFuel((int)(getFuel()*1.2));
                    setShield((int)(getShield()*1.2));
                    setBomb((int)(getBomb()*1.2));
                    setMine((int)(getMine()*1.2));
                    setHealth((int)(getHealth()*1.2));
                    setAmmo((int)(getAmmo()*1.2));
                }  
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    /**
     * Increases the level of the character by one.
     * @author Richard Dong
     */
    public void levelUp()
    {
        try
        {
            if(level<4)
            {
                System.out.println("levelup");
                setFuelLimit((int)(getFuelLimit()*1.2));
                setShieldLimit((int)(getShieldLimit()*1.2));
                setHealthLimit((int)(getHealthLimit()*1.2));
                setAmmoLimit((int)(getAmmoLimit()*1.2));
                setBombLimit((int)(getBombLimit()*1.2));
                setMineLimit((int)(getMineLimit()*1.2));              
            }
            level++;
            setPointLimit((int)(getPointLimit()*1.2));
            MusicPlayer.startLevelUp();
            
        } 
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    public static int getLevel()
    {
        return level;
    }
    /**
     * Decreases the level of the character by one.
     * @author Richard Dong
     */
    public void levelDown()
    {
        try
        {
            if(level>0)
            {
                level--;
                setFuelLimit((int)(getFuelLimit()*0.66));
                setShieldLimit((int)(getShieldLimit()*0.66));
                setHealthLimit((int)(getHealthLimit()*0.66));
                setAmmoLimit((int)(getAmmoLimit()*0.66));
                setBombLimit((int)(getBombLimit()*0.66));
                setMineLimit((int)(getMineLimit()*0.66));
                if(getFuel()>getFuelLimit())
                {
                    setFuel(getFuelLimit());
                }
                if(getShield()>getShieldLimit())
                {
                    setShield(getShieldLimit());
                }
                if(getHealth()>getHealthLimit())
                {
                    setHealth(getHealthLimit());
                }
                if(getBomb()>getBombLimit())
                {
                    setBomb(getBombLimit());
                }
                if(getMine()>getMineLimit())
                {
                    setMine(getMineLimit());
                }
                if(getAmmo()>getAmmoLimit())
                {
                    setAmmo(getAmmoLimit());
                }
                if(getPoints()>getPointLimit())
                {
                    setPoints(getPointLimit());
                }
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Returns the ammoCount of the character.
     * @return ammoCount the quantity of ammunition remaining to the character.
     * @author Richard Dong
     */
    public int getAmmo()
    {
        try
        {
            return ammoCount;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    
    /**
     * Returns the healthCount of the character.
     * @return healthCount the quantity of health remaining to the character.
     * @author Richard Dong
     */
    public int getHealth()
    {
        try
        {
            return healthCount;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    public int getMine()
    {
        try
        {
            return mineCount;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    /**
     * Returns the fuelCount of the character.
     * @return fuelCount the quantity of fuel remaining to the character.
     * @author Richard Dong
     */
    public int getFuel()
    {
        try
        {
            return fuelCount;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    
    /**
     * Returns the bombCount of the character.
     * @return bombCount the quantity of bombs remaining to the character.
     * @author Richard Dong
     */
    public int getBomb()
    {
        try
        {
            return bombCount;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    
    /**
     * Returns the shieldCount of the character.
     * @return shieldCount the quantity of shield remaining to the character.
     * @author Richard Dong
     */
    public int getShield()
    {
        try
        {
            return shieldCount;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    
    /**
     * Returns the pointCount of the character.
     * @return pointCount the quantity of points accumulated to the character.
     * @author Richard Dong
     */
    public int getPoints()
    {
        try
        {
            return pointCount;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    
    /**
     * Sets the ammoCount of the character to the passed parameter.
     * @param count the quantity to set the ammoCount of the character to.
     * @author Richard Dong
     */
    public void setAmmo(int count)
    {
        try
        {
            ammoCount=count;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Sets the healthCount of the character to the passed parameter.
     * @param count the quantity to set the healthCount of the character to.
     * @author Richard Dong
     */
    public void setHealth(int count)
    {
        try
        {
            healthCount=count;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Sets the fuelCount of the character to the passed parameter.
     * @param count the quantity to set the fuelCount of the character to.
     * @author Richard Dong
     */
    public void setFuel(int count)
    {
        try
        {
            fuelCount=count;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Sets the bombCount of the character to the passed parameter.
     * @param count the quantity to set the bombCount of the character to.
     * @author Richard Dong
     */
    public void setBomb(int count)
    {
        try
        {
            bombCount=count;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    public void setMine(int count)
    {
        try
        {
            mineCount=count;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    /**
     * Sets the shieldCount of the character to the passed parameter.
     * @param count the quantity to set the shieldCount of the character to.
     * @author Richard Dong
     */
    public void setShield(int count)
    {
        try
        {
            shieldCount=count;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Sets the pointCount of the character to the passed parameter.
     * @param count the quantity to set the pointCount of the character to.
     * @author Richard Dong
     */
    public void setPoints(int count)
    {
        try
        {
            pointCount=count;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    /**
     * Checks to find whether or not the main character can move to the left.<link>
     * Potential objects that inhibit movement include walls, platforms, doors, and enemies.
     * @return true or false-based on whether the character's movement to the left is blocked or not.
     * @author Richard Dong
     */
    public boolean canMoveLeft(ArrayList<Boundary>boundList,ArrayList<EnemySprite>enemyList,ArrayList<Reactor>reactorList)
    {
        try
        {
            int rx=getXPos();
            int ry=getYPos();
            int rlength=getLength();
            int rwidth=getWidth();
            for(Boundary b:boundList)
            { 
                boolean check=true;
                int bx=b.getXPos();
                int by=b.getYPos();
                int blength=b.getLength();
                int bwidth=b.getWidth();
                if(rx<=bx+blength&&rx>bx)
                {
                    if(by>ry&&by+bwidth<ry+rwidth)    // C=
                    {
                        check=false;
                    }
                    else if(by<ry+rwidth&&by+bwidth>ry+rwidth)  // C-
                    {
                        check=false;
                    }
                    else if(by+bwidth>=ry+rwidth&&by<=ry)    //c]
                    {
                        check=false;
                    }
                    else if(by+bwidth>ry&&by+bwidth<ry+rwidth)  // c=
                    {
                        check=false;
                    }
                 }
                if(check==false)
                {

                    if(b instanceof ElectricBounds)
                    {
                        electricDeduct();
                    }

                    return false;
                }
            }

            for(EnemySprite e:enemyList)
            {
                int ex=e.getXPos();
                int ey=e.getYPos();
                int elength=e.getLength();
                int ewidth=e.getWidth();
                if(rx<=ex+elength&&rx>ex)
                {
                    if(ey>ry&&ey+ewidth<ry+rwidth)    // C=
                    {
                        return false;
                    }
                    else if(ey<ry+rwidth&&ey+ewidth>ry+rwidth)  // C_
                    {
                        return false;
                    }
                    else if(ey+ewidth>=ry+rwidth&&ey<=ry)    //c]
                    {
                        return false;
                    }
                    else if(ey+ewidth>ry&&ey+ewidth<ry+rwidth)  // c=
                    {
                        return false;
                    }
                }
            }
            for(Reactor r:reactorList)
            {
                int ex=r.getXPos();
                int ey=r.getYPos();
                int elength=r.getLength();
                int ewidth=r.getWidth();
                if(rx<=ex+elength&&rx>ex)
                {
                    if(ey>ry&&ey+ewidth<ry+rwidth)    // C=
                    {
                        return false;
                    }
                    else if(ey<ry+rwidth&&ey+ewidth>ry+rwidth)  // C_
                    {
                        return false;
                    }
                    else if(ey+ewidth>=ry+rwidth&&ey<=ry)    //c]
                    {
                        return false;
                    }
                    else if(ey+ewidth>ry&&ey+ewidth<ry+rwidth)  // c=
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return false; 
    }
    public boolean canMoveRight(ArrayList<Boundary>boundList,ArrayList<EnemySprite>enemyList,ArrayList<Reactor>reactorList)
    {
        try
        {
            int rx=getXPos();
            int ry=getYPos();
            int rlength=getLength();
            int rwidth=getWidth();
            for(Boundary b:boundList)
            { 
                boolean check=true;
                int bx=b.getXPos();
                int by=b.getYPos();
                int blength=b.getLength();
                int bwidth=b.getWidth();

                if(rx+rlength>=bx&&rx+rlength<bx+blength)
                {
                    if(by>ry&&by+bwidth<ry+rwidth)    // C=
                    {
                        check=false;
                    }
                    else if(by<ry+rwidth&&by>ry)  // C-
                    {
                        check=false;
                    }
                    else if(by+bwidth>=ry+rwidth&&by<=ry)    //c]
                    {
                        check=false;
                    }
                    else if(by+bwidth>ry&&by+bwidth<ry+rwidth)  // c=
                    {
                        check=false;
                    }
                }

                if(check==false)
                {
                    if(b instanceof ElectricBounds)
                    {
                        electricDeduct();
                    }
                    return false;
                }
            }

            for(EnemySprite e:enemyList)
            {
                int ex=e.getXPos();
                int ey=e.getYPos();
                int elength=e.getLength();
                int ewidth=e.getWidth();

                 if(rx+rlength>=ex&&rx+rlength<ex+elength)
                {
                    if(ey>ry&&ey+ewidth<ry+rwidth)    // C=
                    {                  
                        return false;
                    }
                    else if(ey<ry+rwidth&&ey>ry)  // C_
                    {                 
                        return false;
                    }
                    else if(ey+ewidth>=ry+rwidth&&ey<=ry)    //c]
                    {                   
                        return false;
                    }
                    else if(ey+ewidth>ry&&ey+ewidth<ry+rwidth)  // c=
                    {                   
                        return false;
                    }
                }
            }
            for(Reactor r:reactorList)
            {
                int ex=r.getXPos();
                int ey=r.getYPos();
                int elength=r.getLength();
                int ewidth=r.getWidth();

                 if(rx+rlength>=ex&&rx+rlength<ex+elength)
                {
                    if(ey>ry&&ey+ewidth<ry+rwidth)    // C=
                    {                  
                        return false;
                    }
                    else if(ey<ry+rwidth&&ey>ry)  // C_
                    {                 
                        return false;
                    }
                    else if(ey+ewidth>=ry+rwidth&&ey<=ry)    //c]
                    {                   
                        return false;
                    }
                    else if(ey+ewidth>ry&&ey+ewidth<ry+rwidth)  // c=
                    {                   
                        return false;
                    }
                }
            }
            return true;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return false;
    }
    
    public boolean canMoveUp(ArrayList<Boundary>boundList,ArrayList<EnemySprite>enemyList,ArrayList<Reactor>reactorList)
    {
        try
        {
            int rx=getXPos();
            int ry=getYPos();
            int rlength=getLength();
            int rwidth=getWidth();
            for(Boundary b:boundList)
            { 
                boolean check=true;
                int bx=b.getXPos();
                int by=b.getYPos();
                int blength=b.getLength();
                int bwidth=b.getWidth();

                if(!(b instanceof Platforms))
                {
                    if(ry<=by+bwidth&&ry>by)
                    {
                        if(bx<=rx&&bx+blength>=rx+rlength) //T
                        {
                            check=false;
                        }
                        else if(bx>rx&&bx<rx+rlength) //F
                        {
                            check=false;
                        }
                        else if(bx+blength>rx&&bx+blength<rx+rlength)//9
                        {
                            check=false;
                        }
                        else if(bx+blength<rx+rlength&&bx>rx)//H
                        {
                            check=false;
                        }
                    }
                }


                if(check==false)
                {
                    if(b instanceof ElectricBounds)
                    {
                        electricDeduct();
                    }
                    return false;
                }
            }

            for(EnemySprite e:enemyList)
            {
                int ex=e.getXPos();
                int ey=e.getYPos();
                int elength=e.getLength();
                int ewidth=e.getWidth();

                if(ry<=ey+ewidth&&ry>ey)
                {
                    if(ex<=rx&&ex+elength>=rx+rlength) //T
                    {
                        return false;
                    }
                    else if(ex>rx&&ex<rx+rlength) //F
                    {
                        return false;
                    }
                    else if(ex+elength>rx&&ex+elength<rx+rlength)//9
                    {
                        return false;
                    }
                    else if(ex+elength<rx+rlength&&ex>rx)//H
                    {
                        return false;
                    }
                }
            }
            for(Reactor r:reactorList)
            {
                int ex=r.getXPos();
                int ey=r.getYPos();
                int elength=r.getLength();
                int ewidth=r.getWidth();

                if(ry<=ey+ewidth&&ry>ey)
                {
                    if(ex<=rx&&ex+elength>=rx+rlength) //T
                    {
                        return false;
                    }
                    else if(ex>rx&&ex<rx+rlength) //F
                    {
                        return false;
                    }
                    else if(ex+elength>rx&&ex+elength<rx+rlength)//9
                    {
                        return false;
                    }
                    else if(ex+elength<rx+rlength&&ex>rx)//H
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
        return false;
    }
    public boolean canMoveDown(ArrayList<Boundary>boundList,ArrayList<EnemySprite>enemyList,ArrayList<Reactor>reactorList)
    {
        try
        {
            int rx=getXPos();
            int ry=getYPos();
            int rlength=getLength();
            int rwidth=getWidth();
            for(Boundary b:boundList)
            { 
                boolean check=true;
                int bx=b.getXPos();
                int by=b.getYPos();
                int blength=b.getLength();
                int bwidth=b.getWidth();

                if(ry+rwidth>=by&&ry+rwidth<by+bwidth)
                {
                    if(bx<=rx&&bx+blength>=rx+rlength) // T
                    {
                        check=false;
                    }
                    else if(bx>rx&&bx<rx+rlength) //F
                    {
                        check=false;
                    }
                    else if(bx+blength>rx&&bx+blength<rx+rlength) //9
                    {
                        check=false;
                    }
                    else if(bx+blength<rx+rlength&&bx>rx)//H
                    {
                        check=false;
                    }
                }
                if(check==false)
                {
                    if(b instanceof ElectricBounds)
                    {
                        electricDeduct();
                    }
                    return false;
                }
            }

            for(EnemySprite e:enemyList)
            {
                int ex=e.getXPos();
                int ey=e.getYPos();
                int elength=e.getLength();
                int ewidth=e.getWidth();

                if(ry+rwidth>=ey&&ry+rwidth<ey+ewidth)
                {
                    if(ex<=rx&&ex+elength>=rx+rlength) // T
                    {
                        return false;
                    }
                    else if(ex>rx&&ex<rx+rlength) //F
                    {
                        return false;
                    }
                    else if(ex+elength>rx&&ex+elength<rx+rlength) //9
                    {
                        return false;
                    }
                    else if(ex+elength<rx+rlength&&ex>rx)//H
                    {
                        return false;
                    }
                }
            }
            for(Reactor r:reactorList)
            {
                int ex=r.getXPos();
                int ey=r.getYPos();
                int elength=r.getLength();
                int ewidth=r.getWidth();

                if(ry+rwidth>=ey&&ry+rwidth<ey+ewidth)
                {
                    if(ex<=rx&&ex+elength>=rx+rlength) // T
                    {
                        return false;
                    }
                    else if(ex>rx&&ex<rx+rlength) //F
                    {
                        return false;
                    }
                    else if(ex+elength>rx&&ex+elength<rx+rlength) //9
                    {
                        return false;
                    }
                    else if(ex+elength<rx+rlength&&ex>rx)//H
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }    
        return false;
    }
    
    /**
     *
     */
    public void electricDeduct()
    {
        try
        {
            if(getShield()>1)
            {
                setShield(getShield()-25);
            }
            else
            {
                setHealth(getHealth()-25);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
}
