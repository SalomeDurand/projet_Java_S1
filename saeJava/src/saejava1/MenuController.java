/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava1;

import saejava.Heures.DS;
import saejava.Heures.TD;
import saejava.Heures.PE;
import saejava.Heures.Heures;
import saejava.Heures.CM;
import saejava.Heures.TP;
import applicative.Applicative;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.ArrayList;
import saejava.Enseignant.Enseignant;
import saejava.Enseignant.Titulaire;
import saejava.EnseignantExceptions.EnseignantDejaExistant;
import saejava.EnseignantExceptions.EnseignantNull;
import saejava.GroupeExceptions.GroupeDejaExistant;
import saejava.GroupeExceptions.GroupeNull;
import saejava.GroupeExceptions.SousGroupeDejaExistant;
import saejava.HeureExceptions.AucuneHeureDefinie;
import saejava.HeureExceptions.NombreHeuresNegatif;
import saejava.HeureExceptions.NombreHeuresTropGrand;
import saejava.HeureExceptions.ToutesHeuresNonTP;
import saejava.HeureExceptions.ToutesHeuresTP;
import saejava.Ressource.Formation;
import saejava.Ressource.Groupe;
import saejava.Ressource.Ressource;
import saejava.Ressource.SousGroupe;
import saejava.RessourceExceptions.RessourceDejaExistante;
import saejava.RessourceExceptions.RessourceInexistante;
import saejava.exceptions.ChaineValideException;
import saejava.exceptions.ChoixInvalideException;
import saejava.exceptions.QuitterException;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 */
public class MenuController {

    private final Applicative app;

    public MenuController(Applicative app) {
        this.app = app;
    }

    public void traiterMenu() throws ChoixInvalideException, ChaineValideException, InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, RessourceDejaExistante, GroupeDejaExistant, ToutesHeuresTP, AucuneHeureDefinie, EnseignantDejaExistant, RessourceInexistante, GroupeNull, EnseignantNull, QuitterException, SousGroupeDejaExistant, ToutesHeuresNonTP {
        int reponseMenu;
        int reponseSousMenu;

        while (true) {
            MenuView.afficherMenuPrincipal();
            reponseMenu = Clavier.lireChoix(1, 7);

            switch (reponseMenu) {
                case 1: {
                    MenuView.afficherSousMenuAjouter();
                    reponseSousMenu = Clavier.lireChoix(1, 4);

                    switch (reponseSousMenu) {
                        case 1:
                            ajouterEnseignant();
                            break;
                        case 2:
                            ajouterRessource();
                            break;
                        case 3:
                            ajouterGroupe();
                            break;
                        case 4:
                            System.out.println("À bientôt !");
                            break;
                        default:
                            MenuView.afficherErreur("Option non reconnue. Veuillez choisir parmi celles proposées ci-dessus.");
                            break;
                    }
                    break;
                }
                case 2: {
                    MenuView.afficherSousMenuSupprimer();
                    reponseMenu = Clavier.lireChoix(1, 4);

                    switch (reponseMenu) {
                        case 1:
                            supprimerEnseignant();
                            break;
                        case 2:
                            supprimerRessource();
                            break;
                        case 3:
                            supprimerGroupe();
                            break;
                        case 4:
                            System.out.println("À bientôt !");
                            break;
                        default:
                            MenuView.afficherErreur("Option non reconnue. Veuillez choisir parmi celles proposées ci-dessus.");
                            break;
                    }
                    break;
                }
                case 3: {
                    MenuView.afficherMenuAssocation();
                    reponseMenu = Clavier.lireChoix(1, 3);

                    switch (reponseMenu) {
                        case 1:
                            associerRessourceAGroupe();
                            break;
                        case 2:
                            associerRessourceASousGroupe();
                            break;
                        case 3:
                            associerRessourceAEnseignant();
                            break;
                        case 4:
                            System.out.println("À bientôt !");
                            break;
                        default:
                            MenuView.afficherErreur("Option non reconnue. Veuillez choisir parmi celles proposées ci-dessus.");
                            break;
                    }
                    break;
                }
                case 4: {
                    MenuView.afficherMenuDesassociation();
                    reponseSousMenu = Clavier.lireChoix(1, 3);

                    switch (reponseSousMenu) {
                        case 1:
                            desassocierRessourceAGroupe();
                            break;
                        case 2:
                            desassocierRessourceAEnseignant();
                            break;
                        case 3:
                            System.out.println("À bientôt !");
                            break;
                        default:
                            MenuView.afficherErreur("Option non reconnue. Veuillez choisir parmi celles proposées ci-dessus.");
                            break;
                    }
                    break;
                }
                case 5: {
                    MenuView.afficherMenuSousService();
                    reponseSousMenu = Clavier.lireChoix(1, 2);

                    switch (reponseSousMenu) {
                        case 1:
                            afficherEnseignantSousService();
                            break;
                        case 2:
                            System.out.println("À bientôt !");
                            break;
                        default:
                            MenuView.afficherErreur("Option non reconnue. Veuillez choisir parmi celles proposées ci-dessus.");
                            break;
                    }
                    break;
                }
                case 6: {
                    MenuView.afficherMenuCalcul();
                    reponseSousMenu = Clavier.lireChoix(1, 5);

                    switch (reponseSousMenu) {
                        case 1:
                            calculerSalaireEnseignant();
                            break;
                        case 2:
                            connaitreCoutRessource();
                            break;
                        case 3:
                            connaitreCoutFormation();
                            break;
                        case 4:
                            connaitreCoutTotalFormations();
                            break;
                        case 5:
                            System.out.println("À bientôt !");
                            break;
                        default:
                            MenuView.afficherErreur("Option non reconnue. Veuillez choisir parmi celles proposées ci-dessus.");
                            break;
                    }
                    break;
                }
                case 7: {
                    throw new QuitterException("À bientôt !\n");
                }
                default: {
                    MenuView.afficherErreur("Option non reconnue. Veuillez choisir parmi celles proposées ci-dessus.");
                    break;
                }

            }
        }
    }

