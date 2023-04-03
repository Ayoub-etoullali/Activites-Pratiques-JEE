package ma.enset.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.Date;

@XmlRootElement(name = "compte") //si je l'ai converti la classe Compte à XML, il me donne avec le nom "compte"
@XmlAccessorType(XmlAccessType.FIELD) //j'aimerais mettre les annotations dans les champs et pas les getters/setters
public class Compte {

    private int code;
    private double solde;
    @XmlTransient //ignore cette attribut dans la convertion [JaxB] #N.B il faut utiliser @XmlAccessorType(XmlAccessType.FIELD) pour qu'elle marche
    private Date dateCreation;

    public Compte() {
    }

    public Compte(int code, double solde, Date dateCreation) {
        this.code = code;
        this.solde = solde;
        this.dateCreation = dateCreation;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
