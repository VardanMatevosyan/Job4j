package ru.matevosyan.stax;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import javanet.staxutils.IndentingXMLEventWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.store.JDBCConsoleApp;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * XmlFileGeneratorFromDB using for creation xml file from database data.
 * Created on 19.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class StAXXmlFileGeneratorFromDB {
    private final JDBCConsoleApp jdbcConsoleApp;
    private final Connection connection;
    private final String path;
    private static final Logger LOG = LoggerFactory.getLogger(StAXXmlFileGeneratorFromDB.class.getName());

    /**
     * Constructor StAXXmlFileGeneratorFromDB.
     * @param path path to the file.
     */

    public StAXXmlFileGeneratorFromDB(final String path) {
        this.path = path;
        jdbcConsoleApp = new JDBCConsoleApp();
        this.connection = jdbcConsoleApp.getDataBaseConnection();
    }

    /**
     * method to translate info from database to the xml file using StreamWriter.
     */

    public void translateDatabaseInfoToXmlFileWithStreams() {
        try {
            File pathToCreation = new File(this.path);
            ResultSet resultSet = this.getResultSetFromDB();
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = new IndentingXMLStreamWriter(xmlOutputFactory.createXMLStreamWriter(
                    new FileOutputStream(pathToCreation)));

            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("entries");

            while (resultSet.next()) {
                xmlStreamWriter.writeStartElement("entry");
                xmlStreamWriter.writeStartElement("field");
                String fieldValue = String.valueOf(resultSet.getInt("field"));
                xmlStreamWriter.writeCharacters(fieldValue);
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
            }

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.flush();
            xmlStreamWriter.close();

            jdbcConsoleApp.closeConnectToDB();

        } catch (IOException ioEx) {
            LOG.warn("Problem with IO", ioEx);
        } catch (XMLStreamException xmlStreamEx) {
            LOG.warn("Problem with creation new xml stream writer instance", xmlStreamEx);
        } catch (SQLException sqlEx) {
            LOG.warn("Problem with getting value from result set", sqlEx);
        }

    }

    /**
     * method to translate info from database to the xml file using EventWriter.
     */

    public void translateDatabaseInfoToXmlFileWithEvents() {
        File pathToCreation = new File(this.path);
        ResultSet resultSet = this.getResultSetFromDB();
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        XMLEventFactory xmlEventFactory = XMLEventFactory.newFactory();
        try {
            XMLEventFactory xmlEventInstance = XMLEventFactory.newInstance();
            XMLEvent nl = xmlEventInstance.createDTD("\n");

            XMLEventWriter xmlEventWriter = xmlOutputFactory.createXMLEventWriter(new FileOutputStream(pathToCreation));
            XMLEventWriter indentingXmlEventWriter = new IndentingXMLEventWriter(xmlEventWriter);
            indentingXmlEventWriter.add(xmlEventFactory.createStartDocument());
            indentingXmlEventWriter.add(xmlEventFactory.createStartElement("", "", "entries"));

            StartElement startElementEntry = xmlEventFactory.createStartElement("", "", "entry");
            EndElement endElementEntry = xmlEventFactory.createEndElement("", "", "entry");
            StartElement startElementField = xmlEventFactory.createStartElement("", "", "field");
            EndElement endElementField = xmlEventFactory.createEndElement("", "", "field");

            while (resultSet.next()) {
                indentingXmlEventWriter.add(startElementEntry);
                indentingXmlEventWriter.add(startElementField);
                String fieldValue = String.valueOf(resultSet.getInt("field"));
                indentingXmlEventWriter.add(xmlEventFactory.createCharacters(fieldValue));
                indentingXmlEventWriter.add(endElementField);
                indentingXmlEventWriter.add(endElementEntry);
            }
            xmlEventWriter.add(nl);
            indentingXmlEventWriter.add(xmlEventFactory.createEndDocument());
            indentingXmlEventWriter.flush();
            indentingXmlEventWriter.close();

            this.jdbcConsoleApp.closeConnectToDB();

        } catch (XMLStreamException xmlEventEx) {
            LOG.warn("Problem with creation xml event", xmlEventEx);
        } catch (FileNotFoundException fnfEx) {
            LOG.warn("File was not found", fnfEx);
        } catch (SQLException sqlEx) {
          LOG.warn("Problem with getting result from result set", sqlEx);
        }
    }

    /**
     * get result set from data base.
     * @return result set of all value from database.
     */

    private ResultSet getResultSetFromDB() {
        ResultSet resultSet = null;
        String sql = "SELECT field FROM test WHERE field IS NOT NULL";
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
