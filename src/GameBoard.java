import javax.swing.*;

/**
 * Gameboard backbone.
 */
public class GameBoard {
    private String desc;
    private Actor player;
    //private Room [] roomList;


    public GameBoard(){
        desc = "You are located in Flashpoint.";
        String name = JOptionPane.showInputDialog("Input your name: ");
        player = new Actor(name);
    }

    public String getDesc(){
        return desc;
    }

    public Actor getPlayer(){
        return player;
    }

    public String getPlayerName(){
        return player.getName();
    }
}
