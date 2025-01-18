/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package saejavaTests;

import saejava.exceptions.ChoixInvalideException;
import java.io.*;
import org.junit.Test;
import static org.junit.Assert.*;
import saejava1.Clavier;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class SaeJavaTest {

    public SaeJavaTest() {
    }

    @Test
    public void testValidInput() throws ChoixInvalideException {
        String input = "3\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        int reponse = Clavier.lireInt();

        assertEquals(3, reponse);

        System.setIn(originalSystemIn);
    }

    @Test(expected = ChoixInvalideException.class)
    public void testInvalidInputOutOfRange() throws ChoixInvalideException {
        String input = "0\n8\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Clavier.lireChoix(1, 2);
    }

}
