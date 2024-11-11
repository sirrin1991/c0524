package com.example.ss9.repository;

import com.example.ss9.model.Category;
import com.example.ss9.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static final List<Product> list;
    private static int lastId;
    private final String SL_PRODUCT = "select * from product";
    private final String INS_PRODUCT = "insert into product(name,price,category_id) " +
            "values (?,?,?)";
    private final String SP_INS = "call insert_product(?,?)";

    static {
        list = new ArrayList<>();
        list.add(new Product(1, "Iphone 16", 2000));
        list.add(new Product(2, "Samsung galaxy", 200));
        list.add(new Product(3, "Redmi note 8", 300));
        lastId = 3;
    }

    private CategoryRepository categoryRepository = new CategoryRepository();

    //Statement -> chỉ sử dụng với các câu lenh select khong co where
    //PreparedStatement
    //CallableStatement -> Store procedure
    //SQLInjection
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        BaseRepository baseRepository = new BaseRepository();
        Connection c = baseRepository.getConnection();
        try {
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(SL_PRODUCT);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int categoryId = resultSet.getInt("category_id");
                Category category = categoryRepository.findById(categoryId);
                products.add(new Product(id, name, price,category));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void save(Product product) {
        BaseRepository baseRepository = new BaseRepository();
        Connection c = baseRepository.getConnection();
        try {
            PreparedStatement preparedStatement = c.prepareStatement(INS_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2,product.getPrice());
            preparedStatement.setInt(3,product.getCategory().getId());
            preparedStatement.executeUpdate();
//            CallableStatement callableStatement = c.prepareCall(SP_INS);
//            callableStatement.setString(1, product.getName());
//            callableStatement.setInt(2, product.getPrice());
//            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
