//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.17 at 08:52:22 AM CEST 
//


package eu.apenet.dpt.utils.eag2012;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}building" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}repositorarea" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}lengthshelf" minOccurs="0"/>
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
    "building",
    "repositorarea",
    "lengthshelf"
})
@XmlRootElement(name = "buildinginfo")
public class Buildinginfo {

    protected Building building;
    protected Repositorarea repositorarea;
    protected Lengthshelf lengthshelf;

    /**
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link Building }
     *     
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link Building }
     *     
     */
    public void setBuilding(Building value) {
        this.building = value;
    }

    /**
     * Gets the value of the repositorarea property.
     * 
     * @return
     *     possible object is
     *     {@link Repositorarea }
     *     
     */
    public Repositorarea getRepositorarea() {
        return repositorarea;
    }

    /**
     * Sets the value of the repositorarea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repositorarea }
     *     
     */
    public void setRepositorarea(Repositorarea value) {
        this.repositorarea = value;
    }

    /**
     * Gets the value of the lengthshelf property.
     * 
     * @return
     *     possible object is
     *     {@link Lengthshelf }
     *     
     */
    public Lengthshelf getLengthshelf() {
        return lengthshelf;
    }

    /**
     * Sets the value of the lengthshelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lengthshelf }
     *     
     */
    public void setLengthshelf(Lengthshelf value) {
        this.lengthshelf = value;
    }

}