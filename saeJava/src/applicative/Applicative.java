/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package applicative;

import saejava.Heures.TD;
import saejava.Heures.Heures;
import saejava.Heures.CM;
import saejava.Heures.TP;
import stockage.enseignant.IEnseignantStockage;
import stockage.enseignant.EnseignantMemoire;
import saejava.exceptions.ChaineValideException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import saejava.Enseignant.*;
import saejava.GroupeExceptions.SousGroupeDejaExistant;
import saejava.HeureExceptions.NombreHeuresNegatif;
import saejava.HeureExceptions.NombreHeuresTropGrand;
import saejava.Heures.DS;
import saejava.Heures.PE;
import saejava.Ressource.*;
import saejava.RessourceExceptions.*;
import stockage.formation.*;
import stockage.groupe.*;
import stockage.ressource.*;
import stockage.sousgroupe.*;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class Applicative {

    IEnseignantStockage stockageEnseignants = new EnseignantMemoire();
    IGroupeStockage stockageGroupes = new GroupeMemoire();
    IRessourceStockage stockageRessources = new RessourceMemoire();
    IFormationStockage stockageFormations = new FormationMemoire();
    ISousGroupeStockage stockageSousGroupes = new SousGroupeMemoire();

    public List<Groupe> getGroupesListe() {
        return (List<Groupe>) stockageGroupes.read();
    }

    public List<Enseignant> getEnseignants() {
        return stockageEnseignants.read();
    }

    public List<Formation> getFormations() {
        return stockageFormations.read();
    }

    public Enseignant creerEnseignant(String nom, String prenom, int type, List<Heures> heuresStatutaires, boolean hasPrimeAnnuelle) throws ChaineValideException {
        Enseignant e1 = null;
        switch (type) {
            case 1:             // Titulaire
                e1 = new Titulaire(nom, prenom, heuresStatutaires);
                ((Titulaire) e1).setHasPrimeAnnuelle(hasPrimeAnnuelle);
                stockageEnseignants.ajouter(e1);
                break;
            case 2:              // Vacataire
                e1 = new Vacataire(nom, prenom);
                stockageEnseignants.ajouter(e1);
                break;
            case 3:                // Titulaire Vacataire
                e1 = new VacataireTitulaire(nom, prenom, heuresStatutaires);
                ((VacataireTitulaire) e1).setHasPrimeAnnuelle(hasPrimeAnnuelle);
                stockageEnseignants.ajouter(e1);
                break;
        }
        return e1;
    }

    public Formation creerFormation(String nomFormation) throws ChaineValideException {
        Formation f1 = new Formation(nomFormation);
        stockageFormations.ajouter(f1);
        return f1;
    }

    public Formation trouverFormation(String nomFormation) {
        Formation f1 = null;
        List<Formation> formations = stockageFormations.read();
        for (int i = 0; i < formations.size(); i++) {
            if (formations.get(i).getNomFormation().equals(nomFormation)) {
                f1 = formations.get(i);
                break;
            }
        }
        return f1;
    }

    public Groupe trouveGroupe(String nomGroupe) {
        Groupe g1 = null;
        List<Groupe> groupe = stockageGroupes.read();
        for (int i = 0; i < groupe.size(); i++) {
            if (groupe.get(i).getNomGroupe().equals(nomGroupe)) {
                g1 = groupe.get(i);
                break;
            }
        }
        return g1;
    }

    public SousGroupe trouveSousGroupe(String nomSousGroupe) {
        SousGroupe sg1 = null;
        List<SousGroupe> sousGroupe = stockageSousGroupes.read();
        for (int i = 0; i < sousGroupe.size(); i++) {
            if (sousGroupe.get(i).getNomSGroupe().equals(nomSousGroupe)) {
                sg1 = sousGroupe.get(i);
                break;
            }
        }
        return sg1;
    }

    public Enseignant trouverEnseignant(String nomEnseignant, String prenomEnseignant) {
        Enseignant e1 = null;
        List<Enseignant> enseignants = stockageEnseignants.read();
        for (int i = 0; i < enseignants.size(); i++) {
            if (enseignants.get(i).getNom().equals(nomEnseignant) && enseignants.get(i).getPrenom().equals(prenomEnseignant)) {
                e1 = enseignants.get(i);
                break;
            }
        }
        return e1;
    }

    public Ressource creerRessource(String nomRessource, String desRessource, String formationNom, double nbCM, double nbDS, double nbPE, double nbTD, double nbTP) throws ChaineValideException, InvalidParameterException, NombreHeuresTropGrand, NombreHeuresNegatif, RessourceDejaExistante {
        Formation f1 = trouverFormation(formationNom);
        Ressource r1 = new Ressource(nomRessource, desRessource, f1, nbCM, nbDS, nbPE, nbTD, nbTP);
        f1.ajouterRessource(r1);
        stockageRessources.ajouter(r1);
        return r1;
    }

    public Ressource trouveRessource(String nomRessource) {
        Ressource r1 = null;
        List<Ressource> ressources = stockageRessources.read();

        for (int i = 0; i < ressources.size(); i++) {
            if (ressources.get(i).getNom().equals(nomRessource)) {
                r1 = ressources.get(i);
                break;
            }
        }
        return r1;
    }

    public Groupe creerGroupe(String nomGroupe, Formation formation) throws ChaineValideException, RessourceInexistante {
        Groupe g1 = new Groupe(nomGroupe, formation);
        stockageGroupes.ajouter(g1);
        return g1;
    }

    public SousGroupe creerSousGroupe(Groupe groupe, String nomSousGroupe) throws ChaineValideException, SousGroupeDejaExistant, RessourceDejaExistante {
        SousGroupe d1 = new SousGroupe(nomSousGroupe);
        stockageSousGroupes.ajouter(d1);
        groupe.ajouterSousGroupe(d1);
        return d1;
    }

    public boolean supprimeGroupe(String nomGroupe) {
        List<Groupe> groupe = stockageGroupes.read();
        for (int i = 0; i < groupe.size(); i++) {
            Groupe g = groupe.get(i);
            if (g.getNomGroupe().equalsIgnoreCase(nomGroupe)) {
                groupe.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean supprimeProfesseur(String nomProfesseur) {
        List<Enseignant> enseignants = stockageEnseignants.read();

        for (int i = 0; i < enseignants.size(); i++) {
            Enseignant e = enseignants.get(i);
            if (e.getNom().equalsIgnoreCase(nomProfesseur)) {
                stockageEnseignants.supprimer(e);
                return true;
            }
        }
        return false;
    }

    public boolean supprimeRessource(String nomRessource) throws RessourceInexistante {
        List<Ressource> ressource = stockageRessources.read();
        for (int i = 0; i < ressource.size(); i++) {
            Ressource r = ressource.get(i);
            if (r.getNom().equalsIgnoreCase(nomRessource)) {
                r.getFormation().retirerRessource(r);
                ressource.remove(i);
                return true;
            }
        }
        return false;
    }

    public void initTestData() throws ChaineValideException, SousGroupeDejaExistant, NombreHeuresTropGrand, InvalidParameterException, RessourceDejaExistante, NombreHeuresNegatif, RessourceInexistante {
        List<Heures> heuresStatutaires1 = new ArrayList<>();
        Heures cm1 = new CM();
        heuresStatutaires1.add(cm1);
        Heures cm2 = new CM();
        heuresStatutaires1.add(cm2);
        Heures td1 = new TD();
        heuresStatutaires1.add(td1);
        Heures td2 = new TD();
        heuresStatutaires1.add(td2);
        Heures ds1 = new DS();
        heuresStatutaires1.add(ds1);
        Heures ds2 = new DS();
        heuresStatutaires1.add(ds2);
        Heures tp1 = new TP();
        heuresStatutaires1.add(tp1);
        Heures tp2 = new TP();
        heuresStatutaires1.add(tp2);
        Heures pe1 = new PE();
        heuresStatutaires1.add(pe1);
        Heures pe2 = new PE();
        heuresStatutaires1.add(pe2);
        creerEnseignant("Durand", "Salomé", 1, heuresStatutaires1, true);

        List<Heures> heuresStatutaires2 = new ArrayList<>();
        Heures cm = new CM();
        heuresStatutaires2.add(cm);
        Heures td = new TD();
        heuresStatutaires2.add(td);
        creerEnseignant("Dupont", "Stephanie", 3, heuresStatutaires2, false);

        creerEnseignant("Martin", "Jean", 2, heuresStatutaires2, false);

        Formation f1 = creerFormation("BUT");
        Groupe g1 = new Groupe("G1", f1);
        creerSousGroupe(g1, "G1-1");
        creerSousGroupe(g1, "G1-2");

        creerRessource("maths", "Les maths c'est super !", "BUT", 2, 5, 6, 1, 2);
    }
}
