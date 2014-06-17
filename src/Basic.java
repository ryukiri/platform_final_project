
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
import javax.swing.*;
public class Basic extends Offense {
    private String triangle;
    
    public Basic(Actor a){
        super(a);
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
   
   public void vsOffense(){
        Offense opposingSkill = (Offense) getOpposingSkill();
        if(opposingSkill instanceof Basic){
            Basic opposingBasic = (Basic) opposingSkill;
            setTriangleResp();
        }
   }
    
}
