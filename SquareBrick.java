/* CSCI 282 - SquareBrick- subclass of TetrisBrick
 * Mariam Usman
 * 12/30/2023
 */

public class SquareBrick extends TetrisBrick{
    public SquareBrick(int colCent)
    {
        super(colCent);
        colorNum =4;
        initPosition(colCent);
        
    }
    
    public int[][] initPosition(int columnCenter)
    {
        int[][] position = 
                           {{ 0,columnCenter},
                            { 0, columnCenter-1},
                            { 1, columnCenter},
                            { 1, columnCenter-1}};
        return position;
    }
   
    public void rotate(){}
    
    public void unrotate(){}
}


