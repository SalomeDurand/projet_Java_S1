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
 * Représente une heure de type PE, hérite de la classe Heures. Un PE est
 * associé à un groupe et à un enseignant.
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class PE extends Heures {

    /**
     * Constructeur de la classe PE qui initialise les heures de PE avec un
     * groupe spécifique et un enseignant. Il appelle le constructeur de la
     * classe parente Heures pour effectuer l'initialisation.
     *
     * @param groupe
     * @param enseignant
     * @throws saejava.GroupeExceptions.GroupeNull
     * @throws saejava.EnseignantExceptions.EnseignantNull
     */
    public PE(Groupe groupe, Enseignant enseignant) throws GroupeNull, EnseignantNull {
        super(groupe, enseignant);
    }

    public PE() {
        super();
    }

}
