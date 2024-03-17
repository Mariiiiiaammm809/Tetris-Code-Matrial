/*
 * CSCI 282 - TetrisGame:Controls the logic of the game
 * Mariam Usman
 * 01/29/24
 */

import java.util.*;
import java.io.*;
import javax.swing.*;

public class TetrisGame   {
    private TetrisBrick fallingBrick;
    private int[][] background;
    private int rows ;
    private int cols;
    private int numBrickTypes= 7;
    private int state = 1 ;
    private int score;
    Random randomGen;
    
    public TetrisGame(int rows,int cols)
    {
        this.rows = rows;
        this.cols = cols;
        randomGen = new Random();
        background= new int[rows][cols];
        spawnBrick();
    }
    
    @Override
    public String toString(){
        String board = rows +"  "+cols+"   "+score+"\n";
        for(int row = 0; row < background.length; row++)
        {
            for(int col = 0; col < background[0].length;col++)
            {
                board += background[row][col]+" ";
            }
            board += "\n";
        }
        board += fallingBrick.getBrickType() 
                + " " + fallingBrick.orientation + " ";
        for (int seg = 0; seg < fallingBrick.getNumSegments(); seg++) 
        {
            board += fallingBrick.position[seg][0] +
                    " " + fallingBrick.position[seg][1] + " ";
        }
        board = board.substring(0,board.length()-1);
        return board;
    }
    
    public void initBoard()
    {
        background = new int[rows][cols];
        for (int row = 0; row < rows; row++)
        {
            for (int column = 0; column < cols; column++) 
            {
                background[row][column] = 0; 
                spawnBrick();
            }
        }
    }
    public void newGame()
    {
        initBoard();
        score = 0;
        state = 1;
    }
    
    public int fetchBoardPosition(int row, int column) 
    {
        return background[row][column];
    }
    
    private void spawnBrick ()
    { 
        int randBrick = randomGen.nextInt(numBrickTypes);
        int colCent = cols/2;
        
        switch (randBrick) 
        {
            case 0:
                fallingBrick = new ElBrick(colCent);
                break;
            case 1:
                fallingBrick = new EssBrick(colCent);
                break;
            case 2:
                fallingBrick = new JayBrick(colCent);
                break;
            case 3:
                fallingBrick = new LongBrick(colCent);
                break;
            case 4:
                fallingBrick = new SquareBrick(colCent);
                break;
            case 5:
                fallingBrick = new StackBrick(colCent);
                break;
            case 6:
                fallingBrick = new ZeeBrick(colCent);
                break;
        }
    }  
    
    public void makeMove(String move)
    { 
        if (state ==1){
            switch (move)
            { 
               case  "D":
                    fallingBrick.moveDown();
                    if(!validateMove())
                    {   
                        fallingBrick.moveUp();
                        transferColor();
                        numRowsDropped();
                        spawnBrick();
                        isGameOver();
                        handleGameOver(); 
                    }
                    break;
                case "R":
                    fallingBrick.moveRight();

                    if(!validateMove())
                    {
                        fallingBrick.moveLeft(); 
                    }
                    break;
                case "L":
                    fallingBrick.moveLeft();
                    if(!validateMove())
                    {
                        fallingBrick.moveRight(); 
                    }
                    break;
                case "O": 
                    fallingBrick.rotate();
                    if(!validateMove())
                    {   
                        fallingBrick.unrotate(); 
                    } 
                    break;
            }  
        }
    }
    
    private boolean validateMove()
    {    
        for (int seg = 0; seg < fallingBrick.getNumSegments(); seg++) 
        {
            int segRow = fallingBrick.position[seg][0];
            int segCol = fallingBrick.position[seg][1];
            
            if ( segRow>= rows || segRow<=0 ) //bottom and top
            {
                return false;
            }
            
            if (segCol<0 || segCol >= cols) //left then right
            {   
                return false;
            }
    
            if ( background[segRow][segCol]!=0) //color overlap
            {   
                return false;
            }
        }
        return true;
    }
    
    private void transferColor()
    {
        for (int seg = 0; seg < fallingBrick.getNumSegments(); seg++) 
        {
            int row = fallingBrick.position[seg][0];
            int column = fallingBrick.position[seg][1];
        
            background[row][column] = fallingBrick.getColorNumber();
        }
    }
    
    private boolean rowHasSpace(int row) //implied in name
    {
        for (int column = 0; column < cols; column++) 
        {
            if (background[row][column] == 0) 
            {
                return true;
            }
        }
        return false;
    } 
    
    private int deleteFullRows() //deletes and counts rows
    {
        int numDeletedRows = 0;
        for (int row = rows-1 ; row >= 0; row--)
        {
            if (!rowHasSpace(row)) 
            {
                dropAllRows(row);
                numDeletedRows++;
                row++;
            }
        }
        return numDeletedRows;
        }
     
    private void dropAllRows(int rowNumber) //drops 
    {
        for (int row = rowNumber; row > 0; row--) 
        {
            for (int column = 0; column < cols; column++)
            {
                background[row][column] = background[row - 1][column];
            }
        }
        //clears top 
        for (int column = 0; column < cols; column++) 
        {
            background[0][column] = 0;
        }
    }
    
