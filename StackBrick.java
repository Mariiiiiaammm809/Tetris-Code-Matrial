/* CSCI 282 - StackBrick- subclass of TetrisBrick
 * Mariam Usman
 * 12/18/2023
 */

public class StackBrick extends TetrisBrick {
    public StackBrick(int colCent)
    {
        super(colCent);
        colorNum =5;
        initPosition(colCent);
    }
    
    public int[][] initPosition(int columnCenter)
    {
        int[][] position = {{ 0,columnCenter},
                            { 1, columnCenter-1},
                            { 1, columnCenter},
                            { 1, columnCenter+1}};
        return position;
    }
     
    @Override
    public void rotate()
    {
        
        int centerBrickRow = position[2][0];
        int centerBrickCol = position[2][1];
    
        if (orientation == 0) 
        {                                             
            position = new int[][]{
                { centerBrickRow, centerBrickCol + 1 },
                { centerBrickRow - 1, centerBrickCol },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow + 1, centerBrickCol }
            };
            orientation++;
        }
        else if (orientation == 1) 
        {
            position = new int[][]{
                { centerBrickRow + 1, centerBrickCol },
                { centerBrickRow, centerBrickCol + 1 },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow, centerBrickCol - 1 }
            };
            orientation++;
        }   
        else if (orientation == 2) 
        {
            position = new int[][]{
                { centerBrickRow, centerBrickCol - 1 },
                { centerBrickRow + 1, centerBrickCol },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow - 1, centerBrickCol }
            };
            orientation++;
        }
        else if (orientation == 3)
        {
            position = new int[][]{
                { centerBrickRow - 1, centerBrickCol },
                { centerBrickRow, centerBrickCol - 1 },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow, centerBrickCol + 1 }
            };
            orientation = 0;
        }
}

    @Override
    public void unrotate()
    {

        int centerBrickRow = position[2][0];
        int centerBrickCol = position[2][1];

        if (orientation == 3) 
        {                                             
            position = new int[][]
            {
                { centerBrickRow + 1, centerBrickCol },
                { centerBrickRow, centerBrickCol + 1 },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow, centerBrickCol - 1 }
            };
            orientation--;
        }
        else if (orientation == 2) 
        {
            position = new int[][]{
                { centerBrickRow, centerBrickCol + 1 },
                { centerBrickRow - 1, centerBrickCol },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow + 1, centerBrickCol }
            };
            orientation--;
        }   
        else if (orientation == 1)
        {
            position = new int[][]{
                { centerBrickRow - 1, centerBrickCol },
                { centerBrickRow, centerBrickCol - 1 },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow, centerBrickCol + 1 }
            };
            orientation--;
        }
        else if (orientation == 0) 
        {
            position = new int[][]{
                { centerBrickRow - 1, centerBrickCol },
                { centerBrickRow, centerBrickCol - 1 },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow, centerBrickCol + 1 }
            }; 
            orientation = 3;
        }
    }
    
}