    public void ajouterEnseignant() throws ChoixInvalideException, ChaineValideException {
        String nomEnseignant = MenuView.demanderNomEnseignant();
        String prenomEnseignant = MenuView.demanderPrenomEnseignant();

        if (app.trouverEnseignant(nomEnseignant, prenomEnseignant) != null) {
            MenuView.afficherErreur("Cet enseignant existe déjà dans le système.");
            return;
        }

        int typeProfesseur = MenuView.demanderTypeEnseignant();

        Heures cm;
        Heures td;
        Heures pe;
        Heures tp;
        Heures ds;
        List<Heures> heuresStatutaires = new ArrayList<>();
        boolean hasPrimeAnnuelle = false;

        switch (typeProfesseur) {
            case 1:       // Titulaire
            case 3:       // Titulaire Vacataire
                System.out.print("Entrez le nombre de ses heures statutaires :\npour les CM : ");
                double cmHeures = Clavier.lireDouble();
                for (int i = 0; i < cmHeures; i++) {
                    cm = new CM();
                    heuresStatutaires.add(cm);
                }
                System.out.print("pour les TD : ");
                double tdHeures = Clavier.lireDouble();
                for (int i = 0; i < tdHeures; i++) {
                    td = new TD();
                    heuresStatutaires.add(td);
                }
                System.out.print("pour les PE : ");
                double peHeures = Clavier.lireDouble();
                for (int i = 0; i < peHeures; i++) {
                    pe = new PE();
                    heuresStatutaires.add(pe);
                }
                System.out.print("pour les TP : ");
                double tpHeures = Clavier.lireDouble();
                for (int i = 0; i < tpHeures; i++) {
                    tp = new TP();
                    heuresStatutaires.add(tp);
                }
                System.out.print("pour les DS : ");
                double dsHeures = Clavier.lireDouble();
                for (int i = 0; i < dsHeures; i++) {
                    ds = new DS();
                    heuresStatutaires.add(ds);
                }
                System.out.print("A-t-il la prime annuelle ? : 1 - Oui / 2 - Non\n");
                int prime = Clavier.lireChoix(1, 2);
                hasPrimeAnnuelle = prime == 1;
                break;

            case 2:         // Vacataire
                break;
            default:
                throw new ChoixInvalideException("\n\u274C Type d'enseignant invalide ! \u274C\n");
        }

        Enseignant enseignant = app.creerEnseignant(nomEnseignant, prenomEnseignant, typeProfesseur, heuresStatutaires, hasPrimeAnnuelle);

        MenuView.afficherSuccesEnseignant(enseignant.toString());
    }

