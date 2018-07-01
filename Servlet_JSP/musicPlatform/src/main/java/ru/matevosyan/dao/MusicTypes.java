package ru.matevosyan.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.DBConnection;
import ru.matevosyan.database.Queries;
import ru.matevosyan.entity.MusicType;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * MusicTypes DAO class.
 */
public class MusicTypes implements IGeneric<MusicType, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(MusicType.class.getName());

    @Override
    public CopyOnWriteArrayList<MusicType> getAll() throws DaoException {
        CopyOnWriteArrayList<MusicType> listOfMusicTypes = new CopyOnWriteArrayList<>();
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Queries.GET_MUSICTYPE.getQuery());
            while (resultSet.next()) {
                String musicTypeValue = resultSet.getString("name");
                Integer musicTypeId = resultSet.getInt("id");
                MusicType musicType = new MusicType();
                musicType.setId(musicTypeId);
                musicType.setName(musicTypeValue);
                listOfMusicTypes.add(musicType);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection invoke by getAll method in MusicTypes dao {}", sqlExp);
            throw new DaoException("Problem with getAll musicTypes to DB");
        }
        return listOfMusicTypes;
    }

    @Override
    public MusicType getById(Integer id) throws DaoException {
        String name;
        MusicType musicType = new MusicType();
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.GET_MUSIC_TYPE_BY_ID.getQuery())) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
                musicType.setId(id);
                musicType.setName(name);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection invoke by getById method in MusicTypes dao {}", sqlExp);
            throw new DaoException("Problem with getById music type to DB");
        }
        return musicType;
    }

    @Override
    public void insert(MusicType musicType) throws DaoException {
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT_MUSIC_TYPE.getQuery())) {
            ps.setString(1, musicType.getName());
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection invoke by insert method in MusicTypes dao {}", sqlExp);
            throw new DaoException("Problem with insertion music type to DB");
        }
    }

    @Override
    public void update(MusicType musicType) throws DaoException {
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.UPDATE_MUSIC_TYPE.getQuery())) {
            ps.setString(1, musicType.getName());
            ps.setInt(2, musicType.getId());
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection invoke by update method in MusicTypes dao {}", sqlExp);
            throw new DaoException("Problem with updating music type to DB");
        }
    }

    @Override
    public void delete(MusicType musicType) throws DaoException {
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.DELETE_MUSIC_TYPE.getQuery())) {
            ps.setInt(1, musicType.getId());
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection invoke by delete method in MusicTypes dao {}", sqlExp);
            throw new DaoException("Problem with deleting music type to DB");
        }
    }
}
