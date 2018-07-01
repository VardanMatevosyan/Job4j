package ru.matevosyan.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matevosyan.database.DBConnection;
import ru.matevosyan.database.Queries;
import ru.matevosyan.entity.Address;
import ru.matevosyan.entity.MusicType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Addresses DAO class.
 */
public class Addresses implements IGeneric<Address, Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(MusicType.class.getName());

    @Override
    public CopyOnWriteArrayList<Address> getAll() throws DaoException {
        CopyOnWriteArrayList<Address> listOfAddresses = new CopyOnWriteArrayList<>();
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Queries.GET_ADDRESS.getQuery());
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                Integer number = resultSet.getInt("number");

                Address address = new Address();
                address.setId(id);
                address.setCountry(country);
                address.setCity(city);
                address.setStreet(street);
                address.setNumber(number);

                listOfAddresses.add(address);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection invoke by getById method in Address dao {}", sqlExp);
            throw new DaoException("Problem with insertion address to DB");
        }
        return listOfAddresses;
    }

    @Override
    public Address getById(Integer id) throws DaoException {
        Address address = new Address();
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.GET_ADDRESS_BY_ID.getQuery())) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                Integer number = resultSet.getInt("number");

                address.setId(id);
                address.setCountry(country);
                address.setCity(city);
                address.setStreet(street);
                address.setNumber(number);
            }
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.warn("Problem with getting connection invoke by getById method in Address dao {}", sqlExp);
            throw new DaoException("Problem with insertion address to DB");
        }
        return address;
    }

    @Override
    public void insert(Address address) throws DaoException {
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT_ADDRESS.getQuery())) {
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getStreet());
            ps.setInt(4, address.getNumber());
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection invoke by insert method in Address dao {}", sqlExp);
            throw new DaoException("Problem with insertion Address to DB");
        }
    }

    @Override
    public void update(Address address) throws DaoException {
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.UPDATE_ADDRESS.getQuery())) {
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getStreet());
            ps.setInt(4, address.getNumber());
            ps.setInt(5, address.getId());
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection invoke by update method in Address dao {}", sqlExp);
            throw new DaoException("Problem with updating Address to DB");
        }
    }

    @Override
    public void delete(Address address) throws DaoException {
        try (Connection connection = DBConnection.INSTANCE.getDBConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.DELETE_ADDRESS.getQuery())) {
            ps.setInt(1, address.getId());
            ps.execute();
            connection.close();
        } catch (SQLException sqlExp) {
            LOG.error("Problem with getting connection invoke by delete method in Address dao {}", sqlExp);
            throw new DaoException("Problem with deleting Address to DB");
        }
    }
}
