
package proxy;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour compte complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>{@code
 * <complexType name="compte">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="solde" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compte", propOrder = {
    "code",
    "solde"
})
public class Compte {

    protected int code;
    protected double solde;

    /**
     * Obtient la valeur de la propri�t� code.
     * 
     */
    public int getCode() {
        return code;
    }

    /**
     * D�finit la valeur de la propri�t� code.
     * 
     */
    public void setCode(int value) {
        this.code = value;
    }

    /**
     * Obtient la valeur de la propri�t� solde.
     * 
     */
    public double getSolde() {
        return solde;
    }

    /**
     * D�finit la valeur de la propri�t� solde.
     * 
     */
    public void setSolde(double value) {
        this.solde = value;
    }

}
