package it.uniba.app.Entity;

import java.util.ArrayList;
import java.util.List;
import it.uniba.app.Boundary.Costanti;


/**
 * <<Entity>>: Classe che rappresenta un blocco di caselle sulla scacchiera.
 */
public class Blocca {
    public static final int DIM = 7;
    private Pedina[][] scacchiera;
    private List<Coordinate> caselleBloccateg;

    /**
     * Costruttore della classe Blocca.
     * @param scacchierab La matrice che rappresenta la scacchiera.
     * @param caselleBloccate La lista delle coordinate delle caselle bloccate.
     */
    public Blocca(final Pedina[][] scacchierab, final List<Coordinate> caselleBloccate) {
        // Creiamo una copia difensiva della matrice scacchiera
        this.scacchiera = new Pedina[scacchierab.length][];
        for (int i = 0; i < scacchierab.length; i++) {
            this.scacchiera[i] = scacchierab[i].clone();
        }

        // Creiamo una copia difensiva della lista delle caselle bloccate
        this.caselleBloccateg = new ArrayList<>(caselleBloccate);
    }

    /**
     * Resetta le celle bloccate.
     * @param caselleBloccate La lista delle coordinate delle caselle bloccate da resettare.
     */
public void resettaCelleBloccate(final List<Coordinate> caselleBloccate) {
    caselleBloccate.clear();
}

/**
 * .
 * @param coord
 * @return
 */
public final boolean isBloccata(final Coordinate coord) {
    int riga = coord.getRiga() - 1; // Converti da 1-based a 0-based
    int colonna = coord.getColonna() - 1; // Converti da 1-based a 0-based

    // Controlla che riga e colonna siano all'interno dei limiti validi
    if (riga < 0 || riga >= scacchiera.length || colonna < 0 || colonna >= scacchiera[0].length) {
        return false; // O lancia un'eccezione o gestisci l'errore in altro modo
    }

    // Controlla se la posizione contiene una pedina bloccata con simbolo 'X'
    return scacchiera[riga][colonna] != null && scacchiera[riga][colonna].getCarattere() == Costanti.PEDINA_X;
}
    /**
     * Verifica se una casella è bloccata.
     * @param coord La coordinata della casella da verificare.
     * @return true se la casella è bloccata, altrimenti false.
     */
public final boolean isCasellaBloccata(final Coordinate coord) {
    for (Coordinate blocked : caselleBloccateg) {
        if (blocked.getRiga() == coord.getRiga() && blocked.getColonna() == coord.getColonna()) {
            return true;
        }
    }
    return false;
}
   /**
     * Blocca una casella specifica sulla scacchiera.
     * @param coord La coordinata della casella da bloccare.
     * @return true se la casella è stata bloccata con successo, altrimenti false.
     */
    public final boolean bloccaCasella(final Coordinate coord) {

        int riga = coord.getRiga();
        char colonna = (char) (coord.getColonna() + 'a');

        // Verifica se la cella è sulla riga 4 o sulla colonna 'd'
        if ((riga == Costanti.RIGA_4 && (colonna == 'a' || colonna == 'b' || colonna == 'c' || colonna == 'd'
        || colonna == 'e' || colonna == 'f' || colonna == 'g')) || (colonna == 'd'
                        && (riga == 1 || riga == 2 || riga == Costanti.RIGA_3
                        || riga == Costanti.RIGA_4 || riga == Costanti.RIGA_5
                        || riga == Costanti.RIGA_6 || riga == Costanti.RIGA_7))) {
            caselleBloccateg.add(coord);
            return true;
        } else {
            System.out.println("Non è possibile bloccare questa casella per le regole definite.");
            return false;
   }
  }
   /**
   * .
   * @return
   */
  public Pedina[][] getScacchiera() {
    Pedina[][] scacchieraCopy = new Pedina[scacchiera.length][];
    for (int i = 0; i < scacchiera.length; i++) {
        scacchieraCopy[i] = scacchiera[i].clone();
    }
    return scacchieraCopy;
}
/**
 * .
 * @return
 */
public List<Coordinate> getCaselleBloccateg() {
    return new ArrayList<>(caselleBloccateg);
}
}
