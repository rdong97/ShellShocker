/**
 * Runs a side-scrolling, action/adventure game. 
 */
package shellshocker;


import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Creates an interactive menu system that users will be able
 * to navigate around.<link> The game menu has logins that allow returning users to
 * access their personal accounts.<link> Users who wish to start new profiles may 
 * generate accounts using the menu.<link> Interactive tutorials and credits accompany
 * the other features of the menu.
 * @author Richard Dong
 * @version 2.0. Last edited 2/6/2014
 */
public class TheGame extends JFrame
{
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int width = screenSize.width;
    private static final int height = screenSize.height;
    private static String usernameToSave,passwordToSave;
    public static JFrame theGame;
    private static ControlCentral game;
    private Image menu,tutorial0,tutorial1,tutorial2,tutorial3;
    private Image tutorial4, tutorial5,tutorial6,tutorial7,tutorial8,creditScreen;
    private static Image pauseDisplay,scoreDisplay,addScore,deathScreen;
    private Image loginScreen,newUserScreen,backArrow,forwardArrow;
    private ArrayList<Image>tutorList;
    private static ArrayList<Profile>profileList=new ArrayList<>();
    private static ArrayList<HighScore>scoreList=new ArrayList<>();
    private static Scanner scan;
    private static BufferedWriter bwFile;
    private static JPanel container;
    private int slideCount=0;
    
    
    /**
     * Default constructor for class TheGame.<link> Creates a JFrame which will
     * contain the game.<link> The method also calls out to methods that start the game
     * by starting a menu and take in all images used in the game menu. 
     * @author Richard Dong
     */
    public TheGame()
    {       
        try
        {     
            theGame=((JFrame)this);
            theGame.setSize(width,height);
            theGame.setResizable(true);
            theGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            container = new JPanel();
            theGame.getContentPane().add(container);
            container.setLayout(null);
            container.setBounds(theGame.getBounds());
            container.setOpaque(true);
            container.setBackground(Color.BLACK);         
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }   
    }
    
