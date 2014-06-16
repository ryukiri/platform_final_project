
import java.util.*;
import javax.swing.JTextArea;

public class Offense extends Skill {
    private ArrayList <Actor> targets;
    
    public Offense(Actor a){
        super(a);
    }
    
    @Override
    public void activate(ArrayList <Actor> a){
        
    }
    
    public void vsOffense(){
        Offense first;
        Offense second = this;
        Offense opposingSkill = (Offense) getOpposingSkill();
        first = opposingSkill;
        if(getUser().hasInitiative()){
            getMainField().append("Initiative Breaker!" + "\n");
            first = this;
            second = opposingSkill;
            if(opposingSkill.getUser().hasInitiative()){
                getMainField().append("Initiative Counterbreaker!!!" + "\n");
                first = opposingSkill; 
                second = this;
            }
        }
        first.damageScene(second.getUser());
        second.damageScene(first.getUser());            
    }
    
    public void vsDefense(){
        Defense opposingSkill = (Defense) getOpposingSkill();
        //passback!
        opposingSkill.vsOffense(this);
                
    }
    
    public void damageScene(Actor target){
        //find a way to implement critical chance. incomplete! does not function!
        for(Debuff d : getUser().getDebuffs()){
            if(d.getName().equals("stagger") && d.getTrigger() == true){
                return;
            }
        }
        target.getMomentum().permSetUp(0);
        target.setInitiative(false);
        /*
        space for additional stuff
        
        
        
        0.065*Math.sqrt(target.getArmorVal);
        */
        
        int damage = (int)(Math.random() * (getUser().getAtkVal().getMaxValue() - getUser().getAtkVal().getValue()) + getUser().getAtkVal().getValue() + 1);
        System.out.println(damage);
        double d = Math.random();
        if(d < getUser().getCritChance().getValue()){
            damage *= getUser().getCritDamage().getValue();
            critMsg(target);
        }
        damage = (int) (damage * 1 + getUser().getMomentum().getValue() * 0.1);
        damage = (int) (damage* (1 - 0.065*Math.sqrt(target.getDefVal().getValue())));
        /* more space for stuff
        
        
        
        */
        hitMsg(target, damage);
        if(target.getShield().getValue() > 0){
            int leftover = target.getShield().getValue();
            if(target.getShield().getValue() < damage)
                target.getShield().setValue(0);
            else
                target.getShield().subtract(damage);
            if(damage - leftover > 0){
                damage = damage - leftover;
            }
            else
                damage = 0;
        }
        target.getHealth().subtract(damage);
        target.setFlinch(target.getFlinch() + damage/4);
        if(target.getFlinch() > target.getRes().getValue()*2){
            target.setFlinch(0);
            getMainField().append(target.getName() + " has been staggered!");
            for(Debuff det : target.getDebuffs()){
                if(det.getName().equals("stagger"))
                    det.trigger(0, target, 999);
            }
        }
        /*
        spaaaaaaaaaaaace!
        
        
        */
        
        getUser().getMomentum().permAdd(1);
        getUser().setInitiative(false);
    }
    
    public void critMsg(Actor target){
        getMainField().append("Critical hit on " + target.getName() + "\n");
    }
  
    public void hitMsg(Actor target, int damage){
        getMainField().append(getUser().getName() + " smacks " + target.getName() + " for " + damage + " points of damage." + "\n");
    }
    
    public void atkMsg(Actor target){
        getMainField().append(getUser().getName() + " attempts to smack " + target.getName() + "!" + "\n");
    }
    
    public ArrayList <Actor> getTargets(){
        return targets;
    }
    
    public void setTargets(ArrayList <Actor> a){
        targets = a;
    }
}
