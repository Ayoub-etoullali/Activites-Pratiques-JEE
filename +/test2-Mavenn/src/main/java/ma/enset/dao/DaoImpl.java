package ma.enset.dao;

import org.springframework.stereotype.Component;

@Component("dao1") //au démarrage, à chaque fois qu'il trouve une classe qui commence par la note @Component, il va l'instancier comme nom "dao"
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
