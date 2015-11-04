//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 12:52:00 PM CET 
//


package eu.apenet.dpt.utils.eaccpf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for m.simpleDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="m.simpleDescription"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="term" type="{urn:isbn:1-931666-33-4}term" minOccurs="0"/&gt;
 *         &lt;element name="placeEntry" type="{urn:isbn:1-931666-33-4}placeEntry" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;group ref="{urn:isbn:1-931666-33-4}m.dates" minOccurs="0"/&gt;
 *         &lt;element name="citation" type="{urn:isbn:1-931666-33-4}citation" minOccurs="0"/&gt;
 *         &lt;element name="descriptiveNote" type="{urn:isbn:1-931666-33-4}descriptiveNote" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "m.simpleDescription", propOrder = {
    "term",
    "placeEntry",
    "date",
    "dateRange",
    "dateSet",
    "citation",
    "descriptiveNote"
})
@XmlSeeAlso({
    LocalDescription.class,
    LegalStatus.class,
    Occupation.class,
    Function.class,
    Mandate.class
})
public class MSimpleDescription {

    protected Term term;
    protected List<PlaceEntry> placeEntry;
    protected Date date;
    protected DateRange dateRange;
    protected DateSet dateSet;
    protected Citation citation;
    protected DescriptiveNote descriptiveNote;

    /**
     * Gets the value of the term property.
     * 
     * @return
     *     possible object is
     *     {@link Term }
     *     
     */
    public Term getTerm() {
        return term;
    }

    /**
     * Sets the value of the term property.
     * 
     * @param value
     *     allowed object is
     *     {@link Term }
     *     
     */
    public void setTerm(Term value) {
        this.term = value;
    }

    /**
     * Gets the value of the placeEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the placeEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlaceEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlaceEntry }
     * 
     * 
     */
    public List<PlaceEntry> getPlaceEntry() {
        if (placeEntry == null) {
            placeEntry = new ArrayList<PlaceEntry>();
        }
        return this.placeEntry;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * Gets the value of the dateRange property.
     * 
     * @return
     *     possible object is
     *     {@link DateRange }
     *     
     */
    public DateRange getDateRange() {
        return dateRange;
    }

    /**
     * Sets the value of the dateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateRange }
     *     
     */
    public void setDateRange(DateRange value) {
        this.dateRange = value;
    }

    /**
     * Gets the value of the dateSet property.
     * 
     * @return
     *     possible object is
     *     {@link DateSet }
     *     
     */
    public DateSet getDateSet() {
        return dateSet;
    }

    /**
     * Sets the value of the dateSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateSet }
     *     
     */
    public void setDateSet(DateSet value) {
        this.dateSet = value;
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
