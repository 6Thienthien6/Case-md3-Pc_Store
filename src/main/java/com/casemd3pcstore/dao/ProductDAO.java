package com.casemd3pcstore.dao;

import com.casemd3pcstore.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/pc_store?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO pc_store.products ( `img`, `name`, `quantity`, `price`, `category_id`, `create_at`,`create_by`)VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID = "select id, img, name, quantity, price, category_id from products where id = ?";
    private static final String SELECT_ALL_PRODUCTS = "select id, img, name, quantity, price, category_id from products";
    private static final String DELETE_PRODUCT_SQL = "delete from products where id =?";
    private static final String UPDATE_PRODUCTS_SQL = "update products set img = ?, name=?, quantity=?, price = ?, category_id=? where id = ?";

    private static final String SELECT_BY_CATEGORY = "select id, img, name, quantity, price, category_id from products WHERE category_id = ?;";
    private static String PRODUCT_EXIST_BY_ID = "" +
            "SELECT COUNT(*) AS COUNT " +
            "FROM `products` AS p " +
            "WHERE p.id = ?;";
    public ProductDAO() {

    }

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
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

    public List<Product> findAll() {

        List<Product> list = new ArrayList<>();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
//select id, img, name, quantity, price from products
            System.out.println(this.getClass() + " findAll : " + statement);
            ResultSet rs = statement.executeQuery(SELECT_ALL_PRODUCTS);
            while (rs.next()) {
                int id = rs.getInt("id");
                String img = rs.getString("img");
                String name = rs.getString("name");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                int categoryId = rs.getInt("category_id");

                Product product = new Product(id, img, name, quantity, price, categoryId);
                list.add(product);
            }
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return list;
    }

    public List<Product> getListByCategory(int categoryId) {

        List<Product> list = new ArrayList<>();
        Product product = null;

        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_CATEGORY);) {
            preparedStatement.setInt(1, categoryId);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String img = rs.getString("img");
                String name = rs.getString("name");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                int category_id = rs.getInt("category_id");


                product = new Product(id, img, name, quantity, price, category_id);
                list.add(product);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
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

    public boolean insertProduct(Product product) throws SQLException {
//        String INSERT_PRODUCTS_SQL = "insert into products(img, name, quantity, price, categoryId, createAt, createBy) values(?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println(INSERT_PRODUCTS_SQL);
        boolean result = false;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
            preparedStatement.setString(1, product.getImg());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getQuantity());
            preparedStatement.setString(4, product.getPrice());
            preparedStatement.setInt(5, product.getCategoryId());
            preparedStatement.setDate(6, product.getCreateAt());
            preparedStatement.setString(7, product.getCreateBy());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            printSQLException(e);

        }
        return result;
    }


    public Product selectProduct(int id) {
        Product product = null;

        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String img = rs.getString("img");
                String name = rs.getString("name");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");

                product = new Product(id, img, name, quantity, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    public List<Product> selectAllProduct() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Product> products = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String img = rs.getString("img");
                String name = rs.getString("name");
                String quantity = rs.getString("quantity");
                String price = rs.getString("price");
                int categoryId = rs.getInt("categoryId");
                products.add(new Product(id, img, name, quantity, price, categoryId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public List<Product> searchProduct(String search) throws SQLException {
        return null;
    }

    @Override
    public List<Product> getNumberPage(int offset, int noOfRecords, String name) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        System.out.println("numberpage");

        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM products where name LIKE ? OR price LIKE ? OR quantity LIKE ? limit " + offset + "," + noOfRecords;
        List<Product> list = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, '%' + name + '%');
        ps.setString(2, '%' + name + '%');
        ps.setString(3, '%' + name + '%');
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getString("price"));
            product.setQuantity(rs.getString("quantity"));
            product.setImg(rs.getString("img"));
            product.setCategoryId(rs.getInt("category_id"));
            list.add(product);
        }
        rs = ps.executeQuery("SELECT FOUND_ROWS()");
        if (rs.next()){
            this.noOfRecords = rs.getInt(1);
        }
        connection.close();
        return list;
    }

    @Override
    public List<Product> getNumberPageSortPriceASC(int offset, int noOfRecords, String name) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
//        System.out.println("numberpage");

        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM products where name LIKE ? OR price LIKE ? OR quantity LIKE ? ORDER BY price ASC limit " + offset + "," + noOfRecords;
        List<Product> list = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, '%' + name + '%');
        ps.setString(2, '%' + name + '%');
        ps.setString(3, '%' + name + '%');
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getString("price"));
            product.setQuantity(rs.getString("quantity"));
            product.setImg(rs.getString("img"));
            product.setCategoryId(rs.getInt("category_id"));
            list.add(product);
        }
        rs = ps.executeQuery("SELECT FOUND_ROWS()");
        if (rs.next()){
            this.noOfRecords = rs.getInt(1);
        }
        connection.close();
        return list;
    }

    @Override
    public List<Product> getNumberPageSortPriceDESC(int offset, int noOfRecords, String name) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
//        System.out.println("numberpage");

        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM products where name LIKE ? OR price LIKE ? OR quantity LIKE ? ORDER BY price DESC limit " + offset + "," + noOfRecords;
        List<Product> list = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, '%' + name + '%');
        ps.setString(2, '%' + name + '%');
        ps.setString(3, '%' + name + '%');
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getString("price"));
            product.setQuantity(rs.getString("quantity"));
            product.setImg(rs.getString("img"));
            product.setCategoryId(rs.getInt("category_id"));
            list.add(product);
        }
        rs = ps.executeQuery("SELECT FOUND_ROWS()");
        if (rs.next()){
            this.noOfRecords = rs.getInt(1);
        }
        connection.close();
        return list;
    }

    public boolean update(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
            statement.setString(1, product.getImg());
            statement.setString(2, product.getName());
            statement.setString(3, product.getQuantity());
            statement.setString(4, product.getPrice());
            statement.setDouble(5, product.getCategoryId());
            statement.setInt(6, product.getId());


            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public Product findById(int id) throws SQLException {
        String PRODUCT_BY_ID = "SELECT id, img, name, quantity, price, category_id FROM pc_store.products WHERE id = ?";
        Product product;

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_BY_ID);
        preparedStatement.setInt(1, id);

        System.out.println(this.getClass() + " findById : " + preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int id1 = rs.getInt("id");
            String img = rs.getString("img");
            String name = rs.getString("name");
            String quantity = rs.getString("quantity");
            String price = rs.getString("price");
            int categoryId = Integer.parseInt(rs.getString("category_id"));

            product = new Product(id, img, name, quantity, price, categoryId);
            return product;
        }
        return null;
    }

    public boolean existByProductId(int id) {
        boolean exist = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareCall(PRODUCT_EXIST_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

}
