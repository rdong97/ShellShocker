/*
 * @author Richard Dong
 */
package shellshocker;

import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Runs the ShellShocker game by drawing graphics, creating a user-controlled 
 * weapon and character control system, and logic system for movement of all items
 * in the game.<link> Called by class TheGame.
 * @author Richard Dong
 * @version 2.0 Last edited 2/6/2014
 */
public class ControlCentral extends Canvas implements Runnable, KeyListener, MouseListener,MouseMotionListener
{
    private static boolean[] keys = new boolean[10];
    private Image background;
    private ScanIn getScan;
    private Flamethrower f;
    private StatusTracker s;
    private Ragnar ragnar;
    private Weapon w;
    private Barrel bar;  
    private int frameX,frameY;
    private int mouseX,mouseY;
    private int currentWeapon;
    private BufferedImage back;
    private Profile profile;
    private Timer weaponFire; 
    private Dimension screenSize;
    private ArrayList<Boundary>boundaryList;
    private ArrayList<EnemySprite>enemyList;
    private ArrayList<Bomb>bombList=new ArrayList<>();
    private ArrayList<Mine>mineList=new ArrayList<>();
    private ArrayList<Explosion>expList=new ArrayList<>();
    private ArrayList<Reactor>reactorList=new ArrayList<>();
    private ArrayList<Box>boxList;
    private static boolean paused,dead,ended;
    private boolean check1,check2,check3;
    
