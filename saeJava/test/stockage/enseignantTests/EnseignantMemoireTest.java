/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package stockage.enseignantTests;

import org.junit.Test;
import static org.junit.Assert.*;
import saejava.Enseignant.Vacataire;
import saejava.exceptions.ChaineValideException;
import stockage.enseignant.EnseignantMemoire;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class EnseignantMemoireTest {

    @Test
    public void testAjouter() throws ChaineValideException {
        EnseignantMemoire instance = new EnseignantMemoire();
        int id = instance.ajouter(new Vacataire("test", "test"));
        assertEquals(id, 0);
        assertEquals(instance.read().size(), 1);
    }

}
