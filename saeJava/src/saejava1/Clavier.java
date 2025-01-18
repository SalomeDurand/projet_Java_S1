package saejava1;

import java.io.*;
import saejava.exceptions.ChoixInvalideException;

public class Clavier {

    public static String lireString() // lecture d'une chaine
    {
        String ligne_lue = null;
        try {
            InputStreamReader lecteur = new InputStreamReader(System.in);
            BufferedReader entree = new BufferedReader(lecteur);
            ligne_lue = entree.readLine();
        } catch (IOException err) {
            System.exit(0);
        }
        return ligne_lue;
    }

    public static float lireFloat() // lecture d'un float
    {
        float x = 0;   // valeur a lire
        try {
            String ligne_lue = lireString();
            x = Float.parseFloat(ligne_lue);
        } catch (NumberFormatException err) {
            System.out.println("*** Erreur de donnee ***");
            System.exit(0);
        }
        return x;
    }

    public static double lireDouble() // lecture d'un double
    {
        double x = 0;   // valeur a lire
        try {
            String ligne_lue = lireString();
            x = Double.parseDouble(ligne_lue);
        } catch (NumberFormatException err) {
            System.out.println("*** Erreur de donnee ***");
            System.exit(0);
        }
        return x;
    }

    public static int lireInt() {  //lecture d'un int
        String ligne_lue = lireString();
        return Integer.parseInt(ligne_lue);
    }

    public static char lireChar() // lecture d'un char
    {
        char c = 0;   // valeur a lire
        try {
            String ligne_lue = lireString();
            c = ligne_lue.charAt(0);
        } catch (StringIndexOutOfBoundsException err) {
            System.out.println("*** Erreur de donnee 'vide' ***");
            System.exit(0);
        }
        return c;
    }

    public static int lireChoix(int min, int max) throws ChoixInvalideException {
        boolean validInput = false;
        while (!validInput) {
            try {
                int n = Clavier.lireInt();
                
                if (n < min || n > max) {
                    throw new ChoixInvalideException("\n\u274C Le choix doit être entre " + min + " et " + max + ". \u274C\n");
                }
                
                return n;
            } catch (NumberFormatException e) {
                throw new ChoixInvalideException("\n\u274C Entrée invalide, veuillez entrer un nombre. \u274C\n");
            }
        }
        System.out.println("*** Erreur de donnee ***");
        System.exit(0);
        return -1;
    }

    // programme de test de la classe Clavier
    public static void main(String[] args) throws ChoixInvalideException {
        System.out.println("donnez un flottant");
        float x;
        x = Clavier.lireFloat();
        System.out.println("merci pour " + x);
        System.out.println("donnez un entier");
        int n;
        n = Clavier.lireInt();
        System.out.println("merci pour " + n);
        System.out.println("donnez un caract�re");
        char c;
        c = Clavier.lireChar();
        System.out.println("merci pour " + c);
    }
}
