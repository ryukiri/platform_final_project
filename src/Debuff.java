
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DuncanSkyrim
 */
public class Debuff {
    private String name;
    private int damage;
    private int duration;
    private Stat targetStat;
    private Actor afflicted;
    private Actor inflicting;
    private Boolean isTriggered = false;
    private Boolean curse;
    private JTextArea mainField;
    
    public Debuff(String s, Actor a, Stat st, Boolean curseOrNot){
        name = s;
        afflicted = a;
        targetStat = st;
        curse = curseOrNot;
        mainField = a.getLocation().getGameBoard().getMainGame().getTextArea();
        
    }
    
    public void trigger(int dmg, Actor rudeBoy, int dur){
        isTriggered = true;
        Actor inflicting = rudeBoy;
        duration = dur;
        damage = dmg;
        if(damage > 0){
            if(curse){
                mainField.append(afflicted.getName() + " has taken " + damage + " points of damage to the " + targetStat.getName() + " stat!");
                if(targetStat.getName().equals("Health") || targetStat.getName().equals("Stamina")){
                    targetStat.subtract(dmg);
                }else{
                    targetStat.permAdd(-dmg);
                }
            }
        }
    }
    
    public void turnPass(){
        if(isTriggered){
            duration--;
            if(!curse){
                targetStat.subtract(damage);
                mainField.append(afflicted.getName() + " has taken " + damage + " points of damage to the " + targetStat.getName() + " stat!");
            }
            if(duration == 0){
                mainField.append(afflicted.getName() + "has recovered from " + name);
                isTriggered = false;
                if(curse){
                    if(targetStat.getName().equals("Health") || targetStat.getName().equals("Stamina")){
                        targetStat.add(damage);
                    }else{
                        targetStat.permAdd(damage);
                    }
                }
            }
        }
    }
    public Boolean getTrigger(){
        return isTriggered;
    }
    
    public String getName(){
        return name;
    }
    
    public void extinguish(){
        isTriggered = false;
    }
}
