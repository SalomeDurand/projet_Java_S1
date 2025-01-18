/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Enseignant;

import saejava.exceptions.ChaineValideException;
import saejava.Heures.Heures;
import java.util.List;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class Titulaire extends EnseignantTitulaire {

    public Titulaire(String nom, String prenom, List<Heures> heuresStatutaires) throws ChaineValideException {
        super(nom, prenom, heuresStatutaires);
    }

}
