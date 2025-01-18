/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejava.Ressource;

import org.junit.Test;
import static org.junit.Assert.*;
import saejava.GroupeExceptions.SousGroupeDejaExistant;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class GroupeTest {

    /**
     * Test of ajouterSousGroupe method, of class Groupe.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = SousGroupeDejaExistant.class)
    public void testAjouterSousGroupeDeuxFoisMemeInstance() throws Exception {
        System.out.println("ajouterSousGroupe");
        SousGroupe dg = new SousGroupe("sg");
        Groupe instance = new Groupe("test", new Formation("testformation"));
        instance.ajouterSousGroupe(dg);
        assertEquals(1, instance.getSousGroupe().size());
        instance.ajouterSousGroupe(dg);
    }

    @Test(expected = SousGroupeDejaExistant.class)
    public void testAjouterSousGroupeDeuxFoisMemeNomDifferenteInstance() throws Exception {
        System.out.println("ajouterSousGroupe");
        Groupe instance = new Groupe("test", new Formation("testformation"));
        instance.ajouterSousGroupe(new SousGroupe("sg"));
        assertEquals(1, instance.getSousGroupe().size());
        instance.ajouterSousGroupe(new SousGroupe("sg"));
    }
}
