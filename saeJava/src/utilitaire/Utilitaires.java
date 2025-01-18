/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitaire;

import saejava.exceptions.ChaineValideException;
import java.util.List;
import saejava.Heures.Heures;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class Utilitaires {

    // methode utilitaire
    public static String chaineValide(String s) throws ChaineValideException {
        if (s == null || s.trim().equals(" ") || s.isEmpty()) {
            throw new ChaineValideException("\n\u274C L'entrée n'est pas valide. \u274C\n");
        }
        if (s.length() > 300) {
            throw new IllegalArgumentException("L'entrée est trop longue");
        } else {
            return s.trim();
        }
    }

    public static double getNbHeuresParType(List<Heures> heures, Class classType) {
        double total = 0;

        for (Heures currHeure : heures) {
            if (currHeure.getClass() == classType) {
                total += 1;
            }
        }
        return total;
    }
}
