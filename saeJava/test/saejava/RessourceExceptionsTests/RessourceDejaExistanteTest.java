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
public class RessourceDejaExistanteTest {
    private Formation f1;
    private Ressource r2;
    private Groupe groupe4;
    private SousGroupe sousgroupe4;
    public RessourceDejaExistanteTest() {
    }
    
    @Before
    public void setUp() throws InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, RessourceDejaExistante, GroupeDejaExistant, RessourceInexistante, ChaineValideException {
        f1 = new Formation("BUT2");
        r2 = new Ressource("C", "Programmation en langage C", f1, 0, 0, 0, 0, 0);
        groupe4 = new Groupe("G4", f1);
        sousgroupe4 = new SousGroupe("sg1");
        
    }

    @Test
    public void testajouterRessourceFormation() {
        try {
            f1.ajouterRessource(r2);
            f1.ajouterRessource(r2);
            fail("Une exception RessourceDejaExistante aurait dû être levée");
        } catch (RessourceDejaExistante ex) {
            assertEquals("La ressource " + r2.getNom()+ " fait deja partie de cette formation", ex.getMessage());
        }
    }
    
     @Test
    public void testajouterRessourceGroupes() {
        try {
            f1.ajouterRessource(r2);
            f1.ajouterGroupe(groupe4);
            groupe4.ajouterRessource(r2.getNom());
            fail("Une exception RessourceDejaExistante aurait dû être levée");
        } catch (RessourceDejaExistante ex) {
            assertEquals("La ressource " + r2.getNom() + " est deja affecte a ce groupe", ex.getMessage());
        } catch (GroupeDejaExistant | RessourceInexistante e){
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }
    }
    
    @Test
    public void testajouterRessourceSousGroupes() {
        try {
            f1.ajouterRessource(r2);
            groupe4.ajouterSousGroupe(sousgroupe4);
            f1.ajouterGroupe(groupe4);
            sousgroupe4.ajouterRessource(r2.getNom());
            fail("Une exception RessourceDejaExistante aurait dû être levée");
        } catch (RessourceDejaExistante ex) {
            assertEquals("La ressource " + r2.getNom() + " est deja affectee a ce sousgroupe", ex.getMessage());
        }catch ( Exception e) {
            fail("Une exception innatendue a ete levee : " + e.getMessage());
        }
    }
    
}
