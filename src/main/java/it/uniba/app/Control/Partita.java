package it.uniba.app.Control;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import it.uniba.app.Boundary.Menu;
import it.uniba.app.Boundary.Tastiera;
import it.uniba.app.Entity.Coordinate;
import it.uniba.app.Entity.Giocatore;
import it.uniba.app.Entity.Tavoliere;

/**
 * <<Control>>
 *
 * Classe che rappresenta una partita.
 * Gestisce il flusso del gioco e l'interazione con i giocatori.
 */
public class Partita {
    public static final int RIGA = 1;
    public static final int COLONNA = 7;
    public static final char A = 'a';
    public static final char B = 'b';
    public static final int TIME = 6;
    //private static final String MESSAGGIO_GIOCO = "Inserisci coordinate o comandi del menu: ";
    private static final String MSG_ABBANDONA_PARTITA = "Sei sicuro di voler abbandonare la partita? (si/no) \n";
    private static final String CONFERMA_ABBANDONO = " ha abbandonato la partita.";
    private boolean uscitaRichiesta = false;
    private boolean abbandono = false;
    private Giocatore giocatore2;
    private Giocatore giocatore1;
    private Tavoliere tavoliere;
    private Tastiera tastiera;
    private int turno;

    /**
     * Costruttore della classe Partita.
     *
     * @param giocatoret1
     * @param giocatoret2
     * @param tavolieret
     */
    public Partita(final Giocatore giocatoret1, final Giocatore giocatoret2, final Tavoliere tavolieret) {
        this.giocatore1 = new Giocatore(giocatoret1); //  per creare una nuova istanza di Giocatore
        this.giocatore2 = new Giocatore(giocatoret2); //per creare una nuova istanza di Giocatore
        this.tavoliere = new Tavoliere(tavolieret); // ia una copia difensiva dei suoi elementi interni.
        this.tastiera = new Tastiera();
        this.turno = 1;
    }

    /**
     * Funzione che Avvia la partita.
     */

    public boolean avviaPartita() {
        if (uscitaRichiesta) {
            return true;
            // L'uscita è stata richiesta
        }
        if (abbandono) {
           return false;
        }
        tavoliere.inizializzaPedine(RIGA, RIGA, COLONNA, COLONNA);
        tavoliere.visualizzaTavolierePieno();
        String coordinateInput = "";
        while (!partitaFinita() && !uscitaRichiesta && !abbandono) {
            System.out.println("Turno " + (turno % 2 == 1 ? "Giocatore Nero" : "Giocatore Bianco") + ":");
            System.out.println("Inserisci le coordinate (es. a1-a2) o un comando: ");
            coordinateInput = tastiera.readString("Coordinate: ");
            if (coordinateInput.startsWith("/")) {
                gestisciComando(coordinateInput);
            } else if (validaCoordinate(coordinateInput)) {
                gestisciCoordinate(coordinateInput);
            } else {
                System.out.println("Input inserito non valido. Riprova!");
            }
        }

        return partitaFinita();
       // Ritorna true se la partita è finita
    }

 // Metodo per validare la colonna
    private boolean validaColonna(final char colonna) {
    return colonna >= 'a' && colonna <= 'g';
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
                Menu.helpGioco(tastiera);
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
                boolean confermaAbbandono = false;
                while (!confermaAbbandono) {
                    String conferma = tastiera.readString(MSG_ABBANDONA_PARTITA);
                    if (conferma.equalsIgnoreCase("si")) {
                        // Determina quale giocatore ha abbandonato
                        if (tavoliere.getTurno() % 2 == 0) {
                            System.out.println("Il giocatore " + giocatore2.getNome() + CONFERMA_ABBANDONO);
                        } else {
                            System.out.println("Il giocatore " + giocatore1.getNome() + CONFERMA_ABBANDONO);
                        }
                        // Determina il giocatore opposto
                        Giocatore giocatoreOpposto = (tavoliere.getTurno() % 2 == 0) ? giocatore1 : giocatore2;
                        int numeroPedineGiocatoreOpposto = tavoliere
                                .contaPedine(giocatoreOpposto.getPedina().getCarattere(), tavoliere);
                        System.out.println("Il giocatore " + giocatoreOpposto.getNome() + " ha vinto per "
                                + numeroPedineGiocatoreOpposto + " a 0.");
                        abbandono = true;
                        confermaAbbandono = true;
                        try {
                        TimeUnit.SECONDS.sleep(TIME); // Attendi 6 secondi
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Pulisci lo schermo dopo 6 secondi
                    Menu.clearScreen();
                    } else if (conferma.equalsIgnoreCase("no")) {
                        confermaAbbandono = true;
                    } else {
                        System.out.println("Inserisci 'si' per abbandonare la partita o 'no' per continuare.");
                    }
                }
                break;
            case "/esci":
                uscitaRichiesta = Menu.esci(tastiera);
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
    /**
     * Funzione per resettare lo stato della partita.
     */
    public void reset() {
    uscitaRichiesta = false;
    abbandono = false;
    }
}