    public void ajouterRessource() throws ChoixInvalideException, ChaineValideException, InvalidParameterException, NombreHeuresTropGrand, NombreHeuresNegatif, RessourceDejaExistante {
        String nomRessource = MenuView.demanderNomRessource();
        String desRessource = MenuView.demanderDescriptionRessource();
        String formation = MenuView.demanderFormationRessource();

        Formation f1 = app.trouverFormation(formation);

        if (f1 == null) {
            System.out.println("La formation n'existe pas. Souhaitez-vous la créer ? 1 - Oui / 2 - Non\n");
            int choix = Clavier.lireChoix(1, 2);
            if (choix == 1) {
                app.creerFormation(formation);
                MenuView.afficherFormationCreer();
            } else {
                MenuView.afficherErreur("La formation n'existe pas. Impossible de créer la ressource sans formation.");
                return;
            }
        }

        int nbCM = MenuView.demanderHeuresRessource("CM");
        int nbDS = MenuView.demanderHeuresRessource("DS");
        int nbPE = MenuView.demanderHeuresRessource("PE");
        int nbTD = MenuView.demanderHeuresRessource("TD");
        int nbTP = MenuView.demanderHeuresRessource("TP");

        Ressource r1 = app.creerRessource(nomRessource, desRessource, formation, nbCM, nbDS, nbPE, nbTD, nbTP);

        MenuView.afficherSuccesRessource(r1.toString());
    }

    public void ajouterGroupe() throws ChaineValideException, ChoixInvalideException, SousGroupeDejaExistant, RessourceInexistante, RessourceDejaExistante {
        String nomGroupe = MenuView.demanderNomGroupe();
        Groupe g1 = app.trouveGroupe(nomGroupe);

        if (g1 != null) {
            MenuView.afficherErreur("Le groupe " + nomGroupe + " existe déjà.");
            return;
        }

        String nomFormation = MenuView.demanderNomFormation();
        Formation f1 = app.trouverFormation(nomFormation);

        if (f1 == null) {
            System.out.println("La formation n'existe pas. Souhaitez-vous la créer ? (1 - Oui / 2 - Non)\n");
            int choix = MenuView.demanderOuiNon("");
            if (choix == 1) {
                f1 = app.creerFormation(nomFormation);
                MenuView.afficherFormationCreer();
            } else {
                MenuView.afficherErreur("Impossible de créer le groupe sans formation.");
                return;
            }
        }

        g1 = app.creerGroupe(nomGroupe, f1);

        int repSG = MenuView.demanderOuiNon("Ce groupe est-il composé de sous-groupes ? : 1 - Oui / 2 - Non");

        if (repSG == 1) {
            int nbrSG = MenuView.demanderNombreSousGroupes();
            for (int j = 0; j < nbrSG; j++) {
                String nomSousGroupe = MenuView.demanderNomSousGroupe();
                while (g1.hasSousGroupe(nomSousGroupe)) {
                    MenuView.afficherErreur("Ce sous-groupe est déjà présent");
                    nomSousGroupe = MenuView.demanderNomSousGroupe();
                }

                app.creerSousGroupe(g1, nomSousGroupe);
                MenuView.afficherSuccesSousGroupe();
            }
            MenuView.afficherRecapSousGroupe(g1.toString());

        } else {
            MenuView.afficherSuccesGroupe(g1.toString());
        }
    }

