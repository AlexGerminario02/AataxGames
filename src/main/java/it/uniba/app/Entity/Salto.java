package it.uniba.app.Entity;

import java.util.ArrayList;
import java.util.Arrays;

import it.uniba.app.Boundary.Costanti;

/**
 * <<Entity>>: Classe per rappresentare le mosse di salto di una pedina.
 */
@SuppressWarnings("unused")
public class Salto {
    private static final int DIM = 7;
    private final char[] colonne = {'a', 'b', 'c', 'd', 'e', 'f', 'g' };
    private Pedina[][] scacchiera;
    private Mossa mossa;

    /**
     * Costruttore della classe Salto.
     * @param mossas L'istanza di Mossa associata.
     */
    public Salto(final Mossa mossas) {
        this.mossa = mossas;
    }

/**
 * Calcola le mosse possibili per il salto.
 * @param riga La riga della coordinata di partenza.
 * @param colonna La colonna della coordinata di partenza.
 * @return Un'ArrayList di Coordinate contenente le mosse possibili per il salto.
 */
public final ArrayList<Coordinate> mosseB(final int riga, final int colonna) {
    ArrayList<Coordinate> mosse = new ArrayList<>();

    int[] deltaRighe = {Costanti.MENODUE, Costanti.MENODUE, Costanti.MENODUE, -1, 0, 0, 1, 1,
        Costanti.DUE, Costanti.DUE, Costanti.DUE, Costanti.MENODUE, Costanti.DUE,
        Costanti.DUE, Costanti.DUE, Costanti.DUE, -1, Costanti.MENODUE};
    int[] deltaColonne = {Costanti.MENODUE, -1, 1, Costanti.MENODUE, Costanti.MENODUE,
        Costanti.DUE, Costanti.MENODUE, Costanti.DUE, -1, 0, 1, 0, Costanti.MENODUE,
        0, 1, Costanti.DUE, Costanti.DUE, Costanti.DUE};
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
