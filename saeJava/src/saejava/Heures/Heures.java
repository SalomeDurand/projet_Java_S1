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
import saejava.Enseignant.*;
import saejava.Ressource.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe abstraite Heures représente une gestion d'heures pour des groupes et
 * des enseignants. Elle permet de gérer différents types de cours : CM, TP, TD,
 * PE, DS, et de calculer les équivalences horaires en fonction du type de
 * cours.
 * <p>
 * Il y a plusieurs constructeurs pour initialiser les groupes et les
 * enseignants, ainsi que des méthodes pour ajouter de nouveaux groupes ou
 * enseignants et pour effectuer des calculs d'équivalences horaires.
 * </p>
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public abstract class Heures {

    /**
     * Constructeur de la classe Heures pour un seul groupe et un seul
     * enseignant. Crée une liste de groupes et une liste d'enseignants, et y
     * ajoute respectivement le groupe et l'enseignant fournis en paramètre.
     *
     * @param groupe
     * @param enseignant
     * @throws saejava.GroupeExceptions.GroupeNull
     * @throws saejava.EnseignantExceptions.EnseignantNull
     */
    public Heures(Groupe groupe, Enseignant enseignant) throws GroupeNull, EnseignantNull {
        if (groupe == null) {
            throw new GroupeNull("Le groupe ne peut pas être null");
        }
        if (enseignant == null) {
            throw new EnseignantNull("L'enseignant ne peut pas être null");
        }

        this.groupes = new ArrayList<>();
        this.sousgroupes = new ArrayList<>();
        this.groupes.add(groupe);
        this.enseignants = new ArrayList<>();
        this.enseignants.add(enseignant);
    }

    public Heures() {
        this.sousgroupes = new ArrayList<>();

        this.groupes = new ArrayList<>();
        this.enseignants = new ArrayList<>();

    }

    /**
     * Constructeur de la classe Heures pour plusieurs groupes et un seul
     * enseignant. Crée une liste de groupes à partir de la liste fournie en
     * paramètre, et une liste d'enseignants contenant l'enseignant unique
     * fourni.
     *
     * @param groupes
     * @param enseignant
     * @throws saejava.GroupeExceptions.GroupeNull
     * @throws saejava.EnseignantExceptions.EnseignantNull
     * @throws saejava.GroupeExceptions.GroupeVide
     */
    public Heures(List<Groupe> groupes, Enseignant enseignant) throws GroupeNull, EnseignantNull, GroupeVide {
        this.sousgroupes = new ArrayList<>();

        if (groupes == null) {
            throw new GroupeNull("La liste des groupes ne peut pas être null");
        }
        if (enseignant == null) {
            throw new EnseignantNull("L'enseignant ne peut pas être null");
        }
        if (groupes.isEmpty()) {
            throw new GroupeVide("La liste des groupes ne peut pas être vide.");
        }

        this.groupes = new ArrayList<>(groupes);
        this.enseignants = new ArrayList<>();
        this.enseignants.add(enseignant);

    }

    /**
     * Constructeur de la classe Heures pour plusieurs groupes et plusieurs
     * enseignants. Crée des listes de groupes et d'enseignants en fonction des
     * paramètres fournis.
     *
     * @param groupes
     * @param enseignants
     * @throws saejava.GroupeExceptions.GroupeNull
     * @throws saejava.EnseignantExceptions.EnseignantNull
     * @throws saejava.GroupeExceptions.GroupeVide
     * @throws saejava.EnseignantExceptions.EnseignantVide
     */
    public Heures(List<Groupe> groupes, List<Enseignant> enseignants) throws GroupeNull, EnseignantNull, GroupeVide, EnseignantVide {
        this.sousgroupes = new ArrayList<>();
        if (groupes == null) {
            throw new GroupeNull("La liste des groupes ne peut pas être null");
        }
        if (enseignants == null) {
            throw new EnseignantNull("La liste d'enseignants ne peut pas être null");
        }
        if (groupes.isEmpty()) {
            throw new GroupeVide("La liste des groupes ne peut pas être vide.");
        }
        if (enseignants.isEmpty()) {
            throw new EnseignantVide("La liste des groupes ne peut pas être vide.");
        }

        this.groupes = new ArrayList<>(groupes);
        this.enseignants = new ArrayList<>(enseignants);
    }

    //getters et setters
    /**
     * Retourne la liste des groupes associés à ces heures.
     *
     * @return La liste des groupes.
     */
    public List<Groupe> getGroupes() {
        return groupes;
    }

    /**
     * Retourne la liste des sous-groupes associés à ces heures.
     *
     * @return La liste des sous-groupes.
     */
    public List<SousGroupe> getSousGroupes() {

        return sousgroupes;
    }

    /**
     * Retourne la liste des enseignants associés à ces heures.
     *
     * @return La liste des enseignants.s
     */
    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    //Les coefficients sont suceptibles de changer selon les universités
    public static double getEquivalentCM() {
        return equivalentCM;
    }

    public static double getEquivalentTP() {
        return equivalentTP;
    }

    public static void setEquivalentCM(double eqCM) {
        equivalentCM = eqCM;
    }

    public static void setEquivalentTP(double eqTP) {
        equivalentTP = eqTP;
    }

    //Le tarif est suceptible de changer dans le temps
    public static double getTarif() {
        return tarif;
    }

    public static void setTarif(double tar) {
        tarif = tar;
    }

    /**
     * Ajoute un nouveau groupe à la liste des groupes si pas déjà présent.
     *
     * @param nouveauGroupe Le groupe à ajouter à la liste.
     */
    public void ajouterGroupe(Groupe nouveauGroupe) {
        if (!this.groupes.contains(nouveauGroupe)) {
            this.groupes.add(nouveauGroupe);
        }
    }

    /**
     * Retire un groupe à la liste des groupes.
     *
     * @param nouveauGroupe Le groupe à supprimer de la liste.
     */
    public void retirerGroupe(Groupe nouveauGroupe) {
        this.groupes.remove(nouveauGroupe);
    }

    /**
     * Ajoute un nouveau sous-groupe à la liste des sous-groupes.
     *
     * @param dg Le sous-groupe à ajouter à la liste.
     */
    public void ajouterSGroupe(SousGroupe dg) {
        this.sousgroupes.add(dg);
    }

    /**
     * Retire un sous-groupe à la liste des sous-groupes.
     *
     * @param dg Le sous-groupe à supprimer de la liste.
     */
    public void retirerSousGroupe(SousGroupe dg) {
        this.sousgroupes.remove(dg);
    }

    /**
     * Ajoute un nouvel enseignant à la liste des enseignants.
     *
     * @param nouveauEnseignant L'enseignant à ajouter à la liste.
     */
    public void ajouterEnseignant(Enseignant nouveauEnseignant) {
        if (!this.enseignants.contains(nouveauEnseignant)) {
            this.enseignants.add(nouveauEnseignant);
        }
    }

    /**
     * Retire un enseignant de la liste des enseignants.
     *
     * @param nouveauEnseignant L'enseignant à supprimer de la liste.
     */
    public void retirerEnseignant(Enseignant nouveauEnseignant) {
        this.enseignants.remove(nouveauEnseignant);
    }

    //méthodes métier
    /**
     * Calcule l'équivalent en heures TD en fonction du type de cours et du
     * nombre d'heures. Effectue une conversion selon le type de cours.
     *
     *
     * @return Le nombre d'heures converti en équivalent TD.
     */
    public double calculEquivalentTD() {
        return 1;
    }

    @Override
    public String toString() {
        return "" + this.getClass().getSimpleName();
    }

    //attributs
    /**
     * Liste des groupes associés à ces heures.
     */
    private List<Groupe> groupes;
    private List<SousGroupe> sousgroupes;

    /**
     * Liste des enseignants associés à ces heures.
     */
    private List<Enseignant> enseignants;

    //Constantes susceptibles de changer selon les formations
    //Il faudra demander aux secrétaires les équivalents et les tarifs dans la console
    //Ils sont mis en static car les membres statiques dans une classe abstraite 
    //permettent de regrouper des valeurs ou des comportements qui sont partagés par toutes les sous-classes.
    protected static double equivalentCM;
    protected static double equivalentTP;

    private static double tarif;

}
