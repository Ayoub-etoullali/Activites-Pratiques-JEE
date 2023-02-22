package ma.enset.injectinator.example.service;

import ma.enset.injectinator.example.logger.Logger;
import ma.enset.injectinator.framework.annotation.InjectMe;
import ma.enset.injectinator.framework.annotation.InjectionType;

public class ExampleConstructorSingletonService {

    private final Logger logger;

    @InjectMe(injectionType = InjectionType.SINGLETON)
    public ExampleConstructorSingletonService(final Logger logger) {
        this.logger = logger;
    }

    public void doStuff() {
        this.logger.log("Constructor Injection works.");
    }

    public Logger getLogger() {
        return this.logger;
    }
}
