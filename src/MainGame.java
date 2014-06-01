/** Used Oracle's TextDemo.java as a base
 * http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextDemoProject/src/components/TextDemo.java
 **/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainGame extends JPanel implements ActionListener {
    private static JButton moveButton;
    private static JButton observeButton;
    private static JButton abortButton;
    private static JButton mapButton;
    private static JButton inventoryButton;
    private static JLabel playerName;
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
    private static JLabel playerAtkVal;
    private static JLabel playerDefVal;
    private static JLabel playerWeaponVal;
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
    private static GameBoard mainBoard;
    private static MapGUI map;
    private static ActionListener buttonListener;
    private static ActionListener menuListener;

    public MainGame() {
        super(new GridBagLayout());
        textArea = new JTextArea(70, 90);
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

        mainBoard = new GameBoard();
        playerName = new JLabel("Player Name: " +mainBoard.getPlayerName());
        space = new JLabel(" ");
        //Stats: xp,health,stamina,element,shield,fin,agil,str,cons,res,apt,atkVal,defVal
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
        playerAtkVal = new JLabel(mainBoard.getPlayer().atkVal());
        playerDefVal = new JLabel(mainBoard.getPlayer().defVal());
        playerWeaponVal = new JLabel(mainBoard.getPlayer().weaponVal());
        textArea.append("Use the observe button to look around" + newline);
    }

    public MainGame(int x){

    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        Actor player = mainBoard.getPlayer();
        Room relativeLoc = player.getLocation();
        relativeLoc.explored();
        if(event.getSource() instanceof JButton) {

            if (source == moveButton) {
                /**Custom Button Text
                 * Source: http://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
                 * http://stackoverflow.com/questions/1257420/making-a-joptionpane-with-4-options
                 **/
                Object[] options = {"North",
                        "South",
                        "East",
                        "West"};
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
                    map.revalid();
                    if (relativeLoc.getNorth() != null) {
                        textArea.append("Moving North from " + relativeLoc.getName() + newline);
                        player.setLocation(relativeLoc.getNorth());
                    }
                } else if (n == 1) {
                    map.revalid();
                    if (relativeLoc.getSouth() != null) {
                        textArea.append("Moving South from " + relativeLoc.getName() + newline);
                        player.setLocation(relativeLoc.getSouth());
                    }
                } else if (n == 2) {
                    map.revalid();
                    if (relativeLoc.getEast() != null) {
                        textArea.append("Moving East from " + relativeLoc.getName() + newline);
                        player.setLocation(relativeLoc.getEast());
                    }
                } else if (n == 3) {
                    map.revalid();
                    if (relativeLoc.getWest() != null) {
                        textArea.append("Moving West from " + relativeLoc.getName() + newline);
                        player.setLocation(relativeLoc.getWest());
                    }
                } else if (n == JOptionPane.CLOSED_OPTION) {
                    textArea.append("Movement Canceled" + newline);
                }
            } else if (source == observeButton) {
                textArea.append(relativeLoc.getDesc() + newline);
                textArea.append("Exits:   " + relativeLoc.getExits() + newline);
                if(player.getLocation().getContents()!= null){
                    textArea.append("Items availible to be picked up:" + newline);
                    for(Item i: player.getLocation().getContents()){
                        textArea.append(i.getName() + ", ");
                    }
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
            } else if (source == inventoryButton){
                ArrayList <Item> items = player.getContents();
                for(Item i : items){
                    textArea.append(i.getName() + newline);
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

    private static void buttons(){
        //Buttons
        moveButton = new JButton("Move");
        observeButton = new JButton("Observe");
        abortButton = new JButton("Abort");
        mapButton = new JButton("Map");
        inventoryButton = new JButton("Inventory");

        //Add button listeners
        buttonListener = new MainGame(1);
        moveButton.addActionListener(buttonListener);
        observeButton.addActionListener(buttonListener);
        abortButton.addActionListener(buttonListener);
        mapButton.addActionListener(buttonListener);

    }

    private static void panels(){
        //Panels
        buttonPanel = new JPanel();
        statPanel = new JPanel();
        textPanel = new JPanel();
        mainPanel = new JPanel();

        //Button Panel Grid Layout
        buttonPanel.setLayout(new GridLayout(20,1));
        statPanel.setLayout(new GridLayout(13,1));

        //Add contents to panel
        textPanel.add(new MainGame());
        buttonPanel.add(playerName);
        //Stats: xp,health,stamina,element,shield,fin,agil,str,cons,res,apt,atkVal,defVal
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
        buttonPanel.add(playerAtkVal);
        buttonPanel.add(playerDefVal);
        buttonPanel.add(space);
        buttonPanel.add(moveButton);
        buttonPanel.add(observeButton);
        buttonPanel.add(abortButton);
        buttonPanel.add(mapButton);
        buttonPanel.add(inventoryButton);

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
