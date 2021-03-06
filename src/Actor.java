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
    private Room location;
    private ArrayList <Item> contents;
    private ArrayList <Gear> equipList;
    private Boolean initiative = false;
    private Boolean freeze = false;
    private MainGame overLord;
    private GameBoard land;
    private String allies;
    private ArrayList <Skill> knownSkills;
    private ArrayList <Actor> targetedBy;
    private ArrayList <Actor> opponents;
    private ArrayList <Debuff> debuffs;
    private Boolean client;
    private Skill actingSkill;
    private int spacing;
    private int flinchMeter;

    
    //debuffs
    private Debuff cripple;
    private Debuff stagger;
    private Debuff bleeding;
    private Debuff stamPoison;

    public Actor(String newName, Room r){
        location = r;
        actorName = newName;
        initStats();
        update();
        client = false;
    }

    public Actor(String newName, MainGame q, Room r){
        location = r;
        actorName = newName;
        initStats();
        update();
        overLord = q;
        allies = "player";
        client = true;
    }

    public String getName(){
        return actorName;
    }

    public void initStats(){
        level = new Stat("Level", 1, "skill", this);
        health = new Stat("Health", 50, this);
        stamina = new Stat("Stamina", 25, this);
        element = new Stat("Element", 25, this);
        shield = new Stat("Shield", 0, this);
        xp = new Stat("Experience", 0, 0, "xp", this);
        fin = new Stat("Finesse", 0, "skill", this);
        agil = new Stat("Agility",12, "skill", this);
        str = new Stat("Strength", 4, "skill", this);
        cons = new Stat("Constitution",0, "skill", this);
        res = new Stat("Resistance",0, "skill", this);
        apt = new Stat("Aptitude",0, "skill", this);
        atkVal = new Stat("Attack Value", 0, 0, "range", this);
        defVal = new Stat("Defense Value",0,"skill", this);
        sp = new Stat("Speed", 0, "skill", this);
        baseSp = new Stat("Base Speed", 0, "skill", this);
        momentum = new Stat("Momentum", 0, "skill", this);
        critChance = new Stat("Critical Chance", 0, "skill", this);
        critDamage = new Stat("Critical Damage", 0, "skill", this);
        contents = new ArrayList <Item> ();
        equipList = new ArrayList <Gear> ();
        knownSkills = new ArrayList <Skill> ();
        getHealth().permSet(50 + getCons().getValue()*4 + getLevel().getValue() * 5);
        getStamina().permSet(getStr().getValue()*2 + 25);
        opponents = new ArrayList <Actor> ();
        targetedBy = new ArrayList <Actor> ();
        debuffs = new ArrayList <Debuff> ();
        
        //public Debuff(String s, Actor a, Stat st, Boolean curseOrNot){
        cripple = new Debuff("cripple", this, baseSp, true);
        bleeding = new Debuff("bleeding", this, health, false);
        stagger = new Debuff("stagger", this, baseSp, true);
        stamPoison = new Debuff("poison", this, stamina, false);
        
        debuffs.add(cripple);
        debuffs.add(bleeding);
        debuffs.add(stagger);
        debuffs.add(stamPoison);      
        
    }
    
    public ArrayList <Actor> getTargetedBy(){
        return targetedBy;
    }
    
    public void addAttacker(Actor a){
        targetedBy.add(a);
    }
    
    public void setTargetedBy(ArrayList <Actor> a){
        targetedBy = a;
    }
    
    public Room getLocation(){
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
            BattleAdmin battle = new BattleAdmin(b, overLord.getTextArea());
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
        System.out.println("setup");
    }
    
    public void primal(){
        if(stagger.getTrigger() == true){
            return;
        }
        if(client == true){

            JFrame frame = getOverLord().getJFrame();
            Object[] options = {"Attack",
                    "Move",
                    "Do Nothing"};
            int n = JOptionPane.showOptionDialog(
                    frame,
                    "Attack or Move?",
                    "Attack",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if(n == 0){
                overLord.getTextArea().append("Skills available:" + "\n");
                int x = 0;
                for(Skill i: knownSkills){
                    overLord.getTextArea().append(x + ". ");
                    overLord.getTextArea().append(i.getName() + "\n");
                }
                overLord.getTextArea().append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + "\n");
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
            if(n == 2){
                return;
            }
            if(n == 1) {
                //Move button
                Object[] moreOptions = {"North",
                        "South"};
                int choice = JOptionPane.showOptionDialog(
                        frame,
                        "Where would you like to move?",
                        "Move",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        moreOptions,
                        moreOptions[0]);

                //North Button
                if (choice == 0) {
                    overLord.getTextArea().append(actorName + " attemps to move north.");

                    for(Actor a: targetedBy){
                        Offense target = (Offense)a.getActingSkill();
                        target.damageScene(this);
                        overLord.getTextArea().append(actorName + " moves 1 space north.");
                    }

                    for(Debuff d: debuffs){
                        if(d.getName().equals("stagger")){
                            return;
                        }
                    }
                    spacing++;

                //South Button
                }else if (choice == 1) {
                    overLord.getTextArea().append(actorName + " attemps to move south.");

                    for(Actor a: targetedBy){
                        Offense target = (Offense)a.getActingSkill();
                        target.damageScene(this);
                        overLord.getTextArea().append(actorName + " moves 1 space south.");
                    }

                    for(Debuff d: debuffs){
                        if(d.getName().equals("stagger")){
                            return;
                        }
                    }
                    spacing--;
                }
            }
        }
        else{
            knownSkills.get(0).ignition();
        }
    }

    
    public Skill getActingSkill(){
        return actingSkill;
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
    
    public int getSpacing(){
        return spacing;
    }
    
    public void setSpacing(int n){
        spacing = n;
    }
    
    public ArrayList <Debuff> getDebuffs(){
        return debuffs;        
    }
    
    public int getFlinch(){
        return flinchMeter;
    }
    
    public void setFlinch(int n){
        flinchMeter = n;
        
    }
}
