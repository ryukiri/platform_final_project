
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 15ShepherdDuncan
 */
public class Basic extends SingleTarget{
    private String triangle;
    
    public Basic(Actor a){
        super(a);
    }
    
    public String getTriangle(){
        return triangle;
    }
    
    public void activate(ArrayList <Actor> a){
        super.activate(a);
                
    }
    
    public void setTriangleResp(){
        if(getUser().getClient() == true){
            JFrame frame = getUser().getOverLord().getJFrame();           
            Object[] options = {"True",
            "Counter",};
            int n = JOptionPane.showOptionDialog(
            frame,
            "Basic attack configuration",
            "True or counter?",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
            
            if(n == 0){
                triangle = "True";
            }
            if(n == 1){
                triangle = "Counter";
            }
        }
        else
            //be sure to update this before release
            triangle = "True";
        
   }
   public void setTriangleState(){
        if(getUser().getClient() == true){
            JFrame frame = getUser().getOverLord().getJFrame();           
            Object[] options = {"True",
            "Feint",};
            int n = JOptionPane.showOptionDialog(
            frame,
            "Basic attack configuration",
            "True or counter?",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
            
            if(n == 0){
                triangle = "True";
            }
            if(n == 1){
                triangle = "Feint";
            }
        }
        else
            //be sure to update this before release
            triangle = "True";
       
   }
   
    @Override
   public void vsOffense(){
        Offense opposingSkill = (Offense) getOpposingSkill();
        Offense first = opposingSkill;
        Offense second = this;
        if(getUser().hasInitiative()){
            super.vsOffense();
            return;
        }
        if(opposingSkill instanceof Basic){
            Basic opposingBasic = (Basic) opposingSkill;
            first = opposingBasic;
            setTriangleResp();
            if(opposingBasic == null) {
                System.out.println("opposing Skill is missing");
                return;
            }
            if(opposingBasic.getTriangle() == null){
                System.out.println("opposing triangle is missing");
                return;
            }
            if(this.getTriangle() == null){
                System.out.println("triangle is missing.");
                return;
            }
            if(opposingBasic.getTriangle().equals("True") && triangle.equals("Counter")){
                first = this;
                second = opposingBasic;
                getMainField().append(getUser().getName() + "'s counters " + getOpposingSkill().getUser().getName() +"'s attack! ");
            }
            if(opposingBasic.getTriangle().equals("Feint")){
                if(triangle.equals("True")){
                    first = this;
                    second = opposingBasic;
                    getMainField().append(getUser().getName() + " overwhelms " + getOpposingSkill().getUser().getName() +"'s feint with a true attack! ");
                }
                if(triangle.equals("Counter")){
                    getMainField().append(getUser().getName() + "'s counter fails against " + getOpposingSkill().getUser().getName() +"'s feint! ");
                }
            }
            first.damageScene(second.getUser());
            second.damageScene(first.getUser());
        }
        else{
            super.vsOffense();
        }
   }
   
   public void vsDefense(){
       setTriangleState();
       super.vsDefense();
   }
    
}
