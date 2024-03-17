/*
 * CSCI 282 -TetrisDisplay :the JPanel and 
 * everything relating to displaying the game to the user
 * Mariam Usman
 * 12/06/23
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;

public class TetrisDisplay extends JPanel{
    private TetrisGame game ;
    private int start_x =100;
    private int start_y = 100 ;
    private int cell_size = 10;
    private int speed = 150;
    private boolean pause;
    private Timer timer;
    private Color colors[] =  { Color.BLACK,Color.PINK, Color.RED, Color.BLUE,
                                Color.YELLOW, Color.GREEN,Color.ORANGE,Color.CYAN};
    
    
    public TetrisDisplay (TetrisGame gam)
    {   
        game = gam;
        timer = new Timer(speed, new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                cycleMove();
            }
        });
        timer.start();
        
        this.addKeyListener(new KeyAdapter() 
        {
            public void keyPressed(KeyEvent ke)
            {
              
                translateKey(ke);
            }
        });
        setFocusable(true);
    }
    
    @Override
    public void paintComponent(Graphics g)
    { 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        drawWell(g);
        drawFallingBrick(g);
        drawBackground(g);
        scoreDisplay(g);
        gameOverDisplay(g);
    }
    
    private void drawWell(Graphics g)
    {  
        g.setColor(Color.BLACK);
        g.drawRect(start_x-cell_size, start_y,cell_size, 
                              cell_size*game.getRows()+cell_size);
        g.fillRect(start_x-cell_size,start_y,cell_size,
                           cell_size*game.getRows()+cell_size);
        //right
        g.drawRect(start_x+cell_size*game.getCols(),start_y,
                       cell_size,cell_size*game.getRows()+cell_size);
        g.fillRect(start_x+cell_size*game.getCols(),start_y,
                       cell_size,cell_size*game.getRows()+cell_size);
        //bottom
        g.drawRect(start_x-cell_size,start_y+cell_size*game.getRows(),
                                     cell_size*game.getCols()+2*cell_size, cell_size);
        g.fillRect(start_x-cell_size,start_y+cell_size*game.getRows(),
                                        cell_size*game.getCols()+2*cell_size, cell_size); 
        //the white part of the well
        g.drawRect(start_x,start_y,cell_size*game.getCols(),
                                   cell_size*game.getRows());
        g.setColor(Color.WHITE);
        g.fillRect(start_x,start_y,cell_size*game.getCols(),
                                  cell_size*game.getRows());
    }
    
    private void drawBackground(Graphics g)
    {
        for (int row = 0; row < game.getRows(); row++) 
        {
            for (int column = 0; column < game.getCols(); column++) 
            {
                int colorNum = game.fetchBoardPosition(row,column);
                if (colorNum > 0) 
                {
                    g.setColor(colors[colorNum]);
                    g.fillRect(start_x +column*cell_size,start_y +
                                       row*cell_size,cell_size,cell_size);
                    g.setColor(colors[0]);
                    g.drawRect(start_x +column*cell_size,start_y+
                                       row*cell_size,cell_size,cell_size);
                }
            }
        }
    }
    
     
    private void drawFallingBrick (Graphics g)
    {  
        for(int seg = 0; seg < game.getNumberSegs(); seg++)
        {
            g.setColor(colors[game.getFallingBrickColor()]);
            g.fillRect(start_x+game.getSegCol(seg)*cell_size, start_y+
                      game.getSegRow(seg)*cell_size,cell_size,cell_size);
            g.setColor(colors[0]);
            g.drawRect(start_x+game.getSegCol(seg)*cell_size, start_y+
                      game.getSegRow(seg)*cell_size,cell_size,cell_size);
        }
    }
    
    private void translateKey(KeyEvent ke)
    {
        int key = ke.getKeyCode();
        switch (key)
        {
            case KeyEvent.VK_RIGHT:
                game.makeMove("R");
                break;
            case KeyEvent.VK_LEFT:
                game.makeMove("L");
                break;
            case KeyEvent.VK_UP:
                game.makeMove("O");
                break;
            case KeyEvent.VK_N:
                game.newGame();
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_SPACE:
                if (pause==true )
                {
                    timer.start();
                    pause = false;
                }
                else
                {
                    timer.stop();
                    pause = true;
                }
                break;
        }
        repaint();
    }
    
    private void scoreDisplay(Graphics g)
    {
        int fontSize = 20;
        int fontStyle = 1;
        int pixelRow = 5;
        int pixelCol = 25;
        int scoreBoardWidth = 130;
        int scoreBoardHeight = 30;
       
        Font scoreFont = new Font ("Arial", fontStyle, fontSize); 
        g.setFont(scoreFont);  
        g.setColor(Color.WHITE);
        g.fillRect(fontStyle, fontStyle, scoreBoardWidth, scoreBoardHeight);
        g.setColor(Color.BLACK);  
        g.drawRect(fontStyle, fontStyle, scoreBoardWidth, scoreBoardHeight);
        g.drawString("Score: "+ game.getScore(), pixelRow, pixelCol); 
    }
    
    private void endDisplay(Graphics g)
    {   
        int fontSize = 40;
        int fontStyle = 1;
        int pixelRow = 60;
        int pixelCol = 250;
        int endStartX = 50;
        int endStartY = 200;
        int endGameWidth = 240;
        int endGameHeight = 75;
  
        g.setColor(Color.BLACK);
        g.drawRect(endStartX, endStartY, endGameWidth, endGameHeight);
        g.setColor(Color.WHITE);
        g.fillRect(endStartX, endStartY, endGameWidth, endGameHeight);
        Font endGameFont = new Font("Arial", fontStyle, fontSize);
        g.setFont(endGameFont);
        g.setColor(Color.RED);
        g.drawString("Game Over!", pixelRow, pixelCol);
    }
    
    private void gameOverDisplay(Graphics g) 
    {
        if (game.getState() ==0)
        {
            endDisplay(g);
        }
}
    
    private void cycleMove ()
    {    
         game.makeMove("D");
        repaint();
    }
}
