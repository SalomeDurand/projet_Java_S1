/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Heures;

import saejava.GroupeExceptions.GroupeVide;
import saejava.EnseignantExceptions.EnseignantVide;
import saejava.EnseignantExceptions.EnseignantNull;
import saejava.GroupeExceptions.GroupeNull;
import saejava.Ressource.Groupe;
import java.util.List;
import saejava.Enseignant.*;

/**
 * Représente une heure de type DS, hérite de la classe Heures. Un devoir sur
 * table peut être associé soit à un groupe et un enseignant, soit à une liste
 * de groupes et une liste d'enseignants. La classe gère différents types de DS.
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class DS extends Heures {

    /**
     * Constructeur de la classe DS avec un seul groupe et un seul enseignant.
     * Initialise un DS avec un groupe spécifique et un enseignant, puis appelle
     * le constructeur de la classe parente Heures pour effectuer
     * l'initialisation.
     *
     * @param groupe
     * @param enseignant
     * @throws saejava.GroupeExceptions.GroupeNull
     * @throws saejava.EnseignantExceptions.EnseignantNull
     */
    public DS(Groupe groupe, Enseignant enseignant) throws GroupeNull, EnseignantNull {
        super(groupe, enseignant);
    }

    /**
     * Constructeur de la classe DS avec plusieurs groupes et plusieurs
     * enseignants. Initialise un DS avec une liste de groupes et une liste
     * d'enseignants, puis appelle le constructeur de la classe parente Heures
     * pour effectuer l'initialisation.
     *
     * @param groupes
     * @param enseignants
     * @throws saejava.GroupeExceptions.GroupeNull
     * @throws saejava.GroupeExceptions.GroupeVide
     * @throws saejava.EnseignantExceptions.EnseignantVide
     * @throws saejava.EnseignantExceptions.EnseignantNull
     */
    public DS(List<Groupe> groupes, List<Enseignant> enseignants) throws GroupeNull, GroupeVide, EnseignantVide, EnseignantNull {
        super(groupes, enseignants);
    }

    public DS() {
        super();
    }

}
