package ma.enset.metier;

import ma.enset.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // @Component("MetierImpl")
public class MetierImpl implements IMetier {

    //=>couplage faible

    //pour faire l'injection de dépendance on utilise la notation
//    @Autowired //damander à Spring au moment on va instancier la classe "MetierImpl" cherche parmi les objets qui déja créer, si tu trouve un objet de type "IDao", tu vas me l'injecter dans le variable "dao"
//    @Qualifier("dao1") // injecte moi l'instance dao1

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
