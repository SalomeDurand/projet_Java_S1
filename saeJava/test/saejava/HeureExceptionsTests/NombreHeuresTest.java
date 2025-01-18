/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.HeureExceptionsTests;

import saejava.Heures.CM;
import saejava.Heures.TP;
import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import saejava.HeureExceptions.NombreHeuresNegatif;
import saejava.HeureExceptions.NombreHeuresTropGrand;
import static org.junit.Assert.*;
import saejava.Ressource.*;
import saejava.RessourceExceptions.RessourceDejaExistante;
import saejava.exceptions.ChaineValideException;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class NombreHeuresTest {

    private Formation f1;
    private Formation f2;
    private Ressource r2;

    public NombreHeuresTest() {
    }

    @Before
    public void setUp() throws ChaineValideException {
        f1 = new Formation("BUT2");
        f2 = null;
    }

    @Test
    public void testFormationInexistante() throws NombreHeuresNegatif, NombreHeuresTropGrand, RessourceDejaExistante, ChaineValideException {
        try {
            r2 = new Ressource("C", "Programmation en langage C", f2, 0, 0, 0, 0, 0);
            fail("Une exception InvalidParameterException aurait dû être levée");
        } catch (InvalidParameterException e) {
            assertEquals("Formation inexistante", e.getMessage());

        }
    }

    @Test
    public void testNombreHeuresNegatif() throws NombreHeuresTropGrand, InvalidParameterException, RessourceDejaExistante, ChaineValideException {
        try {
            r2 = new Ressource("C", "Programmation en langage C", f1, -3, 0, 0, 0, 0);
            fail("Une exception NombreHeuresNegatif aurait dû être levée");
        } catch (NombreHeuresNegatif e) {
            assertEquals("Le nombre d'heures de " + "CM"
                    + " est negatif", e.getMessage());

        }
    }

    @Test
    public void testNombreHeuresTropGrand() throws NombreHeuresNegatif, InvalidParameterException, RessourceDejaExistante, ChaineValideException {
        try {
            r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 0, 200000);
            fail("Une exception NombreHeuresTropGrand aurait dû être levée");
        } catch (NombreHeuresTropGrand e) {
            assertEquals("Le nombre d'heures pour "
                    + "TP" + " est trop grand", e.getMessage());

        }
    }

    @Test
    public void testsetHeuresTypeNegatif() throws NombreHeuresTropGrand, InvalidParameterException, RessourceDejaExistante, ChaineValideException {
        try {
            r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 0, 0);
            r2.setHeuresType(CM.class, -3);
            fail("Une exception NombreHeuresNegatif aurait dû être levée");
        } catch (NombreHeuresNegatif e) {
            assertEquals("Le nombre d'heures pour "
                    + "CM" + " est negatif", e.getMessage());

        }
    }

    @Test
    public void testsetHeuresTropGrand() throws NombreHeuresNegatif, InvalidParameterException, RessourceDejaExistante, ChaineValideException {
        try {
            r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 0, 0);
            r2.setHeuresType(TP.class, 200000);
            fail("Une exception NombreHeuresTropGrand aurait dû être levée");
        } catch (NombreHeuresTropGrand e) {
            assertEquals("Le nombre d'heures pour "
                    + "TP" + " est trop grand", e.getMessage());

        }
    }

}
