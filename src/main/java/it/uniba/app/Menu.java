package it.uniba.app;

import java.io.IOException;
import java.util.Scanner;

final class Menu {

    private Menu() {
    }

    // funzione per pulire il terminale
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            // Gestione dell'eccezione se non è possibile pulire il terminale
            System.out.println("Impossibile pulire il terminale.");
        }
    }

    public static void helpPrincipale(final Scanner scanner) {

        System.out.println(Costanti.BENVENUTO_HELP);
        System.out.println(Costanti.MENU_COMANDI_PRINCIPALE);
        System.out.print("\nPer poter digitare un comando tornare al menu principale /indietro: ");
        String aiuto = scanner.nextLine().trim();
        if (aiuto.equalsIgnoreCase("/indietro")) {
            clearScreen();
            return;

        }
    }

    public static boolean esci(final Scanner scanner) {
        while (true) {
            System.out.println(Costanti.CONFERMA_ESCI);
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

    public static void helpGioco(final Scanner scanner) {
        System.out.println(Costanti.BENVENUTO_HELP);
        System.out.print(Costanti.MENU_COMANDI_GIOCO);
        scanner.nextLine().trim();
        if (scanner.nextLine().trim().equalsIgnoreCase("/indietro")) {
            clearScreen();
            return;
        }
    }
}
