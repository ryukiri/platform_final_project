import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.SocketPermission;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainGame extends JPanel implements ActionListener {
    private static ActionListener buttonListener;
    private static ActionListener menuListener;
    private static int temporary;
    private static JButton moveButton;
    private static JButton observeButton;
    private static JButton abortButton;
    private static JButton mapButton;
    private static JButton inventoryButton;
    private static JButton skillButton;
    private static JLabel playerName;
    private static JLabel playerLevel;
    private static JLabel playerHealth;
    private static JLabel playerStamina;
    private static JLabel playerElement;
    private static JLabel playerShield;
    private static JLabel playerXp;
    private static JLabel playerFin;
    private static JLabel playerAgil;
    private static JLabel playerStr;
    private static JLabel playerCons;
    private static JLabel playerRes;
    private static JLabel playerApt;
    private static JLabel playerSp;
    private static JLabel playerAtkVal;
    private static JLabel playerDefVal;
    private static JLabel space;
    private static JMenu help;
    private static JMenu options;
    private static JMenuBar menuBar;
    private static JMenuItem quit;
    private static JMenuItem gameHelp;
    private static JPanel buttonPanel;
    private static JPanel statPanel;
    private static JPanel textPanel;
    private static JPanel mainPanel;
    private static JTextArea textArea;
    private final static String newline = "\n";
    private final static JFrame frame = new JFrame("Server");
    private String line = "----------------------------------------------------------------------------------------------------------------------------------------------------------------";
    private static GameBoard mainBoard;
    private static MapGUI map;


    public MainGame() {
        super(new GridBagLayout());
        textArea = new JTextArea(40, 60);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);

        mainBoard = new GameBoard(this);
        playerName = new JLabel("Player Name: " +mainBoard.getPlayerName());
        space = new JLabel(" ");
        //Stats: xp,health,stamina,element,shield,fin,agil,str,cons,res,apt,atkVal,defVal
        playerLevel = new JLabel(mainBoard.getPlayer().level());
        playerHealth = new JLabel(mainBoard.getPlayer().health());
        playerStamina = new JLabel(mainBoard.getPlayer().stamina());
        playerElement = new JLabel(mainBoard.getPlayer().element());
        playerShield = new JLabel(mainBoard.getPlayer().shield());
        playerXp = new JLabel(mainBoard.getPlayer().xp());
        playerFin = new JLabel(mainBoard.getPlayer().fin());
        playerAgil = new JLabel(mainBoard.getPlayer().agil());
        playerStr = new JLabel(mainBoard.getPlayer().str());
        playerCons = new JLabel(mainBoard.getPlayer().cons());
        playerRes = new JLabel(mainBoard.getPlayer().res());
        playerApt = new JLabel(mainBoard.getPlayer().apt());
        playerSp = new JLabel(mainBoard.getPlayer().sp());
        playerAtkVal = new JLabel(mainBoard.getPlayer().atkVal());
        playerDefVal = new JLabel(mainBoard.getPlayer().defVal());
        textArea.append("Use the observe button to look around" + newline);
        
    }

    public MainGame(int x){

    }
    
    public JFrame getJFrame(){
        return frame;
    }
    
    public void update(){
        playerLevel.setText(mainBoard.getPlayer().level());
        playerHealth.setText(mainBoard.getPlayer().health());
        playerStamina.setText(mainBoard.getPlayer().stamina());
        playerElement.setText(mainBoard.getPlayer().element());
        playerShield.setText(mainBoard.getPlayer().shield());
        playerXp.setText(mainBoard.getPlayer().xp());
        playerFin.setText(mainBoard.getPlayer().fin());
        playerAgil.setText(mainBoard.getPlayer().agil());
        playerStr.setText(mainBoard.getPlayer().str());
        playerCons.setText(mainBoard.getPlayer().cons());
        playerRes.setText(mainBoard.getPlayer().res());
        playerApt.setText(mainBoard.getPlayer().apt());
        playerSp.setText(mainBoard.getPlayer().sp());
        playerAtkVal.setText(mainBoard.getPlayer().atkVal());
        playerDefVal.setText(mainBoard.getPlayer().defVal());
        frame.pack();
    }
    
    public JTextArea getTextArea(){
        return textArea;
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        Actor player = mainBoard.getPlayer();
        Room relativeLoc = player.getLocation();
        relativeLoc.explored();
        if(player.isFrozen() == true){
            return;
        }
        if(event.getSource() instanceof JButton) {

            if (source == moveButton) {
                /**Custom Button Text
                 * Source: http://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
                 * http://stackoverflow.com/questions/1257420/making-a-joptionpane-with-4-options
                 **/
                Object[] options = {"North",
                        "South",
                        "East",
                        "West",
                        "Attack"};
                int n = JOptionPane.showOptionDialog(
                        frame,
                        "Where would you like to move?",
                        "Move",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (n == 0) {
                    if(map != null)
                        map.revalid();
                    if (relativeLoc.getNorth() != null) {
                        player.move(relativeLoc, relativeLoc.getNorth());
                    }
                    if(player.getLocation().getContentEnemies().size() != 0){
                        fight();
                    }
                } else if (n == 1) {
                    if(map != null)
                        map.revalid();
                    if (relativeLoc.getSouth() != null) {
                        player.move(relativeLoc, relativeLoc.getSouth());
                    }
                    if(player.getLocation().getContentEnemies().size() != 0){
                        fight();
                    }
                } else if (n == 2) {
                    if(map != null)
                        map.revalid();
                    if (relativeLoc.getEast() != null) {
                        player.move(relativeLoc, relativeLoc.getEast());
                    }
                    if(player.getLocation().getContentEnemies().size() != 0){
                        fight();
                    }
                } else if (n == 3) {
                    if(map != null)
                        map.revalid();
                    if (relativeLoc.getWest() != null) {
                        player.move(relativeLoc, relativeLoc.getWest());
                    }
                    if(player.getLocation().getContentEnemies().size() != 0){
                        fight();
                    }
                } else if (n == JOptionPane.CLOSED_OPTION) {
                    textArea.append("Movement Canceled" + newline);
                }else if (n == 4){
                        fight();
                }
            } else if (source == observeButton) {
                textArea.append(relativeLoc.getDesc() + newline);
                textArea.append("Exits:" + relativeLoc.getExits() + newline);
                if(player.getLocation().getContents()!= null){
                    textArea.append("Enemies: " + newline);
                    int count = 0;
                    for(Actor a: player.getLocation().getContentEnemies()){
                        textArea.append(count + " ");
                        textArea.append(a.getName() + newline);
                        count++;
                    }

                    textArea.append("Items available to be picked up:" + newline);
                    int x = 0;
                    for(Item i: player.getLocation().getContents()){
                        textArea.append(x + " " );
                        textArea.append(i.getName() + newline);
                        x++;
                    }
                    textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                }
                else
                    System.out.println("Failed.");
            } else if (source == abortButton) {
                int n = JOptionPane.showConfirmDialog(frame, "Quit?");
                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else if (source == mapButton) {
                map = new MapGUI(mainBoard);
                map.mapFrame();
            } else if (source == skillButton){
                ArrayList <Skill> listingSkills = player.getKnownSkills();
                int iter = 0;
                textArea.append("Skills currently equipped: " + newline);
                for(Skill s : listingSkills){
                    textArea.append(iter + ". ");
                    textArea.append(s.getName() + newline);
                    iter++;
                }
                textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                if(iter == 0){
                    textArea.append("There are no skills currently activated." + newline);
                }
            } else if (source == inventoryButton){
                ArrayList <Item> items = player.getContents();
                int iter = 0;
                textArea.append("Items in your inventory:" + newline);
                for(Item i : items){
                    textArea.append(iter + ". ");
                    textArea.append(i.getName() + newline);
                    iter++;
                }
                if(iter == 0){
                    textArea.append("There are no items in your inventory." + newline);
                }

                ArrayList <Gear> equippedItems = player.getEquipList();
                int iterE = 0;
                textArea.append("Items equipped:" + newline);
                for(Gear i : equippedItems){
                    textArea.append(iterE + ". " + i.getSlot() + " ");
                    textArea.append(i.getName() + newline);
                    iterE++;
                }
                if(iterE == 0){
                    textArea.append("There are no items equipped." + newline);
                }
                textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                Object[] options = {"Equip",
                                    "Pick Up",
                                    "Unequip",
                                    "Use",
                                    "Destroy"};
                int n = JOptionPane.showOptionDialog(
                        frame,
                        "What would you like to do?",
                        "Options",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0) {
                    //Equip
                    String choice = JOptionPane.showInputDialog(frame, "Equip which item? (number)");
                    if( choice == null){
                        return;
                    }
                    if(choice.equals(""))
                        return;
                    try {
                        int convertedNum = Integer.parseInt(choice);
                        if (convertedNum >= player.getContents().size()) {
                            textArea.append("That item does not exist." + newline);
                            textArea.append(line);
                            return;
                        }
                        Item decided = player.getContents().get(convertedNum);
                        if (decided instanceof Gear) {
                            player.equip((Gear) decided);
                            player.getContents().remove(convertedNum);
                        } else {
                            textArea.append("This item is not equippable." + newline);
                            textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        }
                    }catch(NumberFormatException NRE){
                        textArea.append("Please enter a legit number!" + newline);
                        textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        return;
                    }catch(ConcurrentModificationException NRE){
                        textArea.append("You need to unequip current item before equiping another." + newline);
                        textArea.append(line);
                        return;
                    }

                }else if (n == 1) {
                    //Pick up
                    String choice = JOptionPane.showInputDialog(frame, "Which item? (number)");
                    if( choice == null){
                        return;
                    }
                    if(choice.equals(""))
                        return;
                    try{
                        int convertedNum = Integer.parseInt(choice);
                        if(convertedNum>= player.getLocation().getContents().size()){
                            textArea.append("That item does not exist." + newline);
                            textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        }
                        else{
                            textArea.append("You have picked up " + player.getLocation().getContents().get(convertedNum).getName() + newline);
                            player.getLocation().getContents().get(convertedNum).Move(player);
                            textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        }
                    }
                    catch(NumberFormatException NRE){
                        textArea.append("Please enter a legit number!" + newline);
                        textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        return;
                    }

                }else if(n == 2){
                    //Unequip button
                    String choice = JOptionPane.showInputDialog(frame, "Unequip which item? (number)");
                    if( choice == null){
                        return;
                    }
                    if(choice.equals(""))
                        return;
                    try {
                        int convertedNum = Integer.parseInt(choice);
                        Gear decided = player.getEquipList().get(convertedNum);
                        player.getContents().add(decided);
                        if (convertedNum >= player.getEquipList().size()) {
                            textArea.append("That item does not exist." + newline);
                            textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        } else if (decided instanceof Gear) {
                            player.unequip((Gear) decided);
                        }
                    }
                    catch(NumberFormatException NRE){
                        textArea.append("Please enter a legit number!" + newline);
                        textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        return;
                    }
                    catch(NullPointerException NRE){
                        textArea.append("Please enter a legit number!" + newline);
                        textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        return;
                    }catch(IndexOutOfBoundsException NRE){
                        textArea.append("You do not have that item equipped. Please enter a legit number!" + newline);
                        textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        return;
                    }
                }else if(n == 3){
                    //Use Button
                    String num = JOptionPane.showInputDialog(frame, "Which item? (number)");
                    if(num == null){
                        return;
                    }
                    if(num.equals(""))
                        return;
                    try {
                        int convertedNum = Integer.parseInt(num);
                        if (player.getContents().get(convertedNum) instanceof Item) {
                            player.getContents().get(convertedNum).use();
                            if(player.getHealth().getValue()<=0){
                                player.getHealth().setValue(0);
                                shutdown();
                            }
                        } else {
                            textArea.append("That item does not exist." + newline);
                            textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);

                        }
                    }
                    catch(NumberFormatException NRE){
                        textArea.append("Please enter a legit number!" +  newline);
                        textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        return;
                    }
                    catch(NullPointerException NRE){
                        textArea.append("Please enter a legit number!" + newline);
                        textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        return;
                    }catch(IndexOutOfBoundsException NRE){
                        textArea.append("You do not have that item. Please enter a legit number!" + newline);
                        textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                        return;
                    }
                }else if(n == 4){
                    //Destroy Button
                    String num = JOptionPane.showInputDialog(frame, "Which item? (number)");
                    if(num == null){
                        return;
                    }
                    if(num.equals(""))
                        return;
                    try {
                        int convertedNum = Integer.parseInt(num);
                        if (player.getContents().get(convertedNum) instanceof Item) {
                            player.getContents().get(convertedNum).remove();
                            textArea.append("You have destroyed " + player.getContents().get(convertedNum).getName() + newline);
                            textArea.append(line);
                            textArea.append(newline);
                        } else {
                            textArea.append("That item does not exist." + newline);
                            textArea.append(line);
                            textArea.append(newline);
                        }
                    }
                    catch(NumberFormatException NRE){
                        textArea.append("Please enter a legit number!" + newline);
                        textArea.append(line);
                        textArea.append(newline);
                        return;
                    }
                    catch(NullPointerException NRE){
                        textArea.append("Please enter a legit number!" + newline);
                        textArea.append(line);
                        textArea.append(newline);
                        return;
                    }catch(IndexOutOfBoundsException NRE){
                        textArea.append("You do not have that item. Please enter a legit number!" + newline);
                        textArea.append(line);
                        textArea.append(newline);
                        return;
                    }
                }
            }
        }else if(event.getSource() instanceof JMenuItem){
            JMenuItem sourceMenu = (JMenuItem) event.getSource(); // <-- error here for me, tested 5/25/14
            if(sourceMenu == quit){
                System.exit(0);
            }else if(sourceMenu == gameHelp){
                JOptionPane.showMessageDialog(frame, "FAQs and Help will go here. Feel free to populate this menu" +
                        "with more stuff and a scroll bar and buttons.");
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons();
        menus();
        panels();

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private void fight(){
        Actor player = mainBoard.getPlayer();
        try {
            int count = 0;
            for(Actor a: player.getLocation().getContentEnemies()){
                textArea.append(count + " ");
                textArea.append(a.getName() + newline);
                count++;
            }
            String choice = JOptionPane.showInputDialog(frame, "Fight who? (number)");
            temporary = Integer.parseInt(choice);
            int loopCounter = player.getLocation().getContentEnemies().get(Integer.parseInt(choice)).getHealth().getValue();
            while(loopCounter > 0) {
                if (choice == null) {
                    return;
                }
                if (choice.equals(""))
                    return;

                int convertedNum = Integer.parseInt(choice);
                if (convertedNum >= player.getLocation().getContentEnemies().size()) {
                    textArea.append("That enemy does not exist." + newline);
                    textArea.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + newline);
                    return;
                } else {
                    Object[] AttackOptions = {"Attack",
                            "Do Nothing",
                            "Run"};
                    int i = JOptionPane.showOptionDialog(
                            frame,
                            "Attack?",
                            "Attack",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            AttackOptions,
                            AttackOptions[0]);
                    if (i == 0) {
                        //Attack Option
                        //Randomly picks number between max attack and min attack
                        if(player.getSp().getValue() > player.getLocation().getContentEnemies().get(convertedNum).getSp().getValue()){
                            //Player attacks enemy
                            int pAttack = (int)(Math.random()*(player.getAtkVal().getMaxValue()-player.getAtkVal().getValue()) + player.getAtkVal().getValue()) -
                                    player.getLocation().getContentEnemies().get(convertedNum).getDefVal().getValue();
                            int health =
                                    player.getLocation().getContentEnemies().get(convertedNum).getHealth().getValue() -
                                            pAttack;
                            player.getLocation().getContentEnemies().get(convertedNum).setHealth(new Stat("Health", health, player.getLocation().getContentEnemies().get(convertedNum)));

                            //Enemy attacks player
                            int eAttack = (int)(Math.random()*(player.getLocation().getContentEnemies().get(convertedNum).getAtkVal().getMaxValue()-
                                    player.getLocation().getContentEnemies().get(convertedNum).getAtkVal().getValue()) + player.getLocation().getContentEnemies().get(convertedNum).getAtkVal().getValue())
                                    - player.getDefVal().getValue();
                            int pHealth = player.getHealth().getValue()- eAttack;
                            player.getHealth().setValue(pHealth);

                            //Lower own stamina
                            if(player.getStamina().getValue() > 3)
                                player.getStamina().setValue(player.getStamina().getValue()-3);

                        }else{
                            //Enemy attacks player
                            int eAttack = (int)(Math.random()*(player.getLocation().getContentEnemies().get(convertedNum).getAtkVal().getMaxValue()-
                                    player.getLocation().getContentEnemies().get(convertedNum).getAtkVal().getValue()) + player.getLocation().getContentEnemies().get(convertedNum).getAtkVal().getValue());
                            int pHealth = player.getHealth().getValue()- eAttack;
                            player.getHealth().setValue(pHealth);

                            //Player attacks enemy
                            int pAttack = (int)(Math.random()*(player.getAtkVal().getMaxValue()-player.getAtkVal().getValue()) + player.getAtkVal().getValue());
                            int health =
                                    player.getLocation().getContentEnemies().get(convertedNum).getHealth().getValue() -
                                            pAttack;
                            player.getLocation().getContentEnemies().get(convertedNum).setHealth(new Stat("Health", health, player.getLocation().getContentEnemies().get(convertedNum)));

                            //Lower own stamina
                            if(player.getStamina().getValue() > 3)
                                player.getStamina().setValue(player.getStamina().getValue()-3);
                        }


                        if(player.getLocation().getContentEnemies().get(convertedNum).getHealth().getValue() <= 0){
                            textArea.append(player.getLocation().getContentEnemies().get(convertedNum).getName() +"'s health: " + 0 + newline);
                            textArea.append("You have defeated the enemy. " + newline);
                            loopCounter = -10;
                            textArea.append(line);
                            textArea.append(newline);
                            int exp = player.getXp().getValue();
                            exp+=5;
                            player.getXp().setValue(exp);
                            //Level up
                            if(exp%10 == 0){
                                player.getLevel().setValue(player.getLevel().getValue()+1);
                                player.getHealth().setValue(player.getHealth().getValue()+4);
                                player.getHealth().setMaxValue(player.getHealth().getMaxValue()+4);
                                player.getSp().setValue(player.getSp().getValue()+2);
                                player.getStamina().setValue(player.getStamina().getValue()+2);
                            }

                        }else if(player.getHealth().getValue() <= 0){
                            textArea.append("Your's health: " + 0 + newline);
                            shutdown();
                            textArea.append(line);
                            textArea.append(newline);
                            loopCounter = -10;

                        }else{
                            //Shows opponent's health and your health
                            textArea.append(player.getLocation().getContentEnemies().get(convertedNum).getName() +"'s health: " +
                                    player.getLocation().getContentEnemies().get(convertedNum).getHealth().getValue() + newline);
                            textArea.append("Your's health: " + player.getHealth().getValue() + newline);
                            int atk = player.getAtkVal().getValue();
                            int maxAtk = player.getAtkVal().getMaxValue();
                            if(player.getStamina().getValue() <= 3){
                                textArea.append("You are too tired to attack. Wait a few turns.");
                                player.getAtkVal().setValue(0);
                                player.getAtkVal().setMaxValue(0);
                            }
                            textArea.append(line);
                            textArea.append(newline);
                        }

                    }else if(i == 1){
                        //Do nothing

                        int eAttack = (int)(Math.random()*(player.getLocation().getContentEnemies().get(convertedNum).getAtkVal().getMaxValue()-
                                player.getLocation().getContentEnemies().get(convertedNum).getAtkVal().getValue()) +
                                player.getLocation().getContentEnemies().get(convertedNum).getAtkVal().getValue());
                        int pHealth = player.getHealth().getValue()- eAttack;
                        player.getHealth().setValue(pHealth);

                        if(player.getStamina().getValue() != player.getStamina().getMaxValue()){
                            player.getStamina().setValue(player.getStamina().getValue()+1);
                        }

                        //If you died...
                        if(player.getHealth().getValue() <= 0){
                            textArea.append("Your's health: " + 0 + newline);
                            textArea.append(line);
                            textArea.append(newline);
                            loopCounter = 0;
                            shutdown();
                        }else{
                            //Shows opponent's health and your health
                            textArea.append(player.getLocation().getContentEnemies().get(convertedNum).getName() +"'s health: "
                                    + player.getLocation().getContentEnemies().get(convertedNum).getHealth().getValue() + newline);
                            textArea.append("Your's health: " + player.getHealth().getValue() + newline);
                            textArea.append(line);
                            textArea.append(newline);
                        }
                        textArea.append(line);
                        textArea.append(newline);
                    }else if(i == 2){
                        player.getLocation().getContentEnemies().remove(temporary);
                        loopCounter = 0;
                        textArea.append("You have lost 10 health from dodging attacks. " + newline + line + newline);
                        if(player.getHealth().getValue()>=10){
                            player.getHealth().setValue(player.getHealth().getValue()-10);
                        }else {
                            player.getHealth().setValue(0);
                            textArea.append(line);
                            textArea.append(newline);
                            shutdown();
                        }
                    }
                }
            }
        }catch (NumberFormatException NRE) {
            textArea.append("Please enter a legit number!" + newline);
            textArea.append(line);
            textArea.append(newline);
            return;
        }catch (IndexOutOfBoundsException NRE) {
            textArea.append("Enemy does not exist!" + newline);
            textArea.append(line);
            textArea.append(newline);
            return;
        }try {
            player.getLocation().getContentEnemies().remove(temporary);
        }catch(IndexOutOfBoundsException NRE){

        }
    }

    public void shutdown(){
        Object[] options = {"Ok"};
        int n = JOptionPane.showOptionDialog(
                frame,
                "You have been defeated.",
                "End",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if(n == 0){
            System.exit(0);
        }else if(n == JOptionPane.CLOSED_OPTION){
            System.exit(0);
        }
    }

    private static void buttons(){
        //Buttons
        moveButton = new JButton("Action");
        observeButton = new JButton("Observe");
        abortButton = new JButton("Abort");
        mapButton = new JButton("Map");
        inventoryButton = new JButton("Inventory");
        //skillButton = new JButton("Skills");
        //Add button listeners
        buttonListener = new MainGame(1);
        moveButton.addActionListener(buttonListener);
        observeButton.addActionListener(buttonListener);
        abortButton.addActionListener(buttonListener);
        mapButton.addActionListener(buttonListener);
        inventoryButton.addActionListener(buttonListener);
        //skillButton.addActionListener(buttonListener);
    }

    private static void panels(){
        //Panels
        buttonPanel = new JPanel();
        statPanel = new JPanel();
        textPanel = new JPanel();
        mainPanel = new JPanel();

        //Button Panel Grid Layout
        buttonPanel.setLayout(new GridLayout(25,1));
        statPanel.setLayout(new GridLayout(13,1));

        //Add contents to panel
        textPanel.add(new MainGame());
        buttonPanel.add(playerName);
        //Stats: xp,health,stamina,element,shield,fin,agil,str,cons,res,apt,atkVal,defVal
        buttonPanel.add(playerLevel);
        buttonPanel.add(playerHealth);
        buttonPanel.add(playerStamina);
        buttonPanel.add(playerElement);
        buttonPanel.add(playerXp);
        buttonPanel.add(playerShield);
        buttonPanel.add(playerFin);
        buttonPanel.add(playerAgil);
        buttonPanel.add(playerStr);
        buttonPanel.add(playerCons);
        buttonPanel.add(playerRes);
        buttonPanel.add(playerApt);
        buttonPanel.add(playerSp);
        buttonPanel.add(playerAtkVal);
        buttonPanel.add(playerDefVal);
        buttonPanel.add(space);
        buttonPanel.add(moveButton);
        buttonPanel.add(observeButton);
        buttonPanel.add(abortButton);
        buttonPanel.add(mapButton);
        buttonPanel.add(inventoryButton);
        //buttonPanel.add(skillButton);

        //Add stuff to main panel
        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(statPanel);

        //Add contents to the frame.
        frame.add(mainPanel);
    }

    private static void menus(){
        //Menu Bar
        menuBar = new JMenuBar();
        options = new JMenu("Options");
        help = new JMenu("Help");
        menuBar.add(options);
        menuBar.add(help);

        //Menu Items
        quit = new JMenuItem("Quit");
        gameHelp = new JMenuItem("Game Help");
        options.add(quit);
        help.add(gameHelp);

        frame.setJMenuBar(menuBar);

        //Add menu listener
        menuListener = new MainGame(1);
        quit.addActionListener(menuListener);
        gameHelp.addActionListener(menuListener);

    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.

        //Add later, makes swing look better
        /*
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
