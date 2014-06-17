import java.util.*;
import javax.swing.*;
public class Actor{
    private String actorName;
    private Stat level;
    private Stat health;
    private Stat stamina;
    private Stat element;
    private Stat shield;
    private Stat xp;
    private Stat fin;
    private Stat agil;
    private Stat str;
    private Stat cons;
    private Stat res;
    private Stat apt;
    private Stat sp;
    private Stat atkVal;
    private Stat defVal;

    //the following stats do not show up
    private Stat armorVal;
    private Stat critChance;
    private Stat critDamage;
    private Stat critMod;
    private Stat critModDmg;
    private Stat momentum;
    private Stat baseSp;

    //other variables
    private ArrayList <Item> contents;
    private ArrayList <Gear> equipList;
    private ArrayList <Skill> knownSkills;
    private ArrayList <Actor> targetedBy;
    private ArrayList <Actor> opponents;
    private Boolean client;
    private Boolean initiative;
    private Boolean freeze = false;
    private MainGame overLord;
    private Room location;
    private String allies;
    private Skill actingSkill;

    public Actor(String newName){
        actorName = newName;
        initStats();
        update();
        client = false;
    }

    public Actor(String newName, MainGame q){
        actorName = newName;
        initStats();
        update();
        overLord = q;
        allies = "player";
        client = true;
    }

    public Actor(){

    }

    public String getName(){
        return actorName;
    }

    public void setActorName(String name){
        actorName = name;
    }

    public void setLevel(Stat l){
        level = l;
    }

    public void setHealth(Stat h){
        health = h;
    }

    public void setAtkVal(Stat a){
        atkVal = a;
    }

    public void setDefVal(Stat d){
        defVal = d;
    }

    public void setSp(Stat s){
        sp = s;
    }

    public void initStats(){
        level = new Stat("Level", 1, "skill", this);
        health = new Stat("Health", 50, this);
        stamina = new Stat("Stamina", 25, this);
        element = new Stat("Element", 25, this);
        shield = new Stat("Shield", 0, this);
        xp = new Stat("Experience", 0, 0, "xp", this);
        fin = new Stat("Finesse", 0, "skill", this);
        agil = new Stat("Agility",0, "skill", this);
        str = new Stat("Strength", 4, "skill", this);
        cons = new Stat("Constitution",0, "skill", this);
        res = new Stat("Resistance",0, "skill", this);
        apt = new Stat("Aptitude",0, "skill", this);
        atkVal = new Stat("Attack Value", 0, 0, "range", this);
        defVal = new Stat("Defense Value",0,"skill", this);
        sp = new Stat("Speed", 0, "skill", this);
        baseSp = new Stat("Base Speed", 0, "skill", this);
        momentum = new Stat("Momentum", 0, "skill", this);
        contents = new ArrayList <Item> ();
        equipList = new ArrayList <Gear> ();
        knownSkills = new ArrayList <Skill> ();
        getHealth().permSet(50 + getCons().getValue()*4 + getLevel().getValue() * 5);
        getStamina().permSet(getStr().getValue()*2 + 25);
        opponents = new ArrayList <Actor> ();
    }
    
    Room getLocation(){
        return location;
    }
    
    public void setLocation(Room l){
        location = l;
    }

    public String health(){
        return "Health: " + health.getDisplay();
    }

    public String stamina(){
        return "Stamina: " + stamina.getDisplay();
    }

    public String element(){
        return "Element: " + element.getDisplay();
    }

    public String shield(){
        return "Shield: " + shield.getDisplay();
    }

    public String xp(){
        return "Experience: " + xp.getDisplay();
    }

    public String fin(){
        return "Finesse: " + fin.getDisplay();
    }

    public String agil(){
        return "Agility: " + agil.getDisplay();
    }

    public String str(){
        return "Strength: " + str.getDisplay();
    }

    public String cons(){
        return "Constitution: " + cons.getDisplay();
    }

    public String res(){
        return "Resistance: " + res.getDisplay();
    }

    public String apt(){
        return "Aptitude: " + apt.getDisplay();
    }

    public String atkVal(){
        return "Attack Value: " + atkVal.getDisplay();
    }

    public String defVal(){
        return "Defense Value: " + defVal.getDisplay();
    }
    
    public String level(){
        return "Level: " + level.getDisplay();
    }
    
    public String sp() {
        return "Speed: " + sp.getDisplay();
    }
    
    public ArrayList <Item> getContents(){
        return contents;
    }
    
    public ArrayList <Gear> getEquipList(){
        return equipList;
    }
    
    public void equip(Gear g){
        if(equipList == null){
            System.out.println("Failed to find equip list.");
            return;
        }
        for(Gear o : equipList){
            if(o.getSlot().equals(g.getSlot()))
                unequip(o);
        }
        equipList.add(g);
        location.getGameBoard().getMainGame().getTextArea().append("You have equipped " + g.getName() + "\n");
        g.equipEffect(this);
    }
    
    public void unequip(Gear g){
        equipList.remove(g);
        location.getGameBoard().getMainGame().getTextArea().append("You have unequipped " + g.getName() + "\n");
        g.unequipEffect(this);
    }
    
    public Stat getHealth(){
        return health;
    }
    
    public Stat getLevel(){
        return level;
    }
    
    public Stat getMomentum(){
        return momentum;
    }
    
