package ru.matevosyan.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.conection.ConnectionDB;
import ru.matevosyan.model.Comments;
import ru.matevosyan.model.Item;
import ru.matevosyan.projectSettings.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class Tracker created for saving all items that have been created.
 * Created on 15.01.2018
 * @since 1.0
 * @author Matevosyan Vardan.
 * @version 2.0 with database.
 */

public class Tracker {

    private ConnectionDB connectionDB = new ConnectionDB();
    private final Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class.getName());
    private static final Settings SETTINGS = Settings.getSettingInstance();

    private static final String INSERT_ITEM = "INSERT INTO items (name, description, created_date) VALUES (?, ?, ?)";
    private static final String DELETE_ITEM = "DELETE FROM items WHERE pk_id = ?";
    private static final String UPDATE_ITEM = "UPDATE items SET name = ?, description = ?, created_date = ?"
            + "WHERE pk_id = ?";
    private static final String FIND_ITEM_BY_ID = "SELECT * FROM items WHERE pk_id = ?";
    private static final String FIND_ITEM_BY_NAME = "SELECT * FROM items WHERE name = ?";
    private static final String FIND_ITEM_BY_DATE = "SELECT * FROM items WHERE created_date::date = ?::date";
    private static final String GET_ALL_FROM_ITEMS = "SELECT * FROM items";
    private static final String INSERT_COMMENT_TO_ITEM = "INSERT INTO comments (comment, fk_id_Item) VALUES (?, ?)";
    private static final String FIND_ITEM_COMMENTS_BY_ID = "SELECT comment FROM Comments WHERE fk_id_item = ?";

    /**
     * Constructor for {@link Tracker}.
     */

    public Tracker() {
        connectionDB.connectToDB();
        connection = ConnectionDB.getConnection();
        this.createTablesInDBTracker();
    }

    /**
     * Getter for connection.
     * @return connection to database.
     */

    public Connection getConnection() {
        return connection;
    }

    /**
     * Method add new item to the database.
     * it's may the specific type of item, for example: new Task or new Bug.
     * @param item which is the new item.
     * @return item that added to the items array
     */

    public Item add(Item item) {
        Connection connection = ConnectionDB.getConnection();
        ResultSet resultSetGenKeys = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            Timestamp timestamp = new Timestamp(new Date().getTime());
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.executeUpdate();

            resultSetGenKeys = preparedStatement.getGeneratedKeys();

            while (resultSetGenKeys.next()) {
                item.setId(String.valueOf(resultSetGenKeys.getInt(1)));
                item.setCreate(resultSetGenKeys.getDate("created_date"));
            }

            LOG.debug("Add item to database {}.", item);
            } catch (SQLException sql) {
                LOG.warn("Can't add item to database. Problem with getting connection", sql);
            }
        return item;
    }

    /**
     * Method deleteItem created for deleted exist item from array of items.
     * it's may remove the specific type of item: Task or Bug.
     * @param id it is for determine which of this specific item must be deleted.
     */

    public void deleteItem(String id) {
        Connection connection = ConnectionDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM)) {
                preparedStatement.setInt(1, Integer.parseInt(id));
                preparedStatement.executeUpdate();
                LOG.debug("Deleted item from database with item id {}.", id);
            } catch (SQLException sql) {
                LOG.warn("Can't delete item from database. Problem with getting connection", sql);
            }
    }

    /**
     * Method editItem update item from database.
     * it's may edit the specific type of item: Task or Bug.
     * @param fresh it is specific item that you want to edit.
     */

    public void editItem(Item fresh) {
        Connection connection = ConnectionDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM)) {
                preparedStatement.setString(1, fresh.getName());
                preparedStatement.setString(2, fresh.getDescription());

                Timestamp timestamp = new Timestamp(new Date().getTime());
                preparedStatement.setTimestamp(3, timestamp);
                preparedStatement.setInt(4, Integer.parseInt(fresh.getId()));
                preparedStatement.executeUpdate();

                LOG.debug("Update item in database with item {}.", fresh);
            } catch (SQLException sql) {
                LOG.warn("Can't delete item from database. Problem with getting connection", sql);
            }
    }

    /**
     * Method findById created for finding exist item from database passing through method item's id.
     * @param id it is specific item's id that item's would you like to find out.
     * @return resultFindById it's concrete item that you find out
     */

    public Item findById(String id) {
        Item resultFindById = null;
        Connection connection = ConnectionDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ITEM_BY_ID)) {
                preparedStatement.setInt(1, Integer.parseInt(id));
                ResultSet resultQuery = preparedStatement.executeQuery();

                while (resultQuery.next()) {
                    String itemName = resultQuery.getString("name");
                    String itemDescription = resultQuery.getString("description");
                    Date createdDate = resultQuery.getDate("created_date");
                    String itemId = String.valueOf(resultQuery.getInt("pk_id"));

                    resultFindById = new Item(itemName, itemDescription);
                    resultFindById.setId(itemId);
                    resultFindById.setCreate(createdDate);
                }

                LOG.debug("Find item by id in the Items database with item {}.", resultFindById);
            } catch (SQLException sql) {
                LOG.warn("Can't find item by id from database. Problem with getting connection", sql);
            }

        return resultFindById;
    }

    /**
     * Method findByName created for finding exist item from database passing through method item's name.
     * @param name it is specific item's id that item's would you like to find out.
     * @return resultFindByName it's concrete item that you find out
     */

    public List<Item> findByName(String name) {
        Item resultFindByName  = null;
        List<Item> items = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ITEM_BY_NAME)) {
                preparedStatement.setString(1, name);
                ResultSet resultQuery = preparedStatement.executeQuery();

                while (resultQuery.next()) {
                    String itemName = resultQuery.getString("name");
                    String itemDescription = resultQuery.getString("description");
                    long createdDate = resultQuery.getTimestamp("created_date").getTime();
                    String itemId = String.valueOf(resultQuery.getInt("pk_id"));

                    resultFindByName = new Item(itemName, itemDescription);
                    resultFindByName.setCreate(new Date(createdDate));
                    resultFindByName.setId(itemId);

                    items.add(resultFindByName);
                }

                LOG.debug("Find item by name in the Items database with item {}.", resultFindByName);
            } catch (SQLException sql) {
                LOG.warn("Can't find item by name from database. Problem with getting connection", sql);
            }
        return items;
    }

    /**
     * Method findByyDate created for find exist item from database passing through method item's created date.
     * @param create it is concrete item's created date that item's would you like to find out.
     * @return resultFindByyDate it's concrete item that you find out
     */

    public List<Item> findByDate(String create) {
        List<Item> items = new ArrayList<>();
        Item resultFindByDate = null;
        Connection connection = ConnectionDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ITEM_BY_DATE)) {
                String formatForDate = "yyyy-MM-dd";
                SimpleDateFormat dateFormat = new SimpleDateFormat(formatForDate);
                Date createdItemDate = dateFormat.parse(create);
                preparedStatement.setTimestamp(1, new Timestamp(createdItemDate.getTime()));

                ResultSet resultQuery = preparedStatement.executeQuery();

                while (resultQuery.next()) {
                    String itemName = resultQuery.getString("name");
                    String itemDescription = resultQuery.getString("description");
                    String createdDate = resultQuery.getTimestamp("created_date").toString();
                    String itemId = String.valueOf(resultQuery.getInt("pk_id"));

                    resultFindByDate = new Item(itemName, itemDescription);
                    resultFindByDate.setId(itemId);

                    SimpleDateFormat dateFormatOut = new SimpleDateFormat(formatForDate);
                    Date createdItemDateOut = dateFormatOut.parse(createdDate);
                    resultFindByDate.setCreate(createdItemDateOut);

                    items.add(resultFindByDate);
                }
                LOG.debug("Find item by date in the Items database with item {}.", resultFindByDate);

            } catch (SQLException sql) {
                LOG.warn("Can't find item by date from database. Problem with getting connection", sql);
            } catch (ParseException pe) {
                LOG.warn("Problem with formatting create date", pe);
            }
        return items;
    }

    /**
     * Method getAll created for getting all of exist item from items table in the database Tracker.
     * @return result array with all items.
     */

    public ArrayList<Item> getAll() {
        ArrayList<Item> result = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_FROM_ITEMS)) {
                ResultSet resultQuery = preparedStatement.executeQuery();
                result = this.fillArrayItemsFromDatabase(result, resultQuery);
                LOG.debug("Find all items from the items table in database.");
            } catch (SQLException sql) {
                LOG.warn("Can't find items table from database. Problem with getting connection", sql);
            }
        return result;
    }

    /**
     * fill an array list of items from the items table in the Tracker database.
     * @param list list to fill all items.
     * @param resultQuery  to extract all data from the database.
     * @return an array list of items.
     */

    private ArrayList<Item> fillArrayItemsFromDatabase(ArrayList<Item> list, ResultSet resultQuery) {
        try {
            while (resultQuery.next()) {
                String itemName = resultQuery.getString("name");
                String itemDescription = resultQuery.getString("description");
                Date createdDate = resultQuery.getDate("created_date");
                String itemId = String.valueOf(resultQuery.getInt("pk_id"));

                Item item = new Item(itemName, itemDescription);
                item.setCreate(createdDate);
                item.setId(itemId);

                list.add(item);
            }
            LOG.debug("Fill all items from the items table in database to an array list.");
        } catch (SQLException sql) {
            LOG.warn("Can't fill items array from database. Problem with getting connection", sql);
        }
        return list;
    }

    /**
     * Method addComment using for adding comment to the item.
     * @param item it is item that you want  to comment
     * @param comment it is comment that you passing to the method to add to your item
     */

    public void addComment(Item item, String comment) {
        Connection connection = ConnectionDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT_TO_ITEM)) {
                preparedStatement.setString(1, comment);
                preparedStatement.setInt(2, Integer.parseInt(item.getId()));
                preparedStatement.executeUpdate();
                LOG.debug("Add comment to item in the database Tracker {}.", comment);
            } catch (SQLException sql) {
                LOG.warn("Can't add comment to comments table in the database Tracker. Problem with getting connection", sql);
            }
    }

    /**
     * create table in database Tracker.
     */

    private void createTablesInDBTracker() {
        Connection connection = ConnectionDB.getConnection();
            try (
                 Statement statement = connection.createStatement()) {
                String createItem = SETTINGS.getValue("sql.createTableItem");
                String createComments = SETTINGS.getValue("sql.createTableComments");

                String sqlCreateItemsTable = this.getSqlScript(createItem);
                String sqlCreateCommentsTable = this.getSqlScript(createComments);

                statement.execute(sqlCreateItemsTable);
                LOG.debug("Table Items was created in database Tracker");

                statement.execute(sqlCreateCommentsTable);
                LOG.debug("Table Comments was created in database Tracker");
            } catch (SQLException sql) {
                LOG.warn("Can't create tables. Problem with connection", sql);
            }
    }

    /**
     * Get sql script from the resource folder file.
     * @param value name of the sql file.
     * @return script as a String.
     */

    private String getSqlScript(String value) {
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
     * Find all comments belong to the item in the comments table.
     * @param id item id.
     * @return list of items comment.
     */

    public List<Comments> findInCommentsByItemId(String id) {
        ArrayList<Comments> itemComments = new ArrayList<>();
        ResultSet resultSetQuery = null;
        try (PreparedStatement preparedStatement = ConnectionDB.getConnection().prepareStatement(FIND_ITEM_COMMENTS_BY_ID)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            resultSetQuery = preparedStatement.executeQuery();

            while (resultSetQuery.next()) {
                String itemComment = resultSetQuery.getString("comment");
                Comments comment = new Comments(itemComment);
                itemComments.add(comment);
            }

        } catch (SQLException sql) {
            LOG.warn("Can't get comments.", sql);
        }
        return itemComments;
    }
}
