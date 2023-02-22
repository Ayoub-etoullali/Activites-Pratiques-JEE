package ma.enset.extension;

import ma.enset.dao.IDao;
import org.springframework.stereotype.Component;

@Component("dao2")
public class DaoImpl2 implements IDao {

    @Override
    public double getData() {
        System.out.println("__version 2__");
        double temp=1000;
        return temp;
    }
}
