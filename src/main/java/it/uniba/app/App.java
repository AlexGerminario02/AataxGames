package it.uniba.app;

import java.util.Scanner;


/**
 * Main class of the application.
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

        Scanner scanner = new Scanner(System.in);
        Tavoliere tavoliere = Tavoliere.creaTavoliere();
        Giocatore giocatore1 = new Giocatore(new Pedina('X', 0, 0), "Nero");
        Giocatore giocatore2 = new Giocatore(new Pedina('O', 0, 0), "Bianco");
        Partita partita = new Partita(giocatore1, giocatore2, tavoliere);
        boolean isRunning = true;



        // Stampa il messaggio di benvenuto


        while (isRunning) {
            stampaBenvenuto();
            System.out.println(Costanti.MENU_COMANDO_INIZIALE);
            System.out.print(Costanti.PROMPT_COMANDO);

            String input = scanner.nextLine().trim(); // inserisco il trim per rimuovere eventuali spazi bianchi

            switch (input.toLowerCase()) {
                case "/help":
                case "--help":
                case "-h":
                    Menu.helpPrincipale(scanner);
                    break;
                case "/esci":
                    if (Menu.esci(scanner)) {
                        isRunning = false;
                    }
                    break;
                case "/gioca":
                    Menu.clearScreen();
                    tavoliere.inizializzaPedine(Costanti.RIGAI, Costanti.COLONNAI, Costanti.RIGAF, Costanti.COLONNAF);
                    partita.avviaPartita();
                    if (partita.avviaPartita()) {
                        return;
                    }
                    break;
                case "/vuoto":
                    Menu.clearScreen();
                    tavoliere.visualizzaTavoliereVuoto();
                    break;
                default:
                    System.out.println("Scelta non valida. Premi un pulsante valido\n");
            }
        }
        scanner.close();
    }
}
