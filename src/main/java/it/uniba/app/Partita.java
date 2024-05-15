package it.uniba.app;

import java.util.Scanner;
/**
 * Classe che rappresenta una partita.
 */
public class Partita {

    @SuppressWarnings("unused")
    private Giocatore giocatore2;
    @SuppressWarnings("unused")
    private Giocatore giocatore1;
    @SuppressWarnings("unused")
    private Tavoliere tavoliere;
    @SuppressWarnings("unused")
    private Scanner scanner;

    /**
     * Costruttore della classe Partita.
     * @param giocatoret1
     * @param giocatoret2
     * @param tavolieret
     */

    public Partita(final Giocatore giocatoret1, final Giocatore giocatoret2, final Tavoliere tavolieret) {
        this.tavoliere = tavolieret;
        this.scanner = new Scanner(System.in);
    }
}
