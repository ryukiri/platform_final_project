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
    private MainGame mainGame;


    public GameBoard(){
        worldName = "Flashpoint";
        roomList = new ArrayList <Room> ();
        desc = "You are located in Station Flashpoint.";
        String name = JOptionPane.showInputDialog("Input your name: ");
        player = new Actor(name);

        //Centauri
        Room Centauri = new Room(8,0,this, "CEN");
        Centauri.setName("Centauri");
        Centauri.setDesc("A darkened docking bay stretches out in front of you, littered by dormant ships and shuttles.");
        player.setLocation(Centauri);
        spawnRandomDungeon(Centauri);
        //spawnLinearDungeon(Centauri);
        Item qq = new Gear(Centauri, "mainhand");
        qq.setName("Super Blunderbuss of Death");
        qq = new Gear(player, "mainhand");
        qq.setName("Blasting Rifle");

        Item drug = new MedKit(player);
        drug.setName("Potion");
        drug = new MedKit(player);
        drug.setName("Poison");

        //Dremol
        Room Dremol = new Room(0,-1, this);
        Dremol.setName("Dremol");
        Dremol.setDesc("Dremol, one of the centrifugal nuclear generators, is now dormant.");

        //Caljack
        Room Caljack = new Room(-1, -1, this);
        Caljack.setName("CalJack");
        Caljack.setDesc("CalJack is a repair station, though now most of the equipment is dysfuntional.");

        //Vionla
        Room Vionla = new Room(0,-2, this);
        Vionla.setName("Vionla");
        Vionla.setDesc("Violna is the residence quarters. Confusing, how everything is named in a foreign language.");

        //Tythis
        Room Tythis = new Room(1,-1, this);
        Tythis.setName("Tythis");
        Tythis.setDesc("Welcome to the turret room, dubbed Tythis. You might find some useless .5 caliber incedinary ammo here.");

        //Itghones
        Room Itghones = new Room(0,1, this);
        Itghones.setName("Itghones");
        Itghones.setDesc("Itghones is the room where almost everything magically disappears. Do not leave anything behind..");

        //Nuqueroth
        Room Nuqueroth = new Room(0,2, this);
        Nuqueroth.setName("Nuqueroth");
        Nuqueroth.setDesc("Welcome to Nuqueroth where the sound of bubbling liquids echo throughout this abandoned alchemist's work-room.");

        //Swathwaite
        Room Swathwaite = new Room(0,3,this);
        Swathwaite.setName("Swathwaite");
        Swathwaite.setDesc("Swathwaite is the main public bath area. The place is usually empty but when its busy, its packed.");

        //Bludown
        Room Bludown = new Room(-1,0, this);
        Bludown.setName("Bludown");
        Bludown.setDesc("Bludown, an abandoned small plaza where you could find some items.");

        //Sldenser
        Room Sldenser = new Room(1,2, this);
        Sldenser.setName("Sldenser");
        Sldenser.setDesc("Dozens of dead animals lay here. Sldenser doesn't seem to be safe...better get out of here.");

        //Thusdale
        Room Thusdale = new Room(1,3, this);
        Thusdale.setName("Thusdale");
        Thusdale.setDesc("You open a door and walk into what seems like a combat room. Thusdale is full of dummies and " +
                "weight lifting equipment.");

        //Eweworth
        Room Eweworth = new Room(2,0, this);
        Eweworth.setName("Eweworth");
        Eweworth.setDesc("Eweworth, an empty, grassy area used for trading items.");

        //Astoudown
        Room Astoudown = new Room(-1,-2, this);
        Astoudown.setName("Astoudown");
        Astoudown.setDesc("Welcome to Astoudown. Here you can find many shops to purchase and optimize weapons.");

        //Helgrove
        Room Helgrove = new Room(-1,3, this);
        Helgrove.setName("Helgrove");
        Helgrove.setDesc("Helgrove, a small town where you can practice your combat moves.");

        //Floupond
        Room Floupond = new Room(2,1, this);
        Floupond.setName("Floupond");
        Floupond.setDesc("Floupond used to be a barn for zoo animals. Now you can only find abandoned farm animals who" +
                "show up from time to time.");

        roomList.add(Centauri);
        /*roomList.add(Dremol);
        roomList.add(Vionla);
        roomList.add(Caljack);
        roomList.add(Tythis);
        roomList.add(Itghones);
        roomList.add(Nuqueroth);
        roomList.add(Swathwaite);
        roomList.add(Bludown);
        roomList.add(Sldenser);
        roomList.add(Thusdale);
        roomList.add(Eweworth);
        roomList.add(Astoudown);
        roomList.add(Helgrove);
        roomList.add(Floupond);*/

        for(Room r : roomList){
            System.out.println(r.getName() + " " + r.getCoordinates());
            r.connectRooms();
            System.out.println(r.getExits());
        }
    }
    
    public Room getLocation(int productX, int productY, String name){
        for(Room stack : roomList){
            if(!stack.getName().equals(name)){
                if(stack != null)
                    if(stack.getX() == productX && stack.getY() == productY){
                        return stack;
                    }
                    
            }
        }
        return null;
    }

    //Experimental: returns true if there is a room there
    public boolean isRoom(int x, int y){
        for(Room stack: roomList){
            if(stack != null){
                if(stack.getX() == x && stack.getY() == y){
                    return true;
                }
            }
        }
        return true;
    }

    //Use instanceof
    public boolean hasRoom(int x, int y){
        for(Room stack: roomList){
            if(stack != null){
                if(stack.getX() == x && stack.getY() == y){
                    return true;
                }
            }
        }
        return true;
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
    
    public void spawnLinearDungeon(Room r){
        Scanner input = new Scanner(System.in);
        System.out.println("How many spaces?");
        int x = input.nextInt();
        for(int i = 0; i < x; i++){
            Room spawnRoom = new Room(r.getX(), r.getY()+i+1, this, (i+1) + "R");
            spawnRoom.setName("Tile" + (i+1));
            spawnRoom.setDesc("notation " + i);
            spawnRoom.setAdd();
            roomList.add(spawnRoom);
            
        }
    }
    
  public void spawnRandomDungeon(Room r){
        int tempRan;
        int x = r.getX();
        int y = r.getY();

        Scanner input = new Scanner(System.in);
        System.out.println("How many spaces?");
        int z = input.nextInt();
        for(int i = 0; i < z; i++){
            Room spawnRoom = new Room(x, i+1+y, this);
            Room rightRoom = new Room(x+1, i+1+y, this);
            Room leftRoom = new Room(x-1, i+1+y, this);
            //spawnRoom.setAdd();
            roomList.add(spawnRoom);
            roomList.add(rightRoom);
            roomList.add(leftRoom);

            int randomNum = (int) ((Math.random()*2)+1);
            int pick = (int) ((Math.random()*2) + 1);
            if(pick == 1){
                //Prevents from going off the screen
                if(x>12) {
                    x-=2;
                }else
                    x+= randomNum;
            }else{
                //Prevents from going off screen
                if(x<0){
                    x+=2;
                }else
                    x -= randomNum;
            }
        }
    } 
    public void addRoom(Room r){
        roomList.add(r);
    }
    
    public void setParentGUI(MainGame m){
        mainGame = m;
    }
    
    public MainGame getMainGame(){
        return mainGame;
    }
}
