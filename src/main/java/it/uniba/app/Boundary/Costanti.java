package it.uniba.app.Boundary;

/**
 * <<Boundary>>
 * Classe che contiene le costanti del gioco.
 * Questa classe fornisce variabili statiche finali utilizzate per la formattazione del testo,
 * i limiti della griglia del gioco, messaggi di benvenuto, menu dei comandi, e le regole del gioco.
 */
public final class Costanti {
    public static final String R = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLU = "\u001B[34m";
    public static final String O = "\u001B[38;5;208m";
    public static final String CONFERMA_ESCI = "Sei sicuro di voler uscire dal gioco (si/no) \n";
    public static final String COMANDO_HELP_GIOCO = "Inserisci il comando /indietro per continuare: ";
    public static final String ERRORE = "Comando inserito non valido";
    public static final int TEMPO = 7;
    public static final char PEDINA_X = 'X'; // 'B' per bianco
    public static final String LINE_SEPARATOR = "+-----";
    public static final String EMPTY_CELL = "|  .  ";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[47m";
    public static final String ANSI_YELLOW = "\u001B[48;2;255;255;0m";
    public static final String ANSI_ORANGE = "\u001B[48;2;255;165;0m";
    public static final String ANSI_PURPLE = "\u001B[45m";
    public static final char PEDINA_ROSSO = 'R'; // 'N'
    public static final char PEDINA_NERA = 'N'; // 'B's

    public static final int RIGAI = 1;
    public static final int COLONNAI = 1;
    public static final int RIGAF = 7;
    public static final int COLONNAF = 7;
    public static final int ORE = 3600000;
    public static final int MINUTI = 60000;
    public static final int SECONDI = 1000;
    public static final int RIGA_3 = 3;
    public static final int RIGA_4 = 4;
    public static final int RIGA_5 = 5;
    public static final int RIGA_6 = 6;
    public static final int RIGA_7 = 7;
    public static final int LIMITE_BLOCCA = 9;
    public static final int TIME5 = 5;
    public static final int TIME6 = 6;
    public static final int TIME7 = 7;
    public static final int DUE = 2;
    public static final int MENODUE = -2;
    public static final String SEPARATORE = "\n" + BLU + "=============================================";
    public static final String SEPARATORE_2 = BLU + "=============================================";
 // Messaggi per la richiesta di abbandono e conferma
 public static final String MSG_ABBANDONA_PARTITA = "Sei sicuro di voler abbandonare la partita? (si/no) \n";
 public static final String CONFERMA_ABBANDONO = " ha abbandonato la partita.";


    public static final String BENVENUTO_HELP =
       "  _   _   _____   _       ____  \n"
     + " | | | | | ____| | |     |  _ \\ \n"
     + " | |_| | |  _|   | |     | |_) |\n"
     + " |  _  | | |___  | |___  |  __/ \n"
     + " |_| |_| |_____| |_____| |_|    \n";

     public static final String MENU_COMANDI_PRINCIPALE =
    BLU + "-------------------------------------------------------\n"
    + BLU + "| " + R +             "Elenco comandi" + BLU + "                                      |\n"
    + BLU + "|-----------------------------------------------------|\n"
    + BLU + "| " + O + "/gioca" + R + "   - 'Avvia la partita'  " + BLU + "                     |\n"
    + BLU + "| " + O + "/vuoto" + R + "   - 'Genera il tavoliere vuoto' " + BLU + "             |\n"
    + BLU + "| " + O + "/esci" + R + "    - 'Esci dal gioco'    " + BLU + "                     |\n"
    + BLU + "|-----------------------------------------------------|\n" + R;


    public static final String MENU_COMANDI_GIOCO =
    BLU + "-------------------------------------------------------\n"
    + BLU + "| " + R +             "Elenco comandi" + BLU + "                                      |\n"
    + BLU + "|-----------------------------------------------------|\n"
    + BLU + "| " + O + "/qualimosse" + R + "   - 'Mostra le mosse possibili'  " + BLU + "       |\n"
    + BLU + "| " + O + "/tavoliere" + R + "   - 'Mostra il tavoliere in gioco' " + BLU + "      |\n"
    + BLU + "| " + O + "/abbandona" + R + "    - 'Abbandona la partita'    " + BLU + "          |\n"
    + BLU + "|-----------------------------------------------------|\n" + R;



    public static final String BENVENUTO_ASCII = "\n"
    + RED + " ________      # _________  # ________      # __     __     # __     __     #\n"
    + BLU + "/_______/\\     #/________/\\ #/_______/\\     #/__/\\ /__/\\    #/__/\\ /__/\\    #\n"
    + RED + "\\::: _  \\ \\    #\\__.::.__\\/ #\\::: _  \\ \\    #\\ \\::\\\\:.\\ \\   #\\ \\::\\\\:.\\ \\   #\n"
    + BLU + " \\::(_)  \\ \\   #   \\::\\ \\   # \\::(_)  \\ \\   # \\_\\::_\\:_\\/   # \\_\\::_\\:_\\/   #\n"
    + RED + "  \\:: __  \\ \\  #    \\::\\ \\  #  \\:: __  \\ \\  #   _\\/__\\_\\_/\\ #   _\\/__\\_\\_/\\ #\n"
    + BLU + "   \\:.\\ \\  \\ \\ #     \\::\\ \\ #   \\:.\\ \\  \\ \\ #   \\ \\ \\ \\::\\ \\#   \\ \\ \\ \\::\\ \\#\n"
    + RED + "    \\__\\/\\__\\/ #      \\__\\/ #    \\__\\/\\__\\/ #    \\_\\/  \\__\\/ #    \\_\\/  \\__\\/ #\n";


    public static final String INTRODUZIONE = BLU + "> INTRODUZIONE\n" + R
            + "Ataxx è un avvincente gioco da tavolo strategico per due giocatori"
            + "progettato per essere giocato in modalità locale."
            + "Nato nei primi anni '90, è diventato un classico grazie alla combinazione"
            + "di regole semplici e profondità tattica.\n";
    public static final String REGOLE = BLU + "> REGOLE\n" + R
            + "Il gioco si svolge su una griglia di 7x7 caselle. Ogni giocatore inizia"
            + "con due pedine disposte agli angoli opposti della griglia. "
            + "L'obiettivo è controllare il maggior numero possibile di caselle."
            + "Durante ogni turno, un giocatore può spostare una pedina in una casella vuota adiacente per duplicarla"
            + "o in una casella vuota a due caselle di distanza."
            + "Le pedine avversarie adiacenti a quella mossa vengono convertite"
            + "al colore del giocatore che ha effettuato la mossa.\n";
    public static final String MENU_COMANDO_INIZIALE = BLU + "> COMANDO\n" + R
            + O + "/help, -h, --help" + R
            + "  - Mostra l'elenco dei comandi\n";

    public static final String PROMPT_COMANDO = "\n" + BLU + "============================" + R
            + "\nDigita un comando: ";
public static final String SCELTA_AVVIA_PARTITA = "Vuoi inserire un comando o le coordinate? (comando/coordinate)\n";
public static final String COORDINATE = "Digita le coordinate: ";

    /**
     * Costruttore della classe Costanti.
     */
    private Costanti() {
        // Costruttore vuoto
    }

}
