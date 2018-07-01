package ru.matevosyan.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.dao.Users;
import ru.matevosyan.database.DBConnection;
import ru.matevosyan.database.Queries;
import ru.matevosyan.database.UserStore;
import ru.matevosyan.entity.Address;
import ru.matevosyan.entity.MusicType;
import ru.matevosyan.entity.User;
import ru.matevosyan.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserRepository class.
 */
public class UserRepository implements IUserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(Users.class.getName());

    @Override
    public Map getAllUsersEntity()  throws SQLException {
        Map<Integer, User> users = new HashMap<>();
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
                User user = new User(userId, resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getTimestamp("createDate"),
                        resultSet.getString("email"), role, address, listOfMusicType);
                users.put(userId, user);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with execution query invoke by get method {}", sqlExp);
            throw new SQLException("Problem with getting All user");
        }
        return users;
    }

    @Override
    public void add(User user) throws SQLException {
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
            throw new SQLException("Problem from UserRepository with inserting user");
        }
    }

    @Override
    public List<User> find(Address address) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement psForUsersMusicPrepare = connection
                     .prepareStatement(Queries.SELECT_USER_MUSIC_PREPARE.getQuery());
             PreparedStatement ps = connection.prepareStatement(Queries.GET_USERS_BY_ADDRESS.getQuery())) {
            ps.setInt(1, address.getId());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                UserRole role = new UserRole(resultSet.getInt(7), resultSet.getString(8));
                List<MusicType> musicTypes = new ArrayList<>();
                Integer userId = resultSet.getInt("id");
                psForUsersMusicPrepare.setInt(1, userId);
                ResultSet resultSetForMusicPrepare = psForUsersMusicPrepare.executeQuery();

                while (resultSetForMusicPrepare.next()) {
                    MusicType musicType = new MusicType();
                    String musicTypeName = resultSetForMusicPrepare.getString("name");
                    musicType.setName(musicTypeName);
                    musicTypes.add(musicType);
                }

                Address userAddress = new Address();
                userAddress.setId(resultSet.getInt(7));
                userAddress.setCountry(resultSet.getString(8));
                userAddress.setCity(resultSet.getString(9));
                userAddress.setStreet(resultSet.getString(10));
                userAddress.setNumber(resultSet.getInt(11));

                User user = new User(
                        userId,
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getTimestamp("createDate"),
                        resultSet.getString("email"),
                      role,
                        userAddress,
                        musicTypes
                );
                users.add(user);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection  invoke by find method {}", sqlExp);
            throw new SQLException("Problem from UserRepository with finding user by address");
        }
        return users;
    }


    @Override
    public List<User> find(UserRole role) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement psForUsersMusicPrepare = connection
                     .prepareStatement(Queries.SELECT_USER_MUSIC_PREPARE.getQuery());
             PreparedStatement ps = connection.prepareStatement(Queries.GET_USERS_BY_ROLE.getQuery())) {
            ps.setInt(1, role.getId());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                List<MusicType> musicTypes = new ArrayList<>();
                Integer userId = resultSet.getInt("id");
                psForUsersMusicPrepare.setInt(1, userId);
                ResultSet resultSetForMusicPrepare = psForUsersMusicPrepare.executeQuery();
                while (resultSetForMusicPrepare.next()) {
                    MusicType musicType = new MusicType();
                    String musicTypeName = resultSetForMusicPrepare.getString("name");
                    musicType.setName(musicTypeName);
                    musicTypes.add(musicType);
                }

                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setNumber(resultSet.getInt("number"));

                UserRole userRole = new UserRole(resultSet.getInt(12),
                        resultSet.getString(13));

                User user = new User(
                        userId,
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getTimestamp("createDate"),
                        resultSet.getString("email"),
                        userRole,
                        address,
                        musicTypes
                );
                users.add(user);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection  invoke by find method {}", sqlExp);
            throw new SQLException("Problem from UserRepository with finding user by address");
        }
        return users;
    }


    @Override
    public List<User> find(MusicType musicType) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getDBConnection();
             PreparedStatement psForUsersMusicPrepare = connection
                     .prepareStatement(Queries.SELECT_USER_MUSIC_PREPARE.getQuery());
             PreparedStatement ps = connection.prepareStatement(Queries.GET_USERS_BY_MUSIC_PREPARE.getQuery())) {
            ps.setInt(1, musicType.getId());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                UserRole role = new UserRole(resultSet.getInt(12), resultSet.getString(13));
                List<MusicType> musicTypes = new ArrayList<>();
                Integer userId = resultSet.getInt("id");
                psForUsersMusicPrepare.setInt(1, userId);
                ResultSet resultSetForMusicPrepare = psForUsersMusicPrepare.executeQuery();

                while (resultSetForMusicPrepare.next()) {
                    MusicType musicUserType = new MusicType();
                    String musicTypeName = resultSetForMusicPrepare.getString("name");
                    musicType.setName(musicTypeName);
                    musicTypes.add(musicType);
                }

                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setNumber(resultSet.getInt("number"));

                User user = new User(
                        userId,
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getTimestamp("createDate"),
                        resultSet.getString("email"),
                        role,
                        address,
                        musicTypes
                );
                users.add(user);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection  invoke by find method {}", sqlExp);
            throw new SQLException("Problem from UserRepository with finding user by address");
        }
        return users;
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
     * @throws SQLException sql exception if Problem with getting connection or inserting users music prepare.
     */
    private void insertMusicPrepare(final Connection connection, final User user) throws SQLException {
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
                System.out.println("User id is -> " + id);
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
            throw new SQLException("Problem from UserRepository with getting connection or inserting users music prepare");
        }
    }

    /**
     * Modify query to insert music type.
     * @param query to modify.
     * @param numberOfParam number of queries.
     * @return modified query.
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

}
