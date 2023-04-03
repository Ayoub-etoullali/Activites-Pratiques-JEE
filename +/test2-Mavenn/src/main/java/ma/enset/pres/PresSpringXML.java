package ma.enset.pres;

import ma.enset.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringXML {
    public static void main(String[] args) {

        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        IMetier metier=(IMetier) context.getBean("m");
        System.out.println("Resultats="+metier.calcul());

    }
}
