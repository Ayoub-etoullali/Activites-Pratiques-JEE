package ma.enset.config;

public interface Syringe {

    void configure();
    <T> Class< ? extends  T> getInjectable(Class<T> type);
}
