/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Scans in data from text files to create the objects of the game.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public class ScanIn 
{        
    private Scanner scan;
    
    /**
     * Returns an ArrayList full of Boundary objects after scanning
     * data in from a text file.
     * @return boundaryList - the ArrayList full of Boundary objects.
     * @author Richard Dong
     */
    public ArrayList<Boundary>scanInBounds()
    {
        try
        {
            String[]strs=new String[10];
            ArrayList<Boundary>boundList=new ArrayList<>();
            ArrayList<Integer>xCoordinateList=new ArrayList<>();
            ArrayList<Integer>yCoordinateList=new ArrayList<>();
            ArrayList<Integer>typeList=new ArrayList<>();
            try
            {
                scan=new Scanner(new FileReader("datafiles/Platform.txt"));
                while(scan.hasNextLine())
                {	                
                    strs=scan.nextLine().split(",");
                    typeList.add(Integer.parseInt(strs[0])); 
                    xCoordinateList.add(Integer.parseInt(strs[1]));
                    yCoordinateList.add(Integer.parseInt(strs[2]));
                }	

            }
            catch(FileNotFoundException | NumberFormatException ex)
            {
                ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
            }
            for(int x=0;x<typeList.size();x++)
            {
                boundList.add(new Platforms(typeList.get(x),xCoordinateList.get(x),yCoordinateList.get(x)));
            }
            xCoordinateList.clear();
            yCoordinateList.clear();
            typeList.clear();
            ArrayList<Integer> lengths=new ArrayList<>();
            ArrayList<Integer> heights=new ArrayList<>();
            ArrayList<Integer> dxCoordinateList=new ArrayList<>();
            ArrayList<Integer> dyCoordinateList=new ArrayList<>();
            ArrayList<Integer> dlengths=new ArrayList<>();
            ArrayList<Integer> dheights=new ArrayList<>();
            try
            {
                scan=new Scanner(new FileReader("datafiles/Doors.txt"));
                while(scan.hasNextLine())
                {	                
                    strs=scan.nextLine().split(",");
                    typeList.add(Integer.parseInt(strs[0])); 
                    xCoordinateList.add(Integer.parseInt(strs[1]));
                    yCoordinateList.add(Integer.parseInt(strs[2]));
                    lengths.add(Integer.parseInt(strs[3]));
                    heights.add(Integer.parseInt(strs[4]));
                    dxCoordinateList.add(Integer.parseInt(strs[5]));
                    dyCoordinateList.add(Integer.parseInt(strs[6]));
                    dlengths.add(Integer.parseInt(strs[7]));
                    dheights.add(Integer.parseInt(strs[8]));     
                }	

            }
            catch(FileNotFoundException | NumberFormatException ex)
            {
                ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
            }		
            for(int x=0;x<typeList.size();x++)
            {
                if(dxCoordinateList.get(x)!=-1)
                {
                    boundList.add(new Doors(typeList.get(x),xCoordinateList.get(x),
                    yCoordinateList.get(x),lengths.get(x),heights.get(x),new DoorOpener
                    (dxCoordinateList.get(x),dyCoordinateList.get(x),dlengths.get(x),dheights.get(x),100)));
                }
                else
                {
                    boundList.add(new Doors(typeList.get(x),xCoordinateList.get(x),yCoordinateList.get(x),lengths.get(x),heights.get(x),null));
                }


            }
            xCoordinateList.clear();
            yCoordinateList.clear();
            typeList.clear();
            lengths.clear();
            heights.clear();
            try
            {
                scan=new Scanner(new FileReader("datafiles/ElectricBounds.txt"));

                while(scan.hasNextLine())
                {
                    strs=scan.nextLine().split(",");
                    typeList.add(Integer.parseInt(strs[0])); 
                    xCoordinateList.add(Integer.parseInt(strs[1]));
                    yCoordinateList.add(Integer.parseInt(strs[2]));
                    lengths.add(Integer.parseInt(strs[3]));
                    heights.add(Integer.parseInt(strs[4]));
                }	
            }
            catch(FileNotFoundException | NumberFormatException ex)
            {
                ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
            }
            for(int x=0;x<typeList.size();x++)
            {
                boundList.add(new ElectricBounds(typeList.get(x),xCoordinateList.get(x),yCoordinateList.get(x),lengths.get(x),heights.get(x)));
            }		
            return boundList;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return null;
    }
    
    /**
     * Returns an ArrayList full of EnemySprite objects after scanning
     * data in from a text file.
     * @return enemyList - the ArrayList full of Boundary objects.
     * @author Richard Dong
     */
    public ArrayList<EnemySprite>scanInEnemy()
    {
        try
        {
            String[]strs=new String[10];
            ArrayList<EnemySprite>enemyList=new ArrayList<>();
            ArrayList<Integer>xCoordinateList=new ArrayList<>();
            ArrayList<Integer>yCoordinateList=new ArrayList<>();
            ArrayList<Integer>typeList=new ArrayList<>();

            try
            {
                scan=new Scanner(new FileReader("datafiles/Tower.txt"));
                while(scan.hasNextLine())
                {	                
                    strs=scan.nextLine().split(",");
                    typeList.add(Integer.parseInt(strs[0])); 
                    xCoordinateList.add(Integer.parseInt(strs[1]));
                    yCoordinateList.add(Integer.parseInt(strs[2]));

                }	
            }
            catch(FileNotFoundException | NumberFormatException ex)
            {
                ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
            }	
            for(int x=0;x<typeList.size();x++)
            {
                enemyList.add(new Tower(typeList.get(x),xCoordinateList.get(x),yCoordinateList.get(x),100));
            }
            xCoordinateList.clear();
            yCoordinateList.clear();
            typeList.clear();
            ArrayList<Integer> xSpeedList=new ArrayList<>();
            ArrayList<Integer> ySpeedList=new ArrayList<>();
            ArrayList<Integer> healthList=new ArrayList<>();
            
            try
            {
                scan=new Scanner(new FileReader("datafiles/Enemy.txt"));
                while(scan.hasNextLine())
                {	                
                    strs=scan.nextLine().split(",");
                    typeList.add(Integer.parseInt(strs[0])); 
                    xCoordinateList.add(Integer.parseInt(strs[1]));
                    yCoordinateList.add(Integer.parseInt(strs[2]));
                    xSpeedList.add(Integer.parseInt(strs[3]));
                    ySpeedList.add(Integer.parseInt(strs[4]));
                    healthList.add(Integer.parseInt(strs[5]));

                }	
            }
            catch(FileNotFoundException | NumberFormatException ex)
            {
                ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
            }
            for(int x=0;x<typeList.size();x++)
            {
                enemyList.add(new Enemy(typeList.get(x),xCoordinateList.get(x),yCoordinateList.get(x),xSpeedList.get(x),ySpeedList.get(x),healthList.get(x)));
            }
            xCoordinateList.clear();
            yCoordinateList.clear();
            typeList.clear();
            ySpeedList.clear();
            xSpeedList.clear();
            healthList.clear();

            try
            {
                scan=new Scanner(new FileReader("datafiles/SmartEnemy.txt"));
                while(scan.hasNextLine())
                {	                
                    strs=scan.nextLine().split(",");
                    typeList.add(Integer.parseInt(strs[0])); 
                    xCoordinateList.add(Integer.parseInt(strs[1]));
                    yCoordinateList.add(Integer.parseInt(strs[2]));
                    xSpeedList.add(Integer.parseInt(strs[3]));
                    ySpeedList.add(Integer.parseInt(strs[4]));
                    healthList.add(Integer.parseInt(strs[5]));           
                }	
            }
            catch(FileNotFoundException | NumberFormatException ex)
            {
                ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
            }	
            for(int x=0;x<typeList.size();x++)
            {
                enemyList.add(new SmartEnemy(typeList.get(x),xCoordinateList.get(x),yCoordinateList.get(x),xSpeedList.get(x),ySpeedList.get(x),healthList.get(x)));
            }
            xCoordinateList.clear();
            yCoordinateList.clear();
            typeList.clear();
            ySpeedList.clear();
            xSpeedList.clear();
            healthList.clear();
            try
            {
                scan=new Scanner(new FileReader("datafiles/Boss.txt"));
                while(scan.hasNextLine())
                {	                
                    strs=scan.nextLine().split(",");
                    typeList.add(Integer.parseInt(strs[0])); 
                    xCoordinateList.add(Integer.parseInt(strs[1]));
                    yCoordinateList.add(Integer.parseInt(strs[2]));
                    xSpeedList.add(Integer.parseInt(strs[3]));
                    ySpeedList.add(Integer.parseInt(strs[4]));
                    healthList.add(Integer.parseInt(strs[5]));           
                }	
            }
            catch(FileNotFoundException | NumberFormatException ex)
            {
                ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
            }	
            for(int x=0;x<typeList.size();x++)
            {
                enemyList.add(new Boss(typeList.get(x),xCoordinateList.get(x),yCoordinateList.get(x),xSpeedList.get(x),ySpeedList.get(x),healthList.get(x)));
            }
            return enemyList;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        
        return null;
        
    }
    
    /**
     * Returns an ArrayList full of Box objects after scanning
     * data in from a text file.
     * @return boxList - the ArrayList full of Box objects.
     * @author Richard Dong
     */
    public ArrayList<Box>scanInBox()
    {
        try
        {
            ArrayList<Integer> xCoordinateList=new ArrayList<>();
            ArrayList<Integer> yCoordinateList=new ArrayList<>();
            ArrayList<Integer> type=new ArrayList<>();
            ArrayList<Box>boxList=new ArrayList<>();
            String[]strs=new String[3];
            try
            {
                scan=new Scanner(new FileReader("datafiles/Box.txt"));
                while(scan.hasNextLine())
                {	                
                    strs=scan.nextLine().split(",");
                    type.add(Integer.parseInt(strs[0])); 
                    xCoordinateList.add(Integer.parseInt(strs[1]));
                    yCoordinateList.add(Integer.parseInt(strs[2]));     
                }	
            }
            catch(FileNotFoundException | NumberFormatException ex)
            {
                ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
            }
            for(int x=0;x<type.size();x++)
            {
                boxList.add(new Box(type.get(x),xCoordinateList.get(x),yCoordinateList.get(x)));
            }	
            return boxList;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return null;
    }
    
    /**
     * Returns an ArrayList full of Reactor objects after scanning
     * data in from a text file.
     * @return reactorList - the ArrayList full of Reactor objects.
     * @author Richard Dong
     */
    public ArrayList<Reactor>scanInReactor()
    {
        try
        {
            ArrayList<Integer> xCoordinateList=new ArrayList<>();
            ArrayList<Integer> yCoordinateList=new ArrayList<>();
            ArrayList<Integer> health=new ArrayList<>();
            ArrayList<Reactor>reactorList=new ArrayList<>();
            String[]strs=new String[3];
            try
            {
                scan=new Scanner(new FileReader("datafiles/Reactor.txt"));
                while(scan.hasNextLine())
                {	                
                    strs=scan.nextLine().split(",");
                    
                    xCoordinateList.add(Integer.parseInt(strs[0]));
                    yCoordinateList.add(Integer.parseInt(strs[1])); 
                    health.add(Integer.parseInt(strs[2]));     
                }	
            }
            catch(FileNotFoundException | NumberFormatException ex)
            {
                ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
            }
            for(int x=0;x<health.size();x++)
            {
                reactorList.add(new Reactor(xCoordinateList.get(x),yCoordinateList.get(x),health.get(x)));
            }	
            return reactorList;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return null;
    }
}
