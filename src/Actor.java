/**
 * Contains all the necessary info for actors and players.
 *
 */
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
}
