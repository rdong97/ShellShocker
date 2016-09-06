/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shellshocker;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Adds information concerning run-time errors of the game to an error logging file.
 * @author Richard Dong
 */
public class ErrorLogger 
{
    private static BufferedWriter bwFile;
    private static Date date;
    private static Scanner scan;
    private static boolean fatal;
    
    /**
     * Constructor for the ErrorLogger, initializes a Date object
     * to log when the game was run.
     * @author Richard Dong.
     */
    public ErrorLogger()
    {
        date=new Date();
    }
    
    /**
     * Logs the error into the text file.
     * @param level indicates the severity of the error.
     * @param location indicates which class the error occurred.
     * @param error indicates what error occurred.
     * @throws IOException when error logging file cannot be located.
     * @author Richard Dong.
     */
    public static void logError(String level, String location, String error)
    {
        try
        { 
            date=new Date();
            if(fatal)
            {
                TheGame.fatalError();
            }
            else
            {
                scan=new Scanner(new FileReader("datafiles/ErrorLogger.txt"));
                ArrayList<String>errors=new ArrayList<>();
                while(scan.hasNext())
                {
                    errors.add(scan.nextLine());
                }
                bwFile=new BufferedWriter(new FileWriter("datafiles/ErrorLogger.txt"));

                String toWrite=date.toString()+" "+level+" "+location+" "+error;
                System.out.println(toWrite);
                for(String s:errors)
                {
                    bwFile.write(s+"\n");
                }
                bwFile.append(toWrite);
                bwFile.close();    
                fatal=true;
            }
            
            
        }
        catch(IOException e)
        {
            System.out.println("ErrorLogger.txt could not be located");
        }
    }
}
