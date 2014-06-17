import java.util.*;
import javax.swing.JTextArea;
public class BattleAdmin {
    private ArrayList <Actor> teamA;
    private ArrayList <Actor> teamB;
    private Room location;
    private JTextArea mainField;
    
    public BattleAdmin(Room r, JTextArea J){
        teamA = new ArrayList <Actor>();
        teamB = new ArrayList <Actor>();
        location = r;
        mainField = J;
        for(Actor a : location.getActorList()) {
            for (Gear g : a.getEquipList()) {
                if (g instanceof Sword) {
                    SwordSlash slash = new SwordSlash(a);
                }
            }

            Basic x = new Basic(a);
            Dodge d = new Dodge(a);
            a.setFlinch(0);
            a.setSpacing(0);
            for(Debuff det : a.getDebuffs())
                det.extinguish();
            a.getMomentum().permSetUp(0);
            if(a.isFrozen() == false)
                a.setFrozen();
            if(a.getAllies() == null || !a.getAllies().equals("player")){
                teamA.add(a);
            }
            else
                teamB.add(a);            
        }
        for(Actor a: teamA){
            a.setOpposingTeam(teamB);
        }
        for(Actor a : teamB){
            a.setOpposingTeam(teamA);
        }
        turnActive(0);
    }
    
    public void turnActive(int recursionVal){
        recursionVal++;
        mainField.append("Round " + recursionVal + "\n");
        ArrayList <Actor> orderedList = rollCall();
        for(Actor a: orderedList){
            mainField.append(a.getName() + " is standing on space " + a.getSpacing() + " with " + a.getHealth().getDisplay() + " Health and " + a.getStamina().getDisplay() + " stamina." + a.getSp().getDisplay() + "speed" + "\n" );
        }
        for(Actor a : orderedList){
            System.out.println(a.getName() + orderedList.lastIndexOf(a));
            a.primal();
            if(a.getActingSkill() != null){
                a.getActingSkill().activate(a.getOpposingTeam());
            }

        }
        for(Actor a : orderedList){
            ArrayList <Actor> newList = new ArrayList <Actor> ();
            a.setTargetedBy(newList);
            Skill s = a.getActingSkill();
            System.out.println("Checked in");
            if(s instanceof Offense){
                System.out.println("Checked out");
                Offense o = (Offense) a.getActingSkill();
                for(Actor target : o.getTargets())
                    o.damageScene(target);
            }
            for(Debuff det : a.getDebuffs()){
                if(det.getName().equals("stagger")){
                    det.extinguish();
                }
                det.turnPass();
            }
        }
        turnActive(recursionVal);
    }
    
    public ArrayList <Actor> rollCall(){ //insertion sort! rah! the complexity!
       ArrayList <Actor> someList = new ArrayList <Actor> ();
       int switchCount = location.getActorList().size();
       ArrayList <Actor> counterList  = location.getActorList();
       for(int i = 0; i < switchCount; i++){
           System.out.println(i + " i");
           if(i == 0){
               someList.add(counterList.get(i));
           }
           else{
               Actor comparison = counterList.get(i);
               int snippet = 0;
               for(int o = 0; o < i; o++){
                   System.out.println(o + " o");
                   if(comparison.getSp().getValue() < someList.get(o).getSp().getValue()){
                       snippet++;
                   }                   
               }
               someList.add(snippet, comparison);
           }               
       }
       return someList;
    }
}
