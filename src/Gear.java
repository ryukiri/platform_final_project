/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DuncanSkyrim
 */
public class Gear extends Item {
    
    private String slot;
    public Gear (Actor a, String newSlot){
        super(a);
        slot = newSlot;
    }
    
    public Gear (Room r, String newSlot){
        super(r);
        slot = newSlot;
    }
    
    public String getSlot(){
        return slot;
    }
}