    public void supprimerEnseignant() {
        String nomEnseignant = MenuView.demanderNomEnseignant();
        String prenomEnseignant = MenuView.demanderPrenomEnseignant();

        Enseignant enseignant = app.trouverEnseignant(nomEnseignant, prenomEnseignant);

        if (enseignant == null) {
            MenuView.afficherErreur("L'enseignant " + nomEnseignant + " " + prenomEnseignant + " n'a pas été trouvé.");
            return;
        }

        if (!enseignant.getListeRessource().isEmpty()) {
            MenuView.afficherErreur("L'enseignant " + nomEnseignant + " " + prenomEnseignant + " est associé à des ressources.");
            return;
        }

        if (app.supprimeProfesseur(nomEnseignant)) {
            MenuView.afficherSucces("L'enseignant " + nomEnseignant + " " + prenomEnseignant + " a été supprimé avec succès.");
        }
    }

    public void supprimerRessource() throws RessourceInexistante {
        String nomRessource = MenuView.demanderNomRessource();
        Ressource ressource = app.trouveRessource(nomRessource);

        if (ressource == null) {
            MenuView.afficherErreur("Aucune ressource trouvée avec le nom " + nomRessource);
            return;
        }

        if (!ressource.getEnseignantsRessource().isEmpty()
                || !ressource.getGroupes().isEmpty()) {
            MenuView.afficherErreur("La ressource " + nomRessource + " est associée à des enseignants ou des groupes.");
            return;
        }

        if (app.supprimeRessource(nomRessource)) {
            MenuView.afficherSucces("La ressource " + nomRessource + " a été supprimée avec succès.");
        }
    }

    public void supprimerGroupe() {
        String nomGroupe = MenuView.demanderNomGroupe();
        Groupe groupe = app.trouveGroupe(nomGroupe);

        if (groupe == null) {
            MenuView.afficherErreur("Aucun groupe trouvé avec le nom " + nomGroupe);
            return;
        }

        if (!groupe.getListeRessource().isEmpty()) {
            MenuView.afficherErreur("Le groupe " + nomGroupe + " est associé à des ressources.");
            return;
        }

        if (app.supprimeGroupe(nomGroupe)) {
            MenuView.afficherSucces("Le groupe " + nomGroupe + " a été supprimé avec succès.");
        }
    }

