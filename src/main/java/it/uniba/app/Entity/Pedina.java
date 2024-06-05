package it.uniba.app.Entity;


/**
 * <<Entity>>
 * Classe che rappresenta una pedina.
 * Una pedina è un'entità che ha un carattere e una posizione
 * (riga e colonna) sulla scacchiera.
 */
public class Pedina {
    // Attributi della pedina
    private char carattere;
    private Coordinate coordinate;
    /**
     * Costruttore della classe Pedina.
     * @param caratteri
     * @param coordinates
     */
    public Pedina(final char caratteri, final Coordinate coordinates) {
        this.carattere = caratteri;
        this.coordinate = coordinates;
    }

    /**
     * Costruttore di copia della classe Pedina.
     * @param copia L'istanza di Pedina da copiare.
     */
    public Pedina(final Pedina copia) {
        this.carattere = copia.carattere;
        this.coordinate = new Coordinate(copia.coordinate.getRiga(), copia.coordinate.getColonna());
    }

    // Metodi getter e setter
    public final char getCarattere() {
        return carattere;
    }

    public final void setCarattere(final char caratteri) {
        this.carattere = caratteri;
    }

    public final Coordinate getCoordinate() {
        return coordinate;
    }

    public final void setCoordinate(final Coordinate coordinates) {
        this.coordinate = coordinates;
    }


    // Metodo toString
    @Override
    public final String toString() {
        return "Pedina{"
                +
                "carattere=" + carattere
                +
                ", coordinate=" + coordinate
                +
                '}';
    }
}
