package it.uniba.app;

import java.util.Scanner;

final class Menu {
    private Menu() { }
    public static void help(final Scanner scanner) {
        boolean helpRunning = true;
        while (helpRunning) {
            System.out.println("====HELP====");
            System.out.println("Benvenuto nel menu Help!");
            System.out.println("=============");
            System.out.println("Elenco comandi: ");
            System.out.println("/gioca - 'Questo comando serve per avviare la partita'");
            System.out.println("/vuoto - 'Questo comando permette di generare il tavoliere del gioco vuoto'");
            System.out.println("/tavoliere - 'Questo comando permette di generare il tavoliere durante il gioco'");
            System.out.println("/esci - 'Questo comando permette di uscire dal gioco'");

            System.out.print("\nDigita '/back' per tornare alla schermata iniziale: ");
            String aiuto = scanner.nextLine().trim();

            switch (aiuto.toLowerCase()) {
                case "/back":
                    helpRunning = false;
                    break;
                default:
                    System.out.println("Scelta non valida. Premi un pulsante valido\n");

            }
        }

    }
    public static boolean esci(final Scanner scanner) {
        while (true) {
            System.out.println(CONFERMA_ESCI);
            String stringa = scanner.nextLine();
            if (stringa.equalsIgnoreCase("si")) {
                return true; // Esce dalla funzione, indicando che l'utente vuole uscire
            } else if (stringa.equalsIgnoreCase("no")) {
                return false; // Esce dalla funzione, indicando che l'utente non vuole uscire
            } else {
                System.out.println("Inserisci solo 'si' o 'no'.");
            }
        }
    }

}
