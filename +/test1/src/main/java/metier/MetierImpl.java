package main.java.metier;

import main.java.dao.IDao;

public class MetierImpl implements IMetier {

    //couplage faible
    private IDao dao;

    @Override
    public double calcul() {
        double tmp= dao.getData();
        double res=tmp*540/Math.cos(tmp*Math.PI);
        return res;
    }

    /*
    Injecter dans la variable dao un objet
    d'une classe qui implémente l'interface IDao
     */
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
