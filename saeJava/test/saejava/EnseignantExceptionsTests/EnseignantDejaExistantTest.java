/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.EnseignantExceptionsTests;

import saejava.Heures.Heures;
import saejava.EnseignantExceptions.EnseignantDejaExistant;
import saejava.RessourceExceptions.RessourceDejaExistante;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import saejava.Enseignant.*;
import saejava.HeureExceptions.*;
import saejava.Ressource.*;
import saejava.exceptions.ChaineValideException;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class EnseignantDejaExistantTest {

    private Ressource r2;
    private Titulaire enseignant4;
    private List<Heures> listeHS2;

    public EnseignantDejaExistantTest() {
    }

    @Before
    public void setUp() throws InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, EnseignantDejaExistant, RessourceDejaExistante, ChaineValideException {
        Formation f1 = new Formation("BUT2");
        r2 = new Ressource("C", "Programmation en langage C", f1, 60, 0, 0, 25, 75);
        listeHS2 = new ArrayList<>();
        enseignant4 = new Titulaire("Alix", "Esmeralda", listeHS2);
        r2.ajouterEnseignant(enseignant4, listeHS2);
    }

    @Test
    public void ajouterEnseignantTest() {
        try {
            r2.ajouterEnseignant(enseignant4, listeHS2);
        } catch (EnseignantDejaExistant ex) {
            assertEquals("Cet enseignant est "
                    + "déjà affecte à cette ressource", ex.getMessage());
        }

    }
}
