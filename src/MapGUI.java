import javax.swing.*;

/**
 * Set main map GUIs
 */
public class MapGUI {
    private static GameBoard board;
    
    public MapGUI(GameBoard g){
        board = g;
    }
    public static void mapFrame(){
        JFrame frame = new JFrame();
        frame.setSize(527,550);
        frame.setTitle( "Graphics Example" );

        JPanel panel = new JPanel();
        Map graphics = new Map(board);
        frame.add(graphics);
        frame.setVisible(true);
    }
}
