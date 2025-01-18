/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Ressource;

import saejava.RessourceExceptions.RessourceInexistante;
import saejava.RessourceExceptions.RessourceDejaExistante;
import java.util.ArrayList;
import java.util.List;
import saejava.GroupeExceptions.SousGroupeDejaExistant;
import saejava.GroupeExceptions.SousGroupeInexistant;
import saejava.exceptions.ChaineValideException;
import static utilitaire.Utilitaires.*;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class Groupe {

    public Groupe(String nomGroupe, Formation f) throws ChaineValideException, RessourceInexistante {
        this.nomGroupe = chaineValide(nomGroupe);
        this.formation = f;
        this.sousgroupes = new ArrayList<>();
        this.listeRessource = new ArrayList<>();
    }

    public void ajouterRessource(String nomRessource) throws RessourceDejaExistante {
        if (sousgroupes.isEmpty()) {
            if (listeRessource.contains(nomRessource)) {
                throw new RessourceDejaExistante("La ressource " + nomRessource + " est deja affecte a ce groupe");
            }
            listeRessource.add(nomRessource);
        } else {
            for (SousGroupe sousgroupe : sousgroupes) {
                sousgroupe.ajouterRessource(nomRessource);
            }
        }
    }

    public void retirerRessource(String nomRessource) throws RessourceInexistante {
        if (sousgroupes.isEmpty()) {
            if (!listeRessource.contains(nomRessource)) {
                throw new RessourceInexistante("La ressource " + nomRessource + "n'est pas affecte a ce groupe");
            }
            listeRessource.remove(nomRessource);
        } else {
            for (SousGroupe sousgroupe : sousgroupes) {
                sousgroupe.retirerRessource(nomRessource);
            }
        }
    }

    public void ajouterSousGroupe(SousGroupe dg) throws SousGroupeDejaExistant, RessourceDejaExistante {
        if (sousgroupes.contains(dg)) {
            throw new SousGroupeDejaExistant("Le sous-groupe " + dg.getNomSGroupe() + " est deja affecte a ce groupe");
        }
        sousgroupes.add(dg);
    }

    public void retirerSousGroupe(SousGroupe dg) throws SousGroupeInexistant, RessourceInexistante {
        if (!sousgroupes.contains(dg)) {
            throw new SousGroupeInexistant("Le sous-groupe " + dg.getNomSGroupe() + "n'est pas affecte a ce groupe");
        }
        sousgroupes.remove(dg);

    }

    public boolean hasSousGroupe(String nom) {
        for (SousGroupe sg : sousgroupes) {
            if (sg.getNomSGroupe().equals(nom)) {
                return true;
            }
        }

        return false;
    }

    public String afficherGroupe() {
        String liste = "";
        for (int i = 0; i < sousgroupes.size(); i++) {
            liste += sousgroupes.get(i).getNomSGroupe() + " ,";
        }
        return liste;
    }

    public String afficherSGroupe() {
        ArrayList<String> noms = new ArrayList<>();
        for (SousGroupe sg : sousgroupes) {
            noms.add(sg.getNomSGroupe());
        }
        return String.join(", ", noms);
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public List<SousGroupe> getSousGroupe() {
        return sousgroupes;
    }

    public Formation getFormation() {
        return formation;
    }

    public List<String> getListeRessource() {
        return listeRessource;
    }

    @Override
    public String toString() {
        return "\nGroupe : " + nomGroupe + "\n"
                + "Formation : " + formation.getNomFormation() + "\n"
                + "Ressources : " + this.getListeRessource() + "\n"
                + "Sous-groupes : " + afficherSGroupe() + "\n"
                + "******************************\n";
    }

    private final String nomGroupe;
    private final List<String> listeRessource;
    private final List<SousGroupe> sousgroupes;
    private final Formation formation;
}
