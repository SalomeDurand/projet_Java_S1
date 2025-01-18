/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.GroupeExceptionsTests;

import saejava.Heures.TD;
import saejava.Heures.TP;
import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import saejava.GroupeExceptions.GroupeDejaExistant;
import static org.junit.Assert.*;
import saejava.HeureExceptions.AucuneHeureDefinie;
import saejava.HeureExceptions.NombreHeuresNegatif;
import saejava.HeureExceptions.NombreHeuresTropGrand;
import saejava.HeureExceptions.ToutesHeuresTP;
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
public class GroupeDejaExistantTest {

    private Formation f1;
    private Ressource r2;
    private Groupe groupe4;

    public GroupeDejaExistantTest() {
    }

    @Before
    public void setUp() throws InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, ToutesHeuresTP, AucuneHeureDefinie, GroupeDejaExistant, RessourceDejaExistante, ChaineValideException, RessourceInexistante {
        f1 = new Formation("BUT2");
        r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 1, 1);
        groupe4 = new Groupe("G4", f1);

    }

    @Test
    public void testenseignerGroupeDejaExistant() throws ToutesHeuresTP, AucuneHeureDefinie {
        try {
            r2.enseignerGroupe(groupe4);
            r2.enseignerGroupe(groupe4);
            fail("Une exception GroupeDejaExistant aurait dû être levée");
        } catch (GroupeDejaExistant ex) {
            assertEquals("Le groupe " + "G4" + " est"
                    + " deja affecte à cette ressource", ex.getMessage());
        } catch (RessourceDejaExistante e) {
            assertEquals("La ressource " + r2.getNom() + "est deja affecte a ce groupe", e.getMessage());
        }

    }

    @Test
    public void testenseignerGroupeToutesHeuresTP() throws NombreHeuresNegatif, NombreHeuresTropGrand {
        try {
            r2.setHeuresType(TD.class, 0);
            r2.setHeuresType(TP.class, 50);
            r2.enseignerGroupe(groupe4);
            fail("Une exception ToutesHeuresTP aurait dû être levée");
        } catch (ToutesHeuresTP ex) {
            assertEquals("Toutes les heures sont des TP, il faut associer des sous-groupes.", ex.getMessage());
            assertFalse(r2.getGroupes().contains(groupe4));
        } catch (Exception e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }
    }

    @Test
    public void testAjouterGroupeDejaExistant() {
        try {
            f1.ajouterGroupe(groupe4);
            f1.ajouterGroupe(groupe4);
            fail("Une exception GroupeDejaExistant aurait dû être levée");
        } catch (GroupeDejaExistant ex) {
            assertEquals("Le groupe " + groupe4.getNomGroupe() + "fait deja partie de cette formation", ex.getMessage());
        } catch (RessourceInexistante | RessourceDejaExistante e) {
            assertEquals("La ressource " + r2.getNom() + "est deja affecte a ce groupe", e.getMessage());
        }

    }

}
