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
 *       &lt;attribute name="resourceRelationType">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="creatorOf"/>
 *             &lt;enumeration value="subjectOf"/>
 *             &lt;enumeration value="other"/>
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
@XmlRootElement(name = "resourceRelation")
public class ResourceRelation
    extends MRelationBody
{

    @XmlAttribute(name = "resourceRelationType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String resourceRelationType;

    /**
     * Gets the value of the resourceRelationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceRelationType() {
        return resourceRelationType;
    }

    /**
     * Sets the value of the resourceRelationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceRelationType(String value) {
        this.resourceRelationType = value;
    }

}