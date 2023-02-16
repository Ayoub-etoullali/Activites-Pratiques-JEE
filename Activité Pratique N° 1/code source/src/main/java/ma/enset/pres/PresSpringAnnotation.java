package ma.enset.pres;

import ma.enset.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringAnnotation {
    public static void main(String[] args) {

        ApplicationContext context=new AnnotationConfigApplicationContext("ma"); //"ma" c'est le base package [("dao","metier");]

        IMetier metier=context.getBean(IMetier.class);
        System.out.println("Resultats="+metier.calcul());


    }
}