    private void associerRessourceAGroupe() throws ChoixInvalideException, ChaineValideException, InvalidParameterException, NombreHeuresTropGrand, NombreHeuresNegatif, RessourceDejaExistante, ToutesHeuresTP, GroupeDejaExistant, AucuneHeureDefinie, SousGroupeDejaExistant, RessourceInexistante {
        String nomGroupe = MenuView.demanderNomGroupe();
        Groupe groupe = app.trouveGroupe(nomGroupe);

        if (groupe == null) {
            System.out.println("Le groupe " + nomGroupe + " n'existe pas, souhaitez-vous le créer ? 1 - Oui / 2 - Non\n");
            int choix1 = MenuView.demanderOuiNon("");
            Formation f1 = null;
            if (choix1 == 1) {
                System.out.print("À quelle formation ce groupe doit-il être associé ?\n");
                String formationNom = Clavier.lireString();
                f1 = app.trouverFormation(formationNom);

                if (f1 == null) {
                    System.out.println("La formation n'existe pas. Souhaitez-vous la créer ? 1 - Oui / 2 - Non\n");
                    int choix2 = Clavier.lireChoix(1, 2);
                    if (choix2 == 1) {
                        f1 = app.creerFormation(formationNom);
                        MenuView.afficherFormationCreer();
                    } else {
                        MenuView.afficherErreur("Impossible de créer un groupe sans formation.");
                        return;
                    }
                }

                groupe = app.creerGroupe(nomGroupe, f1);
                int repSG = MenuView.demanderOuiNon("Ce groupe est-il composé de sous-groupes ? : 1 - Oui / 2 - Non\n");

                if (repSG == 1) {
                    int nbrSG = MenuView.demanderNombreSousGroupes();
                    for (int j = 0; j < nbrSG; j++) {
                        String nomSousGroupe = MenuView.demanderNomSousGroupe();
                        app.creerSousGroupe(groupe, nomSousGroupe);
                        MenuView.afficherSuccesSousGroupe();
                    }
                    MenuView.afficherRecapSousGroupe(groupe.toString());

                } else {
                    MenuView.afficherSuccesGroupe(groupe.toString());
                }
            } else {
                MenuView.afficherErreur("Impossible d'associer une ressource à un groupe non existant !");
                return;
            }

            groupe = app.creerGroupe(nomGroupe, f1);
        }

        String nomRessource = MenuView.demanderNomRessource();
        Ressource ressource = app.trouveRessource(nomRessource);

        if (ressource == null) {
            System.out.println("La ressource n'existe pas. Souhaitez-vous la créer ? 1 - Oui / 2 - Non\n");
            int choix2 = Clavier.lireChoix(1, 2);
            if (choix2 == 2) {
                return;
            }

            String desRessource = MenuView.demanderDescriptionRessource();
            String formation = MenuView.demanderFormationRessource();

            Formation f1 = app.trouverFormation(formation);

            if (f1 == null) {
                System.out.println("La formation n'existe pas. Souhaitez-vous la créer ? 1 - Oui / 2 - Non\n");
                int choix = Clavier.lireChoix(1, 2);
                if (choix == 1) {
                    app.creerFormation(formation);
                    MenuView.afficherFormationCreer();
                } else {
                    MenuView.afficherErreur("La formation n'existe pas. Impossible de créer la ressource sans formation.");
                    return;
                }
            }

            int nbCM = MenuView.demanderHeuresRessource("CM");
            int nbDS = MenuView.demanderHeuresRessource("DS");
            int nbPE = MenuView.demanderHeuresRessource("PE");
            int nbTD = MenuView.demanderHeuresRessource("TD");
            int nbTP = MenuView.demanderHeuresRessource("TP");

            ressource = app.creerRessource(nomRessource, desRessource, formation, nbCM, nbDS, nbPE, nbTD, nbTP);
            MenuView.afficherSuccesRessource(ressource.toString());
        }

        if (groupe.getListeRessource().contains(nomRessource) && ressource.getGroupes().contains(groupe)) {
            MenuView.afficherErreur("\n\u274C La ressource " + nomRessource + " est déjà associée au groupe " + nomGroupe + ". \u274C\n");
        } else {
            try {
                ressource.enseignerGroupe(groupe);
                MenuView.afficherSucces(
                        "La ressource " + nomRessource + " a été associée au groupe " + nomGroupe + ".");
            } catch (ToutesHeuresTP e) {
                MenuView.afficherErreur(e.getMessage());
            }
        }
    }

