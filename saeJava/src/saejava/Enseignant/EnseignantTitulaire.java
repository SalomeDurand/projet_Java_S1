/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Enseignant;

import saejava.Heures.TD;
import saejava.Heures.DS;
import saejava.Heures.PE;
import saejava.Heures.Heures;
import saejava.Heures.CM;
import saejava.Heures.TP;
import saejava.exceptions.ChaineValideException;
import java.util.List;
import saejava.EnseignantExceptions.EnseignantNull;
import utilitaire.Utilitaires;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 *
 */
public abstract class EnseignantTitulaire extends Enseignant {

    private final List<Heures> heuresStatutaires;
    private static double primeAnnuelle = 0.0;
    private boolean hasPrimeAnnuelle;

    public EnseignantTitulaire(String nom, String prenom, List<Heures> heuresStatutaires) throws ChaineValideException {//Lorsque l'on veut ajouter un enseignant titulaire, on ne sait pas dès le début de l'année si il aura droit à la prime
        // On initialise donc le booléen à false dans le constructeur, 
        //et l'on ajoute une méthode pour plus tard spécifier si il aura droit à la prime ou non. On pourra alors calculer le salaire
        super(nom, prenom);
        this.hasPrimeAnnuelle = false;
        this.heuresStatutaires = heuresStatutaires;
    }

    public List<Heures> getHeuresStatutaires() {
        return this.heuresStatutaires;
    }

    public static double getPrimeAnnuelle() {
        return primeAnnuelle;
    }

    //Getter pour pouvoir accéder au boolean depuis les classes filles  
    public boolean gethasPrimeAnnuelle() {
        return hasPrimeAnnuelle;
    }

    //a revoir   
    public double calculerPourcentageSousService(double heuresRealisees, double heuresStatutaires) {
        return heuresRealisees * 100 / heuresStatutaires;
    }

    /*public void setHeuresStatutaires(List<Heures> nbHeures) {
        if (nbHeures == null) {
            throw new nbHeuresStatutairesException("La liste des heures ne peut pas être nulle.");
        }
        if (this.calculerHeuresStatutairesEquivalentTD() < 64 || this.calculerHeuresStatutairesEquivalentTD() > 384) {
            throw new nbHeuresStatutairesException("Le nombre d'heures doit être compris entre 64 et 384.");
        }
        this.heuresStatutaires = nbHeures;
    }*/
    public static void setPrimeAnnuelle(double montant) {
        primeAnnuelle = montant;
    }

    //Méthode pour définir si il aura droit à la prime ou non
    public void setHasPrimeAnnuelle(boolean p) {
        hasPrimeAnnuelle = p;
    }

    public boolean hasHeuresStatutairesRequises() throws EnseignantNull {
        Class[] classList = {
            CM.class,
            DS.class,
            PE.class,
            TD.class,
            TP.class,};

        for (Class currentClass : classList) {
            if (Utilitaires.getNbHeuresParType(getHeuresTotales(), currentClass)
                    < Utilitaires.getNbHeuresParType(this.heuresStatutaires, currentClass)) {
                return false;
            }
        }

        return true;
    }

    public double calculerHeuresStatutairesEquivalentTD() {

        double total = 0;

        if (this.heuresStatutaires == null) {
            System.out.println("Les heures statutaires ne sont pas initialisées.");
        }
        for (Heures heure : this.heuresStatutaires) {
            total += heure.calculEquivalentTD();
        }

        return total;
    }

    /**
     *
     * @return
     */
    @Override
    public double calculerSalaire() {
        try {
            double salaire = 0;

            if (!this.hasHeuresStatutairesRequises()) {               // n'a pas fait les heures statutaires
                salaire = (this.calculerHeuresStatutairesEquivalentTD() * Heures.getTarif())
                        * (1 - ((calculerPourcentageSousService(calculerHeuresTotalesEquivalentTD(), calculerHeuresStatutairesEquivalentTD())) / 100)); // des parenthèses étaient mal placées

            } else if (this.calculerHeuresStatutairesEquivalentTD() >= 192) {
                salaire = (this.calculerHeuresTotalesEquivalentTD() * Heures.getTarif())
                        + ((calculerHeuresTotalesEquivalentTD() - this.calculerHeuresTotalesEquivalentTD()) * Heures.getTarif()); // On paye l'enseignant sur les heures statutaires + les heures supp, c'est à dire (heures totales - heures statutaires)
            } else {
                salaire = this.calculerHeuresStatutairesEquivalentTD() * Heures.getTarif();
            }
            if (hasPrimeAnnuelle) {      //Si il a droit à la prime annuelle
                return salaire + primeAnnuelle;
            } else {                    //Sinon
                return salaire;
            }
        } catch (EnseignantNull ex) {
            System.err.println("Erreur dans le calcul du salaire : " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public String toString() {
        double heuresStatutairesTotales = Utilitaires.getNbHeuresParType(heuresStatutaires, CM.class)
                + Utilitaires.getNbHeuresParType(heuresStatutaires, DS.class)
                + Utilitaires.getNbHeuresParType(heuresStatutaires, PE.class)
                + Utilitaires.getNbHeuresParType(heuresStatutaires, TD.class)
                + Utilitaires.getNbHeuresParType(heuresStatutaires, TP.class);

        return "\nNom : " + nom
                + "\nPrénom : " + prenom
                + "\nHeures statutaires totales : " + heuresStatutairesTotales
                + " (équivalent TD : " + calculerHeuresStatutairesEquivalentTD() + ")"
                + "\n******************************\n";
    }
}
