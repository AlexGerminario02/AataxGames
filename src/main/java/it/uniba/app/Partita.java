package it.uniba.app;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe che rappresenta una partita.
 */
public class Partita {
    public static final int RIGA = 1;
    public static final int COLONNA = 7;
    public static final char A = 'a';
    public static final char B = 'b';
    private static final String MESSAGGIO_GIOCO = "Inserisci coordinate o comandi del menu: ";
    private static final String MSG_ABBANDONA_PARTITA = "Sei sicuro di voler abbandonare la partita? (si/no) \n";
    private static final String CONFERMA_ABBANDONO = " ha abbandonato la partita.";
    //private boolean uscitaRichiesta = false;
    private Giocatore giocatore2;
    private Giocatore giocatore1;
    private Tavoliere tavoliere;
    private Scanner scanner;

    /**
     * Costruttore della classe Partita.
     *
     * @param giocatoret1
     * @param giocatoret2
     * @param tavolieret
     */

    public Partita(final Giocatore giocatoret1, final Giocatore giocatoret2, final Tavoliere tavolieret) {
        this.giocatore1 = giocatoret1;
        this.giocatore2 = giocatoret2;
        this.tavoliere = tavolieret;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Funzione che Avvia la partita.
     */

    public void avviaPartita() {
        tavoliere.visualizzaTavolierePieno();
        while (!partitaFinita()) {
            System.out.print(MESSAGGIO_GIOCO);
            String input = scanner.nextLine().trim();
            if (input.startsWith("/") || input.startsWith("-") || input.startsWith("--")) {
                // Gestisci i comandi del menu
                gestisciComando(input);
            } else {
                // Gestisci le coordinate inserite dall'utente
                gestisciCoordinate(input);
            }

        }

        scanner.close();
    }

    private boolean partitaFinita() {
        // Implementa la logica per determinare se la partita è finita
        return false;
    }

    /**
     * Funzione che gestisce i comandi del menu.
     *
     * @param comando
     */
    private void gestisciComando(final String comando) {
        // Implementa la logica per gestire i comandi del menu
        switch (comando.toLowerCase()) {
            case "/help":
            case "-h":
            case "--help":
                Menu.help(scanner);
                break;
            case "/vuoto":
                tavoliere.visualizzaTavoliereVuoto();
                break;
            case "/tavoliere":
                tavoliere.visualizzaTavolierePieno();
                break;
            case "/qualimosse":
                // Coordinate delle pedine del giocatore 1 (X)
                Coordinate[] pedineGiocatore1 = {
                        new Coordinate(RIGA, 'a'), // Pedina in cella[1,1]
                        new Coordinate(COLONNA, 'g') // Pedina in cella[7,7]
                };

                ArrayList<Coordinate> mossea = new ArrayList<>();
                ArrayList<Coordinate> mosseb = new ArrayList<>();
                // ArrayList<Coordinate> mossec = new ArrayList<>();
                for (Coordinate pedina : pedineGiocatore1) {
                    mossea.addAll(tavoliere.mosseA(pedina.getRiga(), pedina.getColonna()));
                    mosseb.addAll(tavoliere.mosseB(pedina.getRiga(), pedina.getColonna()));
                    // mossec.addAll(tavoliere.mosseC(pedina.getRiga(), pedina.getColonna()));
                }

                tavoliere.stampaMosseDisponibili(mossea, mosseb);
                break;
            case "/abbandona":
                // qui va messo keyboard al posto di scanner.
                System.out.println(MSG_ABBANDONA_PARTITA);
                String conferma = scanner.nextLine();
                if (conferma.equalsIgnoreCase("si")) {
                    // Determina quale giocatore ha abbandonato
                    if (tavoliere.getTurno() % 2 == 0) {
                        System.out.println("Il giocatore " + giocatore2.getNome() + CONFERMA_ABBANDONO);
                    } else {
                        System.out.println("Il giocatore " + giocatore1.getNome() + CONFERMA_ABBANDONO);
                    }
                    // Determina il giocatore opposto
                    Giocatore giocatoreOpposto = (tavoliere.getTurno() % 2 == 0) ? giocatore1 : giocatore2;
                    int numeroPedineGiocatoreOpposto =
                    tavoliere.contaPedine(giocatoreOpposto.getPedina().getCarattere(), tavoliere);
                    System.out.println("Il giocatore " + giocatoreOpposto.getNome() + " ha vinto per "
                            + numeroPedineGiocatoreOpposto + " a 0.");
                    return;
                }
                break;
                case "/esci":
                uscitaRichiesta = Menu.esci(scanner);
                break;
            default:
                System.out.println("Comando non valido. Riprova.");
        }
    }

    private void gestisciCoordinate(final String coordinate) {
        // Implementa la logica per gestire le coordinate inserite dall'utente
        System.out.println("Funzione da implementare per gestire le coordinate: " + coordinate);
        System.out.println("");

    }
}