    private void associerRessourceASousGroupe() throws ChoixInvalideException, ChaineValideException, InvalidParameterException, NombreHeuresTropGrand, NombreHeuresNegatif, RessourceDejaExistante, ToutesHeuresTP, GroupeDejaExistant, AucuneHeureDefinie, SousGroupeDejaExistant, ToutesHeuresNonTP {
        String nomSousGroupe = MenuView.demanderNomSousGroupe();
        SousGroupe sousGroupe = app.trouveSousGroupe(nomSousGroupe);

        if (sousGroupe == null) {
            System.out.println("Le sous-groupe " + nomSousGroupe + " n'existe pas, veuillez créer un groupe.");
            return;
        }

        String nomRessource = MenuView.demanderNomRessource();
        Ressource ressource = app.trouveRessource(nomRessource);

        if (ressource == null) {
            System.out.println("La ressource n'existe pas. Souhaitez-vous la créer ? 1 - Oui / 2 - Non");
            int choix2 = Clavier.lireChoix(1, 2);
            if (choix2 == 2) {
                return;
            }

            String desRessource = MenuView.demanderDescriptionRessource();
            String formation = MenuView.demanderFormationRessource();

            Formation f1 = app.trouverFormation(formation);

            if (f1 == null) {
                System.out.println("La formation n'existe pas. Souhaitez-vous la créer ? 1 - Oui / 2 - Non");
                int choix = Clavier.lireChoix(1, 2);
                if (choix == 1) {
                    app.creerFormation(formation);
                    MenuView.afficherFormationCreer();
                } else {
                    MenuView.afficherErreur("La formation n'existe pas. Impossible de créer la ressource sans formation. ");
                    return;
                }
            }

            int nbCM = MenuView.demanderHeuresRessource("CM");
            int nbDS = MenuView.demanderHeuresRessource("DS");
            int nbPE = MenuView.demanderHeuresRessource("PE");
            int nbTD = MenuView.demanderHeuresRessource("TD");
            int nbTP = MenuView.demanderHeuresRessource("TP");

            ressource = app.creerRessource(nomRessource, desRessource, formation, nbCM, nbDS, nbPE, nbTD, nbTP);
            MenuView.afficherSuccesRessource(ressource.toString());
        }

        if (sousGroupe.getListeRessource().contains(nomRessource) && ressource.getSousGroupes().contains(sousGroupe)) {
            MenuView.afficherErreur("La ressource " + nomRessource + " est déjà associée au sous-groupe " + nomSousGroupe + ".");
        } else {
            try {
                ressource.enseignerSousGroupe(sousGroupe);
                MenuView.afficherSucces(
                        "La ressource " + nomRessource + " a été associée au sous-groupe " + nomSousGroupe + ".");
            } catch (ToutesHeuresNonTP e) {
                MenuView.afficherErreur(e.getMessage());
            }
        }
    }

