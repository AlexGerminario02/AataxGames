package it.uniba.app.Entity;

import java.util.List;
import it.uniba.app.Boundary.Costanti;


/**
 * <<Entity>>: Classe che rappresenta un blocco di caselle sulla scacchiera.
 */
@SuppressWarnings("unused")
public class Blocca {
    public static final int DIM = 7;
    private Pedina[][] scacchiera;
    private List<Coordinate> caselleBloccateg;
    private Tavoliere tavoliere;

        /**
     * Costruttore della classe Blocca.
     * @param scacchierab La matrice che rappresenta la scacchiera.
     * @param caselleBloccate La lista delle coordinate delle caselle bloccate.
     * @param tavolieret Il tavoliere associato al blocco.
     */
    public Blocca(final Pedina[][] scacchierab, final List<Coordinate> caselleBloccate, final Tavoliere tavolieret) {
        this.scacchiera = scacchierab;
        this.caselleBloccateg = caselleBloccate;
        this.tavoliere = tavolieret;
    }

    /**
     * Resetta le celle bloccate.
     * @param caselleBloccate La lista delle coordinate delle caselle bloccate da resettare.
     */
public void resettaCelleBloccate(final List<Coordinate> caselleBloccate) {
    caselleBloccateg.clear();
}

/**
 * .
 * @param coord
 * @return
 */
public final boolean isBloccata(final Coordinate coord) {
    int riga = coord.getRiga() - 1; // Converti da 1-based a 0-based
    int colonna = coord.getColonna() - 1; // Converti da 1-based a 0-based
    // Controlla se la posizione contiene una pedina bloccata con simbolo 'X'
    return scacchiera[riga][colonna] != null && scacchiera[riga][colonna].getCarattere() == "XX";
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
}
