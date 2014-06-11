import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sun.font.GraphicComponent;

/**
 * Background frameworks for the map.
 */
public class Map extends JComponent {
    private static GameBoard board;
    private static JPanel mainPanel = new JPanel();
    
    public Map (GameBoard g){
        board = g;
    }
    public void paintComponent(Graphics g)
    {
        // http://stackoverflow.com/questions/18777893/jframe-background-image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("space.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GraphicComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        int[][] map = new int[17][17];
        
        int sRow = 0;
        int sColumn = 0;
        for( int r = 0; r < map.length; r++ ){
            for(int c = 0; c < map[0].length; c++ ){
                sRow = r * 30;
                sColumn = (map[0].length- c - 1)*30;
                Room testRoom = board.getLocation(r,c, "0");
                if( testRoom != null){
                    if(board.getPlayer().getLocation().equals(testRoom))
                    {
                        g2.setColor( Color.WHITE );
                        g2.fillRect( sRow, sColumn, 30,30);
                        g2.setColor( Color.RED );
                        g2.drawRect( sRow, sColumn, 30,30);
                        g2.setColor( Color.BLACK );
                        g2.drawString("P", sRow + 12, sColumn +20);
                    }
                    else if(testRoom.hasExplored()){
                        g2.setColor( Color.WHITE );
                        g2.fillRect( sRow, sColumn, 30,30);
                        g2.setColor( Color.BLACK );
                        g2.drawRect( sRow, sColumn, 30, 30 );
                        g2.drawString(testRoom.getSymbol(), sRow + 2, sColumn +20);
                    }
                    else
                    {
                        g2.setColor(Color.BLACK);
                        g2.fillRect(sRow, sColumn, 30, 30);
                        g2.setColor(Color.BLUE);
                        g2.drawRect(sRow, sColumn, 30, 30);
                    }
                }
            }
        }
    }
}
