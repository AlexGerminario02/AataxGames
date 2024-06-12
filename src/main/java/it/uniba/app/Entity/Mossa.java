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
     * Calcola le mosse possibili per la pedina.
     * @param riga La riga della coordinata di partenza.
     * @param colonna La colonna della coordinata di partenza.
     * @return Un'ArrayList di Coordinate contenente le mosse possibili per la pedina.
     */
    public final ArrayList<Coordinate> mosseC(final int riga, final int colonna) {
        ArrayList<Coordinate> mosseC = new ArrayList<>(); // Inizializza mosseC come null
        // Se sia mossea che mosseb sono null, restituisci un'ArrayList vuota
        if (mossea == null && mosseb == null) {
            return new ArrayList<>();
        }
        // Se mossea è null, mosseC sarà null e non verrà eseguita alcuna operazione aggiuntiva
        if (mossea == null) {
            return new ArrayList<>();
        }
        // Se mosseb è null, mosseC sarà null e non verrà eseguita alcuna operazione aggiuntiva
        if (mosseb == null) {
            return new ArrayList<>();
        } else {
            mosseC.addAll(mossea.mosseA(riga, colonna));
            mosseC.addAll(mosseb.mosseB(riga, colonna));
        }
        return mosseC; // Restituisci l'ArrayList mosseC
    }
/**
 * Verifica se una posizione sulla scacchiera è vuota.
 * @param riga La riga della posizione.
 * @param colonna La colonna della posizione.
 * @return true se la posizione è vuota, altrimenti false.
 */
public final boolean posizioneVuota(final int riga, final char colonna) {
    int indiceColonna = Arrays.binarySearch(colonne, Character.toLowerCase(colonna));
    if (indiceColonna < 0 || riga < 1 || riga > DIM || indiceColonna >= colonne.length) {
        return false;
    }

    // Controllo per le celle specifiche sulla diagonale principale e secondaria
    if ((riga == 1 && colonna == 'a') || (riga == DIM && colonna == 'a')
    || (riga == 1 && colonna == 'g') || (riga == DIM && colonna == 'g')) {
        return false;
    }

    // Controllo se la posizione sulla scacchiera è vuota
    return scacchiera[riga - 1][indiceColonna] == null;
}



}
