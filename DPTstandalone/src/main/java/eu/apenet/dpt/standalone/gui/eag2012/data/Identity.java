//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.17 at 08:52:22 AM CEST 
//


package eu.apenet.dpt.standalone.gui.eag2012.data;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}repositorid" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}otherRepositorId" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}autform" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}parform" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}nonpreform" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}repositoryType" maxOccurs="unbounded" minOccurs="0"/>
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
    "repositorid",
    "otherRepositorId",
    "autform",
    "parform",
    "nonpreform",
    "repositoryType"
})
@XmlRootElement(name = "identity")
public class Identity {

    protected Repositorid repositorid;
    protected OtherRepositorId otherRepositorId;
    @XmlElement(required = true)
    protected List<Autform> autform;
    protected List<Parform> parform;
    protected List<Nonpreform> nonpreform;
    protected List<RepositoryType> repositoryType;

    /**
     * Gets the value of the repositorid property.
     * 
     * @return
     *     possible object is
     *     {@link Repositorid }
     *     
     */
    public Repositorid getRepositorid() {
        return repositorid;
    }

    /**
     * Sets the value of the repositorid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repositorid }
     *     
     */
    public void setRepositorid(Repositorid value) {
        this.repositorid = value;
    }

    /**
     * Gets the value of the otherRepositorId property.
     * 
     * @return
     *     possible object is
     *     {@link OtherRepositorId }
     *     
     */
    public OtherRepositorId getOtherRepositorId() {
        return otherRepositorId;
    }

    /**
     * Sets the value of the otherRepositorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherRepositorId }
     *     
     */
    public void setOtherRepositorId(OtherRepositorId value) {
        this.otherRepositorId = value;
    }

    /**
     * Gets the value of the autform property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the autform property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutform().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Autform }
     * 
     * 
     */
    public List<Autform> getAutform() {
        if (autform == null) {
            autform = new ArrayList<Autform>();
        }
        return this.autform;
    }

    /**
     * Gets the value of the parform property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parform property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParform().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Parform }
     * 
     * 
     */
    public List<Parform> getParform() {
        if (parform == null) {
            parform = new ArrayList<Parform>();
        }
        return this.parform;
    }

    /**
     * Gets the value of the nonpreform property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nonpreform property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNonpreform().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Nonpreform }
     * 
     * 
     */
    public List<Nonpreform> getNonpreform() {
        if (nonpreform == null) {
            nonpreform = new ArrayList<Nonpreform>();
        }
        return this.nonpreform;
    }
    public void setNonpreform(List<Nonpreform> nonpreform) {
        this.nonpreform = nonpreform;
    }

    /**
     * Gets the value of the repositoryType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the repositoryType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRepositoryType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RepositoryType }
     * 
     * 
     */
    public List<RepositoryType> getRepositoryType() {
        if (repositoryType == null) {
            repositoryType = new ArrayList<RepositoryType>();
        }
        return this.repositoryType;
    }

}
