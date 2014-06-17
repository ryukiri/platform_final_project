
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DuncanSkyrim
 */
public class Parry extends Defense {
    
    public Parry(Actor a){
        super(a);
    }
    //min(100,max(15,0.15*sqrt(x) * 100))
    
     public void activate(ArrayList <Actor> a){
         for(Gear g : getUser().getEquipList()){
             if(g instanceof Sword){
                 super.activate(a);
                 return;
             }
         }
         getMainField().append("This is the incorrect weapon to use! \n.");
     }
    
    @Override
    public void vsOffense(Offense opposingSkill){
        for(Debuff d : getUser().getDebuffs()){
            if(d.getName().equals("stagger") && d.getTrigger() == true){
                return;
            }
        }
        if(opposingSkill instanceof Basic){
            Basic opposingBasic = (Basic) opposingSkill;
            if(opposingBasic.getTriangle().equals("Feint")){
                getMainField().append(opposingBasic.getUser().getName() + " feints out" + this.getUser().getName() + "'s parry." + "\n");
                opposingBasic.getUser().setActingSkill(null);
                opposingBasic.damageScene(getUser());
                return;
            }
        }
        double d = Math.random();
        double chance = 0.15*Math.sqrt(getUser().getFin().getValue()*2 - opposingSkill.getUser().getFin().getValue());
        if(chance < 0.15){
            chance = 0.15;
        }
        if(chance > 1){
            chance = 1;
        }
        System.out.println(chance + " chance to parry.");
        if(d < chance){
            getMainField().append(getUser().getName() + " successfully parries " + opposingSkill.getUser().getName() + "'s attack with a swift movement." + "\n");
            
        }else{
            getMainField().append(getUser().getName() + " fails to parry " + opposingSkill.getUser().getName() + "'s attack." + "\n");
            opposingSkill.damageScene(getUser());
        }
        if(opposingSkill instanceof SingleTarget){
            opposingSkill.getUser().setActingSkill(null);
        }
    }
}
