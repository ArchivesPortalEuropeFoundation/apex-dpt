/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.apenet.dpt.utils.ese2edm;

import eu.apenet.dpt.utils.ead2ese.XMLTransformer;
import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author papp
 */
public class EdmConfig implements Serializable {

    private static final long serialVersionUID = -3232731426711003838L;
    private XMLTransformer transformerXML2XML;
    private XMLTransformer transformerXML2HTML;

    private String edmIdentifier;
    private String prefixUrl;
    private String repositoryCode;
    private String xmlTypeName;
    private boolean minimalConversion;
    private Properties properties;

    private boolean transferToFileOutput;

    public EdmConfig(boolean transferToFileOutput) {
        this.transferToFileOutput = transferToFileOutput;
    }

    public XMLTransformer getTransformerXML2XML() {
        if (transformerXML2XML == null) {
            transformerXML2XML = new XMLTransformer("/ese2edm/ese2edm.xslt", getProperties());
        }
        return transformerXML2XML;
    }

    public String getEdmIdentifier() {
        return edmIdentifier;
    }

    public void setEdmIdentifier(String edmIdentifier) {
        this.edmIdentifier = edmIdentifier;
    }

    public String getPrefixUrl() {
        return prefixUrl;
    }

    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    public String getRepositoryCode() {
        return repositoryCode;
    }

    public void setRepositoryCode(String repositoryCode) {
        this.repositoryCode = repositoryCode;
    }

    public String getXmlTypeName() {
        return xmlTypeName;
    }

    public void setXmlTypeName(String xmlTypeName) {
        this.xmlTypeName = xmlTypeName;
    }

    public boolean isTransferToFileOutput() {
        return transferToFileOutput;
    }

    public void setTransferToFileOutput(boolean transferToFileOutput) {
        this.transferToFileOutput = transferToFileOutput;
            transformerXML2XML = new XMLTransformer("/ese2edm/ese2edm.xslt", getProperties());
    }

    /**
	 * @return the minimalConversion
	 */
	public boolean isMinimalConversion() {
		return this.minimalConversion;
	}

	/**
	 * @param minimalConversion the minimalConversion to set
	 */
	public void setMinimalConversion(boolean minimalConversion) {
		this.minimalConversion = minimalConversion;
	}

    public Properties getProperties() {
        if (properties == null) {
			properties = new Properties();
			properties.put("edm_identifier", getString(getEdmIdentifier()));
			properties.put("prefix_url", getString(getPrefixUrl()));
                        String repCodeAfterReplacement = getString(getRepositoryCode()).replace('/', '_');
			properties.put("repository_code", getString(repCodeAfterReplacement));
			properties.put("xml_type_name", getString(getXmlTypeName()));
			properties.put("minimalConversion", getString(new Boolean(isMinimalConversion()).toString()));
        }
        return properties;
    }

    private static String getString(String string) {
        if (string == null) {
            return "";
        } else {
            return string;
        }
    }

    public XMLTransformer getTransformerXML2HTML() {
        if (transformerXML2HTML == null) {
            transformerXML2HTML = new XMLTransformer("/ese2edm/edm2html.xslt", getProperties());
        }
        return transformerXML2HTML;
    }
}
