package ma.enset.injectinator.example.service;

import ma.enset.injectinator.framework.annotation.InjectMe;
import ma.enset.injectinator.example.logger.Logger;

public class ExampleSetterService {

    private Logger logger;

    public void doStuff() {
        this.logger.log("Setter Injection works.");
    }

    @InjectMe
    public void setLogger(final Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return this.logger;
    }
}
