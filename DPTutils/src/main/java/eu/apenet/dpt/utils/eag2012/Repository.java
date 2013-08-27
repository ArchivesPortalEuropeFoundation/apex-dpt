//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.17 at 08:52:22 AM CEST 
//


package eu.apenet.dpt.utils.eag2012;

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
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}repositoryName" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}repositoryRole" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}geogarea"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}location" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}telephone" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}fax" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}email" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}webpage" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}directions" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}repositorhist" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}repositorfound" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}repositorsup" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}buildinginfo" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}adminhierarchy" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}holdings" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}timetable"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}access"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}accessibility" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}services" minOccurs="0"/>
 *         &lt;element ref="{http://www.archivesportaleurope.eu/profiles/eag/}descriptiveNote" minOccurs="0"/>
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
    "repositoryName",
    "repositoryRole",
    "geogarea",
    "location",
    "telephone",
    "fax",
    "email",
    "webpage",
    "directions",
    "repositorhist",
    "repositorfound",
    "repositorsup",
    "buildinginfo",
    "adminhierarchy",
    "holdings",
    "timetable",
    "access",
    "accessibility",
    "services",
    "descriptiveNote"
})
@XmlRootElement(name = "repository")
public class Repository {

    protected List<RepositoryName> repositoryName;
    protected RepositoryRole repositoryRole;
    @XmlElement(required = true)
    protected Geogarea geogarea;
    @XmlElement(required = true)
    protected List<Location> location;
    protected List<Telephone> telephone;
    protected List<Fax> fax;
    @XmlElement(required = true)
    protected List<Email> email;
    protected List<Webpage> webpage;
    protected List<Directions> directions;
    protected Repositorhist repositorhist;
    protected Repositorfound repositorfound;
    protected Repositorsup repositorsup;
    protected Buildinginfo buildinginfo;
    protected Adminhierarchy adminhierarchy;
    protected Holdings holdings;
    @XmlElement(required = true)
    protected Timetable timetable;
    @XmlElement(required = true)
    protected Access access;
    @XmlElement(required = true)
    protected List<Accessibility> accessibility;
    protected Services services;
    protected DescriptiveNote descriptiveNote;

    /**
     * Gets the value of the repositoryName property.
     * 
     * @return
     *     possible object is
     *     {@link RepositoryName }
     *     
     */
    public List<RepositoryName> getRepositoryName() {
        return repositoryName;
    }

    /**
     * Sets the value of the repositoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepositoryName }
     *     
     */
    public void setRepositoryName(List<RepositoryName> value) {
        this.repositoryName = value;
    }

    /**
     * Gets the value of the repositoryRole property.
     * 
     * @return
     *     possible object is
     *     {@link RepositoryRole }
     *     
     */
    public RepositoryRole getRepositoryRole() {
        return repositoryRole;
    }

    /**
     * Sets the value of the repositoryRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepositoryRole }
     *     
     */
    public void setRepositoryRole(RepositoryRole value) {
        this.repositoryRole = value;
    }

    /**
     * Gets the value of the geogarea property.
     * 
     * @return
     *     possible object is
     *     {@link Geogarea }
     *     
     */
    public Geogarea getGeogarea() {
        return geogarea;
    }

    /**
     * Sets the value of the geogarea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Geogarea }
     *     
     */
    public void setGeogarea(Geogarea value) {
        this.geogarea = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the location property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Location }
     * 
     * 
     */
    public List<Location> getLocation() {
        if (location == null) {
            location = new ArrayList<Location>();
        }
        return this.location;
    }

    /**
     * Gets the value of the telephone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the telephone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTelephone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Telephone }
     * 
     * 
     */
    public List<Telephone> getTelephone() {
        if (telephone == null) {
            telephone = new ArrayList<Telephone>();
        }
        return this.telephone;
    }

    /**
     * Gets the value of the fax property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fax property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFax().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Fax }
     * 
     * 
     */
    public List<Fax> getFax() {
        if (fax == null) {
            fax = new ArrayList<Fax>();
        }
        return this.fax;
    }

