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
 *         &lt;element ref="{urn:isbn:1-931666-33-4}abbreviation" minOccurs="0"/>
 *         &lt;element ref="{urn:isbn:1-931666-33-4}citation"/>
 *         &lt;element ref="{urn:isbn:1-931666-33-4}descriptiveNote" minOccurs="0"/>
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
    "abbreviation",
    "citation",
    "descriptiveNote"
})
@XmlRootElement(name = "localTypeDeclaration")
public class LocalTypeDeclaration {

    protected Abbreviation abbreviation;
    @XmlElement(required = true)
    protected Citation citation;
    protected DescriptiveNote descriptiveNote;

    /**
     * Gets the value of the abbreviation property.
     * 
     * @return
     *     possible object is
     *     {@link Abbreviation }
     *     
     */
    public Abbreviation getAbbreviation() {
        return abbreviation;
    }

    /**
     * Sets the value of the abbreviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Abbreviation }
     *     
     */
    public void setAbbreviation(Abbreviation value) {
        this.abbreviation = value;
    }

    /**
     * Gets the value of the citation property.
     * 
     * @return
     *     possible object is
     *     {@link Citation }
     *     
     */
    public Citation getCitation() {
        return citation;
    }

    /**
     * Sets the value of the citation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Citation }
     *     
     */
    public void setCitation(Citation value) {
        this.citation = value;
    }

    /**
     * Gets the value of the descriptiveNote property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveNote }
     *     
     */
    public DescriptiveNote getDescriptiveNote() {
        return descriptiveNote;
    }

    /**
     * Sets the value of the descriptiveNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveNote }
     *     
     */
    public void setDescriptiveNote(DescriptiveNote value) {
        this.descriptiveNote = value;
    }

}