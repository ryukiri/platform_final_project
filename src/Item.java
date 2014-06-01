/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DuncanSkyrim
 */
public class Item {
    private String name;
    private Actor holder;
    private Room location;
    
    public Item(Room r){
        location = r;
        r.getContents().add(this);
    }
    
    public Item(Actor a){
        holder = a;
        a.getContents().add(this);
        
    }
    
    public void Move(Actor A){
        remove();
        holder = A;
        A.getContents().add(this);
    }
    
    public void Move(Room r){
        remove();
        location = r;
        r.getContents().add(this);
    }
    
    public void remove(){
        if(holder != null){
            holder.getContents().remove(this);
            holder = null;
        }
        if(location != null){
            location.getContents().remove(this);
            location = null;
        }
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
        
    }
    
}
