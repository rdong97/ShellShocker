/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Loads and plays music and sound effects for the ShellShocker game.
 * @author Richard Dong
 * @version 2.0 Last modified 2/6/2014
 */
public class MusicPlayer 
{
    private static InputStream scanner;
    private static AudioStream music;    
    private static AudioStream bullet;   
    private static AudioStream explosion;   
    private static AudioStream laser;
    private static AudioStream flamethrower;
    private static AudioStream box;
    private static AudioStream bomb;
    private static AudioStream boss;
    private static AudioStream levelUp;
    private static AudioStream level;
    private static AudioPlayer musicPlayer = AudioPlayer.player;
    private static boolean themeadded;

   /**
    * Loads and plays the theme music for the ShellShocker game menu.
    * @author Richard Dong
    */
    public static void startTheme()
    {
        if(themeadded==false)
        {
            try
            {
                scanner=new FileInputStream("musicFiles/Theme.wav");
                music=new AudioStream(scanner);
                themeadded=true;
            }
            catch(IOException ex)
            {
                System.out.println("Theme music could not play");
            }
            musicPlayer.start(music);
        }
        
    }
    /**
    * Stops sound effects for the ShellShocker game menu.
    * @author Richard Dong
    */
    public static void stopAll()
    {
        musicPlayer.stop(flamethrower);
        musicPlayer.stop(bomb);
        musicPlayer.stop(boss);
        musicPlayer.stop(level);
    }
   /**
    * Stops the theme music for the ShellShocker game menu.
    * @author Richard Dong
    */
    public static void stopTheme()
    {
        musicPlayer.stop(music);
    }
   /**
    * Loads and plays the bomb whistle sound effect for the ShellShocker game.
    * @author Richard Dong
    */
    public static void startBomb()
    {
        try
        {
            scanner=new FileInputStream("musicFiles/Bomb.wav");
            bomb=new AudioStream(scanner);
        }
        catch(IOException ex)
        {
            System.out.println("Bomb music could not play");
        }
        musicPlayer.start(bomb);  
    }
   /**
    * Loads and starts the boss music music for the ShellShocker game.
    * @author Richard Dong
    */
    public static void startBoss()
    {
        try
        {
            scanner=new FileInputStream("musicFiles/BossTheme.wav");
            boss=new AudioStream(scanner);
        }
        catch(IOException ex)
        {
            System.out.println("Boss music could not play");
        }
        musicPlayer.start(boss);  
    }
   /**
    * Loads and starts the regular level music for the ShellShocker game.
    * @author Richard Dong
    */
    public static void startLevelMusic()
    {
        try
        {
            scanner=new FileInputStream("musicFiles/LevelMusic.wav");
            level=new AudioStream(scanner);
        }
        catch(IOException ex)
        {
            System.out.println("Level music could not play");
        }
        musicPlayer.start(level); 
    }
   /**
    * Stops the regular level music for the ShellShocker game.
    * @author Richard Dong
    */
    public static void stopLevelMusic()
    {
        musicPlayer.stop(level); 
    }
    /**
    * Stops the boss level music for the ShellShocker game.
    * @author Richard Dong
    */
    public static void stopBossMusic()
    {
        musicPlayer.stop(boss); 
    }
    /**
    * Loads and plays the level up sound effect for the ShellShocker game.
    * @author Richard Dong
    */
    public static void startLevelUp()
    {
        try
        {
            scanner=new FileInputStream("musicFiles/LevelUp.wav");
            levelUp=new AudioStream(scanner);
        }
        catch(IOException ex)
        {
            System.out.println("Level Up music could not play");
        }
        musicPlayer.start(levelUp);  
    }
   /**
    * Stops the bomb whistle sound effect for the ShellShocker game.
    * @author Richard Dong
    */
    public static void stopBomb()
    {
        musicPlayer.stop(bomb);
    }
   /**
    * Loads and starts a bullet sound effect for the ShellShocker game.
    * @author Richard Dong
    */
    public static void startBulletSound()
    {
        try
        {
            scanner=new FileInputStream("musicFiles/BulletSound.wav");
            bullet=new AudioStream(scanner);
        }
        catch(IOException ex)
        {
            System.out.println("Bullet music could not play");
        }
        musicPlayer.start(bullet);
    }
   /**
    * Loads and starts an explosion sound effect for the ShellShocker game.
    * @author Richard Dong
    */
    public static void startExplosionSound()
    {
        try
        {
            scanner=new FileInputStream("musicFiles/Explosion.wav");
            explosion=new AudioStream(scanner);
        }
        catch(IOException ex)
        {
            System.out.println("Explosion music could not play");
        }
        musicPlayer.start(explosion);
    }
   /**
    * Loads and starts a laser sound effect for the ShellShocker game.
    * @author Richard Dong
    */
    public static void startLaserSound()
    {
        try
        {
            scanner=new FileInputStream("musicFiles/Laser.wav");
            laser=new AudioStream(scanner);
        }
        catch(IOException ex)
        {
            System.out.println("Laser music could not play");
        }
        musicPlayer.start(laser);
    }
   /**
    * Loads and starts a flamethrower sound effect for the ShellShocker game.
    * @author Richard Dong
    */
    public static void startFlamethrower()
    {
        try
        {
            scanner=new FileInputStream("musicFiles/Flamethrower.wav");
            flamethrower=new AudioStream(scanner);
        }
        catch(IOException ex)
        {
            System.out.println("Flamethrower music could not play");
        }
        musicPlayer.start(flamethrower);
    }
   /**
    * Loads and starts a sound effect for the ShellShocker game when the user picks up a power-up.
    * @author Richard Dong
    */
    public static void startBoxSound()
    {
        try
        {
            scanner=new FileInputStream("musicFiles/Box.wav");
            box=new AudioStream(scanner);
        }
        catch(IOException ex)
        {
            System.out.println("Box music could not play");
        }
        musicPlayer.start(box);
    }
   /**
    * Stops a flamethrower sound effect for the ShellShocker game.
    * @author Richard Dong
    */
    public static void stopFlamethrower()
    {
        musicPlayer.stop(flamethrower);
    }
}
