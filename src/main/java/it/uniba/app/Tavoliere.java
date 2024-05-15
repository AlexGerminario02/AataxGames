package it.uniba.app;

/**
 * Classe che rappresenta il tavoliere di gioco.
 */
@SuppressWarnings("unused")

public class Tavoliere {
private static final String LINE_SEPARATOR = "+-----";
private static final String EMPTY_CELL = "|  .  ";

// Costanti per i caratteri dei giocatori
private static final char PLAYER_X_CHAR = '\u26C0'; // 'X'
private static final char PLAYER_Y_CHAR = '\u26C1'; // 'Y'
    // Attributi del tavoliere

    /** La matrice che rappresenta lo stato del tavoliere. */
    private Pedina[][] scacchiera;

    /** La dimensione del tavoliere (numero di righe e colonne). */
    private static final int DIM = 7;

    /** I caratteri che rappresentano le colonne del tavoliere. */
    private final char[] colonne = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};

    /** Il numero del turno attuale. */
    private int turno;


       /**
     * Crea un nuovo tavoliere con la dimensione predefinita.
     *
     * @return un nuovo tavoliere con una dimensione predefinita
     */
    public static Tavoliere creaTavoliere() {
        return new Tavoliere(DIM);
    }
      /**
     * Costruisce una nuova istanza di Tavoliere con la dimensione specificata.
     *
     * @param dim dimensione del tavoliere (numero di righe e colonne)
     */

     public Tavoliere(final int dim) {
        this.scacchiera = new Pedina[dim][dim];
        this.turno = 0;
    }
}
