/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Enseignant;

import saejava.exceptions.ChaineValideException;
import saejava.Heures.Heures;
import java.util.List;
import saejava.EnseignantExceptions.EnseignantNull;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class VacataireTitulaire extends EnseignantTitulaire {

    public VacataireTitulaire(String nom, String prenom, List<Heures> heuresStatutaires) throws ChaineValideException {
        super(nom, prenom, heuresStatutaires);

    }

    @Override
    public double calculerSalaire() {
        double salaire = 0;
        //On a pas besoin de vérifier si il a fait ses heures statutaires ou non                                                                                                             
        if (calculerHeuresStatutairesEquivalentTD() >= 192) {
            try {
                salaire = (calculerHeuresTotalesEquivalentTD() * Heures.getTarif())
                        + ((calculerHeuresTotalesEquivalentTD() - calculerHeuresTotalesEquivalentTD()) * Heures.getTarif()); // On paye l'enseignant sur les heures statutaires + les heures supp, c'est à dire (heures totales - heures statutaires)
            } catch (EnseignantNull ex) {
                System.err.println("Erreur dans le calcul du salaire de l'enseignant : " + ex.getMessage());
            }
        } else {
            salaire = calculerHeuresStatutairesEquivalentTD() * Heures.getTarif();
        }
        if (gethasPrimeAnnuelle()) {       //Si il a droit à la prime annuelle
            return salaire + getPrimeAnnuelle();
        } else {
            return salaire;
        }

    }
}
