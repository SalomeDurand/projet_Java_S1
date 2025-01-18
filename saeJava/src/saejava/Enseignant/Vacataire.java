/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Enseignant;

import saejava.exceptions.ChaineValideException;
import saejava.EnseignantExceptions.EnseignantNull;
import saejava.Heures.Heures;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class Vacataire extends Enseignant {

    public Vacataire(String nom, String prenom) throws ChaineValideException {
        super(nom, prenom);
    }

    @Override
    public double calculerSalaire() {
        try {
            return this.calculerHeuresTotalesEquivalentTD() * Heures.getTarif();
        } catch (EnseignantNull ex) {
            System.err.println("Erreur lors du calcul du salaire : " + ex.getMessage());
        }
        return 0;
    }
}