    public Stat getBaseSp(){
        return baseSp;
    }
    
    public Stat getSp(){
        return sp;
    }
    
    public Stat getStamina(){
        return stamina;
    }

    public Stat getElement(){
        return element;
    }

    public Stat getShield(){
        return shield;
    }

    public Stat getXp(){
        return xp;
    }

    public Stat getFin(){
        return fin;
    }

    public Stat getAgil(){
        return agil;
    }

    public Stat getStr(){
        return str;
    }

    public Stat getCons(){
        return cons;
    }

    public Stat getRes(){
        return res;
    }

    public Stat getApt(){
        return apt;
    }

    public Stat getAtkVal(){
        return atkVal;
    }

    public Stat getDefVal(){
        return defVal;
    }

    public Stat getArmorVal(){
        return armorVal;
    }

    public Stat getCritChance(){
        return critChance;
    }

    public Stat getCritDamage(){
        return critDamage;
    }

    public Stat getCritMod(){
        return critMod;
    }

    public Stat getCritModDmg(){
        return critModDmg;
    }

    public void update(){
        getSp().permSet(getMomentum().getValue()*4 + getBaseSp().getValue());
        if(overLord instanceof MainGame)
            overLord.update();
    }
    
    public MainGame getMainGame(){
        return overLord;
    }
    
    public void setMainGame(MainGame q){
        overLord = q;
    }
    
    public boolean isFrozen(){
        if(freeze == true){
            return true;
        }
        return false;
    }
    
    public void setFrozen(){
        if(freeze == true){
            freeze = false;
        }
        else
            freeze = true;
    }
    
    public String getAllies(){
        return allies;
    }
    
    public void move(Room a, Room b){
        if(a instanceof Room){
            a.getActorList().remove(this);
            if(overLord instanceof MainGame)
                overLord.getTextArea().append("Moving from " + a.getName());
        }
        b.getActorList().add(this);
        setLocation(b);
        if(overLord instanceof MainGame)
            overLord.getTextArea().append(" to " + b.getName() + "." + "\n");
        int x = 0;
        for(Actor A : b.getActorList()){
            if(A.getAllies() == null || !A.getAllies().equals("player")){
                x++;
            }           
        }
        if(x != 0 ){
            BattleAdmin battle = new BattleAdmin(b);
        }
    }
    
    public void move(Room a){
        a.getActorList().add(this);
        setLocation(a);
        if(overLord instanceof MainGame)
            overLord.getTextArea().append("Moved to " + a.getName() + "." + "\n");
    }
    
    public void setSpeed(int x){ //for testing purposes
        sp.permSet(x);
    }
    
    public ArrayList <Skill> getKnownSkills(){
        return knownSkills;
    }
    
    public void setActingSkill(Skill s){
        actingSkill = s;
        System.out.println(actingSkill.getName());
    }
    
    public void primal(){
        if(client == true){
            String num = JOptionPane.showInputDialog("Which skill? (number)");
            if(num == null){
                primal();
                return;
            }
            if(num.equals("")){
                primal();
                return;
            }
            try{
                int convertedNum = Integer.parseInt(num);
                if(convertedNum >= knownSkills.size()){
                    overLord.getTextArea().append("That skill does not exist." + "\n");
                    primal();
                    return;
                }
                if(knownSkills.get(convertedNum) instanceof Skill){
                    knownSkills.get(convertedNum).ignition();
                }
            }
            catch(NumberFormatException NRE){
                primal();
                return;
            }
           }
        else{
            knownSkills.get(0).ignition();
        }
    }

    public Skill getActingSkill(){
        return actingSkill;
    }
    
    public ArrayList <Actor> getTargetedBy(){
        return targetedBy;
    }
    
    public ArrayList <Actor> singleTarget(ArrayList <Actor> A){
        ArrayList <Actor> resultList = new ArrayList <Actor> ();
        ArrayList <Actor> enemyTeam = A;
        if(client == true){
            JTextArea textAreaLocal = overLord.getTextArea();
            textAreaLocal.append("The following targets are numbered off: ");
            for(Actor target : enemyTeam){
                textAreaLocal.append(enemyTeam.lastIndexOf(target) + ". " + target.getName() + "\n");
            }
            String reqNum = JOptionPane.showInputDialog("Input target number: ");
            if(reqNum.equals("") || reqNum == null){
                return null;
            }
            try{
                int convertedNum = Integer.parseInt(reqNum);
                if(convertedNum >= enemyTeam.size()){
                    textAreaLocal.append("That opponent does not exist.");
                    return null;
                }
                else{
                    resultList.add(enemyTeam.get(convertedNum));
                }
            }
            catch(NumberFormatException NRE){
                textAreaLocal.append("Please enter numbers and only numbers.");
                return null;
            }
        }else{
            Actor spec = null;
            for(Actor target : enemyTeam){
                spec = target;
            }
            resultList.add(spec);
        }
        return resultList; 
    }
    
    public ArrayList <Actor> getOpposingTeam(){
        return opponents;
    }
    
    public void setOpposingTeam(ArrayList <Actor> a){
        opponents = a;
    }
    
    public Boolean getClient(){
        return client;
    }
    
    public MainGame getOverLord(){
        return overLord;
    }
    
    public Boolean hasInitiative(){
        return initiative;
    }
    
    public void setInitiative(Boolean r){
        initiative = r;
    }
}
