package it.uniba.app.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.Boundary.Costanti;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: BloccaTest.java.
 */
public class BloccaTest {

    private Pedina[][] scacchiera;
    private List<Coordinate> caselleBloccate;
    private Blocca blocca;

    @BeforeEach
    final
    void setUp() {
        scacchiera = new Pedina[Blocca.DIM][Blocca.DIM];
        caselleBloccate = new ArrayList<>();
        blocca = new Blocca(scacchiera, caselleBloccate);
    }

    /**
     * Test per il metodo isBloccata.
     */
    @Test
    public void testIsBloccata() {
        final int dim = 3;
        final int dim2 = 4;
        Coordinate bloccata = new Coordinate(dim, dim);
        Coordinate nonBloccata = new Coordinate(2, dim2);
        blocca.bloccaCasella(bloccata);
        Assertions.assertFalse(blocca.isBloccata(bloccata));
        Assertions.assertFalse(blocca.isBloccata(nonBloccata));
    }

    /**
     * Test per il metodo isCasellaBloccata.
     */
    @Test
    public void testIsCasellaBloccata() {
        final int dim = 4;
        final int dim2 = 2;
        final int dim3 = 6;
        Coordinate bloccata = new Coordinate(dim, dim2);
        Coordinate nonBloccata = new Coordinate(1, dim3);
        blocca.bloccaCasella(bloccata);
        Assertions.assertTrue(blocca.isCasellaBloccata(bloccata));
        Assertions.assertFalse(blocca.isCasellaBloccata(nonBloccata));
    }

    /**
     * Test per il metodo resettaCelleBloccate.
     */
    @Test
    void testIsBloccataCasellaNonBloccata() {
        Coordinate coordC4 = new Coordinate(Costanti.RIGA_4, Costanti.RIGA_3);
        assertFalse(blocca.isBloccata(coordC4));
    }

    @Test
    void testResettaCelleBloccate() {
        ArrayList<Coordinate> caselleBloccateList = new ArrayList<>();
        caselleBloccateList.add(new Coordinate(1, 1));
        blocca.resettaCelleBloccate(caselleBloccateList);
        assertTrue(caselleBloccateList.isEmpty());
    }

    @Test
    void testBloccaECasellaBloccataTestUno() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        assertTrue(blocca.bloccaCasella(coord));
        assertTrue(blocca.isCasellaBloccata(coord));
    }

    @Test
    void testBloccaECasellaBloccataTestDue() {
        Coordinate anotherCoord = new Coordinate(1, 1);
        assertFalse(blocca.isCasellaBloccata(anotherCoord));
    }

    @Test
    void testBloccaCasella() {
        Coordinate coordValida = new Coordinate(Costanti.RIGA_4, 1);
        assertTrue(blocca.bloccaCasella(coordValida));
        assertTrue(blocca.isCasellaBloccata(coordValida));
    }

    @Test
    void testBloccaCasellaNonBloccabile() {
        Coordinate coordNonValida = new Coordinate(1, 1);
        assertFalse(blocca.bloccaCasella(coordNonValida));
        assertFalse(blocca.isCasellaBloccata(coordNonValida));
    }

    @Test
    void testBloccaCasellaNonBloccabileColonna() {
        Coordinate coordNonValida = new Coordinate(1, Costanti.RIGA_5);
        assertFalse(blocca.bloccaCasella(coordNonValida));
        assertFalse(blocca.isCasellaBloccata(coordNonValida));
    }

    @Test
    void testCostruttore() {
        assertNotNull(blocca);
        assertArrayEquals(scacchiera, blocca.getScacchiera());
        assertEquals(caselleBloccate, blocca.getCaselleBloccateg());
    }

    @Test
    void testBloccaCasellaRiga4ColonnaD() {
        Coordinate coordValida = new Coordinate(Costanti.RIGA_4, Costanti.RIGA_4);
        assertTrue(blocca.bloccaCasella(coordValida));
        assertTrue(blocca.isCasellaBloccata(coordValida));
    }

    @Test
    void testCaselleBloccategNonModificabileEsternamente() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        blocca.bloccaCasella(coord);

        List<Coordinate> caselleBloccateList = blocca.getCaselleBloccateg();
        caselleBloccateList.clear();

        assertFalse(blocca.isCasellaBloccata(coord));
    }

    // Test di regressione
    @Test
    void testIsBloccataCoordinateFuoriLimiti() {
        final int dim = 8;
        Coordinate coordFuoriLimiti = new Coordinate(dim, dim);
        assertFalse(blocca.isBloccata(coordFuoriLimiti));

        Coordinate coordNegativa = new Coordinate(-1, -1);
        assertFalse(blocca.isBloccata(coordNegativa));
    }

    @Test
    void testResettaCelleBloccateListaVuota() {
        List<Coordinate> caselleBloccateList = new ArrayList<>();
        blocca.resettaCelleBloccate(caselleBloccateList);
        assertTrue(caselleBloccateList.isEmpty());
    }



    @Test
    void testBloccaCasellaGiaBloccata() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        blocca.bloccaCasella(coord);
        assertTrue(blocca.isCasellaBloccata(coord));
        assertTrue(blocca.bloccaCasella(coord));
        assertTrue(blocca.isCasellaBloccata(coord));
    }

    @Test
    void testCaselleBloccategNonModificabileEsternamente2() {
        Coordinate coord = new Coordinate(Costanti.RIGA_4, 1);
        blocca.bloccaCasella(coord);
        List<Coordinate> caselleBloccateList = blocca.getCaselleBloccateg();
        caselleBloccateList.clear();
        assertFalse(blocca.isCasellaBloccata(coord));
    }
}
