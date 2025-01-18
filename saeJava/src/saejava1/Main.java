/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saejava1;

import applicative.Applicative;
import java.security.InvalidParameterException;
import saejava.Enseignant.EnseignantTitulaire;
import saejava.EnseignantExceptions.EnseignantDejaExistant;
import saejava.EnseignantExceptions.EnseignantNull;
import saejava.GroupeExceptions.GroupeDejaExistant;
import saejava.GroupeExceptions.GroupeNull;
import saejava.GroupeExceptions.SousGroupeDejaExistant;
import saejava.HeureExceptions.AucuneHeureDefinie;
import saejava.HeureExceptions.NombreHeuresNegatif;
import saejava.HeureExceptions.NombreHeuresTropGrand;
import saejava.HeureExceptions.ToutesHeuresTP;
import saejava.Heures.Heures;
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
public class Main {

    @SuppressWarnings("UseSpecificCatch")
    public static void main(String[] args) throws ChoixInvalideException, ChaineValideException, InvalidParameterException, NombreHeuresNegatif, NombreHeuresTropGrand, RessourceDejaExistante, ToutesHeuresTP, GroupeDejaExistant, EnseignantDejaExistant, AucuneHeureDefinie, RessourceInexistante, GroupeNull, EnseignantNull, QuitterException, SousGroupeDejaExistant {
        Heures.setEquivalentCM(1.5f);
        Heures.setEquivalentTP((2.0 / 3.0));
        Heures.setTarif(43);
        EnseignantTitulaire.setPrimeAnnuelle(1000);

        Applicative app = new Applicative();
        MenuController menuController = new MenuController(app);
        app.initTestData();

        while (true) {
            try {
                menuController.traiterMenu();
            } catch (QuitterException e) {
                return;
            } catch (ChaineValideException | ChoixInvalideException | SousGroupeDejaExistant e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.printf("\n\u274C Erreur inattendue : %S \u274C\n", e);
            }
        }
    }
}
