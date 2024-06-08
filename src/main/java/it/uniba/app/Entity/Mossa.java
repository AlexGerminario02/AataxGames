package it.uniba.app.Entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <<Entity>>: Classe per rappresentare le mosse possibili di una pedina.
 */
public class Mossa {
    private static final int DIM = 7;
    private final char[] colonne = {'a', 'b', 'c', 'd', 'e', 'f', 'g' };
    private Duplicazione mossea;
    private Salto mosseb;
    private Pedina[][] scacchiera;

    /**
     * Costruttore della classe Mossa.
     * @param scacchieram La scacchiera su cui eseguire le mosse.
     * @param mosseaa Le mosse di duplicazione associate.
     * @param mossebb Le mosse di salto associate.
     */
    public Mossa(final Pedina[][] scacchieram, final Duplicazione mosseaa, final Salto mossebb) {
        // Creiamo una copia difensiva della matrice scacchiera
        this.scacchiera = new Pedina[scacchieram.length][];
        for (int i = 0; i < scacchieram.length; i++) {
            this.scacchiera[i] = scacchieram[i].clone();
        }

        // Assumiamo che Duplicazione e Salto siano immutabili, altrimenti bisognerebbe fare una copia difensiva
        this.mossea = mosseaa;
        this.mosseb = mossebb;
    }

    /**
     * Copy constructor.
     * @param original The original Mossa instance to copy.
     */
    public Mossa(final Mossa original) {
        this(original.scacchiera, original.mossea, original.mosseb);
    }

    /**
     * Calcola le mosse possibili per la pedina.
     * @param riga La riga della coordinata di partenza.
     * @param colonna La colonna della coordinata di partenza.
     * @return Un'ArrayList di Coordinate contenente le mosse possibili per la pedina.
     */
    public final ArrayList<Coordinate> mosseC(final int riga, final int colonna) {
        ArrayList<Coordinate> mosseC = new ArrayList<>();
        if (mossea != null) {
            mosseC.addAll(mossea.mosseA(riga, colonna));
        }
        if (mosseb != null) {
            mosseC.addAll(mosseb.mosseB(riga, colonna));
        }
        return mosseC;
    }

    /**
     * Verifica se una posizione sulla scacchiera è vuota.
     * @param riga La riga della posizione.
     * @param colonna La colonna della posizione.
     * @return true se la posizione è vuota, altrimenti false.
     */
    public final boolean posizioneVuota(final int riga, final char colonna) {
        int indiceColonna = Arrays.binarySearch(colonne, Character.toLowerCase(colonna));
        if (indiceColonna < 0 || riga < 1 || riga > DIM) {
            return false;
        }
        return scacchiera[riga - 1][indiceColonna] == null;
    }
}
