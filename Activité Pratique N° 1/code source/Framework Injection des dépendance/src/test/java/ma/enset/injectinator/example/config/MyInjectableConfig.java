package ma.enset.injectinator.example.config;

import ma.enset.injectinator.example.logger.ExtraLogger;
import ma.enset.injectinator.example.logger.Logger;
import ma.enset.injectinator.framework.module.AbstractConfigModule;
import ma.enset.injectinator.example.logger.AnotherLogger;
import ma.enset.injectinator.example.logger.DefaultLogger;

public class MyInjectableConfig extends AbstractConfigModule {

    @Override
    public void configure() {
        enableInjectable(Logger.class, DefaultLogger.class);
        enableInjectable(AnotherLogger.class, ExtraLogger.class);
    }
}
