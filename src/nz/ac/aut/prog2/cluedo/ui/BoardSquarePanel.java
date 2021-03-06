package nz.ac.aut.prog2.cluedo.ui;

import java.awt.Color;
import nz.ac.aut.prog2.cluedo.model.BoardSquare;
import nz.ac.aut.prog2.cluedo.model.Cluedo;

/**
 * A class to represent the the Board Square panel of the Cluedo GUI.
 *
 * @author Warrick Wills 13831575
 * @version 2014.10: Created
 */
public class BoardSquarePanel extends javax.swing.JPanel {

    private final Cluedo world;
    private final int row, column;

    /**
     * Creates new form BoardSquarePanel
     *
     * @param world The current world
     * @param row The row of the panel
     * @param column The column of the panel
     */
    public BoardSquarePanel(Cluedo world, int row, int column) {
        this.world = world;
        this.row = row;
        this.column = column;
        initComponents();
        update();
    }

    /**
     * Updates the panel
     */
    public void update() {
        // set colours of board contents
        BoardSquare bs = world.getBoardSquare(row, column);
        if ("W".equals(bs.getStringRepresentation())) {
            setBackground(bs.getColour());
        } else if (".".equals(bs.getStringRepresentation())) {
            setBackground(bs.getColour());
        } else if ("D".equals(bs.getStringRepresentation())) {
            setBackground(Color.gray);
        }
        if ("S".equals(bs.getOccupantStringRepresentation())) {
            setBackground(Color.BLUE);
        } else if ("P".equals(bs.getOccupantStringRepresentation())) {
            setBackground(Color.PINK);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblContents = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(new java.awt.BorderLayout());

        lblContents.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        add(lblContents, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblContents;
    // End of variables declaration//GEN-END:variables
}
