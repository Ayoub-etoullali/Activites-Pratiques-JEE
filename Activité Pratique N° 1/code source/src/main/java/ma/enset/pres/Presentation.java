package ma.enset.pres;

import ma.enset.dao.DaoImpl;
import ma.enset.metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
        /*
        Injection de dépendances par instanciation statique (utilisation de "new")
         */
        DaoImpl dao=new DaoImpl();
        MetierImpl metier=new MetierImpl();
        metier.setDao(dao);
        System.out.println ("Résultats = "+metier.calcul());
    }
}
