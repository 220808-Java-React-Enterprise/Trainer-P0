package com.revature.yolp.daos;

import com.revature.yolp.models.Restaurant;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidSQLException;
import com.revature.yolp.utils.database.ConnectionFactory;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAO implements CrudDAO<Restaurant> {
    @Override
    public void save(Restaurant obj) {

    }

    @Override
    public void update(Restaurant obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Restaurant getById(String id) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        List<Restaurant> restaurants = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM restaurants");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Restaurant resto = new Restaurant(rs.getString("id"), rs.getString("name"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zipcode"));
                restaurants.add(resto);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return restaurants;
    }
}
