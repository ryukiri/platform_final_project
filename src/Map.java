import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Background frameworks for the map.
 */
public class Map extends JComponent {
    private static GameBoard board;
    
    public Map (GameBoard g){
        board = g;
    }
    public void paintComponent(Graphics g){
        // Recover Graphics g
        Graphics2D g2 = (Graphics2D) g;
        int[][] map = new int[17][17];

        int column = 0, row;
        for( int r = 0; r < map.length; r++ ){
            for( int c = 0; c < map[0].length; c++ ){
                row = r*30;
                column = c*30;
                g2.setColor( Color.BLACK);
                Rectangle2D.Double rectangle1 = new Rectangle.Double( row, column, 30, 30 );
                g2.draw( rectangle1 );

                //g2.drawString( "A", (int)(Math.random()+1)*row + 5, (int)(Math.random()+1)*column -5);
            }
        }

        int sRow = 0;
        int sColumn = 0;
        for( int r = 0; r < map.length; r++ ){
            for(int c = 0; c < map[0].length; c++ ){
                sRow = r * 30;
                sColumn = (map[0].length- c - 1)*30;
                //System.out.print(random);
                Room testRoom = board.getLocation(r,c, "0");
                if( testRoom != null){
                    if(board.getPlayer().getLocation().equals(testRoom))
                        g2.drawString("P", sRow + 12, sColumn - 10);
                    else if(testRoom.hasExplored()){
                        g2.drawString(testRoom.getSymbol(), sRow + 2, sColumn - 10);
                    }
                    else
                       g2.drawString("0", sRow + 12, sColumn - 10);
                }
            }
        }
    }
}
