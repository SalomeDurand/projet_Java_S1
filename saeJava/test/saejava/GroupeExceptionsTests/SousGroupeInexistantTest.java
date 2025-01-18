/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.GroupeExceptionsTests;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import saejava.GroupeExceptions.SousGroupeInexistant;
import static org.junit.Assert.*;
import saejava.HeureExceptions.NombreHeuresNegatif;
import saejava.HeureExceptions.NombreHeuresTropGrand;
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
public class SousGroupeInexistantTest {

    private Formation f1;
    private Ressource r2;
    private Groupe groupe4;
    private SousGroupe sousgroupe4;

    public SousGroupeInexistantTest() {
    }

    @Before
    public void setUp() throws InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, RessourceDejaExistante, ChaineValideException, RessourceInexistante {
        f1 = new Formation("BUT2");
        r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 0, 1);
        groupe4 = new Groupe("G4", f1);
        sousgroupe4 = new SousGroupe("sg1");

    }

    @Test
    public void testretirerSousGroupeRessource() throws RessourceInexistante {
        try {
            r2.retirerSousGroupe(sousgroupe4);
            fail("Une exception SousGroupeInexistant aurait dû être levée");
        } catch (SousGroupeInexistant ex) {
            assertEquals("Ce sousgroupe n'est pas "
                    + "affecte à cette ressource", ex.getMessage());
        }
    }

}
