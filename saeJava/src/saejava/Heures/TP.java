/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Heures;

import saejava.EnseignantExceptions.EnseignantNull;
import saejava.GroupeExceptions.GroupeNull;
import saejava.Ressource.Groupe;
import saejava.Enseignant.*;

/**
 * Représente une heure de type TP, hérite de la classe Heures. Un TP est
 * associé à un groupe et à un enseignant.
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class TP extends Heures {

    /**
     * Constructeur qui initialise un TP avec un groupe spécifique et un
     * enseignant. Il appelle le constructeur de la classe parente Heures pour
     * effectuer l'initialisation.
     *
     * @param groupe
     * @param enseignant
     * @throws saejava.GroupeExceptions.GroupeNull
     * @throws saejava.EnseignantExceptions.EnseignantNull
     */
    public TP(Groupe groupe, Enseignant enseignant) throws GroupeNull, EnseignantNull {
        super(groupe, enseignant);
    }

    public TP() {
        super();
    }

    @Override
    public double calculEquivalentTD() {
        return 1 * equivalentTP;
    }

}
