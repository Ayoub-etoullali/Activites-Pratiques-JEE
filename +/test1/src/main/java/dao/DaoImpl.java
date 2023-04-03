package main.java.dao;

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
