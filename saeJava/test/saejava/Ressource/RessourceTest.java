/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.Ressource;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import saejava.Enseignant.Enseignant;
import saejava.Enseignant.Vacataire;
import saejava.Heures.CM;
import saejava.Heures.Heures;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class RessourceTest {

    /**
     * Test of retirerGroupe method, of class Ressource.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRetirerGroupe() throws Exception {
        Formation f = new Formation("test");
        Groupe g = new Groupe("test", f);
        Ressource instance = new Ressource("test", "test", f, 1, 0, 0, 0, 0);

        instance.enseignerGroupe(g);
        assertEquals(1, instance.getGroupes().size());
        assertEquals(1, g.getListeRessource().size());

        instance.retirerGroupe(g);
        assertEquals(0, instance.getGroupes().size());
        assertEquals(0, g.getListeRessource().size());
    }

    @Test
    public void testCalculerCoutTotal() throws Exception {
        Heures.setEquivalentCM(1.5f);
        Heures.setTarif(43);
        Formation f = new Formation("test");
        Ressource instance = new Ressource("test", "test", f, 1, 0, 0, 0, 0);
        Enseignant ens = new Vacataire("test", "test");
        List<Heures> ensHeures = new ArrayList<>();
        ensHeures.add(new CM());
        instance.ajouterEnseignant(ens, ensHeures);
        assertEquals(64.5, instance.calculerCoutTotal(), 0);
    }
}
