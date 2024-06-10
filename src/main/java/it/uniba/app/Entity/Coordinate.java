package it.uniba.app.Entity;
import java.util.Objects;

/**
 * <<Entity>>: Classe per rappresentare le coordinate di una pedina sul tavoliere.
 */
public class Coordinate {
    private int riga;
    private int colonna;

/**
 * Costruttore della classe Coordinate.
 * @param row
 * @param column
 */

    public Coordinate(final int row, final int column) {
        this.riga = row;
        this.colonna = column;
    }

    /**
     * Costruttore di copia della classe Coordinate.
     * @param copia l'istanza di Coordinate da copiare
     */
    public Coordinate(final Coordinate copia) {
        this.riga = copia.riga;
        this.colonna = copia.colonna;
    }


    public final int getColonna() {
        return colonna;
    }

    public final void setColonna(final int column) {
        this.colonna = column;
    }

    public final int getRiga() {
        return riga;
    }
    public final void setRiga(final int row) {
        this.riga = row;
    }

    /**
     * Metodo equals della classe Coordinate.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            Coordinate that = (Coordinate) obj;
            return riga == that.riga && colonna == that.colonna;
       }
  }

/**
     * Metodo hashCode della classe Coordinate.
     */
    @Override
    public int hashCode() {
        return Objects.hash(riga, colonna);
}

    @Override
    public final String toString() {
        return "(" + (char) ('a' + colonna) + "  " + riga + " )";
    }
}
