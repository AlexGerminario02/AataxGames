package it.uniba.app.Entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <<Entity>>: Classe per rappresentare la duplicazione di una mossa su una scacchiera.
 */
@SuppressWarnings("unused")
public class Duplicazione {
    private static final int DIM = 7;
    private final char[] colonne = {'a', 'b', 'c', 'd', 'e', 'f', 'g' };
    private Pedina[][] scacchiera;
    private Mossa mossa;

    /**
     * Costruttore della classe Duplicazione.
     * @param mossad La mossa da duplicare.
     */
    public Duplicazione(final Mossa mossad) {
        this.mossa = mossad;
    }

    /*
     * Metodo che calcola le mosse possibili per la duplicazione
     */
    /**
     * Calcola le mosse possibili per la duplicazione.
     * @param riga La riga della coordinata di partenza.
     * @param colonna La colonna della coordinata di partenza.
     * @return Un'ArrayList di Coordinate contenente le mosse possibili per la duplicazione.
     */
    public ArrayList<Coordinate> mosseA(final int riga, final int colonna) {
        ArrayList<Coordinate> mosse = new ArrayList<>();
        int[] deltaRighe = {-1, -1, -1, 0, 0, 1, 1, 1 };
        int[] deltaColonne = {-1, 0, 1, -1, 1, -1, 0, 1 };
        for (int i = 0; i < deltaRighe.length; i++) {
            int nuovaRiga = riga + deltaRighe[i];
            char nuovaColonna = (char) (colonna + deltaColonne[i]);
            Coordinate nuovaCoordinata = new Coordinate(nuovaRiga, nuovaColonna);
            if (nuovaRiga >= 1 && nuovaRiga <= DIM && Arrays.binarySearch(colonne, nuovaColonna) >= 0) {
                if (mossa != null && mossa.posizioneVuota(nuovaRiga, nuovaColonna)) {
                    mosse.add(nuovaCoordinata);
                }
            }
        }
        return mosse;
}
}
