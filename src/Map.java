import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Background frameworks for the map.
 */
public class Map extends JComponent {
    public void paintComponent(Graphics g){
        // Recover Graphics g
        Graphics2D g2 = (Graphics2D) g;
        int[][] map = new int[17][17];

        int column = 0, row;
        for( int r = 0; r < map.length; r++ ){
            for( int c = 0; c < map[0].length; c++ ){
                row = r*25;
                column = c*25;
                g2.setColor( Color.BLACK);
                Rectangle2D.Double rectangle1 = new Rectangle.Double( row, column, 25, 25 );
                g2.draw( rectangle1 );

                //g2.drawString( "A", (int)(Math.random()+1)*row + 5, (int)(Math.random()+1)*column -5);
            }
        }

        int sRow = 0;
        int sColumn = 0;
        for( int r = 0; r < map.length; r++ ){
            for(int c = 0; c < map[0].length; c++ ){
                sRow = r* 25;
                sColumn = c*25;
                int random = (int)(Math.random()*26);
                //System.out.print(random);
                if( random%26 == 0 )
                    g2.drawString("A", sRow+10, sColumn+20);
                else if (random%26 == 1)
                    g2.drawString("B", sRow+10, sColumn+20);
            }
        }
    }
}
