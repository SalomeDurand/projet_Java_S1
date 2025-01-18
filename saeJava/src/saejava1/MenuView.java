/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package saejava1;

import saejava.exceptions.ChoixInvalideException;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class MenuView {

    public static void afficherMenuPrincipal() {
        System.out.println("Que souhaitez-vous faire ?\n");
        System.out.println("1 - AJOUTER");
        System.out.println("2 - SUPPRIMER");
        System.out.println("3 - ASSOCIER");
        System.out.println("4 - DÉSASSOCIER");
        System.out.println("5 - VÉRIFICATIONS");
        System.out.println("6 - COMPTABILITÉ");
        System.out.println("7 - QUITTER");
    }

    public static void afficherSousMenuAjouter() {
        System.out.println("\t1 - Ajouter un nouvel enseignant");
        System.out.println("\t2 - Ajouter une nouvelle ressource");
        System.out.println("\t3 - Ajouter un nouveau groupe d'étudiants");
        System.out.println("\t4 - Quitter");
    }

    public static void afficherSousMenuSupprimer() {
        System.out.println("\t1 - Supprimer un enseignant");
        System.out.println("\t2 - Supprimer une ressource");
        System.out.println("\t3 - Supprimer un groupe d'étudiants");
        System.out.println("\t4 - Quitter");
    }

    public static void afficherMenuAssocation() {
        System.out.println("\t1 - Associer une ressource à un groupe");
        System.out.println("\t2 - Associer une ressource à un sous-groupe");
        System.out.println("\t3 - Associer une ressource à un enseignant");
        System.out.println("\t4 - Quitter");
    }

    public static void afficherMenuDesassociation() {
        System.out.println("\t1 - Désassocier une ressource d'un groupe d'étudiants");
        System.out.println("\t2 - Désassocier une ressource d'un enseignant");
        System.out.println("\t3 - Quitter");
    }

    public static void afficherMenuSousService() {
        System.out.println("\t1 - Vérifier s'il y a des titulaires en sous-service");
        System.out.println("\t2 - Quitter");
    }

    public static void afficherMenuCalcul() {
        System.out.println("\t1 - Calculer le montant brut à verser pour un enseignant pour l'année");
        System.out.println("\t2 - Connaître le coût total d'une ressource");
        System.out.println("\t3 - Connaître le coût total d'une formation");
        System.out.println("\t4 - Connaître le coût total de toutes les formations");
        System.out.println("\t5 - Quitter");
    }

    //méthodes enseignant
    public static String demanderNomEnseignant() {
        System.out.print("Entrez le nom de l'enseignant : ");
        return Clavier.lireString();
    }

    public static String demanderPrenomEnseignant() {
        System.out.print("Entrez le prénom de l'enseignant : ");
        return Clavier.lireString();
    }

    public static int demanderTypeEnseignant() throws ChoixInvalideException {
        System.out.print("Est-il :\n1 - Titulaire\n2 - Vacataire\n3 - Titulaire vacataire ?\n");
        return Clavier.lireChoix(1, 3);
    }

    public static void afficherSuccesEnseignant(String enseignantDetails) {
        System.out.println("\n**********************\nEnseignant ajouté !\n**********************\n");
        System.out.println("Récapitulatif : " + enseignantDetails);
    }

    public static int demanderHeuresEnseignant(String typeHeure) throws ChoixInvalideException {
        System.out.print("Entrez le nombre d'heures à attribuer à l'enseignant pour " + typeHeure + " : ");
        return Clavier.lireInt();
    }

    public static boolean demanderPrimeAnnuelle() throws ChoixInvalideException {
        System.out.print("A-t-il la prime annuelle ? : 1 - Oui / 2 - Non\n");
        return Clavier.lireChoix(1, 2) == 1;
    }

    //méthodes ressource
    public static String demanderNomRessource() {
        System.out.print("Entrez le nom de la ressource : ");
        return Clavier.lireString();
    }

    public static String demanderDescriptionRessource() {
        System.out.print("Entrez une courte description de la ressource : ");
        return Clavier.lireString();
    }

    public static String demanderFormationRessource() {
        System.out.print("À quelle formation appartient cette ressource ?\n");
        return Clavier.lireString();
    }

    public static int demanderHeuresRessource(String typeHeure) throws ChoixInvalideException {
        System.out.print("Combien d'heures de " + typeHeure + " souhaitez-vous attribuer à la ressource ?\n");
        return Clavier.lireInt();
    }

    public static void afficherSuccesRessource(String recapitulatif) {
        System.out.println("\n**********************\nRessource ajoutée !\n**********************\n");
        System.out.println("Récapitulatif : " + recapitulatif);
    }

    public static void afficherFormationCreer() {
        System.out.println("\n**********************\nFormation créée !\n**********************\n");
    }

    //méthodes Groupe
    public static void afficherSuccesGroupe(String recapitulatif) {
        System.out.println("\n**********************\nGroupe ajouté !\n**********************\n");
        System.out.println("Récapitulatif : " + recapitulatif);
    }

    public static void afficherSuccesSousGroupe() {
        System.out.println("\n**********************\nSous-groupe ajouté !\n**********************\n");
    }

    public static void afficherRecapSousGroupe(String recapitulatif) {
        System.out.println("**********************\nTous les sous-groupes ont été ajoutés !\n**********************\n");
        System.out.println("Récapitulatif : " + recapitulatif);
    }

    public static String demanderNomGroupe() {
        System.out.print("Entrez le nom du groupe : ");
        return Clavier.lireString();
    }

    public static String demanderNomFormation() {
        System.out.print("Entrez le nom de la formation : ");
        return Clavier.lireString();
    }

    public static int demanderOuiNon(String message) throws ChoixInvalideException {
        System.out.print(message);
        return Clavier.lireInt();
    }

    public static int demanderNombreSousGroupes() throws ChoixInvalideException {
        System.out.print("Combien de sous-groupes souhaitez-vous ajouter ? ");
        return Clavier.lireInt();
    }

    public static String demanderNomSousGroupe() {
        System.out.print("Entrez le nom du sous-groupe : ");
        return Clavier.lireString();
    }

    public static void afficherErreur(String message) {
        System.out.println("\n\u274C " + message + " \u274C\n");
    }

    public static void afficherSucces(String message) {
        System.out.println("\n\u2705 " + message + " \u2705\n");
    }

    //méthodes sous-serive        
    public static void afficherTitulaireSousService(String nom, String prenom) {
        System.out.println("-" + nom + " " + prenom + " : ");
    }
}
