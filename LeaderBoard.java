/*
 * CSCI 282 - LeaderBoard class: Handles everything relating to the leaderBoard
 * Mariam Usman
 * 01/09/24
 */
import java.util.*;
import java.io.*;
import javax.swing.*;

public class LeaderBoard {
       
    private final String fileName = "Score.dat";
    private final int maxNumScore=10;
    
    @Override
    public String toString() 
    {
        String result = "";
    
        ArrayList<String> currentScores = getCurrentScores();
        for (int index =0; index < currentScores.size(); index++)
        {
            String score = currentScores.get(index);
            result += score + "\n";
        }
    
     return result;
    }
    
    public void recordScore(String playerName, int score)
    {  
        ArrayList<String> currentScores = getCurrentScores();
        
        if (currentScores.size() >= maxNumScore)
        {
            int lowestScore = getLowestScore(currentScores);
            for (int index=0; index < currentScores.size(); index++)
            { 
                String entry = currentScores.get(index);
                if (entry.contains(Integer.toString(lowestScore))) 
                {
                    currentScores.remove(entry);
                    currentScores.add(playerName + ": " + score);
                    break;
                }
            }
        }
        else 
        {
            currentScores.add(playerName + ": " + score);
        } 

        File fileConnection = new File(fileName);
        if (fileConnection.exists() && !fileConnection.canWrite())
        {
            String errorMessage = "An error has occurred writing to file.";
            JOptionPane.showMessageDialog(null, errorMessage, "Error!", 0);
            return;
        }
        try 
        {
            FileWriter outWriter = new FileWriter(fileConnection,false);//overwriting 
            for (int index =0; index< currentScores.size(); index++)
            {
                String currentScore = currentScores.get(index);
                outWriter.write(currentScore + "\n");
            }
            outWriter.flush();
            outWriter.close();  
        } 
        catch (IOException ioe) 
        {
            String errorMessage = "An error has occurred writing to file: score";
            JOptionPane.showMessageDialog(null, errorMessage, "Error!", 0);
        }
    }
    
    public String scoreSorter(ArrayList<String> scores) 
    {
        ArrayList<Score> scoreList = new ArrayList<>();  
        for (int index = 0; index < scores.size(); index++) 
        {
            String score = scores.get(index);
            Scanner scanner = new Scanner(score);
            scanner.useDelimiter(": ");
            if (scanner.hasNext()) 
            {
                String playerName = scanner.next();
                if (scanner.hasNextInt())
                {
                    int playerScore = scanner.nextInt();
                    scoreList.add(new Score(playerName, playerScore));
                }
            }
            scanner.close();
            if (scoreList.size() >= maxNumScore) {
                break;
            }
        }
        Collections.sort(scoreList); //read oracle notes
        String newScores = "";
        int number = 1;
        for (int index = 0; index < scoreList.size(); index++)
        {
            Score score = scoreList.get(index);
            newScores += number + ". " + score.getPlayerName() + ": " + score.getScore() + "\n";
            number++;
        }
        return newScores;
    }

    public void clearScore()
    {   
        String[] choices = {"Clear Board","Cancel Action"}; 
        String prompt = "Are you sure you want to clear the leader board?";
        int choice = JOptionPane.showOptionDialog(null, prompt, 
                "         Clear board?",0, 0, null, choices, choices[0]);

        switch (choice)
        {
            case 0:
                try 
                {
                    FileWriter file = new FileWriter(fileName, false);
                    PrintWriter flusher = new PrintWriter(file, false);
                    flusher.flush();
                    flusher.close();
                    file.close();
                } 
                catch (IOException e) 
                {
                    String errorMessage = "Trouble opening selected file"+fileName;
                    JOptionPane.showMessageDialog(null, errorMessage, "Error!", 0);
                }
            case 1 :
                break;
                
            default :
                break;
        }
    }

    public ArrayList<String> getCurrentScores()  //reads the scores from file
    {
        ArrayList<String> currentScores = new ArrayList<>();
        try
        {
            File file = new File(fileName);
            Scanner inScan = new Scanner(file);
            while (inScan.hasNextLine()) 
            {
                currentScores.add(inScan.nextLine());
            }
            inScan.close();
        } 
        catch (FileNotFoundException e) 
        {
            String errorMessage = "Trouble opening selected file";
            JOptionPane.showMessageDialog(null, errorMessage, "Error!", 0);
        }
        return currentScores;
    }

    public int getLowestScore(ArrayList<String> scores) 
    {
        if (scores.size()== 0) 
        {
            return 0;
        }

        ArrayList<Integer> playerScore = new ArrayList<>();
        for (int index = 0; index < scores.size(); index++) {
            String score = scores.get(index);
            Scanner scanner = new Scanner(score);
            scanner.useDelimiter(": ");

            if (scanner.hasNext()) 
            { 
                scanner.next(); 
            }

            if (scanner.hasNextInt())
            { 
                playerScore.add(scanner.nextInt());
            }

             scanner.close();
        }
        int minScore = playerScore.get(0);
        for (int index= 1; index < playerScore.size();index++){
            int currentScore = playerScore.get(index);
            if (currentScore < minScore)
            {
                minScore = currentScore;
            }
        }
        return minScore;
    }
    
    public String getScoreBoard()  //gets the current score from board
    {
        ArrayList<String> initialScores = getCurrentScores();
        return scoreSorter(initialScores);
    }
}