    private void associerRessourceAEnseignant() throws ChoixInvalideException, ChaineValideException, InvalidParameterException, NombreHeuresTropGrand, NombreHeuresNegatif, EnseignantDejaExistant, RessourceDejaExistante {
        String nomEnseignant = MenuView.demanderNomEnseignant();
        String prenomEnseignant = MenuView.demanderPrenomEnseignant();
        Enseignant enseignant = app.trouverEnseignant(nomEnseignant, prenomEnseignant);
        List<Heures> heures = new ArrayList();

        if (enseignant == null) {
            System.out.println("L'enseignant " + nomEnseignant + " n'existe pas, soushaitez-vous le créer ? 1 - Oui / 2 - Non\n");
            int choix1 = MenuView.demanderOuiNon("");
            if (choix1 == 1) {
                int typeProfesseur = MenuView.demanderTypeEnseignant();

                Heures cm;
                Heures td;
                Heures pe;
                Heures tp;
                Heures ds;
                List<Heures> heuresStatutaires = new ArrayList<>();
                boolean hasPrimeAnnuelle = false;

                switch (typeProfesseur) {
                    case 1:       // Titulaire
                    case 3:       // Titulaire Vacataire
                        System.out.print("Entrez le nombre de ses heures statutaires :\npour les CM : ");
                        double cmHeures = Clavier.lireDouble();
                        for (int i = 0; i < cmHeures; i++) {
                            cm = new CM();
                            heuresStatutaires.add(cm);
                        }
                        System.out.print("pour les TD : ");
                        double tdHeures = Clavier.lireDouble();
                        for (int i = 0; i < tdHeures; i++) {
                            td = new TD();
                            heuresStatutaires.add(td);
                        }
                        System.out.print("pour les PE : ");
                        double peHeures = Clavier.lireDouble();
                        for (int i = 0; i < peHeures; i++) {
                            pe = new PE();
                            heuresStatutaires.add(pe);
                        }
                        System.out.print("pour les TP : ");
                        double tpHeures = Clavier.lireDouble();
                        for (int i = 0; i < tpHeures; i++) {
                            tp = new TP();
                            heuresStatutaires.add(tp);
                        }
                        System.out.print("pour les DS : ");
                        double dsHeures = Clavier.lireDouble();
                        for (int i = 0; i < dsHeures; i++) {
                            ds = new DS();
                            heuresStatutaires.add(ds);
                        }
                        System.out.print("A-t-il la prime annuelle ? : 1 - Oui / 2 - Non\n");
                        int prime = Clavier.lireChoix(1, 2);
                        hasPrimeAnnuelle = (prime == 1);
                        break;
                    case 2:         // Vacataire
                        break;
                    default:
                        throw new ChoixInvalideException("\n\u274C Type d'enseignant invalide ! \u274C\n");
                }

                enseignant = app.creerEnseignant(nomEnseignant, prenomEnseignant, typeProfesseur, heuresStatutaires, hasPrimeAnnuelle);
                MenuView.afficherSuccesEnseignant(enseignant.toString());
            } else {
                System.out.println("\n\u274C Impossible d'associer une ressource à un enseignant non existant ! \u274C");
                return;
            }
        }

        String nomRessource = MenuView.demanderNomRessource();
        Ressource ressource = app.trouveRessource(nomRessource);

        if (ressource == null) {
            System.out.println("La ressource n'existe pas. Souhaitez-vous la créer ? 1 - Oui / 2 - Non");
            int choix2 = Clavier.lireChoix(1, 2);
            if (choix2 == 2) {
                return;
            }

            String desRessource = MenuView.demanderDescriptionRessource();
            String formation = MenuView.demanderFormationRessource();

            Formation f1 = app.trouverFormation(formation);

            if (f1 == null) {
                System.out.println("La formation n'existe pas. Souhaitez-vous la créer ? 1 - Oui / 2 - Non");
                int choix = Clavier.lireChoix(1, 2);
                if (choix == 1) {
                    f1 = app.creerFormation(formation);
                    MenuView.afficherFormationCreer();
                } else {
                    MenuView.afficherErreur("La formation n'existe pas. Impossible de créer la ressource sans formation. ");
                    return;
                }
            }

            int nbCM = MenuView.demanderHeuresRessource("CM");
            int nbDS = MenuView.demanderHeuresRessource("DS");
            int nbPE = MenuView.demanderHeuresRessource("PE");
            int nbTD = MenuView.demanderHeuresRessource("TD");
            int nbTP = MenuView.demanderHeuresRessource("TP");

            ressource = app.creerRessource(nomRessource, desRessource, f1.getNomFormation(), nbCM, nbDS, nbPE, nbTD, nbTP);
            MenuView.afficherSuccesRessource(ressource.toString());
        }

        if (enseignant.getListeRessource().contains(ressource) && ressource.getEnseignantsRessource().contains(enseignant)) {
            MenuView.afficherErreur("La ressource " + nomRessource + " est déjà associée à l'enseignant " + nomEnseignant);
            return;
        }

        int nbCM = MenuView.demanderHeuresEnseignant("CM");
        for (int i = 0; i < nbCM; i++) {
            heures.add(new CM());
        }

        int nbDS = MenuView.demanderHeuresEnseignant("DS");
        for (int i = 0; i < nbDS; i++) {
            heures.add(new DS());
        }

        int nbPE = MenuView.demanderHeuresEnseignant("PE");
        for (int i = 0; i < nbPE; i++) {
            heures.add(new PE());
        }

        int nbTD = MenuView.demanderHeuresEnseignant("TD");
        for (int i = 0; i < nbTD; i++) {
            heures.add(new TD());
        }

        int nbTP = MenuView.demanderHeuresEnseignant("TP");
        for (int i = 0; i < nbTP; i++) {
            heures.add(new TP());
        }

        ressource.ajouterEnseignant(enseignant, heures);
        MenuView.afficherSucces("La ressource " + nomRessource + " a été associée à l'enseignant " + nomEnseignant + " " + prenomEnseignant + ".");
    }

    private void desassocierRessourceAGroupe() throws RessourceInexistante, GroupeNull {
        String nomGroupe = MenuView.demanderNomGroupe();
        Groupe groupe = app.trouveGroupe(nomGroupe);

        if (groupe == null) {
            MenuView.afficherErreur("Ce groupe n'existe pas.");
            return;
        }

        String nomRessource = MenuView.demanderNomRessource();
        Ressource ressource = app.trouveRessource(nomRessource);

        if (ressource == null) {
            MenuView.afficherErreur("Cette ressource n'existe pas.");
            return;
        }

        if (groupe.getListeRessource().contains(nomRessource)) {
            ressource.retirerGroupe(groupe);
            MenuView.afficherSucces("La ressource " + nomRessource + " a été désassociée du groupe " + nomGroupe + ".");
        } else {
            MenuView.afficherErreur("La ressource " + nomRessource + " n'est pas associée au groupe " + nomGroupe + ".");
        }
    }

