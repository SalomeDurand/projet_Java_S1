/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.HeureExceptionsTests;

import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;
import saejava.HeureExceptions.AucuneHeureDefinie;
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
public class AucuneHeureDefinieTest {

    private Ressource r2;

    public AucuneHeureDefinieTest() {
    }

    @Before
    public void setUp() throws InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, RessourceDejaExistante, ChaineValideException {
        Formation f1 = new Formation("BUT2");
        r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 0, 0);
    }

    @Test
    public void toutesHeuresAffecteesTest() {
        try {
            r2.toutesHeuresAffectees();
        } catch (AucuneHeureDefinie ex) {
            assertEquals("Aucune heure n'a ete "
                    + "realisee dans cette ressource", ex.getMessage());
        }
    }
}
