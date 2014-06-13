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
    private GameBoard gameBoard;
    private MainGame mainGame;
    private Actor actor;
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

    public Weapon(Room a, String newSlot, int atkVal, int atkRange, String name, int level){
        super(a, newSlot);
        range = atkRange;
        getRanNum(atkVal, level);

        String ss = "";
        if(attackVal >=0 && attackVal < 10){
            ss += "Highly Flammable Aluminum ";
            setName(name = ss + name);
        }else if(attackVal >= 10 && attackVal < 20){
            ss+="Metal Foam ";
            setName(name = ss + name);
        }else if(attackVal >= 20 && attackVal < 30){
            ss += "Carbonized Glass ";
            setName(name = ss + name);
        }else if(attackVal >= 30 && attackVal < 40){
            ss += "Liquid Metal ";
            setName(name = ss + name);
        }else if(attackVal >= 40 && attackVal < 50){
            ss += "Kryptic Hydronized Uranium ";
            setName(name = ss + name);
        }else if(attackVal >=50 && attackVal < 60){
            ss += "Radioactive Wooden Calcium Dilithium ";
            setName(name = ss + name);
        }
    }

    private void getRanNum(int atk, int lv){
        int y = (int)(Math.random()*10);
        int x = (atk+(lv*y)+y)/2;

        attackVal = x;
        System.out.println("y= " + y);
        System.out.println("attack value: " + attackVal);
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
