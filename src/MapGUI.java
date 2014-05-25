import javax.swing.*;

/**
 * Set main map GUIs
 */
public class MapGUI {
    public static void mapFrame(){
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setTitle( "Graphics Example" );

        JPanel panel = new JPanel();
        Map graphics = new Map();
        frame.add(graphics);
        frame.setVisible(true);
    }
}
