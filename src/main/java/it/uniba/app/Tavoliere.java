package it.uniba.app;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe che rappresenta il tavoliere di gioco.
 */
@SuppressWarnings("unused")

public class Tavoliere {
    public static final int MOVE_UP_LEFT = -2;
    public static final int MOVE_UP_RIGHT = -1;
    public static final int MOVE_RIGHT_UP = 1;
    public static final int MOVE_RIGHT_DOWN = 2;
    public static final int MOVE_DOWN_RIGHT = 2;
    public static final int MOVE_DOWN_LEFT = 1;
    public static final int MOVE_LEFT_DOWN = -1;
    public static final int MOVE_LEFT_UP = -2;
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
    private final char[] colonne = {'a', 'b', 'c', 'd', 'e', 'f', 'g' };

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

    /**
     * Controlla se la posizione specificata sul tavoliere è vuota.
     *
     * @param riga    la riga della posizione
     * @param colonna la colonna della posizione (come carattere)
     * @return true se la posizione è vuota, false altrimenti
     */
    public boolean posizioneVuota(final int riga, final char colonna) {
        return getPedina(riga, colonna) == null;
    }

    /**
     * Visualizza il tavoliere pieno con le coordinate sulle righe e sui numeri.
     */
    public void visualizzaTavolierePieno() {
        System.out.print("   ");
        for (char colonna : colonne) {
            System.out.print("   " + colonna + "  ");
        }
        System.out.println();
        for (int i = 1; i <= DIM; i++) {
            System.out.print("  ");
            for (char colonna : colonne) {
                System.out.print(LINE_SEPARATOR);
            }
            System.out.println("+");
            System.out.print(i + " ");
            for (char colonna : colonne) {
                System.out.print("|  ");
                Pedina pedina = getPedina(i, colonna);
                if (pedina != null) {
                    System.out.print(pedina.getCarattere() + "  ");
                } else {
                    System.out.print(".  ");
                }
            }
            System.out.println("| " + i); // Numero di riga a destra
        }
        System.out.print("  ");
        for (char colonna : colonne) {
            System.out.print(LINE_SEPARATOR);
        }
        System.out.println("+");
        System.out.print("   ");
        for (char colonna : colonne) {
            System.out.print("   " + colonna + "  ");
        }
        System.out.println();
    }

    /**
     * Visualizza il tavoliere vuoto con le coordinate sulle righe e sui numeri.
     */

    public void visualizzaTavoliereVuoto() {
        System.out.print("    ");
        for (char colonna : colonne) {
            System.out.print("   " + colonna + "  ");
        }
        System.out.println();
        for (int i = 1; i <= DIM; i++) {
            System.out.print("  ");
            for (char colonna : colonne) {
                System.out.print(LINE_SEPARATOR);
            }
            System.out.println("+");
            System.out.print(i + " ");
            for (char colonna : colonne) {
                System.out.print(EMPTY_CELL);
            }
            System.out.println("| " + i); // Numero di riga a destra
        }
        System.out.print("  ");
        for (char colonna : colonne) {
            System.out.print(LINE_SEPARATOR);
        }
        System.out.println("+");
        System.out.print("    ");
        for (char colonna : colonne) {
            System.out.print("   " + colonna + "  ");
        }
        System.out.println();
    }

    /**
     * Visualizza il tavoliere vuoto con le coordinate sulle righe e sui numeri.
     */
    public final void inizializzaPedine() {
        int i = 0;
        // Posiziona una pedina 'X' nella prima riga e nella prima colonna
        setPedina(new Pedina('X', i + 1, i + 1), i + 1, colonne[0]);
        // Posiziona una pedina 'O' nella prima riga e nell'ultima colonna
        setPedina(new Pedina('O', i + 1, DIM - i), i + 1, colonne[DIM - 1]);
        int j = DIM;
        // Posiziona una pedina 'X' nell'ultima riga e nell'ultima colonna
        setPedina(new Pedina('X', j, DIM - i), DIM, colonne[DIM - 1]);
        // Posiziona una pedina 'O' nell'ultima riga e nella prima colonna
        setPedina(new Pedina('O', DIM, i + 1), DIM, colonne[0]);

    }

