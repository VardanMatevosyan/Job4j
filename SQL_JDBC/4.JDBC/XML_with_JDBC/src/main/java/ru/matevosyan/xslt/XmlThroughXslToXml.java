package ru.matevosyan.xslt;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * XmlThroughXslToXml class to transform xml file to another xml file using xsl file as template.
 * Created on 07.01.2018
 * @author  Matevosyan Vardan.
 * @version 1.0
 */

public class XmlThroughXslToXml {
    private final String xslPath;
    private final String pathToXmlFile;
    private final String outputXml;

    /**
     * Constructor for XmlThroughXslToXml.
     * @param xslPath path to xsl file.
     * @param pathToXmlFile path to xml file which is input.
     * @param outputXml output xml file.
     */

    public XmlThroughXslToXml(final String xslPath, final String pathToXmlFile, final String outputXml) {
        this.xslPath = xslPath;
        this.pathToXmlFile = pathToXmlFile;
        this.outputXml = outputXml;
    }

    /**
     * Transform first xml file to second xml file.
     */

    public void transformation() {
        StreamSource streamSourceXsl = new StreamSource(this.xslPath);
        StreamSource streamSourceXml = new StreamSource(this.pathToXmlFile);
        StreamResult streamResult = new StreamResult(this.outputXml);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer(streamSourceXsl);
            transformer.transform(streamSourceXml, streamResult);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.getMessage();
        }
    }

}
