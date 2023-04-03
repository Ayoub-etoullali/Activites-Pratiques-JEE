package ma.enset.pres;

import ma.enset.dao.DaoImpl;
import ma.enset.extension.DaoImpl2;
import ma.enset.metier.MetierImpl;

public class Présentation {
    public static void main(String[] args) {
        /*
        Injection de dépendances par
        instanciation statique
        cad utilisation de "new"
         */
        DaoImpl dao=new DaoImpl(); //(1)
//        MetierImpl metier=new MetierImpl();
        MetierImpl metier=new MetierImpl();
        metier.setDao(dao); //(2) satisfait les dépendance
        System.out.println ("Résultats="+metier.calcul());
    }
}
