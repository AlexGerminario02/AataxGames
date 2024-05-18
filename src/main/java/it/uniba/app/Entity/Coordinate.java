package it.uniba.app.Entity;

/**
 * <<Entity>>: Classe per rappresentare le coordinate di una pedina sul tavoliere.
 */
public class Coordinate {
    private int riga;
    private char colonna;

/**
 * Costruttore della classe Coordinate.
 * @param row
 * @param column
 */

    public Coordinate(final int row, final char column) {
        this.riga = row;
        this.colonna = column;
    }

    public final char getColonna() {
        return colonna;
    }

    public final int getRiga() {
        return riga;
    }
}
