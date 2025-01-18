/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.RessourceExceptionsTests;

import saejava.GroupeExceptions.GroupeDejaExistant;
import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import saejava.GroupeExceptions.SousGroupeDejaExistant;
import saejava.HeureExceptions.*;
import saejava.Ressource.*;
import saejava.RessourceExceptions.RessourceDejaExistante;
import saejava.RessourceExceptions.RessourceInexistante;
import saejava.exceptions.ChaineValideException;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class RessourceInexistanteTest {

    private Formation f1;
    private Formation f2;
    private Ressource r2;
    private Ressource r1;
    private Groupe groupe4;
    private SousGroupe sousgroupe4;

    public RessourceInexistanteTest() {
    }

    @Before
    public void setUp() throws InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, GroupeDejaExistant, RessourceDejaExistante, RessourceInexistante, ChaineValideException, SousGroupeDejaExistant {
        f1 = new Formation("BUT2");
        f2 = new Formation("BUT1");
        r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 0, 0);
        r1 = new Ressource("Reseaux", "Gestion des reseaux", f2, 0, 0, 0, 0, 0);
        groupe4 = new Groupe("G4", f1);
        sousgroupe4 = new SousGroupe("sg1");
        groupe4.ajouterSousGroupe(sousgroupe4);
    }

    @Test
    public void testretirerRessourceFormation() {
        try {
            f1.retirerRessource(r1);
            fail("Une exception RessourceInexistante aurait dû être levée");
        } catch (RessourceInexistante ex) {
            assertEquals("La ressource " + r1.getNom() + " ne fait pas partie de cette formation", ex.getMessage());
        }
    }

    @Test
    public void testafficherRessources() {
        try {
            f1.retirerRessource(r2);
            f1.afficherRessources();
            fail("Une exception RessourceInexistante aurait dû être levée");
        } catch (RessourceInexistante ex) {
            assertEquals("La ressource " + r2.getNom() + " ne fait pas partie de cette formation", ex.getMessage());
        }
    }

    @Test
    public void testcalculerCoutFormation() {
        try {

            f1.calculerCoutFormation();
            fail("Une exception RessourceInexistante aurait dû être levée");
        } catch (RessourceInexistante ex) {
            assertEquals("Aucune ressource n'a ete ajoutee a cette formation", ex.getMessage());
        } 
    }

    @Test
    public void testretirerRessourcesGroupe() {
        try {
            groupe4.retirerSousGroupe(sousgroupe4);
            groupe4.retirerRessource(r1.getNom());
            fail("Une exception RessourceInexistante aurait dû être levée");
        } catch (RessourceInexistante ex) {
            assertEquals("La ressource " + r1.getNom() + "n'est pas affecte a ce groupe", ex.getMessage());
        } catch (Exception e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }
    }

    @Test
    public void testRetirerRessourceSousGroupes() {
        try {
            sousgroupe4.retirerRessource(r1.getNom());
            fail("Une exception RessourceDejaExistante aurait dû être levée");
        } catch (RessourceInexistante ex) {
            assertEquals("La ressource " + r1.getNom() + " n'a pas ete ajoutee a ce sousgroupe", ex.getMessage());
        } catch (Exception e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }
    }

}