    private void desassocierRessourceAEnseignant() throws RessourceInexistante, EnseignantNull {
        String nomEnseignant = MenuView.demanderNomEnseignant();
        String prenomEnseignant = MenuView.demanderPrenomEnseignant();
        Enseignant enseignant = app.trouverEnseignant(nomEnseignant, prenomEnseignant);

        if (enseignant == null) {
            MenuView.afficherErreur("Cet enseignant n'existe pas.");
            return;
        }

        String nomRessource = MenuView.demanderNomRessource();
        Ressource ressource = app.trouveRessource(nomRessource);

        if (ressource == null) {
            MenuView.afficherErreur("Cette ressource n'existe pas.");
            return;
        }

        if (enseignant.getListeRessource().contains(ressource)) {
            ressource.retirerEnseignant(enseignant);
            MenuView.afficherSucces("La ressource " + nomRessource + " a été désassociée de l'enseignant " + nomEnseignant + " " + prenomEnseignant + ".");
        } else {
            MenuView.afficherErreur("La ressource " + nomRessource + " n'est pas associée à l'enseignant " + nomEnseignant + " " + prenomEnseignant + ".");
        }
    }

    private void afficherEnseignantSousService() throws EnseignantNull {
        int nbSousService = 0;
        MenuView.afficherSucces("Enseignant(s) titulaire(s) en sous-service : ");

        for (Enseignant enseignant : app.getEnseignants()) {
            if (enseignant instanceof Titulaire) {
                if (!(((Titulaire) enseignant).hasHeuresStatutairesRequises())) {
                    nbSousService++;
                    MenuView.afficherTitulaireSousService(enseignant.getNom(), enseignant.getPrenom());
                }
            }
        }
        System.out.println("Total : " + nbSousService + ".");
    }

    private void calculerSalaireEnseignant() {
        System.out.println("Entrez le nom de l'enseignant : ");
        String nomEnseignant = Clavier.lireString();
        System.out.print("Entrez le prénom de l'enseignant : ");
        String prenomEnseignant = Clavier.lireString();

        Enseignant enseignant = app.trouverEnseignant(nomEnseignant, prenomEnseignant);
        if (enseignant != null) {
            System.out.println("Salaire annuel brut : " + enseignant.calculerSalaire() + " €.");
        } else {
            MenuView.afficherErreur("Cet enseignant ne fait pas partie de la liste.");
        }
    }

    private void connaitreCoutRessource() {
        System.out.println("Entrez le nom de la ressource : ");
        String nomRessource = Clavier.lireString();

        Ressource ressource = app.trouveRessource(nomRessource);
        if (ressource != null) {
            System.out.println("Coût total de la ressource : " + ressource.calculerCoutTotal() + " €.");
        } else {
            MenuView.afficherErreur("La ressource indiquée n'a pas été trouvée.");
        }
    }

    private void connaitreCoutFormation() throws RessourceInexistante {
        System.out.println("Entrez le nom de la formation : ");
        String nomFormation = Clavier.lireString();

        Formation formation = app.trouverFormation(nomFormation);
        if (formation != null) {
            System.out.println("Coût total de la formation : " + formation.calculerCoutFormation() + " €.");
        } else {
            MenuView.afficherErreur("La formation indiquée n'a pas été trouvée.");
        }
    }

    private void connaitreCoutTotalFormations() throws RessourceInexistante {
        double coutTotal = 0;
        for (Formation formation : app.getFormations()) {
            coutTotal += formation.calculerCoutFormation();
        }
        System.out.println("Coût total de toutes les formations : " + coutTotal + " €.");
    }

}
