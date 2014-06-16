/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DuncanSkyrim
 */
public class Dodge extends Defense {
    
    public Dodge(Actor a){
        super(a);
    }
    //min(100,max(15,0.15*sqrt(x) * 100))
    
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
                getMainField().append(opposingBasic.getUser().getName() + " feint fails against " + this.getUser().getName() + "'s dodge." + "\n");
                opposingBasic.getUser().setActingSkill(null);
                getUser().setInitiative(true);
                return;
            }
        }
        double d = Math.random();
        double chance = 0.15*Math.sqrt(getUser().getAgil().getValue()*2 - opposingSkill.getUser().getFin().getValue());
        if(chance < 0.15){
            chance = 0.15;
        }
        if(chance > 1){
            chance = 1;
        }
        System.out.println(chance + " chance to dodge.");
        if(d < chance){
            getMainField().append(getUser().getName() + " successfully dodges " + opposingSkill.getUser().getName() + "'s attack with a swift movement." + "\n");
            getUser().setInitiative(true);
        }else{
            getMainField().append(getUser().getName() + " fails to dodge " + opposingSkill.getUser().getName() + "'s attack." + "\n");
            opposingSkill.damageScene(getUser());
        }
        if(opposingSkill instanceof SingleTarget){
            opposingSkill.getUser().setActingSkill(null);
        }
    }
}
