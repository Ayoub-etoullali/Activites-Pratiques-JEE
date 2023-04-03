import jakarta.xml.ws.Endpoint;
import ma.enset.ws.BanqueService;

public class ServerJWS {
    public static void main(String[] args) {

        //démarrer un service http qui utilise le port 8088 pour consulter uniquement ce web service
        Endpoint.publish("http://localhost:8088/",new BanqueService()); // => connecteur http pour le WS
        //0.0.0.0 : cad accepte n'importe address IP
        System.out.println("Web Service déployé sur l'adresse http://localhost:8088/");
    }
}
