import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Set main map GUIs
 */
public class MapGUI {
    private static GameBoard board;
    private static JFrame fra;
    public MapGUI(GameBoard g){
        board = g;
    }
    public static void mapFrame(){
        JFrame frame = new JFrame();
        fra = frame;
        frame.setSize(527,550);
        frame.setTitle( "MAP" );

        JPanel panel = new JPanel();
        Map graphics = new Map(board);
        frame.add(graphics);
        frame.setVisible(true);
        
    }
    
    public void revalid()
    {
        fra.repaint();
        fra.revalidate();
        
    }
}
