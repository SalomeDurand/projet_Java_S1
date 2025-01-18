/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Ressource;

import saejava.RessourceExceptions.RessourceInexistante;
import saejava.RessourceExceptions.RessourceDejaExistante;
import saejava.exceptions.ChaineValideException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static utilitaire.Utilitaires.*;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class SousGroupe {

    public SousGroupe(String nomSousGroupe) throws ChaineValideException {
        this.nomSousGroupe = chaineValide(nomSousGroupe);
        this.listeRessource = new ArrayList<>();
    }

    public void ajouterRessource(String nomRessource) throws RessourceDejaExistante {
        if (listeRessource.contains(nomRessource)) {
            throw new RessourceDejaExistante("La ressource " + nomRessource + " est deja affectee a ce sousgroupe");
        }
        listeRessource.add(nomRessource);
    }

    public void retirerRessource(String nomRessource) throws RessourceInexistante {
        if (!listeRessource.contains(nomRessource)) {
            throw new RessourceInexistante("La ressource " + nomRessource + " n'a pas ete ajoutee a ce sousgroupe");
        }
        listeRessource.remove(nomRessource);
    }

    public List<String> getListeRessource() {
        return listeRessource;
    }

    @Override
    public String toString() {
        return " Sous-groupe:" + nomSousGroupe
                + " Ressources :" + listeRessource;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final SousGroupe other = (SousGroupe) obj;
        return this.nomSousGroupe.equals(other.nomSousGroupe);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nomSousGroupe);
        hash = 59 * hash + Objects.hashCode(this.listeRessource);
        return hash;
    }

    public String getNomSGroupe() {
        return nomSousGroupe;
    }

    private final String nomSousGroupe;
    private final List<String> listeRessource;
}
