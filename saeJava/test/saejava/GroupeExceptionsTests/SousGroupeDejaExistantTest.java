/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.GroupeExceptionsTests;

import saejava.Heures.TD;
import saejava.Heures.TP;
import saejava.GroupeExceptions.SousGroupeInexistant;
import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import saejava.GroupeExceptions.SousGroupeDejaExistant;
import static org.junit.Assert.*;
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
public class SousGroupeDejaExistantTest {

    private Formation f1;
    private Ressource r2;
    private Groupe groupe4;
    private SousGroupe sousgroupe4;

    public SousGroupeDejaExistantTest() {
    }

    @Before
    public void setUp() throws InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, SousGroupeDejaExistant, RessourceDejaExistante, ChaineValideException, RessourceInexistante {
        f1 = new Formation("BUT2");
        r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 0, 1);
        groupe4 = new Groupe("G4", f1);
        sousgroupe4 = new SousGroupe("sg1");

    }

    @Test
    public void testenseignerSousGroupe() {
        try {
            groupe4.ajouterSousGroupe(sousgroupe4);
            r2.enseignerSousGroupe(sousgroupe4);
            r2.enseignerSousGroupe(sousgroupe4);
            fail("Une exception SousGroupeDejaExistant aurait dû être levée");
        } catch (SousGroupeDejaExistant ex) {
            assertEquals("Le sousgroupe " + "sg1"
                    + " est deja affecte à cette ressource", ex.getMessage());
        } catch (Exception e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }

    }

    @Test
    public void testenseignerSousGroupeToutesHeuresTP() {
        try {
            groupe4.ajouterSousGroupe(sousgroupe4);
            r2.setHeuresType(TD.class, 50);
            r2.setHeuresType(TP.class, 0);
            r2.enseignerSousGroupe(sousgroupe4);
            fail("Une exception ToutesHeuresNonTP aurait dû être levée");
        } catch (ToutesHeuresNonTP ex) {
            assertEquals("Pour affecter un sous-groupe à une ressource, toutes les heures doivent être des TP.", ex.getMessage());
            assertFalse(r2.getSousGroupes().contains(sousgroupe4));
        } catch (Exception e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }
    }

    @Test
    public void testAjouterSousGroupeGroupe() {
        try {
            groupe4.ajouterSousGroupe(sousgroupe4);
            groupe4.ajouterSousGroupe(sousgroupe4);
            fail("Une exception SousGroupeDejaExistant aurait dû être levée");
        } catch (SousGroupeDejaExistant ex) {
            assertEquals("Le sous-groupe " + sousgroupe4.getNomSGroupe() + " est deja affecte a ce groupe", ex.getMessage());
            assertEquals(1, groupe4.getSousGroupe().size());
        } catch (Exception e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }
    }

    @Test
    public void testRetirerSousGroupeGroupe() {
        try {
            groupe4.retirerSousGroupe(sousgroupe4);
            fail("Une exception SousGroupeInexistant aurait dû être levée");
        } catch (SousGroupeInexistant ex) {
            assertEquals("Le sous-groupe " + sousgroupe4.getNomSGroupe() + "n'est pas affecte a ce groupe", ex.getMessage());
            assertEquals(0, groupe4.getSousGroupe().size());
        } catch (Exception e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }
    }
}
