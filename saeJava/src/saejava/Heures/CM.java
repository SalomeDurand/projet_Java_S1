/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Heures;

import saejava.GroupeExceptions.GroupeVide;
import saejava.EnseignantExceptions.EnseignantNull;
import saejava.GroupeExceptions.GroupeNull;
import saejava.Ressource.Groupe;
import java.util.List;
import saejava.Enseignant.*;

/**
 * Représente une heure de type CM, hérite de la classe Heures. Un cours
 * magistral est associé à une liste de groupes et à un enseignant.
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class CM extends Heures {

    /**
     * Constructeur qui initialise un CM avec une liste de groupes et un
     * enseignant. Il appelle le constructeur de la classe parente Heures pour
     * effectuer l'initialisation.
     *
     * @param groupes
     * @param enseignant
     * @throws saejava.GroupeExceptions.GroupeNull
     * @throws saejava.EnseignantExceptions.EnseignantNull
     * @throws saejava.GroupeExceptions.GroupeVide
     */
    public CM(List<Groupe> groupes, Enseignant enseignant) throws GroupeNull, EnseignantNull, GroupeVide {
        super(groupes, enseignant);
    }

    public CM() {
        // Appelle le constructeur de la classe parente avec des valeurs par défaut.
        super();
    }

    @Override
    public double calculEquivalentTD() {
        return 1 * equivalentCM;
    }

}
