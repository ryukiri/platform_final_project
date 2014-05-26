import javax.swing.*;
import java.util.*;

/**
 * Gameboard backbone.
 */
public class GameBoard {
    private String desc;
    private Actor player;
    private ArrayList <Room> roomList;
    private String worldName;


    public GameBoard(){
        worldName = "Flashpoint";
        roomList = new ArrayList <Room> ();
        desc = "You are located in Station Flashpoint.";
        String name = JOptionPane.showInputDialog("Input your name: ");
        player = new Actor(name);
        Room Centauri = new Room(this);
        Centauri.setName("Centauri");
        Centauri.setDesc("A darkened docking bay strethces out in front of you, littered by dormant ships and shuttles.");
        player.setLocation(Centauri);
        Room Dremol = new Room(0,-1, this);
        Dremol.setName("Dremol");
        Dremol.setDesc("Dremol, one of the centrifugal nuclear generators, is now dormant.");
        Room Caljack = new Room(-1, -1, this);
        Caljack.setName("CalJack");
        Caljack.setDesc("CalJack is a repair station, though now most of the equipment is dysfuntional.");
        Room Vionla = new Room(0,-2, this);
        Vionla.setName("Vionla");
        Vionla.setDesc("Violna is the residence quarters. Confusing, how everything is named in a foreign language.");
        Room Tythis = new Room(1,-1, this);
        Tythis.setName("Tythis");
        Tythis.setDesc("Welcome to the turret room, dubbed Tythis. You might find some useless .5 caliber incedinary ammo here.");
                
        roomList.add(Centauri);
        roomList.add(Dremol);
        roomList.add(Vionla);
        roomList.add(Caljack);
        roomList.add(Tythis);
        for(Room r : roomList){
            System.out.println(r.getName() + " " + r.getCoordinates());
        }
    }
    
    public Room getLocation(int productX, int productY, String name){
        for(Room stack : roomList){
            if(stack.getName() != name){
                System.out.println("scan vision");
                System.out.println(stack.getName() + " " + stack.getCoordinates());
                if(stack != null)
                    System.out.println("" + stack.getX() + " == " + productX + " && " + stack.getY() + " == " + productY);
                    if(stack.getX() == productX && stack.getY() == productY){
                        System.out.println(stack.getName()+ " returned ");
                        return stack;
                    }
                    
            }
        }
        System.out.println("failed to find location");
        return null;
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
    
    public ArrayList <Room> getList(){
        return roomList;
    }
    
    public String getName(){
        return worldName;
    }
}
