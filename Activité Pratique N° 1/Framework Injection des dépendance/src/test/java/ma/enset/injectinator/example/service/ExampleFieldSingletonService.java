package ma.enset.injectinator.example.service;

import ma.enset.injectinator.framework.annotation.InjectMe;
import ma.enset.injectinator.framework.annotation.InjectionType;
import ma.enset.injectinator.example.logger.Logger;

public class ExampleFieldSingletonService {

    @InjectMe(injectionType = InjectionType.SINGLETON)
    private Logger logger;

    public void doStuff() {
        this.logger.log("Field injection works.");
    }

    public void setExtraMessage(final String extraMessage) {
        this.logger.setExtraMessage(extraMessage);
    }

    public Logger getLogger() {
        return this.logger;
    }
}
