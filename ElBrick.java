/*
 * CSCI 282 - Lab 4 ElBrick- subclass of TetrisBrick
 * Mariam Usman
 * 12/18/2023
 */

public class ElBrick extends TetrisBrick {
    public ElBrick(int colCent)
    {
      
        super(colCent);
        colorNum =2;
        initPosition(colCent);
    }
    
    public int[][] initPosition(int columnCenter)
    {
        int[][] position = {{  0,columnCenter-1},
                             { 1, columnCenter-1},
                             { 2, columnCenter-1},
                             { 2, columnCenter}};
        return position;   
    }
    
    @Override
    public void rotate()
    {   
        int centerBrickRow =position [1][0];
        int centerBrickCol = position[1][1];
        if(orientation == 0)
        {                                             
           position = new int[][]{
                { centerBrickRow,centerBrickCol+1},
                { centerBrickRow, centerBrickCol},
                { centerBrickRow, centerBrickCol-1},
                { centerBrickRow+1, centerBrickCol-1}
            };
            orientation ++;
        }
        else if(orientation == 1)
        {
            position = new int[][]{
                    { centerBrickRow+1,centerBrickCol},
                    { centerBrickRow,centerBrickCol},
                    { centerBrickRow-1,centerBrickCol},
                    { centerBrickRow-1, centerBrickCol-1}};
            orientation ++;
        }   
        else if (orientation == 2)
        { 
            position = new int[][]{
                    { centerBrickRow,centerBrickCol-1},
                    { centerBrickRow,centerBrickCol},
                    { centerBrickRow, centerBrickCol+1},
                    { centerBrickRow-1, centerBrickCol+1}};
            orientation ++;
        }
        else if(orientation == 3)
        {
            position = new int[][]{
                    { centerBrickRow-1,centerBrickCol},
                    { centerBrickRow,centerBrickCol},
                    { centerBrickRow+1,centerBrickCol},
                    { centerBrickRow+1, centerBrickCol+1}};
            orientation =0;
        }
    }
    
    public void unrotate()
    {
        int centerBrickRow =position [1][0];
        int centerBrickCol = position[1][1];
        if(orientation == 3)
        {                                             
            position = new int[][]{
                    { centerBrickRow+1,centerBrickCol},
                    { centerBrickRow,centerBrickCol},
                    { centerBrickRow-1,centerBrickCol},
                    { centerBrickRow-1, centerBrickCol-1}};
            orientation --;
        }
        else if(orientation == 2)
        {
            position = new int[][]{
                { centerBrickRow,centerBrickCol+1},
                { centerBrickRow, centerBrickCol},
                { centerBrickRow, centerBrickCol-1},
                { centerBrickRow+1, centerBrickCol-1}};
            orientation --;
        }   
        else if (orientation == 1)
        {  
            position = new int[][]{
                    { centerBrickRow-1,centerBrickCol},
                    { centerBrickRow,centerBrickCol},
                    { centerBrickRow+1,centerBrickCol},
                    { centerBrickRow+1, centerBrickCol+1}};
            orientation --;
        }
        
        else if(orientation == 0)
        {
            position = new int[][]{
                    { centerBrickRow,centerBrickCol-1},
                    { centerBrickRow,centerBrickCol},
                    { centerBrickRow, centerBrickCol+1},
                    { centerBrickRow-1, centerBrickCol+1}};    
            orientation =3;
        }
    }
}
