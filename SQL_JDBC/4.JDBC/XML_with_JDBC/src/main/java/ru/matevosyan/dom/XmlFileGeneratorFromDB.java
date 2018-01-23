package ru.matevosyan.dom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * XmlFileGeneratorFromDB using for creation xml file from database data.
 * Created on 06.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class XmlFileGeneratorFromDB {
    private final Connection connection;
    private Document document;
    private static final Logger LOG = LoggerFactory.getLogger("XmlFileGeneratorFromDB");


    /**
     * Constructor for connection.
     * @param connection database connection.
     */

    public XmlFileGeneratorFromDB(Connection connection) {
        this.connection = connection;
        this.document = this.getNewDocumentForCreatingMarking();
    }

    /**
     * Create xml file.
     * @param filePath file path to create file.
     */

    public void createXMLFile(String filePath) {
        this.createHierarchyWithValue(this.getResultSetFromDB());
        this.transformLogicalDOMtoConcreteHDDPlace(filePath);
    }

    /**
     * transform logical DOM to concrete HDD place.
     * @param filePath file path.
     */

    private void transformLogicalDOMtoConcreteHDDPlace(String filePath) {
        TransformerFactory transformFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformFactory.newTransformer();
            DOMSource domSource = new DOMSource(this.document);
            StreamResult streamResult = new StreamResult(new File(filePath));
            //indents
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //encoding file
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            //indent-amount
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(domSource, streamResult);
            LOG.debug("XML file is created");
        } catch (TransformerConfigurationException tce) {
            LOG.warn("Problem with getting configuration for transformer object", tce);
        } catch (TransformerException te) {
            LOG.warn("Problem with transform xmlSource to target file", te);
        }
    }

    /**
     * create hierarchy with value.
     * @param resultSet return result set object.
     */

    private void createHierarchyWithValue(ResultSet resultSet) {
        Element rootElement = this.document.createElement("entries");
        this.document.appendChild(rootElement);

        try {
            while (resultSet != null && resultSet.next()) {
                Element entry = this.document.createElement("entry");
                rootElement.appendChild(entry);

                Element field = this.document.createElement("field");
                field.appendChild(this.document.createTextNode(String.valueOf(resultSet.getInt(1))));
                entry.appendChild(field);
                LOG.debug("Document was configured with all values from database and root is - {}", rootElement);
            }
        } catch (SQLException sql) {
            LOG.warn("Problem with getting result from result set", sql);
        }
    }

    /**
     * Create new document for marking.
     * @return new document.
     */
    private Document getNewDocumentForCreatingMarking() {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            document = factory.newDocumentBuilder().newDocument();
            LOG.debug("Get document to configure DOMSource object - {}", document);
        } catch (ParserConfigurationException pce) {
            LOG.warn("Problem with get new document", pce);
        }

        return document;
    }

    /**
     * get result set from data base.
     * @return result set of all value from database.
     */

    private ResultSet getResultSetFromDB() {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM test WHERE field IS NOT NULL";
        try {
            Statement st = this.connection.createStatement();
            resultSet = st.executeQuery(sql);
            LOG.debug("execute query is ok");
        } catch (SQLException e) {
            LOG.warn("Problem with execute query or creation statement to database", sql);
        }

        return resultSet;
    }

}
