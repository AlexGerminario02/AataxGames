package it.uniba.app.Boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * <<Boundary>>
 * Classe per gestire l'input da tastiera.
 * Questa classe fornisce metodi per leggere diversi tipi di input dall'utente,
 * come stringhe, interi e caratteri, gestendo eventuali errori di input.
 *  
 */
public class Tastiera {
    private BufferedReader reader;

    /**
     * Costruttore della classe.
     */
    public Tastiera() {
        reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    /**
     * Chiede all'utente di inserire una stringa e la restituisce.
     *
     * @param prompt Il messaggio da mostrare all'utente.
     * @return La stringa inserita dall'utente.
     */
    public String readString(final String prompt) {
        System.out.print(prompt);
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Errore nella lettura dell'input della stringa.");
        }
        return input;
    }

    /**
     * Chiede all'utente di inserire un intero e lo restituisce.
     *
     * @param prompt Il messaggio da mostrare all'utente.
     * @return L'intero inserito dall'utente.
     */
    public int readInt(final String prompt) {
        int value = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(reader.readLine());
                valid = true;
            } catch (IOException e) {
                System.out.println("Errore nella lettura dell'input.");
            } catch (NumberFormatException e) {
                System.out.println("Intero non valido. Per favore riprova.");
            }
        }
        return value;
    }

    /**
     * Chiede all'utente di inserire un carattere e lo restituisce.
     *
     * @param prompt Il messaggio da mostrare all'utente.
     * @return Il carattere inserito dall'utente.
     */
    public char readChar(final String prompt) {
        char value = Character.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                String input = reader.readLine();
                if (input != null && input.length() == 1) {
                    value = input.charAt(0);
                    valid = true;
                } else {
                    System.out.println("Carattere non valido. Per favore inserisci un singolo carattere.");
                }
            } catch (IOException e) {
                System.out.println("Errore nella lettura dell'input.");
            }
        }
        return value;
    }

}
