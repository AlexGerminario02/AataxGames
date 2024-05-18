package it.uniba.app;

/**
 * <<Entity>>
 * Classe che rappresenta una pedina.
 * Una pedina è un'entità che ha un carattere e una posizione
 * (riga e colonna) sulla scacchiera.
 */
public class Pedina {
    // Attributi della pedina
    private char carattere;
    private int riga;
    private int colonna;

    /**
     * Costruttore della classe Pedina.
     * @param caratteri
     * @param row
     * @param column
     */
    public Pedina(final char caratteri, final int row, final int column) {
        this.carattere = caratteri;
        this.riga = row;
        this.colonna = column;
    }

    // Metodi getter e setter
    public final char getCarattere() {
        return carattere;
    }

    public final void setCarattere(final char caratteri) {
        this.carattere = caratteri;
    }

    public final int getRiga() {
        return riga;
    }
    public final void setRiga(final int row) {
        this.riga = row;
    }

    public final int getColonna() {
        return colonna;
    }

    public final void setColonna(final int column) {
        this.colonna = column;
    }

    // Metodo toString
    @Override
    public final String toString() {
        return "Pedina{"
                +
                "carattere=" + carattere
                +
                ", riga=" + riga
                +
                ", colonna=" + colonna
                +
                '}';
    }
}
