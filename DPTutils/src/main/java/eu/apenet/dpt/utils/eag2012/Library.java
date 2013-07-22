//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.17 at 08:52:22 AM CEST 
//


package eu.apenet.dpt.utils.eag2012;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}contact" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}webpage" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}monographicpub" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}serialpub" minOccurs="0"/>
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
    "contact",
    "webpage",
    "monographicpub",
    "serialpub"
})
@XmlRootElement(name = "library")
public class Library {

    protected Contact contact;
    protected List<Webpage> webpage;
    protected Monographicpub monographicpub;
    protected Serialpub serialpub;
    @XmlAttribute(name = "question", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String question;

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link Contact }
     *     
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setContact(Contact value) {
        this.contact = value;
    }

    /**
     * Gets the value of the webpage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the webpage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWebpage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Webpage }
     * 
     * 
     */
    public List<Webpage> getWebpage() {
        if (webpage == null) {
            webpage = new ArrayList<Webpage>();
        }
        return this.webpage;
    }

    /**
     * Gets the value of the monographicpub property.
     * 
     * @return
     *     possible object is
     *     {@link Monographicpub }
     *     
     */
    public Monographicpub getMonographicpub() {
        return monographicpub;
    }

    /**
     * Sets the value of the monographicpub property.
     * 
     * @param value
     *     allowed object is
     *     {@link Monographicpub }
     *     
     */
    public void setMonographicpub(Monographicpub value) {
        this.monographicpub = value;
    }

    /**
     * Gets the value of the serialpub property.
     * 
     * @return
     *     possible object is
     *     {@link Serialpub }
     *     
     */
    public Serialpub getSerialpub() {
        return serialpub;
    }

    /**
     * Sets the value of the serialpub property.
     * 
     * @param value
     *     allowed object is
     *     {@link Serialpub }
     *     
     */
    public void setSerialpub(Serialpub value) {
        this.serialpub = value;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