    private void numRowsDropped()
    {
       int rowsDeleted = deleteFullRows(); //assigns the num of rows to var
       score = scoreCounter(rowsDeleted);
    }
    
   private int scoreCounter(int rowsDeleted)
    {
        switch(rowsDeleted) 
        {   
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 600;
                break;
            case 4:
                score += 1200;
                break;
        }
        return score;
    } 
    
    private boolean isGameOver() 
    {
        for (int column = 0; column < cols; column++) 
        {
            if (background[0][column] != 0)
            {  
                state = 0;
                return true;  
            }
        }
            return false;  // Game Not over
    }
    
    private void handleGameOver() {
        LeaderBoard leaderBoard = new LeaderBoard();
        if (state == 0)
        {
            ArrayList<String> currentScores = leaderBoard.getCurrentScores();
            int lowestScore = leaderBoard.getLowestScore(currentScores);

            if (score > lowestScore)
            {
                String nameMessage = "<html>Game is Over"
                        + "<h2>Congratulations you are a high scorer.</h2>"
                        + "<br>Please enter your name:";
                String playerName = JOptionPane.showInputDialog(null,nameMessage);
                if (playerName == null)
                {
                    playerName = "John Doe";
                }
                leaderBoard.recordScore(playerName, score);
            }
        }
    }
    
    public void saveToFile(String fileName)
    {
    
        if (fileName == null) // to avoid nullpointer error
        {
            String errorMessage = "Invalid file name. Please enter a valid file name.";
            JOptionPane.showMessageDialog(null, errorMessage, "Error!", 0);
            return;
        }

        String errorMessage = "An error has occurred saving the game.";
        File fileConnection = new File(fileName);

        if (fileConnection.exists() && !fileConnection.canWrite())
        {
            JOptionPane.showMessageDialog(null, errorMessage, "Error!", 0);
            return;
        }
        try 
        {
            FileWriter outWriter = new FileWriter(fileConnection);
            outWriter.write(this.toString());
            outWriter.close();
        } 
        catch (IOException ioe) 
        {

            JOptionPane.showMessageDialog(null, errorMessage, "Error!", 0);
        }
    }

    public void retrieveFromFile() 
    {   
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File     //Oracle helper notes
                                        (System.getProperty("user.dir")));

        int file = fileChooser.showOpenDialog(null);
        if (file == JFileChooser.APPROVE_OPTION) 
        {
            File fileSelected = fileChooser.getSelectedFile();
            retrieveHandling(fileSelected);
        }
    }

    private void retrieveHandling(File fileConnection) //helper method for retrieve
    {
        String errorMessage = "An error has occurred during read from file.";
        
        try {
            Scanner inScan = new Scanner(fileConnection);
            rows = inScan.nextInt();
            cols = inScan.nextInt();
            score = inScan.nextInt();
            background = new int[rows][cols];

            for (int row = 0; row < rows; row++) 
            {
                for (int column = 0; column < cols; column++) 
                {
                    background[row][column] = inScan.nextInt();
                }
            }
            
            String brickType = inScan.next();
            int colCent = cols/2;
            switch (brickType) 
            {

                case "LongBrick":
                    fallingBrick = new LongBrick(colCent);
                    break;
                case "ElBrick":
                    fallingBrick = new ElBrick(colCent);
                    break;
                case "EssBrick":
                    fallingBrick = new EssBrick(colCent);
                    break;
                case "JayBrick":
                    fallingBrick = new JayBrick(colCent);
                    break;
                case "SquareBrick":
                    fallingBrick = new SquareBrick(colCent);
                    break;
                case "StackBrick":
                    fallingBrick = new StackBrick(colCent);
                    break;
                case "ZeeBrick":
                    fallingBrick = new ZeeBrick(colCent);
                    break;
            }
            
            int orientation = inScan.nextInt();
            int[][] position = new int[fallingBrick.getNumSegments()][2];
            
            for (int seg = 0; seg < fallingBrick.getNumSegments(); seg++) 
            {
                position[seg][0] = inScan.nextInt();
                position[seg][1] = inScan.nextInt();
            }
            
            fallingBrick.position = position;
            fallingBrick.orientation = orientation;
        
        } 
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, errorMessage+ fileConnection.getPath(), "Error!", 0);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, errorMessage, "Error!", 0);

        }
    }

    public int getFallingBrickColor()
    {
        return fallingBrick.colorNum;
    }
    
    public int getNumberSegs()
    {
        return fallingBrick.getNumSegments();
    }
    
    public int getSegRow(int segmentNumber)
    {
       return fallingBrick.position[segmentNumber][0];  
    }
    
    public int getSegCol(int segmentNumber)
    {
        return fallingBrick.position[segmentNumber][1];
    }
    
    public int getRows()
    {
        return rows;
    }
    
    public int getCols()
    {
        return cols;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getState()
    {
        return state;
    }
}