    /**
     * Gets images used in the menu.<link> Adds the images to appropriate locations
     * @author Richard Dong
     */
    public void getSlides()
    {
        try
        {
            menu=Toolkit.getDefaultToolkit().getImage("resources/Menu.png");
            pauseDisplay=Toolkit.getDefaultToolkit().getImage("resources/PauseScreen.png");
            addScore=Toolkit.getDefaultToolkit().getImage("resources/EnterScreen.png");
            scoreDisplay=Toolkit.getDefaultToolkit().getImage("resources/ScoreBoard.png");
            deathScreen=Toolkit.getDefaultToolkit().getImage("resources/DeathScreen.png");
            tutorial0=Toolkit.getDefaultToolkit().getImage("resources/Tutorial0.png");
            tutorial1=Toolkit.getDefaultToolkit().getImage("resources/Tutorial1.png");
            tutorial2=Toolkit.getDefaultToolkit().getImage("resources/Tutorial2.png");
            tutorial3=Toolkit.getDefaultToolkit().getImage("resources/Tutorial3.png");
            tutorial4=Toolkit.getDefaultToolkit().getImage("resources/Tutorial4.png");
            tutorial5=Toolkit.getDefaultToolkit().getImage("resources/Tutorial5.png");
            tutorial6=Toolkit.getDefaultToolkit().getImage("resources/Tutorial6.png");
            tutorial7=Toolkit.getDefaultToolkit().getImage("resources/Tutorial7.png");
            tutorial8=Toolkit.getDefaultToolkit().getImage("resources/Tutorial8.png");
            creditScreen=Toolkit.getDefaultToolkit().getImage("resources/Credits.png");
            loginScreen=Toolkit.getDefaultToolkit().getImage("resources/LoginScreen.png");
            newUserScreen=Toolkit.getDefaultToolkit().getImage("resources/NewUserScreen.png");
            backArrow=Toolkit.getDefaultToolkit().getImage("resources/BackArrow.png");
            forwardArrow=Toolkit.getDefaultToolkit().getImage("resources/ForwardArrow.png");
            tutorList=new ArrayList<>();
            tutorList.add(tutorial0);
            tutorList.add(tutorial1);
            tutorList.add(tutorial2);
            tutorList.add(tutorial3);
            tutorList.add(tutorial4);
            tutorList.add(tutorial5);
            tutorList.add(tutorial6);
            tutorList.add(tutorial7);
            tutorList.add(tutorial8);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        } 
    }
    /**
     * Removes the current profile accessed by the user from the list of profiles.
     * @throws IOException - Login file could not be written into.
     * @author Richard Dong
     */ 
    public static void removeProfile()
    {
        Profile toChange=null;
        for(Profile p:profileList)
        {
            if(p.getUserName().equals(usernameToSave)&&p.getPassWord().equals(passwordToSave))
            {
                toChange=p;
            }
        }
        if(toChange!=null)
        {
            profileList.remove(toChange);
        }
        try
        {
            bwFile=new BufferedWriter(new FileWriter("datafiles/Login.txt"));
            
            for(Profile p:profileList)
            {
                bwFile.write(p.getUserName()+","+p.getPassWord()+","+p.getCheckPoint()+","+p.getLevel()+","+p.getHealth()+","+p.getPoint()+"\n");
            }
            bwFile.close();
        }
        catch(IOException ex)
        {
            JOptionPane frame=new JOptionPane();
            JOptionPane.showMessageDialog(frame, "Fatal Error Occurred: User Profile Database Not Found!");
            frame.show();
            System.exit(0); 
        }
    }
    /**
     * Creates a screen with options that is presented to the user at the end of the game.
     * @throws IOException - ScoreBoard file could not be written into.
     * @author Richard Dong
     */
    public static void endGame(final int score)
    {
        try
        {
            game.setVisible(false);
            container.setVisible(true);
            container.removeAll();
            MusicPlayer.stopAll();
            createScoreBoard();
            removeProfile();
            final JTextField nameText = new JTextField(10);
            nameText.setBounds((int)(0.7*width),(int)(0.44*height),(int)(0.1*width),(int)(0.03*height));
            final JButton add=new JButton("Add");
            add.setBounds((int)(0.75*width),(int)(0.7*height),(int)(0.1*width),(int)(0.05*height));
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String namevalue=nameText.getText();
                    scoreList.add(new HighScore(namevalue,score));
                    try
                    {
                        bwFile=new BufferedWriter(new FileWriter("datafiles/ScoreBoard.txt"));
                        for(HighScore h:scoreList)
                        {
                            bwFile.write(h.getName()+","+h.getScore()+"\n");
                        }
                        bwFile.close();
                        container.removeAll();
                        container.updateUI();
                        displayScores();
                    }
                    catch(Exception ex)
                    {
                        JOptionPane frame=new JOptionPane();
                        JOptionPane.showMessageDialog(frame, "Fatal Error Occurred: Score Database Not Found!");
                        frame.show();
                        System.exit(0); 
                    }
                }
            });
            
            
            JButton exit=new JButton("Exit");
            exit.setBounds((int)(0.75*width),(int)(0.8*height),(int)(0.1*width),(int)(0.05*height));
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });
            JLabel addPic=new JLabel(new ImageIcon(addScore));
            addPic.setBounds(0,0,width,height);
            container.add(add);
            container.add(exit);
            container.add(nameText);
            container.add(addPic);
        }
        catch (Exception ex) 
        {
            JOptionPane frame=new JOptionPane();
            JOptionPane.showMessageDialog(frame, "Fatal Error Occurred: Score Database Not Found!");
            frame.show();
            System.exit(0); 
        }
    }
    /**
     * Sorts high scores into descending order to be presented on a scoreboard.
     * @author Richard Dong
     */
    public static void sortScores()
    {
        int max;
        if(scoreList.size()>1)
        {
            for (int i = 0; i < scoreList.size()-1; i++) 
            {
                max=i;
                for (int j = i + 1; j < scoreList.size(); j++) 
                {
                    if (scoreList.get(j).getScore()>scoreList.get(max).getScore())
                    {
                        max = j;
                    }
                }
                if (max != i) 
                {
                    HighScore temp = scoreList.get(max);
                    scoreList.set(max,scoreList.get(i));
                    scoreList.set(i,temp);
                }
            }
        }
    }
    /**
     * Creates a score board screen with options that is presented to the user at the end of the game.
     * @author Richard Dong
     */
    public static void displayScores()
    {
        sortScores();
        
        for(int i=0;i<scoreList.size()&&i<5;i++)
        {
            if(scoreList.get(i)!=null)
            {
                JLabel toAdd=new JLabel((i+1)+"------"+scoreList.get(i).getName()+"----------------"+scoreList.get(i).getScore());
                toAdd.setBounds((int)(0.25*width),(int)((0.1*(i+3)*height)),(int)(0.3*width),(int)(0.2*height));
                toAdd.setForeground(Color.RED);
                container.add(toAdd);
            }
        }
        
        JButton exit=new JButton("Exit");
            exit.setBounds((int)(0.75*width),(int)(0.8*height),(int)(0.1*width),(int)(0.05*height));
            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });
            JLabel scorePic=new JLabel(new ImageIcon(scoreDisplay));
            scorePic.setBounds(0,0,width,height);
        container.add(exit);
        container.add(scorePic);
    }
    /**
     * Creates a score board list from a 
     * saved file that is later presented to the user at the end of the game.
     * @throws IOException - ScoreBoard file could not be read from.
     * @author Richard Dong
     */
    public static void createScoreBoard()
    {
        String[]chopper=new String[2];
        try
        {
            scan=new Scanner(new FileReader("datafiles/ScoreBoard.txt"));
            while(scan.hasNextLine())
            {
                chopper=scan.nextLine().split(",");
                String n=chopper[0];
                int s=Integer.parseInt(chopper[1]);
                scoreList.add(new HighScore(n,s));
            }  
        }
        catch(FileNotFoundException | NumberFormatException ex)
        {
            JOptionPane frame=new JOptionPane();
            JOptionPane.showMessageDialog(frame, "Fatal Error Occurred: Score File Not Found!");
            frame.show();
            System.exit(0); 
        }   
    }
    /**
     * Starts the game by removing the menu from the JFrame and adding the game.
     * @param p the current profile used to start the game.
     * @author Richard Dong
     */
    public static void startGame(Profile p)
    {
        try
        {
            container.setVisible(false);
            game = new ControlCentral();
            game.setProfile(p);
            game.setStartLocation();
            game.setVisible(true);
            ((Component)game).setFocusable(true);
            theGame.getContentPane().add(game); 
            MusicPlayer.stopTheme();
            ControlCentral.unPause();
        }
        catch(Exception ex)
        {
            JOptionPane frame=new JOptionPane();
            JOptionPane.showMessageDialog(frame, "Fatal Error Occurred: Game Unable To Start");
            frame.show();
            System.exit(0); 
        }   
    }
    
    /**
     * Runs the start page of the menu.<link> The start page will include buttons
     * that provide options to the user for accessing the game along with 
     * other information about the game.
     * @author Richard Dong
     */ 
    public void runStartPage()
    {
        try
        {
            getSlides();
            createProfiles();
            MusicPlayer.startTheme();
            JButton login=new JButton("Existing Users Login");
            login.setBounds((int)(0.40*width),(int)(0.35*height),(int)(0.2*width),(int)(0.05*height));
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    container.removeAll();
                    container.updateUI();
                    runLoginPage();
                }
            });

            JButton newUser=new JButton("Create New Profile");
            newUser.setBounds((int)(0.40*width),(int)(0.45*height),(int)(0.2*width),(int)(0.05*height));
            newUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    container.removeAll();
                    container.updateUI();
                    runNewUser();
                }
            });

            JButton tutorial=new JButton("Tutorials");
            tutorial.setBounds((int)(0.45*width),(int)(0.55*height),(int)(0.1*width),(int)(0.05*height)); 
            tutorial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    container.removeAll();
                    container.updateUI();
                    slideCount=0;
                    runTutorial();
                }
            });

            JButton credit=new JButton("Credits");  
            credit.setBounds((int)(0.45*width),(int)(0.65*height),(int)(0.1*width),(int)(0.05*height));
            credit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    container.removeAll();
                    container.updateUI();
                    runCredits();
                }
            });

            JButton exit=new JButton("Exit");  
            exit.setBounds((int)(0.45*width),(int)(0.75*height),(int)(0.1*width),(int)(0.05*height));
            exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    container.removeAll();
                    container.updateUI();
                    System.exit(0);
                }
            });


            JLabel menuPic=new JLabel(new ImageIcon(menu));
            menuPic.setBounds(0,0,width,height);


            container.add(login);
            container.add(newUser);
            container.add(tutorial);
            container.add(credit);
            container.add(exit);
            container.add(menuPic);     

        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Runs a navigable tutorial screen system intended to teach the game user the 
     * basic information about the ShellShocker game.
     * @author Richard Dong
     */
    public void runTutorial()
    {
        try
        {
            JButton previous=new JButton(new ImageIcon(backArrow));
            previous.setBounds(0,0,(int)(0.1*getWidth()),(int)(0.05*getHeight()));
            previous.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    slideCount--;
                    if(slideCount<0)
                    {
                        container.removeAll();
                        container.updateUI();
                        runStartPage();
                    }
                    else
                    {
                        container.removeAll();
                        container.updateUI();
                        runTutorial();
                    }
                }
            });

            JButton next=new JButton(new ImageIcon(forwardArrow));
            next.setBounds((int)(0.9*getWidth()),0,(int)(0.1*getWidth()),(int)(0.05*getHeight()));
            next.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    slideCount++;
                    container.removeAll();
                    container.updateUI();
                    runTutorial();
                }
            });

                JLabel tutPic=new JLabel(new ImageIcon(tutorList.get(slideCount)));
                tutPic.setBounds(0,0,width,height);

                container.add(previous);
                if(slideCount<8)
                {
                    container.add(next);
                }

                container.add(tutPic);
            }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
     }
        
        
    /**
     * Runs a screen containing the credits for the game.
     * @author Richard Dong
     */
    public void runCredits()
    {
        try
        {
            JButton previous=new JButton(new ImageIcon(backArrow));
            previous.setBounds(0,0,(int)(0.1*getWidth()),(int)(0.05*getHeight()));
            previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                container.removeAll();
                container.updateUI();
                runStartPage(); 

            }
            });
            JLabel credPic=new JLabel(new ImageIcon(creditScreen));
            credPic.setBounds(0,0,width,height);
            container.add(previous);
            container.add(credPic);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    
    /**
     * Runs a page containing a login system in which a user enters a 
     * username and password in order to access a profile.
     * @throws FileNotFoundException - Login text file not found
     * @throws NumberFormatException - Login text file information formatting 
     *                                 does not match format required 
     * @author Richard Dong
     */
    public void runLoginPage()
    { 
        try
        {
            JButton previous=new JButton(new ImageIcon(backArrow));
            previous.setBounds(0,0,(int)(0.1*getWidth()),(int)(0.05*getHeight()));
            previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                container.removeAll();
                container.updateUI();
                runStartPage(); 
            }
            });
            JLabel loginPic=new JLabel(new ImageIcon(loginScreen));
            loginPic.setBounds(0,0,width,height);
            container.add(previous);
            final JTextField userText = new JTextField(10);
            final JPasswordField passwordText = new JPasswordField(10);
            userText.setBounds((int)(0.5*width),(int)(0.45*height),(int)(0.1*width),(int)(0.03*height));
            passwordText.setBounds((int)(0.5*width),(int)(0.55*height),(int)(0.1*width),(int)(0.03*height));
            JButton loginBut=new JButton("Login");
            loginBut.setBounds((int)(0.45*width),(int)(0.7*height),(int)(0.1*width),(int)(0.05*height));
            loginBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String usernamevalue=userText.getText();
                String passwordvalue=passwordText.getText();
                for(Profile p:profileList)
                {
                    if(p.getUserName().equals(usernamevalue)&&p.getPassWord().equals(passwordvalue))
                    {
                        usernameToSave=usernamevalue;
                        passwordToSave=passwordvalue;
                        container.removeAll();
                        container.updateUI();
                        startGame(p);
                        break;
                    }
                }

                    JLabel incorrect =new JLabel("Invalid Username or Password. Please Try Again");
                    incorrect.setForeground(Color.RED);
                    incorrect.setBounds((int)(0.4*width),(int)(0.6*height),(int)(0.4*width),(int)(0.1*height));
                    container.removeAll();
                    container.updateUI();
                    container.add(incorrect);
                    runLoginPage();     
            }
            });
            container.add(userText);
            container.add(passwordText);
            container.add(loginBut);
            container.add(loginPic);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }  
    }
    
    /**
     * Runs a page that allows users to create new profiles.<link> A username
     * and password are asked to be entered by the user.
     * @throws IOException - Login file could not be written into.
     * @author Richard Dong
     */
    public void runNewUser()
    {
        try
        {
            JButton previous=new JButton(new ImageIcon(backArrow));
            previous.setBounds(0,0,(int)(0.1*getWidth()),(int)(0.05*getHeight()));
            previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                container.removeAll();
                container.updateUI();
                runStartPage(); 

            }
            });
            JLabel newUserPic=new JLabel(new ImageIcon(newUserScreen));
            newUserPic.setBounds(0,0,width,height);
            container.add(previous);
            final JTextField userText = new JTextField(10);
            final JPasswordField passwordText = new JPasswordField(10);
            userText.setBounds((int)(0.5*width),(int)(0.45*height),(int)(0.1*width),(int)(0.03*height));
            passwordText.setBounds((int)(0.5*width),(int)(0.55*height),(int)(0.1*width),(int)(0.03*height));
            JButton createBut=new JButton("Create");
            createBut.setBounds((int)(0.45*width),(int)(0.7*height),(int)(0.1*width),(int)(0.05*height));
            createBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                String usernamevalue=userText.getText();
                String passwordvalue=passwordText.getText();
                boolean exists=false;
                for(Profile p:profileList)
                {
                    if(p.getUserName().equals(usernamevalue)||p.getPassWord().equals(passwordvalue))
                    {
                        exists=true;

                    }
                }
                if(exists)
                {
                    JLabel incorrect =new JLabel("Profile already exists. Please Enter Again");
                    incorrect.setForeground(Color.RED);
                    incorrect.setBounds((int)(0.4*width),(int)(0.6*height),(int)(0.2*width),(int)(0.1*height));
                    container.removeAll();
                    container.updateUI();
                    container.add(incorrect);
                    runNewUser();
                }
                else
                {
                    try
                    {
                        bwFile=new BufferedWriter(new FileWriter("datafiles/Login.txt"));
                        Profile current=new Profile(usernamevalue,passwordvalue,0,0,10000,0);
                        usernameToSave=usernamevalue;
                        passwordToSave=passwordvalue;
                        profileList.add(current);
                        for(Profile p:profileList)
                        {
                            bwFile.write(p.getUserName()+","+p.getPassWord()+","+p.getCheckPoint()+","+p.getLevel()+","+p.getHealth()+","+p.getPoint()+"\n");
                        }
                        bwFile.close();
                        container.removeAll();
                        container.updateUI();
                        startGame(current);
                    }
                    catch (IOException ex) 
                    {
                        JOptionPane frame=new JOptionPane();
                        JOptionPane.showMessageDialog(frame, "Fatal Error Occurred: User Profile Database Not Found!");
                        frame.show();
                        System.exit(0); 
                    }             
                }
            }
            });


            container.add(userText);
            container.add(passwordText);
            container.add(createBut);
            container.add(newUserPic);
        }
        catch(Exception ex)
        {
            ErrorLogger.logError("SEVERE",this.getClass().toString(),ex.toString());
        }
    }
    /**
     * Creates profiles from a stored file.
     * @throws IOException - Login file could not be read from.
     * @author Richard Dong
     */
    public void createProfiles()
    {
        String[]chopper=new String[7];
        try
        {
            scan=new Scanner(new FileReader("datafiles/Login.txt"));
            while(scan.hasNextLine())
            {
                chopper=scan.nextLine().split(",");
                String u=chopper[0];
                String p=chopper[1];
                int c=Integer.parseInt(chopper[2]);
                int l=Integer.parseInt(chopper[3]);
                int h=Integer.parseInt(chopper[4]);
                int po=Integer.parseInt(chopper[5]);
                profileList.add(new Profile(u,p,c,l,h,po));
            }
        }
        catch(FileNotFoundException | NumberFormatException ex)
        {
            JOptionPane frame=new JOptionPane();
            JOptionPane.showMessageDialog(frame, "Fatal Error Occurred: User Profile Database Not Found!");
            frame.show();
            System.exit(0); 
        }   
    }
    /**
     * Discards the game, called when fatal error occurs that stops the game from working properly.
     * @author Richard Dong
     */
    public static void fatalError()
    {
        JOptionPane frame=new JOptionPane();
        JOptionPane.showMessageDialog(frame, "Fatal Error Occurred! Please See README.txt for instructions");
        frame.show();
        System.exit(0); 
    }
    /**
     * Creates a screen with button options when the user-controlled character dies.
     * @throws IOException - Login file could not be written into.
     * @author Richard Dong
     */
    public static void deathScreen()
    {
        game.setVisible(false);
        theGame.getContentPane().remove(game);
        container.setVisible(true);    
        container.removeAll();
        container.updateUI();
        MusicPlayer.stopAll();
        
        JButton resume=new JButton("Restart From Last CheckPoint");
        resume.setBounds((int)(0.3*width),(int)(0.4*height),(int)(0.4*width),(int)(0.05*height));
        resume.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            container.removeAll();
            container.updateUI();
            removeProfile();
            try
            {
                bwFile=new BufferedWriter(new FileWriter("datafiles/Login.txt"));
                Profile current=new Profile(usernameToSave,passwordToSave,Ragnar.getCheckPoint(),Ragnar.getSavedLevel(),Ragnar.getSavedHealth(),Ragnar.getSavedPoints());
                profileList.add(current);
                for(Profile p:profileList)
                {
                    bwFile.write(p.getUserName()+","+p.getPassWord()+","+p.getCheckPoint()+","+p.getLevel()+","+p.getHealth()+","+p.getPoint()+"\n");
                }
                bwFile.close();
                container.setVisible(false);
                startGame(current);
            }
            catch (IOException ex) 
            {
                JOptionPane frame=new JOptionPane();
                JOptionPane.showMessageDialog(frame, "Fatal Error Occurred! Please See README.txt for instructions");
                frame.show();
                System.exit(0); 
            }            
        }   
        });   
        JLabel deathPic=new JLabel(new ImageIcon(deathScreen));
        deathPic.setBounds(0,0,width,height);
        
        JButton end=new JButton("Save and Exit");
        end.setBounds((int)(0.40*width),(int)(0.5*height),(int)(0.2*width),(int)(0.05*height));
        end.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            container.removeAll();
            container.updateUI();
            removeProfile();
            try
            {
                bwFile=new BufferedWriter(new FileWriter("datafiles/Login.txt"));
                Profile current=new Profile(usernameToSave,passwordToSave,Ragnar.getCheckPoint(),Ragnar.getSavedLevel(),Ragnar.getSavedHealth(),Ragnar.getSavedPoints());
                profileList.add(current);
                for(Profile p:profileList)
                {
                    bwFile.write(p.getUserName()+","+p.getPassWord()+","+p.getCheckPoint()+","+p.getLevel()+","+p.getHealth()+","+p.getPoint()+"\n");
                }
                bwFile.close();
                System.exit(0);
            }
            catch (IOException ex) 
            {
                JOptionPane frame=new JOptionPane();
                JOptionPane.showMessageDialog(frame, "Fatal Error Occurred! Please See README.txt for instructions");
                frame.show();
                System.exit(0); 
            }            
        }   
        });   
        
        container.add(resume);
        container.add(end);
        container.add(deathPic);
    }
    /**
     * Creates a screen with button options for the user when the game is paused.
     * @author Richard Dong
     */
    public static void pauseScreen()
    {       
        game.setVisible(false);
        container.removeAll();
        container.updateUI();
        container.setVisible(true);
        MusicPlayer.stopAll();
        JButton resume=new JButton("Resume Game");
        resume.setBounds((int)(0.45*width),(int)(0.4*height),(int)(0.1*width),(int)(0.05*height));
        resume.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            container.removeAll();
            container.updateUI();
            container.setVisible(false);
            game.setVisible(true);
            ControlCentral.unPause();
        }
        });
        
        JButton save=new JButton("Save Game and Exit");
        save.setBounds((int)(0.40*width),(int)(0.5*height),(int)(0.2*width),(int)(0.05*height));
        save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            saveGame();
        }
        });
            
        JButton exit=new JButton("Don't Save Game and Exit");
        exit.setBounds((int)(0.35*width),(int)(0.6*height),(int)(0.3*width),(int)(0.05*height));
        exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
        });    
            
            JLabel pauseScreen=new JLabel(new ImageIcon(pauseDisplay));
            pauseScreen.setBounds(0,0,width,height);
            
            
            
        container.add(resume);
        container.add(save);
        container.add(exit);
        container.add(pauseScreen);
    }
    /**
     * Saves the game into a stored file.
     * @throws IOException - Login file could not be written into.
     * @author Richard Dong
     */
    public static void saveGame()
    {
        Profile toChange=null;
        for(Profile p:profileList)
        {
            if(p.getUserName().equals(usernameToSave)&&p.getPassWord().equals(passwordToSave))
            {
                toChange=p;
            }
        }
        if(toChange!=null)
        {
            profileList.remove(toChange);
        }       
        try
        {
            bwFile=new BufferedWriter(new FileWriter("datafiles/Login.txt"));          
            for(Profile p:profileList)
            {
                bwFile.write(p.getUserName()+","+p.getPassWord()+","+p.getCheckPoint()+","+p.getLevel()+","+p.getHealth()+","+p.getPoint()+"\n");
            }
            bwFile.write(usernameToSave+","+passwordToSave+","+Ragnar.getCheckPoint()+","+Ragnar.getSavedLevel()+","+Ragnar.getSavedHealth()+","+Ragnar.getSavedPoints());
            bwFile.close();
        }
        catch (IOException ex) 
        {
            JOptionPane frame=new JOptionPane();
            JOptionPane.showMessageDialog(frame, "Fatal Error Occurred! Please See README.txt for instructions");
            frame.show();
            System.exit(0);
        }       
        System.exit(0);
    }
    /**
     * Main method of the ShellShocker package.<link> Calls the default constructor
     * of the the class TheGame.
     * @param args System specific inputs to the program
     */
    public static void main( String args[] )
    {
        TheGame ex = new TheGame();       
        ex.runStartPage();
        ex.setVisible(true);
    }
}
