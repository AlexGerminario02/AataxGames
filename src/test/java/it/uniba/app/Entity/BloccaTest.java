package it.uniba.app.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniba.app.Boundary.Costanti;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;



class BloccaTest {

    private Pedina[][] scacchiera;
    private List<Coordinate> caselleBloccate;
    private Blocca blocca;

    @BeforeEach
    void setUp() {
        scacchiera = new Pedina[Blocca.DIM][Blocca.DIM];
        caselleBloccate = new ArrayList<>();
        blocca = new Blocca(scacchiera, caselleBloccate);
    }

    @Test
    void testIsBloccata() {
        final int dim = 3;
        Coordinate bloccata = new Coordinate(dim, dim);
        blocca.bloccaCasella(bloccata);
        assertFalse(blocca.isBloccata(bloccata), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testIsBloccataNonBloccata() {
        final int dim2 = 4;
        Coordinate nonBloccata = new Coordinate(2, dim2);
        assertFalse(blocca.isBloccata(nonBloccata), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testIsCasellaBloccata() {
        final int dim = 4;
        final int dim2 = 2;
        Coordinate bloccata = new Coordinate(dim, dim2);
        blocca.bloccaCasella(bloccata);
        assertTrue(blocca.isCasellaBloccata(bloccata), "La casella dovrebbe essere bloccata");
    }

    @Test
    void testIsCasellaNonBloccata() {
        final int dim3 = 6;
        Coordinate nonBloccata = new Coordinate(1, dim3);
        assertFalse(blocca.isCasellaBloccata(nonBloccata), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testIsBloccataCasellaNonBloccata() {
        Coordinate coordC4 = new Coordinate(Costanti.RIGA_4, Costanti.RIGA_3);
        assertFalse(blocca.isBloccata(coordC4), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testResettaCelleBloccate() {
        ArrayList<Coordinate> caselleBloccateList = new ArrayList<>();
        caselleBloccateList.add(new Coordinate(1, 1));
        blocca.resettaCelleBloccate(caselleBloccateList);
        assertTrue(caselleBloccateList.isEmpty(), "La lista di caselle bloccate dovrebbe essere vuota");
    }

    @Test
    void testBloccaECasellaBloccata() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        assertTrue(blocca.bloccaCasella(coord), "La casella dovrebbe essere bloccata");
    }

    @Test
    void testCasellaBloccata() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        blocca.bloccaCasella(coord);
        assertTrue(blocca.isCasellaBloccata(coord), "La casella dovrebbe essere bloccata");
    }

    @Test
    void testCasellaNonBloccata() {
        Coordinate anotherCoord = new Coordinate(1, 1);
        assertFalse(blocca.isCasellaBloccata(anotherCoord), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testBloccaCasella() {
        Coordinate coordValida = new Coordinate(Costanti.RIGA_4, 1);
        assertTrue(blocca.bloccaCasella(coordValida), "La casella dovrebbe essere bloccata");
    }

    @Test
    void testCasellaBloccataValida() {
        Coordinate coordValida = new Coordinate(Costanti.RIGA_4, 1);
        blocca.bloccaCasella(coordValida);
        assertTrue(blocca.isCasellaBloccata(coordValida), "La casella dovrebbe essere bloccata");
    }

    @Test
    void testBloccaCasellaNonBloccabile() {
        Coordinate coordNonValida = new Coordinate(1, 1);
        assertFalse(blocca.bloccaCasella(coordNonValida), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testCasellaNonBloccabile() {
        Coordinate coordNonValida = new Coordinate(1, 1);
        blocca.bloccaCasella(coordNonValida);
        assertFalse(blocca.isCasellaBloccata(coordNonValida), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testBloccaCasellaNonBloccabileColonna() {
        Coordinate coordNonValida = new Coordinate(1, Costanti.RIGA_5);
        assertFalse(blocca.bloccaCasella(coordNonValida), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testCasellaNonBloccabileColonna() {
        Coordinate coordNonValida = new Coordinate(1, Costanti.RIGA_5);
        blocca.bloccaCasella(coordNonValida);
        assertFalse(blocca.isCasellaBloccata(coordNonValida), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testCostruttore() {
        assertNotNull(blocca, "L'oggetto Blocca non dovrebbe essere nullo");
    }

    @Test
    void testScacchiera() {
        assertArrayEquals(scacchiera, blocca.getScacchiera(),
        "La scacchiera dovrebbe essere uguale a quella inizializzata");
    }

    @Test
    void testCaselleBloccateInizializzate() {
        assertEquals(caselleBloccate, blocca.getCaselleBloccateg(),
        "La lista di caselle bloccate dovrebbe essere uguale a quella inizializzata");
    }

    @Test
    void testBloccaCasellaRiga4ColonnaD() {
        Coordinate coordValida = new Coordinate(Costanti.RIGA_4, Costanti.RIGA_4);
        assertTrue(blocca.bloccaCasella(coordValida), "La casella dovrebbe essere bloccata");
    }

    @Test
    void testCasellaBloccataRiga4ColonnaD() {
        Coordinate coordValida = new Coordinate(Costanti.RIGA_4, Costanti.RIGA_4);
        blocca.bloccaCasella(coordValida);
        assertTrue(blocca.isCasellaBloccata(coordValida), "La casella dovrebbe essere bloccata");
    }

    @Test
    void testCaselleBloccategNonModificabileEsternamente() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        blocca.bloccaCasella(coord);

        List<Coordinate> caselleBloccateList = blocca.getCaselleBloccateg();
        caselleBloccateList.clear();

        assertTrue(blocca.isCasellaBloccata(coord), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testIsBloccataCoordinateFuoriLimiti() {
        final int dim = 8;
        Coordinate coordFuoriLimiti = new Coordinate(dim, dim);
        assertFalse(blocca.isBloccata(coordFuoriLimiti), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testIsBloccataCoordinateNegativa() {
        Coordinate coordNegativa = new Coordinate(-1, -1);
        assertFalse(blocca.isBloccata(coordNegativa), "La casella non dovrebbe essere bloccata");
    }

    @Test
    void testResettaCelleBloccateListaVuota() {
        List<Coordinate> caselleBloccateList = new ArrayList<>();
        blocca.resettaCelleBloccate(caselleBloccateList);
        assertTrue(caselleBloccateList.isEmpty(), "La lista di caselle bloccate dovrebbe essere vuota");
    }

    @Test
    void testBloccaCasellaGiaBloccata() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        blocca.bloccaCasella(coord);
        assertTrue(blocca.isCasellaBloccata(coord), "La casella dovrebbe essere bloccata");
    }

    @Test
    void testBloccaCasellaGiaBloccataDiNuovo() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        blocca.bloccaCasella(coord);
        assertTrue(blocca.bloccaCasella(coord), "La casella dovrebbe essere bloccata");
    }

    @Test
    void testCaselleBloccategNonModificabileEsternamente2() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        blocca.bloccaCasella(coord);
        List<Coordinate> caselleBloccateList = blocca.getCaselleBloccateg();
        caselleBloccateList.clear();
        assertTrue(blocca.isCasellaBloccata(coord), "La casella non dovrebbe essere bloccata");
    }
}
