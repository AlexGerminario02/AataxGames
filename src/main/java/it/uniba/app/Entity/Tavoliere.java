package it.uniba.app.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.uniba.app.Boundary.Costanti;

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
    public static final String ANSI_GRAY_BACKGROUND = "\u001B[47m";
    public static final String ANSI_YELLOW = "\u001B[48;2;255;255;0m";
    public static final String ANSI_ORANGE = "\u001B[48;2;255;165;0m";
    public static final String ANSI_PURPLE = "\u001B[45m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[47m";

    // Costanti per i caratteri dei giocatori
    private static final char PEDINA_ROSSO = 'R'; // 'N'
    private static final char PEDINA_NERA = 'N'; // 'B'

    // Attributi del tavoliere

    /** La matrice che rappresenta lo stato del tavoliere. */
    private Pedina[][] scacchiera;

    private List<Coordinate> caselleBloccate;

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
     * Posiziona una pedina sulla scacchiera.
     * @param pedina  La pedina da posizionare.
     * @param riga    La riga in cui posizionare la pedina.
     * @param colonna La colonna in cui posizionare la pedina.
     * @return true se la pedina è stata posizionata con successo, false altrimenti.
     */
    public boolean setPedina(final Pedina pedina, final int riga, final int colonna) {
        if (colonna < 1 || colonna > DIM || riga < 1 || riga > DIM) {
            return false; // Posizione non valida
        }

        if (pedina == null) {
            scacchiera[riga - 1][colonna - 1] = null;
            return true;
        } else if (scacchiera[riga - 1][colonna - 1] != null) {
            return false; // Posizione occupata
        }

        scacchiera[riga - 1][colonna - 1] = pedina;
        pedina.setCoordinate(new Coordinate(riga, colonna));
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
     * Inizializzazione le pedine nel Tavoliere.
     * @param rigaIniziale
     * @param colonnaIniziale
     * @param rigaFinale
     * @param colonnaFinale
     */
   public void inizializzaPedine(final int rigaIniziale, final int colonnaIniziale, final int rigaFinale,
            final int colonnaFinale) {
        setPedina(new Pedina(PEDINA_NERA, new Coordinate(rigaIniziale, colonnaIniziale)), rigaIniziale,
                colonnaIniziale);
        setPedina(new Pedina(PEDINA_ROSSO, new Coordinate(rigaIniziale, colonnaFinale)), rigaIniziale,
                colonnaFinale);
        setPedina(new Pedina(PEDINA_NERA, new Coordinate(rigaFinale, colonnaFinale)), rigaFinale,
                colonnaFinale);
        setPedina(new Pedina(PEDINA_ROSSO, new Coordinate(rigaFinale, colonnaIniziale)), rigaFinale,
                colonnaIniziale);
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
     public ArrayList<Coordinate> mosseB(final int riga, final int colonna) {
        ArrayList<Coordinate> mosse = new ArrayList<>();
        int[] deltaRighe = {MENODUE, MENODUE, MENODUE, -1, 0, 0, 1, 1, DUE, DUE, DUE, MENODUE, DUE, DUE, DUE, DUE, -1,
                MENODUE };
        int[] deltaColonne = {MENODUE, -1, 1, MENODUE, MENODUE, DUE, MENODUE, DUE, -1, 0, 1, 0, MENODUE, 0, 1, DUE,
                DUE, DUE };
        for (int i = 0; i < deltaRighe.length; i++) {
            int nuovaRiga = riga + deltaRighe[i];
            char nuovaColonna = (char) (colonna + deltaColonne[i]);
            Coordinate nuovaCoordinata = new Coordinate(nuovaRiga, nuovaColonna);
            if (nuovaRiga >= 1 && nuovaRiga <= DIM && Arrays.binarySearch(colonne, nuovaColonna) >= 0
                    && posizioneVuota(nuovaRiga, nuovaColonna)) {
                mosse.add(nuovaCoordinata);
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
     * Stampa le mosse disponibili sul tavoliere per il giocatore corrente.
     *
     * @param giocatoreCorrente Il giocatore corrente.
     */
    public void stampaMosseDisponibili(final Giocatore giocatoreCorrente) {
        // Inizializza le pedine del tavoliere
        Pedina[][] tabelloneStampato = new Pedina[DIM][DIM];
        boolean[][] pedineRaggiunteDaA = new boolean[DIM][DIM];
        boolean[][] pedineRaggiunteDaB = new boolean[DIM][DIM];

        // Aggiungi le pedine al tavoliere stampato
        for (int riga = 0; riga < DIM; riga++) {
            for (int colonna = 0; colonna < DIM; colonna++) {
                Pedina pedina = getPedina(riga + 1, (char) ('a' + colonna));
                if (pedina != null) {
                    tabelloneStampato[riga][colonna] = new Pedina(pedina);
                }
            }
        }

        // Aggiungi le mosse disponibili sul tavoliere stampato
        for (int riga = 1; riga <= DIM; riga++) {
            for (char colonna : colonne) {
                Pedina pedina = getPedina(riga, colonna);
                if (pedina != null && pedina.getCarattere() == giocatoreCorrente.getPedina().getCarattere()) {
                    ArrayList<Coordinate> mossea = mosseA(riga, colonna);
                    ArrayList<Coordinate> mosseb = mosseB(riga, colonna);
                    for (Coordinate mossa : mossea) {
                        int rigaMossa = mossa.getRiga() - 1;
                        int colonnaMossa = mossa.getColonna() - 'a';
                        if (rigaMossa >= 0 && rigaMossa < DIM && colonnaMossa >= 0 && colonnaMossa < DIM) {
                            tabelloneStampato[rigaMossa][colonnaMossa] = new Pedina('A', mossa);
                            pedineRaggiunteDaA[rigaMossa][colonnaMossa] = true;
                        }
                    }
                    for (Coordinate mossa : mosseb) {
                        int rigaMossa = mossa.getRiga() - 1;
                        int colonnaMossa = mossa.getColonna() - 'a';
                        if (rigaMossa >= 0 && rigaMossa < DIM && colonnaMossa >= 0 && colonnaMossa < DIM) {
                            if (!pedineRaggiunteDaA[rigaMossa][colonnaMossa]) {
                                if (tabelloneStampato[rigaMossa][colonnaMossa] == null) {
                                    tabelloneStampato[rigaMossa][colonnaMossa] = new Pedina('B', mossa);
                                } else if (tabelloneStampato[rigaMossa][colonnaMossa].getCarattere() == 'C') {
                                    tabelloneStampato[rigaMossa][colonnaMossa] = new Pedina('B', mossa);
                                }
                            }
                            pedineRaggiunteDaB[rigaMossa][colonnaMossa] = true;
                        }
                    }
                }
            }
        }

        // Aggiungi la C alle caselle raggiunte sia da mosse di tipo A che di tipo B
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (pedineRaggiunteDaA[i][j] && pedineRaggiunteDaB[i][j]) {
                    tabelloneStampato[i][j] = new Pedina('C', new Coordinate(i + 1, (char) ('a' + j)));
                }
            }
        }

        // Stampa l'intestazione delle colonne
        System.out.print("  ");
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
                    if (pedina.getCarattere() == 'X') {
                        System.out.print(ANSI_WHITE + "  " + ANSI_RESET + " ");
                    } else if (pedina.getCarattere() == 'A') {
                        System.out.print(ANSI_YELLOW + "  " + ANSI_RESET + " ");
                    } else if (pedina.getCarattere() == 'B') {
                        System.out.print(ANSI_ORANGE + "  " + ANSI_RESET + " ");
                    } else if (pedina.getCarattere() == 'C') {
                        System.out.print(ANSI_PURPLE + "  " + ANSI_RESET + " ");
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
        System.out.print("  ");
        for (char colonna : colonne) {
            System.out.print("   " + colonna + "  ");
        }
        System.out.println();
    }


    /**
     * Controlla se la posizione è vuota nel Tavoliere.
     * @param riga
     * @param colonna
     * @return
     */
    public boolean posizioneVuota(final int riga, final int colonna) {
        return scacchiera[riga][colonna] == null;
    }

    /**
     * .
     * @param coordinateBloccata
    */
    public final void inizializzaCaselleBloccate(final Coordinate coordinateBloccata) {
        int riga = coordinateBloccata.getRiga();
        int colonna = coordinateBloccata.getColonna() + 1;
        setPedina(new Pedina(Costanti.PEDINA_X, new Coordinate(riga + 1, colonna + 1)), riga, colonna);

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
    return scacchiera[riga][colonna] != null && scacchiera[riga][colonna].getCarattere() == 'X';
}

    /**
 * .
 * @param coord
 * @return
 */
public final boolean isCasellaBloccata(final Coordinate coord) {
    for (Coordinate blocked : caselleBloccate) {
        if (blocked.getRiga() == coord.getRiga() && blocked.getColonna() == coord.getColonna()) {
            return true;
        }
    }
    return false;
}
}
