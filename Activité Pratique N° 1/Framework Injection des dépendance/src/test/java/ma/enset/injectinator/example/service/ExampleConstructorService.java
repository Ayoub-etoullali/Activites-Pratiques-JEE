package ma.enset.injectinator.example.service;

import ma.enset.injectinator.example.logger.Logger;
import ma.enset.injectinator.framework.annotation.InjectMe;

public class ExampleConstructorService {

    private final Logger logger;

    @InjectMe
    public ExampleConstructorService(final Logger logger) {
        this.logger = logger;
    }

    public void doStuff() {
        this.logger.log("Constructor Injection works.");
    }

    public Logger getLogger() {
        return this.logger;
    }
}
