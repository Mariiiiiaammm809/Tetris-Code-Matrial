/* CSCI 282 -  LongBrick- subclass of TetrisBrick
 * Mariam Usman
 * 12/08/2023
 */

public class LongBrick extends TetrisBrick{
     public LongBrick(int colCent)
    { 
        super(colCent);
        colorNum =1;
        initPosition(colCent);
    }
     
     
    
    @Override
    public int[][] initPosition(int columnCenter)
    {  
        int[][] position =  {{0, columnCenter-2},
                             {0, columnCenter-1},
                             {0, columnCenter},
                             {0, columnCenter+1}};
        return position;
    }
    
    @Override
    public void rotate() 
    {
        
        int centerBrickRow = position[2][0];
        int centerBrickCol = position[2][1];

        if (orientation == 0) 
        {                                             
            position = new int[][]
            {
                { centerBrickRow - 2, centerBrickCol },
                { centerBrickRow - 1, centerBrickCol },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow + 1, centerBrickCol }
            };
            orientation++;
        }
        else if (orientation == 1)
        {
            position = new int[][]{
                { centerBrickRow, centerBrickCol - 2 },
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

        if (orientation == 0) 
        {
            position = new int[][]
            {
                { centerBrickRow - 2, centerBrickCol },
                { centerBrickRow - 1, centerBrickCol },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow + 1, centerBrickCol }
            };
            orientation++;
        }
        else if (orientation == 1) 
        {
            position = new int[][]{
                { centerBrickRow, centerBrickCol - 2 },
                { centerBrickRow, centerBrickCol - 1 },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow, centerBrickCol + 1 }
            };
            orientation = 0;
        }
    }

}
