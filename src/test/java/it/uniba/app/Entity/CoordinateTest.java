package it.uniba.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniba.app.Boundary.Costanti;

/**
 * Test per la classe Coordinate.
 */
class CoordinateTest {  // La classe di test è ora package-private

    /**
     * Test per il costruttore e il metodo getRiga.
     */
    @Test
    void testConstructorGetRiga() {
        final int dim = 3;
        Coordinate coord = new Coordinate(dim, Costanti.RIGA_4);
        assertEquals(dim, coord.getRiga(), "Il valore della riga deve essere uguale a " + dim);
    }

    /**
     * Test per il costruttore e il metodo getColonna.
     */
    @Test
    void testConstructorGetColonna() {
        final int dim2 = 4;
        Coordinate coord = new Coordinate(Costanti.RIGA_3, dim2);
        assertEquals(dim2, coord.getColonna(), "Il valore della colonna deve essere uguale a " + dim2);
    }

    /**
     * Test per il costruttore di copia e il metodo getRiga.
     */
    @Test
    void testCopyConstructorGetRiga() {
        Coordinate original = new Coordinate(2, Costanti.RIGA_5);
        Coordinate copy = new Coordinate(original);
        assertEquals(2, copy.getRiga(), "Il valore della riga della copia deve essere uguale a 2");
    }

    /**
     * Test per il costruttore di copia e il metodo getColonna.
     */
    @Test
    void testCopyConstructorGetColonna() {
        final int dim = 5;
        Coordinate original = new Coordinate(2, dim);
        Coordinate copy = new Coordinate(original);
        assertEquals(dim, copy.getColonna(), "Il valore della colonna della copia deve essere uguale a " + dim);
    }

    /**
     * Test per il metodo setRiga.
     */
    @Test
    void testSetRiga() {
        final int dim = 7;
        Coordinate coord = new Coordinate(0, 0);
        coord.setRiga(dim);
        assertEquals(dim, coord.getRiga(), "Il valore della riga deve essere uguale a " + dim);
    }

    /**
     * Test per il metodo setColonna.
     */
    @Test
    void testSetColonna() {
        Coordinate coord = new Coordinate(0, 0);
        coord.setColonna(2);
        assertEquals(2, coord.getColonna(), "Il valore della colonna deve essere uguale a 2");
    }

    /**
     * Test per il metodo equals sullo stesso oggetto.
     */
    @Test
    void testEqualsSameObject() {
        Coordinate coord = new Coordinate(1, 1);
        assertTrue(coord.equals(coord), "Un oggetto deve essere uguale a se stesso");
    }

    /**
     * Test per il metodo equals con oggetti diversi ma con gli stessi valori.
     */
    @Test
    void testEqualsDifferentObjectSameValues() {
        final int dim = 3;
        Coordinate coord1 = new Coordinate(dim, dim);
        Coordinate coord2 = new Coordinate(dim, dim);
        assertTrue(coord1.equals(coord2), "Oggetti diversi con gli stessi valori devono essere uguali");
    }

    /**
     * Test per il metodo equals con oggetti diversi e con valori diversi.
     */
    @Test
    void testEqualsDifferentObjectDifferentValues() {
        final int dim = 3;
        final int dim2 = 4;
        Coordinate coord1 = new Coordinate(dim, dim2);
        Coordinate coord2 = new Coordinate(dim2, dim);
        assertFalse(coord1.equals(coord2), "Oggetti diversi con valori diversi non devono essere uguali");
    }

}
