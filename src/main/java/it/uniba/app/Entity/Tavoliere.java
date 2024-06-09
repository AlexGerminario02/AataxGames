package it.uniba.app.Entity;
import java.util.Arrays;

import it.uniba.app.Boundary.Costanti;

/**
 * <<Entity>>: Classe per rappresentare il tavoliere di gioco.
 */
@SuppressWarnings("unused")
public class Tavoliere {
    public static final int ZERO = 0;
    public static final int UNO = 1;
    public static final int DUE = 2;
    public static final int RIGAINIZIALE = 1;
    public static final int COLONNAINIZIALE = 1;
    public static final int RIGAFINALE = 7;
    public static final int COLONNAFINALE = 7;
    private static final int DIM = 7;

    private Pedina[][] scacchiera;
    private final char[] colonne = {'a', 'b', 'c', 'd', 'e', 'f', 'g' };
    private int turno;
  /**
     * Metodo statico per creare un tavoliere.
     *
     * @return un nuovo oggetto Tavoliere
     */
    public static Tavoliere creaTavoliere() {
        return new Tavoliere(DIM);
    }
   /**
     * Costruttore per inizializzare un tavoliere con dimensione specifica.
     *
     * @param dim la dimensione del tavoliere
     */
    public Tavoliere(final int dim) {
        this.scacchiera = new Pedina[dim][dim];
    }


/**
     * Costruttore per creare una copia di un tavoliere.
     *
     * @param copia il tavoliere da copiare
     */
    public Tavoliere(final Tavoliere copia) {
        int dim = copia.scacchiera.length;
        this.scacchiera = new Pedina[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (copia.scacchiera[i][j] != null) {
                    this.scacchiera[i][j] = new Pedina(copia.scacchiera[i][j]); // Creare una nuova istanza di Pedina
                }
            }
        }
    }
/**
     * Restituisce la scacchiera.
     *
     * @return la scacchiera
     */
    public Pedina[][] getScacchiera() {
        Pedina[][] scacchieraCopy = new Pedina[scacchiera.length][];
        for (int i = 0; i < scacchiera.length; i++) {
            scacchieraCopy[i] = new Pedina[scacchiera[i].length];
            for (int j = 0; j < scacchiera[i].length; j++) {
                if (scacchiera[i][j] != null) {
                    scacchieraCopy[i][j] = new Pedina(scacchiera[i][j]); // assuming Pedina has a copy constructor
                }
            }
        }
        return scacchieraCopy;
    }

 /**
     * Conta il numero di pedine con un carattere specifico sul tavoliere.
     *
     * @param caratterePedina il carattere della pedina
     * @param tavoliere        il tavoliere
     * @return il numero di pedine con il carattere specificato
     */
    public final int contaPedine(final String caratterePedina, final Tavoliere tavoliere) {
        int count = 0;
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                Pedina pedina = scacchiera[i][j];
                if (pedina != null && pedina.getCarattere().equals(caratterePedina)) {
                    count++;
                }
            }
        }
        return count;
    }
 /**
     * Restituisce il turno attuale.
     *
     * @return il turno attuale
     */
    public int getTurno() {
        return turno;
    }
    /**
     * Restituisce la pedina alla posizione specificata sul tavoliere.
     *
     * @param riga    la riga della pedina
     * @param colonna la colonna della pedina (come carattere)
     * @return la pedina alla posizione specificata, null se la posizione è vuota
     */
     public Pedina getPedina(final int riga, final char colonna) {
        int indiceColonna = Arrays.binarySearch(colonne, Character.toLowerCase(colonna));
        if (indiceColonna < 0 || riga < 1 || riga > DIM) {
            return null; // Posizione non valida
        }
        Pedina pedina = scacchiera[riga - 1][indiceColonna];
        return pedina == null ? null : new Pedina(pedina); // Return a copy
    }
 /**
     * Imposta la pedina alla posizione specificata sul tavoliere.
     *
     * @param pedina  la pedina da impostare
     * @param riga    la riga della pedina
     * @param colonna la colonna della pedina
     * @return true se la pedina è stata impostata con successo, false altrimenti
     */

    public boolean setPedina(final Pedina pedina, final int riga, final int colonna) {
        if (colonna < 1 || colonna > DIM || riga < 1 || riga > DIM) {
            return false;
        }

        if (pedina == null) {
            scacchiera[riga - 1][colonna - 1] = null;
            return true;
        } else if (scacchiera[riga - 1][colonna - 1] != null) {
            return false;
        }

        scacchiera[riga - 1][colonna - 1] = pedina;
        pedina.setCoordinate(new Coordinate(riga, colonna));
        return true;
    }
    /**
     * Verifica se la posizione specificata sul tavoliere è vuota.
     *
     * @param riga    la riga della posizione
     * @param colonna la colonna della posizione
     * @return true se la posizione è vuota, false altrimenti
     */

    public boolean posizioneVuota(final int riga, final int colonna) {
        return scacchiera[riga][colonna] == null;
    }

  /**
     * Inizializza le pedine nelle posizioni specificate sul tavoliere.
     *
     * @param rigaIniziale    la riga iniziale
     * @param colonnaIniziale la colonna iniziale
     * @param rigaFinale      la riga finale
     * @param colonnaFinale   la colonna finale
     */
    public void inizializzaPedine(final int rigaIniziale, final int colonnaIniziale, final int rigaFinale,
            final int colonnaFinale) {
        setPedina(new Pedina(Costanti.PEDINA_NERA, new Coordinate(rigaIniziale, colonnaIniziale)), rigaIniziale,
                colonnaIniziale);
        setPedina(new Pedina(Costanti.PEDINA_BIANCA, new Coordinate(rigaIniziale, colonnaFinale)), rigaIniziale,
                colonnaFinale);
        setPedina(new Pedina(Costanti.PEDINA_NERA, new Coordinate(rigaFinale, colonnaFinale)), rigaFinale,
                colonnaFinale);
        setPedina(new Pedina(Costanti.PEDINA_BIANCA, new Coordinate(rigaFinale, colonnaIniziale)), rigaFinale,
                colonnaIniziale);
    }

    /**
     * Inizializza una casella bloccata sulla scacchiera.
     *
     * @param coordinateBloccata le coordinate della casella bloccata
     */

   public final void inizializzaCaselleBloccate(final Coordinate coordinateBloccata) {
       int riga = coordinateBloccata.getRiga();
       int colonna = coordinateBloccata.getColonna() + 1;
       //set pedina è il problema perché non inizializza X!.
       setPedina(new Pedina(Costanti.PEDINA_X, new Coordinate(riga + 1, colonna + 1)), riga, colonna);
   }
}
