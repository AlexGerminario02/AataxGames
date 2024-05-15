package it.uniba.app;

import java.util.Scanner;
/**
 * Classe che rappresenta una partita.
 */
public class Partita {

    private static final String MESSAGGIO_GIOCO = "Inserisci coordinate o comandi del menu: ";

    @SuppressWarnings("unused")
    private Giocatore giocatore2;
    @SuppressWarnings("unused")
    private Giocatore giocatore1;
    private Tavoliere tavoliere;
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
