/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DuncanSkyrim
 */
public class Weapon extends Gear {
    private int attackVal;
    private int range;
    
    public Weapon(Actor a, String newSlot, int atkVal, int atkRange){
        super(a, newSlot);
        attackVal = atkVal;
        range = atkRange;
    }
    
    public Weapon(Room a, String newSlot, int atkVal, int atkRange){
        super(a, newSlot);
        attackVal = atkVal;
        range = atkRange;
    }
    
    public int getAttackVal(){
        return attackVal;
    }
    
    public int getRange(){
        return range;
    }
    
    public void setAttackVal(int x){
        attackVal = x;
    }
    
    public void setRange(int x){
        range = x;
    }
    
    @Override
    public void equipEffect(Actor a){
        a.getAtkVal().setValue(a.getAtkVal().getValue() + process(a, attackVal));
        a.getAtkVal().setMaxValue(a.getAtkVal().getMaxValue() + process(a, attackVal) + process(a, range));
    }
    @Override
    public void unequipEffect(Actor a){
        a.getAtkVal().setValue(a.getAtkVal().getValue() - process(a, attackVal));
        a.getAtkVal().setMaxValue(a.getAtkVal().getMaxValue() -  process(a, attackVal) - process(a, range));
    }
    
    public int process(Actor a, int x){
        System.out.println((double) a.getStr().getValue()/20 + " strength modifier");
        return (int) (x * (1 + (double) a.getStr().getValue()/20 ));
    }
}