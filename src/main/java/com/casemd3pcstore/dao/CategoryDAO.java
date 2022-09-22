package com.casemd3pcstore.dao;

import com.casemd3pcstore.model.Category;
import com.casemd3pcstore.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/pc_store?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";

    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM categories;";
    private static final String SELECT_CATEGORY_BY_ID = "select cid, category_name from products where cid = ?";


    public CategoryDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Category> selectAllCategories() {
        List<Category> categories = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection ();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement ( SELECT_ALL_CATEGORIES );) {
            System.out.println ( preparedStatement );
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery ();

            // Step 4: Process the ResultSet object.
            while (rs.next ()) {
                int id = rs.getInt ( "cid" );
                String name = rs.getString ( "category_name" );
                categories.add ( new Category ( id, name ) );
            }
        } catch (SQLException e) {
            printSQLException ( e );
        }
        return categories;
    }

    @Override
    public Category findById(int categoryId) {
        Category category = null;

        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);) {
            preparedStatement.setInt(1, categoryId);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("cid");
                String nameCategory = rs.getString("category_name");

                category = new Category(id, nameCategory);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return category;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
