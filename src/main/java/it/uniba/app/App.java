package it.uniba.app;

import java.util.Scanner;

/**
 * Main class of the application.
 */
public final class App {

    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public String getGreeting() {
        return "===BENVENUTO IN ATAXX!===";
    }

    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(new App().getGreeting());
            System.out.println("\nDigita '/help','--help' o '-h' per visulizzare l'elenco dei comandi ");
            System.out.println("\n============================");

            System.out.print("\nDigita un comando: ");
            String input = scanner.nextLine().trim(); // inserisco il trim per rimuovere eventuali spazi bianchi

            switch (input.toLowerCase()) {
                case "/help":
                case "--help":
                case "-h":
                    Menu.help(scanner);
                    break;
                case "/esci":
                    isRunning = false; // esco dal ciclo while
                    break;
                default:
                    System.out.println("Scelta non valida. Premi un pulsante valido\n");

            }
        }
        scanner.close();

    }
}
