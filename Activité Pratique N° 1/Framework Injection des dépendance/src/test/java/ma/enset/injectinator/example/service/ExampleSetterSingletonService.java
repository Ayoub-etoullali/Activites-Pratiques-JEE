package ma.enset.injectinator.example.service;

import ma.enset.injectinator.framework.annotation.InjectMe;
import ma.enset.injectinator.framework.annotation.InjectionType;
import ma.enset.injectinator.example.logger.Logger;

public class ExampleSetterSingletonService {

    private Logger logger;

    public void doStuff() {
        this.logger.log("Setter Injection works.");
    }

    @InjectMe(injectionType = InjectionType.SINGLETON)
    public void setLogger(final Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return this.logger;
    }
}
