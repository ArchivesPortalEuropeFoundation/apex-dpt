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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for relations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="relations"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cpfRelation" type="{urn:isbn:1-931666-33-4}cpfRelation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="resourceRelation" type="{urn:isbn:1-931666-33-4}resourceRelation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="functionRelation" type="{urn:isbn:1-931666-33-4}functionRelation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "relations", propOrder = {
    "cpfRelation",
    "resourceRelation",
    "functionRelation"
})
public class Relations {

    protected List<CpfRelation> cpfRelation;
    protected List<ResourceRelation> resourceRelation;
    protected List<FunctionRelation> functionRelation;

    /**
     * Gets the value of the cpfRelation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cpfRelation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCpfRelation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CpfRelation }
     * 
     * 
     */
    public List<CpfRelation> getCpfRelation() {
        if (cpfRelation == null) {
            cpfRelation = new ArrayList<CpfRelation>();
        }
        return this.cpfRelation;
    }

    /**
     * Gets the value of the resourceRelation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceRelation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceRelation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceRelation }
     * 
     * 
     */
    public List<ResourceRelation> getResourceRelation() {
        if (resourceRelation == null) {
            resourceRelation = new ArrayList<ResourceRelation>();
        }
        return this.resourceRelation;
    }

    /**
     * Gets the value of the functionRelation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the functionRelation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFunctionRelation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FunctionRelation }
     * 
     * 
     */
    public List<FunctionRelation> getFunctionRelation() {
        if (functionRelation == null) {
            functionRelation = new ArrayList<FunctionRelation>();
        }
        return this.functionRelation;
    }

}
