/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.GroupeExceptionsTests;

import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import saejava.GroupeExceptions.GroupeNull;
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
public class GroupeNullTest {

    private Formation f1;
    private Ressource r2;
    private Groupe groupe4;

    public GroupeNullTest() {
    }

    @Before
    public void setUp() throws InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, RessourceDejaExistante, ChaineValideException, RessourceInexistante {
        f1 = new Formation("BUT2");
        r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 1, 1);
        groupe4 = new Groupe("G4", f1);
    }

    @Test
    public void testretirerGroupe() {
        try {
            r2.retirerGroupe(groupe4);
            fail("Une exception GroupeNull aurait dû être levée");
        } catch (GroupeNull ex) {
            assertEquals("Ce groupe n'est pas "
                    + "affecte à cette ressource", ex.getMessage());
        } catch (RessourceInexistante e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }
    }

}
