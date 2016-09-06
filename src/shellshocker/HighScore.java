/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

/**
 * Creates a high score statistic.<link> Each high score contains a name and score.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public class HighScore 
{
    private String name;
    private int score;
    
    /**
     * Constructor for the high score object.
     * @param n the name of the high score.
     * @param s the score of the high score.
     */
    public HighScore(String n,int s)
    {
        try
        {
            name=n;
            score=s;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Returns the name of the HighScore object.
     * @return name - the name of the HighScore object.
     * @author Richard Dong
     */
    public String getName()
    {
        try
        {
            return name;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return null;
        
    }
    
    /**
     * Returns the score of the HighScore object.
     * @return score - the score of the HighScore object.
     * @author Richard Dong
     */
    public int getScore()
    {
        try
        {
            return score;
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
        return 0;
    } 
}