    /**
     * Default constructor for class.<link> Makes calls to different classes to 
     * create features of the game such as the user-controlled vehicle, platforms,
     * enemies, and weapons.
     * @author Richard Dong
     */
    public ControlCentral()
    {
        try
        {
            background=Toolkit.getDefaultToolkit().getImage("resources/Background.png");
            screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            getScan=new ScanIn();
            boundaryList=getScan.scanInBounds();
            enemyList=getScan.scanInEnemy();
            boxList=getScan.scanInBox();
            reactorList=getScan.scanInReactor();
            ragnar=new Ragnar();
            s=new StatusTracker();
            s.startTimer();
            w=new Weapon();
            w.generateCommonImages();
            f=new Flamethrower();
            bar=new Barrel(f,ragnar);
            paused=false;
            addKeyListener((KeyListener) this);
            addMouseListener((MouseListener) this);
            addMouseMotionListener((MouseMotionListener)this);
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            setFocusable(true);
            requestFocusInWindow();
            new Thread(this).start();  
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Sets the current user profile object to the passed parameter.
     * @param p the parameter the profile is to be set to.
     * @author Richard Dong
     */
    public void setProfile(Profile p)
    {
        try
        {
            profile=p;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Finds and sets the start location of the user according to the profile.
     * @author Richard Dong
     */
    public void setStartLocation()
    {
        try
        {
            if(profile.getCheckPoint()==0)
            {
                frameX=0;
                frameY=4200;   
            }
            else if(profile.getCheckPoint()==1)
            {
                frameX=2300;
                frameY=2250;
            }
            else if(profile.getCheckPoint()==2)
            {
                frameX=7800;
                frameY=0;
            }
            else if(profile.getCheckPoint()==3)
            {
                frameX=17740;
                frameY=4220;
            }
            ragnar.setXPos(frameX+screenSize.width/2-150);
            ragnar.setYPos(frameY+screenSize.height/2-75);      
            ragnar.setLevel(profile.getLevel()); 
            ragnar.setHealth(profile.getHealth());
            if(ragnar.getHealth()<=1000)
            {
                ragnar.setHealth(2000);
            }
            ragnar.setPoints(profile.getPoint());
            ragnar.setCheckPoint(profile.getCheckPoint());
            currentWeapon=1;
            if(profile.getLevel()>2)
            {
                currentWeapon=2;
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }       
    }
    
    /**
     * Updates the canvas.<link> Calls the repaint method which calls the paint 
     * method that draws content onto the canvas.
     * @param window The specified Graphics context.
     * @see Canvas
     * @author Richard Dong
     */
    @Override
    public void update(Graphics window)
    {
        try
        {
            paint(window);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    /**
     * Resumes the game.
     * @author Richard Dong
     */
    public static void unPause()
    {
        keys[5]=false;
        paused=false;
        dead=false;
        for(int i=0;i<5;i++)
        {
            keys[i]=false;
        }
        if(Ragnar.getCheckPoint()==3)
        {
            MusicPlayer.stopLevelMusic();
            MusicPlayer.startBoss();
        }
        else if(Ragnar.getCheckPoint()<3)
        {
            MusicPlayer.stopBossMusic();
            MusicPlayer.startLevelMusic();
        }
    }
    /**
     * Paints on the canvas.<link> Items drawn by the method include the main 
     * user-controlled character, platforms, a background, and enemy character.
     * @param window The specified Graphics context.
     * @see Canvas
     * @author Richard Dong
     */
    @Override
    public void paint(Graphics window)
    {
        try
        {
            if(back==null)
            {
                back=(BufferedImage)createImage(getWidth(),getHeight());   
            }
            Graphics2D twoDGraph = (Graphics2D)window;
            Graphics graphToBack= back.createGraphics();
            
            if(keys[5]||paused)
            {
                if(!paused)
                { 
                    TheGame.pauseScreen();
                    paused=true;
                }   
                return;
            }
            if(ragnar.getHealth()<=0||dead)           
            {
                if(!dead)
                {
                    TheGame.deathScreen();
                    dead=true;
                }
                return;
            }
            if(reactorList.isEmpty()||ended)
            {
                if(!ended)
                {
                    TheGame.endGame(ragnar.getPoints());
                    ended=true;
                }
                return;
            }
            graphToBack.translate(-frameX,-frameY);
            graphToBack.setColor(Color.BLACK);
            graphToBack.fillRect(-600,-600,26300,6000);
            graphToBack.drawImage(background,frameX,frameY,frameX+screenSize.width,frameY+screenSize.height,(int)(0.2*(frameX)),(int)(0.2*(frameY)),(int)(0.2*(frameX+screenSize.width)),(int)(0.2*(frameY+screenSize.height)),null);  

            for(Boundary b:boundaryList)
            {
                b.draw(graphToBack);
            }
            for(EnemySprite e:enemyList)
            {
                e.draw(graphToBack);
            }
            for(Bomb b:bombList)
            {
                b.draw(graphToBack);
            }
            for(Mine m:mineList)
            {
                m.draw(graphToBack);
            }
            for(Box b:boxList)
            {
                b.draw(graphToBack);
            }
            for(Explosion e:expList)
            {
                e.draw(graphToBack);    
            }
            for(Reactor r:reactorList)
            {
                r.draw(graphToBack);    
            }
            checkRange();

            drawRagnar(graphToBack);
            bar.update(mouseX, mouseY,ragnar.getXPos()+ragnar.getLength()/2,ragnar.getYPos());
            bar.draw(graphToBack);           
            checkHitBox();          
            checkRemoveEnemy();
            enemyCheckNextLoc();        
            fireEnemy();                                
            w.draw(graphToBack);
            checkBombs();

            checkHit();
            manageLevel();
            manageExplosions();
            manageEndGame();
            manageDoorOpeners();
            checkCheckPoints();
            if(keys[6])
            {
                f.draw(graphToBack);
                if(f.getActive())
                {
                    checkHitFlame();
                }
            }
            else
            {
                MusicPlayer.stopFlamethrower();
            }
            s.update(ragnar.getPoints(),ragnar.getShield(), ragnar.getAmmo(), ragnar.getBomb(),ragnar.getMine(), ragnar.getHealth(), ragnar.getFuel(),ragnar.getPointLimit(),ragnar.getShieldLimit(),ragnar.getAmmoLimit(),ragnar.getBombLimit(),ragnar.getMineLimit(),ragnar.getHealthLimit(),ragnar.getFuelLimit(),boundaryList,enemyList,reactorList);
            s.drawStatusBar(graphToBack, frameX-ragnar.getXSpd(), frameY-ragnar.getYSpd(), getWidth(), getHeight(),ragnar,keys[6],keys[8]);
            twoDGraph.drawImage(back,0,0,null);  
        }
            
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }    
    }
    /**
     * Sets the checkpoint statistic of the user-controlled vehicle when
     * the specified locations have been reached.
     * @author Richard Dong
     */
    public void checkCheckPoints()
    {
        if(ragnar.getXPos()<3350&&ragnar.getXPos()>3100)
        {
            if(ragnar.getYPos()<3000&&ragnar.getYPos()>2600)
            {
                if(!check1)
                {
                    ragnar.setCheckPoint(1);
                    check1=true;
                }            
            }
        }
        if(ragnar.getXPos()<8300&&ragnar.getXPos()>8100)
        {
            if(ragnar.getYPos()<500&&ragnar.getYPos()>0)
            {
                if(!check2)
                {
                    ragnar.setCheckPoint(2);
                    check2=true;
                }
            }
        }
        if(ragnar.getXPos()<18300&&ragnar.getXPos()>17900)
        {
            if(ragnar.getYPos()<5000&&ragnar.getYPos()>4400)
            {
                if(!check3)
                {
                    ragnar.setCheckPoint(3);
                    check3=true;
                }
            }
        }
    }
    /**
     * Calculates and sets the level of the user-controlled vehicle based
     * on health and points.
     * @author Richard Dong
     */
    public void manageLevel()
    {
        if((double)ragnar.getHealth()/(double)ragnar.getHealthLimit()<0.2)
        {
            ragnar.levelDown();
        }
        if(ragnar.getPoints()>=ragnar.getPointLimit())
        {
            ragnar.levelUp();
        }
        
    }
    /**
     * Checks to see whether the user-controlled character has hit a box object.
     * <link> If it has, the box is removed and then the effects of the box are activated.
     * @author Richard Dong
     */
    public void checkHitBox()
    {
        try
        {
            int action=10;
            Box toRemove=null;
            for(Box b:boxList)
            {
                int xPos=ragnar.getXPos();
                int yPos=ragnar.getYPos();
                int bxPos=b.getXPos();
                int byPos=b.getYPos();
                int rWidth=ragnar.getWidth();
                int rLength=ragnar.getLength();
                int bWidth=b.getWidth();
                int bLength=b.getLength();
                if(xPos<=bxPos+bLength&&xPos>bxPos)
                {
                    if(byPos>yPos&&byPos+bWidth<yPos+rWidth)    // C=
                    {
                        action=b.getType();
                        toRemove=b;                 
                    }
                    else if(byPos<yPos+rWidth&&byPos+bWidth>yPos+rWidth)  // C-
                    {
                        action=b.getType();
                        toRemove=b;                    
                    }
                    else if(byPos+bWidth>yPos+rWidth&&byPos<yPos)    //c]
                    {
                        action=b.getType();
                        toRemove=b;                 
                    }
                    else if(byPos+bWidth>yPos&&byPos+bWidth<yPos+rWidth)  // c=
                    {
                        action=b.getType();
                        toRemove=b;           
                    }                
                 }
                 if(xPos+rLength>=bxPos&&xPos+rLength<bxPos+bLength)
                 {
                    if(byPos>yPos&&byPos+bWidth<yPos+rWidth)    // C=
                    {
                        action=b.getType();
                        toRemove=b;              
                    }
                    else if(byPos<yPos+rWidth&&byPos>yPos)  // C-
                    {
                        action=b.getType();
                        toRemove=b;      
                    }
                    else if(byPos+bWidth>yPos+rWidth&&byPos<yPos)    //c]
                    {
                        action=b.getType();
                        toRemove=b;  
                    }
                    else if(byPos+bWidth>yPos&&byPos+bWidth<yPos+rWidth)  // c=
                    {
                        action=b.getType();
                        toRemove=b;  
                    }
                }     
                if(yPos<=byPos+bWidth&&yPos>byPos)
                {
                    if(bxPos<xPos&&bxPos+bLength>xPos+rLength) //T
                    {
                        action=b.getType();
                        toRemove=b;                   
                    }
                    else if(bxPos>xPos&&bxPos<xPos+rLength) //F
                    {
                        action=b.getType();
                        toRemove=b;                    
                    }
                    else if(bxPos+bLength>xPos&&bxPos+bLength<xPos+rLength)//9
                    {
                        action=b.getType();
                        toRemove=b;                   
                    }
                    else if(bxPos+bLength<xPos+rLength&&bxPos>xPos)//H
                    {
                        action=b.getType();
                        toRemove=b;                    
                    }
                }
                if(yPos+rWidth>=byPos&&yPos+rWidth<byPos+bWidth)
                {
                    if(bxPos<xPos&&bxPos+bLength>xPos+rLength) // T
                    {
                        action=b.getType();
                        toRemove=b;                   
                    }
                    else if(bxPos>xPos&&bxPos<xPos+rLength) //F
                    {
                        action=b.getType();
                        toRemove=b;                    
                    }
                    else if(bxPos+bLength>xPos&&bxPos+bLength<xPos+rLength) //9
                    {
                        action=b.getType();
                        toRemove=b;                    
                    }
                    else if(bxPos+bLength<xPos+rLength&&bxPos>xPos)//H
                    {
                        action=b.getType();
                        toRemove=b;                   
                    }
                }
            }       
            //0:point 1:health 2:ammo 3:fuel 4:shield 5:bomb 6:mine
            if(action==0)
            {
                ragnar.setPoints(ragnar.getPoints()+500);
                if(ragnar.getPoints()>ragnar.getPointLimit())
                {
                    ragnar.setPoints(ragnar.getPointLimit());
                }
            }
            else if(action==1)
            {
                ragnar.setHealth(ragnar.getHealth()+2000);
                if(ragnar.getHealth()>ragnar.getHealthLimit())
                {
                    ragnar.setHealth(ragnar.getHealthLimit());
                }
            }
            else if(action==2)
            {
                ragnar.setAmmo(ragnar.getAmmo()+50);
                if(ragnar.getAmmo()>ragnar.getAmmoLimit())
                {
                    ragnar.setAmmo(ragnar.getAmmoLimit());
                }
            }
            else if(action==3)
            {
                ragnar.setFuel(ragnar.getFuel()+3000);
                if(ragnar.getFuel()>ragnar.getFuelLimit())
                {
                    ragnar.setFuel(ragnar.getFuelLimit());
                }
            } 
            else if(action==4)
            {
                ragnar.setShield(ragnar.getShield()+2000);
                if(ragnar.getShield()>ragnar.getShieldLimit())
                {
                    ragnar.setShield(ragnar.getShieldLimit());
                }
            } 
            else if(action==5)
            {
                ragnar.setBomb(ragnar.getBomb()+2);
                if(ragnar.getBomb()>ragnar.getBombLimit())
                {
                    ragnar.setBomb(ragnar.getBombLimit());
                }
            }
            else if(action==6)
            {
                ragnar.setMine(ragnar.getMine()+2);
                if(ragnar.getMine()>ragnar.getMineLimit())
                {
                    ragnar.setMine(ragnar.getMineLimit());
                }
            }
            if(toRemove!=null)
            {
                MusicPlayer.startBoxSound();
                boxList.remove(toRemove);
                checkHitBox();
            }   
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }    
    }
    
    /**
     * Controls the final stage of the game by managing the primary goals of the game.
     * @author Richard Dong
     */
    public void manageEndGame()
    {
        boolean exists=false;
        for(EnemySprite e:enemyList)
        {
            if(e instanceof Boss)
            {
                exists=true;
            }
        }
        if(exists==false)
        {
            for(Reactor r:reactorList)
            {
                r.setActive(false);
            }
        }
        
        
    }
    /**
     * Checks to see if bombs have detonated be comparing locations.<link>Bombs that
     * have hit boundaries or enemies will detonate.
     * @author Richard Dong
     */
    public void checkBombs()
    {
        try
        {
            Bomb toRemove=null;
            int xPos=0;
            int yPos=0;      
            for(Bomb b:bombList)
            {
                if(b.getXPos()<frameX-1000||b.getYPos()<frameY-1000||b.getXPos()>frameX+getWidth()+1000||b.getYPos()>frameY+getHeight()+1000)
                {
                    toRemove=b;
                    xPos=b.getXPos();
                    yPos=b.getYPos();
                }
                for(Boundary bound:boundaryList)
                {
                    if(b.getXPos()+0.5*b.getLength()<=bound.getXPos()+bound.getLength()&&b.getXPos()+0.5*b.getLength()>=bound.getXPos())
                    {
                        if(b.getYPos()+b.getWidth()>=bound.getYPos()&&b.getYPos()+b.getWidth()<=bound.getYPos()+bound.getWidth())
                        {
                            toRemove=b;
                            xPos=b.getXPos();
                            yPos=b.getYPos();
                        }
                    }
                }

                for(EnemySprite enemy:enemyList)
                {
                    if(b.getXPos()+0.5*b.getLength()<=enemy.getXPos()+enemy.getLength()&&b.getXPos()+0.5*b.getLength()>=enemy.getXPos())
                    {
                        if(b.getYPos()+b.getWidth()>=enemy.getYPos()&&b.getYPos()+b.getWidth()<=enemy.getYPos()+enemy.getWidth())
                        {
                            toRemove=b;
                            xPos=b.getXPos();
                            yPos=b.getYPos();
                        }
                    }  
                }
                for(Reactor r:reactorList)
                {
                    if(b.getXPos()+0.5*b.getLength()<=r.getXPos()+r.getLength()&&b.getXPos()+0.5*b.getLength()>=r.getXPos())
                    {
                        if(b.getYPos()+b.getWidth()>=r.getYPos()&&b.getYPos()+b.getWidth()<=r.getYPos()+r.getWidth())
                        {
                            toRemove=b;
                            xPos=b.getXPos();
                            yPos=b.getYPos();
                        }
                    }  
                }

            }
            if(toRemove!=null)
            {
                removeEnemies(xPos,yPos);
                expList.add(new Explosion(xPos-500,yPos-500,1000,1000));
                MusicPlayer.startExplosionSound();
                bombList.remove(toRemove);
                MusicPlayer.stopBomb();
                checkBombs();
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        
    }
    /**
     * Checks to see if mines can be detonated be contact with enemy entities.
     * @author Richard Dong
     */
    public void checkHit()
    {
        try
        {
            int x=0;
            int y=0;
            Mine toRemove=null;
            for(Mine m:mineList)
            {
                for(EnemySprite enemy:enemyList)
                {
                    if(m.getXPos()+0.5*m.getLength()<=enemy.getXPos()+enemy.getLength()&&m.getXPos()+0.5*m.getLength()>=enemy.getXPos())
                    {
                        if(m.getYPos()+0.5*m.getWidth()>=enemy.getYPos()&&m.getYPos()+0.5*m.getWidth()<=enemy.getYPos()+enemy.getWidth())
                        {
                            toRemove=m;
                            x=m.getXPos();
                            y=m.getYPos();
                        }
                    }  
                }    
            }
            if(toRemove!=null)
            {
                System.out.println("exploded");
                for(EnemySprite enemy:enemyList)
                {
                    int xDiff=Math.abs(x-enemy.getXPos());
                    int yDiff=Math.abs(y-enemy.getYPos());
                    double distance=Math.pow(Math.pow(xDiff,2)+Math.pow(yDiff,2), 0.5);
                    if(distance<500)
                    {
                        enemy.setHealth(enemy.getHealth()-90);
                    }
                }     
                expList.add(new Explosion(x-500,y-500,1000,1000));
                MusicPlayer.startExplosionSound();
                mineList.remove(toRemove);
                checkHit();
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        
    }
    /**
     * Manages the explosion animations in the game.
     * @author Richard Dong
     */
    public void manageExplosions()
    {
        try
        {
            Explosion toRemove=null;
            for(Explosion e: expList)
            {
                if(e.getTime()>15)
                {
                    toRemove=e;
                }
            }
            if(toRemove!=null)
            {
                expList.remove(toRemove);
                manageExplosions();
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        
    }
    
    /**
     * Manages the door unlocking mechanisms that allow for the user to 
     * unlock new levels of the game.
     * @author Richard Dong
     */
    public void manageDoorOpeners()
    {
        try
        {
            Weapon toRemove=null;
            Doors check=null;

            ArrayList<Weapon>weaponList=w.getWeaponList();        
            for(Weapon weapon:weaponList)
            {
                if(weapon.getType()!=0)
                {
                    for(Boundary door:boundaryList)
                    {
                        if(door instanceof Doors)
                        {
                            DoorOpener d=((Doors)door).getOpener();
                            int x=(int)weapon.getXPos();
                            int y=(int)weapon.getYPos();
                            if(d!=null)
                            {
                                if(x>=d.getXPos()&&x<=d.getXPos()+d.getLength())
                                {
                                    if(y>=d.getYPos()&&y<=d.getYPos()+d.getWidth())
                                    {
                                        toRemove=weapon;
                                        d.setHealth(d.getHealth()-10);
                                        if(d.getHealth()<=0)
                                        {
                                            check=((Doors)door);
                                        }
                                    }
                                }
                            }     
                        }
                    }          
                }    
            }
            if(toRemove!=null)
            {
                if(toRemove.getType()>1)
                {
                    expList.add(new Explosion((int)toRemove.getXPos()-50,(int)toRemove.getYPos()-50,100,100));
                    MusicPlayer.startExplosionSound();
                }
                w.getWeaponList().remove(toRemove);
                boundaryList.remove(check);
                manageDoorOpeners();
            }  
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        
    }
    
    /**
     * Runs an artificial intelligence algorithm that causes enemies to shoot 
     * at the user-controlled character based on range.
     * @author Richard Dong
     */
    public void fireEnemy()
    {
        try
        {
            for(EnemySprite e:enemyList)
            {
                int xDiff=Math.abs(ragnar.getXPos()-e.getXPos());
                int yDiff=Math.abs(ragnar.getYPos()-e.getYPos());
                double distance=Math.pow(Math.pow(xDiff,2)+Math.pow(yDiff,2), 0.5);    
                if(e instanceof Boss)
                {
                    if(distance<3000)
                    {
                        e.setActive(true);
                    }
                    else
                    {
                        e.setActive(false);
                    } 
                }
                else
                {
                    if(distance<1500)
                    {
                        e.setActive(true);
                    }
                    else
                    {
                        e.setActive(false);
                    }
                }
                if(e.getActive())
                {
                    e.findNextLoc();
                    e.fireWeapons(w, ragnar);
                }
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        
    }

    /**
     * Runs an artificial intelligence algorithm that checks the movements of
     * enemy entities based on boundaries, other enemies, and the user-controlled 
     * character in order to simulate realistic conditions.
     * @author Richard Dong
     */
    public void enemyCheckNextLoc()
    {
        try
        {
            for(EnemySprite enemy:enemyList)
            {
                if(enemy instanceof SmartEnemy)
                {
                    ((SmartEnemy)enemy).track(ragnar.getXPos(),ragnar.getYPos());
                }
                if(enemy instanceof Boss)
                {
                    ((Boss)enemy).track(ragnar.getXPos(),ragnar.getYPos());
                }
                if(!(enemy instanceof Tower))
                {
                    int xPosition=enemy.getXPos();
                    int yPosition=enemy.getYPos();
                    int xPosit=enemy.getXPos()+enemy.getXSpd();
                    int yPosit=enemy.getYPos()+enemy.getYSpd();
                    boolean changeX=false;
                    boolean changeY=false;
                    if(xPosition<=ragnar.getXPos()+ragnar.getLength()&&xPosition>ragnar.getXPos())
                    {
                        if(ragnar.getYPos()>yPosition&&ragnar.getYPos()+ragnar.getWidth()<yPosition+enemy.getWidth())    // C=
                        {
                            changeX=true;
                        }
                        else if(ragnar.getYPos()<yPosition+enemy.getWidth()&&ragnar.getYPos()+ragnar.getWidth()>yPosition+enemy.getWidth())  // C-
                        {
                            changeX=true;
                        }
                        else if(ragnar.getYPos()+ragnar.getWidth()>=yPosition+enemy.getWidth()&&ragnar.getYPos()<=yPosition)    //c]
                        {
                            changeX=true;
                        }
                        else if(ragnar.getYPos()+ragnar.getWidth()>yPosition&&ragnar.getYPos()+ragnar.getWidth()<yPosition+enemy.getWidth())  // c=
                        {
                            changeX=true;
                        }
                     }
                    if(xPosition+enemy.getLength()>=ragnar.getXPos()&&xPosition+enemy.getLength()<ragnar.getXPos()+ragnar.getLength())
                    {
                        if(ragnar.getYPos()>yPosition&&ragnar.getYPos()+ragnar.getWidth()<yPosition+enemy.getWidth())    // C=
                        {
                            changeX=true;
                        }
                        else if(ragnar.getYPos()<yPosition+enemy.getWidth()&&ragnar.getYPos()>yPosition)  // C-
                        {
                            changeX=true;
                        }
                        else if(ragnar.getYPos()+ragnar.getWidth()>=yPosition+enemy.getWidth()&&ragnar.getYPos()<=yPosition)    //c]
                        {
                            changeX=true;
                        }
                        else if(ragnar.getYPos()+ragnar.getWidth()>yPosition&&ragnar.getYPos()+ragnar.getWidth()<yPosition+enemy.getWidth())  // c=
                        {
                            changeX=true;
                        }
                    }
                    if(yPosition<=ragnar.getYPos()+ragnar.getWidth()&&yPosition>ragnar.getYPos())
                    {
                        if(ragnar.getXPos()<=xPosition&&ragnar.getXPos()+ragnar.getLength()>=xPosition+enemy.getLength()) //T
                        {
                            changeY=true;
                        }
                        else if(ragnar.getXPos()>xPosition&&ragnar.getXPos()<xPosition+enemy.getLength()) //F
                        {
                            changeY=true;
                        }
                        else if(ragnar.getXPos()+ragnar.getLength()>xPosition&&ragnar.getXPos()+ragnar.getLength()<xPosition+enemy.getLength())//9
                        {
                            changeY=true;
                        }
                        else if(ragnar.getXPos()+ragnar.getLength()<xPosition+enemy.getLength()&&ragnar.getXPos()>xPosition)//H
                        {
                            changeY=true;
                        }
                    }
                    if(yPosition+enemy.getWidth()>=ragnar.getYPos()&&yPosition+enemy.getWidth()<ragnar.getYPos()+ragnar.getWidth())
                    {
                        if(ragnar.getXPos()<=xPosition&&ragnar.getXPos()+ragnar.getLength()>=xPosition+enemy.getLength()) // T
                        {
                            changeY=true;
                        }
                        else if(ragnar.getXPos()>xPosition&&ragnar.getXPos()<xPosition+enemy.getLength()) //F
                        {
                            changeY=true;
                        }
                        else if(ragnar.getXPos()+ragnar.getLength()>xPosition&&ragnar.getXPos()+ragnar.getLength()<xPosition+enemy.getLength()) //9
                        {
                            changeY=true;
                        }
                        else if(ragnar.getXPos()+ragnar.getLength()<xPosition+enemy.getLength()&&ragnar.getXPos()>xPosition)//H
                        {
                            changeY=true;
                        }
                    }
                    for(Boundary b:boundaryList)
                    {
                        if(xPosit<=b.getXPos()+b.getLength()&&xPosit>b.getXPos())
                        {
                            if(b.getYPos()>yPosit&&b.getYPos()+b.getWidth()<yPosit+enemy.getWidth())    // C=
                            {
                                changeX=true;
                            }
                            else if(b.getYPos()<yPosit+enemy.getWidth()&&b.getYPos()+b.getWidth()>yPosit+enemy.getWidth())  // C-
                            {
                                changeX=true;
                            }
                            else if(b.getYPos()+b.getWidth()>=yPosit+enemy.getWidth()&&b.getYPos()<=yPosit)    //c]
                            {
                                changeX=true;
                            }
                            else if(b.getYPos()+b.getWidth()>yPosit&&b.getYPos()+b.getWidth()<yPosit+enemy.getWidth())  // c=
                            {
                                changeX=true;
                            }
                         }
                        if(xPosit+enemy.getLength()>=b.getXPos()&&xPosit+enemy.getLength()<b.getXPos()+b.getLength())
                        {
                            if(b.getYPos()>yPosit&&b.getYPos()+b.getWidth()<yPosit+enemy.getWidth())    // C=
                            {
                                changeX=true;
                            }
                            else if(b.getYPos()<yPosit+enemy.getWidth()&&b.getYPos()>yPosit)  // C-
                            {
                                changeX=true;
                            }
                            else if(b.getYPos()+b.getWidth()>=yPosit+enemy.getWidth()&&b.getYPos()<=yPosit)    //c]
                            {
                                changeX=true;
                            }
                            else if(b.getYPos()+b.getWidth()>yPosit&&b.getYPos()+b.getWidth()<yPosit+enemy.getWidth())  // c=
                            {
                                changeX=true;
                            }
                        }
                        if(yPosit<=b.getYPos()+b.getWidth()&&yPosit>b.getYPos())
                        {
                            if(b.getXPos()<=xPosit&&b.getXPos()+b.getLength()>=xPosit+enemy.getLength()) //T
                            {
                                changeY=true;
                            }
                            else if(b.getXPos()>xPosit&&b.getXPos()<xPosit+enemy.getLength()) //F
                            {
                                changeY=true;
                            }
                            else if(b.getXPos()+b.getLength()>xPosit&&b.getXPos()+b.getLength()<xPosit+enemy.getLength())//9
                            {
                                changeY=true;
                            }
                            else if(b.getXPos()+b.getLength()<xPosit+enemy.getLength()&&b.getXPos()>xPosit)//H
                            {
                                changeY=true;
                            }
                        }
                        if(yPosit+enemy.getWidth()>=b.getYPos()&&yPosit+enemy.getWidth()<b.getYPos()+b.getWidth())
                        {
                            if(b.getXPos()<=xPosit&&b.getXPos()+b.getLength()>=xPosit+enemy.getLength()) // T
                            {
                                changeY=true;
                            }
                            else if(b.getXPos()>xPosit&&b.getXPos()<xPosit+enemy.getLength()) //F
                            {
                                changeY=true;
                            }
                            else if(b.getXPos()+b.getLength()>xPosit&&b.getXPos()+b.getLength()<xPosit+enemy.getLength()) //9
                            {
                                changeY=true;
                            }
                            else if(b.getXPos()+b.getLength()<xPosit+enemy.getLength()&&b.getXPos()>xPosit)//H
                            {
                                changeY=true;
                            }
                        }
                    }
                    for(Reactor r:reactorList)
                    {
                        if(xPosit<=r.getXPos()+r.getLength()&&xPosit>r.getXPos())
                        {
                            if(r.getYPos()>yPosit&&r.getYPos()+r.getWidth()<yPosit+enemy.getWidth())    // C=
                            {
                                changeX=true;
                            }
                            else if(r.getYPos()<yPosit+enemy.getWidth()&&r.getYPos()+r.getWidth()>yPosit+enemy.getWidth())  // C-
                            {
                                changeX=true;
                            }
                            else if(r.getYPos()+r.getWidth()>=yPosit+enemy.getWidth()&&r.getYPos()<=yPosit)    //c]
                            {
                                changeX=true;
                            }
                            else if(r.getYPos()+r.getWidth()>yPosit&&r.getYPos()+r.getWidth()<yPosit+enemy.getWidth())  // c=
                            {
                                changeX=true;
                            }
                         }
                        if(xPosit+enemy.getLength()>=r.getXPos()&&xPosit+enemy.getLength()<r.getXPos()+r.getLength())
                        {
                            if(r.getYPos()>yPosit&&r.getYPos()+r.getWidth()<yPosit+enemy.getWidth())    // C=
                            {
                                changeX=true;
                            }
                            else if(r.getYPos()<yPosit+enemy.getWidth()&&r.getYPos()>yPosit)  // C-
                            {
                                changeX=true;
                            }
                            else if(r.getYPos()+r.getWidth()>=yPosit+enemy.getWidth()&&r.getYPos()<=yPosit)    //c]
                            {
                                changeX=true;
                            }
                            else if(r.getYPos()+r.getWidth()>yPosit&&r.getYPos()+r.getWidth()<yPosit+enemy.getWidth())  // c=
                            {
                                changeX=true;
                            }
                        }
                        if(yPosit<=r.getYPos()+r.getWidth()&&yPosit>r.getYPos())
                        {
                            if(r.getXPos()<=xPosit&&r.getXPos()+r.getLength()>=xPosit+enemy.getLength()) //T
                            {
                                changeY=true;
                            }
                            else if(r.getXPos()>xPosit&&r.getXPos()<xPosit+enemy.getLength()) //F
                            {
                                changeY=true;
                            }
                            else if(r.getXPos()+r.getLength()>xPosit&&r.getXPos()+r.getLength()<xPosit+enemy.getLength())//9
                            {
                                changeY=true;
                            }
                            else if(r.getXPos()+r.getLength()<xPosit+enemy.getLength()&&r.getXPos()>xPosit)//H
                            {
                                changeY=true;
                            }
                        }
                        if(yPosit+enemy.getWidth()>=r.getYPos()&&yPosit+enemy.getWidth()<r.getYPos()+r.getWidth())
                        {
                            if(r.getXPos()<=xPosit&&r.getXPos()+r.getLength()>=xPosit+enemy.getLength()) // T
                            {
                                changeY=true;
                            }
                            else if(r.getXPos()>xPosit&&r.getXPos()<xPosit+enemy.getLength()) //F
                            {
                                changeY=true;
                            }
                            else if(r.getXPos()+r.getLength()>xPosit&&r.getXPos()+r.getLength()<xPosit+enemy.getLength()) //9
                            {
                                changeY=true;
                            }
                            else if(r.getXPos()+r.getLength()<xPosit+enemy.getLength()&&r.getXPos()>xPosit)//H
                            {
                                changeY=true;
                            }
                        }
                    }

                    for(EnemySprite evil:enemyList)
                    {
                        if(!(evil.equals(enemy)))
                        {
                            if(xPosit<=evil.getXPos()+evil.getXSpd()+evil.getLength()&&xPosit>evil.getXPos()+evil.getXSpd())
                            {
                                if(evil.getYPos()+evil.getYSpd()>yPosit&&evil.getYPos()+evil.getYSpd()+evil.getWidth()<yPosit+enemy.getWidth())    // C=
                                {
                                    changeX=true;
                                }
                                else if(evil.getYPos()+evil.getYSpd()<yPosit+enemy.getWidth()&&evil.getYPos()+evil.getYSpd()+evil.getWidth()>yPosit+enemy.getWidth())  // C_
                                {
                                    changeX=true;
                                }
                                else if(evil.getYPos()+evil.getYSpd()+evil.getWidth()>=yPosit+enemy.getWidth()&&evil.getYPos()+evil.getYSpd()<=yPosit)    //c]
                                {
                                    changeX=true;
                                }
                                else if(evil.getYPos()+evil.getYSpd()+evil.getWidth()>yPosit&&evil.getYPos()+evil.getYSpd()+evil.getWidth()<yPosit+enemy.getWidth())  // c=
                                {
                                    changeX=true;
                                }
                            }
                            if(xPosit+enemy.getLength()>=evil.getXPos()+evil.getXSpd()&&xPosit+enemy.getLength()<evil.getXPos()+evil.getXSpd()+evil.getLength())
                            {
                                if(evil.getYPos()+evil.getYSpd()>yPosit&&evil.getYPos()+evil.getYSpd()+evil.getWidth()<yPosit+enemy.getWidth())    // C=
                                {
                                    changeX=true;
                                }
                                else if(evil.getYPos()+evil.getYSpd()<yPosit+enemy.getWidth()&&evil.getYPos()+evil.getYSpd()>yPosit)  // C-
                                {
                                    changeX=true;
                                }
                                else if(evil.getYPos()+evil.getYSpd()+evil.getWidth()>=yPosit+enemy.getWidth()&&evil.getYPos()+evil.getYSpd()<=yPosit)    //c]
                                {
                                    changeX=true;
                                }
                                else if(evil.getYPos()+evil.getYSpd()+evil.getWidth()>yPosit&&evil.getYPos()+evil.getYSpd()+evil.getWidth()<yPosit+enemy.getWidth())  // c=
                                {
                                    changeX=true;
                                }
                            }
                            if(yPosit<=evil.getYPos()+evil.getYSpd()+evil.getWidth()&&yPosit>evil.getYPos()+evil.getYSpd())
                            {
                                if(evil.getXPos()+evil.getXSpd()<=xPosit&&evil.getXPos()+evil.getXSpd()+evil.getLength()>=xPosit+enemy.getLength()) //T
                                {
                                    changeY=true;
                                }
                                else if(evil.getXPos()+evil.getXSpd()>xPosit&&evil.getXPos()+evil.getXSpd()<xPosit+enemy.getLength()) //F
                                {
                                    changeY=true;
                                }
                                else if(evil.getXPos()+evil.getXSpd()+evil.getLength()>xPosit&&evil.getXPos()+evil.getXSpd()+evil.getLength()<xPosit+enemy.getLength())//9
                                {
                                    changeY=true;
                                }
                                else if(evil.getXPos()+evil.getXSpd()+evil.getLength()<xPosit+enemy.getLength()&&evil.getXPos()+evil.getXSpd()>xPosit)//H
                                {
                                    changeY=true;
                                }
                            }
                            if(yPosit+enemy.getWidth()>=evil.getYPos()+evil.getYSpd()&&yPosit+enemy.getWidth()<evil.getYPos()+evil.getYSpd()+evil.getWidth())
                            {
                                if(evil.getXPos()+evil.getXSpd()<=xPosit&&evil.getXPos()+evil.getXSpd()+evil.getLength()>=xPosit+enemy.getLength()) // T
                                {
                                    changeY=true;
                                }
                                else if(evil.getXPos()+evil.getXSpd()>xPosit&&evil.getXPos()+evil.getXSpd()<xPosit+enemy.getLength()) //F
                                {
                                    changeY=true;
                                }
                                else if(evil.getXPos()+evil.getXSpd()+evil.getLength()>xPosit&&evil.getXPos()+evil.getXSpd()+evil.getLength()<xPosit+enemy.getLength()) //9
                                {
                                    changeY=true;
                                }
                                else if(evil.getXPos()+evil.getXSpd()+evil.getLength()<xPosit+enemy.getLength()&&evil.getXPos()+evil.getXSpd()>xPosit)//H
                                {
                                    changeY=true;
                                }
                            }
                        }           
                    }
                    if(enemy instanceof SmartEnemy||enemy instanceof Boss)
                    {

                        if(changeX==true)
                        {
                            enemy.setXSpd(0);
                        }
                        if(changeY==true)
                        {
                            enemy.setYSpd(0);
                        }
                    }
                    else if(enemy instanceof Enemy)
                    {
                        if(changeX==true)
                        {
                            enemy.setXSpd(-enemy.getXSpd());
                        }
                        if(changeY==true)
                        {
                            enemy.setYSpd(-enemy.getYSpd());
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
     * Checks to see if enemies have made contact with the damaging flamethrower 
     * weapon of the user-controlled character.
     * @author Richard Dong
     */
    public void checkHitFlame()
    {
        try
        {
            ArrayList<Point>hitList=f.getHitPoints();
            for(Point p:hitList)
            { 
                for(EnemySprite e:enemyList)
                {
                    if(p.getX()>e.getXPos()&&p.getX()<e.getXPos()+e.getLength())
                    {
                        if(p.getY()>e.getYPos()&&p.getY()<e.getYPos()+e.getWidth())
                        {
                            e.setHealth(e.getHealth()-1);
                        }
                    }
                }
                for(Reactor r:reactorList)
                {
                    if(p.getX()>r.getXPos()&&p.getX()<r.getXPos()+r.getLength())
                    {
                        if(p.getY()>r.getYPos()&&p.getY()<r.getYPos()+r.getWidth())
                        {
                            if(!r.getActive())
                            {
                                r.setHealth(r.getHealth()-1);
                            }                     
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
     * Simulates a bomb explosion by dealing damage to all enemy units in a 
     * specified range of the blast.
     * @param x the x-coordinate of the blast.
     * @param y the y-coordinate of the blast.
     */
    public void removeEnemies(int x, int y)
    {    
        try
        {
            for(EnemySprite e:enemyList)
            {
                int xDiff=Math.abs(x-e.getXPos());
                int yDiff=Math.abs(y-e.getYPos());
                double distance=Math.pow(Math.pow(xDiff,2)+Math.pow(yDiff,2), 0.5);
                if(distance<500)
                {
                    e.setHealth(e.getHealth()-90);
                }
            }
            for(Reactor r:reactorList)
            {
                int xDiff=Math.abs(x-(r.getXPos()+r.getLength()/2));
                int yDiff=Math.abs(y-(r.getYPos()+r.getWidth()/2));
                double distance=Math.pow(Math.pow(xDiff,2)+Math.pow(yDiff,2), 0.5);
                if(distance<500)
                {
                    if(!r.getActive())
                    {
                        r.setHealth(r.getHealth()-100);
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
     * Checks to see if all projectiles fired have impacted other objects, such as
     * boundaries, other enemies, or the user-controlled character, in order to 
     * simulate realistic conditions.
     * @author Richard Dong
     */
    public void checkRange()
    {
        try
        {
            ArrayList<Weapon>weaponList=w.getWeaponList(); 
            Weapon toRemove=null;
            for(Weapon weapon:weaponList)
            {
                double x=weapon.getXPos();
                double y=weapon.getYPos();
                if(x<ragnar.getXPos()-1500||x>ragnar.getXPos()+1500||y<ragnar.getYPos()-1500||y>ragnar.getYPos()+1500)
                {
                    toRemove=weapon;
                }
                if(x<=ragnar.getXPos()+ragnar.getLength()&&x>=ragnar.getXPos())
                {
                    if(y>=ragnar.getYPos()&&y<=ragnar.getYPos()+ragnar.getWidth())
                    {
                        toRemove=weapon;
                        if(ragnar.getShield()-200<0&&ragnar.getShield()>0)
                        {
                            ragnar.setShield(0);
                        }
                        else if(ragnar.getShield()-200>=0)
                        {
                            ragnar.setShield(ragnar.getShield()-200);
                        }
                        else
                        {
                            ragnar.setHealth(ragnar.getHealth()-200);
                        }
                    }
                }
                if(x<frameX-500||y<frameY-500||x>frameX+getWidth()+500||y>frameY+getHeight()+500)
                {
                    toRemove=weapon; 
                }
                for(Boundary b:boundaryList)
                {
                    if(x<=b.getXPos()+b.getLength()&&x>=b.getXPos())
                    {
                        if(y>=b.getYPos()&&y<=b.getYPos()+b.getWidth())
                        {
                            toRemove=weapon; 
                        }
                    }
                }

                for(EnemySprite enemy:enemyList)
                {
                    if(x<=enemy.getXPos()+enemy.getLength()&&x>=enemy.getXPos())
                    {
                        if(y>=enemy.getYPos()&&y<=enemy.getYPos()+enemy.getWidth())
                        {
                            if(weapon.getType()==1)
                            {
                                enemy.setHealth(enemy.getHealth()-10);
                                toRemove=weapon; 
                            }  
                            else if(weapon.getType()==2)
                            {
                                enemy.setHealth(enemy.getHealth()-20);
                                toRemove=weapon; 
                            } 
                        }
                    }  
                }
                for(Reactor r:reactorList)
                {
                    if(x<=r.getXPos()+r.getLength()&&x>=r.getXPos())
                    {
                        if(y>=r.getYPos()&&y<=r.getYPos()+r.getWidth())
                        {
                            toRemove=weapon;
                            if(!r.getActive())
                            {
                                if(weapon.getType()==1)
                                {
                                    r.setHealth(r.getHealth()-10);            
                                }   
                                else if(weapon.getType()==2)
                                {
                                    r.setHealth(r.getHealth()-20);
                                }   
                            }
                            
                        }
                    }  
                }
            }
            if(toRemove!=null)
            {
                if(toRemove.getType()>1)
                {
                    expList.add(new Explosion((int)toRemove.getXPos()-50,(int)toRemove.getYPos()-50,100,100));
                    MusicPlayer.startExplosionSound();
                }
                
                w.getWeaponList().remove(toRemove);
                checkRange();
            }  
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }         
    }
    
    /**
     * Checks to remove all enemies that have health levels less than or equal
     * to 0 to simulate death conditions.
     * @author Richard Dong
     */
    public void checkRemoveEnemy()
    {
        try
        {
            EnemySprite toRemove=null;
            for(EnemySprite enemy:enemyList)
            {
                if(enemy.getHealth()<=0)
                {
                    enemy.setActive(false);                
                    toRemove=enemy;
                }  
            }
            if(toRemove!=null)
            {           
                expList.add(new Explosion(toRemove.getXPos(),toRemove.getYPos(),toRemove.getLength(),toRemove.getWidth()));
                MusicPlayer.startExplosionSound();
                if(enemyList.remove(toRemove))
                {
                    ragnar.setPoints(ragnar.getPoints()+100);
                    if(toRemove instanceof Boss)
                    {
                        ragnar.setPoints(ragnar.getPoints()+1000);
                    }
                }
                checkRemoveEnemy();  
            }
            
            Reactor check=null;
            for(Reactor r:reactorList)
            {
                if(r.getHealth()<=0)
                {                
                    check=r;
                }  
            }
            if(check!=null)
            {           
                expList.add(new Explosion(check.getXPos(),check.getYPos(),check.getLength(),check.getWidth()));
                MusicPlayer.startExplosionSound();
                if(reactorList.remove(check))
                {
                    ragnar.setPoints(ragnar.getPoints()+100);
                }
                checkRemoveEnemy();  
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    
    
    /**
     * Invoked when a key is pressed.<link> Pressing a key invokes a command that 
     * leads to a change in the game.
     * @param e The specified KeyEvent context. 
     * @see KeyListener
     * @see KeyEvent
     * @author Richard Dong
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        try
        {
            switch(toUpperCase(e.getKeyChar()))
            {
                case KeyEvent.VK_W : keys[0]=true; break; //up
                case KeyEvent.VK_A : keys[1]=true; break; //left
                case KeyEvent.VK_D : keys[2]=true; break; //right
                case KeyEvent.VK_S : keys[3]=true; break; //down 
                case KeyEvent.VK_SPACE : keys[4]=true; break; //stabilize
                case KeyEvent.VK_P : keys[5]=!keys[5]; break; //pause
                case KeyEvent.VK_F : keys[6]=!keys[6]; break; //flamethrower
                case KeyEvent.VK_E : keys[8]=!keys[8]; break; //minelayer

            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }

    /**
     * Invoked when a key is released.<link> Releasing a key invokes a command that 
     * leads to a change in the game.
     * @param e The specified KeyEvent context. 
     * @see KeyListener
     * @see KeyEvent
     * @author Richard Dong
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        try
        {
            switch(toUpperCase(e.getKeyChar()))
            {
                case KeyEvent.VK_W : keys[0]=false; break;
                case KeyEvent.VK_A : keys[1]=false; break;
                case KeyEvent.VK_D : keys[2]=false; break;
                case KeyEvent.VK_S : keys[3]=false; break;
                case KeyEvent.VK_SPACE  : keys[4]=false; break;
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }

    /**
     * Invoked when a key is typed.<link>Typing a key invokes a command that 
     * leads to a change in the game.
     * @param e The specified KeyEvent context. 
     * @see KeyListener
     * @see KeyEvent
     * @author Richard Dong
     */
    @Override
        public void keyTyped(KeyEvent e){}
	 
    /**
     * Invoked when the mouse is clicked.<link> Clicking the mouse invokes a command that 
     * leads to a change in the game.
     * @param e The specified MouseEvent context. 
     * @see MouseListener
     * @see MouseEvent
     * @author Richard Dong
     */
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        try
        {
            if(e.getButton()==1)
            {
                mouseX=e.getX()+frameX;
                mouseY=e.getY()+frameY;
                System.out.println(mouseX+","+mouseY);
                double xDiff=mouseX-(ragnar.getXPos()+ragnar.getLength()/2);
                double yDiff=-(mouseY-(ragnar.getYPos()+35));
                double rotationRequired;
                if(xDiff!=0)
                {
                    rotationRequired=-Math.atan(yDiff/xDiff);
                }
                else
                {
                    rotationRequired=-Math.PI/2;     
                }
                if(yDiff>=0)
                {
                    if(xDiff<0)
                    {
                        rotationRequired=rotationRequired+Math.PI;
                    }
                    if(ragnar.getAmmo()>0)
                    {
                        newWeapon(rotationRequired);
                        ragnar.setAmmo(ragnar.getAmmo()-1);
                    }
                }
            }

            if(e.getButton()==3&&!keys[8])
            {
                if(ragnar.getBomb()>0)
                {
                    ragnar.setBomb(ragnar.getBomb()-1);
                    addBomb();
                }
            }   
            else if(e.getButton()==3&&keys[8])
            {
                if(ragnar.getMine()>0)
                {
                    ragnar.setMine(ragnar.getMine()-1);
                    addMine();
                }
            }   
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }   
    }

    /**
     * Invoked when the mouse enters the screen.<link> Entering invokes a command that 
     * leads to a change in the game.
     * @param e The specified MouseEvent context. 
     * @see MouseListener
     * @see MouseEvent
     * @author Richard Dong
     */
    @Override
    public void mouseEntered(MouseEvent e) 
    {
        try
        {
            mouseX=e.getX()+frameX;
            mouseY=e.getY()+frameY;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Invoked when the mouse exits the screen.<link> Exiting invokes a command that 
     * leads to a change in the game.
     * @param e The specified MouseEvent context. 
     * @see MouseListener
     * @see MouseEvent
     * @author Richard Dong
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
        try
        {
            mouseX=e.getX()+frameX;
            mouseY=e.getY()+frameY;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Used to create a thread, starting the thread causes the run method to be called 
     * in that thread.<link> Calls the repaint method of Canvas.
     * @see Runnable
     * @author Richard Dong
     */ 
    @Override
    public void run()
    {   
        try
        {
            int delay = 15;
            ActionListener taskPerformer = new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) {
                       repaint();
                }};
            new Timer(delay, taskPerformer).start();
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Draws the user-controlled character after performing calculations on the 
     * character's location.
     * @param window The specified Graphics context.
     * @author Richard Dong
     */
    public void drawRagnar(Graphics window)
    {   
        try
        {
            ragnar.gravity();
            if (keys[0]==true)
            { 
                if(ragnar.getFuel()>0)
                {
                    ragnar.setYSpd(ragnar.getYSpd()-1);  
                }
            }
            if (keys[1]==true)
            {
                ragnar.setXSpd(-8);
            }
            if (keys[2]==true)
            {
                ragnar.setXSpd(8);     
            }
            if (keys[3]==true)
            {
                ragnar.setYSpd(ragnar.getYSpd()+1); 
            }
            if (keys[1]==false&&keys[2]==false)
            {
                ragnar.setXSpd(0);
            }
            if (keys[4]==true)
            {
                ragnar.setYSpd(0);
            } 
            ragnar.storePos();     
            ragnar.setNextPos();

            if(ragnar.canMoveRight(boundaryList,enemyList,reactorList)==false&&ragnar.getXSpd()>0)
            {
                ragnar.revertXPos();
                ragnar.setXSpd(0);
            }
            if(ragnar.canMoveLeft(boundaryList,enemyList,reactorList)==false&&ragnar.getXSpd()<0)
            {
                ragnar.revertXPos();
                ragnar.setXSpd(0);
            }
            if(ragnar.canMoveUp(boundaryList,enemyList,reactorList)==false&&ragnar.getYSpd()<0)
            {
                ragnar.revertYPos();
                ragnar.setYSpd(0);
            }
            if(ragnar.canMoveDown(boundaryList,enemyList,reactorList)==false&&ragnar.getYSpd()>0)
            {
                ragnar.revertYPos();
                ragnar.setYSpd(0);
            }  
              frameX=frameX+ragnar.getXSpd();
              frameY=frameY+ragnar.getYSpd();  
            if(keys[0]==true&&keys[1]==true)
            {
                ragnar.setFuel(ragnar.getFuel()-1);
                ragnar.setVersion(3);
            }
            else if(keys[0]==true&&keys[2]==true)
            {
                ragnar.setFuel(ragnar.getFuel()-1);
                ragnar.setVersion(1);
            }
            else if(keys[2]==true)
            {
                ragnar.setVersion(0);
            }
            else if(keys[1])
            {
                ragnar.setVersion(2);
            }
            if(keys[4]==true||keys[0]==true)
            {
                ragnar.setFuel(ragnar.getFuel()-1);
                if(ragnar.getVersion()==0)
                {
                    ragnar.setVersion(1);
                }
                if(ragnar.getVersion()==2)
                {
                    ragnar.setVersion(3);
                }
            }
            if(keys[0]==false&&keys[4]==false)
            {
                if(ragnar.getVersion()==1)
                {
                    ragnar.setVersion(0);
                }
                if(ragnar.getVersion()==3)
                {
                    ragnar.setVersion(2);
                }   
            }
            ragnar.draw(window);    
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }  
     
    /**
     * Invoked when the mouse is pressed.<link> Pressing the mouse invokes a command that 
     * leads to a change in the game.
     * @param e The specified MouseEvent context. 
     * @see MouseListener
     * @see MouseEvent
     * @author Richard Dong
     */
    @Override
    public void mousePressed(MouseEvent e) 
    {
        try
        {
            int delay = 100;
            mouseX=e.getX()+frameX;
            mouseY=e.getY()+frameY;
            final int button=e.getButton();
            ActionListener taskPerformer = new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                   if(button==1&&keys[6]==false)
                    {
                        double xDiff=mouseX-(ragnar.getXPos()+ragnar.getLength()/2);
                        double yDiff=-(mouseY-(ragnar.getYPos()+35));
                        double rotationRequired;
                        if(xDiff!=0)
                        {
                            rotationRequired=-Math.atan(yDiff/xDiff);
                        }
                        else
                        {
                            rotationRequired=-Math.PI/2;     
                        }
                        if(yDiff>=0)
                        {
                            if(xDiff<0)
                            {
                                rotationRequired=rotationRequired+Math.PI;
                            }
                            if(ragnar.getAmmo()>0)
                            {
                                newWeapon(rotationRequired);
                                ragnar.setAmmo(ragnar.getAmmo()-1);
                            }
                        }
                    }
                    if(button==1&&keys[6])
                    {
                        if(ragnar.getFuel()>2000)
                        {
                            f.setActive(true);
                            ragnar.setFuel(ragnar.getFuel()-50);
                        }
                    }
                    if(button==3&&keys[8]==false)
                    {
                        if(ragnar.getBomb()>0)
                        {
                            ragnar.setBomb(ragnar.getBomb()-1);
                            addBomb();
                        }
                    }   
                    else if(button==3&&keys[8]==true)
                    {
                        if(ragnar.getMine()>0)
                        {
                            ragnar.setMine(ragnar.getMine()-1);
                            addMine();
                        }
                    }   
                }
            };
            if(weaponFire==null)
            {
                weaponFire=new Timer(delay, taskPerformer);

            }
            weaponFire.start();
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }

    /**
     * Invoked when the mouse is released.<link> Releasing the mouse invokes a command that 
     * leads to a change in the game.
     * @param e The specified MouseEvent context. 
     * @see MouseListener
     * @see MouseEvent
     * @author Richard Dong
     */
    @Override
    public void mouseReleased(MouseEvent e) 
    {
        try
        {
            if(weaponFire!=null)
            {
                weaponFire.stop();
                weaponFire=null;
            }
            if(f.getActive())
            {
                f.setActive(false);
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Creates a new weapon projectile to the list of active weapon objects.
     * @param radians the amount to rotate the weapon.
     * @author Richard Dong
     */
    public void newWeapon(double radians)
    {
        try
        {
            if(Ragnar.getLevel()>2)
            {
                currentWeapon=2;
            }
            int speed=15;
            double xSpd=speed*Math.cos(radians);
            double ySpd=speed*Math.sin(radians);
            double dxPos=ragnar.getXPos()-100+ragnar.getLength()/2;
            double dyPos=ragnar.getYPos()-70;
            double xPos=dxPos+100+100*Math.cos(radians);
            double yPos=dyPos+100+100*Math.sin(radians);
            BufferedImage img=w.rotateImage(radians);
            w.getWeaponList().add(new Weapon(currentWeapon,xPos,yPos,dxPos,dyPos,xSpd,ySpd,radians,img));
            MusicPlayer.startBulletSound();
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }       
    }
    
    /**
     * Adds a bomb object to the list of active bomb objects.
     * @author Richard Dong
     */
    public void addBomb()
    {
        try
        {
            bombList.add(new Bomb(ragnar.getXPos()+50,ragnar.getYPos()+ragnar.getWidth(),ragnar.getXSpd(),ragnar.getYSpd()+4));
            MusicPlayer.startBomb();
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Adds a mine object to the list of active bomb objects.
     * @author Richard Dong
     */
    public void addMine()
    {
        try
        {
            mineList.add(new Mine(0,ragnar.getXPos()+50,ragnar.getYPos()+ragnar.getWidth()/2));
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
        
    }

    /**
     * Invoked when the mouse is dragged.<link> Releasing the mouse invokes a command that 
     * leads to a change in the game.
     * @param e The specified MouseEvent context. 
     * @see MouseMotionListener
     * @see MouseEvent
     * @author Richard Dong
     */
    @Override
    public void mouseDragged(MouseEvent e) 
    {
        try
        {
            mouseX=e.getX()+frameX;
            mouseY=e.getY()+frameY;   
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    
    /**
     * Invoked when the mouse is moved.<link> Releasing the mouse invokes a command that 
     * leads to a change in the game.
     * @param e The specified MouseEvent context. 
     * @see MouseMotionListener
     * @see MouseEvent
     * @author Richard Dong
     */
    @Override
    public void mouseMoved(MouseEvent e) 
    {
        try
        {
            mouseX=e.getX()+frameX;
            mouseY=e.getY()+frameY;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
}