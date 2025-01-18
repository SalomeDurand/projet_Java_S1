/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.EnseignantExceptionsTests;

import saejava.EnseignantExceptions.EnseignantNull;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import saejava.Enseignant.*;
import saejava.HeureExceptions.*;
import saejava.Heures.Heures;
import saejava.Ressource.*;
import saejava.RessourceExceptions.RessourceDejaExistante;
import saejava.exceptions.ChaineValideException;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class EnseignantNullTest {

    private Ressource r2;
    private Titulaire enseignant4;
    private List<Heures> listeHS2;

    public EnseignantNullTest() {
    }

    @Before
    public void setUp() throws InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, RessourceDejaExistante, ChaineValideException {
        Formation f1 = new Formation("BUT2");
        r2 = new Ressource("C", "Programmation en langage C", f1, 60, 0, 0, 25, 75);
        listeHS2 = new ArrayList<>();
        enseignant4 = new Titulaire("Alix", "Esmeralda", listeHS2);

    }

    @Test
    public void getHeuresPourEnseignantTest() {
        try {
            r2.getHeuresPourEnseignant(enseignant4);
        } catch (EnseignantNull ex) {
            assertEquals("Cet enseignant n'est pas "
                    + "lié à cette ressource.", ex.getMessage());
        }
    }

    @Test
    public void retirerEnseignantTest() {
        try {
            r2.retirerEnseignant(enseignant4);
        } catch (EnseignantNull ex) {
            assertEquals("Cet enseignant n'est "
                    + "pas lie a cette ressource.", ex.getMessage());
        } catch (Exception e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }

    }

}
