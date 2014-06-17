import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
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
        Map graphics = new Map(board);
        frame.setLocation( 750,0);
        frame.add(graphics);
        frame.setVisible(true);
    }
    
    public void revalid()
    {
        fra.repaint();
        fra.revalidate();
    }
}
