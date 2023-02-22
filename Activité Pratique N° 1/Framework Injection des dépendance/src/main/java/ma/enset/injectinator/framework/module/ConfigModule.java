package ma.enset.injectinator.framework.module;

import ma.enset.injectinator.framework.Injectinator;

public interface ConfigModule {

    /**
     * The implementation of this class should initiate all the logic that enables
     * {@link Injectinator} to be initialized
     * and do its work.
     */
    void configure();

    <T> Class<? extends T> getInjectable(final Class<T> type);
}
