package ma.enset.dao;

import org.springframework.stereotype.Component;

@Component("dao") //au démarrage, à chaque fois qu'il troouve une classe qui commence par la note @Component, il va l'instancier comme nom "dao"
public class DaoImpl implements IDao{
    @Override
    public double getData() {
        System.out.println("__version 1__");
        /*
        se connecter à la BD pour récupérer la température
         */
        double temp=Math.random()*40;
        return temp;
    }
}
