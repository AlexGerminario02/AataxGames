package it.uniba.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test per la classe Coordinate.
 */
public class CoordinateTest {

    /**
     * Test per il costruttore e i metodi getter.
     */
    @Test
    public void testConstructorAndGetters() {
        final int dim = 3;
        final int dim2 = 4;
        Coordinate coord = new Coordinate(dim, dim2);
        assertEquals(dim, coord.getRiga());
        assertEquals(dim2, coord.getColonna());
    }

    /**
     * Test per il costruttore di copia.
     */
    @Test
    public void testCopyConstructor() {
        final int dim = 5;
        Coordinate original = new Coordinate(2, dim);
        Coordinate copy = new Coordinate(original);
        assertEquals(2, copy.getRiga());
        assertEquals(dim, copy.getColonna());
    }
/**
 * Test per i metodi setter.
 */
    @Test
    public void testSetters() {
        final int dim = 7;
        Coordinate coord = new Coordinate(0, 0);
        coord.setRiga(dim);
        coord.setColonna(2);
        assertEquals(dim, coord.getRiga());
        assertEquals(2, coord.getColonna());
    }
/**
 * Test per il metodo equals.
 */
    @Test
    public void testEqualsSameObject() {
        Coordinate coord = new Coordinate(1, 1);
        assertTrue(coord.equals(coord));
    }
/**
 * Test per il metodo equals con oggetti diversi ma con gli stessi valori.
 */
    @Test
    public void testEqualsDifferentObjectSameValues() {
        final int dim = 3;
        Coordinate coord1 = new Coordinate(dim, dim);
        Coordinate coord2 = new Coordinate(dim, dim);
        assertTrue(coord1.equals(coord2));
    }

    /**
     * Test per il metodo equals con oggetti diversi e con valori diversi.
     */
    @Test
    public void testEqualsDifferentObjectDifferentValues() {
         final int dim = 3;
         final  int dim2 = 4;
        Coordinate coord1 = new Coordinate(dim, dim2);
        Coordinate coord2 = new Coordinate(dim2, dim);
        assertFalse(coord1.equals(coord2));
    }

    /**
     * Test per il metodo equals con oggetto null.
     */
    @Test
    public void testEqualsNull() {
        Coordinate coord = new Coordinate(1, 1);
        assertFalse(coord.equals(null));
    }

    /**
     *  Test per il metodo equals con oggetto di classe diversa.
     */
    @Test
    public void testEqualsDifferentClass() {
        Coordinate coord = new Coordinate(1, 1);
        String notACoord = "Not a Coordinate";
        assertFalse(coord.equals(notACoord));
    }

    /**
     *  Test per il metodo hashCode.
     */
    @Test
    public void testHashCode() {
        final int dim = 3;
        Coordinate coord1 = new Coordinate(dim, dim);
        Coordinate coord2 = new Coordinate(dim, dim);
        assertEquals(coord1.hashCode(), coord2.hashCode());
    }

    /**
     *  Test per il metodo toString.
     */
    @Test
    public void testToString() {
        Coordinate coord = new Coordinate(1, 2);
        assertEquals("(c  1 )", coord.toString());
    }
}
