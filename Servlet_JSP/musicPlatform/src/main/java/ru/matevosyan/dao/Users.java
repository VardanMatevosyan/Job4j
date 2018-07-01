package ru.matevosyan.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.DBConnection;
import ru.matevosyan.database.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.matevosyan.database.UserStore;
import ru.matevosyan.entity.Address;
import ru.matevosyan.entity.MusicType;
import ru.matevosyan.entity.User;
import ru.matevosyan.entity.UserRole;

/**
 * Users DAO class.
 */
public class Users implements IGeneric<User, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(Users.class.getName());

    /**
     * get user info from the database to represent to the browser.
     * @return list with user value.
     * @throws DaoException throw if problem with connection or getting user info.
     */
    @Override
    public CopyOnWriteArrayList<User> getAll() throws DaoException {
        CopyOnWriteArrayList<User> list = new CopyOnWriteArrayList<>();
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             Statement ps = connection.createStatement();
             PreparedStatement psForUsersMusicPrepare = connection
                     .prepareStatement(Queries.SELECT_USER_MUSIC_PREPARE.getQuery())) {
            ResultSet resultSet = ps.executeQuery(Queries.SELECT.getQuery());

            while (resultSet.next()) {
                UserRole role = new UserRole(resultSet.getInt(7), resultSet.getString(8));
                int userId = resultSet.getInt("id");
                Address address = new Address();
                address.setId(resultSet.getInt(9));
                address.setCountry(resultSet.getString(10));
                address.setCity(resultSet.getString(11));
                address.setStreet(resultSet.getString(12));
                address.setNumber(resultSet.getInt(13));

                List<MusicType> listOfMusicType = new ArrayList<>();
                psForUsersMusicPrepare.setInt(1, userId);
                ResultSet resultSetForMusicPrepare = psForUsersMusicPrepare.executeQuery();

                while (resultSetForMusicPrepare.next()) {
                    MusicType musicType = new MusicType();
                    String musicTypeName = resultSetForMusicPrepare.getString("name");
                    musicType.setName(musicTypeName);
                    listOfMusicType.add(musicType);
                }

                if (resultSetForMusicPrepare.wasNull()) {
                    MusicType musicType = new MusicType();
                    musicType.setName("");
                    listOfMusicType.add(musicType);
                }

                list.add(new User(userId,
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getTimestamp("createDate"),
                        resultSet.getString("email"),
                        role, address, listOfMusicType));
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by get method {}", sqlExp);
            throw new DaoException("Problem with getting All user");
        }
        return list;
    }

    /**
     * Get user by id.
     * @param id user id.
     * @return user from database by id.
     * @throws DaoException throw if problem with connection or getting user by id.
     */
    @Override
    public User getById(Integer id) throws DaoException {
        User user;
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement psForUsersMusicPrepare = connection
                     .prepareStatement(Queries.SELECT_USER_MUSIC_PREPARE.getQuery());
            PreparedStatement statement = connection.prepareStatement(Queries.GET_USER_BY_ID.getQuery())) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.getResultSet();
            int userId = resultSet.getInt("id");
            UserRole role = new UserRole(resultSet.getInt(7), resultSet.getString(8));

            Address address = new Address();
            address.setId(resultSet.getInt(9));
            address.setCountry(resultSet.getString(10));
            address.setCity(resultSet.getString(11));
            address.setStreet(resultSet.getString(12));
            address.setNumber(resultSet.getInt(13));

            List<MusicType> listOfMusicType = new ArrayList<>();
            psForUsersMusicPrepare.setInt(1, userId);
            ResultSet resultSetForMusicPrepare = psForUsersMusicPrepare.executeQuery();

            while (resultSetForMusicPrepare.next()) {
                MusicType musicType = new MusicType();
                System.out.println("before getting string from ps ");
                String musicTypeName = resultSetForMusicPrepare.getString("name");
                musicType.setName(musicTypeName);
                System.out.println("MUSIC PREPARE is " + musicTypeName);
                listOfMusicType.add(musicType);
            }

            user = new User(userId,
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getTimestamp("createDate"),
                    resultSet.getString("email"),
                    role, address, listOfMusicType);

        } catch (SQLException sqlExp) {
            LOG.error("Problem with connection trying to get user by id from dao.User class {}", sqlExp);
            throw new DaoException("Problem with getting user by id");
        }
        return user;
    }

    /**
     * Insert user to database.
     * @param user to insert to database.
     * @throws DaoException throw if problem with connection.
     */
    @Override
    public void insert(final User user) throws DaoException {
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT.getQuery())) {
            this.insertAddress(user.getAddress(), connection);
            int addressId = UserStore.STORE.getAddressId(user.getAddress().getCity(),
                    user.getAddress().getStreet(), user.getAddress().getNumber());
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setTimestamp(5, user.getCreateDate());
            ps.setInt(6, user.getRole().getId());
            ps.setInt(7, addressId);
            ps.execute();
            if ((user.getMusicType().size() > 0)) {
                this.insertMusicPrepare(connection, user);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection  invoke by create method {}", sqlExp);
            throw new DaoException("Problem with inserting user");
        }
    }

    /**
     * InsertAddress to the database.
     * @param address user.
     * @param connection user.
     */
    private void insertAddress(final Address address, final Connection connection) {
        try (PreparedStatement psForAddressInsertion = connection.prepareStatement(Queries.INSERT_ADDRESS.getQuery())) {
            psForAddressInsertion.setString(1, address.getCountry());
            psForAddressInsertion.setString(2, address.getCity());
            psForAddressInsertion.setString(3, address.getStreet());
            psForAddressInsertion.setInt(4, address.getNumber());
            psForAddressInsertion.execute();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with address insertion {}", sqlExp);
        }
    }

    /**
     * insertMusicPrepare to insert music type to third table for M:M relationship.
     * @param connection database connection.
     * @param user user in the database.
     * @throws DaoException DAO exception if Problem with getting connection or inserting users music prepare.
     */
    private void insertMusicPrepare(final Connection connection, final User user) throws DaoException {
        int numberOfParam = user.getMusicType().size();
        String modifyQuery = this.modifyQuery(Queries.INSERT_MUSIC_PREPARE.getQuery(), numberOfParam);
        try (PreparedStatement psForGettingUserId = connection.prepareStatement(Queries.GET_USER_ID.getQuery());
             PreparedStatement psForInsertion = connection.prepareStatement(modifyQuery)) {
            psForGettingUserId.setString(1, user.getLogin());
            psForGettingUserId.setString(2, user.getPassword());
            ResultSet resultSet = psForGettingUserId.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            if (numberOfParam == 1) {
                psForInsertion.setInt(1, id);
                psForInsertion.setInt(2, user.getMusicType().get(0).getId());
            } else {
                int counter = 0;
                    List<MusicType> userMusicTypes = user.getMusicType();
                    for (MusicType mt : userMusicTypes) {
                    psForInsertion.setInt(++counter, id);
                    psForInsertion.setInt(++counter, mt.getId());
                    }
            }
            psForInsertion.execute();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection  invoke by create method {}", sqlExp);
            throw new DaoException("Problem with getting connection or insertMusicPrepare users music prepare");
        }
    }

    /**
     * Modify query to insert music type.
     * @param query to modify.
     * @param numberOfParam numbers of queries to append.
     * @return modifyQuery.
     */
    private String modifyQuery(final String query, final int numberOfParam) {
        StringBuilder queryBuilder = new StringBuilder();
        if (numberOfParam == 1) {
            return query;
        } else if (numberOfParam > 1) {
            for (int i = 0; i < numberOfParam; i++) {
                queryBuilder.append(" ");
                queryBuilder.append(query);
            }
        }
        return queryBuilder.toString();
    }

    /**
     * Update user in the database.
     * @param user in the database.
     * @throws DaoException throw if problem with connection through updating the user.
     */
    @Override
    public void update(final User user) throws DaoException {
            try (Connection connection = DBConnection.getInstance().getDBConnection();
                 PreparedStatement ps = connection.prepareStatement(Queries.UPDATE.getQuery())) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getRole().getName());
                ps.setInt(4, user.getId());
                ps.execute();
                connection.close();
            } catch (SQLException sqlExp) {
                LOG.warn("Problem with execution query invoke by update method {}", sqlExp);
                throw new DaoException("Problem with getting user by id");
            }
        }

    /**
     * delete user from the database.
     * @param user in the database.
     * @throws DaoException throw if problem with connection through deleting the user.
     */
    @Override
    public void delete(final User user) throws DaoException {
            try (Connection connection = DBConnection.getInstance().getDBConnection();
                 PreparedStatement psForDeleteMusicPrepare = connection.prepareStatement(Queries.DELETE_MUSIC_PREPARE.getQuery());
                 PreparedStatement psForDeleteAddress = connection.prepareStatement(Queries.DELETE_ADDRESS.getQuery());
                 PreparedStatement psForDeleteUser = connection.prepareStatement(Queries.DELETE.getQuery())) {
                psForDeleteMusicPrepare.setInt(1, user.getId());
                psForDeleteMusicPrepare.execute();
                psForDeleteAddress.setInt(1, user.getAddress().getId());
                psForDeleteAddress.execute();
                psForDeleteUser.setString(1, user.getName());
                psForDeleteUser.setString(2, user.getLogin());
                psForDeleteUser.execute();
                connection.close();
            } catch (SQLException sqlExp) {
                LOG.warn("Problem with execution query invoke by delete method {}", sqlExp);
                throw new DaoException("Problem with deleting a user");
            }
        }
}
