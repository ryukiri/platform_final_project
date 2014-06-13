
import java.util.*;
import javax.swing.JTextArea;

public class Offense extends Skill {
    private ArrayList <Actor> targets;
    private JTextArea mainField;
    private String atkMsg;
    private String hitMsg;
    
    public Offense(Actor a){
        super(a);
        mainField = getUser().getLocation().getGameBoard().getMainGame().getTextArea();
    }
    
    @Override
    public void activate(ArrayList <Actor> a){
        System.out.println("activated");
        targets = getUser().singleTarget(a);
        if(targets == null){
            activate(a);
            return;
        }
        
        for(Actor target : targets){
            setOpposingSkill(target.getActingSkill());
            mainField.append(atkMsg);
            if(target.getActingSkill() instanceof Offense){
                vsOffense();
            }
            if(target.getActingSkill() instanceof Defense){
                vsDefense();
            }
        }
    }
    
    public void vsOffense(){
        Offense first;
        Offense second = this;
        Offense opposingSkill = (Offense) getOpposingSkill();
        first = opposingSkill;
        if(getUser().hasInitiative()){
            mainField.append("Initiative Breaker!" + "\n");
            first = this;
            second = opposingSkill;
            if(opposingSkill.getUser().hasInitiative()){
                mainField.append("Initiative Counterbreaker!!!" + "\n");
                first = opposingSkill; 
                second = this;
            }
        }
        first.damageScene(second.getUser());
        second.damageScene(first.getUser());            
    }
    
    public void vsDefense(){
                
    }
    
    public JTextArea getMainField(){
        return mainField;
    }
    
    public void damageScene(Actor target){
        //find a way to implement critical chance. incomplete! does not function!
        mainField.append(hitMsg);
        int damage = (int)(Math.random() * getUser().getAtkVal().getValue() + getUser().getAtkVal().getMaxValue() + 1);
        if(target.getShield().getValue() < 0){
            target.getShield().setValue(0);
        }
        if(target.getShield().getValue() < damage){
            damage -= target.getShield().getValue();
        }
    }
    
}
