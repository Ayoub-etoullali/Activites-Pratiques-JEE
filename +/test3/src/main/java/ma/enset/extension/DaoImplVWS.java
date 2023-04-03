package ma.enset.extension;

import ma.enset.dao.IDao;
import org.springframework.stereotype.Component;

@Component("dao3")
public class DaoImplVWS implements IDao {

    // VMS = Version Web Service
    @Override
    public double getData() {
        System.out.println("__version Web Service__");
        return 90;
    }
}
