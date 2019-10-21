package ru.matevosyan.application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.confingForJunit.Settings;
import ru.matevosyan.database.conection.ConnectionDB;
import ru.matevosyan.model.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Class FunctionalTrackerTest created for testing all functionality in the Tracker class.
 * Created on 18.01.2018
 * @author Matevosyan Vardan.
 * @version 1.0 with database.
 */

public class FunctionalTrackerTest {

    private Tracker tracker;
    private static final Logger LOG = LoggerFactory.getLogger(FunctionalTrackerTest.class.getName());
    private static final Settings SETTINGS = Settings.getSettingInstance();

    /**
     * Initialization tracker before ech test because of connection closed.
     */

    @Before
    public void init() {
        tracker = new Tracker();
    }

    /**
     * After exh test case delete all created table and after closed connection to database.
     */

    @After
    public void deleteTablesAndCloseConnection() {
        try {
            this.deleteAllTables();
            tracker.getConnection().close();
            LOG.debug("Connection closed");
        } catch (SQLException sqlE) {
            LOG.warn("Problem with closing connection", sqlE);
        }

    }

    /**
     * Testing {@link Tracker#add(Item)} method (item adding functionality.
     */

    @Test
    public void whenAddItemThanCheckItFromTheDatabase() {
        String actualItemName = "";
        String actualItemDescription = "";
        String expectedItemName = "TestItem";
        String expectedItemDescription = "TestItemDesc";

        tracker.add(new Item(expectedItemName, expectedItemDescription));

        String addedItem = SETTINGS.getValue("sql.getItemsFromDB");
        String sqlScript = getSqlScript(addedItem);

        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(sqlScript)) {
            preparedStatement.setString(1, expectedItemName);
            ResultSet resultSetOfQuery = preparedStatement.executeQuery();

            while (resultSetOfQuery.next()) {
                actualItemName = resultSetOfQuery.getString("name");
                actualItemDescription = resultSetOfQuery.getString("description");
            }

            assertThat(actualItemName, is(expectedItemName));
            assertThat(actualItemDescription, is(expectedItemDescription));

            if (actualItemName.equals(expectedItemName) && actualItemDescription.equals(expectedItemDescription)) {
                LOG.info("Test is passed");
            }

        } catch (SQLException sqlEx) {
            LOG.warn("Problem with getting connection or execute sql script", sqlEx);
        }
    }

    /**
     * Testing the {@link Tracker#deleteItem(String)} method delete item through defining item id..
     */
    @Test
    public void whenAddTwoItemsAndDeleteOneItemThanCheckCountInDatabaseShouldBeOne() {
        int count = 0;
        int expectedCount = 1;
        String itemId = "";
        String expectedItemName = "TestItem";
        String expectedItemDescription = "TestItemDesc";

        tracker.add(new Item(expectedItemName, expectedItemDescription));
        tracker.add(new Item(expectedItemName + 2, expectedItemDescription + 2));

        String addedItem = SETTINGS.getValue("sql.getItemsFromDB");
        String countRowsInDB = SETTINGS.getValue("sql.countRowsInDB");

        String sqlScript = getSqlScript(addedItem);
        String sqlScriptToCount = getSqlScript(countRowsInDB);

        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(sqlScript)) {
            preparedStatement.setString(1, expectedItemName);
            ResultSet resultSetOfQuery = preparedStatement.executeQuery();

            while (resultSetOfQuery.next()) {
                itemId = String.valueOf(resultSetOfQuery.getInt("pk_id"));
            }

            tracker.deleteItem(itemId);

            try (Statement statement = ConnectionDB.getConnection().createStatement()) {
                ResultSet resultSetOfQueryToCount = statement.executeQuery(sqlScriptToCount);

                while (resultSetOfQueryToCount.next()) {
                    count = resultSetOfQueryToCount.getInt("count");
                }
            } catch (SQLException sqlEx) {
                LOG.warn("Problem with getting connection or execute sql script", sqlEx);
            }
            assertThat(count, is(expectedCount));

            if (count == expectedCount) {
                LOG.info("Test is passed");
            }

        } catch (SQLException sqlEx) {
            LOG.warn("Problem with getting connection or execute sql script", sqlEx);
        }
    }

    /**
     * Testing {@link Tracker#editItem(Item)} method edit item to another new item.
     */

    @Test
    public void whenAddItemWithNotEditItemAndEditItemNameToEditItemThanGetItemWithEditItemName() {
        String itemId = "";
        String actualItemName = "";
        String actualItemDescription = "";
        String expectedItemName = "EditItem";
        String expectedItemDescription = "EditItemDesc";
        String itemName = "NotEditItem";
        String itemDescription = "NotEditItemDesc";

        Item item = new Item(itemName, itemDescription);
        tracker.add(item);
        String expectedItemId = item.getId();

        String addedItem = SETTINGS.getValue("sql.getItemsFromDB");
        String sqlScript = getSqlScript(addedItem);

        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(sqlScript)) {
            preparedStatement.setString(1, itemName);
            ResultSet resultSetOfQuery = preparedStatement.executeQuery();


            while (resultSetOfQuery.next()) {
                itemId = String.valueOf(resultSetOfQuery.getInt("pk_id"));
            }

            Item editItem = new Item(expectedItemName, expectedItemDescription);
            editItem.setId(itemId);
            tracker.editItem(editItem);

            try (PreparedStatement preparedStatementForEditItem = ConnectionDB.getConnection().prepareStatement(sqlScript)) {
                preparedStatementForEditItem.setString(1, expectedItemName);
                ResultSet resultSetOfQueryForEditItem = preparedStatementForEditItem.executeQuery();

                while (resultSetOfQueryForEditItem.next()) {
                    actualItemName = resultSetOfQueryForEditItem.getString("name");
                    actualItemDescription = resultSetOfQueryForEditItem.getString("description");
                }
            } catch (SQLException sqlEx) {
                LOG.warn("Problem with getting connection or execute sql script", sqlEx);
            }

        } catch (SQLException sqlEx) {
            LOG.warn("Problem with getting connection or execute sql script", sqlEx);
        }

        assertThat(actualItemName, is(expectedItemName));
        assertThat(actualItemDescription, is(expectedItemDescription));
        assertThat(itemId, is(expectedItemId));

        if (actualItemName.equals(expectedItemName) && actualItemDescription.equals(expectedItemDescription)) {
            LOG.info("Test is passed");
        }

    }

    /**
     * Testing {@link Tracker#addComment(Item, String)} method add comment to the item through defining item id.
     */

    @Test
    public void whenAddOneCommentToOneItemToTheDatabaseThanCheckCommentNameShouldBeFirstItemComment() {
        String actualItemId = "";
        String actualItemComment = "";
        String expectedItemComment = "FirstItemComment";
        String itemName = "NotEditItem";
        String itemDescription = "NotEditItemDesc";

        Item item = new Item(itemName, itemDescription);
        tracker.add(item);
        String expectedItemId = item.getId();
        tracker.addComment(item, expectedItemComment);

        String addedItem = SETTINGS.getValue("sql.getItemsCommentsFromDB");
        String sqlScript = getSqlScript(addedItem);

        try (PreparedStatement preparedStatementForEditItem = ConnectionDB.getConnection().prepareStatement(sqlScript)) {
            preparedStatementForEditItem.setString(1, expectedItemComment);
            ResultSet resultSetOfQueryForEditItem = preparedStatementForEditItem.executeQuery();

            while (resultSetOfQueryForEditItem.next()) {
                actualItemComment = resultSetOfQueryForEditItem.getString("comment");
                actualItemId = String.valueOf(resultSetOfQueryForEditItem.getInt("pk_id"));
            }

            assertThat(actualItemComment, is(expectedItemComment));
            assertThat(actualItemId, is(expectedItemId));
        } catch (SQLException sqlEx) {
            LOG.warn("Problem with getting connection or execute sql script", sqlEx);
        }

    }

    /**
     * Testing {@link Tracker#findById(String)} method that find items by items id.
     */

    @Test
    public void whenAddItemsAndFindByItemIdThanGetTheSameItemFromDatabase() {
        String expectedFirstItemName = "FirstItem";
        String expectedFirstDescription = "FirstItemDesc";

        Item firstItem = new Item(expectedFirstItemName, expectedFirstDescription);
        tracker.add(firstItem);

        String getItemId = firstItem.getId();
        Item itemGetFromDatabaseById = tracker.findById(getItemId);

        assertThat(itemGetFromDatabaseById.getName(), is(firstItem.getName()));
        assertThat(itemGetFromDatabaseById.getDescription(), is(firstItem.getDescription()));
        assertThat(itemGetFromDatabaseById.getId(), is(firstItem.getId()));
        assertThat(itemGetFromDatabaseById.getCreate(), is(firstItem.getCreate()));
    }

    /**
     * Testing {@link Tracker#findByName(String)} method that find items by items name.
     */

    @Test
    public void whenAddTwoItemsAndFindByItemNameThanGetTheSameItemFromDatabase() {
        int expectedSize = 2;
        String expectedItemName = "FirstItem";
        String expectedFirstDescription = "FirstItemDesc";
        String expectedSecondDescription = "SecondItemDesc";

        Item firstItem = new Item(expectedItemName, expectedFirstDescription);
        Item secondItem = new Item(expectedItemName, expectedSecondDescription);
        tracker.add(firstItem);
        tracker.add(secondItem);

        List<Item> itemGetFromDatabaseById = tracker.findByName(expectedItemName);

        assertThat(itemGetFromDatabaseById.size(), is(expectedSize));
        assertThat(itemGetFromDatabaseById.get(0).getName(), is(expectedItemName));
        assertThat(itemGetFromDatabaseById.get(1).getName(), is(expectedItemName));


        assertThat(itemGetFromDatabaseById.get(0).getDescription(), is(firstItem.getDescription()));
        assertThat(itemGetFromDatabaseById.get(0).getId(), is(firstItem.getId()));
        assertThat(itemGetFromDatabaseById.get(0).getCreate(), is(firstItem.getCreate()));
        assertThat(itemGetFromDatabaseById.get(1).getDescription(), is(secondItem.getDescription()));
        assertThat(itemGetFromDatabaseById.get(1).getId(), is(secondItem.getId()));
        assertThat(itemGetFromDatabaseById.get(1).getCreate(), is(secondItem.getCreate()));
    }

    /**
     * Testing {@link Tracker#findByDate(String)} method that find items by items date.
     */

    @Test
    public void whenAddTwoItemsAndFindByDateThanGetTwoItemFromDatabase() {
        int expectedSize = 2;
        String expectedFirstItemName = "FirstItem";
        String expectedSecondItemName = "SecondItem";
        String expectedFirstDescription = "FirstItemDesc";
        String expectedSecondDescription = "SecondItemDesc";
        Timestamp time = new Timestamp(new Date().getTime());

        Item firstItem = new Item(expectedFirstItemName, expectedFirstDescription);
        Item secondItem = new Item(expectedSecondItemName, expectedSecondDescription);

        tracker.add(firstItem);
        tracker.add(secondItem);

        List<Item> itemGetFromDatabaseById = tracker.findByDate(time.toString());

        assertThat(itemGetFromDatabaseById.size(), is(expectedSize));

        assertThat(itemGetFromDatabaseById.get(0).getDescription(), is(firstItem.getDescription()));
        assertThat(itemGetFromDatabaseById.get(0).getId(), is(firstItem.getId()));
        assertThat(itemGetFromDatabaseById.get(0).getCreate(), is(firstItem.getCreate()));

        assertThat(itemGetFromDatabaseById.get(1).getDescription(), is(secondItem.getDescription()));
        assertThat(itemGetFromDatabaseById.get(1).getId(), is(secondItem.getId()));
        assertThat(itemGetFromDatabaseById.get(1).getCreate(), is(secondItem.getCreate()));
    }


    /**
     * Get sql script from the resource folder file.
     * @param value name of the sql file.
     * @return script as a String.
     */

    private static String getSqlScript(String value) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            ClassLoader classLoader = Settings.class.getClassLoader();
            URL urlToSqlFile = classLoader.getResource(value);

            assert urlToSqlFile != null;
            File path = Paths.get(urlToSqlFile.toURI()).toFile();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

            while (reader.ready()) {
                stringBuilder.append(reader.readLine());
            }

            LOG.debug("sql script is ready to return");
        } catch (IOException ioe) {
            LOG.warn("Can't read the file", ioe);
        } catch (URISyntaxException uri) {
            LOG.warn("Can't convert URL to URI", uri);
        }

        return stringBuilder.toString();
    }

    /**
     * delete all tables.
     */

    private void deleteAllTables() {
        String sqlFileNameForDeletingTheTables = SETTINGS.getValue("sql.deleteAllTables");
        String sqlScript = getSqlScript(sqlFileNameForDeletingTheTables);

        try (Statement statement = ConnectionDB.getConnection().createStatement()) {
            statement.execute(sqlScript);

            LOG.info("Tables in database was deleted");
        } catch (SQLException sqlEx) {
            LOG.warn("Problem with getting connection or execute sql script", sqlEx);
        }
    }

}