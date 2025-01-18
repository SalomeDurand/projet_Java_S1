package saejava.HeuresTests;

import saejava.Heures.CM;
import saejava.Heures.Heures;
import saejava.GroupeExceptions.GroupeVide;
import saejava.EnseignantExceptions.EnseignantNull;
import saejava.EnseignantExceptions.EnseignantVide;
import saejava.GroupeExceptions.GroupeNull;
import saejava.Enseignant.Vacataire;
import saejava.Ressource.Formation;
import saejava.Ressource.Groupe;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import saejava.Enseignant.*;
import saejava.RessourceExceptions.RessourceInexistante;
import saejava.exceptions.ChaineValideException;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class TestCM {

    public TestCM() {
    }

    private Heures cm1;
    private Formation f;
    private Enseignant enseignant1;
    private Groupe groupe1, groupe2;
    private List<Groupe> expectedGroupes, listeGroupes1;

    @Before
    public void setUp() throws EnseignantNull, GroupeNull, GroupeVide, EnseignantVide, ChaineValideException, RessourceInexistante {
        f = new Formation("BUT");
        groupe1 = new Groupe("G1", f);
        groupe2 = new Groupe("G2", f);
        enseignant1 = new Vacataire("Dupont", "Martin");
        listeGroupes1 = new ArrayList<>();
        listeGroupes1.add(groupe1);
        listeGroupes1.add(groupe2);
        cm1 = new CM(listeGroupes1, enseignant1);
        expectedGroupes = new ArrayList<>();
    }

    @Test
    public void testConstructeurCMInitialise() {
        assertNotNull(cm1);
        assertEquals(cm1.getGroupes(), listeGroupes1);
        assertTrue(cm1.getEnseignants().contains(enseignant1));
    }

    @Test(expected = GroupeNull.class)
    public void testConstructeurCMAvecArgument1Invalide() throws GroupeNull, EnseignantNull, GroupeVide {
        cm1 = new CM(null, enseignant1);
    }

    @Test(expected = EnseignantNull.class)
    public void testConstructeurCMAvecArgument2Invalide() throws EnseignantNull, GroupeNull, GroupeVide {
        cm1 = new CM(listeGroupes1, null);
    }

    @Test(expected = GroupeVide.class)
    public void testConstructeurCMAvecArgument1Vide() throws EnseignantNull, GroupeNull, GroupeVide, EnseignantVide {
        cm1 = new CM(expectedGroupes, enseignant1);
    }
}
