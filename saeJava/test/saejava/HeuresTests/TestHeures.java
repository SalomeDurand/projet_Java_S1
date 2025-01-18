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
public class TestHeures {

    public TestHeures() {
    }

    private Heures td1;
    private Formation f;
    private Enseignant enseignant1, enseignant2;
    private Groupe groupe1, groupe2;
    private List<Groupe> expectedGroupes;
    private List<Enseignant> expectedEnseignants;
    private List<Heures> heuresStatutaires;

    @Before
    public void setUp() throws EnseignantNull, GroupeNull, GroupeVide, EnseignantVide, ChaineValideException, RessourceInexistante {
        f = new Formation("BUT");
        groupe1 = new Groupe("G1", f);
        groupe2 = new Groupe("G2", f);
        enseignant1 = new Vacataire("Dupont", "Martin");
        heuresStatutaires = new ArrayList<>();
        enseignant2 = new Titulaire("Durand", "Samuel", heuresStatutaires);
        td1 = new TD(groupe1, enseignant1);
        expectedGroupes = new ArrayList<>();
        expectedEnseignants = new ArrayList<>();
    }

    //méthodes class Heures
    @Test
    public void testGetGroupesMulti() {
        expectedGroupes.add(groupe1);
        expectedGroupes.add(groupe2);
        td1.ajouterGroupe(groupe2);
        assertEquals(expectedGroupes, td1.getGroupes());
    }

    @Test
    public void testGetGroupeSolo() {
        expectedGroupes.add(groupe1);
        assertEquals(expectedGroupes, td1.getGroupes());
    }

    @Test
    public void testGetGroupesNoGroupe() {
        assertNotEquals(expectedGroupes, td1.getGroupes());
    }

    @Test
    public void testAddGroupeDejaPresent() {
        expectedGroupes.add(groupe1);
        td1.ajouterGroupe(groupe1);
        assertEquals(expectedGroupes, td1.getGroupes());
    }

    @Test
    public void testGetEnseignantsMulti() {
        td1.ajouterEnseignant(enseignant2);
        expectedEnseignants.add(enseignant1);
        expectedEnseignants.add(enseignant2);
        assertEquals(expectedEnseignants, td1.getEnseignants());
    }

    @Test
    public void testGetEnseignantSolo() {
        expectedEnseignants.add(enseignant1);
        assertEquals(expectedEnseignants, td1.getEnseignants());
    }

    @Test
    public void testGetEnseignantsNoEnseignant() {
        assertNotEquals(expectedEnseignants, td1.getEnseignants());
    }

    @Test
    public void testAddEnseignantDejaPresent() {
        expectedEnseignants.add(enseignant1);
        td1.ajouterEnseignant(enseignant1);
        assertEquals(expectedEnseignants, td1.getEnseignants());
    }
}
