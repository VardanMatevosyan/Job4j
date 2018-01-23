package ru.matevosyan.jaxb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.entity.toXML.Entries;
import ru.matevosyan.entity.toXML.Entry;
import ru.matevosyan.store.JDBCConsoleApp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * XmlParsingToXML using for creation xml file from database data.
 * Created on 21.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0
 */

public class XmlParsingToXML {
    private ArrayList<Entry> listOfFiled = new ArrayList<>();
    private final Connection connection;
    private final JDBCConsoleApp jdbcConsoleApp;
    private final String pathToTheFIle;
    private final ResultSet resultSet;
    private static final Logger LOG = LoggerFactory.getLogger(XmlParsingToXML.class.getName());

    /**
     * XmlParsingToXML constructor.
     * @param pathToTheFIle path to the xml file.
     */

    public XmlParsingToXML(String pathToTheFIle) {
        this.pathToTheFIle = pathToTheFIle;
        this.jdbcConsoleApp = new JDBCConsoleApp();
        this.connection = this.jdbcConsoleApp.getDataBaseConnection();
        this.resultSet = this.getResultSetFromDB();
    }

    /**
     * Convert to XML file from database.
     */

    public void convertToXML() {
        try {
            File file  = new File(this.pathToTheFIle);

            Entries entries = new Entries();
            this.fillListOfField();
            entries.setEntry(this.listOfFiled);

            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(entries, file);
        } catch (JAXBException jaxbEx) {
            LOG.warn("JAXB exception - problem with getting new jaxb instance", jaxbEx);
        }

        jdbcConsoleApp.closeConnectToDB();
        LOG.debug("Close connection to the database", this.connection);
    }

    /**
     * fill list of fields.
     */

    private void fillListOfField() {

        String columnName;
        try {
            columnName = this.resultSet.getMetaData().getColumnName(1);

            while (this.resultSet.next()) {
                Entry entry = new Entry();
                int value = this.resultSet.getInt(columnName);
                entry.setValue(value);
                this.listOfFiled.add(entry);

            }

        } catch (SQLException sqlEx) {
            LOG.warn("SQL exception - problem with getting value from result set", sqlEx);
        }
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
