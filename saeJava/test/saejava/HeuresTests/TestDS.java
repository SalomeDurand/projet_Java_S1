/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.HeuresTests;

import saejava.Heures.DS;
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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import saejava.Enseignant.*;
import saejava.RessourceExceptions.RessourceInexistante;
import saejava.exceptions.ChaineValideException;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class TestDS {

    public TestDS() {
    }

    private Heures ds1, ds2;
    private Formation f;
    private Enseignant enseignant1, enseignant2;
    private Groupe groupe1, groupe2;
    private List<Groupe> expectedGroupes, listeGroupes1;
    private List<Enseignant> expectedEnseignants, listeEns1;
    private List<Heures> heuresStatutaires;

    @Before
    public void setUp() throws EnseignantNull, GroupeNull, GroupeVide, EnseignantVide, ChaineValideException, RessourceInexistante {
        f = new Formation("BUT");
        groupe1 = new Groupe("G1", f);
        groupe2 = new Groupe("G2", f);
        enseignant1 = new Vacataire("Dupont", "Martin");
        heuresStatutaires = new ArrayList<>();
        enseignant2 = new Titulaire("Durand", "Samuel", heuresStatutaires);
        listeGroupes1 = new ArrayList<>();
        listeGroupes1.add(groupe1);
        listeGroupes1.add(groupe2);
        listeEns1 = new ArrayList<>();
        listeEns1.add(enseignant1);
        listeEns1.add(enseignant2);
        ds1 = new DS(listeGroupes1, listeEns1);
        ds2 = new DS(groupe1, enseignant1);
        expectedGroupes = new ArrayList<>();
        expectedEnseignants = new ArrayList<>();
    }

    @Test
    public void testConstructeurDS1Initialise() {
        assertNotNull(ds1.getGroupes());
        assertEquals(ds1.getGroupes(), listeGroupes1);
        assertNotNull(ds1.getEnseignants());
        assertEquals(ds1.getEnseignants(), listeEns1);
    }

    @Test
    public void testConstructeurDS2Initialise() {
        assertNotNull(ds2.getGroupes());
        assertTrue(ds2.getGroupes().contains(groupe1));
        assertNotNull(ds2.getEnseignants());
        assertTrue(ds2.getEnseignants().contains(enseignant1));
    }

    @Test(expected = GroupeNull.class)
    public void testConstructeurDS1AvecArgument1Invalide() throws GroupeNull, EnseignantNull, GroupeVide, EnseignantVide {
        ds1 = new DS(null, listeEns1);
    }

    @Test(expected = EnseignantNull.class)
    public void testConstructeurDS1AvecArgument2Invalide() throws EnseignantNull, GroupeNull, GroupeVide, EnseignantVide {
        ds1 = new DS(listeGroupes1, null);
    }

    @Test(expected = GroupeNull.class)
    public void testConstructeurDS2AvecArgument1Invalide() throws GroupeNull, EnseignantNull, GroupeVide, EnseignantVide {
        ds2 = new DS(null, enseignant1);
    }

    @Test(expected = EnseignantNull.class)
    public void testConstructeurDS2AvecArgument2Invalide() throws EnseignantNull, GroupeNull, GroupeVide, EnseignantVide {
        ds2 = new DS(groupe1, null);
    }

    @Test(expected = GroupeVide.class)
    public void testConstructeurDSAvecArgument1Vide() throws EnseignantNull, GroupeNull, GroupeVide, EnseignantVide {
        ds1 = new DS(expectedGroupes, listeEns1);
    }

    @Test(expected = EnseignantVide.class)
    public void testConstructeurDSAvecArgument2Vide() throws EnseignantNull, GroupeNull, GroupeVide, EnseignantVide {
        ds1 = new DS(listeGroupes1, expectedEnseignants);
    }
}
