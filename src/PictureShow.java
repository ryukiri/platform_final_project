//Replace of "Map" and "MapGUI" classes

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureShow
{
    public static void mapFrame()
    {
        JFrame frame = new JFrame( "MAP ");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(767,789);
        
        
        class PictureMap extends JComponent{
            public void paintComponent( Graphics g ){
                // Recover Graphics g
                Graphics2D g2 = (Graphics2D) g;  
                int[][] map = new int[15][15];
                
                // background color set
                // g2.setColor( Color.CYAN);
                // g2.fillRect(0,0, 767,789);
                int column = 0;
                int row = 0;
                for( int r = 0; r < map.length; r++ )
                {
                    for( int c = 0; c < map[0].length; c++ )
                    {
                        row = r*50;
                        column = c*50;
                        if( r%2 == 0 && c%2 ==0)
                        {
                            g2.setColor( Color.BLACK);
                            Rectangle2D.Double rectangle1 = new Rectangle.Double( row, column, 50, 50 );
                            g2.draw( rectangle1 );
                            g2.drawString( "0", row+22, column+30 );
                        }
                        else if( r%2 == 1 && c%2 == 1)
                        {
                            g2.drawString( "=", row+25, column-25);
                            
                        }
                    }
                }
                
                
                /*
                // randomly prints out the string "Monster" or "Treasure"
                int sRow = 0;
                int sCol = 0;
                for( int r = 0; r < map.length; r++ )
                {
                    for( int c = 0; c < map[0].length; c++ )
                    {
                        sRow = r*50;
                        sCol = c*50;
                        int random = (int)(Math.random()*26);
                        if( random%26 == 0 )
                            g2.drawString("Monster", sRow+3, sCol+20);
                        else if (random%26 == 1)
                            g2.drawString("Treasure", sRow+1, sCol+20);
                    }
                }
                */
 
                g2.setColor( Color.WHITE );
                g2.fillRect( 0, 0, 50, 50);
                g2.fillRect(700,700,50,50);
                g2.setColor( Color.RED );
                g2.drawString( "BOSS",5,30 );
                g2.drawString( "START", 705, 730);
                
            }
        }
        
        
        
        
        
        PictureMap graphics = new PictureMap();
        frame.add( graphics );
        frame.setVisible( true );
    }
}
