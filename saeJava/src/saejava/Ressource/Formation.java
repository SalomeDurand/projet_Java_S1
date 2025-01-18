/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Ressource;

import saejava.RessourceExceptions.*;
import java.util.ArrayList;
import java.util.List;
import saejava.GroupeExceptions.*;
import saejava.exceptions.ChaineValideException;
import static utilitaire.Utilitaires.chaineValide;

/**
 * Une formation
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class Formation {

    public Formation(String nomFormation) throws ChaineValideException {
        this.nomFormation = chaineValide(nomFormation);
        this.listeRessource = new ArrayList<>();
        this.groupes = new ArrayList<>();
    }

    public void ajouterRessource(Ressource r) throws RessourceDejaExistante {
        if (listeRessource.contains(r)) {
            throw new RessourceDejaExistante("La ressource " + r.getNom() + " fait deja partie de cette formation");
        }
        listeRessource.add(r);
    }

    public void retirerRessource(Ressource r) throws RessourceInexistante {
        if (!listeRessource.contains(r)) {
            throw new RessourceInexistante("La ressource " + r.getNom() + " ne fait pas partie de cette formation");
        }
        listeRessource.remove(r);
    }

    public void ajouterGroupe(Groupe g) throws GroupeDejaExistant, RessourceInexistante, RessourceDejaExistante {
        if (groupes.contains(g)) {
            throw new GroupeDejaExistant("Le groupe " + g.getNomGroupe() + "fait deja partie de cette formation");
        }
        groupes.add(g);
        for (Ressource s : listeRessource) {
            g.ajouterRessource(s.getNom());
        }
    }

    public String afficherRessources() {
        String liste = "";
        for (Ressource r : listeRessource) {
            liste += r.getNom() + " ,";
        }
        return liste;
    }

    public String afficherGroupe() {
        String liste = "";
        for (Groupe g : groupes) {
            liste += g.getNomGroupe() + " ,";
        }
        return liste;
    }

    public double calculerCoutFormation() throws RessourceInexistante {
        if (listeRessource.isEmpty()) {
            throw new RessourceInexistante("Aucune ressource n'a ete ajoutee a cette formation");
        }
        double coutf = 0;
        for (Ressource r : listeRessource) {
            coutf += r.calculerCoutTotal();
        }
        return coutf;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public List<Ressource> getRessource() {
        return listeRessource;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    @Override
    public String toString() {
        return "\n\tNom de la formation :" + nomFormation
                + "\n\tRessources de la formation :" + afficherRessources()
                + "\n\tGroupes de la formation :" + afficherGroupe()
                + "\n\n\t *********************************************************\n";
    }

    private final String nomFormation;
    private final List<Ressource> listeRessource;
    private final List<Groupe> groupes;
}
