package it.uniba.app;


import it.uniba.app.Boundary.Costanti;
import it.uniba.app.Boundary.Menu;
import it.uniba.app.Boundary.Tastiera;
import it.uniba.app.Control.Partita;
import it.uniba.app.Entity.Giocatore;
import it.uniba.app.Entity.Mossa;
import it.uniba.app.Entity.Pedina;
import it.uniba.app.Entity.Tavoliere;
import it.uniba.app.Entity.Blocca;
import it.uniba.app.Entity.Coordinate;
import it.uniba.app.Entity.Duplicazione;
import it.uniba.app.Entity.Salto;
import it.uniba.app.Entity.StampaTavoliere;

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
    Blocca blocca = new Blocca(tavoliere.getScacchiera(), new ArrayList<>(), tavoliere);
    Duplicazione mossaa = new Duplicazione(tavoliere.getScacchiera(), null);
    Salto mossab = new Salto(tavoliere.getScacchiera(), null);
    Mossa mossa = new Mossa(tavoliere.getScacchiera(), mossaa, mossab);
    StampaTavoliere stamp = new StampaTavoliere(tavoliere, blocca, mossa);
    boolean isRunning = true;
    Partita partita = null;
    List<Coordinate> caselleDaBloccare = new ArrayList<>();
    Menu.stampaBenvenuto();
    System.out.println(Costanti.MENU_COMANDO_INIZIALE);
    while (isRunning) {
        String input = tastiera.readString(Costanti.PROMPT_COMANDO);
        if (input.toLowerCase().startsWith("/blocca")) {
            Menu.clearScreen();
            System.out.println(Costanti.MENU_COMANDO_INIZIALE);
            Partita.gestioneBlocca(input, caselleDaBloccare, tavoliere, blocca);
            System.out.println();
            continue;
        }
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
                Giocatore giocatore1 = new Giocatore(new Pedina('N', new Coordinate(0, 0)), "Nero");
                Giocatore giocatore2 = new Giocatore(new Pedina('R', new Coordinate(0, 0)), "Bianco");
                partita = new Partita(giocatore1, giocatore2, tavoliere, blocca, mossaa, mossab);
                Menu.clearScreen();
                if (partita.avviaPartita(caselleDaBloccare)) {
                    return;
                } else {
                    partita.reset(caselleDaBloccare);
                }
                break;

            case "/vuoto":
                Menu.clearScreen();
                System.out.println(Costanti.GAME);
                stamp.visualizzaTavoliereVuoto();
                break;
            default:
                System.out.println("Scelta non valida. Premi un pulsante valido\n");
                Menu.clearScreen();
        }
    }
}
}
