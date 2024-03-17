/*
 * Lab4 CSCI 282 - The executable Tetris Game Window that houses the display
 * Mariam Usman
 * 01/09/24
 */

import javax.swing.*;
import java.awt.event.*;

public class TetrisWindow extends JFrame {
    private TetrisGame game ;
    private TetrisDisplay display;
    private LeaderBoard leaderBoard;
    private int win_width = 450;
    private int win_height = 650;
    private int grid_rows = 20;
    private int grid_cols = 12;
    
    
    public TetrisWindow()
    {   
        menuItems();
        this.setTitle("\tMy Tetris Game: Part 3   Mariam Usman");
        this.setSize(win_width, win_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        game = new TetrisGame(grid_rows,grid_cols);
        leaderBoard = new LeaderBoard(); 
        display = new TetrisDisplay(game);
        
        this.add(display);                  //display class housed in window
        this.setVisible(true);
       
    }
    
    public void menuItems(){
        
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        JMenu scoreMenu = new JMenu ("High Scores");
        menuBar.add(scoreMenu);
        
        JMenuItem leader = new JMenuItem("Leader Board ");
        leader.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
              JOptionPane.showMessageDialog(null, leaderBoard.getScoreBoard(), 
                                      "Leader Board",0);
            }
        });
        scoreMenu.add(leader);
        
        JMenuItem startOverBoard = new JMenuItem("Clear Scores");
        startOverBoard.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                leaderBoard.clearScore();
            }
        });
        scoreMenu.add(startOverBoard);
        
        JMenu fileMenu = new JMenu ("File");
        menuBar.add(fileMenu);
        
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog(null, "Please "
                      + "enter your file name:\t","Save your file",0);
                
                game.saveToFile(fileName);
            }
        });
        fileMenu.add(saveGame);
        
        JMenuItem retreiveGame = new JMenuItem("Retrieve Game");
        retreiveGame.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {   
                 
               game.retrieveFromFile();
            }
        });
        fileMenu.add(retreiveGame);
        fileMenu.addSeparator();
        
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                game.newGame();
                
            }
        });
        fileMenu.add(newGame);
        
    }
    
    public static void main (String[] args) 
    {
        TetrisWindow myWindow = new TetrisWindow();
    }
}
