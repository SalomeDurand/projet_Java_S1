/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava.Enseignant;

import saejava.Heures.Heures;
import saejava.exceptions.ChaineValideException;
import saejava.Ressource.Ressource;
import java.util.ArrayList;
import java.util.List;
import saejava.EnseignantExceptions.EnseignantNull;
import saejava.RessourceExceptions.RessourceInexistante;
import static utilitaire.Utilitaires.*;

/**
 * Représente un enseignant dans un département universitaire, avec différents
 * types de statuts : titulaire, vacataire non titulaire, ou vacataire
 * titulaire.
 * <p>
 * - Le vacataire non titulaire est payé en fonction du total d'heures
 * effectuées pendant l'année scolaire (1er septembre - 30 juin). - Le titulaire
 * bénéficie d'un salaire de base correspondant à un nombre d'heures statutaire
 * (entre 64h et 384h), ainsi que des heures supplémentaires rémunérées (sous
 * certaines conditions). En cas de sous-service, le salaire est réduit
 * proportionnellement. - Le vacataire titulaire est payé comme un titulaire,
 * mais sans obligation de vérifier le respect des heures statutaires.
 * </p>
 * Cette classe est abstraite et sera étendue par des classes spécifiques pour
 * calculer le salaire selon le type de contrat de l'enseignant.
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
// Classe de base Enseignant
public abstract class Enseignant {

    protected final String nom;
    protected final String prenom;
    protected List<Ressource> ressources;

    /**
     * Constructeur de la classe Enseignant qui initialise l'enseignant avec un
     * nom et un prénom.
     *
     * @param nom Le nom de l'enseignant.
     * @param prenom Le prénom de l'enseignant.
     * @throws saejava.exceptions.ChaineValideException
     */
    public Enseignant(String nom, String prenom) throws ChaineValideException {
        this.nom = chaineValide(nom);
        this.prenom = chaineValide(prenom);
        this.ressources = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public List<Ressource> getListeRessource() {
        return ressources;
    }

    /**
     * Ajoute une ressource à la liste des ressources de l'enseignant si elle
     * n'est pas déjà présente.
     *
     * @param nomRessource La ressource à ajouter.
     */
    public void ajouterRessource(Ressource nomRessource) {
        if (!ressources.contains(nomRessource)) {
            ressources.add(nomRessource);
        }
    }

    /**
     * Retire une ressource de la liste des ressources de l'enseignant si elle
     * existe.
     *
     * @param nomRessource La ressource à retirer.
     * @throws saejava.RessourceExceptions.RessourceInexistante
     */
    public void retirerRessource(Ressource nomRessource) throws RessourceInexistante {
        if (ressources.contains(nomRessource)) {
            ressources.remove(nomRessource);
        } else {
            throw new RessourceInexistante("La ressource à retirer n'est pas présente dans la collection.");
        }
    }

    @Override
    public String toString() {
        return "\nNom : " + nom
                + "\nPrénom : " + prenom + "\n"
                + "******************************\n";
    }

    /**
     * Récupère la liste totale des heures de l'enseignant en récupèrant les
     * heures de toutes les ressources auxquelles il est associé.
     *
     * @return Une liste contenant toutes les heures de l'enseignant.
     * @throws saejava.EnseignantExceptions.EnseignantNull
     */
    protected List<Heures> getHeuresTotales() throws EnseignantNull {
        List<Heures> heuresTotales = new ArrayList<>();
        for (Ressource ressource : this.ressources) {
            List<Heures> heuresRessource = ressource.getHeuresPourEnseignant(this);
            heuresTotales.addAll(heuresRessource);
        }
        return heuresTotales;
    }

    /**
     * Calcule le nombre total d'heures d'enseignement de l'enseignant en
     * équivalent TD
     *
     * @return Le total des heures d'enseignement de l'enseignant sous forme
     * d'une valeur en heures de TD.
     * @throws saejava.EnseignantExceptions.EnseignantNull
     */
    protected double calculerHeuresTotalesEquivalentTD() throws EnseignantNull {
        double total = 0;

        for (Heures h : this.getHeuresTotales()) {
            total += h.calculEquivalentTD();
        }

        return total;
    }

    /**
     * Calcule le salaire de l'objet actuel.
     *
     * Cette méthode est abstraite et doit être implémentée par les sous-classes
     * pour calculer le salaire en fonction des critères spécifiques à chaque
     * type d'objet.
     *
     * @return le salaire calculé; Le salaire peut être basé sur plusieurs
     * critères comme le nombre d'heures, les primes, les taux horaires, etc.,
     * selon l'implémentation de la sous-classe.
     */
    public abstract double calculerSalaire();
}
