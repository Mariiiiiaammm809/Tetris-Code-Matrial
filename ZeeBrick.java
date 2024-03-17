/* CSCI 282 - ZeeBrick- subclass of TetrisBrick
 * Mariam Usman
 * 12/18/2023
 */

public class ZeeBrick extends TetrisBrick{
   public ZeeBrick(int colCent)
    {
       super(colCent);
       colorNum =7;
       initPosition(colCent);
    }
    
    public int[][] initPosition( int columnCenter)
    {
        int[][] position = {{ 0,columnCenter-2},
                            { 0, columnCenter-1},
                            { 1, columnCenter-1},
                            { 1, columnCenter}};
        return position;
    }
    
    @Override
    public void rotate()
    {
        int centerBrickRow = position[2][0];
        int centerBrickCol = position[2][1];

        if (orientation == 0) {                                             
            position = new int[][]{
                { centerBrickRow - 1, centerBrickCol + 1 },
                { centerBrickRow, centerBrickCol + 1 },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow + 1, centerBrickCol }
            };
            orientation++;
        }
        else if (orientation == 1) {
            position = new int[][]{
                { centerBrickRow - 1, centerBrickCol - 1 },
                { centerBrickRow - 1, centerBrickCol },
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

        if (orientation == 0) {
            position = new int[][]
            {
                { centerBrickRow - 1, centerBrickCol + 1 },
                { centerBrickRow, centerBrickCol + 1 },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow + 1, centerBrickCol }
            };  
            orientation++;
        }
        else if (orientation == 1) 
        {
            position = new int[][]{
                { centerBrickRow - 1, centerBrickCol - 1 },
                { centerBrickRow - 1, centerBrickCol },
                { centerBrickRow, centerBrickCol },
                { centerBrickRow, centerBrickCol + 1 }
            };
            orientation = 0;
        }
    }
}
