/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DuncanSkyrim
 */
public class Sword extends Weapon{
    
    public Sword(Room r, String s){
        super(r, "mainhand", 12, 5);
        setName(s);
    }
    
    public Sword (Room r, String s, int newValue, int newRange){
        super(r, "mainhand", newValue, newRange);
        setName(s);
    }
}
