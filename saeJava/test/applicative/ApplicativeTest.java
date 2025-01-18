/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package applicative;

import saejava.Heures.Heures;
import saejava.EnseignantExceptions.EnseignantDejaExistant;
import saejava.exceptions.ChaineValideException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import saejava.Enseignant.Enseignant;
import saejava.Ressource.*;
import saejava.RessourceExceptions.*;
import saejava.HeureExceptions.*;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class ApplicativeTest {

    /**
     * Test of creerEnseignant method, of class Applicative.
     */
    @Test
    public void testCreerEnseignant() throws ChaineValideException {
        String nom = "mon nom";
        String prenom = "mon prenom";
        int type = 1;
        List<Heures> heuresStatutaires = null;
        boolean hasPrimeAnnuelle = false;
        Applicative instance = new Applicative();
        instance.creerEnseignant(nom, prenom, type, heuresStatutaires, hasPrimeAnnuelle);
        assertEquals(1, instance.getEnseignants().size());
    }

    @Test
    public void testSupprimeProfesseur() throws ChaineValideException {
        String nom = "mon nom";
        String prenom = "mon prenom";
        int type = 1;
        List<Heures> heuresStatutaires = null;
        boolean hasPrimeAnnuelle = false;
        Applicative instance = new Applicative();
        instance.creerEnseignant(nom, prenom, type, heuresStatutaires, hasPrimeAnnuelle);
        assertEquals(1, instance.getEnseignants().size());
        instance.supprimeProfesseur(nom);
        assertEquals(0, instance.getEnseignants().size());
    }

    @Test
    public void testAjouterRessourcePuisSuppprimer() throws ChaineValideException, InvalidParameterException, NombreHeuresTropGrand, NombreHeuresNegatif, EnseignantDejaExistant, RessourceInexistante, RessourceDejaExistante {
        String nom = "mon nom";
        String prenom = "mon prenom";
        int type = 1;
        List<Heures> heuresStatutaires = null;
        boolean hasPrimeAnnuelle = false;
        Applicative instance = new Applicative();
        Enseignant e = instance.creerEnseignant(nom, prenom, type, heuresStatutaires, hasPrimeAnnuelle);
        Formation f = instance.creerFormation("test formation");
        Ressource r = instance.creerRessource("test", "test description", "test formation", 0, 0, 0, 0, 0);
        List<Heures> lh = new ArrayList();
        r.ajouterEnseignant(e, lh);

        assertTrue(r.getEnseignantsRessource().contains(e));
        assertTrue(e.getListeRessource().contains(r));
        assertFalse(r.getEnseignantsRessource().isEmpty());
        assertFalse(e.getListeRessource().isEmpty());

        e.retirerRessource(r);
        assertTrue(e.getListeRessource().isEmpty());
        assertFalse(r.getEnseignantsRessource().isEmpty());
    }

    @Test
    public void testTrouverEnseignantApresSuppression() throws ChaineValideException {
        Applicative instance = new Applicative();
        instance.creerEnseignant("test", "test", 2, null, true);
        instance.creerEnseignant("test2", "test2", 2, null, true);
        instance.supprimeProfesseur("test");
        Enseignant ens = instance.trouverEnseignant("test2", "test2");
        assertNotNull(ens);
    }

    // TODO: tester suppression sur meme nom
}
