/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 * Created by austin on 6/5/14.
 */
public class MedKit extends Item {
    private int value;
    
    public MedKit (Actor a, int toRestore){
        super(a);
        value = toRestore;
    }

    public MedKit (Room r, int toRestore){
        super(r);
        value = toRestore;
    }
    
    public void use(){
        Actor a = getHolder();
        a.getHealth().add(value);
        a.getLocation().getGameBoard().getMainGame().getTextArea().append("You regained " + value + " points of health." + "\n");
        remove();
    }
}
