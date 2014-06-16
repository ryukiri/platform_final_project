/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 15ShepherdDuncan
 */
import java.util.*;
import javax.swing.JTextArea;

public class Defense extends Skill{
    private ArrayList <Actor> targets;
    private JTextArea mainField;
    
    public Defense(Actor a){
        super(a);
        mainField = getUser().getLocation().getGameBoard().getMainGame().getTextArea();
    }
    
     public void activate(ArrayList <Actor> a){
         mainField.append(getUser().getName() + " takes a defensive stance." + "\n");
         ArrayList <Actor> removeList = getUser().getTargetedBy();
         for(Actor attacker : getUser().getTargetedBy()){
             vsOffense((Offense) attacker.getActingSkill());
         }
     }
     
     public void vsOffense(Offense opposingSkill){
     }
    
}
