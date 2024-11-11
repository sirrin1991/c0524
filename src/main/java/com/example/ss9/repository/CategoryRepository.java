package com.example.ss9.repository;

import com.example.ss9.model.Category;
import com.example.ss9.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    private final String FIND_BY_ID = "select * from category where id = ?";
    private final String FIND_ALL = "select * from category";
    public Category findById(int id) {
        Category category = null;
        BaseRepository baseRepository = new BaseRepository();
        Connection c = baseRepository.getConnection();
        try {
            PreparedStatement preparedStatement = c.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        BaseRepository baseRepository = new BaseRepository();
        Connection c = baseRepository.getConnection();
        try {
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
}
