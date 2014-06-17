import java.util.*;
public class BattleAdmin {
    private ArrayList <Actor> teamA;
    private ArrayList <Actor> teamB;
    private Room location;
    
    public BattleAdmin(Room r){
        teamA = new ArrayList <Actor>();
        teamB = new ArrayList <Actor>();
        location = r;
        for(Actor a : location.getActorList()){
            Offense x = new Offense(a);
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
        turnActive();
    }
    
    public void turnActive(){
        ArrayList <Actor> orderedList = rollCall();
        for(Actor a : orderedList){
            System.out.println(a.getName() + orderedList.lastIndexOf(a));
            a.primal();
            a.getActingSkill().activate(a.getOpposingTeam());
        }
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
