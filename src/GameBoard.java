import javax.swing.*;
import java.util.*;

/**
 * Gameboard backbone.
 */
public class GameBoard {
    private int level;
    private String desc;
    private Actor player;
    private Actor fast;
    private Actor faster;
    private Actor fastest;
    private ArrayList <Room> roomList;
    private String worldName;
    private MainGame mainGame;
    private Room Centauri;
    private Room temp;
    private Item drug;


    public GameBoard(MainGame q){
        mainGame = q;
        worldName = "Flashpoint";
        roomList = new ArrayList <Room> ();
        desc = "You are located in Station Flashpoint.";
        String name = JOptionPane.showInputDialog("Input your name: ");
//public Sword (Room r, String s, int newValue, int newRange){

        //Centauri
        Centauri = new Room(6,0,this, "CEN");
        Centauri.setName("Centauri");
        Centauri.setDesc("A darkened docking bay stretches out in front of you, littered by dormant ships and shuttles.");
        player = new Actor(name, mainGame, Centauri);
        fast = new Actor("Fast", Centauri);
        player.move(Centauri);
        fast.move(Centauri);
        fast.setSpeed(1);
        level = player.getLevel().getValue();
        spawnRandomDungeon(Centauri);

        drug = new MedKit(player, 12);
        drug.setName("Potion");
        drug = new MedKit(player, -12);
        drug.setName("Poison");
        Sword superSword = new Sword(Centauri, "Blasting Wand", 2, 34);

        //Weapons, all in Centauri for testing purposes
        //Sword sword = new Sword(Centauri, 3, level);
        //Gun gun = new Gun(Centauri, 1, level);
        //Saber saber = new Saber(Centauri, 6, level);
        //Falcon falcon = new Falcon(Centauri, 10, level);
        //Fork fork = new Fork(Centauri, 0, level);
        //Bow bow = new Bow(Centauri, 9, level);
        //Hammer hammer = new Hammer(Centauri, 8, level);

        //putItems();

        //Armor
        //CookingPan pan = new CookingPan(Centauri, 10, level);
        //BulletProofVest vest = new BulletProofVest(Centauri, 20, level);
        //ConstructionHelmet helmet = new ConstructionHelmet(Centauri, 7, level);
        //WheelBarrow barrow = new WheelBarrow(Centauri, 15, level);
        //LightningRod stick = new LightningRod(Centauri, 12, level);

        roomList.add(Centauri);

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

            /*if(spawnRoom.getName().equals("Medical Bay")) {
                drug = new MedKit(spawnRoom, 12);
                drug.setName("Potion");
                drug = new MedKit(spawnRoom, 1);
                drug.setName("Potion");
                drug = new MedKit(spawnRoom, 10);
                drug.setName("Potion");
                drug = new MedKit(spawnRoom, -12);
                drug.setName("Poison");
                drug = new MedKit(spawnRoom, -12);
                drug.setName("Poison");
            }else if(rightRoom.getName().equals("Medical Bay")) {
                drug = new MedKit(rightRoom, 12);
                drug.setName("Potion");
                drug = new MedKit(rightRoom, 1);
                drug.setName("Potion");
                drug = new MedKit(rightRoom, 10);
                drug.setName("Potion");
                drug = new MedKit(rightRoom, -12);
                drug.setName("Poison");
                drug = new MedKit(rightRoom, -12);
                drug.setName("Poison");
            }else if(leftRoom.getName().equals("Medical Bay")) {
                drug = new MedKit(leftRoom, 12);
                drug.setName("Potion");
                drug = new MedKit(leftRoom, 1);
                drug.setName("Potion");
                drug = new MedKit(leftRoom, 10);
                drug.setName("Potion");
                drug = new MedKit(leftRoom, -12);
                drug.setName("Poison");
                drug = new MedKit(leftRoom, -12);
                drug.setName("Poison");
            }else if(spawnRoom.getName().equals("Kitchen")){
                Fork fork = new Fork(spawnRoom, 6, level);
                CookingPan pan = new CookingPan(spawnRoom, 10, level);
            }else if(leftRoom.getName().equals("Kitchen")){
                Fork fork = new Fork(leftRoom, 6, level);
                CookingPan pan = new CookingPan(leftRoom, 10, level);
            }else if(rightRoom.getName().equals("Kitchen")){
                Fork fork = new Fork(rightRoom, 6, level);
                CookingPan pan = new CookingPan(rightRoom, 10, level);
            }else if(spawnRoom.getName().equals("Abandoned Barracks")){
                BulletProofVest vest = new BulletProofVest(spawnRoom, 20, level);
                WheelBarrow barrow = new WheelBarrow(spawnRoom, 15, level);
            }else if(leftRoom.getName().equals("Abandoned Barracks")){
                BulletProofVest vest = new BulletProofVest(leftRoom, 20, level);
                WheelBarrow barrow = new WheelBarrow(leftRoom, 15, level);
            }else if(rightRoom.getName().equals("Abandoned Barracks")){
                BulletProofVest vest = new BulletProofVest(rightRoom, 20, level);
                WheelBarrow barrow = new WheelBarrow(rightRoom, 15, level);
            }else if(spawnRoom.getName().equals("Lodging Area")){
                LightningRod stick = new LightningRod(spawnRoom, 12, level);
            }else if(leftRoom.getName().equals("Lodging Area")){
                LightningRod stick = new LightningRod(leftRoom, 12, level);
            }else if(rightRoom.getName().equals("Lodging Area")){
                LightningRod stick = new LightningRod(rightRoom, 12, level);
            }else if(spawnRoom.getName().equals("Storage Room")){
                ConstructionHelmet helmet = new ConstructionHelmet(spawnRoom, 7, level);
            }else if(leftRoom.getName().equals("Storage Room")){
                ConstructionHelmet helmet = new ConstructionHelmet(leftRoom, 7, level);
            }else if(rightRoom.getName().equals("Storage Room")){
                ConstructionHelmet helmet = new ConstructionHelmet(rightRoom, 7, level);
            }else if(spawnRoom.getName().equals("Repairs and Logistics")){
                ConstructionHelmet helmet = new ConstructionHelmet(spawnRoom, 7, level);
            }else if(leftRoom.getName().equals("Repairs and Logistics")){
                ConstructionHelmet helmet = new ConstructionHelmet(leftRoom, 7, level);
            }else if(rightRoom.getName().equals("Repairs and Logistics")){
                ConstructionHelmet helmet = new ConstructionHelmet(rightRoom, 7, level);
            }else if(spawnRoom.getName().equals("Engine Room")){
                ConstructionHelmet helmet = new ConstructionHelmet(spawnRoom, 7, level);
            }else if(leftRoom.getName().equals("Engine Room")){
                ConstructionHelmet helmet = new ConstructionHelmet(leftRoom, 7, level);
            }else if(rightRoom.getName().equals("Engine Room")){
                ConstructionHelmet helmet = new ConstructionHelmet(rightRoom, 7, level);
            }else if(spawnRoom.getName().equals("Research and Development")){
                Sword sword = new Sword(spawnRoom, 3, level);
            }else if(leftRoom.getName().equals("Research and Development")){
                Sword sword = new Sword(leftRoom, 3, level);
            }else if(rightRoom.getName().equals("Research and Development")){
                Sword sword = new Sword(rightRoom, 3, level);
            }else if(spawnRoom.getName().equals("Jail")){
                Falcon falcon = new Falcon(spawnRoom, 10, level);
                drug = new MedKit(spawnRoom, -22);
                drug.setName("Poison");
                drug = new MedKit(spawnRoom, -32);
                drug.setName("Poison");
            }else if(leftRoom.equals("Jail")){
                Falcon falcon = new Falcon(leftRoom, 10, level);
                drug = new MedKit(leftRoom, -22);
                drug.setName("Poison");
                drug = new MedKit(leftRoom, -32);
                drug.setName("Poison");
            }else if(rightRoom.getName().equals("Jail")){
                Falcon falcon = new Falcon(rightRoom, 10, level);
                drug = new MedKit(rightRoom, -22);
                drug.setName("Poison");
                drug = new MedKit(rightRoom, -32);
                drug.setName("Poison");
            }else if(spawnRoom.getName().equals("Centauri")){
                drug = new MedKit(spawnRoom, -22);
                drug.setName("Poison");
            }else if(leftRoom.getName().equals("Centauri")){
                drug = new MedKit(leftRoom, -22);
                drug.setName("Poison");
            }else if(rightRoom.getName().equals("Centauri")){
                drug = new MedKit(rightRoom, -22);
                drug.setName("Poison");
            }else if(spawnRoom.getName().equals("Waste Management Facility")){

            }else if(leftRoom.getName().equals("Waste Management Facility")){

            }else if(rightRoom.getName().equals("Waste Management Facility")){

            }else if(spawnRoom.getName().equals("Emergency Space Shuttles")){

            }else if(leftRoom.getName().equals("Emergency Space Shuttles")){

            }else if(rightRoom.getName().equals("Emergency Space Shuttles")){

            }else if(spawnRoom.getName().equals("Co-Captain Room")){

            }else if(leftRoom.getName().equals("Co-Captain Room")){

            }else if(rightRoom.getName().equals("Co-Captain Room")){

            }else if(spawnRoom.getName().equals("Captain Room")){
                Hammer hammer = new Hammer(spawnRoom, 8, level);
            }else if(leftRoom.getName().equals("Captain Room")){
                Hammer hammer = new Hammer(leftRoom, 8, level);
            }else if(rightRoom.getName().equals("Captain Room")){
                Hammer hammer = new Hammer(rightRoom, 8, level);
            }else if(spawnRoom.getName().equals("Main Deck")){
                Saber saber = new Saber(spawnRoom, 6, level);
            }else if(leftRoom.getName().equals("Main Deck")){
                Saber saber = new Saber(leftRoom, 6, level);
            }else if(rightRoom.getName().equals("Main Deck")){
                Saber saber = new Saber(rightRoom, 6, level);
            }else if(spawnRoom.getName().equals("Shower Room")){

            }else if(leftRoom.getName().equals("Shower Room")){

            }else if(rightRoom.getName().equals("Shower Room")){

            }else if(spawnRoom.getName().equals("Kitchen")){
                Fork fork = new Fork(spawnRoom, 6, level);
                CookingPan pan = new CookingPan(spawnRoom, 10, level);
            }else if(leftRoom.getName().equals("Kitchen")){
                Fork fork = new Fork(leftRoom, 6, level);
                CookingPan pan = new CookingPan(leftRoom, 10, level);
            }else if(rightRoom.getName().equals("Kitchen")){
                Fork fork = new Fork(rightRoom, 6, level);
                CookingPan pan = new CookingPan(rightRoom, 10, level);
            }else if(spawnRoom.getName().equals("Dinning Room")){
                Fork fork = new Fork(spawnRoom, 0, level);
                fork = new Fork(spawnRoom, 3, level);
                fork = new Fork(spawnRoom, 1, level);
            }else if(leftRoom.getName().equals("Dinning Room")){
                Fork fork = new Fork(leftRoom, 0, level);
                fork = new Fork(leftRoom, 3, level);
                fork = new Fork(leftRoom, 1, level);
            }else if(rightRoom.getName().equals("Dinning Room")){
                Fork fork = new Fork(rightRoom, 0, level);
                fork = new Fork(rightRoom, 3, level);
                fork = new Fork(rightRoom, 1, level);
            }else if(spawnRoom.getName().equals("Weapon Storage Room")){
                Bow bow = new Bow(spawnRoom, 9, level);
                Gun gun = new Gun(spawnRoom, 1, level);
            }else if(leftRoom.getName().equals("Weapon Storage Room")){
                Bow bow = new Bow(leftRoom, 9, level);
                Gun gun = new Gun(leftRoom, 1, level);
            }else if(rightRoom.getName().equals("Weapon Storage Room")){
                Bow bow = new Bow(rightRoom, 9, level);
                Gun gun = new Gun(rightRoom, 1, level);
            }else if(spawnRoom.getName().equals("Superconductivity Plant")){
                ConstructionHelmet helmet = new ConstructionHelmet(spawnRoom, 7, level);
                BulletProofVest vest = new BulletProofVest(spawnRoom, 20, level);
            }else if(leftRoom.getName().equals("Superconductivity Plant")){
                ConstructionHelmet helmet = new ConstructionHelmet(leftRoom, 7, level);
                BulletProofVest vest = new BulletProofVest(leftRoom, 20, level);
            }else if(rightRoom.getName().equals("Superconductivity Plant")){
                ConstructionHelmet helmet = new ConstructionHelmet(rightRoom, 7, level);
                BulletProofVest vest = new BulletProofVest(rightRoom, 20, level);
            }
            */

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
