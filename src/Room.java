/*
 *
 * @author DuncanSkyrim
 */

import java.util.*;

public class Room {
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private int x;
    private int y;
    private String desc;
    private String name;
    private GameBoard parentBoard;
    private boolean explored = false;
    private String symbol;
    private ArrayList <Item> contents;
    
    public Room(int newX, int newY, GameBoard g, String newSymbol){
        x = newX;
        y = newY;
        parentBoard = g;
        symbol = newSymbol;
        contents = new ArrayList <Item> ();
    }
    
    public Room(int newX, int newY, GameBoard g){ // there needs to be moar!
        x = newX;
        y = newY;
        parentBoard = g;
        symbol = "X";
        contents = new ArrayList <Item> ();
        int randomNum = (int) ((Math.random()*9)+1);
        if(randomNum == 1){
            name = "Medical Bay";
            desc = "A medical bay stands before you. Maybe there are some medical kits to be had here.";
            symbol = "MDB";
        }
        if(randomNum == 2){
            name = "Abandoned Barracks";
            desc = "Soldiers and officers used to mill about here between their battles and duties. Weapons might be here. Go look around.";
            symbol = "BAR";
            
        }
        if(randomNum == 3){
            name = "Lodging Area";
            desc = "Civillians used to rest here. They might all be dead, or possibly, they might all be abducted by those blasted alien robots.";
            symbol = "LDA";           
        }
        
        if(randomNum == 4){
            name = "Storage Room";
            desc = "A set of canisters, capsules, and boxes lie about in an expansive storage room. Time to get to work on looking for useful items.";
            symbol = "SOR";           
        }
        if(randomNum == 5){
            name = "Repairs and Logistics";
            desc = "Robot and other mechanical parts lie strewn about in worktables and shelves. Be careful not to be shocked here by stray static charges.";
            symbol = "RAL";
        }
        if(randomNum == 6){
            name = "Engine Room";
            desc = "Here lies the core that powered the thrusters of the ship. It is dead now, and there is no way of getting it working again.";
            symbol = "ENR";
        }
        if(randomNum == 7){
            name = "Research and Development";
            desc = "Scientists used to labor here in strenuous efforts to create the ultimate medicine, the ultimate stimulant, or maybe the ultmate weapon of mass destruction.";
            symbol= "RAD";
            
        }
        if(randomNum == 8){
            name = "Superconductivity Plant";
            desc = "This is the heart of the mothership. It's disheartening to see it in such a dysfunctional, broken state.";
            symbol = "SCP";
            
        }
        if(randomNum == 9){
            name = "Waste Management Facility";
            desc = "All of the garbage and refuse leaves through ducts that fire at stars as a natural vaporization method.";
            symbol = "WMF";
        }
    }
            
    
    public Room(GameBoard g, String newSymbol){
        x = 0;
        y = 0;
        parentBoard = g;
        symbol = newSymbol;
    }
    
    public void explored(){
        explored = true;
    }
    
    public boolean hasExplored(){
        return explored;
    }
    
    public String getSymbol(){
        return symbol;
    }
    
    
    public Room getNorth(){
        return north;
    }
    
    public Room getEast(){
        return east;
    }
    
    public Room getSouth(){
        return south;
    }
    
    public Room getWest(){
        return west;
    }
    
    public void connectRooms(){
        GameBoard g = parentBoard;
        Room tempNorth = g.getLocation(x, y+1, name);
        if(tempNorth != null){
            north = tempNorth;
        }
        Room tempEast = g.getLocation(x+1, y, name);
        if(tempEast != null){
            east = tempEast;
        }
        Room tempSouth = g.getLocation(x, y-1, name);
        if(tempSouth != null){
            south = tempSouth;
        }
        Room tempWest = g.getLocation(x-1, y, name);
        if(tempWest != null){
            west = tempWest;
        }
    }
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public String getDesc(){
        return desc;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public void setDesc(String newDesc){
        desc = newDesc;
    }
    
    public String getCoordinates(){
        return "" + x + "," + y ;
    }
    
    public String getExits(){
        String x = "";
        if(north != null)
            x += " North: " + north.getName() + " |";
        if(east != null)
            x += " East: " + east.getName() +" |";
        if(south != null)
            x += " South: " + south.getName() +" |";
        if(west != null)
            x += " West: " + west.getName() + " |";
        if(x.equals(""))
            return "There are no exits";
        return x;
    }
    
    public void setDirRoom(int x, Room r){
        if(x == 0){
            north = r;
        if(x == 1){
            east = r;
        }
        if(x == 2){
            south = r;
        }
        if(x == 3){
            west = r;
        }
        }
    }
    
    public void setAdd(){
        int z= 2;
        for(int i = 1; i <= 2; i++){
            Room setRoom = new Room(z, this.y, parentBoard);
            setRoom.setName("Derivative" + (i));
            setRoom.setDesc("notation " + i);
            z+=2;
            parentBoard.addRoom(setRoom);
        }
    }

    public boolean equals(Room r){
        if(r.getCoordinates().equals(getCoordinates()))
            return true;
        return false;
    }
    
    public ArrayList <Item> getContents(){
        return contents;
    }
}
