/* CSCI 282 - JayBrick- subclass of TetrisBrick
 * Mariam Usman
 * 12/08/2023
 */

public class JayBrick extends TetrisBrick {
    public JayBrick(int colCent)
    {  
        super(colCent);
        colorNum =6;
        initPosition(colCent);
    }
    
    public int[][] initPosition(int columnCenter)
    {
        int[][] position = {{ 0,columnCenter},
                            { 1, columnCenter},
                            { 2, columnCenter},
                            { 2, columnCenter-1}};
        return position;
    }
    
    @Override
    public void rotate() 
    {
    int centerBrickRow = position[1][0];
    int centerBrickCol = position[1][1];
    
    if (orientation == 0) 
    {                                             
        position = new int[][]
        {
            { centerBrickRow, centerBrickCol+1},
            { centerBrickRow, centerBrickCol},
            { centerBrickRow, centerBrickCol-1},
            { centerBrickRow-1, centerBrickCol-1}
        };
        orientation++;
    }
    else if (orientation == 1) 
    {
        position = new int[][]
        {
            { centerBrickRow + 1, centerBrickCol },
            { centerBrickRow , centerBrickCol },
            { centerBrickRow-1, centerBrickCol },
            { centerBrickRow-1, centerBrickCol + 1 }
        };
        orientation++;
    }   
    else if (orientation == 2) 
    {
        position = new int[][]{
            { centerBrickRow, centerBrickCol -1},
            { centerBrickRow, centerBrickCol },
            { centerBrickRow, centerBrickCol+1 },
            { centerBrickRow + 1, centerBrickCol+1 }
        };
        orientation++;
    }
    else if (orientation == 3)
    {
        position = new int[][]{
            { centerBrickRow - 1, centerBrickCol },
            { centerBrickRow , centerBrickCol },
            { centerBrickRow+1, centerBrickCol },
            { centerBrickRow+1, centerBrickCol - 1 }
        };
        orientation = 0;
    }
}

    @Override
    public void unrotate() 
    {
        int centerBrickRow = position[1][0];
        int centerBrickCol = position[1][1];
        if (orientation == 3) 
        {
            position = new int[][]{
                { centerBrickRow + 1, centerBrickCol },
                { centerBrickRow , centerBrickCol },
                { centerBrickRow-1, centerBrickCol },
                { centerBrickRow-1, centerBrickCol + 1 }
            };
            orientation--;
        }
        else if (orientation == 2) 
        {
                position = new int[][]{
                { centerBrickRow, centerBrickCol+1},
                { centerBrickRow, centerBrickCol},
                { centerBrickRow, centerBrickCol-1},
                { centerBrickRow-1, centerBrickCol-1}
            };
            orientation--;
        }   
        else if (orientation == 1)
        {
            position = new int[][]{
            { centerBrickRow - 1, centerBrickCol },
            { centerBrickRow , centerBrickCol },
            { centerBrickRow+1, centerBrickCol },
            { centerBrickRow+1, centerBrickCol - 1 }
        };
            orientation--;
        }
        else if (orientation == 0) 
        {   
             position = new int[][]{
            { centerBrickRow, centerBrickCol -1},
            { centerBrickRow, centerBrickCol },
            { centerBrickRow, centerBrickCol+1 },
            { centerBrickRow + 1, centerBrickCol+1 }
        };
       
            orientation = 3;
        }     
    }
}
