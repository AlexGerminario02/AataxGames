package it.uniba.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
}
