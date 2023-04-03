package ma.enset;

import ma.enset.annotation.InjectMe;
import ma.enset.config.Syringe;

import java.lang.reflect.Field;

public class Injectinator {

    private final Syringe syringe;

    private Injectinator(Syringe syringe){
        this.syringe=syringe;
        this.syringe.configure();
    }

    public static Injectinator getInjectinator(Syringe syringe){
        return new Injectinator(syringe);
    }

    public <T> T inject(Class<T> ClassToInjectTo){
        return injectViaFields(ClassToInjectTo);    
    }

    private <T> void injectViaFields(Class<T> classToInjectTo) throws NoSuchMethodException {

        T instance=classToInjectTo.getConstructor().newInstance();
        for (Field field : classToInjectTo.getDeclaredFields()) {
            if ( field.isAnnotationPresent(InjectMe.class)){
                field.setAccessible(true);
                field.set(instance,inject(syringe.getInjectable(field.getType())));
            }
        }
        return instance;
    }
}
