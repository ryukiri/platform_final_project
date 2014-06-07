/**
 * Contains all the necessary info for actors and players.
 *
 */
import java.util.*;
public class Actor {
    private String actorName;
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
    private Stat atkVal;
    private Stat defVal;
    private Stat weaponVal;

    //the following stats do not show up
    private Stat armorVal;
    private Stat critChance;
    private Stat critDamage;
    private Stat critMod;
    private Stat critModDmg;

    //other variables
    private Room location;
    private ArrayList <Item> contents;
    private ArrayList <Gear> equipList;

    public Actor(){
        initStats();
    }

    public Actor(String newName){
        actorName = newName;
        initStats();
    }

    public String getName(){
        return actorName;
    }

    public void initStats(){
        health = new Stat("Health", 50);
        stamina = new Stat("Stamina", 25);
        element = new Stat("Element", 25);
        shield = new Stat("Shield", 0);
        xp = new Stat("Experience", 0, 0, "xp");
        fin = new Stat("Finesse", 0, "skill");
        agil = new Stat("Agility",0, "skill");
        str = new Stat("Strength",0, "skill");
        cons = new Stat("Constitution",0, "skill");
        res = new Stat("Resistance",0, "skill");
        apt = new Stat("Aptitude",0, "skill");
        atkVal = new Stat("Attack Value", 0, 0, "range");
        defVal = new Stat("Defense Value",0,"skill");
        weaponVal = new Stat("Weapon Value", 0, 0, "range");
        contents = new ArrayList <Item> ();
        equipList = new ArrayList <Gear> ();
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

    public String weaponVal(){
        return "Weapon Value: " + weaponVal.getDisplay();
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
    }
    
    public void unequip(Gear g){
        equipList.remove(g);
        location.getGameBoard().getMainGame().getTextArea().append("You have unequipped " + g.getName() + "\n");
    }

    public void lowerHP(int x){
        health.subtract(x);
    }

    public void increaseHP(int x){
        health.add(x);
    }

    public Stat getHealth(){
        return health;
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

    public Stat getWeaponVal(){
        return weaponVal;
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

    public void setHealth(Stat x){
        health = x;
    }

    public void setStamina(Stat x){
        stamina = x;
    }

    public void setElement(Stat x){
        element = x;
    }

    public void setShield(Stat x){
        shield = x;
    }

    public void setXp(Stat x){
        xp = x;
    }

    public void setFin(Stat x){
        fin = x;
    }

    public void setAgil(Stat x){
        agil = x;
    }

    public void setStr(Stat x){
        str = x;
    }

    public void setCons(Stat x){
        cons = x;
    }

    public void setRes(Stat x){
        res = x;
    }

    public void setApt(Stat x){
        apt = x;
    }

    public void setAtkVal(Stat x){
        atkVal = x;
    }

    public void setDefVal(Stat x){
        defVal = x;
    }

    public void setWeaponVal(Stat x){
        weaponVal = x;
    }

    public void setArmorVal(Stat x){
        armorVal = x;
    }

    public void setCritChance(Stat x){
        critChance = x;
    }

    public void setCritDamage(Stat x){
        critDamage = x;
    }

    public void setCritMod(Stat x){
        critMod = x;
    }

    public void setCritModDmg(Stat x){
        critModDmg = x;
    }
}
