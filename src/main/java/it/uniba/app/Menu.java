package it.uniba.app;

import java.util.Scanner;

public class Menu {
    private Menu() { }
    public static void help(Scanner scanner) {
        boolean helpRunning = true;
        while (helpRunning) {
            System.out.println("====HELP====");
            System.out.println("Benvenuto nel menu Help!");
            System.out.println("=============");
            System.out.println("Elenco comandi: ");
            System.out.println("/gioca - 'Questo comando serve per avviare la partita'");
            System.out.println("/vuoto - 'Questo comando permette di generare il tavoliere del gioco vuoto'");
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

}
