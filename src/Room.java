/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DuncanSkyrim
 */
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
    
    public Room(int newX, int newY, GameBoard g){
        x = newX;
        y = newY;
        parentBoard = g;
        
    }
    
    public Room(GameBoard g){
        x = 0;
        y = 0;
        parentBoard = g;
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
        System.out.println("NAME: " + g.getName());
        System.out.println("northcheck");
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
            x += " North: " + north.getName();
        if(east != null)
            x += " East: " + east.getName();
        if(south != null)
            x += " South: " + south.getName();
        if(west != null)
            x += " West: " + west.getName();
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
}
    
