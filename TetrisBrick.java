/*
 * CSCI 282 - Abstract super class for all the brick classes 
 * Mariam Usman
 * 12/06/23
 */

public abstract class TetrisBrick {
    
    protected int numSegments =4;
    protected int position[][] = new int[numSegments][2];
    protected int orientation =0;
    protected int colorNum;
  
    public TetrisBrick( int colCent)
    {
      position = initPosition(colCent);
      this.numSegments = numSegments;
      this.colorNum = colorNum;
    } 
    @Override
    public String toString()
    {
        String result = "TetrisBrick{" +
                "numSegments=" + numSegments +
                ", orientation=" + orientation +
                ", colorNum=" + colorNum +
                ", position=[";

        for (int segment = 0; segment < numSegments; segment++)
        {
            if (segment < numSegments ) 
            {
                result += ", ";
            }
        }

        result += "]}";

        return result;
    }
    
    
    public abstract int[][] initPosition(int columnCenter);
    public abstract void rotate();
    public abstract void unrotate();
   
    
    public int getColorNumber()
    {
      return colorNum;
    }
    
    public int getNumSegments()
    {
        return numSegments;
    }
    
    public void moveLeft()
    {
        for (int segment = 0; segment < numSegments; segment++)
        {
            position[segment][1] -= 1;
        }
    }
    
    public void moveRight()
    {
        for (int segment = 0; segment < numSegments; segment++)
        {
            position[segment][1] += 1; 
        }
    }
    
    public void moveUp()
    { 
      for(int segment= 0; segment < numSegments; segment++)
        {
            position[segment][0] -=1;
        } 
    }
    
    public void moveDown()
    {
       for(int segment= 0; segment < numSegments; segment++)
        {
            position[segment][0] +=1;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        
    public String getBrickType() 
    {
        return this.getClass().getSimpleName();
    }
}
