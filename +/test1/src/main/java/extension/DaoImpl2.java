package main.java.extension;

import main.java.dao.IDao;

public class DaoImpl2 implements IDao {

    @Override
    public double getData() {
        System.out.println("__version 2__");
        double temp=1000;
        return temp;
    }
}
