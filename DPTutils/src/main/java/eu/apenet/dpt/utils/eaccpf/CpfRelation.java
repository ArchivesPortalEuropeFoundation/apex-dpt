//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.01 at 03:56:00 PM CEST 
//


package eu.apenet.dpt.utils.eaccpf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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
 *     &lt;extension base="{urn:isbn:1-931666-33-4}m.relationBody">
 *       &lt;attribute name="cpfRelationType">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="identity"/>
 *             &lt;enumeration value="hierarchical"/>
 *             &lt;enumeration value="hierarchical-parent"/>
 *             &lt;enumeration value="hierarchical-child"/>
 *             &lt;enumeration value="temporal"/>
 *             &lt;enumeration value="temporal-earlier"/>
 *             &lt;enumeration value="temporal-later"/>
 *             &lt;enumeration value="family"/>
 *             &lt;enumeration value="associative"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "cpfRelation")
public class CpfRelation
    extends MRelationBody
{

    @XmlAttribute(name = "cpfRelationType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String cpfRelationType;

    /**
     * Gets the value of the cpfRelationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpfRelationType() {
        return cpfRelationType;
    }

    /**
     * Sets the value of the cpfRelationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpfRelationType(String value) {
        this.cpfRelationType = value;
    }

}
