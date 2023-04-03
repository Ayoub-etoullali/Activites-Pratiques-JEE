package main.java.pres;

import main.java.extension.DaoImpl2;
import main.java.metier.MetierImpl;

public class Présentation {
    public static void main(String[] args) {
        /*
        Injection de dépendances par
        instanciation statique
        cad utilisation de "new"
         */
        DaoImpl2 dao=new DaoImpl2(); //(1)
        MetierImpl metier=new MetierImpl();
        metier.setDao(dao); //(2) satisfait les dépendance
        System.out.println ("Résultats="+metier.calcul());
    }
}