    /**
     * Gets the value of the email property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the email property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Email }
     * 
     * 
     */
    public List<Email> getEmail() {
        if (email == null) {
            email = new ArrayList<Email>();
        }
        return this.email;
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
     * Gets the value of the directions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the directions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirections().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Directions }
     * 
     * 
     */
    public List<Directions> getDirections() {
        if (directions == null) {
            directions = new ArrayList<Directions>();
        }
        return this.directions;
    }

    /**
     * Gets the value of the repositorhist property.
     * 
     * @return
     *     possible object is
     *     {@link Repositorhist }
     *     
     */
    public Repositorhist getRepositorhist() {
        return repositorhist;
    }

    /**
     * Sets the value of the repositorhist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repositorhist }
     *     
     */
    public void setRepositorhist(Repositorhist value) {
        this.repositorhist = value;
    }

    /**
     * Gets the value of the repositorfound property.
     * 
     * @return
     *     possible object is
     *     {@link Repositorfound }
     *     
     */
    public Repositorfound getRepositorfound() {
        return repositorfound;
    }

    /**
     * Sets the value of the repositorfound property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repositorfound }
     *     
     */
    public void setRepositorfound(Repositorfound value) {
        this.repositorfound = value;
    }

    /**
     * Gets the value of the repositorsup property.
     * 
     * @return
     *     possible object is
     *     {@link Repositorsup }
     *     
     */
    public Repositorsup getRepositorsup() {
        return repositorsup;
    }

    /**
     * Sets the value of the repositorsup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repositorsup }
     *     
     */
    public void setRepositorsup(Repositorsup value) {
        this.repositorsup = value;
    }

    /**
     * Gets the value of the buildinginfo property.
     * 
     * @return
     *     possible object is
     *     {@link Buildinginfo }
     *     
     */
    public Buildinginfo getBuildinginfo() {
        return buildinginfo;
    }

    /**
     * Sets the value of the buildinginfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Buildinginfo }
     *     
     */
    public void setBuildinginfo(Buildinginfo value) {
        this.buildinginfo = value;
    }

    /**
     * Gets the value of the adminhierarchy property.
     * 
     * @return
     *     possible object is
     *     {@link Adminhierarchy }
     *     
     */
    public Adminhierarchy getAdminhierarchy() {
        return adminhierarchy;
    }

    /**
     * Sets the value of the adminhierarchy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Adminhierarchy }
     *     
     */
    public void setAdminhierarchy(Adminhierarchy value) {
        this.adminhierarchy = value;
    }

    /**
     * Gets the value of the holdings property.
     * 
     * @return
     *     possible object is
     *     {@link Holdings }
     *     
     */
    public Holdings getHoldings() {
        return holdings;
    }

    /**
     * Sets the value of the holdings property.
     * 
     * @param value
     *     allowed object is
     *     {@link Holdings }
     *     
     */
    public void setHoldings(Holdings value) {
        this.holdings = value;
    }

    /**
     * Gets the value of the timetable property.
     * 
     * @return
     *     possible object is
     *     {@link Timetable }
     *     
     */
    public Timetable getTimetable() {
        return timetable;
    }

    /**
     * Sets the value of the timetable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Timetable }
     *     
     */
    public void setTimetable(Timetable value) {
        this.timetable = value;
    }

    /**
     * Gets the value of the access property.
     * 
     * @return
     *     possible object is
     *     {@link Access }
     *     
     */
    public Access getAccess() {
        return access;
    }

    /**
     * Sets the value of the access property.
     * 
     * @param value
     *     allowed object is
     *     {@link Access }
     *     
     */
    public void setAccess(Access value) {
        this.access = value;
    }

    /**
     * Gets the value of the accessibility property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessibility property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessibility().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Accessibility }
     * 
     * 
     */
    public List<Accessibility> getAccessibility() {
        if (accessibility == null) {
            accessibility = new ArrayList<Accessibility>();
        }
        return this.accessibility;
    }

    public void setAccessibility(List<Accessibility> accessibility) {
        this.accessibility = accessibility;
    }

    /**
     * Gets the value of the services property.
     * 
     * @return
     *     possible object is
     *     {@link Services }
     *     
     */
    public Services getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link Services }
     *     
     */
    public void setServices(Services value) {
        this.services = value;
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
