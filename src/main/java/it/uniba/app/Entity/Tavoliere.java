package it.uniba.app.Entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <<Entity>>
 * Classe che rappresenta il tavoliere di gioco.
 * Questa classe gestisce lo stato del tavoliere, le mosse disponibili e la
 * visualizzazione del tavoliere.
 *  
 */
@SuppressWarnings("unused")

public class Tavoliere {
    public static final int ZERO = 0;
    public static final int UNO = 1;
    public static final int MENOUNO = 1;
    public static final int DUE = 2;
    public static final int MENODUE = -2;
    public static final int RIGAINIZIALE = 1;
    public static final int COLONNAINIZIALE = 1;
    public static final int RIGAFINALE = 7;
    public static final int COLONNAFINALE = 7;
    private static final String LINE_SEPARATOR = "+-----";
    private static final String EMPTY_CELL = "|  .  ";
    private static final String ANSI_YELLOW = "\u001B[38;2;255;255;0m";
    private static final String ANSI_ORANGE = "\u001B[38;2;255;165;0m";

    // Costanti per i caratteri dei giocatori
    private static final char PEDINA_ROSSO = 'R'; // 'N'
    private static final char PEDINA_NERA = 'N'; // 'B'

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
        this.turno = 1;
    }

    /**
     * Costruisce una nuova istanza di Tavoliere copiando i valori da un'altra
     * istanza esistente di Tavoliere.
     * Questo costruttore esegue una copia profonda della scacchiera, creando nuove
     * istanze di Pedina per
     * evitare la condivisione di riferimenti mutabili tra le istanze.
     *
     * @param copia l'istanza di Tavoliere da cui copiare i valori
     */
     public Tavoliere(final Tavoliere copia) {
        int dim = copia.scacchiera.length;
        this.scacchiera = new Pedina[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (copia.scacchiera[i][j] != null) {
                    this.scacchiera[i][j] = new Pedina(copia.scacchiera[i][j]); // Usa il costruttore di copia di Pedina
                }
            }
        }
        this.turno = copia.turno;
    }

    // Metodi per gestire il tavoliere

    /**
     *
     * Conta le pedine di un giocatore.
     */
    public final int contaPedine(final char caratterePedina, final Tavoliere tavoliere) {
        int count = 0;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                Pedina pedina = scacchiera[i][j];
                if (pedina != null && pedina.getCarattere() == caratterePedina) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * Restituisce il numero del turno attuale.
     *
     * @return il numero del turno attuale
     */
    public int getTurno() {
        return turno;
    }

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
    public void inizializzaPedine(final int rigaIniziale, final int colonnaIniziale, final int rigaFinale,
            final int colonnaFinale) {
        // Posiziona una pedina 'X' nella prima riga e nella prima colonna
        setPedina(new Pedina(PEDINA_NERA, rigaIniziale, colonnaIniziale), rigaIniziale, colonne[0]);
        // Posiziona una pedina 'O' nella prima riga e nell'ultima colonna
        setPedina(new Pedina(PEDINA_ROSSO, rigaIniziale, colonnaFinale), rigaIniziale, colonne[DIM - 1]);
        // Posiziona una pedina 'X' nell'ultima riga e nell'ultima colonna
        setPedina(new Pedina(PEDINA_NERA, rigaFinale, colonnaFinale), rigaFinale, colonne[DIM - 1]);
        // Posiziona una pedina 'O' nell'ultima riga e nella prima colonna
        setPedina(new Pedina(PEDINA_ROSSO, rigaFinale, colonnaIniziale), rigaFinale, colonne[0]);
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

    // Modifica alla funzione mosseB
    public ArrayList<Coordinate> mosseB(final int riga, final char colonna) {
        ArrayList<Coordinate> mosse = new ArrayList<>();
        // Coordinate delle caselle adiacenti (in orizzontale, verticale e diagonale)
        int[] deltaRighe = {MENODUE, MENODUE, MENODUE, -1, 0, 0, 1, 1,
                DUE, DUE, DUE, MENODUE, DUE, DUE, DUE, DUE, -1,
                MENODUE }; // Aggiunti ulteriori spostamenti verticali
        int[] deltaColonne = {MENODUE, -1, 1, MENODUE, MENODUE, DUE, MENODUE,
                DUE, -1, 0, 1, 0, MENODUE, 0, 1, DUE,
                DUE, DUE }; // Aggiunti ulteriori spostamenti orizzontali
        // Controlla ogni casella adiacente
        for (int i = 0; i < deltaRighe.length; i++) {
            int nuovaRiga = riga + deltaRighe[i];
            char nuovaColonna = (char) (colonna + deltaColonne[i]);
            // Se la casella adiacente è all'interno del tavoliere e vuota, aggiungi la
            // mossa
            if (nuovaRiga >= 1 && nuovaRiga <= DIM && Arrays.binarySearch(colonne, nuovaColonna) >= 0
                    && posizioneVuota(nuovaRiga, nuovaColonna)) {
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

    /**
     * Stampa le mosse disponibili sul tavoliere.
     *
     * @param mosseA Lista delle mosse disponibili di tipo A.
     * @param mosseB Lista delle mosse disponibili di tipo B.
     */
    public void stampaMosseDisponibili(final ArrayList<Coordinate> mosseA, final ArrayList<Coordinate> mosseB) {
        // Inizializza le pedine del tavoliere
        inizializzaPedine(RIGAINIZIALE, COLONNAINIZIALE, RIGAFINALE, COLONNAFINALE);
        Pedina[][] tabelloneStampato = new Pedina[DIM][DIM];
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (scacchiera[i][j] != null) {
                    tabelloneStampato[i][j] = new Pedina(scacchiera[i][j].getCarattere(), i, j);
                }
            }
        }

        // Segna le mosse disponibili di tipo A sul tavoliere
        for (Coordinate mossa : mosseA) {
            int riga = mossa.getRiga();
            char colonna = mossa.getColonna();
            tabelloneStampato[riga - 1][colonna - 'a'] = new Pedina('A', riga, colonna);
        }

        // Segna le mosse disponibili di tipo B sul tavoliere
        for (Coordinate mossa : mosseB) {
            int riga = mossa.getRiga();
            char colonna = mossa.getColonna();
            tabelloneStampato[riga - 1][colonna - 'a'] = new Pedina('B', riga, colonna);
        }
        // Stampa l'intestazione delle colonne
        System.out.print("    ");
        for (char colonna : colonne) {
            System.out.print("   " + colonna + "  ");
        }
        System.out.println();

        // Stampa il tavoliere con le mosse disponibili
        for (int i = 1; i <= DIM; i++) {
            System.out.print("  ");
            for (char colonna : colonne) {
                System.out.print("+-----");
            }
            System.out.println("+");
            System.out.print(i + " ");
            for (char colonna : colonne) {
                System.out.print("|  ");
                Pedina pedina = tabelloneStampato[i - 1][colonna - 'a'];
                if (pedina != null) {
                    if (pedina.getCarattere() == 'A') {
                        System.out.print(ANSI_YELLOW + pedina.getCarattere() + "\u001B[0m  ");
                    } else if (pedina.getCarattere() == 'B') {
                        System.out.print(ANSI_ORANGE + pedina.getCarattere() + "\u001B[0m  ");
                    } else if (pedina.getCarattere() == 'C') {
                        System.out.print("\u001B[35m" + pedina.getCarattere() + "\u001B[0m  "); // 'Z' in rosa
                    } else {
                        System.out.print(pedina.getCarattere() + "  ");
                    }
                } else {
                    System.out.print(".  ");
                }
            }
            System.out.println("| " + i);
        }

        // Stampa la chiusura delle righe del tavoliere
        System.out.print("  ");
        for (char colonna : colonne) {
            System.out.print("+-----");
        }
        System.out.println("+");

        // Stampa l'intestazione delle colonne in fondo
        System.out.print("    ");
        for (char colonna : colonne) {
            System.out.print("   " + colonna + "  ");
        }
        System.out.println();
    }
}
