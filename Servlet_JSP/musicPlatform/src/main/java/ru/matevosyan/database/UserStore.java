package ru.matevosyan.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class is for manipulate with data in the database.
 * Created on 11.03.2018.
 * @author Matevosyan Vardan.
 * @version 1.0
 */
public enum UserStore {
    STORE;
    private static final Settings SETTINGS = Settings.getSettingInstance();
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class.getName());

    /**
     * Static block.
     * create database.
     * create all tables.
     * insert default user and role.
     */
    static {
        createDB();
        try {
            createDBTables(DBConnection.getInstance().getDBConnection());
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection to create tables {}", sqlExp);
        }
        insertDefUser();
    }
    /**
     * UserStore constructor.
     */
     UserStore() {

     }

    /**
     * Get an instance of UserStore class.
     * @return an instance of UserStore.
     */
    public static UserStore getInstance() {
        return STORE;
    }

    /**
     * if user values already exist in the system to avoid insertion values or update not exist values.
     * For user sign in use -> SELECT_ONE_USER query name that check user login and password to sign in.
     * For check user name and login use other values.
     * @param queryName user name in the database.
     * @param values array of parameters to check.
     * @return false if user values already exist in the system, else true.
     */

    public boolean checkUserValuesInTheSystem(String queryName, String ... values) {
        boolean exist = false;
        String query;
        if (queryName.equals("SELECT_ONE_USER")) {
            query = Queries.SELECT_ONE_USER.getQuery();
        } else {
            query = Queries.CHECK_LOGIN_OR_NAME_IS_IN_DB.getQuery();
        }
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ResultSet resultSet = ps.executeQuery();
            exist = resultSet.next();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by get method {}", sqlExp);
        }
        return exist;
    }

    /**
     * Check if passing user values is already exist in the system.
     * @param login user login.
     * @param name user name.
     * @param email email name.
     * @return true if values already exist, else false.
     */
    public boolean validateAllFieldsForUserCreation(String login, String name, String email) {
        boolean areValid = false;
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.CHECK_EMAIL.getQuery())) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
               areValid = true;
            }
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by validateAllFieldForUserCreation {}", sqlExp);
        }
        return areValid && !checkUserValuesInTheSystem("CHECK_LOGIN_OR_NAME_IS_IN_DB", login, name);
    }

    /**
     * createDB method used to create database.
     */
    private static void createDB() {
        Connection connection;
        LOG.debug("Get connection to create db");
            try {
                Class.forName(SETTINGS.getValue("jdbc.driver_class"));
                connection = DriverManager.getConnection(SETTINGS.getValue("jdbc.defaultUrl"),
                        SETTINGS.getValue("jdbc.username"), SETTINGS.getValue("jdbc.password"));
                Statement statement = connection.createStatement();
                statement.execute(Queries.CREATE_DATABASE.getQuery());
                connection.close();
                LOG.info("Create database musicPlatform");
            } catch (SQLException sqlEx) {
                LOG.warn("Problem with execution query", sqlEx);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }


    /**
     * CreateDBTable method used to create table in database when table is not exist.
     * @param connectionDB connection to database.
     */
    private static void createDBTables(final Connection connectionDB) {
        if (DBConnection.getInstance().isConnected(connectionDB)) {
            try (final Statement statement = connectionDB.createStatement()) {
                statement.execute(Queries.CREATE_TABLE_AND_INSERT_COUNTRY.getQuery());
                LOG.info("create country table");
                statement.execute(Queries.CREATE_TABLE_AND_INSERT_CITY.getQuery());
                LOG.info("Create table city");
                statement.execute(Queries.CREATE_TABLE_AND_INSERT_ROLE.getQuery());
                LOG.info("create roles table");
                statement.execute(Queries.CREATE_TABLE_AND_INSERT_MUSICTYPE.getQuery());
                LOG.info("create music type table");
                statement.execute(Queries.CREATE_TABLE_ADDRESS.getQuery());
                LOG.info("create address table");
                statement.execute(Queries.CREATE_TABLE.getQuery());
                LOG.info("Create table users");
                statement.execute(Queries.CREATE_TABLE_MUSIC_PREPARE.getQuery());
                LOG.info("Create table musicPrepare");

            } catch (SQLException sqlExp) {
                LOG.warn(sqlExp.getMessage(), sqlExp);
            }
            try {
                connectionDB.close();
            } catch (SQLException sqlExp) {
                LOG.warn("Can't close connection ", sqlExp);
            }
        } else {
            LOG.warn("can't create table, because of connection problem");
        }
    }

    /**
     * Insert default user to database.
     */
    private static void insertDefUser() {
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT_ROOT.getQuery())) {
            ps.setString(1, "rootCountry");
            ps.setString(2, "rootCity");
            ps.setString(3, "rootStreet");
            ps.setInt(4, 1);
            ps.setString(5, "root");
            ps.setString(6, "root");
            ps.setString(7, "root");
            ps.setString(8, "root@root");
            ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            ps.setInt(10, 3);
            ps.setInt(11, 1);
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection  invoke by insertDefUser method {}", sqlExp);
        }
    }

    /**
     * Get cities from the database for passing country values.
     * @param country values to get cities.
     * @return concurrentHashMap of cities.
     */
    public ConcurrentHashMap<String, String> getCities(Integer country) {
        ConcurrentHashMap<String, String> cities = new ConcurrentHashMap<>();
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.GET_CITIES.getQuery())) {
            ps.setInt(1, country);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                cities.put(String.valueOf(resultSet.getInt("city_id_pk")), resultSet.getString("name"));
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection  invoke by getCity() method {}", sqlExp);
        }
        return cities;
    }
    /**
     * Get all countries from the database.
     * @return concurrentHashMap of countries.
     */
    public ConcurrentHashMap<String, String> getCounties() {
        ConcurrentHashMap<String, String> countries = new ConcurrentHashMap<>();
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.GET_COUNTIES.getQuery())) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                countries.put(String.valueOf(resultSet.getInt("country_id_pk")),
                        resultSet.getString("name"));
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection  invoke by getCity() method {}", sqlExp);
        }
        return countries;
    }

    /**
     * Get user id from database.
     * @param login user.
     * @param password user.
     * @return user id.
     */
    public int getUserId(final String login, final String password) {
        int id = -1;
        try (PreparedStatement psForGettingUserId = DBConnection.INSTANCE.getDBConnection()
                .prepareStatement(Queries.GET_USER_ID.getQuery())) {
            psForGettingUserId.setString(1, login);
            psForGettingUserId.setString(2, password);
            ResultSet resultSet = psForGettingUserId.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                System.out.println("get User Id = for MusicType" + id);
            }
        } catch (SQLException sqlExp) {
            LOG.error("You get this msg because of something goes wrong with connection or query {}", sqlExp);
        }
        return id;
    }

    /**
     * Get address id from database.
     * @param city user.
     * @param street user.
     * @param number user.
     * @return user id.
     */
    public int getAddressId(final String city, final String street, final int number) {
        int id = -1;
        try (PreparedStatement psForGettingAddressId = DBConnection.INSTANCE.getDBConnection()
                .prepareStatement(Queries.GET_ADDRESS_ID.getQuery())) {
            psForGettingAddressId.setString(1, city);
            psForGettingAddressId.setString(2, street);
            psForGettingAddressId.setInt(3, number);
            ResultSet resultSet = psForGettingAddressId.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException sqlExp) {
            LOG.error("You get this msg because of something goes wrong with connection or query {}", sqlExp);
        }
        return id;
    }

}
