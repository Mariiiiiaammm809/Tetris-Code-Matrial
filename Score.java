/*
 * CSCI 282 - Score class 
 * Comparable interface for sorting scores in descending order
 * Mariam Usman
 * 01/09/23
 */

public class Score implements Comparable<Score> 
{
    private String playerName;
    private int score;

    public Score(String playerName, int score)
    {
        this.playerName = playerName;
        this.score = score;
    }

    @Override
    public  int compareTo(Score initialScore)
    {

        if(this.getScore() < initialScore.getScore())
        {
            return 1;
        }
        if(this.getScore() > initialScore.getScore())
        {
            return -1;
        }
        return 0;
    }
    
    public String getPlayerName()
    {
        return playerName;
    }

    public int getScore() 
    {
        return score;
    }
}
