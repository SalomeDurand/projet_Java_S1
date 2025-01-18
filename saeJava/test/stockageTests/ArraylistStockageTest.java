/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package stockageTests;

import stockage1.ArraylistStockage;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class ArraylistStockageTest {

    @Test
    public void testAjouter() {
        Object elem = new Object();
        ArraylistStockage instance = new ArraylistStockage();
        int id = instance.ajouter(elem);
        assertEquals(id, 0);
        assertEquals(instance.read().size(), 1);
    }

    @Test
    public void testAjouterPlusieurs() {
        Object elem = new Object();
        ArraylistStockage instance = new ArraylistStockage();
        instance.ajouter(elem);
        instance.ajouter(elem);
        int id = instance.ajouter(elem);
        assertEquals(id, 2);
        assertEquals(instance.read().size(), 3);
    }

    @Test
    public void testSupprimer() {
        Object elem = new Object();
        ArraylistStockage instance = new ArraylistStockage();
        instance.ajouter(elem);
        boolean supprime = instance.supprimer(elem);
        assertTrue(supprime);
    }

    @Test()
    public void testSupprimerIdInexistant() {
        ArraylistStockage instance = new ArraylistStockage();
        boolean supprime = instance.supprimer(new Object());
        assertFalse(supprime);
    }

    @Test
    public void testGet() {
        Object elem = new Object();
        ArraylistStockage instance = new ArraylistStockage();
        instance.ajouter(elem);
        Object res = instance.get(0);
        assertEquals(elem, res);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInexistant() {
        ArraylistStockage instance = new ArraylistStockage();
        instance.get(2);
    }

    @Test
    public void testGetApresSuppressionMilieu() {
        Object elem1 = new Object();
        Object elem2 = new Object();
        Object elem3 = new Object();
        ArraylistStockage instance = new ArraylistStockage();
        instance.ajouter(elem1);
        instance.ajouter(elem2);
        instance.ajouter(elem3);
        instance.supprimer(elem2);
        Object res = instance.get(2);
        assertEquals(elem3, res);
    }

    @Test
    public void testReadApresSuppression() {
        ArraylistStockage instance = new ArraylistStockage();
        Object elem1 = new Object();
        Object elem2 = new Object();
        instance.ajouter(elem1);
        instance.ajouter(elem2);
        instance.supprimer(elem1);
        List<Object> r = instance.read();
        assertEquals(1, r.size());
    }
}