    /**
     * Restituice il numero di mosse di tipo A disponibili nel gioco.
     *
     * @return le mosse di tipo A disponibili
     */
    public ArrayList<Coordinate> mosseA(final int riga, final char colonna) {
        ArrayList<Coordinate> mosse = new ArrayList<>();
        int[] deltaRighe = {-1, -1, -1, 0, 0, 1, 1, 1 };
        int[] deltaColonne = {-1, 0, 1, -1, 1, -1, 0, 1 };
        for (int i = 0; i < deltaRighe.length; i++) {
            int nuovaRiga = riga + deltaRighe[i];
            char nuovaColonna = (char) (colonna + deltaColonne[i]);
            if (nuovaRiga >= 1 && nuovaRiga <= DIM && Arrays.binarySearch(colonne,
                    nuovaColonna) >= 0
                    && posizioneVuota(nuovaRiga, nuovaColonna)) {
                mosse.add(new Coordinate(nuovaRiga, nuovaColonna));
            }
        }
        return mosse;
    }

    /**
     * Restituice il numero di mosse di tipo B disponibili nel gioco.
     *
     * @return le mosse di tipo B disponibili
     */

     public ArrayList<Coordinate> mosseB(final int riga, final char colonna) {
        ArrayList<Coordinate> mosse = new ArrayList<>();
        // Coordinate delle caselle adiacenti (in orizzontale, verticale e diagonale)
        int[] deltaRighe = {
            MOVE_UP_LEFT, MOVE_UP_LEFT, MOVE_UP_LEFT, MOVE_RIGHT_UP, MOVE_RIGHT_DOWN,
            MOVE_RIGHT_DOWN, MOVE_DOWN_RIGHT, MOVE_DOWN_LEFT, MOVE_DOWN_LEFT,
            MOVE_LEFT_DOWN, MOVE_LEFT_DOWN, MOVE_LEFT_DOWN, MOVE_UP_LEFT
        };
        int[] deltaColonne = {
            MOVE_UP_LEFT, MOVE_RIGHT_UP, MOVE_RIGHT_DOWN, MOVE_RIGHT_DOWN,
            MOVE_RIGHT_DOWN, MOVE_UP_RIGHT, MOVE_UP_LEFT, MOVE_UP_LEFT,
            MOVE_LEFT_DOWN, MOVE_LEFT_DOWN, MOVE_LEFT_DOWN, MOVE_UP_LEFT, MOVE_UP_LEFT
        };
        // Controlla ogni casella adiacente
        for (int i = 0; i < deltaRighe.length; i++) {
            int nuovaRiga = riga + deltaRighe[i];
            char nuovaColonna = (char) (colonna + deltaColonne[i]);
            // Se la casella adiacente è all'interno del tavoliere e vuota, aggiungi la mossa
            if (nuovaRiga >= 1 && nuovaRiga
             <= DIM && Arrays.binarySearch(colonne, nuovaColonna) >= 0 && posizioneVuota(nuovaRiga, nuovaColonna)) {
                mosse.add(new Coordinate(nuovaRiga, nuovaColonna));
            }
        }
        return mosse;
    }
     /**
     * Restituice il numero di mosse di tipo C disponibili nel gioco.
     *
     * @return le mosse di tipo C disponibili
     */

    public ArrayList<Coordinate> mosseC(final int riga, final char colonna) {
        ArrayList<Coordinate> mosseC = new ArrayList<>();
        mosseC.addAll(mosseA(riga, colonna));
        mosseC.addAll(mosseB(riga, colonna));
        return mosseC;
    }
}

