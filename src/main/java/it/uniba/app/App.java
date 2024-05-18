package it.uniba.app;

import java.util.Scanner;

/**
 * Main class of the application.
 */
public final class App {
    // Definizione delle sequenze di escape ANSI per i colori
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLU = "\u001B[34m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";

    // Costanti per i messaggi del menu
    private static final String BENVENUTO_ASCII = "\n"
    + RED + " ________      # _________  # ________      # __     __     # __     __     #\n"
    + BLU + "/_______/\\     #/________/\\ #/_______/\\     #/__/\\ /__/\\    #/__/\\ /__/\\    #\n"
    + RED + "\\::: _  \\ \\    #\\__.::.__\\/ #\\::: _  \\ \\    #\\ \\::\\\\:.\\ \\   #\\ \\::\\\\:.\\ \\   #\n"
    + BLU + " \\::(_)  \\ \\   #   \\::\\ \\   # \\::(_)  \\ \\   # \\_\\::_\\:_\\/   # \\_\\::_\\:_\\/   #\n"
    + RED + "  \\:: __  \\ \\  #    \\::\\ \\  #  \\:: __  \\ \\  #   _\\/__\\_\\_/\\ #   _\\/__\\_\\_/\\ #\n"
    + BLU + "   \\:.\\ \\  \\ \\ #     \\::\\ \\ #   \\:.\\ \\  \\ \\ #   \\ \\ \\ \\::\\ \\#   \\ \\ \\ \\::\\ \\#\n"
    + RED + "    \\__\\/\\__\\/ #      \\__\\/ #    \\__\\/\\__\\/ #    \\_\\/  \\__\\/ #    \\_\\/  \\__\\/ #\n";


    private static final String INTRODUZIONE = BLU + "> INTRODUZIONE\n" + ANSI_RESET
            + "Ataxx è un avvincente gioco da tavolo strategico per due giocatori"
            + "progettato per essere giocato in modalità locale."
            + "Nato nei primi anni '90, è diventato un classico grazie alla combinazione"
            + "di regole semplici e profondità tattica.\n";
    private static final String REGOLE = BLU + "> REGOLE\n" + ANSI_RESET
            + "Il gioco si svolge su una griglia di 7x7 caselle. Ogni giocatore inizia"
            + "con due pedine disposte agli angoli opposti della griglia. "
            + "L'obiettivo è controllare il maggior numero possibile di caselle."
            + "Durante ogni turno, un giocatore può spostare una pedina in una casella vuota adiacente per duplicarla"
            + "o in una casella vuota a due caselle di distanza."
            + "Le pedine avversarie adiacenti a quella mossa vengono convertite"
            + "al colore del giocatore che ha effettuato la mossa.\n";
    private static final String MENU_COMANDI = BLU + "> MENU COMANDI\n" + ANSI_RESET
            + ANSI_ORANGE + "/gioca" + ANSI_RESET + " - Inizia una nuova partita\n" + ANSI_ORANGE + "/help" + ANSI_RESET
            + "  - Mostra l'elenco dei comandi\n" + ANSI_ORANGE + "/esci" + ANSI_RESET + "  - Esce dal gioco\n"
            + ANSI_ORANGE + "/vuoto" + ANSI_RESET + " - Visualizza il tavoliere vuoto\n";
    private static final String PROMPT_COMANDO = "\n" + BLU + "============================" + ANSI_RESET
            + "\nDigita un comando: ";

    public static final int RIGAI = 1;
    public static final int COLONNAI = 1;
    public static final int RIGAF = 7;
    public static final int COLONNAF = 7;

    private App() {
        // Costruttore privato per evitare l'instanziazione della classe
    }

    /**
     * Metodo per stampare il benvenuto con i colori.
     */
    private static void stampaBenvenuto() {
        System.out.println(BENVENUTO_ASCII);
        System.out.println(INTRODUZIONE);
        System.out.println(REGOLE);
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
        @SuppressWarnings("unused")
        boolean giocoIniziato = false;

        stampaBenvenuto(); // Stampa il messaggio di benvenuto
        System.out.println(MENU_COMANDI);

        while (isRunning) {
            System.out.print(PROMPT_COMANDO);
            String input = scanner.nextLine().trim(); // inserisco il trim per rimuovere eventuali spazi bianchi

            switch (input.toLowerCase()) {
                case "/help":
                case "--help":
                case "-h":
                    Menu.help(scanner);
                    break;
                case "/esci":
                    if (Menu.esci(scanner)) {
                        isRunning = false;
                    }
                    break;
                case "/gioca":
                    giocoIniziato = true;
                    tavoliere.inizializzaPedine(RIGAI, COLONNAI, RIGAF, COLONNAF);
                    partita.avviaPartita();
                    if (partita.avviaPartita()) {
                        return;
                    }
                    break;
                case "/vuoto":
                    tavoliere.visualizzaTavoliereVuoto();
                    break;
                default:
                    System.out.println("Scelta non valida. Premi un pulsante valido\n");
            }
        }
        scanner.close();
    }
}
