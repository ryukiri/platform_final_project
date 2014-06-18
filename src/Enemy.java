import java.util.ArrayList;

/**
 * Created by austin on 6/17/14.
 */
public class Enemy extends Actor {
    private Actor player;
    private static GameBoard gameBoard;
    private Room location;
    private String name;
    private int attack;
    private int speed;
    private int defense;

    public Enemy() {

    }

    public Enemy(int atk, int def, int sp, Room a) {
        location = a;
        attack = atk;
        defense = def;
        speed = sp;
        initStats();
        setLocation(a);
        a.getContentEnemies().add(this);
    }

    public Enemy(int lv, int atk, int def, int sp, Room a) {
        location = a;
        getRanNumAtk(atk, lv);
        getRanNumDef(def, lv);
        getRanNumSpeed(sp, lv);
        initStats();
        setLocation(a);
        a.getContentEnemies().add(this);
    }

    public void setName(String name) {
        setActorName(name);
    }

    private void getRanNumAtk(int atk, int lv){
        int y = (int)(Math.random()*5);
        int x = (atk+(lv*y)+y)/2;
        attack = x;
    }

    private void getRanNumDef(int def, int lv){
        int y = (int)(Math.random()*5);
        int x = (def+(lv*y)+y)/2;
        defense = x;
    }

    private void getRanNumSpeed(int sp, int lv){
        int y = (int)(Math.random()*5);
        int x = (sp+(lv*y)+y)/2;
        speed = x;
    }

    @Override
    public void initStats() {
        setLevel(new Stat("Level", 1, "skill", this));
        setHealth(new Stat("Health", 50 + (int) Math.ceil(10 * 1.25), this));
        setAtkVal(new Stat("Attack Value", attack, attack + (int) (Math.random() * 8) + 2, "range", this));
        setDefVal(new Stat("Defense Value", defense, "skill", this));
        setSp(new Stat("Speed", speed, "skill", this));
    }
}
