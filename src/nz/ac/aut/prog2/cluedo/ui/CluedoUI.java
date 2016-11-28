package nz.ac.aut.prog2.cluedo.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import nz.ac.aut.prog2.cluedo.model.Clue;
import nz.ac.aut.prog2.cluedo.model.Cluedo;
import nz.ac.aut.prog2.cluedo.model.GameState;
import nz.ac.aut.prog2.cluedo.model.MoveDirection;
import nz.ac.aut.prog2.cluedo.model.MurderDetails;
import nz.ac.aut.prog2.cluedo.model.RoomName;
import nz.ac.aut.prog2.cluedo.model.Suspect;
import nz.ac.aut.prog2.cluedo.model.SuspectName;
import nz.ac.aut.prog2.cluedo.model.WeaponName;

/**
 * A class to represent the Cluedo GUI
 *
 * @author Warrick Wills 13831575
 * @version 2014.10: Created
 * @version 2014.10: Added button functionality
 * @version 2014.10: Added action points bar, new game, instruction menu
 * functionality and other functionality
 * @version 2014.10: Added gameLost() method, altered winning component to make
 * accusation event listener.
 * @version 2014.10: Added key pressed button functionality
 * @version 2014.10: Added getPlayerName() and pictures
 */
public class CluedoUI extends javax.swing.JFrame {

    private final Cluedo newGame;

    /**
     * Creates new form CluedoUI
     *
     * @param newGame The instance of the game being played
     */
    public CluedoUI(Cluedo newGame) {
        this.newGame = newGame;
        initComponents();
        createGridSquarePanels();

        // center JFrame
        setLocationRelativeTo(null);

        // set the player name text and at the same time call the method to display message
        playerNameLabel.setText("Player's Name: Detective " + getPlayerName());

        newGame.startGame();
        update();
    }

