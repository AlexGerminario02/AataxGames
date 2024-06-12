package it.uniba.app.Entity;

import java.util.Objects;

/**
 * <<Entity>>
 * Classe che rappresenta una pedina.
 * Una pedina è un'entità che ha un carattere e una posizione
 * (riga e colonna) sulla scacchiera.
 */
public class Pedina {
    // Attributi della pedina
    private String carattere;
    private Coordinate coordinate;
    /**
     * Costruttore della classe Pedina.
     * @param caratteri
     * @param coordinates
     */
    public Pedina(final String caratteri, final Coordinate coordinates) {
        this.carattere = caratteri;
        this.coordinate = new Coordinate(coordinates.getRiga(), coordinates.getColonna());
    }

    /**
     * Costruttore di copia della classe Pedina.
     * @param copia L'istanza di Pedina da copiare.
     */
    public Pedina(final Pedina copia) {
        this.carattere = copia.carattere;
        this.coordinate = new Coordinate(copia.coordinate); // Supponendo che Coordinate abbia un costruttore di copia
    }
    // Metodi getter e setter
    public final String getCarattere() {
        return carattere;
    }

    public final void setCarattere(final String caratteri) {
        this.carattere = caratteri;
    }

    public final Coordinate getCoordinate() {
        return new Coordinate(coordinate.getRiga(), coordinate.getColonna());
    }

    public final void setCoordinate(final Coordinate coordinates) {
        this.coordinate = new Coordinate(coordinates.getRiga(), coordinates.getColonna());
    }

    /**
 *  Metodo equals della classe Pedina.
 */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pedina pedina = (Pedina) o;
        return Objects.equals(carattere, pedina.carattere)
         && Objects.equals(coordinate, pedina.coordinate);
    }

    /**
     * Metodo hashCode della classe Pedina.
     */
    @Override
    public int hashCode() {
        return Objects.hash(carattere, coordinate);
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
