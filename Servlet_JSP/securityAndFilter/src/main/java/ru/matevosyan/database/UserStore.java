package ru.matevosyan.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.configuration.Settings;
import ru.matevosyan.model.User;
import ru.matevosyan.model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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
     * insert defult user and role.
     */
    static {
        createDB();
        try {
            createDBTables(DBConnection.getInstance().getDBConnection());
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection to create tables {}", sqlExp);
        }
        insertDefaultRole();
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
     * Insert user to database.
     * @param user to insert to database.
     */
    public void insert(final User user) {
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT.getGuery())) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setTimestamp(5, user.getCreateDate());
            ps.setInt(6, user.getRole().getId());
            ps.setInt(7, Integer.parseInt(user.getCountry()));
            ps.setInt(8, Integer.parseInt(user.getCity()));

            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection  invoke by create method {}", sqlExp);
        }
    }

    /**
     * Update user in the database.
     * @param name in the database.
     * @param login in the database.
     * @param newName to set.
     * @param newLogin to set.
     * @param newRole to set.
     */
    public void update(final String name, final String login, final String newName, final String newLogin, final String newRole) {
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.UPDATE.getGuery())) {
            ps.setString(1, newName);
            ps.setString(2, newLogin);
            ps.setString(3, newRole);
            ps.setString(4, name);
            ps.setString(5, login);
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by update method {}", sqlExp);
        }
    }

    /**
     * delete user from the database.
     * @param name in the database.
     * @param login in the database.
     */
    public void delete(final String name, final String login) {
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.DELETE.getGuery())) {
            ps.setString(1, name);
            ps.setString(2, login);
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by delete method {}", sqlExp);
        }
    }

    /**
     * get user info from the database to represent to the browser.
     * @return list with user value.
     */
    public CopyOnWriteArrayList<User> getResult() {
        CopyOnWriteArrayList<User> list = new CopyOnWriteArrayList<>();
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             Statement ps = connection.createStatement()) {
            ResultSet resultSet = ps.executeQuery(Queries.SELECT.getGuery());
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getTimestamp("createDate"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        new UserRole(resultSet.getInt(6), resultSet.getString(7)),
                        resultSet.getString(8),
                        resultSet.getString(9)));

            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by get method {}", sqlExp);
        }
        return list;
    }

    /**
     * get all roles info from the database to represent to the browser.
     * @return list with roles value.
     */
    public CopyOnWriteArrayList<UserRole> getAllRoles() {
        CopyOnWriteArrayList<UserRole> list = new CopyOnWriteArrayList<>();

        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.SELECT_ROLES.getGuery())) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new UserRole(resultSet.getInt("id"), resultSet.getString("name")));
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by get method {}", sqlExp);
        }
        return list;
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
            query = Queries.SELECT_ONE_USER.getGuery();
        } else {
            query = Queries.CHECK_LOGIN_OR_NAME_IS_IN_DB.getGuery();
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
             PreparedStatement ps = connection.prepareStatement(Queries.CHECK_EMAIL.getGuery())) {
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
                statement.execute(Queries.CREATE_DATABASE.getGuery());
                connection.close();
                LOG.info("Create database userstore");
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
                statement.execute(Queries.CREATE_TABLE_AND_INSERT_COUNTRY.getGuery());
                LOG.info("create country table");
                statement.execute(Queries.CREATE_TABLE_AND_INSERT_CITY.getGuery());
                LOG.info("Create table city");
                statement.execute(Queries.CREATE_TABLE_ROLE.getGuery());
                LOG.info("create roles table");
                statement.execute(Queries.CREATE_TABLE.getGuery());
                LOG.info("Create table users");

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
     * Insert default role to database.
     */
    private static void insertDefaultRole() {
        try {
            Connection connection = DBConnection.getInstance().getDBConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(Queries.INSERT_ROLE.getGuery());
            ps.setInt(1, 1);
            ps.setString(2, "admin");
            ps.setInt(3, 1);
            ps.execute();
            ps.setInt(1, 2);
            ps.setString(2, "user");
            ps.setInt(3, 2);
            ps.execute();
            connection.commit();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection  invoke by insertDefaultRole method {}", sqlExp);
        }
    }
    /**
     * Get role by id.
     * @param name role name.
     * @return int values wich is roles id.
     */
    public int getRoleIdByName(String name) {
        int roleId = 0;
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.GET_ROLE_ID.getGuery())) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                roleId = resultSet.getInt("id");
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection  invoke by create method {}", sqlExp);
        }
        return roleId;
    }

    /**
     * Insert default user to database.
     */
    private static void insertDefUser() {
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT_ROOT.getGuery())) {
            ps.setString(1, "root");
            ps.setString(2, "root");
            ps.setString(3, "root@root");
            ps.setString(4, "root");
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.setInt(6, 1);
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
             PreparedStatement ps = connection.prepareStatement(Queries.GET_CITIES.getGuery())) {
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
             PreparedStatement ps = connection.prepareStatement(Queries.GET_COUNTIES.getGuery())) {
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
}
