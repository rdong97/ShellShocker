/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

/**
 * A profile object that represents each player account.<link> Each profile has
 * statistics of username, password, level, checkpoint, health, and points.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public class Profile 
{
    private String userName,passWord;
    private int checkPoint, level, health,point;
    
    /**
     * Creates a profile object.
     * @param user the username of the profile.
     * @param pass the password of the profile.
     * @param c the checkpoint of the profile.
     * @param l the level of the profile.
     * @param h the health of the profile.
     * @param p the points of the profile.
     * @author Richard Dong
     */
    public Profile(String user, String pass, int c, int l, int h,int p)
    {
        try
        {
            userName=user;
            passWord=pass;
            checkPoint=c;
            level=l;
            health=h;
            point=p;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Returns the username of the profile object which is used for login.
     * @return userName - the username of the profile object.
     * @author Richard Dong
     */
    public String getUserName()
    {
        try
        {
            return userName;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return null;
        
    }
    /**
     * Returns the password of the profile object which is used for login.
     * @return passWord - the password of the profile object.
     * @author Richard Dong
     */
    public String getPassWord()
    {
        try
        {
            return passWord;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return null;
    }
    /**
     * Returns the checkpoint of the profile object which is used to determine starting location.
     * @return checkPoint - the checkPoint of the profile object.
     * @author Richard Dong
     */
    public int getCheckPoint()
    {
        try
        {
            return checkPoint;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    /**
     * Returns the level of the profile object which determines the attributes of the user-controlled character.
     * @return level - the level of the profile object.
     * @author Richard Dong
     */
    public int getLevel()
    {
        try
        {
            return level;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    /**
     * Returns the points of the profile object.
     * @return point - the points of the profile object.
     * @author Richard Dong
     */
    public int getPoint()
    {
        try
        {
            return point;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
    /**
     * Returns the health of the profile object.
     * @return health - the health of the profile object.
     * @author Richard Dong
     */
    public int getHealth()
    {
        try
        {
            return health;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    }
}
