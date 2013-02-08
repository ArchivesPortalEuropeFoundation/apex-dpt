package eu.apenet.dpt.utils.service;

import eu.apenet.dpt.utils.util.APEXmlCatalogResolver;
import eu.apenet.dpt.utils.util.DiscardDtdEntityResolver;
import eu.apenet.dpt.utils.util.Xsd_enum;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.ValidatorHandler;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;


/**
 * User: Yoann Moranville
 * Date: 16 dec. 2009
 */
public class DocumentValidation {
    private static final Logger LOG = Logger.getLogger(DocumentValidation.class);

    /* @param in The actual InputStream of the XML file
     * @param isApeFormat Is true if the XML data has to be validated against our schema, false if it just has to be validated against EAD 2002.
     * @param schemaToUse Is the schema contained in an enum.
     * @return A List of SAXParserException containing the errors of the validation. Null if there are none.
     */
    public static List<SAXParseException> xmlValidation(InputStream in, URL schemaPath, boolean isXsd11) throws SAXException {
        ErrorHandler  errorHandler = new ErrorHandler();
        try {
            List<URL> schemaURLs = new ArrayList<URL>();
            if(isXsd11)
                System.setProperty("javax.xml.validation.SchemaFactory:http://www.w3.org/XML/XMLSchema/v1.1", "org.apache.xerces.jaxp.validation.XMLSchema11Factory");
            schemaURLs.add(schemaPath);
            schemaURLs.add(DocumentValidation.class.getResource("/xlink.xsd"));
            Schema schema = getSchema(schemaURLs, isXsd11);

            XMLReader saxParser = XMLReaderFactory.createXMLReader();

            saxParser.setEntityResolver(new DiscardDtdEntityResolver());
            ValidatorHandler vHandler = schema.newValidatorHandler();
            vHandler.setErrorHandler(errorHandler);
            saxParser.setContentHandler(vHandler);
            saxParser.parse(new InputSource(in));

            if (!errorHandler.exceptions.isEmpty()) {
                return errorHandler.exceptions;
            }
            return null;
        } catch (SAXException e) {
            LOG.error("Problem validating file " + e);
            throw new SAXException(e);
        } catch (IOException e) {
            LOG.error("Problem reading file while validating " + e);
            throw new RuntimeException(e);
        }
    }

    public static List<SAXParseException> xmlValidation(File file, URL schemaPath, boolean isXsd11) throws SAXException, IOException {
        return xmlValidation(FileUtils.openInputStream(file), schemaPath, isXsd11);
    }

    public static List<SAXParseException> xmlValidation(String filePath, URL schemaPath, boolean isXsd11) throws SAXException, IOException {
        return xmlValidation(new File(filePath), schemaPath, isXsd11);
    }

    private static Schema getSchema(List<URL> schemaURLs, boolean isXsd11) throws SAXException {
        SchemaFactory schemaFactory;
        if(isXsd11)
		    schemaFactory = SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");
        else
            schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        schemaFactory.setResourceResolver(new APEXmlCatalogResolver());
		List<StreamSource> schemaSources = new ArrayList<StreamSource>();
		for (URL schemaURL : schemaURLs)
			schemaSources.add(new StreamSource(schemaURL.toExternalForm()));
		return schemaFactory.newSchema(schemaSources.toArray(new StreamSource[]{})); //todo: test schemaSources.toArray(new StreamSource[schemaSources.size()])
	}

    private static class ErrorHandler extends DefaultHandler {
        private List<SAXParseException> exceptions = new ArrayList<SAXParseException>();

        public void error(SAXParseException parseException) throws SAXException {
            exceptions.add(SAXParseAPEnetException.changeException(parseException.getMessage(), parseException));
        }

        public void fatalError(SAXParseException parseException) throws SAXException {
            exceptions.add(parseException);
        }
    }
}