    /**
     * Creates the board and sets it to the text grid
     */
    private void createGridSquarePanels() {
        // get size of world
        int rows = newGame.getNumberOfRows();
        int columns = newGame.getNumberOfColumns();

        // clear the grid panel
        cluedoTextGrid.removeAll();

        // set the layout manager
        cluedoTextGrid.setLayout(new GridLayout(rows, columns));

        // fill the panel with the grid square panels
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                BoardSquarePanel panel = new BoardSquarePanel(newGame, row, col);
                cluedoTextGrid.add(panel);
            }
        }

    }

    /**
     * Updates the JFrame components
     */
    private void update() {
        for (Component component : cluedoTextGrid.getComponents()) {
            BoardSquarePanel bs = (BoardSquarePanel) component;
            bs.update();
        }
        // Action points bar
        int playerActionPoints = newGame.getPlayer().getActionPoints();
        actionPointsRemaining.setValue(playerActionPoints);
        actionPointsRemaining.setString(playerActionPoints + "%");

        // Clues List text area
        Clue[] clues;
        clues = newGame.getPlayer().getCluesAsArray();
        cluesList.setListData(clues);

        // set game state label
        gameState.setText("Game State: " + newGame.getGameState());

        if (newGame.getCurrentRoom() == null) {
            currentRoom.setText("Current Room: Player not in room");
        } else {
            currentRoom.setText("Current Room: " + newGame.getCurrentRoom().getName());
        }

        //playerNameLabel.setText("Player's Name:" + getPlayerName());
        if (playerActionPoints == 0) {
            gameLost();
        }

        // call suspect info method
        suspectInfo();

    }

    /**
     * Based on whether suspect is present or not this method sets the suspect
     * text area to the suspects present, and clues each hold or the other
     * relative information based on what the room contains.
     */
    public void suspectInfo() {
        String print = "";

        // if the player isn't in a room
        if (newGame.getCurrentRoom() == null) {
            print = "No suspects in hallway";
        } // if the player is in a room with no suspects
        else if (newGame.getCurrentRoom().containsSuspect() == false) {
            print = "No suspects present";
        } // if a player is in a room with suspects
        else if (newGame.getCurrentRoom() != null) {

            // get the suspects as an array and create a string of this
            Suspect[] suspects = newGame.getCurrentRoom().getSuspectsAsArray();
            for (Suspect suspect : suspects) {
                print += suspect.getName().toString() + "\n" + suspect.getCluesAsArray().length + " clue(s) \n";
            }
        }

        // set the text area to the string print
        suspectText.setText(print);
    }

    /**
     * Presents user with game lost notice. Asks if user would like to play
     * again.
     */
    public void gameLost() {
        // Prompt usergameLost for confirmation.
        int confirm = JOptionPane.showConfirmDialog(null,
                "You have failed to identify the murderer and have run out of Action Points!\n"
                + "The corect answer was " + newGame.getMurderDescription().getFullDescription()
                + "\nDo you want to play again?.", "You lose!",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        // Exit game if OK.
        if (confirm == 0) {
            newGame.getPlayer().reset();
            newGame.startGame();
            update();
        } else {
            System.exit(0);
        }

    }

    /**
     * Allows instructions window to be displayed
     */
    public static void showInstructions() {
        // Create and show instructions window.
        Instructions instructions = new Instructions();
        instructions.setVisible(true);

        // Make just the instructions window close rather than entire program.
        instructions.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Allows about window to be displayed
     */
    private void showAbout() {
        // Create and show about window.
        About about = new About();
        about.setVisible(true);

        // Make just the window close rather than entire program.
        about.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Prompts player for name until a non-empty value is entered.
     *
     * @return The player's name.
     */
    public static String getPlayerName() {
        String input;
        do {
            input = (JOptionPane.showInputDialog(null,
                    "Enter your name, Detective:",
                    "Cluedo", JOptionPane.PLAIN_MESSAGE));
        } while (input != null && input.length() == 0);

        // Cancel option selected
        if (input == null) {
            System.exit(0);
        }

        return input;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        murderSuspectSelect = new javax.swing.JComboBox();
        murderWeaponSelect = new javax.swing.JComboBox();
        murderRoomSelect = new javax.swing.JComboBox();
        murderSuspectLabel = new javax.swing.JLabel();
        murderWeaponLabel = new javax.swing.JLabel();
        murderRoomLabel = new javax.swing.JLabel();
        MakeAccusationButton = new javax.swing.JButton();
        actionPointsRemaining = new javax.swing.JProgressBar();
        ActionPointsLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cluesList = new javax.swing.JList();
        clueLabel = new javax.swing.JLabel();
        getClueButton = new javax.swing.JButton();
        moveUp = new javax.swing.JButton();
        moveDown = new javax.swing.JButton();
        moveLeft = new javax.swing.JButton();
        moveRight = new javax.swing.JButton();
        movePlayerLabel = new javax.swing.JLabel();
        gameState = new javax.swing.JLabel();
        currentRoom = new javax.swing.JLabel();
        pictureLabel = new javax.swing.JLabel();
        cluedoLabel = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        suspectText = new javax.swing.JTextArea();
        suspectInfoLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cluedoTextGrid = new javax.swing.JTextArea();
        imageLabel2 = new javax.swing.JLabel();
        imageLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        gameMenu = new javax.swing.JMenu();
        newGameMI = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMI = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        instructionsMI = new javax.swing.JMenuItem();
        aboutMI = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);

        murderSuspectSelect.setModel(new javax.swing.DefaultComboBoxModel(SuspectName.values()));
        murderSuspectSelect.setFocusable(false);

        murderWeaponSelect.setModel(new javax.swing.DefaultComboBoxModel(WeaponName.values()));
        murderWeaponSelect.setFocusable(false);

        murderRoomSelect.setModel(new javax.swing.DefaultComboBoxModel(RoomName.values()));
        murderRoomSelect.setFocusable(false);

        murderSuspectLabel.setFont(new java.awt.Font("Papyrus", 0, 16)); // NOI18N
        murderSuspectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        murderSuspectLabel.setText("Murder Suspect");
        murderSuspectLabel.setFocusable(false);

        murderWeaponLabel.setFont(new java.awt.Font("Papyrus", 0, 16)); // NOI18N
        murderWeaponLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        murderWeaponLabel.setText("Murder Weapon");
        murderWeaponLabel.setFocusable(false);

        murderRoomLabel.setFont(new java.awt.Font("Papyrus", 0, 16)); // NOI18N
        murderRoomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        murderRoomLabel.setText("Murder Room   ");
        murderRoomLabel.setFocusable(false);

        MakeAccusationButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        MakeAccusationButton.setText("Make Accusation");
        MakeAccusationButton.setFocusable(false);
        MakeAccusationButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MakeAccusationButtonMouseClicked(evt);
            }
        });

        actionPointsRemaining.setFont(new java.awt.Font("Kalinga", 0, 18)); // NOI18N
        actionPointsRemaining.setForeground(new java.awt.Color(0, 0, 255));
        actionPointsRemaining.setFocusable(false);
        actionPointsRemaining.setString("100");
        actionPointsRemaining.setStringPainted(true);

        ActionPointsLabel.setFont(new java.awt.Font("Papyrus", 0, 16)); // NOI18N
        ActionPointsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ActionPointsLabel.setText("  Action Points");
        ActionPointsLabel.setFocusable(false);

        jScrollPane2.setFocusable(false);

        cluesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        cluesList.setFocusable(false);
        jScrollPane2.setViewportView(cluesList);

        clueLabel.setFont(new java.awt.Font("Tempus Sans ITC", 0, 18)); // NOI18N
        clueLabel.setText("Clues Collected");
        clueLabel.setFocusable(false);

        getClueButton.setFont(new java.awt.Font("Simplified Arabic", 0, 18)); // NOI18N
        getClueButton.setText("Get Clue From Suspect");
        getClueButton.setFocusable(false);
        getClueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getClueButtonMouseClicked(evt);
            }
        });

        moveUp.setText("UP");
        moveUp.setToolTipText("");
        moveUp.setFocusable(false);
        moveUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moveUpMouseClicked(evt);
            }
        });

        moveDown.setText("DOWN");
        moveDown.setToolTipText("");
        moveDown.setFocusable(false);
        moveDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moveDownMouseClicked(evt);
            }
        });

        moveLeft.setText("LEFT");
        moveLeft.setToolTipText("");
        moveLeft.setFocusable(false);
        moveLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moveLeftMouseClicked(evt);
            }
        });

        moveRight.setText("RIGHT");
        moveRight.setToolTipText("");
        moveRight.setFocusable(false);
        moveRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moveRightMouseClicked(evt);
            }
        });

        movePlayerLabel.setFont(new java.awt.Font("Papyrus", 0, 16)); // NOI18N
        movePlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movePlayerLabel.setText("Move Player");
        movePlayerLabel.setToolTipText("");
        movePlayerLabel.setFocusable(false);

        gameState.setText("Game State: XX");
        gameState.setToolTipText("");
        gameState.setFocusable(false);

        currentRoom.setText("Current Room: XX");
        currentRoom.setFocusable(false);

        pictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cluedo.jpg"))); // NOI18N
        pictureLabel.setToolTipText("");
        pictureLabel.setFocusable(false);

        cluedoLabel.setFont(new java.awt.Font("Ravie", 0, 48)); // NOI18N
        cluedoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cluedoLabel.setText("Cluedo");
        cluedoLabel.setToolTipText("");
        cluedoLabel.setFocusable(false);

        playerNameLabel.setFont(new java.awt.Font("Lucida Calligraphy", 0, 14)); // NOI18N
        playerNameLabel.setText("Player's Name: XX");
        playerNameLabel.setFocusable(false);

        jScrollPane1.setFocusable(false);

        suspectText.setEditable(false);
        suspectText.setColumns(20);
        suspectText.setRows(5);
        jScrollPane1.setViewportView(suspectText);

        suspectInfoLabel.setFont(new java.awt.Font("Tempus Sans ITC", 0, 18)); // NOI18N
        suspectInfoLabel.setText("Suspects and Clues Present");
        suspectInfoLabel.setFocusable(false);

        cluedoTextGrid.setEditable(false);
        cluedoTextGrid.setColumns(20);
        cluedoTextGrid.setRows(5);
        cluedoTextGrid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cluedoTextGridKeyPressed(evt);
                newGameKey(evt);
                quitGameKey(evt);
                getClueKey(evt);
            }
        });
        jScrollPane3.setViewportView(cluedoTextGrid);

        imageLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Carmen_Sandiego.jpg"))); // NOI18N

        imageLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/bloody_knife_cluedo.jpg"))); // NOI18N

        jMenuBar1.setFocusable(false);

        gameMenu.setText("Game");

        newGameMI.setText("New Game");
        newGameMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameMIActionPerformed(evt);
            }
        });
        gameMenu.add(newGameMI);
        gameMenu.add(jSeparator1);

        exitMI.setText("Exit");
        exitMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMIActionPerformed(evt);
            }
        });
        gameMenu.add(exitMI);

        jMenuBar1.add(gameMenu);

        helpMenu.setText("Help");

        instructionsMI.setText("Instructions");
        instructionsMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instructionsMIActionPerformed(evt);
            }
        });
        helpMenu.add(instructionsMI);

        aboutMI.setText("About");
        aboutMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMIActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMI);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(playerNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(currentRoom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(gameState, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(suspectInfoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(getClueButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(clueLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pictureLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                        .addComponent(imageLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cluedoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(moveLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(moveRight, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(movePlayerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(murderRoomSelect, 0, 172, Short.MAX_VALUE)
                        .addComponent(murderWeaponSelect, 0, 172, Short.MAX_VALUE)
                        .addComponent(murderSuspectSelect, 0, 172, Short.MAX_VALUE)
                        .addComponent(MakeAccusationButton, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addComponent(actionPointsRemaining, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addComponent(ActionPointsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(murderSuspectLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(murderWeaponLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(murderRoomLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(moveUp, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moveDown, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(86, 86, 86))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cluedoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imageLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imageLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ActionPointsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actionPointsRemaining, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(murderSuspectLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(murderSuspectSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(murderWeaponLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(murderWeaponSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(murderRoomLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(murderRoomSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(MakeAccusationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(movePlayerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(moveUp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(moveLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moveRight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(moveDown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gameState, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(suspectInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(getClueButton)
                        .addGap(18, 18, 18)
                        .addComponent(clueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MakeAccusationButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MakeAccusationButtonMouseClicked
        // takes player inputed accusation
        SuspectName murderSuspect = (SuspectName) murderSuspectSelect.getSelectedItem();
        WeaponName murderWeapon = (WeaponName) murderWeaponSelect.getSelectedItem();
        RoomName murderRoom = (RoomName) murderRoomSelect.getSelectedItem();
        MurderDetails accusation = new MurderDetails(murderSuspect, murderWeapon, murderRoom);

        // checks if game is in play
        if (newGame.isAccusationPossible() == true) {
            newGame.makeAccusation(accusation);
        } else {
            JOptionPane.showMessageDialog(null, "Accusation not possible! Game must be in play!");
        }

        // if game is won based on a corret accusation
        if (newGame.getGameState() == GameState.WON) {

            // Prompt user for confirmation.
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Congratulations, you have reprehended the murderer!"
                    + "\nDo you want to play again?",
                    "You win!", JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            // Exit game if OK.
            if (confirm == 0) {
                newGame.getPlayer().reset();
                newGame.startGame();
                update();
            } else {
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect! Please Try Again!!");
        }
        update();
    }//GEN-LAST:event_MakeAccusationButtonMouseClicked

    private void getClueButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getClueButtonMouseClicked
        // checks if clue is available and gets it, otherwise display message
        if (newGame.isClueAvailable() == true) {
            newGame.getClueForPlayer();
        } else {
            JOptionPane.showMessageDialog(null, "Clue is not available, make sure you are in a room with a suspect!");
        }
        update();
    }//GEN-LAST:event_getClueButtonMouseClicked

    private void moveUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveUpMouseClicked
        // checks if move is possible and does it, otherwise display message
        if (newGame.moveIsPossible(MoveDirection.NORTH)) {
            newGame.movePlayer(MoveDirection.NORTH);
        } else {
            JOptionPane.showMessageDialog(null, "This move is not possible! Please Try Again!!");
        }
        update();
    }//GEN-LAST:event_moveUpMouseClicked

    private void moveDownMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveDownMouseClicked
        // checks if move is possible and does it, otherwise display message
        if (newGame.moveIsPossible(MoveDirection.SOUTH)) {
            newGame.movePlayer(MoveDirection.SOUTH);
        } else {
            JOptionPane.showMessageDialog(null, "This move is not possible! Please Try Again!!");
        }
        update();
    }//GEN-LAST:event_moveDownMouseClicked

    private void moveLeftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveLeftMouseClicked
        // checks if move is possible and does it, otherwise display message
        if (newGame.moveIsPossible(MoveDirection.WEST)) {
            newGame.movePlayer(MoveDirection.WEST);
        } else {
            JOptionPane.showMessageDialog(null, "This move is not possible! Please Try Again!!");
        }
        update();
    }//GEN-LAST:event_moveLeftMouseClicked

    private void moveRightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveRightMouseClicked
        // checks if move is possible and does it, otherwise display message
        if (newGame.moveIsPossible(MoveDirection.EAST)) {
            newGame.movePlayer(MoveDirection.EAST);
        } else {
            JOptionPane.showMessageDialog(null, "This move is not possible! Please Try Again!!");
        }
        update();
    }//GEN-LAST:event_moveRightMouseClicked

    private void newGameMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameMIActionPerformed
        // Prompt user for confirmation
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you wish to start a new game? All progress will be lost",
                "Are you sure?", JOptionPane.OK_CANCEL_OPTION);

        // Exit game if OK
        if (confirm == 0) {
            newGame.getPlayer().reset();
            newGame.startGame();
            update();
        }
    }//GEN-LAST:event_newGameMIActionPerformed

    private void exitMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMIActionPerformed
        // Prompt user for confirmation
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you wish to Quit? All progress will be lost",
                "Are you sure?", JOptionPane.OK_CANCEL_OPTION);

        // Exit game if OK
        if (confirm == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitMIActionPerformed

    private void instructionsMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instructionsMIActionPerformed
        showInstructions();
    }//GEN-LAST:event_instructionsMIActionPerformed

    private void aboutMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMIActionPerformed
        showAbout();
    }//GEN-LAST:event_aboutMIActionPerformed

    private void cluedoTextGridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cluedoTextGridKeyPressed
        // checks which key the user entered
        // checks if the move is possible in that direction
        // if true, does it, otherwise displays message
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (newGame.moveIsPossible(MoveDirection.NORTH)) {
                newGame.movePlayer(MoveDirection.NORTH);
                update();
            } else {
                JOptionPane.showMessageDialog(null, "This move is not possible! Please Try Again!!");
            }
        } else if ((evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            if (newGame.moveIsPossible(MoveDirection.SOUTH)) {
                newGame.movePlayer(MoveDirection.SOUTH);
                update();
            } else {
                JOptionPane.showMessageDialog(null, "This move is not possible! Please Try Again!!");
            }
        } else if ((evt.getKeyCode() == KeyEvent.VK_RIGHT)) {
            if (newGame.moveIsPossible(MoveDirection.EAST)) {
                newGame.movePlayer(MoveDirection.EAST);
                update();
            } else {
                JOptionPane.showMessageDialog(null, "This move is not possible! Please Try Again!!");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            if (newGame.moveIsPossible(MoveDirection.WEST)) {
                newGame.movePlayer(MoveDirection.WEST);
                update();
            } else {
                JOptionPane.showMessageDialog(null, "This move is not possible! Please Try Again!!");
            }
        }
    }//GEN-LAST:event_cluedoTextGridKeyPressed

    private void newGameKey(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newGameKey
        // if player types N
        if (evt.getKeyCode() == KeyEvent.VK_N) {

            // Prompt user for confirmation.
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you wish to start a new game? All progress will be lost",
                    "Are you sure?", JOptionPane.OK_CANCEL_OPTION);

            // Exit game if OK.
            if (confirm == 0) {
                newGame.getPlayer().reset();
                newGame.startGame();
                update();
            }
        }
    }//GEN-LAST:event_newGameKey

    private void quitGameKey(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quitGameKey
        // if player types Q
        if (evt.getKeyCode() == KeyEvent.VK_Q) {

            // Prompt user for confirmation.
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you wish to Quit? All progress will be lost",
                    "Are you sure?", JOptionPane.OK_CANCEL_OPTION);

            // Exit game if OK.
            if (confirm == 0) {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_quitGameKey

    private void getClueKey(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_getClueKey
        // if player types C
        if (evt.getKeyCode() == KeyEvent.VK_C) {

            // checks if clue is available and gets it, otherwise display message
            if (newGame.isClueAvailable() == true) {
                newGame.getClueForPlayer();
            } else {
                JOptionPane.showMessageDialog(null, "Clue is not available, make sure you are in a room with a suspect!");
            }
            update();
        }
    }//GEN-LAST:event_getClueKey
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActionPointsLabel;
    private javax.swing.JButton MakeAccusationButton;
    private javax.swing.JMenuItem aboutMI;
    private javax.swing.JProgressBar actionPointsRemaining;
    private javax.swing.JLabel clueLabel;
    private javax.swing.JLabel cluedoLabel;
    private javax.swing.JTextArea cluedoTextGrid;
    private javax.swing.JList cluesList;
    private javax.swing.JLabel currentRoom;
    private javax.swing.JMenuItem exitMI;
    private javax.swing.JMenu gameMenu;
    private javax.swing.JLabel gameState;
    private javax.swing.JButton getClueButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel imageLabel2;
    private javax.swing.JLabel imageLabel3;
    private javax.swing.JMenuItem instructionsMI;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton moveDown;
    private javax.swing.JButton moveLeft;
    private javax.swing.JLabel movePlayerLabel;
    private javax.swing.JButton moveRight;
    private javax.swing.JButton moveUp;
    private javax.swing.JLabel murderRoomLabel;
    private javax.swing.JComboBox murderRoomSelect;
    private javax.swing.JLabel murderSuspectLabel;
    private javax.swing.JComboBox murderSuspectSelect;
    private javax.swing.JLabel murderWeaponLabel;
    private javax.swing.JComboBox murderWeaponSelect;
    private javax.swing.JMenuItem newGameMI;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JLabel suspectInfoLabel;
    private javax.swing.JTextArea suspectText;
    // End of variables declaration//GEN-END:variables
}
