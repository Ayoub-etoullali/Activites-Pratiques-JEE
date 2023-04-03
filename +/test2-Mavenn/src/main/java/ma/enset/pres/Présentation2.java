package ma.enset.pres;

import ma.enset.dao.IDao;
import ma.enset.metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Présentation2 {
    public static void main(String[] args) throws  Exception {

        Scanner scanner=new Scanner(new File("config.txt"));

        String daoClassName=scanner.nextLine();
        Class cDao=Class.forName(daoClassName); //charger un classe (tout en bytecode)
        IDao dao = (IDao) cDao.newInstance();


        String metierClassName=scanner.nextLine();
        Class cMetier=Class.forName(metierClassName); //charger un classe (tout en bytecode)
        IMetier metier = (IMetier) cMetier.newInstance();

        Method method= cMetier.getMethod("setDao", IDao.class);
        // metier.setDao(dao); => statique
        method.invoke(metier,dao); //j'invoke (exécute) la méthode setDao sur l'objet "metier" en transformer en paramètre "dao" => dynamique
        System.out.println ("Résultats="+metier.calcul() );
    }
}
