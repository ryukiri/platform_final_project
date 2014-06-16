
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
public class SingleTarget extends Offense {
    
    public SingleTarget(Actor a){
        super(a);
    }
    
    public void activate(ArrayList <Actor> a){
        System.out.println("activated");
        setTargets(getUser().singleTarget(a));
        ArrayList <Actor> targets = getTargets();
        if(targets == null){
            activate(a);
            return;
        }
        for(Actor target : targets){
            setOpposingSkill(target.getActingSkill());
            atkMsg(target);
            if(target.getActingSkill() instanceof Offense){
                vsOffense();
                return;
            }
            if(target.getActingSkill() instanceof Defense){
                vsDefense();
                return;
            }
            target.addAttacker(getUser());
        }
        if(this instanceof Basic){
            Basic selfObject = (Basic) this;
            selfObject.setTriangleState();
        }
        
    }
    
    public void damageScene(Actor a){
        super.damageScene(a);
        getUser().setActingSkill(null);
    }
    
}
