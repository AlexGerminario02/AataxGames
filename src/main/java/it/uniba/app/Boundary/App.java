package it.uniba.app.Boundary;

import it.uniba.app.Control.Partita;
import it.uniba.app.Entity.Giocatore;
import it.uniba.app.Entity.Pedina;
import it.uniba.app.Entity.Tavoliere;

/**
 * <<Boundary>>
 * Main class of the application.
 * Questa classe rappresenta il punto di ingresso dell'applicazione Ataxx.
 *  
 */
public final class App {
    // Definizione delle sequenze di escape ANSI per i colori

    private App() {
        // Costruttore privato per evitare l'instanziazione della classe
    }

    /**
     * Metodo per stampare il benvenuto con i colori.
     */
    private static void stampaBenvenuto() {
        System.out.println(Costanti.BENVENUTO_ASCII);
        System.out.println(Costanti.INTRODUZIONE);
        System.out.println(Costanti.REGOLE);
    }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {

        Tastiera tastiera = new Tastiera();
        Tavoliere tavoliere = Tavoliere.creaTavoliere();
        Giocatore giocatore1 = new Giocatore(new Pedina('X', 0, 0), "Nero");
        Giocatore giocatore2 = new Giocatore(new Pedina('O', 0, 0), "Bianco");
        Partita partita = new Partita(giocatore1, giocatore2, tavoliere);
        boolean isRunning = true;

        // Stampa il messaggio di benvenuto

        while (isRunning) {
            stampaBenvenuto();
            System.out.println(Costanti.MENU_COMANDO_INIZIALE);
            String input = tastiera.readString(Costanti.PROMPT_COMANDO);

            switch (input.toLowerCase()) {
                case "/help":
                case "--help":
                case "-h":
                    Menu.helpPrincipale(tastiera);
                    break;
                case "/esci":
                    if (Menu.esci(tastiera)) {
                        isRunning = false;
                    }
                    break;
                case "/gioca":
                    Menu.clearScreen();
                    tavoliere.inizializzaPedine(Costanti.RIGAI, Costanti.COLONNAI, Costanti.RIGAF, Costanti.COLONNAF);
                    partita.avviaPartita();
                    if (partita.avviaPartita()) {
                        return;
                    } else {
                        partita.reset();
                    }
                    break;
                case "/vuoto":
                    Menu.clearScreen();
                    tavoliere.visualizzaTavoliereVuoto();
                    break;
                default:
                    //System.out.println("Scelta non valida. Premi un pulsante valido\n");
                    Menu.clearScreen();
            }
        }
    }
}
