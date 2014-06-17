import javax.swing.*;
import java.util.*;

/**
 * Gameboard backbone.
 */
public class GameBoard {
    private Actor player;
    private Actor fast;
    private Actor faster;
    private Actor fastest;
    private Actor enemy;
    private ArrayList <Room> roomList;
    private int level;
    private Item drug;
    private MainGame mainGame;
    private Room Centauri;
    private Room location;
    private String desc;
    private String worldName;

    public GameBoard(MainGame q){
        mainGame = q;
        worldName = "Flashpoint";
        roomList = new ArrayList <Room> ();
        desc = "You are located in Station Flashpoint.";
        String name = JOptionPane.showInputDialog("Input your name: ");
        player = new Actor(name, mainGame);
        fast = new Actor("Fast");
        fast.setSpeed(2);
        faster = new Actor("Faster");
        faster.setSpeed(3);
        fastest = new Actor("Fastest");
        fastest.setSpeed(4);

        level = player.getLevel().getValue();

        //Enemies
        enemy = new Enemy(10, 10, 2, Centauri);
        enemy.setActorName("Enemy");

        //Centauri
        Centauri = new Room(6,0,this, "CEN");
        Centauri.setName("Centauri");
        Centauri.setDesc("A darkened docking bay stretches out in front of you, littered by dormant ships and shuttles.");
        player.move(Centauri);
        fast.move(Centauri);
        faster.move(Centauri);
        fastest.move(Centauri);
        spawnRandomDungeon(Centauri);

        drug = new MedKit(player, 12);
        drug.setName("Potion");
        drug = new MedKit(player, -12);
        drug.setName("Poison");

        roomList.add(Centauri);

        for(Room r : roomList){
            System.out.println(r.getName() + " " + r.getCoordinates());
            r.connectRooms();
            System.out.println(r.getExits());
        }
    }

    public GameBoard(){

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

    public void addItem(){
        for(Room box : roomList){
            if(box.equals("Medical Bay")){
                drug = new MedKit(box, 12);
                drug.setName("Potion");
                drug = new MedKit(box, 1);
                drug.setName("Potion");
                drug = new MedKit(box, 10);
                drug.setName("Potion");
                drug = new MedKit(box, -12);
                drug.setName("Poison");
                drug = new MedKit(box, -12);
                drug.setName("Poison");
            }else if(box.getName().equals("Kitchen")){
                Fork fork = new Fork(box, 6, level);
                CookingPan pan = new CookingPan(box, 10, level);
            }else if(box.getName().equals("Abandoned Barracks")){
                BulletProofVest vest = new BulletProofVest(box, 20, level);
                WheelBarrow barrow = new WheelBarrow(box, 15, level);
            }else if(box.getName().equals("Lodging Area")){
                LightningRod stick = new LightningRod(box, 12, level);
            }else if(box.getName().equals("Storage Room")){
                ConstructionHelmet helmet = new ConstructionHelmet(box, 7, level);
            }else if(box.getName().equals("Repairs and Logistics")){
                ConstructionHelmet helmet = new ConstructionHelmet(box, 7, level);
            }else if(box.getName().equals("Engine Room")){
                ConstructionHelmet helmet = new ConstructionHelmet(box, 7, level);
            }else if(box.getName().equals("Research and Development")){
                Sword sword = new Sword(box, 3, level);
            }else if(box.getName().equals("Jail")){
                Falcon falcon = new Falcon(box, 10, level);
                drug = new MedKit(box, -22);
                drug.setName("Poison");
                drug = new MedKit(box, -32);
                drug.setName("Poison");
            }else if(box.getName().equals("Centauri")){
                drug = new MedKit(box, -22);
                drug.setName("Poison");
            }else if(box.getName().equals("Waste Management Facility")){

            }else if(box.getName().equals("Emergency Space Shuttles")){

            }else if(box.getName().equals("Co-Captain Room")){

            }else if(box.getName().equals("Captain Room")){
                Hammer hammer = new Hammer(box, 8, level);
            }else if(box.getName().equals("Main Deck")) {
                Saber saber = new Saber(box, 6, level);
            }else if(box.getName().equals("Shower Room")) {

            }else if(box.getName().equals("Kitchen")) {
                Fork fork = new Fork(box, 6, level);
                CookingPan pan = new CookingPan(box, 10, level);
            }else if(box.getName().equals("Dinning Room")) {
                Fork fork = new Fork(box, 0, level);
                fork = new Fork(box, 3, level);
                fork = new Fork(box, 1, level);
            }else if(box.getName().equals("Weapon Storage Room")) {
                Bow bow = new Bow(box, 9, level);
                Gun gun = new Gun(box, 1, level);
            }else if(box.getName().equals("Superconductivity Plant")){
                ConstructionHelmet helmet = new ConstructionHelmet(box, 7, level);
                BulletProofVest vest = new BulletProofVest(box, 20, level);
            }
        }
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
        //System.out.println("How many spaces?");
        //int x = input.nextInt();
        int x = 10;
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
        //System.out.println("How many spaces?");
        //int z = input.nextInt();
        int z=10;
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

        addItem();
    } 
    public void addRoom(Room r){
        roomList.add(r);
    }
    
    public MainGame getMainGame(){
        return mainGame;
    }
}
