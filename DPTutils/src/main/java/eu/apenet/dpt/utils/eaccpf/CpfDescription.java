//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.01 at 03:56:00 PM CEST 
//


package eu.apenet.dpt.utils.eaccpf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:isbn:1-931666-33-4}identity"/>
 *         &lt;element ref="{urn:isbn:1-931666-33-4}description" minOccurs="0"/>
 *         &lt;element ref="{urn:isbn:1-931666-33-4}relations" minOccurs="0"/>
 *         &lt;element ref="{urn:isbn:1-931666-33-4}alternativeSet" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "identity",
    "description",
    "relations",
    "alternativeSet"
})
@XmlRootElement(name = "cpfDescription")
public class CpfDescription {

    @XmlElement(required = true)
    protected Identity identity;
    protected Description description;
    protected Relations relations;
    protected AlternativeSet alternativeSet;

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link Identity }
     *     
     */
    public Identity getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Identity }
     *     
     */
    public void setIdentity(Identity value) {
        this.identity = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Gets the value of the relations property.
     * 
     * @return
     *     possible object is
     *     {@link Relations }
     *     
     */
    public Relations getRelations() {
        return relations;
    }

    /**
     * Sets the value of the relations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Relations }
     *     
     */
    public void setRelations(Relations value) {
        this.relations = value;
    }

    /**
     * Gets the value of the alternativeSet property.
     * 
     * @return
     *     possible object is
     *     {@link AlternativeSet }
     *     
     */
    public AlternativeSet getAlternativeSet() {
        return alternativeSet;
    }

    /**
     * Sets the value of the alternativeSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlternativeSet }
     *     
     */
    public void setAlternativeSet(AlternativeSet value) {
        this.alternativeSet = value;
    }

}