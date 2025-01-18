package saejava.Ressource;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
import saejava.EnseignantExceptions.*;
import saejava.GroupeExceptions.*;
import saejava.RessourceExceptions.*;
import saejava.exceptions.ChaineValideException;
import saejava.HeureExceptions.*;
import java.security.InvalidParameterException;
import saejava.Enseignant.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import saejava.GroupeExceptions.GroupeException;
import saejava.Heures.*;
import utilitaire.Utilitaires;
import static utilitaire.Utilitaires.*;

/**
 * Représente une ressource associée à une formation. Elle permet de gérer les
 * différents attributs d'une ressource tels que son nom, sa description, son
 * identifiant unique, les heures associées à différents types de cours (CM, TD,
 * PE, TP, DS) et les groupes ou sous-groupes qui lui sont affectés.
 *
 * <p>
 * Une ressource est toujours associée à une formation, et ses attributs doivent
 * être valides selon les règles métier définies.</p>
 *
 * <p>
 * Cette classe lève des exceptions en cas de non-respect des contraintes sur
 * ses attributs.</p>
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class Ressource {

    /**
     * Crée une nouvelle ressource avec ses caractéristiques principales. Chaque
     * ressource possède un identifiant unique généré automatiquement basé sur
     * son nom. Les heures pour les différents types de cours doivent être
     * spécifiées.
     *
     * @param nom le nom de la ressource (ne doit pas être nul ou vide)
     * @param description une description détaillée de la ressource (ne doit pas
     * être nulle ou vide)
     * @param formation la formation à laquelle la ressource est associée (ne
     * peut pas être nulle)
     * @param nbCM le nombre d'heures de CM
     * @param nbDS le nombre d'heures de DS
     * @param nbPE le nombre d'heures de PE
     * @param nbTD le nombre d'heures de TD
     * @param nbTP le nombre d'heures de TP
     * @throws InvalidParameterException si la formation est nulle
     * @throws ChaineValideException si le nom ou la description sont invalides
     * (nuls ou vides)
     * @throws NombreHeuresNegatif si l'une des valeurs d'heures est négative
     * @throws NombreHeuresTropGrand si l'une des valeurs d'heures dépasse 6000
     * @throws RessourceDejaExistante si une ressource avec le même nom est déjà
     * associée à la formation
     */
    public Ressource(
            String nom,
            String description,
            Formation formation,
            double nbCM,
            double nbDS,
            double nbPE,
            double nbTD,
            double nbTP
    ) throws InvalidParameterException, ChaineValideException, NombreHeuresNegatif, NombreHeuresTropGrand, RessourceDejaExistante {
        this.nom = chaineValide(nom);
        this.id = nom + ++idR;
        this.description = chaineValide(description);
        if (formation == null) {
            throw new InvalidParameterException("Formation inexistante");
        }
        this.formation = formation;
        this.groupes = new ArrayList<>();
        this.sousgroupes = new ArrayList<>();
        this.heuresEnseignants = new HashMap<>();

        this.heuresParTypesCours = new HashMap<>();
        validerEtAjouter(CM.class, nbCM);
        validerEtAjouter(DS.class, nbDS);
        validerEtAjouter(PE.class, nbPE);
        validerEtAjouter(TD.class, nbTD);
        validerEtAjouter(TP.class, nbTP);

    }

    //Méthode pour une gestion des exceptions optimales des heures ajoutées
    private void validerEtAjouter(Class<? extends Heures> typeCours,
            double nbHeures) throws NombreHeuresNegatif, NombreHeuresTropGrand {
        if (nbHeures < 0) {
            throw new NombreHeuresNegatif("Le nombre d'heures de "
                    + typeCours.getSimpleName() + " est negatif");
        }
        if (nbHeures > 6000) {
            throw new NombreHeuresTropGrand("Le nombre d'heures pour "
                    + typeCours.getSimpleName() + " est trop grand");
        }
        this.heuresParTypesCours.put(typeCours, nbHeures);

    }

    /**
     * Modifie le nombre d'heures pour un type d'heure pour la ressource. Ce
     * setter permet de définir pour la ressource un type d'heure (CM ou TD ou
     * PE ou TP ou DS) et un nombre d'heure correspondant.
     *
     * @param t le type d'heures
     * @param h le nombre d'heures
     * @throws NombreHeuresNegatif si le nombre d'heures défini est négatif
     * @throws NombreHeuresTropGrand si le nombre d'heures défini est supérieur
     * à 6000
     */
    public void setHeuresType(Class<? extends Heures> t,
            double h) throws NombreHeuresNegatif, NombreHeuresTropGrand {
        if (h < 0) {
            throw new NombreHeuresNegatif("Le nombre d'heures pour "
                    + t.getSimpleName() + " est negatif");
        }
        if (h > 6000) {
            throw new NombreHeuresTropGrand("Le nombre d'heures pour "
                    + t.getSimpleName() + " est trop grand");
        }
        this.heuresParTypesCours.replace(t, h);
    }

    //Methode pour vérifier que toutes les heures sont des TP
    private boolean toutesLesHeuresSontTP() throws AucuneHeureDefinie {
        if (heuresParTypesCours.isEmpty()) {
            throw new AucuneHeureDefinie("Aucune heure n'a été "
                    + "affecté à cette ressource");
        }
        for (Class t : this.heuresParTypesCours.keySet()) {
            if (t == TP.class) {
                continue;
            }
            if (this.heuresParTypesCours.get(t) > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Affecte un groupe à la ressource. Elle vérifie que toutes les heures ne
     * sont pas des TP, puis affecte le groupe à la ressource.
     *
     * @param g le groupe à affecter
     * @throws ToutesHeuresTP si toutes les heures sont des TP
     * @throws GroupeDejaExistant si le groupe est déjà affecté à la ressource
     * @throws AucuneHeureDefinie si on ne peut pas vérifier que toutes les
     * heures sont des TP
     * @throws RessourceDejaExistante si le groupe suit déjà cette ressource
     */
    public void enseignerGroupe(Groupe g)
            throws ToutesHeuresTP, GroupeDejaExistant, AucuneHeureDefinie, RessourceDejaExistante {
        if (toutesLesHeuresSontTP()) {
            throw new ToutesHeuresTP("Toutes les heures sont des TP, il faut associer des sous-groupes.");
        }

        if (groupes.contains(g)) {
            throw new GroupeDejaExistant("Le groupe " + g.getNomGroupe() + " est"
                    + " deja affecte à cette ressource");
        }

        g.ajouterRessource(this.getNom());
        this.groupes.add(g);
    }

    /**
     * Affecte un sousgroupe à la ressource. Elle vérifie que toutes les heures
     * sont des TP, puis affecte le sousgroupe à la ressource.
     *
     * @param dg le sousgroupe à affecter
     * @throws ToutesHeuresNonTP si toutes les heures ne sont pas des TP
     * @throws SousGroupeDejaExistant si le sousgroupe est déjà affecté à la
     * ressource
     * @throws AucuneHeureDefinie si on ne peut pas vérifier que toutes les
     * heures sont des TP
     * @throws RessourceDejaExistante si le sousgroupe suit déjà cette ressource
     */
    public void enseignerSousGroupe(SousGroupe dg)
            throws ToutesHeuresNonTP, SousGroupeDejaExistant, AucuneHeureDefinie, RessourceDejaExistante {
        if (!toutesLesHeuresSontTP()) {
            throw new ToutesHeuresNonTP("Pour affecter un sous-groupe à une ressource, toutes les heures doivent être des TP.");
        }

        if (sousgroupes.contains(dg)) {
            throw new SousGroupeDejaExistant("Le sousgroupe " + dg.getNomSGroupe()
                    + " est deja affecte à cette ressource");
        }
        dg.ajouterRessource(this.getNom());
        this.sousgroupes.add(dg);
    }

    /**
     * Calcule l'équivalent TD des heures réalisées par les enseignants.
     *
     * @return L'équivalent TD des heures réalisées
     */
    public double calculHeuresEnseignantsEquivalentTD() {
        double total = 0;
        if (heuresEnseignants.isEmpty()) {
            return total;
        }

        for (List<Heures> heures : this.heuresEnseignants.values()) {
            for (Heures heure : heures) {
                total += heure.calculEquivalentTD();
            }
        }
        return total;
    }

    /**
     * Calcule le coût de la ressource selon le tarif défini.
     *
     * @return Coût total de la ressource
     */
    public double calculerCoutTotal() {
        return this.calculHeuresEnseignantsEquivalentTD() * Heures.getTarif();
    }

    /**
     * Retourne le nombre d'heures effectuée pour un enseignant dans cette
     * ressource
     *
     * @param enseignant l'enseigant dont on veut connaître les heures réalisées
     * @return Nombre d'heures pour l'enseignant demandé
     * @throws EnseignantNull si l'enseignant n'est pas affecté à la ressource
     */
    public List<Heures> getHeuresPourEnseignant(Enseignant enseignant)
            throws EnseignantNull {
        if (!this.heuresEnseignants.containsKey(enseignant)) {
            throw new EnseignantNull("Cet enseignant n'est pas lié à cette ressource.");
        }

        for (Enseignant currentEnseignant : this.heuresEnseignants.keySet()) {
            if (enseignant == currentEnseignant) {
                return this.heuresEnseignants.get(currentEnseignant);
            }
        }

        return new ArrayList<>();
    }

    /**
     * Vérifie si la liste des enseignants de la ressource est vide.
     *
     * @return true si aucun enseignant n'est affecté à la ressource
     */
    public boolean estDispensee() {
        return this.heuresEnseignants.isEmpty();
    }

    /**
     * Vérifie que toutes les heures de la ressource sont affectées à un
     * enseignant.
     *
     * @return true si toutes les heures sont affectées
     * @throws AucuneHeureDefinie si aucune heure n'a été réalisée
     */
    public boolean toutesHeuresAffectees() throws AucuneHeureDefinie {
        if (heuresEnseignants.isEmpty()) {
            throw new AucuneHeureDefinie("Aucune heure n'a ete realisee dans cette ressource");
        }
        for (Class typeCours : typesCours) {
            double totalAttendu = this.heuresParTypesCours.get(typeCours);
            double totalAssigne = 0;

            for (List<Heures> heures : this.heuresEnseignants.values()) {
                totalAssigne += Utilitaires.getNbHeuresParType(heures, typeCours);
            }

            if (totalAttendu != totalAssigne) {
                return false;
            }
        }

        return true;
    }

    /**
     * Retire le groupe de la ressource. Cette méthode permet de retirer le
     * groupe associé à la ressource, et donc de dissocier le groupe de la
     * ressource.
     *
     * @param g le groupe à retirer
     * @throws GroupeNull si le groupe n'est pas affecté à la ressource
     * @throws RessourceInexistante si le groupe ne suit pas cette ressource
     */
    public void retirerGroupe(Groupe g) throws GroupeNull, RessourceInexistante {
        if (!groupes.contains(g)) {
            throw new GroupeNull("Ce groupe n'est pas "
                    + "affecte à cette ressource");
        }

        g.retirerRessource(this.getNom());
        this.groupes.remove(g);
    }

    /**
     * Retire le sousgroupe de la ressource. Cette méthode permet de retirer le
     * sousgroupe associé à la ressource, et donc de dissocier le sousgroupe de
     * la ressource.
     *
     * @param dg le sousgroupe à retirer
     * @throws SousGroupeInexistant si le sousgroupe n'est pas affecté à la
     * ressource
     * @throws RessourceInexistante si le sousgroupe ne suit pas cette ressource
     */
    public void retirerSousGroupe(SousGroupe dg) throws SousGroupeInexistant, RessourceInexistante {
        if (!sousgroupes.contains(dg)) {
            throw new SousGroupeInexistant("Ce sousgroupe n'est pas "
                    + "affecte à cette ressource");
        }
        dg.retirerRessource(this.getNom());
        this.sousgroupes.remove(dg);
    }

    /**
     * Ajoute un enseignant ainsi que ses heures à effectuer à la ressource. En
     * ajoutant l'enseignant à cette ressource, elle l'ajoute également à la
     * liste de ressource de l'enseignant.
     *
     * @param ens l'enseignant à ajouter
     * @param heures la liste d'heure qu'il va effectuer
     * @throws EnseignantDejaExistant si l'enseignant est déjà affecté à la
     * ressource
     */
    public void ajouterEnseignant(Enseignant ens, List<Heures> heures)
            throws EnseignantDejaExistant {
        if (this.heuresEnseignants.containsKey(ens)) {
            throw new EnseignantDejaExistant("Cet enseignant est "
                    + "déjà affecte à cette ressource");
        }
        this.heuresEnseignants.put(ens, heures);
        ens.ajouterRessource(this);
    }

    /**
     * Retire un enseignant de la ressource. En retirant l'enseignant à cette
     * ressource, elle la retire également de la liste de ressources de
     * l'enseignant.
     *
     * @param ens l'enseignant à retirer
     * @throws EnseignantNull si l'enseignant n'est pas affecté à cette
     * ressource
     * @throws RessourceInexistante si la ressource n'est pas dans la liste des
     * ressources de l'enseignant
     */
    public void retirerEnseignant(Enseignant ens) throws EnseignantNull, RessourceInexistante {
        if (!this.heuresEnseignants.containsKey(ens)) {
            throw new EnseignantNull("Cet enseignant n'est "
                    + "pas lie a cette ressource.");
        }
        this.heuresEnseignants.remove(ens);
        ens.retirerRessource(this);
    }

    /**
     * Affiche la liste des groupes pour cette ressource.
     *
     * @return le nom des groupes.
     */
    public String afficherGroupe() {
        if (sousgroupes.isEmpty() & groupes.isEmpty()) {
            System.out.println("Aucun n'est affecté à la ressource.");
        }
        ArrayList<String> noms = new ArrayList<>();
        for (Groupe g : groupes) {
            noms.add(g.getNomGroupe());
        }
        return String.join(", ", noms);
    }

    /**
     * Affiche la liste des sousgroupes pour cette ressource.
     *
     * @return le nom des sousgroupes.
     */
    public String afficherSGroupe() {
        if (groupes.isEmpty() & sousgroupes.isEmpty()) {
            System.out.println("Aucun sousgroupe n'est "
                    + "affecte a la ressource " + nom);
        }
        ArrayList<String> noms = new ArrayList<>();
        for (SousGroupe sg : sousgroupes) {
            noms.add(sg.getNomSGroupe());
        }
        return String.join(", ", noms);
    }

    /**
     * Affiche la liste des enseignants pour cette ressource.
     *
     * @return Chaîne de caractère contenant le nom des enseignants.
     * @throws EnseignantVide si aucun enseignant n'est affecté à la ressource
     */
    public String afficherEnseignants() throws EnseignantVide {
        if (heuresEnseignants.isEmpty()) {
            System.out.println("Aucun enseignant n'a ete "
                    + "affecte a la ressource " + nom);
        }
        ArrayList<String> noms = new ArrayList<>();
        for (Enseignant ens : this.heuresEnseignants.keySet()) {
            noms.add(ens.getNom());
        }
        return String.join(", ", noms);

    }

    /**
     * Affiche la liste des heures pour cette ressource.
     *
     * @return Chaîne de caractère contenant la liste des heures.
     */
    public String afficherHeures() {
        ArrayList<String> heures = new ArrayList<>();

        for (Class typeCours : typesCours) {
            heures.add(typeCours.getSimpleName() + " : " + this.heuresParTypesCours.get(typeCours));
        }

        return String.join(", ", heures);
    }

    /**
     * Affiche les attributs d'une ressource
     *
     * @return Une ressource
     */
    @Override
    public String toString() {
        try {
            return "\nNom de la ressource : " + nom + "\n"
                    + "Identifiant de la ressource : " + id + "\n"
                    + "Descritpion de la ressource : " + description + "\n"
                    + "Heures totales de la ressource : " + afficherHeures() + "\n"
                    + "Groupes et/ou demi-groupes concernés par la ressource :" + "\n"
                    + "\tGroupes :" + afficherGroupe() + "\n"
                    + "\tSous-Groupes :" + afficherSGroupe() + "\n"
                    + "Enseignants de la ressource : \n" + afficherEnseignants()
                    + "Formation de la ressource : " + formation.getNomFormation() + "\n"
                    + "******************************\n";
        } catch (EnseignantException e) {
            System.out.println("Erreur : " + e.getMessage());

        }
        return null;
    }

    /**
     * Getter pour le nom de la ressource.
     *
     * @return Nom de la ressource
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter pour l'id de la ressource.
     *
     * @return id de la ressource
     */
    public String getId() {
        return id;
    }

    /**
     * Getter pour la description de la ressource.
     *
     * @return Description de la ressource
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter pour les heures attendues de la ressource.
     *
     * @return Heures attendues de la ressource
     */
    public HashMap<Class<? extends Heures>, Double> getHeuresParTypesCours() {
        return heuresParTypesCours;
    }

    /**
     * Getter pour la formation de la ressource.
     *
     * @return Formation de la ressource
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     * Getter pour les heures réalisées dans cette ressource
     *
     * @return Heures réalisées pour cette ressource
     */
    public HashMap<Enseignant, List<Heures>> getHeuresEnseignants() {
        return this.heuresEnseignants;
    }

    /**
     * Getter pour le groupe affecté à la ressource
     *
     * @return Groupe affecté à la ressource
     */
    public List<Groupe> getGroupes() {
        return groupes;
    }

    /**
     * Getter pour les enseignants affectés à la ressource
     *
     * @return Enseignants affectés à la ressource
     */
    public List<Enseignant> getEnseignantsRessource() {
        ArrayList<Enseignant> nomsEns = new ArrayList<>();
        for (Enseignant ens : this.heuresEnseignants.keySet()) {
            nomsEns.add(ens);
        }
        return nomsEns;
    }

    /**
     * Getter pour le sousgroupe affecté à la ressource
     *
     * @return Sousgroupe affecté à la ressource
     */
    public List<SousGroupe> getSousGroupes() {
        return sousgroupes;
    }

    private static final Class[] typesCours = {CM.class, DS.class, PE.class, TD.class, TP.class};
    private static int idR = 0;
    private final String nom, id, description;
    private final Formation formation;
    private final List<Groupe> groupes;
    private final List<SousGroupe> sousgroupes;
    private final HashMap<Class<? extends Heures>, Double> heuresParTypesCours;
    private final HashMap<Enseignant, List<Heures>> heuresEnseignants;

}
