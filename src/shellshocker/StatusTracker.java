/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Creates a status tracker for the user-controlled character.<link> Tracks and
 * displays information such as health, points, shield, ammo, bomb, and fuel.
 * in the specified Graphics context.
 * @author Richard Dong
 */
public class StatusTracker 
{
    private int ammoLimit,healthLimit,pointLimit,fuelLimit,shieldLimit,bombLimit,mineLimit;
    private int ammoCount,healthCount,pointCount,fuelCount,shieldCount,bombCount,mineCount;
    private double time;
    private ArrayList<EnemySprite>enemyList=new ArrayList<>();
    private ArrayList<Boundary>boundList=new ArrayList<>();
    private ArrayList<Reactor>reactorList=new ArrayList<>();
    
    /**
     * Default constructor for the status tracker.<link> Takes in images for display
     * in the window.
     * @author Richard Dong
     */
    public StatusTracker()
    {
        try
        {
            pointLimit=1000;
            healthLimit=10000;
            fuelLimit=10000;
            shieldLimit=10000;
            bombLimit=10;
            ammoLimit=100;
            time=0.0;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }

    /**
     * Draws the status bar in the specified graphics context.
     * @param window the specified Graphics context.
     * @param x the x-coordinate to draw the Status Bar.
     * @param y the y-coordinate to draw the Status Bar.
     * @param width the width of the Status Bar.
     * @param height the height of the Status Bar.
     * @param mine boolean value to indicate whether or not the mine dropping mechanism is on.
     * @param ragnar the user-controlled object reference.
     * @param flame boolean value to indicate whether or not the flamethrower mechanism is on.
     * @author Richard Dong
     */
    public void drawStatusBar(Graphics window, int x, int y, int width, int height, Ragnar ragnar, boolean flame,boolean mine)
    {
        try
        {
            if(((int)(time*10))%10==0)
            {
                if(ragnar.getPoints()>1)
                {
                    ragnar.setPoints(ragnar.getPoints()-1);
                }
            }
            window.setColor(new Color(255,215,0));
            window.fillRect(x,y,width,height/5);
            Font myFont=new Font("Helvetica",Font.BOLD, 20);
            window.setColor(Color.BLACK);
            window.setFont(myFont);
            window.drawString("POINT:", x+width/20,y+height/20);
            window.drawString(pointCount+"",x+width/11,y+height/20);

            window.drawString("SHIELD:", x+width/5,y+height/20);
            String shield=shieldCount+"";
            int shieldLength=shield.length();
            if(shieldLength>2&&shieldCount>0)
            {
                shield=shield.substring(0,shieldLength-2)+"."+shield.substring(shieldLength-2);
                window.drawString(shield,x+width/4,y+50);
            }
            else
            {
                window.drawString("0.0",x+width/4,y+50);
            }



            window.drawString("AMMO:", x+2*width/5,y+height/20);
            window.drawString(ammoCount+"",x+9*width/20,y+height/20);

            window.drawString("BOMBS:", x+width/20,y+height/10);
            window.drawString(bombCount+"",x+width/10,y+height/10);
            window.drawString("MINES:", x+width/20,y+3*height/20);
            window.drawString(mineCount+"",x+width/10,y+3*height/20);

            window.drawString("HEALTH:", x+width/5,y+height/10);
            String health=healthCount+"";
            int healthLength=health.length();
            if(healthLength>2&&healthCount>0)
            {
                health=health.substring(0,healthLength-2)+"."+health.substring(healthLength-2);
                window.drawString(health,x+width/4,y+height/10);
            }
            else
            {
                window.drawString("0.0",x+width/4,y+height/10);
            }



            window.drawString("FUEL:", x+2*width/5,y+height/10);
            String fuel=fuelCount+"";
            int length=fuel.length();
            if(length>2)
            {
                fuel=fuel.substring(0,length-2)+"."+fuel.substring(length-2);
                window.drawString(fuel,x+9*width/20,y+height/10);
            }
            else
            {
                window.drawString("0.0",x+9*width/20,y+height/10);
            }


            window.drawString("TIME:", x+width/5,y+3*height/20);
            String displayTime=time+"";
            if(time<10)
            {
                displayTime=displayTime.substring(0,3);
            }
            else if(time<100)
            {
                displayTime=displayTime.substring(0,4);
            }
            else if(time<1000)
            {
                displayTime=displayTime.substring(0,5);
            }
            else if(time<10000)
            {
                displayTime=displayTime.substring(0,6);
            }
            window.drawString(displayTime, x+width/4,y+3*height/20);

            window.setColor(Color.GREEN);
            
            window.drawRect(x+width/8, y+height/40,width/20,height/40);
            window.fillRect(x+width/8,y+height/40,(int)(width/20*pointCount/(double)pointLimit), height/40);
            
            
            if(100*shieldCount/(double)shieldLimit<50)
            {
                window.setColor(Color.RED);
            }
            window.drawRect(x+3*width/10, y+height/40,width/20,height/40);
            window.fillRect(x+3*width/10,y+height/40,(int)(width/20*shieldCount/(double)shieldLimit), height/40);
            window.setColor(Color.GREEN);
            
            if(100*ammoCount/(double)ammoLimit<50)
            {
                window.setColor(Color.RED);
            }
            window.drawRect(x+width/2, y+height/40,width/20,height/40);
            window.fillRect(x+width/2,y+height/40,(int)(width/20*ammoCount/(double)ammoLimit), height/40);
            window.setColor(Color.GREEN);

            window.drawRect(x+width/8,y+ 3*height/40,width/20,height/40);
            window.fillRect(x+width/8,y+3*height/40,(int)(width/20*bombCount/(double)bombLimit), height/40);

            window.drawRect(x+width/8,y+5*height/40,width/20,height/40);
            window.fillRect(x+width/8,y+5*height/40,(int)(width/20*mineCount/(double)mineLimit), height/40);

            if(100*healthCount/(double)healthLimit<50)
            {
                window.setColor(Color.RED);
            }
            window.drawRect(x+3*width/10, y+3*height/40,width/20,height/40);
            window.fillRect(x+3*width/10,y+3*height/40,(int)(width/20*healthCount/(double)healthLimit),height/40);
            window.setColor(Color.GREEN);
            
            if(100*fuelCount/(double)fuelLimit<50)
            {
                window.setColor(Color.RED);
            }
            window.drawRect(x+width/2, y+3*height/40,width/20,height/40);
            window.fillRect(x+width/2,y+3*height/40,(int)(width/20*fuelCount/(double)fuelLimit),height/40);

            
            window.setColor(new Color(0,0,100));
            window.fillOval(x+4*width/5,y,width/10,height/5);
            window.setColor(Color.YELLOW);
            window.fillOval(x+17*width/20,y+width/20,10,10);
            window.setColor(new Color(128,0,0));
            if(flame==true)
            {
                window.drawString("FLAME ON",x+3*width/5,y+3*height/20);
            }
            window.drawString("CHECKPOINT:"+ragnar.getCheckPoint(),x+3*width/5,y+height/40);
            window.drawString("LEVEL:"+ragnar.getLevel(),x+3*width/5,y+height/20);
            if(mine==true)
            {
                window.drawString("MINE ON",x+3*width/5,y+height/10);
            }
            for(EnemySprite e : enemyList)
            {
                int xDiff=(int)(0.05*(e.getXPos()-ragnar.getXPos()));
                int yDiff=(int)(0.05*(e.getYPos()-ragnar.getYPos()));
                double distance=Math.pow(Math.pow(xDiff,2)+Math.pow(yDiff,2), 0.5);
                if(distance<90)
                {
                    window.fillOval(xDiff+x+17*width/20,yDiff+y+width/20,5,5);
                } 
            }

            window.setColor(new Color(0,100,0));
            for(Boundary b:boundList)
            {
                if(b instanceof Doors)
                {
                    DoorOpener s=((Doors)b).getOpener();
                    if(s!=null)
                    {
                        int xDiff=(int)(0.05*(s.getXPos()-ragnar.getXPos()));
                        int yDiff=(int)(0.05*(s.getYPos()-ragnar.getYPos()));
                        double distance=Math.pow(Math.pow(xDiff,2)+Math.pow(yDiff,2), 0.5);
                        if(distance<90)
                        {
                            window.fillOval(xDiff+x+17*width/20,yDiff+y+width/20,12,12);
                        } 
                    }
                }
            }
            for(Reactor r:reactorList)
            {                
                int xDiff=(int)(0.05*(r.getXPos()-ragnar.getXPos()));
                int yDiff=(int)(0.05*(r.getYPos()-ragnar.getYPos()));
                double distance=Math.pow(Math.pow(xDiff,2)+Math.pow(yDiff,2), 0.5);
                if(distance<90)
                {
                    window.fillOval(xDiff+x+17*width/20,yDiff+y+width/20,12,12);
                }
            }
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        
    }
    
    /**
     * Updates the information for the status tracker.
     * @param p the current point count.
     * @param pl the current point limit.
     * @param s the current shield value remaining.
     * @param al the current ammunition limit.
     * @param b the number of bombs remaining.
     * @param m the number of mines remaining.
     * @param a the amount of ammunition remaining. 
     * @param h the amount of health remaining.
     * @param sl the current shield limit.
     * @param f the amount of fuel remaining.
     * @param enemy an ArrayList full of EnemySprite objects.
     * @param bl the current bomb limit.
     * @param ml the current mine limit.
     * @param fl the current fuel limit.
     * @param bound an ArrayList full of Boundary objects.
     * @param hl the current health limit.
     * @author Richard Dong
     */
    public void update(int p,int s, int a, int b,int m, int h, int f,int pl, int sl, int al, int bl, int ml, int hl, int fl, ArrayList<Boundary> bound,ArrayList<EnemySprite>enemy,ArrayList<Reactor>reactor)
    {
        try
        {
            pointCount=p;
            shieldCount=s;
            ammoCount=a;
            bombCount=b;
            mineCount=m;
            healthCount=h;
            fuelCount=f;
            boundList=bound;
            enemyList=enemy; 
            reactorList=reactor;
            pointLimit=pl;
            shieldLimit=sl;      
            ammoLimit=al;
            bombLimit=bl;
            mineLimit=ml;
            healthLimit=hl;
            fuelLimit=fl;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        
    }
    
    /**
     * Starts a timer that keeps track of the user's current time playing the game.
     * @author Richard Dong
     */
    public void startTimer()
    {
        try
        {
            int delay = 100;
            ActionListener taskPerformer = new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   time=time+0.1;
                }
            };
            new Timer(delay, taskPerformer).start();
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }   
    }  
}
