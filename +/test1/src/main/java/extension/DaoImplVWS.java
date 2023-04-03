package main.java.extension;

import main.java.dao.IDao;

public class DaoImplVWS implements IDao {

    // VMS = Version Web Service
    @Override
    public double getData() {
        System.out.println("__version Web Service__");
        double temp=1000;
        return temp;
    }
}
