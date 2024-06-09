package it.uniba.app.Entity;

import java.util.ArrayList;
import it.uniba.app.Boundary.Costanti;

/**
 * <<Entity>>
 * Classe che gestisce la stampa del tavoliere.
 */
@SuppressWarnings("unused")
public class StampaTavoliere {
    private static final int DIM = 7;
    /** I caratteri che rappresentano le colonne del tavoliere. */
    private final char[] colonne = {'a', 'b', 'c', 'd', 'e', 'f', 'g' };
    private Tavoliere tavoliere;
    private Blocca blocca;
    private Duplicazione mossaa;
    private Salto mossab;

    /**
     * Costruttore della classe StampaTavoliere.
     * @param tavolieres Il tavoliere da stampare.
     * @param bloccas L'istanza di Blocca per gestire le caselle bloccate.
     * @param mossa L'istanza di Mossa per gestire le mosse disponibili.
     */
    public StampaTavoliere(final Tavoliere tavolieres, final Blocca bloccas, final Mossa mossa) {
        this.tavoliere = tavolieres;
        this.blocca = bloccas;
        this.mossaa = new Duplicazione(mossa);
        this.mossab = new Salto(mossa);
    }
    /**
     * Visualizza il tavoliere vuoto.
     */
    @SuppressWarnings("unused")
    public final void visualizzaTavoliereVuoto() {

        // Intestazione colonne
        System.out.print("  ");
        for (char colonna : colonne) {
            System.out.print("   " + Costanti.RED + colonna + Costanti.ANSI_RESET + "  ");
        }
        System.out.println();

        // Corpo del tavoliere
        for (int i = 1; i <= DIM; i++) {
            // Linea di separazione orizzontale
            System.out.print("  ");
            for (char colonna : colonne) {
                System.out.print(Costanti.RED + Costanti.LINE_SEPARATOR + Costanti.ANSI_RESET);
            }
            System.out.println(Costanti.RED + "+" + Costanti.ANSI_RESET);

            // Riga con celle vuote
            System.out.print(Costanti.BLU + i + " " + Costanti.ANSI_RESET); // Numero di riga a sinistra in blu
            for (char colonna : colonne) {
                System.out.print(Costanti.RED + "|  " + Costanti.ANSI_RESET);
                System.out.print(".  "); // Celle vuote con punti
            }
            System.out.println(Costanti.RED + "| " + Costanti.BLU + i
            + Costanti.ANSI_RESET); // Numero di riga a destra in blu
        }

        // Stampa le intestazioni delle colonne finali
        stampaSeparatoreRiga(Costanti.RED, Costanti.ANSI_RESET);
        stampaIntestazioneColonne(Costanti.RED, Costanti.ANSI_RESET);
        System.out.println(Costanti.SEPARATORE + Costanti.ANSI_RESET);
        System.out.println();
    }
    /**
     * Visualizza il tavoliere con le pedine posizionate.
     */
    public final void visualizzaTavolierePieno() {

        // Stampa le intestazioni delle colonne
        stampaIntestazioneColonne(Costanti.RED, Costanti.ANSI_RESET);

        for (int i = 1; i <= DIM; i++) {
            // Stampa separatore di riga
            stampaSeparatoreRiga(Costanti.RED, Costanti.ANSI_RESET);

            // Stampa numero di riga a sinistra in blu
            System.out.print(Costanti.BLU + i + " " + Costanti.ANSI_RESET);

            for (char colonna : colonne) {
                System.out.print(Costanti.RED + "|  " + Costanti.ANSI_RESET);
                Coordinate coord = new Coordinate(i, colonna - 'a');
                Pedina pedina = tavoliere.getPedina(i, colonna);
                if (blocca.isCasellaBloccata(coord)) {
                    Pedina pedinaBloccata = tavoliere.getPedina(i, colonna);
                    if (pedinaBloccata != null) {
                        if (pedinaBloccata.getCarattere() == Costanti.PEDINA_X) {
                            System.out.print(Costanti.ANSI_WHITE + "  " + Costanti.ANSI_RESET + " ");
                        }
                    } else {
                        System.out.print(Costanti.RED + "Y  " + Costanti.ANSI_RESET); // Stampa Y se la casella è
                                                                                      // bloccata ma non c'è una pedina
                    }
                } else if (pedina != null) {
                    System.out.print(pedina.getCarattere() + "  ");
                } else {
                    System.out.print(".  ");
                }
            }
            // Stampa numero di riga a destra in blu
            System.out.println(Costanti.RED + "| " + Costanti.BLU + i + Costanti.ANSI_RESET);
        }

        // Stampa separatore di riga finale
        stampaSeparatoreRiga(Costanti.RED, Costanti.ANSI_RESET);

        // Stampa le intestazioni delle colonne finali
        stampaIntestazioneColonne(Costanti.RED, Costanti.ANSI_RESET);
        System.out.println(Costanti.SEPARATORE + Costanti.ANSI_RESET);
        System.out.println();
    }
   /**
     * Stampa le mosse disponibili per il giocatore corrente.
     * @param giocatoreCorrente Il giocatore corrente.
     */
    @SuppressWarnings("unused")
    public final void stampaMosseDisponibili(final Giocatore giocatoreCorrente) {

        // Inizializza le pedine del tavoliere
        Pedina[][] tabelloneStampato = new Pedina[DIM][DIM];
        boolean[][] pedineRaggiunteDaA = new boolean[DIM][DIM];
        boolean[][] pedineRaggiunteDaB = new boolean[DIM][DIM];

        // Aggiorna il modello di dati del tabellone
        for (int riga = 0; riga < DIM; riga++) {
            for (int colonna = 0; colonna < DIM; colonna++) {
                tabelloneStampato[riga][colonna] = null; // Pulisci il tabellone
                Pedina pedina = tavoliere.getPedina(riga + 1, (char) ('a' + colonna));
                if (pedina != null) {
                    tabelloneStampato[riga][colonna] = new Pedina(pedina);
                }
            }
        }

        // Aggiungi le caselle bloccate al tabellone stampato
        for (int riga = 1; riga <= DIM; riga++) {
            for (char colonna : colonne) {
                Coordinate coord = new Coordinate(riga, colonna);
                if (blocca.isCasellaBloccata(coord)) {
                    tabelloneStampato[riga - 1][colonna - 'a'] = new Pedina(Costanti.PEDINA_X, coord);
                }
            }
        }

        // Aggiungi le mosse disponibili sul tabellone stampato
        for (int riga = 1; riga <= DIM; riga++) {
            for (char colonna : colonne) {
                Pedina pedina = tavoliere.getPedina(riga, colonna);
                if (pedina != null && pedina.getCarattere().equals(giocatoreCorrente.getPedina().getCarattere())) {
                    ArrayList<Coordinate> mossea = mossaa.mosseA(riga, colonna);
                    ArrayList<Coordinate> mosseb = mossab.mosseB(riga, colonna);
                    for (Coordinate mossa : mossea) {
                        int rigaMossa = mossa.getRiga() - 1;
                        int colonnaMossa = mossa.getColonna() - 'a';
                        if (rigaMossa >= 0 && rigaMossa < DIM && colonnaMossa >= 0 && colonnaMossa < DIM) {
                            // Ensure the move doesn't override the current player's piece or blocked cells
                            if (tabelloneStampato[rigaMossa][colonnaMossa] == null) {
                                tabelloneStampato[rigaMossa][colonnaMossa] = new Pedina("AA", mossa);
                            }
                            pedineRaggiunteDaA[rigaMossa][colonnaMossa] = true;
                        }
                    }
                    for (Coordinate mossa : mosseb) {
                        int rigaMossa = mossa.getRiga() - 1;
                        int colonnaMossa = mossa.getColonna() - 'a';
                        if (rigaMossa >= 0 && rigaMossa < DIM && colonnaMossa >= 0 && colonnaMossa < DIM) {
                            // Ensure the move doesn't override the current player's piece or blocked cells
                            if (tabelloneStampato[rigaMossa][colonnaMossa] == null) {
                                tabelloneStampato[rigaMossa][colonnaMossa] = new Pedina("BB", mossa);
                            } else if (tabelloneStampato[rigaMossa][colonnaMossa].getCarattere() == "AA") {
                                tabelloneStampato[rigaMossa][colonnaMossa] = new Pedina("CC", mossa);
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
                if (tabelloneStampato[i][j] != null && (tabelloneStampato[i][j].getCarattere().equals(Costanti.PEDINA_X)
                || tabelloneStampato[i][j].getCarattere().equals(giocatoreCorrente.getPedina().getCarattere()))) {
                    continue; // Skip blocked cells and current player's pieces
                }
                if (pedineRaggiunteDaA[i][j] && pedineRaggiunteDaB[i][j]) {
                    tabelloneStampato[i][j] = new Pedina("CC", new Coordinate(i + 1, (char) ('a' + j)));
                }
            }
        }

        // Stampa l'intestazione delle colonne
        System.out.print("  ");
        for (char colonna : colonne) {
            System.out.print("   " + Costanti.RED + colonna + Costanti.ANSI_RESET + "  ");
        }
        System.out.println();

        // Stampa il tabellone con le mosse disponibili
        for (int i = 1; i <= DIM; i++) {
            System.out.print("  ");
            for (char colonna : colonne) {
                System.out.print(Costanti.RED + "+-----" + Costanti.ANSI_RESET);
            }
            System.out.println(Costanti.RED + "+" + Costanti.ANSI_RESET);
            System.out.print(Costanti.BLU + i + " " + Costanti.ANSI_RESET);
            for (char colonna : colonne) {
                System.out.print(Costanti.RED + "|  " + Costanti.ANSI_RESET);
                Pedina pedina = tabelloneStampato[i - 1][colonna - 'a'];
                if (pedina != null) {
                    if (pedina.getCarattere() == Costanti.PEDINA_X) {
                        System.out.print(Costanti.ANSI_WHITE + "   " + Costanti.ANSI_RESET);
                    } else if (pedina.getCarattere() == "AA") {
                        System.out.print(Costanti.ANSI_YELLOW + "   " + Costanti.ANSI_RESET);
                    } else if (pedina.getCarattere() == "BB") {
                        System.out.print(Costanti.ANSI_ORANGE + "   " + Costanti.ANSI_RESET);
                    } else if (pedina.getCarattere() == "CC") {
                        System.out.print(Costanti.ANSI_PURPLE + "   " + Costanti.ANSI_RESET);
                    } else {
                        System.out.print(pedina.getCarattere() + "  ");
                    }
                } else {
                    System.out.print(".  ");
                }
            }
System.out.println(Costanti.RED + "| " + Costanti.ANSI_RESET + Costanti.BLU + i + " " + Costanti.ANSI_RESET);
        }

        // Stampa la chiusura delle righe del tabellone
        System.out.print("  ");
        for (char colonna : colonne) {
            System.out.print(Costanti.RED + "+-----" + Costanti.ANSI_RESET);
        }
        System.out.println(Costanti.RED + "+" + Costanti.ANSI_RESET);

        // Stampa le intestazioni delle colonne finali
        System.out.print("  ");
        for (char colonna : colonne) {
            System.out.print("   " + Costanti.RED + colonna + Costanti.ANSI_RESET + "  ");
        }
        System.out.println(Costanti.SEPARATORE + Costanti.ANSI_RESET);
        System.out.println(Costanti.MENU_MOSSE);
        System.out.println(Costanti.SEPARATORE_2 + Costanti.ANSI_RESET);
        System.out.println();
    }
 /**
     * Stampa un separatore orizzontale tra le righe del tavoliere.
     * @param color Il colore del testo.
     * @param reset Il reset del colore del testo.
     */
    @SuppressWarnings("unused")
    private void stampaSeparatoreRiga(final String color, final String reset) {
        System.out.print("  ");
        for (char colonna : colonne) {
            System.out.print(color + "+-----" + reset);
        }
        System.out.println(color + "+" + reset);
    }
  /**
     * Stampa le intestazioni delle colonne del tavoliere.
     * @param color Il colore del testo.
     * @param reset Il reset del colore del testo.
     */
    private void stampaIntestazioneColonne(final String color, final String reset) {
    System.out.print("  ");
    for (char colonna : colonne) {
        System.out.print("   " + color + colonna + reset + "  ");
    }
    System.out.println();

}
}
