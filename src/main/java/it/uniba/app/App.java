package it.uniba.app;


import it.uniba.app.Boundary.Costanti;
import it.uniba.app.Boundary.Menu;
import it.uniba.app.Boundary.Tastiera;
import it.uniba.app.Control.Partita;
import it.uniba.app.Entity.Giocatore;
import it.uniba.app.Entity.Pedina;
import it.uniba.app.Entity.Tavoliere;
import it.uniba.app.Entity.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Costruttore privato per evitare l'istanziazione della classe.
 */
public final class App {

    private App() {
        // Costruttore privato per evitare l'istanziazione della classe
    }

/**
 * Metodo principale che avvia l'applicazione.
 * Questo metodo gestisce il ciclo principale dell'applicazione,
 * visualizza il menu e gestisce i comandi dell'utente.
 *
 * @param args Argomenti della riga di comando (non utilizzati).
 */
    public static void main(final String[] args) {
        Tastiera tastiera = new Tastiera();
        Tavoliere tavoliere = Tavoliere.creaTavoliere();
        boolean isRunning = true;
        Partita partita = null;
        List<Coordinate> caselleDaBloccare = new ArrayList<>();
        Menu.stampaBenvenuto();

        while (isRunning) {
            System.out.println(Costanti.MENU_COMANDO_INIZIALE);
            String input = tastiera.readString(Costanti.PROMPT_COMANDO);
                if (input.toLowerCase().startsWith("/blocca")) {
                    Menu.clearScreen();
                    Partita.gestioneBlocca(input, caselleDaBloccare, tavoliere);
                    System.out.println();
                    continue;
            }
            switch (input.toLowerCase()) {
                case "/help":
                case "--help":
                case "-h":
                    Menu.helpPrincipale(tastiera);
                    Menu.delay(Costanti.TIME7);
                    Menu.clearScreen();
                    break;
                case "/esci":
                    if (Menu.esci(tastiera)) {
                        isRunning = false;
                    }
                    break;

                case "/gioca":
                    Giocatore giocatore1 = new Giocatore(new Pedina('N', new Coordinate(0, 0)), "Nero");
                    Giocatore giocatore2 = new Giocatore(new Pedina('R', new Coordinate(0, 0)), "Rosso");
                    partita = new Partita(giocatore1, giocatore2, tavoliere);
                    Menu.clearScreen();
                    if (partita.avviaPartita(caselleDaBloccare)) {
                        return;
                    } else {
                        partita.reset(caselleDaBloccare);
                    }
                    break;

                    case "/vuoto":
                    Menu.clearScreen();
                    tavoliere.visualizzaTavoliereVuoto();
                    while (true) {
                            Menu.delay(Costanti.TIME5);
                            Menu.clearScreen();
                            break;
                        }
                        break;
                default:
                    System.out.println("Scelta non valida. Premi un pulsante valido\n");
                    Menu.delay(2);
                    Menu.clearScreen();
            }
        }
    }
}
