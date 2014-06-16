import java.lang.*;
import java.util.*;
import javax.swing.JTextArea;

public class Skill {
    private Skill opposingSkill;
    private Actor user;
    private String name;
    private JTextArea mainField;
    
    public Skill(Actor a){
        a.getKnownSkills().add(this);
        user = a;
        name = "Generic Skill 1012";
        mainField = user.getLocation().getGameBoard().getMainGame().getTextArea();
    }
    
    public Skill (String s){
        
        
    }
    
    public JTextArea getMainField(){
        return mainField;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String s){
        name = s;
    }
    
    public void setOpposingSkill(Skill s){
        opposingSkill = s;
    }
    
    public Skill getOpposingSkill(){
        return opposingSkill;
    }
    
    public void ignition(){
        setName("Ignited Skill 1012");
        user.setActingSkill(this);
    }
    
    public Actor getUser(){
        return user;
    }
    
    public void activate(ArrayList <Actor> a){
        
    }
}
