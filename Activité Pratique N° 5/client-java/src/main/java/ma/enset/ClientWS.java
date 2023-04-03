package ma.enset;

import proxy.BanqueService;
import proxy.BanqueWS;
import proxy.Compte;

public class ClientWS {
    public static void main(String[] args) {
        //générer un proxy : à partir de Wsdl je vais générer un classe java qui permètre de communiquer avec le Web Service
        //sélectionner dossier "src" -> Help -> Find Action... -> cherche "Generate Java code From Wsdl (plugin Jakarta WS)

        BanqueService stub=new BanqueWS().getBanqueServicePort(); //proxy client, on l'appel stub qui est un Middleware (intérmédiaire)
                                                                //grace à cette objet stub, mon application peut communiquer à la WS facilement
                                                                //=> POOD
        System.out.println(stub.convert(7600)); //exemple

        Compte cp=stub.getCompte(5);
        System.out.println(cp.getCode());
        System.out.println(cp.getSolde());
    }
}