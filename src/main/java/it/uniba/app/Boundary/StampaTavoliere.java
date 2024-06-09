package it.uniba.app.Boundary;

import it.uniba.app.Entity.Blocca;
import it.uniba.app.Entity.Coordinate;
import it.uniba.app.Entity.Duplicazione;
import it.uniba.app.Entity.Giocatore;
import it.uniba.app.Entity.Mossa;
import it.uniba.app.Entity.Pedina;
import it.uniba.app.Entity.Salto;
import it.uniba.app.Entity.Tavoliere;
import java.util.ArrayList;

/**
 * <<Boundary>>
 * Classe che gestisce la stampa del tavoliere.
 */
public final class StampaTavoliere {
    private static final int DIM = 7;
    private static final char[] COLONNE = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
    // Private constructor to prevent instantiation
    private StampaTavoliere() {
    }

    /**
     * Visualizza il tavoliere vuoto.
     */
    @SuppressWarnings("unused")
    public static void visualizzaTavoliereVuoto() {
        // Intestazione colonne
        System.out.print("  ");
        for (char colonna : COLONNE) {
            System.out.print("   " + Costanti.RED + colonna + Costanti.ANSI_RESET + "  ");
        }
        System.out.println();

        // Corpo del tavoliere
        for (int i = 1; i <= DIM; i++) {
            // Linea di separazione orizzontale
            System.out.print("  ");
            for (char colonna : COLONNE) {
                System.out.print(Costanti.RED + Costanti.LINE_SEPARATOR + Costanti.ANSI_RESET);
            }
            System.out.println(Costanti.RED + "+" + Costanti.ANSI_RESET);

            // Riga con celle vuote
            System.out.print(Costanti.BLU + i + " " + Costanti.ANSI_RESET); // Numero di riga a sinistra in blu
            for (char colonna : COLONNE) {
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
    public static void visualizzaTavolierePieno(final Tavoliere tavoliere, final Blocca blocca) {
        // Stampa le intestazioni delle colonne
        stampaIntestazioneColonne(Costanti.RED, Costanti.ANSI_RESET);

        for (int i = 1; i <= DIM; i++) {
            // Stampa separatore di riga
            stampaSeparatoreRiga(Costanti.RED, Costanti.ANSI_RESET);

            // Stampa numero di riga a sinistra in blu
            System.out.print(Costanti.BLU + i + " " + Costanti.ANSI_RESET);

            for (char colonna : COLONNE) {
                System.out.print(Costanti.RED + "|  " + Costanti.ANSI_RESET);
                Coordinate coord = new Coordinate(i, colonna - 'a');
                Pedina pedina = tavoliere.getPedina(i, colonna);
                if (pedina != null) {
                    if (blocca.isCasellaBloccata(coord) && pedina.getCarattere().equals(Costanti.PEDINA_X)) {
                        System.out.print(Costanti.ANSI_WHITE + "  " + Costanti.ANSI_RESET + " ");
                    } else {
                        System.out.print(pedina.getCarattere() + "  ");
                    }
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
     *
     * @param giocatoreCorrente Il giocatore corrente.
     * @param tavoliere         Il tavoliere di gioco.
     * @param blocca            L'oggetto Blocca per gestire le caselle bloccate.
     * @param mossa             L'oggetto Mossa per gestire le mosse disponibili.
     */
    @SuppressWarnings("unused")
    public static void stampaMosseDisponibili(final Giocatore giocatoreCorrente, final Tavoliere tavoliere,
                                          final Blocca blocca, final Mossa mossa) {
    // Inizializza le mosse
    Duplicazione mossaa = new Duplicazione(mossa);
    Salto mossab = new Salto(mossa);
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
        for (char colonna : COLONNE) {
            Coordinate coord = new Coordinate(riga, colonna);
            if (blocca.isCasellaBloccata(coord)) {
                tabelloneStampato[riga - 1][colonna - 'a'] = new Pedina(Costanti.PEDINA_X, coord);
            }
        }
    }

    // Aggiungi le mosse disponibili sul tabellone stampato
    for (int riga = 1; riga <= DIM; riga++) {
        for (char colonna : COLONNE) {
            Pedina pedina = tavoliere.getPedina(riga, colonna);
            if (pedina != null && pedina.getCarattere().equals(giocatoreCorrente.getPedina().getCarattere())) {
                ArrayList<Coordinate> mossea = mossaa.mosseA(riga, colonna);
                ArrayList<Coordinate> mosseb = mossab.mosseB(riga, colonna);
                for (Coordinate mossaA : mossea) {
                    int rigaMossaA = mossaA.getRiga() - 1;
                    int colonnaMossaA = mossaA.getColonna() - 'a';
                    if (rigaMossaA >= 0 && rigaMossaA < DIM && colonnaMossaA >= 0 && colonnaMossaA < DIM) {
                        // Assicurati che la mossa non sovrascriva nessuna pedina o casella bloccata
                        if (tabelloneStampato[rigaMossaA][colonnaMossaA] == null) {
                            pedineRaggiunteDaA[rigaMossaA][colonnaMossaA] = true;
                        }
                    }
                }
                for (Coordinate mossaB : mosseb) {
                    int rigaMossaB = mossaB.getRiga() - 1;
                    int colonnaMossaB = mossaB.getColonna() - 'a';
                    if (rigaMossaB >= 0 && rigaMossaB < DIM && colonnaMossaB >= 0 && colonnaMossaB < DIM) {
                        // Assicurati che la mossa non sovrascriva nessuna pedina o casella bloccata
                        if (tabelloneStampato[rigaMossaB][colonnaMossaB] == null) {
                            pedineRaggiunteDaB[rigaMossaB][colonnaMossaB] = true;
                        }
                    }
                }
            }
        }
    }

    // Aggiungi le mosse di tipo A e B sul tabellone stampato
    for (int riga = 0; riga < DIM; riga++) {
        for (int colonna = 0; colonna < DIM; colonna++) {
            if (pedineRaggiunteDaA[riga][colonna] && pedineRaggiunteDaB[riga][colonna]) {
                tabelloneStampato[riga][colonna] = new Pedina("CC", new Coordinate(riga + 1, (char) ('a' + colonna)));
            } else if (pedineRaggiunteDaA[riga][colonna]) {
                tabelloneStampato[riga][colonna] = new Pedina("AA", new Coordinate(riga + 1, (char) ('a' + colonna)));
            } else if (pedineRaggiunteDaB[riga][colonna]) {
                tabelloneStampato[riga][colonna] = new Pedina("BB", new Coordinate(riga + 1, (char) ('a' + colonna)));
            }
        }
    }

    // Stampa l'intestazione delle colonne
    System.out.print("  ");
    for (char colonna : COLONNE) {
        System.out.print("   " + Costanti.RED + colonna + Costanti.ANSI_RESET + "  ");
    }
    System.out.println();

    // Stampa il tabellone con le mosse disponibili
    for (int i = 1; i <= DIM; i++) {
        System.out.print("  ");
        for (char colonna : COLONNE) {
            System.out.print(Costanti.RED + "+-----" + Costanti.ANSI_RESET);
        }
        System.out.println(Costanti.RED + "+" + Costanti.ANSI_RESET);
        System.out.print(Costanti.BLU + i + " " + Costanti.ANSI_RESET);
        for (char colonna : COLONNE) {
            System.out.print(Costanti.RED + "|  " + Costanti.ANSI_RESET);
            Pedina pedina = tabelloneStampato[i - 1][colonna - 'a'];
            if (pedina != null) {
                if (pedina.getCarattere().equals(Costanti.PEDINA_X)) {
                    System.out.print(Costanti.ANSI_WHITE + "   " + Costanti.ANSI_RESET);
                } else if (pedina.getCarattere().equals("AA")) {
                    System.out.print(Costanti.ANSI_YELLOW + "   " + Costanti.ANSI_RESET);
                } else if (pedina.getCarattere().equals("BB")) {
                    System.out.print(Costanti.ANSI_ORANGE + "   " + Costanti.ANSI_RESET);
                } else if (pedina.getCarattere().equals("CC")) {
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
    for (char colonna : COLONNE) {
        System.out.print(Costanti.RED + "+-----" + Costanti.ANSI_RESET);
    }
    System.out.println(Costanti.RED + "+" + Costanti.ANSI_RESET);

    // Stampa le intestazioni delle colonne finali
    System.out.print("  ");
    for (char colonna : COLONNE) {
        System.out.print("   " + Costanti.RED + colonna + Costanti.ANSI_RESET + "  ");
    }
    System.out.println(Costanti.SEPARATORE + Costanti.ANSI_RESET);
    System.out.println(Costanti.MENU_MOSSE);
    System.out.println(Costanti.SEPARATORE_2 + Costanti.ANSI_RESET);
    System.out.println();
}
    /**
     * Stampa le intestazioni delle colonne.
     */
    private static void stampaIntestazioneColonne(final String color, final String reset) {
        System.out.print("  ");
        for (char colonna : COLONNE) {
            System.out.print("   " + color + colonna + reset + "  ");
        }
        System.out.println();
    }

    /**
     * Stampa il separatore di riga.
     */
    private static void stampaSeparatoreRiga(final String color, final String reset) {
        System.out.print("  ");
        for (@SuppressWarnings("unused") char colonna : COLONNE) {
            System.out.print(color + Costanti.LINE_SEPARATOR + reset);
        }
        System.out.println(color + "+" + reset);
    }
}
