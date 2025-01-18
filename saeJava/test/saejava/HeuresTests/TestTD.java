/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.HeuresTests;

import saejava.Heures.TD;
import saejava.Heures.Heures;
import saejava.GroupeExceptions.GroupeVide;
import saejava.EnseignantExceptions.EnseignantNull;
import saejava.EnseignantExceptions.EnseignantVide;
import saejava.GroupeExceptions.GroupeNull;
import saejava.Enseignant.Vacataire;
import saejava.Ressource.Formation;
import saejava.Ressource.Groupe;
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
public class TestTD {

    public TestTD() {
    }

    private Heures td1;
    private Formation f;
    private Enseignant enseignant1;
    private Groupe groupe1;

    @Before
    public void setUp() throws EnseignantNull, GroupeNull, GroupeVide, EnseignantVide, ChaineValideException, RessourceInexistante {
        f = new Formation("BUT");
        groupe1 = new Groupe("G1", f);
        enseignant1 = new Vacataire("Dupont", "Martin");
        td1 = new TD(groupe1, enseignant1);
    }

    //TD
    @Test
    public void testConstructeurTDInitialise() {
        assertNotNull(td1.getGroupes());
        assertTrue(td1.getGroupes().contains(groupe1));
        assertNotNull(td1.getEnseignants());
        assertTrue(td1.getEnseignants().contains(enseignant1));
    }

    @Test(expected = GroupeNull.class)
    public void testConstructeurTDAvecArgument1Invalide() throws GroupeNull, EnseignantNull {
        td1 = new TD(null, enseignant1);
    }

    @Test(expected = EnseignantNull.class)
    public void testConstructeurTDAvecArgument2Invalide() throws EnseignantNull, GroupeNull {
        td1 = new TD(groupe1, null);
    }
}
