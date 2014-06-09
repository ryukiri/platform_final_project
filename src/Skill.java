/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DuncanSkyrim
 */

import java.lang.*;
import java.util.*;

public class Skill {
    private Skill opposingSkill;
    private Actor user;
    private String name;
    
    public Skill(Actor a){
        a.getKnownSkills().add(this);
        user = a;
        name = "Generic Skill 1012";
    }
    
    public Skill (String s){
        
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
    
    public void ignition(){
        setName("Ignited Skill 1012");
        user.setActingSkill(this);
        System.out.println(user.getName() + "attacks!");
    }
}
    
    
