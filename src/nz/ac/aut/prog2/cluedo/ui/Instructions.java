package nz.ac.aut.prog2.cluedo.ui;

/**
 * Class for the Instructions window.
 * 
 * @author Warrick Wills 13831575
 * @version 2014.10: Created
 */
public class Instructions extends javax.swing.JFrame
{

    /**
     * Creates new form Instructions
     */
    public Instructions()
    {
        initComponents();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText(" Introduction:\n\nMr. Black has been found murdered in tudor mansion. It is up to you, the top detective in New York to figure out the crime commited based on clues that suspects give you. Three pieces of evidence are needed in order to solve the case. The murderer, the murder weapon and the room in which the murder was committed. To solve the case you must travel around the mansion and interrogate suspects, by which you will aquire clues leading you closer to closing the case!\n\nRules\n\n- You will be given 100 action points when the game starts, and if you run out of these points, the game is over.\n- You will be given 3 clues when the game starts.\n- When you are not in a room (you are either in the doorway or hallway) you will be charged 1 action point per square moved.\n- Collect clue is free if you get a clue you havent got before. Otherwise it will cost 10 points.\n- If you make a wrong accusation it will cost 20 action points. However if you are correct, you win the game and have apprehended the murderer!\n\n Shortcut keys\n\n - Q : Exit the Game.\n - N : Restart the game.\n - C : Collect Clue.\n - Arrow Keys : Move player.\n\n Cluedo Interface:\n\n ***On the board***\n\n - The player (you) appears as a pink square.\n - Suspects appear as blue squares.\n - Doorways appear as grey squares and can be moved through.\n - Walls appear as brown squares and cannot be moved through.\n - Room tiles appear as yellow square and can be walked along.\n - Hallway tiles appear as white squares and can be walked along.\n\n ***To the left of the board***\n \n - Current Room - This is the name of the room you are currently in.\n - Game State - Tells you if you are playing, have won or have lost.\n - Suspects and Clues Present - Tells you which suspects are present and how many clues each suspect is holding.\n - Get Clue From Suspect - Click this button to get a clue from a suspect in the same room as you.\n - Clues Collected - This is a list of the clues you currently have.\n\n ***To the right of the board***\n \n - Action points - This is the amount of action points you have remaining.\n - Make accusation - You can select the murder details you think are correctusing the drop dow boxes provided, and make an accusation by pressing themake accusation button.\n - Move player - You can use these buttons to move your player if you wish.\n \n ***Menu***\n\n - Game\n   - New Game - Restart the game.\n   - Exit - Exit the game.\n\n - Help\n   - Instructions - Display these instructions at any time during the game.\n   - About - Information about the creation of the game.\n\nHave fun and good luck detective!");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}