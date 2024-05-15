package it.uniba.app;

import java.util.Arrays;

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
    // Metodi per gestire il tavoliere

    /**
     * Restituisce la pedina alla posizione specificata sul tavoliere.
     *
     * @param riga    la riga della pedina
     * @param colonna la colonna della pedina (come carattere)
     * @return la pedina alla posizione specificata, null se la posizione è vuota
     */
    public Pedina getPedina(final int riga, final char colonna) {
        int indiceColonna = Arrays.binarySearch(colonne, Character.toLowerCase(colonna));
        if (indiceColonna < 0 || riga < 1 || riga > DIM) {
            return null; // Posizione non valida
        }
        return scacchiera[riga - 1][indiceColonna];
    }

    /**
     * Imposta la pedina alla posizione specificata sul tavoliere.
     *
     * @param pedina  la pedina da impostare
     * @param riga    la riga della pedina
     * @param colonna la colonna della pedina (come carattere)
     * @return true se la pedina è stata impostata con successo, false altrimenti
     */
    public boolean setPedina(final Pedina pedina, final int riga, final char colonna) {
        int indiceColonna = Arrays.binarySearch(colonne, Character.toLowerCase(colonna));
        if (indiceColonna < 0 || riga < 1 || riga > DIM || scacchiera[riga - 1][indiceColonna] != null) {
            return false; // Posizione non valida o occupata
        }
        scacchiera[riga - 1][indiceColonna] = pedina;
        return true;
    }
}
